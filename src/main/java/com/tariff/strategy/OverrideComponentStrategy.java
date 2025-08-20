package com.tariff.strategy;

import org.springframework.stereotype.Component;

@Component("override")
public class OverrideComponentStrategy implements TariffCombinationStrategy {
    @Override
    public double combine(double itemTariff, double componentTariff) {
        return componentTariff;
    }
}