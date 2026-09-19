package com.gurusainathp.pulsequeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class PulsequeueApplication {

	public static void main(String[] args) {
		SpringApplication.run(PulsequeueApplication.class, args);
	}

}
