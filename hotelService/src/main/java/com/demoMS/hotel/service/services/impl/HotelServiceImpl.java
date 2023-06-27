package com.demoMS.hotel.service.services.impl;

import com.demoMS.core.responses.HotelResponse;
import com.demoMS.core.responses.fetch.Rating;
import com.demoMS.hotel.service.component.RatingServiceRestConsumer;
import com.demoMS.hotel.service.entities.Hotel;
import com.demoMS.hotel.service.expections.HotelServiceException;
import com.demoMS.hotel.service.repositories.HotelRepository;
import com.demoMS.hotel.service.services.HotelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final static Logger LOGGER = LoggerFactory.getLogger(HotelServiceImpl.class);

    private RatingServiceRestConsumer ratingServiceRestConsumer;

    private HotelRepository hotelRepository;

    public HotelServiceImpl(HotelRepository hotelRepository, RatingServiceRestConsumer ratingServiceRestConsumer) {
        this.ratingServiceRestConsumer = ratingServiceRestConsumer;
        this.hotelRepository = hotelRepository;
    }

    @Override
    public HotelResponse createNew(Hotel hotel) {
        LOGGER.debug("createNew() process ::");
        hotel.setHotelID(UUID.randomUUID().toString());
        hotelRepository.save(hotel);
        HotelResponse hotelResp = new HotelResponse();
        BeanUtils.copyProperties(hotel, hotelResp);
        return hotelResp;
    }

    @Override
    public List<HotelResponse> getAll() {
        LOGGER.debug("getAll() process ::");
        return hotelRepository.findAll().stream().map(hotel -> {
            HotelResponse hotelResp = new HotelResponse();
            BeanUtils.copyProperties(hotel, hotelResp);
            return hotelResp;
        }).collect(Collectors.toList());
    }

    @Override
    public HotelResponse getHotelById(String hotelID) {
        LOGGER.debug("getHotelById() process :: {}", hotelID);
        Hotel hotel = hotelRepository.findById(hotelID)
                .orElseThrow(() -> new HotelServiceException("RESOURCE NOT FOUND :: " + hotelID));
        HotelResponse hotelResp = new HotelResponse();
        BeanUtils.copyProperties(hotel, hotelResp);
        try {
            ArrayList<Rating> ratings = ratingServiceRestConsumer.executeGetMethod(
                    ratingServiceRestConsumer.getGetHotelRatingHost(hotelID), null, ArrayList.class).getBody();
            hotelResp.setRating(ratings);
        }
        catch (RestClientException restClientException) {
            LOGGER.warn("WebAPI Issue :: " + restClientException.getMessage() );
        }
        return hotelResp;
    }
}
