package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import lombok.*;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name="survey_catalog")
public class SurveyCatalog {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "owner_id")
    private String ownerId;

    @Column(name = "capabilities")
    @Convert(converter = SurveyCatalogCapabilitiesToStringConverter.class)
    private SurveyCatalogCapabilities capabilities;

}
