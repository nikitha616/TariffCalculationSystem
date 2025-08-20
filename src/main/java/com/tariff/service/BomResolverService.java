package com.tariff.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tariff.exception.ItemNotFoundException;
import com.tariff.model.BomEntry;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import java.io.InputStream;
import java.util.*;

@Service
public class BomResolverService {

    private Map<String, BomEntry> bomDataMap = new HashMap<>();

    @PostConstruct
    public void loadBomData() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        InputStream is = getClass().getResourceAsStream("/data/bom.json");
        BomEntry[] entries = mapper.readValue(is, BomEntry[].class);
        for (BomEntry entry : entries) {
            bomDataMap.put(entry.getItemId(), entry);
        }
    }

    public BomEntry getBomForItem(String itemId) {
        BomEntry bom = bomDataMap.get(itemId);
        if (bom == null) {
            throw new ItemNotFoundException(itemId);
        }
        return bom;
    }
}