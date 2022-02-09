package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.*;

@Getter
public class SurveyPassportDescription {

    String name;
    String participantPassportRequirements;
    String surveyCatalogId;

    //единственный законный способ создавать SurveyPassportDescription
    public static SurveyPassportDescription create(String name, String participantPassportRequirements, String surveyCatalogId) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Не хватает наименования опроса");
        }
        if (surveyCatalogId == null || surveyCatalogId.isBlank()) {
            throw new IllegalArgumentException("Не указан каталог опросов");
        }
        String ppr = (participantPassportRequirements == null) ? "" : participantPassportRequirements.trim();
        return new SurveyPassportDescription(name.trim(), ppr, surveyCatalogId.trim());
    }

    private SurveyPassportDescription(String name, String participantPassportRequirements, String surveyCatalogId) {
        this.name = name;
        this.participantPassportRequirements = participantPassportRequirements;
        this.surveyCatalogId = surveyCatalogId;
    }
}
