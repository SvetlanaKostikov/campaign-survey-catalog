package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import com.crowdsourcing.campaign.survey.catalog.domain.EventEnvelope;
import com.crowdsourcing.campaign.survey.catalog.domain.EventType;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "survey_passport_event")
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class SurveyPassportEvent extends EventEnvelope<SurveyPassportEventPayload> implements Serializable {

    public static SurveyPassportEvent from(String surveyPassportId, SurveyPassportDescription surveyPassportDescription, EventType type) {
        String eventId = UUID.randomUUID().toString();

        SurveyPassportEventPayload eventPayload = new SurveyPassportEventPayload(
                surveyPassportId,
                surveyPassportDescription.getName(),
                surveyPassportDescription.getSurveyCatalogId(),
                surveyPassportDescription.getParticipantPassportRequirements()
        );

        return new SurveyPassportEvent(
                eventId,
                "survey-catalog",
                type.toString(),
                "1.0",
                LocalDateTime.now(),
                surveyPassportId,
                eventPayload
        );
    }

    public static SurveyPassportEvent from(SurveyPassport surveyPassport, EventType type){
        String eventId = UUID.randomUUID().toString();

        SurveyPassportEventPayload eventPayload = new SurveyPassportEventPayload(
                surveyPassport.getId(),
                null,
                surveyPassport.getSurveyCatalogId(),
                null
        );

        return new SurveyPassportEvent(
                eventId,
                "survey-catalog",
                type.toString(),
                "1.0",
                LocalDateTime.now(),
                surveyPassport.getId(),
                eventPayload
        );
    }



    public SurveyPassportEvent(String eventId, String eventOccurringContext, String eventType, String eventVersion, LocalDateTime eventOccurringTime, String eventEntityId, SurveyPassportEventPayload eventPayload) {
        super(eventId, eventOccurringContext, eventType, eventVersion, eventOccurringTime, eventEntityId, eventPayload);
    }

    public EventType extractEventType() {
        return EventType.valueOf(getEventType());
    }

}
