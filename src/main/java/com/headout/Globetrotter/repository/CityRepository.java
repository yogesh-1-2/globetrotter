package com.headout.Globetrotter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.headout.Globetrotter.model.City;


@Repository
public interface CityRepository extends MongoRepository<City, String> {
    @Query(value = "{}", fields = "{ 'gameId' : 1 }")
    List<City> getAllGameIds();

    @Query("{ 'gameId' : ?0 }")
    City getGameByGameId(String id);

    @Query(value = "{ 'name': ?0 }", fields = "{ 'clues' : 1 }")
    Optional<City> getCluesByCity(String city);

    @Query(value = "{ 'name': ?0 }", fields = "{ 'funFact' : 1 }")
    Optional<City> getFunFactByCity(String city);

    @Query(value = "{ 'name': ?0 }", fields = "{ 'trivia' : 1 }")
    Optional<City> getTriviaByCity(String city);

    List<City> findByNameNot(String name, Pageable pageable);
}

