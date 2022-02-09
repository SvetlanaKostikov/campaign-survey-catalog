package com.crowdsourcing.campaign.survey.catalog.application.config;
import com.crowdsourcing.campaign.survey.catalog.domain.EventEnvelope;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableConfigurationProperties(KafkaProperties.class)
public class KafkaProducerConfig {
    private final KafkaProperties config;

    public KafkaProducerConfig(KafkaProperties config) {
        this.config = config;
    }

    @Bean
    public ProducerFactory<String, EventEnvelope> producerFactory() {
        ObjectMapper om = new ObjectMapper();
        om.registerModule(new JavaTimeModule());
        om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        DefaultKafkaProducerFactory<String, EventEnvelope> f = new DefaultKafkaProducerFactory<>(producerConfig());
        f.setKeySerializer(new StringSerializer());
        f.setValueSerializer(new JsonSerializer<>(om));
        return f;
    }

    @Bean
    public KafkaTemplate<String, EventEnvelope> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    @Bean
    public Map<String, Object> producerConfig() {
        Map<String, Object> props = new HashMap<>();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, config.getServer());
        return props;
    }
}