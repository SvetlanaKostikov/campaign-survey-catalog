package com.crowdsourcing.campaign.survey.catalog.api.events;

import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class CampaignEventPayload {

    private String id;
    private String name;
    private String status;
    private String ownerId;
}
