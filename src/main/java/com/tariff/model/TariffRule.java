package com.tariff.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffRule {
    private String level;
    private String entityId;
    private String country;
    private double tariffRate;
    private String startDate;
    private String source;

}