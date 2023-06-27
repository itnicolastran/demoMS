package com.demoMS.rating.service.services.impl;

import com.demoMS.rating.service.entities.Rating;
import com.demoMS.rating.service.repositories.RatingRepository;
import com.demoMS.rating.service.services.RatingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final static Logger LOGGER = LoggerFactory.getLogger(RatingServiceImpl.class);

    private RatingRepository ratingRepo;

    public RatingServiceImpl(RatingRepository ratingRepo) {
        this.ratingRepo = ratingRepo;
    }

    @Override
    public Rating create(Rating rating) {
        return ratingRepo.save(rating);
    }

    @Override
    public List<Rating> getRatingByUser(String userID) {
        return ratingRepo.findRatingByUserID(userID);
    }

    @Override
    public List<Rating> getRating() {
        return ratingRepo.findAll();
    }

    @Override
    public List<Rating> getRatingByHotel(String hotelID) {
        return ratingRepo.findRatingByHotelID(hotelID);
    }

    @Override
    public void removeRating(String ratingID) {

    }
}
