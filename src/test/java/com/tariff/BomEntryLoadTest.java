package com.tariff;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tariff.model.BomEntry;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BomEntryLoadTest {

    @Test
    public void testBomDeserialization() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/bom.json");
        List<BomEntry> bomEntries = Arrays.asList(mapper.readValue(is, BomEntry[].class));

        assertEquals(1, bomEntries.size());
        BomEntry bom = bomEntries.get(0);
        assertEquals("ITEM123", bom.getItemId());
        assertEquals(2, bom.getComponents().size());
        assertEquals("STEEL_A", bom.getComponents().get(0).getComponentId());
    }
}