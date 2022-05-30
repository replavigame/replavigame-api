package com.coach;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableEurekaClient
@SpringBootApplication
public class CoachApplication {

	public static void main(String[] args) {
		SpringApplication.run(CoachApplication.class, args);
	}

}