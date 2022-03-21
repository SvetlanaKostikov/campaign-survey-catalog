package com.crowdsourcing.campaign.survey.api.web;

import com.crowdsourcing.campaign.survey.api.web.jwt.UserOperations;
import com.crowdsourcing.campaign.survey.catalog.domain.RegisteredUser;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportDescription;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportOperationException;
import com.crowdsourcing.campaign.survey.catalog.service.SurveyPassportAdministratorService;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyPassportNotFoundException;
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
    private final UserOperations userOperations;

    @PostMapping("/survey-passport")
    @ApiOperation("Создание паспорта опроса")
    public ResponseEntity<AddModifySurveyPassportResult> addSurveyPassport(@RequestHeader("Authorization") String token,
                                                                           @RequestBody AddModifySurveyPassportCommand addModifySurveyPassportCommand) throws IllegalArgumentException, SurveyPassportOperationException {
        SurveyPassportDescription surveyPassportDescription = addModifySurveyPassportCommand.getSurveyPassportDescription();
        RegisteredUser user = userOperations.createFrom(token);
        String id = surveyPassportAdministratorService.addNewSurveyPassport(surveyPassportDescription, user);
        return ResponseEntity.ok(new AddModifySurveyPassportResult(id));
    }

    @PutMapping("/survey-passport/{surveyPassportId}")
    @ApiOperation("Изменение паспорта опроса")
    public void modifySurveyPassport(@RequestHeader("Authorization") String token,
                                     @PathVariable String surveyPassportId,
                                     @RequestBody AddModifySurveyPassportCommand addModifySurveyPassportCommand) throws IllegalArgumentException, SurveyPassportOperationException, SurveyPassportNotFoundException {
        if(surveyPassportId == null || surveyPassportId.isBlank()) {
            throw new IllegalArgumentException("Не указан идентификатор опроса");
        }
        RegisteredUser user = userOperations.createFrom(token);
        SurveyPassportDescription surveyPassportDescription = addModifySurveyPassportCommand.getSurveyPassportDescription();
        surveyPassportAdministratorService.modifySurveyPassport(surveyPassportId, surveyPassportDescription, user);
    }

    @DeleteMapping("/survey-passport/{surveyPassportId}")
    @ApiOperation("Удаление паспорта опроса")
    public void deleteSurveyPassport(@RequestHeader("Authorization") String token,
                                     @PathVariable String surveyPassportId) throws SurveyPassportOperationException {
        if(surveyPassportId == null || surveyPassportId.isBlank()) {
            throw new IllegalArgumentException("Не указан идентификатор опроса");
        }
        RegisteredUser user = userOperations.createFrom(token);
        surveyPassportAdministratorService.deleteSurveyPassport(surveyPassportId, user);
    }


}
