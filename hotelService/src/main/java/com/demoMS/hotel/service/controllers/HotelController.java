package com.demoMS.hotel.service.controllers;

import com.demoMS.core.responses.HotelResponse;
import com.demoMS.hotel.service.entities.Hotel;
import com.demoMS.hotel.service.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/Hotel")
public class HotelController {

    private final static Logger LOGGER = LoggerFactory.getLogger(HotelController.class);

    private HotelService hotelService;

    public HotelController (HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping
    public ResponseEntity createNew(@RequestBody Hotel hotel){
        HotelResponse createdHotel = hotelService.createNew(hotel);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdHotel);
    }

    @GetMapping
    public ResponseEntity getAllHotels() {
        return ResponseEntity.ok(hotelService.getAll());
    }

    @GetMapping("/{hotelID}")
    public ResponseEntity getHotelById(@PathVariable String hotelID) {
        return ResponseEntity.ok(hotelService.getHotelById(hotelID));
    }
}
