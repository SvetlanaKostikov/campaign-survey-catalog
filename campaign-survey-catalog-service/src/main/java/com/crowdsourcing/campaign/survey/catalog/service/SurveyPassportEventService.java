package com.crowdsourcing.campaign.survey.catalog.service;


import com.crowdsourcing.campaign.survey.catalog.domain.catalog.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class SurveyPassportEventService {

    private final SurveyPassportEventJournal surveyPassportEventJournal;

    @Transactional
    public void handle(SurveyPassportEvent event) {
        surveyPassportEventJournal.save(event);
    }


}

