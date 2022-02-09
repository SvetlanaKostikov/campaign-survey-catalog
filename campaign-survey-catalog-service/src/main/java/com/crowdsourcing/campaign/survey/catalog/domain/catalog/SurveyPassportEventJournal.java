package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SurveyPassportEventJournal extends JpaRepository<SurveyPassportEvent, String> {
    List<SurveyPassportEvent> findAllByEventEntityIdOrderByEventOccurringTime(String id);
}
