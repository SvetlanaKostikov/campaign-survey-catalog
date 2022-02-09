package com.crowdsourcing.campaign.survey.catalog.domain.catalog;

import com.crowdsourcing.campaign.survey.catalog.domain.catalog.SurveyCatalogCapabilities;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Arrays;
import java.util.List;


@Slf4j
@Converter
public class SurveyCatalogCapabilitiesToStringConverter implements AttributeConverter<SurveyCatalogCapabilities, String> {

    @Override
    public String convertToDatabaseColumn(SurveyCatalogCapabilities surveyCatalogCapabilities) {
        String withoutComma = surveyCatalogCapabilities.getCapabilities().toString();
        String withComma = String.join(",", withoutComma);
        withComma = withComma.replaceAll("[\\p{Ps}\\p{Pe}]", "");
        return withComma;
    }

    @Override
    public SurveyCatalogCapabilities convertToEntityAttribute(String s) {
        List<String> capabilities = Arrays.asList(s.split("\\s*,\\s*"));
        return new SurveyCatalogCapabilities(capabilities);
    }
}

