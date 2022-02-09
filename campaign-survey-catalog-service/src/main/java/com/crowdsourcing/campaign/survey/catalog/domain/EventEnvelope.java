package com.crowdsourcing.campaign.survey.catalog.domain;

import com.crowdsourcing.campaign.survey.catalog.api.events.CampaignEventPayload;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@MappedSuperclass
@TypeDef(name = "EventPayloadType", typeClass = JsonBinaryType.class)
public class EventEnvelope<T> {
    /**
     * Идентификатор события
     */
    @Id
    @Column(name = "event_id")
    private String eventId;

    /**
     * Контекст события
     */
    @Column(name = "event_occurring_context")
    private String eventOccurringContext;

    /**
     * Тип события
     */
    @Column(name = "event_type")
    private String eventType;

    /**
     * Версия
     */
    @Column(name = "event_version")
    private String eventVersion;

    /**
     * Время события
     */
    @Column(name = "event_occurring_time")
    private LocalDateTime eventOccurringTime;

    /**
     * Id объекта
     */
    @Column(name = "event_entity_id")
    private String eventEntityId;

    /**
     * Объект
     */
    @Type(type = "EventPayloadType")
    @Column(name = "event_payload", columnDefinition = "jsonb")
    private T eventPayload;

    public EventEnvelope(String eventId, String eventOccurringContext, String eventAuthor, String eventType, String eventVersion, LocalDateTime eventOccurringTime, String eventEntityId, CampaignEventPayload eventPayload) {
    }
}
