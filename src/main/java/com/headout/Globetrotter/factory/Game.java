package com.headout.Globetrotter.factory;

import com.headout.Globetrotter.response.GameResponse;


public interface Game {
    String getClues(String entity , Integer number);
    String getCorrectAnswer(String id);
    String getRandomGameForUser(String userName);
    GameResponse isCorrectAnswer(String id, String answer, String userName);

}