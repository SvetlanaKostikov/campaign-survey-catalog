package com.crowdsourcing.campaign.survey.catalog.domain;

public interface NotificationService <T extends EventEnvelope>{
    void send(T event) throws NotificationException;
}
