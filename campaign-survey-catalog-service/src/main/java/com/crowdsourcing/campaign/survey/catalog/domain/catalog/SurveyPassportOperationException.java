package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

public class SurveyPassportOperationException extends Exception {

    public SurveyPassportOperationException(String message) {
        super(message);
    }

    public SurveyPassportOperationException(String message, Throwable cause) {
        super(message, cause);
    }
}
