package com.demoMS.user.service.UserService.components;

import com.demoMS.core.restAPI.GenericRestConsumer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RatingServiceRestConsumer extends GenericRestConsumer {
    @Value("${config.rating-service.uri.host}")
    private String uriHost;

    @Value("${config.rating-service.uri.userID}")
    private String getUserRatingHost;

    @Value("${config.rating-service.uri.hotelID}")
    private String getHotelRatingHost;

    public RatingServiceRestConsumer(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public String getUriHost() {
        return uriHost;
    }

    public String getGetUserRatingHost(String userID) {
        StringBuilder stringBuilder = new StringBuilder(uriHost).append(getUserRatingHost);
        return stringBuilder.append(userID).toString();
    }

    public String getGetHotelRatingHost(String hotelID) {
        StringBuilder stringBuilder = new StringBuilder(uriHost).append(getHotelRatingHost);
        return stringBuilder.append(hotelID).toString();
    }
}
