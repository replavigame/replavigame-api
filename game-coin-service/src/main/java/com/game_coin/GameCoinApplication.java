package com.game_coin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableEurekaClient
@SpringBootApplication
@EnableHystrix
@EnableHystrixDashboard
@EnableFeignClients
public class GameCoinApplication {

	public static void main(String[] args) {
		SpringApplication.run(GameCoinApplication.class, args);
	}

}
