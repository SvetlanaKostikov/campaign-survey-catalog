package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

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
    private String participantPassportRequirements;


    public SurveyPassportEventPayload(String id, String name, String surveyCatalogId, String participantPassportRequirements) {
        this.id = id;
        this.name = name;
        this.surveyCatalogId = surveyCatalogId;
        this.participantPassportRequirements = participantPassportRequirements;
    }
}
