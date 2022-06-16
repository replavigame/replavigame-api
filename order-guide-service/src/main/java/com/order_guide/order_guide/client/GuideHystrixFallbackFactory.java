package com.order_guide.order_guide.client;

import com.order_guide.order_guide.model.Category;
import com.order_guide.order_guide.model.Guide;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class GuideHystrixFallbackFactory implements GuideClient {

    @Override
    public ResponseEntity<Guide> getById(Long id) {
        Guide guide = new Guide();
        Category category = new Category();
        category.setGameId(0L);
        category.setId(null);
        category.setName("undefined");
        guide.setCategory(category);
        guide.setCoachId(0L);
        guide.setDescount(1.0);
        guide.setDescription("undefined");
        guide.setGameId(0L);
        guide.setId(null);
        guide.setPoints(0L);
        guide.setTitle("undefined");

        return new ResponseEntity<>(guide, HttpStatus.OK);
    }

}
