package com.smartgnt.travel.itinerary;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

public record Itinerary(
        @NotBlank String destination,
        @Min(value = 1, message = "number of days must be at least 1") Integer numberOfDays,
        List<Day> days
) {
}
