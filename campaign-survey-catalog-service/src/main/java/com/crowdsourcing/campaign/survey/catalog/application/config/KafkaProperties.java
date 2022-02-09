package com.crowdsourcing.campaign.survey.catalog.application.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("kafka")
@Data
public class KafkaProperties {
    private String server;
    private String groupId;
}
