package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import com.crowdsourcing.campaign.survey.catalog.domain.NotificationException;
import com.crowdsourcing.campaign.survey.catalog.domain.EventType;
import com.crowdsourcing.campaign.survey.catalog.domain.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SurveyPassportOperations {


    @Qualifier(value = "surveyPassportNotificationService")
    private final NotificationService<SurveyPassportEvent> notificationService;
    private final SurveyCatalogRepository surveyCatalogRepository;

    public String addSurveyPassport(SurveyPassportDescription surveyPassportDescription) throws SurveyPassportOperationException {
        //проверка на роль - 403
        Optional<SurveyCatalog> surveyCatalog = surveyCatalogRepository.findById(surveyPassportDescription.getSurveyCatalogId());

        if (surveyCatalog.isEmpty()) {
            throw new SurveyPassportOperationException("Каталог опросов не обнаружен");
        }

        if (!surveyCatalog.get().getCapabilities().canModify()) {
            throw new SurveyPassportOperationException("Каталог не допускает изменение паспортов опросов");
        }

        try {
            String id = UUID.randomUUID().toString();
            //кто автор ивента
            SurveyPassportEvent event = SurveyPassportEvent.from(id, surveyPassportDescription, EventType.SURVEY_PASSPORT_CREATED);
            notificationService.send(event);
            return id;
        } catch (Exception e) {
            throw new SurveyPassportOperationException("Не удалось выполнить операцию", e);
        }
    }

    public void modifySurveyPassport(SurveyPassport surveyPassport, SurveyPassportDescription surveyPassportDescription) throws SurveyPassportOperationException {

        Optional<SurveyCatalog> surveyCatalog = surveyCatalogRepository.findById(surveyPassport.getSurveyCatalogId());

        if(surveyCatalog.isEmpty()){
            throw new SurveyPassportOperationException("Каталог опросов не обнаружен");
        }

        if (!surveyPassport.allowsModification()) {
            throw new SurveyPassportOperationException("Паспорт опроса нельзя изменять");
        }

        if(!surveyCatalog.get().getCapabilities().canModify()){
            throw new SurveyPassportOperationException("Каталог не допускает изменение паспортов опросов");
        }

        try {
            SurveyPassportEvent event = SurveyPassportEvent.from(surveyPassport.getId(), surveyPassportDescription, EventType.SURVEY_PASSPORT_MODIFIED);
            notificationService.send(event);
        } catch (Exception e) {
                throw new SurveyPassportOperationException("Не удалось выполнить операцию", e);
        }
    }

    public void deleteSurveyPassport(SurveyPassport surveyPassport) throws SurveyPassportOperationException {
        Optional<SurveyCatalog> surveyCatalog = surveyCatalogRepository.findById(surveyPassport.getSurveyCatalogId());

        if(surveyCatalog.isEmpty()){
            throw new SurveyPassportOperationException("Каталог опросов не обнаружен");
        }

        if (surveyPassport.isDeleted()) {
            return;
        }

        if(!surveyCatalog.get().getCapabilities().canModify()){
            throw new SurveyPassportOperationException("Каталог не допускает удаление опросов");
        }

        try {
            SurveyPassportEvent event = SurveyPassportEvent.from(surveyPassport, EventType.SURVEY_PASSPORT_DELETED);
            notificationService.send(event);
        } catch (Exception e) {
            throw new SurveyPassportOperationException("Не удалось выполнить операцию", e);
        }
    }
}
