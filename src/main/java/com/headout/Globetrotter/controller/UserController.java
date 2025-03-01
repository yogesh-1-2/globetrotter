package com.headout.Globetrotter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.headout.Globetrotter.response.UserResponse;
import com.headout.Globetrotter.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<UserResponse> registerUser(@RequestParam String username) {
        return ResponseEntity.ok(userService.addUser(username));
    }

    @GetMapping("/{username}/games")
    public ResponseEntity<List<String>> getGamesPlayed(@PathVariable String username) {
        List<String> gamesPlayed = userService.getGamesPlayedByUsername(username);
        return ResponseEntity.ok(gamesPlayed);
    }

    @PostMapping("/{username}/games/{gameId}")
    public ResponseEntity<Void> addGameToUser(@PathVariable String username, @PathVariable String gameId) {
        userService.addGameToUser(username, gameId);
        return ResponseEntity.ok().build();
    }
}