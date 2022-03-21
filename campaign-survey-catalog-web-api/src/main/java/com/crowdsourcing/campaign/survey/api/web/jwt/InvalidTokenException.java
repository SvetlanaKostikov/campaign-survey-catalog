package com.crowdsourcing.campaign.survey.api.web.jwt;

public class InvalidTokenException extends RuntimeException{

    public InvalidTokenException(String message){
        super(message);
    }
}
