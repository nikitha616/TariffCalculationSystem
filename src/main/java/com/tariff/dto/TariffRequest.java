package com.tariff.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TariffRequest {
    @NotBlank(message = "Item ID is required")
    private String itemId;

    @NotBlank(message = "Country is required")
    private String country;

    @NotNull(message = "Item cost is required")
    @Min(value = 1, message = "Item cost must be greater than 0")
    private double itemCost;

    private String policy;
}