package com.woke.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.woke")
public class WokePropertyServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(WokePropertyServiceApplication.class, args);
	}
}
