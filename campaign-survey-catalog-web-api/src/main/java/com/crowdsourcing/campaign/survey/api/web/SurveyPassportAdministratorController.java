package com.crowdsourcing.campaign.survey.api.web;

import com.crowdsourcing.campaign.survey.catalog.domain.NotificationException;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportDescription;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportOperationException;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyPassportAdministratorService;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyPassportNotFoundException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1.0/survey-passport-catalog")
@Api("Актуализация паспорта опроса")
public class SurveyPassportAdministratorController {

    private final SurveyPassportAdministratorService surveyPassportAdministratorService;

    @PostMapping("/survey-passport")
    @ApiOperation("Создание паспорта опроса")
    public ResponseEntity<AddModifySurveyPassportResult> addSurveyPassport(@RequestBody AddModifySurveyPassportCommand addModifySurveyPassportCommand) throws IllegalArgumentException, SurveyPassportOperationException {
        SurveyPassportDescription surveyPassportDescription = addModifySurveyPassportCommand.getSurveyPassportDescription();
        String id = surveyPassportAdministratorService.addNewSurveyPassport(surveyPassportDescription);
        return ResponseEntity.ok(new AddModifySurveyPassportResult(id));
    }

    @PutMapping("/survey-passport/{surveyPassportId}")
    @ApiOperation("Изменение паспорта опроса")
    public void modifySurveyPassport(@PathVariable String surveyPassportId, @RequestBody AddModifySurveyPassportCommand addModifySurveyPassportCommand) throws IllegalArgumentException, SurveyPassportOperationException, SurveyPassportNotFoundException {
        if(surveyPassportId == null || surveyPassportId.isBlank()) {
            throw new IllegalArgumentException("Не указан идентификатор опроса");
        }
        SurveyPassportDescription surveyPassportDescription = addModifySurveyPassportCommand.getSurveyPassportDescription();
        surveyPassportAdministratorService.modifySurveyPassport(surveyPassportId, surveyPassportDescription);
    }

    @DeleteMapping("/survey-passport/{surveyPassportId}")
    @ApiOperation("Удаление паспорта опроса")
    public void deleteSurveyPassport(@PathVariable String surveyPassportId) throws SurveyPassportOperationException {
        if(surveyPassportId == null || surveyPassportId.isBlank()) {
            throw new IllegalArgumentException("Не указан идентификатор опроса");
        }
        surveyPassportAdministratorService.deleteSurveyPassport(surveyPassportId);
    }


}
