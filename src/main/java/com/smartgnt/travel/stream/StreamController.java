package com.smartgnt.travel.stream;

import com.smartgnt.travel.common.TravelRequest;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class StreamController {

    private final StreamService streamService;


    public StreamController(StreamService streamService) {
        this.streamService = streamService;
    }

    @GetMapping(value = "api/travel/stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> generation(@Validated @ModelAttribute TravelRequest request) {
        return streamService.ask(request);
    }
}
