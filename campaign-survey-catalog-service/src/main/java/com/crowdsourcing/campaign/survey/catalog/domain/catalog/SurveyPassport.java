package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.*;

import java.util.List;
import java.util.Optional;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyPassport {

    private String id;
    private String status;
    private String surveyCatalogId;

    public boolean allowsModification() {
        return !(status.equals("SURVEY_PASSPORT_DELETED"));
    }

    public boolean isDeleted() {
        return status.equals("SURVEY_PASSPORT_DELETED");
    }

    public static Optional<SurveyPassport> create(List<SurveyPassportEvent> events) {
        if (events.isEmpty()) {
            return Optional.empty();
        } else {
            String actualStatus = findActualStatus(events);
            String surveyCatalogId = findSurveyCatalogId(events);
            String id = findId(events);
            return Optional.of(new SurveyPassport(id, actualStatus, surveyCatalogId));
        }
    }

    private static String findId(List<SurveyPassportEvent> events) {
        SurveyPassportEvent firstEvent = events.get(0);
        return firstEvent.getEventPayload().getId();
    }

    private static String findSurveyCatalogId(List<SurveyPassportEvent> events) {
        SurveyPassportEvent firstEvent = events.get(0);
        return firstEvent.getEventPayload().getSurveyCatalogId();
    }

    private static String findActualStatus(List<SurveyPassportEvent> events) {
       SurveyPassportEvent lastEvent = events.get(events.size() - 1);
       return lastEvent.getEventType();
    }
}
