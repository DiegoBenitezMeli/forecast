package com.meli.forecast.controller;

import com.meli.core.forecast.strategy.ForecastContext;
import com.meli.core.forecast.strategy.MeliForecastStrategy;
import com.meli.core.solar.system.Planet;
import com.meli.core.solar.system.Weather;
import com.meli.forecast.response.WeatherResponse;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;


@RestController
public class ForecastController {


    /**
     * Premisas:
     * ●
     * El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
     * horario. Su distancia con respecto al sol es de 500Km.
     * ● El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
     * horario. Su distancia con respecto al sol es de 2000Km.
     * ● El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
     * anti­horario, su distancia con respecto al sol es de 1000Km.
     * ● Todas las órbitas son circulares.
     * @return
     */
    private List<Planet> loadPlanets() {
        List<Planet> planets = new ArrayList<>();

        planets = new ArrayList<Planet>();

        Planet ferengi = new Planet.Builder()
                .setCivilization("Ferengis")
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(500.0)
                .build();

        Planet betazoide = new Planet.Builder()
                .setCivilization("Betazoides")
                .setVelocity(3)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(2000.0)
                .build();

        Planet vulcano = new Planet.Builder()
                .setCivilization("Vulcanos")
                .setVelocity(5)
                .setSence(Planet.Sence.COUNTERCLOCKWISE)
                .setDistanceFromSun(1000.0)
                .build();

        planets.add(ferengi);
        planets.add(betazoide);
        planets.add(vulcano);

        return planets;
    }

    public List<Planet> loadPlanetFromDB() {

        List<Planet> planets = new ArrayList<>();

        return planets;

    /*    List<com.meli.core.solar.system.Planet> planets = new ArrayList<>();
        for (com.meli.database.entity.Planet planet : planetRepository.findAll()) {
           planets.add(ObjectConverter.dbPlanet2PlanetVO(planet));
        }
     */

    }

    @RequestMapping(path = "/clima", method = RequestMethod.GET)
    @ResponseBody
    public  WeatherResponse clima(@RequestParam("dia") Integer day) {

        ForecastContext forecastContext = new ForecastContext();
        forecastContext.setStrategy(new MeliForecastStrategy());

        Weather weather = forecastContext.getForecast(day, loadPlanets());
        return new WeatherResponse(day, weather.getPeriod().name());
    }
}
