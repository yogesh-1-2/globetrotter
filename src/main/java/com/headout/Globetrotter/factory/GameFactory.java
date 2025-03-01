package com.headout.Globetrotter.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.headout.Globetrotter.games.CityGame;

@Component
public class GameFactory {

    @Autowired
    private CityGame cityGame;

    public Game getGame(String gameType) {
        if ("city".equalsIgnoreCase(gameType)) {
            return cityGame;
        }
        throw new IllegalArgumentException("Invalid game type: " + gameType);
    }
}