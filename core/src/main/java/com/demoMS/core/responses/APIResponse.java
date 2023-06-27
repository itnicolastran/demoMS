package com.demoMS.core.responses;

import lombok.*;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class APIResponse {

    private String massage;

    private boolean success;

    private HttpStatus status;

}
