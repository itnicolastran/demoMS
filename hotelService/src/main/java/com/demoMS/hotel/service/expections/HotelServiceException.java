package com.demoMS.hotel.service.expections;

public class HotelServiceException extends RuntimeException {
    public HotelServiceException(String message) {
        super(message);
    }
}
