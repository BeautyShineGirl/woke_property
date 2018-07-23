package com.woke.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.woke")
public class WokePropertyDaoApplication {

	public static void main(String[] args) {
		SpringApplication.run(WokePropertyDaoApplication.class, args);
	}
}
