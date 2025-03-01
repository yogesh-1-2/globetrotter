package com.headout.Globetrotter.model;

import java.util.List;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String username;
    private Integer points;
    private List<String> gamesPlayed;
}

