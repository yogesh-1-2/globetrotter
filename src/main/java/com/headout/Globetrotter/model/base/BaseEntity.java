package com.headout.Globetrotter.model.base;

import java.util.List;

import org.springframework.data.annotation.Id;

import lombok.Data;


@Data
public abstract class BaseEntity {
    @Id
    private String id;
    private String gameId;
    private String name;
    private List<String> clues;
    private List<String> funFacts;
    private List<String> trivia;
}
