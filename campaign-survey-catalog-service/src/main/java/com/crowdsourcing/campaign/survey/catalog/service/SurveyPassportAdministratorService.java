package com.crowdsourcing.campaign.survey.catalog.service;

import com.crowdsourcing.campaign.survey.catalog.domain.NotificationException;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SurveyPassportAdministratorService {

    private final SurveyPassportOperations surveyPassportOperations;
    private final SurveyPassportRepository surveyPassportRepository;

    public String addNewSurveyPassport(SurveyPassportDescription surveyPassportDescription) throws SurveyPassportOperationException {
        //проверка на аутентификацию пользователя - 401 установление личности
        return surveyPassportOperations.addSurveyPassport(surveyPassportDescription);
    }

    public void modifySurveyPassport(String surveyPassportId, SurveyPassportDescription surveyPassportDescription) throws SurveyPassportOperationException, SurveyPassportNotFoundException {
       Optional<SurveyPassport> surveyPassport =  surveyPassportRepository.findSurveyPassportById(surveyPassportId);

       if (surveyPassport.isEmpty()) {
           throw new SurveyPassportNotFoundException("Опрос не найден");
       }
       surveyPassportOperations.modifySurveyPassport(surveyPassport.get(), surveyPassportDescription);
    }

    public void deleteSurveyPassport(String surveyPassportId) throws SurveyPassportOperationException {
        Optional<SurveyPassport> surveyPassport =  surveyPassportRepository.findSurveyPassportById(surveyPassportId);

        if (surveyPassport.isPresent()) {
            surveyPassportOperations.deleteSurveyPassport(surveyPassport.get());
        }

    }
}
