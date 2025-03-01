package com.headout.Globetrotter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.headout.Globetrotter.factory.GameFactory;
import com.headout.Globetrotter.games.CityGame;
import com.headout.Globetrotter.response.GameResponse;

import jakarta.annotation.PostConstruct;

@RestController
@RequestMapping("/globetrotter/city")
public class GameController {

    @Autowired
    private GameFactory gameFactory;

    private CityGame cityGame;

    @PostConstruct
    public void init() {
        this.cityGame = (CityGame) gameFactory.getGame("city");
    }

    @GetMapping("/random")
    public ResponseEntity<String> getRandomItem(@RequestParam String userName) {
        return ResponseEntity.ok(cityGame.getRandomGameForUser(userName));
    }

    @GetMapping("/{gameId}/hints/{hintNumber}")
    public ResponseEntity<String> getGameHint(@PathVariable String gameId, @PathVariable Integer hintNumber) {
        return ResponseEntity.ok(cityGame.getClues(gameId, hintNumber));
    }

    @GetMapping("/{gameId}/Options")
    public ResponseEntity<List<String>>getOptions(@PathVariable String gameId) {
        return ResponseEntity.ok(cityGame.getOptions(gameId));
    }

    @GetMapping("/{gameId}/check")
    public ResponseEntity<GameResponse> checkAnswer(
            @PathVariable String gameId,
            @RequestParam String answer,
            @RequestParam String userId) {

        GameResponse gameResponse = cityGame.isCorrectAnswer(gameId, answer, userId);
        return ResponseEntity.ok(gameResponse);
    }
}
