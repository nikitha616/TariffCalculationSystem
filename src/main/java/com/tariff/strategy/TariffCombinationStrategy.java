package com.tariff.strategy;

public interface TariffCombinationStrategy {
    double combine(double itemTariff, double componentTariff);
}