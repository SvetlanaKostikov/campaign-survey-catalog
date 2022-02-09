package com.crowdsourcing.campaign.survey.catalog.api.events;

import com.crowdsourcing.campaign.survey.catalog.domain.catalog.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurveyCatalogCommandConverter {


    public SurveyCatalogCapabilities convertCampaignStatusToCapabilities(String status) {
        switch (status) {
            case "CREATED":
                return new SurveyCatalogCapabilities(List.of("MODIFY", "CHANGE", "ADD", "DELETE"));
            default :
                return new SurveyCatalogCapabilities(Collections.emptyList());
        }

    }
}




