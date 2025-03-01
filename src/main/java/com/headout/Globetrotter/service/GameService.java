package com.headout.Globetrotter.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

import com.headout.Globetrotter.model.City;
import com.headout.Globetrotter.repository.CityRepository;


@Service
public class GameService {

    @Autowired
    private CityRepository cityRepository;

    public City getRandomCity() {
        List<City> cities = cityRepository.findAll();
        if (cities.isEmpty()) return null;
        return cities.get(new Random().nextInt(cities.size()));
    }

}

