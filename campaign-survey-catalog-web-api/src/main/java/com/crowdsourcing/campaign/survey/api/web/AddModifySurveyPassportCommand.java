package com.crowdsourcing.campaign.survey.api.web;

import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportDescription;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AddModifySurveyPassportCommand {

    private String name;
    private String participantPassportRequirements;
    private String surveyCatalogId;

    public SurveyPassportDescription getSurveyPassportDescription() {
        return SurveyPassportDescription.create(name, participantPassportRequirements, surveyCatalogId);
    }
}
