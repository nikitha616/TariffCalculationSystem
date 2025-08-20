package com.tariff.strategy;

import org.springframework.stereotype.Component;

@Component("maximum")
public class MaximumCombinationStrategy implements TariffCombinationStrategy {
    @Override
    public double combine(double itemTariff, double componentTariff) {
        return Math.max(itemTariff, componentTariff);
    }
}