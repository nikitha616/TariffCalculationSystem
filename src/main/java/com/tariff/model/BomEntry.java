package com.tariff.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class BomEntry {
    private String itemId;
    private String description;
    private List<Component> components;

}