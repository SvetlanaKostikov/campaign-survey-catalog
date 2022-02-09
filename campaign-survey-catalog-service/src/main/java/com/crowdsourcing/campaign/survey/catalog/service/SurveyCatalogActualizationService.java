package com.crowdsourcing.campaign.survey.catalog.service;


import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalogCapabilities;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalogRepository;
import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SurveyCatalogActualizationService {

    private final SurveyCatalogRepository surveyCatalogRepository;


    public void addNewSurveyCatalog(String id, String name, String ownerId, SurveyCatalogCapabilities capabilities) {
        SurveyCatalog surveyCatalog = new SurveyCatalog(id, name, ownerId, capabilities);
        surveyCatalogRepository.save(surveyCatalog);

    }

    public void modifySurveyCatalog(String id, String name, String ownerId, SurveyCatalogCapabilities capabilities) {
        SurveyCatalog surveyCatalog = surveyCatalogRepository.findById(id).get();
        surveyCatalog.setName(name);
        surveyCatalog.setOwnerId(ownerId);
        surveyCatalog.setCapabilities(capabilities);
        surveyCatalogRepository.save(surveyCatalog);

    }

    public SurveyCatalog getSurveyCatalogById(String id) {
        return surveyCatalogRepository.findById(id).get();
    }
}
