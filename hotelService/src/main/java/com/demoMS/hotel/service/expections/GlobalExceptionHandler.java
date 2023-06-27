package com.demoMS.hotel.service.expections;

import com.demoMS.core.responses.APIResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final static Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(HotelServiceException.class)
    public ResponseEntity<APIResponse> handleHotelServiceException(HotelServiceException ex) {
        LOGGER.warn("handleHotelServiceException() process.");
        String message = ex.getMessage();
        APIResponse response = APIResponse.builder().massage(message).success(false).status(HttpStatus.NOT_FOUND).build();
        return new ResponseEntity<>(response,HttpStatus.NOT_FOUND);
    }
}
