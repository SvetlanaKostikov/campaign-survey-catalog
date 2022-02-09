package com.crowdsourcing.campaign.survey.api.web;

import com.crowdsourcing.campaign.survey.catalog.api.events.CampaignEventPayload;
import com.crowdsourcing.campaign.survey.catalog.api.events.SurveyCatalogCommandConverter;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalog;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalogCapabilities;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyCatalogActualizationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/survey-catalog")
@Api("Актуализация кампании")
public class CampaignEventDeveloperController {

    private final SurveyCatalogActualizationService surveyCatalogActualizationService;
    private final SurveyCatalogCommandConverter surveyCatalogCommandConverter;

    @PostMapping("/add-campaign")
    @ApiOperation("Создание кампании. Превращение ее в справочник опросов")
    public void addNewCampaign(@RequestBody CampaignEventPayload campaignEventPayload) {
        SurveyCatalogCapabilities capabilities = surveyCatalogCommandConverter.convertCampaignStatusToCapabilities(campaignEventPayload.getStatus());
        surveyCatalogActualizationService.addNewSurveyCatalog(campaignEventPayload.getId(),campaignEventPayload.getName(),
                campaignEventPayload.getOwnerId(),capabilities);
    }

    @GetMapping("/get-surveycatalog")
    @ApiOperation("Получение справочника опросов по идентификатору")
    public SurveyCatalog getSurveyCatalogById(@RequestParam String id) {
        return surveyCatalogActualizationService.getSurveyCatalogById(id);
    }


}
