package com.headout.Globetrotter.response;

import com.headout.Globetrotter.model.base.BaseEntity;

import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class GameResponse {
    private BaseEntity entity;
    private String message;
}
