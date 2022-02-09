package com.crowdsourcing.campaign.survey.catalog.api.events;

import com.crowdsourcing.campaign.survey.catalog.domain.infrastructure.SurveyPassportNotificationService;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportEvent;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyPassportEventService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@EnableKafka
@RequiredArgsConstructor
@Slf4j
public class SurveyPassportEventConsumer {

    private final SurveyPassportEventService surveyPassportEventService;

    @KafkaListener(topics = SurveyPassportNotificationService.TOPIC_NAME)
    public void surveyPassportAddedEventListener(SurveyPassportEvent event) {
        log.info("Получено событие: '{}'", event);

        try {
            surveyPassportEventService.handle(event);

            log.info("Событие успешно обработано: '{}'", event);
        } catch (Exception e) {
            log.error("Ошибка обработки события - {}", event, e);
        }
    }


}
