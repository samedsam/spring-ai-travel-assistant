package com.smartgnt.travel.recommend;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RecommendController {
    private final RecommendService recommendService;

    public RecommendController(RecommendService recommendService) {
        this.recommendService = recommendService;
    }
    @GetMapping("/api/travel/recommend")
    public String generation(@Validated @ModelAttribute TravelRequest request){
        return recommendService.ask(request);
    }
}
