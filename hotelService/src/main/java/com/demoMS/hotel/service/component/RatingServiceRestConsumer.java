package com.demoMS.hotel.service.component;

import com.demoMS.core.restAPI.GenericRestConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RatingServiceRestConsumer extends GenericRestConsumer {

    @Value("${config.rating-service.uri.host}")
    private String uriHost;

    @Value("${config.rating-service.uri.hotelID}")
    private String getHotelRatingHost;

    private RestTemplate template;
    public RatingServiceRestConsumer(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public String getGetHotelRatingHost(String hotelID) {
        StringBuilder stringBuilder = new StringBuilder(uriHost).append(getHotelRatingHost);
        return stringBuilder.append(hotelID).toString();
    }


}
