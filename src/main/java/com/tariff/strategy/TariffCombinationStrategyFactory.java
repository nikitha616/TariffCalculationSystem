package com.tariff.strategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

//I am using factory class as we require different implementations to be returned based on input
@Component
public class TariffCombinationStrategyFactory {

    private final Map<String, TariffCombinationStrategy> strategyMap;

    @Autowired
    public TariffCombinationStrategyFactory(Map<String, TariffCombinationStrategy> strategyMap) {
        this.strategyMap = strategyMap;
    }

    public TariffCombinationStrategy getStrategy(String policy) {
        return strategyMap.getOrDefault(policy.toLowerCase(), strategyMap.get("additive"));
    }
}