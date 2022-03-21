package com.crowdsourcing.campaign.survey.catalog.domain;

public class OperationForbiddenException extends Exception {

    public OperationForbiddenException(String message) {
        super(message);
    }

}
