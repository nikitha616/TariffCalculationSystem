package com.tariff;

import com.tariff.model.BomEntry;
import com.tariff.service.BomResolverService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BomResolverServiceTest {

    @Autowired
    private BomResolverService bomResolverService;

    @Test
    public void testGetBomForItem() {
        BomEntry bom = bomResolverService.getBomForItem("ITEM123");
        assertEquals("Refrigerator", bom.getDescription());
        assertEquals(2, bom.getComponents().size());
        assertEquals("STEEL_A", bom.getComponents().get(0).getComponentId());
    }
}