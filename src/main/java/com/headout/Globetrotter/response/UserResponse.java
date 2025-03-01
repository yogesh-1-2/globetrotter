package com.headout.Globetrotter.response;

import com.headout.Globetrotter.model.User;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserResponse {
    private String message;
    private User user;

    public UserResponse(String message, User user) {
        this.message = message;
        this.user = user;
    }
}
