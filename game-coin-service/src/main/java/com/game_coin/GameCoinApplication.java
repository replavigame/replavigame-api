package com.game_coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class GameCoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameCoinApplication.class, args);
	}

}
