package com.tariff.strategy;

import org.springframework.stereotype.Component;

@Component("additive")
public class AdditiveCombinationStrategy implements TariffCombinationStrategy {
    @Override
    public double combine(double itemTariff, double componentTariff) {
        return itemTariff + componentTariff;
    }
}