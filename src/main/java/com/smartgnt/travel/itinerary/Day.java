package com.smartgnt.travel.itinerary;

import jakarta.validation.constraints.Min;

import java.util.List;

public record Day(
        @Min(value = 1,message = "Day number must be at least 1") Integer day,
        List<Timeslot> timeslots
)
{}
