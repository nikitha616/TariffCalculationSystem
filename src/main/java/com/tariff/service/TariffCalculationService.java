package com.tariff.service;

import com.tariff.dto.TariffCalculationResult;
import com.tariff.exception.InvalidBomException;
import com.tariff.model.BomEntry;
import com.tariff.model.Component;
import com.tariff.strategy.TariffCombinationStrategy;
import com.tariff.strategy.TariffCombinationStrategyFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TariffCalculationService {

    @Autowired
    private BomResolverService bomResolverService;

    @Autowired
    private TariffRuleService tariffRuleService;

    @Autowired
    private TariffCombinationStrategyFactory strategyFactory;

    public TariffCalculationResult calculateTariff(String itemId, String country, double itemCost, String policy) {

        BomEntry bom = bomResolverService.getBomForItem(itemId);

        double totalShare = bom.getComponents().stream()
                .mapToDouble(Component::getCostShare)
                .sum();

        if (Math.abs(totalShare - 1.0) > 0.0001) {
            throw new InvalidBomException(itemId);
        }

        double itemTariffRate = tariffRuleService.getItemTariffRate(itemId, country);
        double itemTariffAmount = itemTariffRate * itemCost;

        double componentTariffAmount = 0.0;
        Map<String, Double> componentBreakdown = new HashMap<>();

        for (Component c : bom.getComponents()) {
            double rate = tariffRuleService.getComponentTariffRate(c.getComponentId(), country);
            double cost = c.getCostShare() * itemCost;
            double tariff = rate * cost;
            componentBreakdown.put(c.getComponentId(), tariff);
            componentTariffAmount += tariff;
        }

        TariffCombinationStrategy strategy = strategyFactory.getStrategy(policy);
        double finalTariff = strategy.combine(itemTariffAmount, componentTariffAmount);

        TariffCalculationResult result = new TariffCalculationResult();
        result.setItemTariff(itemTariffAmount);
        result.setComponentTariff(componentTariffAmount);
        result.setTotalTariff(finalTariff);
        result.setComponentBreakdown(componentBreakdown);

        return result;
    }
}