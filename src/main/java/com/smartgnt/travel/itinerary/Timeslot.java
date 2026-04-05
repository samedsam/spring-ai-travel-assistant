package com.smartgnt.travel.itinerary;

import jakarta.validation.constraints.NotBlank;

public record Timeslot(
        @NotBlank String time,
        @NotBlank String activity,
        @NotBlank String description
) {
}
