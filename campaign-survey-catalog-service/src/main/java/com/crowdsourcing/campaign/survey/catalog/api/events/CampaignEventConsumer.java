package com.crowdsourcing.campaign.survey.catalog.api.events;

import com.crowdsourcing.campaign.survey.catalog.domain.EventType;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalogCapabilities;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyCatalogActualizationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@RequiredArgsConstructor
@Slf4j
public class CampaignEventConsumer {

    private final SurveyCatalogActualizationService surveyCatalogActualizationService;
    private final SurveyCatalogCommandConverter surveyCatalogCommandConverter;

    /*
    получаем событие из модуля campaign-participation
    */
    @KafkaListener(topics = "campaign_event")
    public void registerCampaignASaSurveyCatalog(CampaignEvent event){

        final String TYPE = "CREATED";

        log.info("Получено событие: '{}'", event);
        try {
            String id = event.getEventPayload().getId();
            String name = event.getEventPayload().getName();
            String ownerId = event.getEventPayload().getOwnerId();
            SurveyCatalogCapabilities capabilities = surveyCatalogCommandConverter.convertCampaignStatusToCapabilities(event.getEventPayload().getStatus());
            if (TYPE.equals(event.extractEventType())){
                surveyCatalogActualizationService.addNewSurveyCatalog(id, name, ownerId, capabilities);
            } else {
                surveyCatalogActualizationService.modifySurveyCatalog(id, name, ownerId, capabilities);
            }

            log.info("Событие успешно обработано: '{}'", event);
        } catch (Exception e) {
            log.error("Ошибка обработки события - {}", event, e);
        }
    }




}
