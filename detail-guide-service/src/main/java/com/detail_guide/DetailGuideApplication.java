package com.detail_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class DetailGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(DetailGuideApplication.class, args);
	}

}
