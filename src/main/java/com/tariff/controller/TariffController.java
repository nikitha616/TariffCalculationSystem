package com.tariff.controller;

import com.tariff.dto.TariffCalculationResult;
import com.tariff.dto.TariffRequest;
import com.tariff.service.TariffCalculationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tariff")
public class TariffController {

    @Autowired
    private TariffCalculationService tariffCalculationService;

    @PostMapping("/calculate")
    public TariffCalculationResult calculateTariff(@Valid @RequestBody TariffRequest request) {
        return tariffCalculationService.calculateTariff(
                request.getItemId(),
                request.getCountry(),
                request.getItemCost(),
                request.getPolicy() == null ? "additive" : request.getPolicy()
        );
    }
}