package com.demoMS.rating.service.services;

import com.demoMS.rating.service.entities.Rating;

import java.util.List;

public interface RatingService {

    // Create Rating
    Rating create(Rating rating);

    //get All User Ratings
    List<Rating> getRatingByUser(String userID);

    //get All Ratings
    List<Rating> getRating();
    //get All Ratings of Hotel
    List<Rating> getRatingByHotel(String hotelID);
    // Remove
    void removeRating(String ratingID);
}
