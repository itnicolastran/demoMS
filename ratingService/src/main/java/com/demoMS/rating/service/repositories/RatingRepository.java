package com.demoMS.rating.service.repositories;

import com.demoMS.rating.service.entities.Rating;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RatingRepository extends MongoRepository<Rating, String> {

    //Customized finding methods
    List<Rating> findRatingByUserID(String userID);
    List<Rating> findRatingByHotelID(String hotelID);

}
