package com.smartgnt.travel.itinerary;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ItineraryController {
    private final ItineraryService itineraryService;

    public ItineraryController(ItineraryService itineraryService) {
        this.itineraryService = itineraryService;
    }

    @GetMapping("/api/travel/itinerary")
    public Itinerary generation(@Validated @ModelAttribute TravelRequest request) {
        return itineraryService.ask(request);

    }
}
