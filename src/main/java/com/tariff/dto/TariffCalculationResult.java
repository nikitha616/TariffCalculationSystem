package com.tariff.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
public class TariffCalculationResult {
    private double itemTariff;
    private double componentTariff;
    private double totalTariff;
    private Map<String, Double> componentBreakdown;
}