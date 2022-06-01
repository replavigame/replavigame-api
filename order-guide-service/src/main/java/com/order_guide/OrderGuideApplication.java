package com.order_guide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderGuideApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderGuideApplication.class, args);
	}

}