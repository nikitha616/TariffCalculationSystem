package com.tariff;

import com.tariff.dto.TariffCalculationResult;
import com.tariff.service.TariffCalculationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TariffCalculationServiceTest {

    @Autowired
    private TariffCalculationService tariffCalculationService;

    @Test
    public void testCalculateTariff_AdditivePolicy() {
        double itemCost = 100.0;
        TariffCalculationResult result = tariffCalculationService.calculateTariff("ITEM123", "CHN", itemCost, "additive");

        assertEquals(10.0, result.getItemTariff());
        assertEquals(0.15 * 30.0 + 0.2 * 70.0, result.getComponentTariff());
        assertEquals(result.getItemTariff() + result.getComponentTariff(), result.getTotalTariff());
    }
}