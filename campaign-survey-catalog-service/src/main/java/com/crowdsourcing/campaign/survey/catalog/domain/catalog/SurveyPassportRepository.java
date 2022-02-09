package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;


@AllArgsConstructor
@Component
public class SurveyPassportRepository  {

    private final SurveyPassportEventJournal surveyPassportEventJournal;

    public Optional<SurveyPassport> findSurveyPassportById(String id) {
        List<SurveyPassportEvent> events = surveyPassportEventJournal.findAllByEventEntityIdOrderByEventOccurringTime(id);
        return SurveyPassport.create(events);
    }


    public SurveyPassportEventPayload findSurveyPassportPayloadBySurveyPassportId(String id) {
        List<SurveyPassportEvent> events = surveyPassportEventJournal.findAllByEventEntityIdOrderByEventOccurringTime(id);
        return events.stream().map(x->x.getEventPayload()).findFirst().get();
    }
}
