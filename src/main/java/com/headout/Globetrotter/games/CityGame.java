package com.headout.Globetrotter.games;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.headout.Globetrotter.factory.Game;
import com.headout.Globetrotter.model.City;
import com.headout.Globetrotter.model.base.BaseEntity;
import com.headout.Globetrotter.repository.CityRepository;
import com.headout.Globetrotter.response.GameResponse;
import com.headout.Globetrotter.service.UserService;


@Component
public class CityGame implements Game {

    @Autowired
    private UserService userService;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public String getClues(String gameId, Integer clueNumber) {
        List<String> clues =  cityRepository.getGameByGameId(gameId).getClues();
        if (clueNumber > clues.size()) {
            return "No clues available";
        }
        return clues.get(clueNumber-1);
    }

    @Override
    public String getCorrectAnswer(String gameId) {
        return cityRepository.getGameByGameId(gameId).getName();
    }

    private List<String> getIncorrectCities(String correctCityName, int limit) {
        Pageable pageable = PageRequest.of(0, limit, Sort.by(Sort.Direction.ASC, "_id"));
        List<City> incorrectCities = cityRepository.findByNameNot(correctCityName, pageable);
        List<String> cityNames = new ArrayList<>();
        incorrectCities.forEach(city -> cityNames.add(city.getName()));
        return cityNames;
    }

    private List<String> generateShuffledOptions(String correctCity, List<String> incorrectCities) {
        List<String> options = new ArrayList<>();
        options.add(correctCity);
        options.addAll(incorrectCities);
        Collections.shuffle(options);
        return options;
    }

    public List<String> getOptions(String gameId) {
        String correctCityName = getCorrectAnswer(gameId);
        if (correctCityName == null) {
            return List.of();
        }

        List<String> incorrectCities = getIncorrectCities(correctCityName, 3);

        return generateShuffledOptions(correctCityName, incorrectCities);
    }

    public String getRandomGameForUser(String userName) {
        List<String>gamesPlayed = userService.getGamesPlayedByUsername(userName);
        Set<String> playedSet = Set.copyOf(gamesPlayed);

        List<String> gameIds = cityRepository.getAllGameIds()
                .stream()
                .map(City::getGameId) // Extract 'id' field from City objects
                .toList();
        List<String> unplayedGames = gameIds.stream()
                .filter(gameId -> !playedSet.contains(gameId))
                .toList();

        if (unplayedGames.isEmpty()) {
            return "No new games available";
        }

        return unplayedGames.get(new Random().nextInt(unplayedGames.size()));
    }

    @Override
    public GameResponse isCorrectAnswer(String gameId, String answer, String userName) {
        String correctCityName = getCorrectAnswer(gameId);
        GameResponse.GameResponseBuilder gameResponseBuilder = GameResponse.builder();
        if (correctCityName != null && correctCityName.equalsIgnoreCase(answer)) {
            userService.addGameToUser(userName, gameId);
            BaseEntity city = cityRepository.getGameByGameId(gameId);
            gameResponseBuilder.entity(city).message("Hurrah! you won").build();
            return gameResponseBuilder.build();
        } else {
            return gameResponseBuilder.message("Sorry!! wrong answer try again").build();
        }
    }
}