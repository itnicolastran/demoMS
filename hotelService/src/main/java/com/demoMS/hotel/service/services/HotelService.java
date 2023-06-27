package com.demoMS.hotel.service.services;

import com.demoMS.core.responses.HotelResponse;
import com.demoMS.hotel.service.entities.Hotel;

import java.util.List;

public interface HotelService {

    // Create Hotel
    HotelResponse createNew(Hotel hotel);

    // Get All Hotels
    List<HotelResponse> getAll();

    // Get  Hotel by hotel ID
    HotelResponse getHotelById(String hotelID);
}
