package com.tariff;

import com.tariff.service.TariffRuleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TariffRuleServiceTest {

    @Autowired
    private TariffRuleService tariffRuleService;

    @Test
    public void testItemTariffFetch() {
        double rate = tariffRuleService.getItemTariffRate("ITEM123", "CHN");
        assertEquals(0.1, rate);
    }

    @Test
    public void testComponentTariffFetch() {
        double rate = tariffRuleService.getComponentTariffRate("STEEL_A", "CHN");
        assertEquals(0.15, rate);
    }
}