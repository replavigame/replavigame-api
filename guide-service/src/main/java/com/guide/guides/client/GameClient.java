package com.guide.guides.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "game-service", path = "/games")
public interface GameClient {

}
