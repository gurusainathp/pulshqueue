package com.gurusainathp.pulsequeue;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class PulsequeueController {
    @GetMapping("/hello")
    public String getHello() {
        return new String("Hello, PulseQueue!");
    }

}
