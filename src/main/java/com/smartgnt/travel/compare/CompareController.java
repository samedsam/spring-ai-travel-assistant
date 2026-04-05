package com.smartgnt.travel.compare;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.ExecutionException;

@RestController
public class CompareController {
    private final CompareService compareService;

    public CompareController(CompareService compareService) {
        this.compareService = compareService;
    }
    @GetMapping("api/travel/compare")
    public Map<String, String> generation(@RequestParam String input) throws ExecutionException, InterruptedException {
        return compareService.ask(input);
    }
}
