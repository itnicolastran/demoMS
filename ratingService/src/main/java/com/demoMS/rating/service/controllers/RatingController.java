package com.demoMS.rating.service.controllers;

import com.demoMS.rating.service.entities.Rating;
import com.demoMS.rating.service.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Rating")
public class RatingController {

    private final static Logger LOGGER = LoggerFactory.getLogger(RatingController.class);

    private RatingService ratingService;

    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping
    public ResponseEntity create(@RequestBody Rating rating){
        return ResponseEntity.status(HttpStatus.CREATED).body(ratingService.create(rating));
    }

    @GetMapping
    public ResponseEntity getAllRatings(){
        return ResponseEntity.ok(ratingService.getRating());
    }
    @GetMapping("/Users/{userID}")
    public ResponseEntity getUserRatings(@PathVariable String userID) {
        return ResponseEntity.ok(ratingService.getRatingByUser(userID));
    }

    @GetMapping("/Hotels/{hotelID}")
    public ResponseEntity getHotelRatings(@PathVariable String hotelID) {
        return ResponseEntity.ok(ratingService.getRatingByHotel(hotelID));
    }


}
