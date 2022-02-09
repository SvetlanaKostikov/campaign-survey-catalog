package com.crowdsourcing.campaign.survey.catalog.api.events;

import com.crowdsourcing.campaign.survey.catalog.domain.EventEnvelope;
import com.crowdsourcing.campaign.survey.catalog.domain.EventType;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
public class CampaignEvent extends EventEnvelope<CampaignEventPayload> implements Serializable {

    public CampaignEvent(String eventId, String eventOccurringContext, String eventAuthor, String eventType, String eventVersion, LocalDateTime eventOccurringTime, String eventEntityId, CampaignEventPayload eventPayload) {
        super(eventId, eventOccurringContext, eventAuthor, eventType, eventVersion, eventOccurringTime, eventEntityId, eventPayload);
    }

    public EventType extractEventType() {
        return EventType.valueOf(getEventType());
    }

}
