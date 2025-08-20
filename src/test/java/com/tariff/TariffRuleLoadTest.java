package com.tariff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tariff.model.TariffRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class TariffRuleLoadTest {

    @Test
    public void testTariffRuleDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/tariff-rules.json");
        List<TariffRule> rules = Arrays.asList(mapper.readValue(is, TariffRule[].class));

        assertEquals(3, rules.size());

        assertEquals("CHN", rules.get(0).getCountry());
        assertEquals("ITEM123", rules.get(0).getEntityId());
        assertEquals(0.1, rules.get(0).getTariffRate());

        assertEquals("CHN", rules.get(1).getCountry());
        assertEquals("STEEL_A", rules.get(1).getEntityId());
        assertEquals(0.15, rules.get(1).getTariffRate());

        assertEquals("CHN", rules.get(2).getCountry());
        assertEquals("ALUMINUM_B", rules.get(2).getEntityId());
        assertEquals(0.2, rules.get(2).getTariffRate());

    }
}