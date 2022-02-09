package com.crowdsourcing.campaign.survey.catalog.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableEurekaClient
@EnableJpaRepositories("com.crowdsourcing.campaign.survey")
@EntityScan("com.crowdsourcing.campaign.survey")
@SpringBootApplication(scanBasePackages = {"com.crowdsourcing.campaign.survey"})
public class CampaignSurveyCatalogApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampaignSurveyCatalogApplication.class, args);
    }
}
