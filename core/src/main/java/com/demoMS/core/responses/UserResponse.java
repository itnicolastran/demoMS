package com.demoMS.core.responses;


import com.demoMS.core.responses.fetch.Rating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private  String userID;
    private String name;
    private String email;
    private String about;
    private List<Rating> rating;
}
