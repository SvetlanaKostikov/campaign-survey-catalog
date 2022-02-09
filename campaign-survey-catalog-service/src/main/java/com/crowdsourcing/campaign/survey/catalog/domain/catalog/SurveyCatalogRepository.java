package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyCatalogRepository extends JpaRepository<SurveyCatalog,String> {

}
