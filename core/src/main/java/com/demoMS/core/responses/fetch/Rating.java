package com.demoMS.core.responses.fetch;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Rating {
    private String ratingID;

    private String userID;

    private String hotelID;

    private int ratingPoint;
    private String feedback;

}
