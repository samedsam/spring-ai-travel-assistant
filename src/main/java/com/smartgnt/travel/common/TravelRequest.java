package com.smartgnt.travel.common;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public record TravelRequest(
        @NotBlank(message = "Destination required") String destination,
        @Min(value = 0, message = "Budget must be postive") int budget,
        @Min(value = 1, message = "Days must be at least 1") int days,
        String model) { }
