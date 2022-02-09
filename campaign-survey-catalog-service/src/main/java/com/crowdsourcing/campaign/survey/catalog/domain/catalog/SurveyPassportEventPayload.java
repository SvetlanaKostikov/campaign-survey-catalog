package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
public class SurveyPassportEventPayload {

    private String id;
    private String name;
    private String surveyCatalogId;
    private String accessRules;


    public SurveyPassportEventPayload(String id, String name, String surveyCatalogId, String accessRules) {
        this.id = id;
        this.name = name;
        this.surveyCatalogId = surveyCatalogId;
        this.accessRules = accessRules;
    }
}
