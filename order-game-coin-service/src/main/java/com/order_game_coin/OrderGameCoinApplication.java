package com.order_game_coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class OrderGameCoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderGameCoinApplication.class, args);
	}

}
