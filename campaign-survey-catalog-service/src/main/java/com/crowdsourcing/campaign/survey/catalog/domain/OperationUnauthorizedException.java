package com.crowdsourcing.campaign.survey.catalog.domain;

public class OperationUnauthorizedException extends Exception {

    public OperationUnauthorizedException(String message) {
        super(message);
    }
}
