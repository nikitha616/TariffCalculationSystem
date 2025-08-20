package com.tariff.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tariff.model.TariffRule;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class TariffRuleService {

    private List<TariffRule> tariffRules = new ArrayList<>();

    @PostConstruct
    public void loadTariffRules() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/tariff-rules.json");
        TariffRule[] rules = mapper.readValue(is, TariffRule[].class);
        for (TariffRule rule : rules) {
            tariffRules.add(rule);
        }
    }

    public Double getItemTariffRate(String itemId, String country) {
        return tariffRules.stream()
                .filter(rule -> rule.getLevel().equalsIgnoreCase("item"))
                .filter(rule -> rule.getEntityId().equalsIgnoreCase(itemId))
                .filter(rule -> rule.getCountry().equalsIgnoreCase(country))
                .map(TariffRule::getTariffRate)
                .findFirst()
                .orElse(0.0);
    }

    public Double getComponentTariffRate(String componentId, String country) {
        return tariffRules.stream()
                .filter(rule -> rule.getLevel().equalsIgnoreCase("component"))
                .filter(rule -> rule.getEntityId().equalsIgnoreCase(componentId))
                .filter(rule -> rule.getCountry().equalsIgnoreCase(country))
                .map(TariffRule::getTariffRate)
                .findFirst()
                .orElse(0.0);
    }
}