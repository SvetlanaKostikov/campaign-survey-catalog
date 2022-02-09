package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.Getter;

import java.util.List;

@Getter
public class SurveyCatalogCapabilities {
    private List<String> capabilities;

    public SurveyCatalogCapabilities(List<String> capabilities) {
        this.capabilities = capabilities;
    }

    public boolean canModify() {
        return capabilities.contains("MODIFY");
    }


}
