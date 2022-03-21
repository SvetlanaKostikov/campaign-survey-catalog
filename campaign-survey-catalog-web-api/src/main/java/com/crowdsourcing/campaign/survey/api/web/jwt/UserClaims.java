package com.crowdsourcing.campaign.survey.api.web.jwt;

public enum UserClaims {

    SUB("sub"),
    USER_TYPE("user_type"),
    EMPLOYEE_NUMBER("employee_number");

    private final String name;

    UserClaims(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }
}
