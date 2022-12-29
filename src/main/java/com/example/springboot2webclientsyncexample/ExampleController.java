package com.example.springboot2webclientsyncexample;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class ExampleController {

    private final WebClient Webclient;

    @GetMapping
    String example() {
        return Webclient.get()
                .uri("https://httpbin.org/get")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }
}
