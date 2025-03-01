package com.headout.Globetrotter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import com.headout.Globetrotter.model.User;
import com.headout.Globetrotter.repository.UserRepository;
import com.headout.Globetrotter.response.UserResponse;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserResponse addUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            return UserResponse.builder().message("username already exists").build();
        }
        User user = new User();
        user.setUsername(username);
        user = userRepository.save(user);
        return UserResponse.builder().user(user).message("new user created").build();
    }

    public void getUserByUsername(String username) {
        userRepository.findByUsername(username);
    }

    public List<String> getGamesPlayedByUsername(String userName) {
        return userRepository.findByUsername(userName)
                .map(User::getGamesPlayed)
                .orElse(List.of());
    }

    public void addGameToUser(String userName, String gameId) {
        Optional<User> userOptional = userRepository.findByUsername(userName);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user.getGamesPlayed() == null) {
                user.setGamesPlayed(new ArrayList<>());
            }
            if (Objects.nonNull(user.getPoints())) {
                user.setPoints(user.getPoints() + 1);
            } else {
                user.setPoints(1);
            }
            if (!user.getGamesPlayed().contains(gameId)) {
                user.getGamesPlayed().add(gameId);
                userRepository.save(user);
            }
        }
    }
}

