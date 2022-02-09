package com.crowdsourcing.campaign.survey.catalog.domain.infrastructure;


import com.crowdsourcing.campaign.survey.catalog.domain.EventEnvelope;
import com.crowdsourcing.campaign.survey.catalog.domain.NotificationException;
import com.crowdsourcing.campaign.survey.catalog.domain.NotificationService;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * Служба отправки событий в брокер событий
 */
@Component(value = "surveyPassportNotificationService")
@RequiredArgsConstructor
@Slf4j
public class SurveyPassportNotificationService implements NotificationService<SurveyPassportEvent> {

    public static final String TOPIC_NAME = "survey_passport";

    private final KafkaTemplate<String, EventEnvelope> kafkaTemplate;


    /**
     * Отправка события в kafka
     * @param event
     * @throws NotificationException
     */
    @Override
    public void send(SurveyPassportEvent event) throws NotificationException {
        ListenableFuture<SendResult<String, EventEnvelope>> future = kafkaTemplate.send(
                TOPIC_NAME,
                event.getEventId(),
                event
        );

        if (future.isCancelled()) {
            log.error("Событие \"" + TOPIC_NAME + "\" не отправлено: {}", event);
            throw new NotificationException("Событие " + TOPIC_NAME + " не отправлено");
        }

        log.info("Событие \"" + TOPIC_NAME + "\" отправлено: {}", event);
    }

}




