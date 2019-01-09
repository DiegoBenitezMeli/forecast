package com.meli.core.forecast.strategy;

import com.meli.core.solar.system.Planet;
import com.meli.core.solar.system.Weather;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ForecastStrategyTest {

    ForecastContext forecastContext;
    List<Planet> planets;

    @Before
    public void setUp() {
    }

    @Test
    public void testSequia() {

        forecastContext = new ForecastContext();
        forecastContext.setStrategy(new MeliForecastStrategy());
        planets = new ArrayList<Planet>();

        Planet ferengi = new Planet.Builder()
                .setCivilization("Ferengis")
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(2.0))
                .build();

        Planet betazoide = new Planet.Builder()
                .setCivilization("Betazoides")
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(8.0))
                .build();

        Planet vulcano = new Planet.Builder()
                .setCivilization("Vulcanos")
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(18.0))
                .build();

        planets.add(ferengi);
        planets.add(betazoide);
        planets.add(vulcano);

        Weather weather = forecastContext.getForecast(45, planets);
        assertTrue(weather.getPeriod().equals(MeliForecastStrategy.Period.SEQUIA));

        planets.removeAll(planets);
    }

    @Test
    public void testLluvia() {

        forecastContext = new ForecastContext();
        forecastContext.setStrategy(new MeliForecastStrategy());
        planets = new ArrayList<Planet>();

        Planet ferengi = new Planet.Builder()
                .setCivilization("Ferengis")
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(2.0))
                .build();

        Planet betazoide = new Planet.Builder()
                .setCivilization("Betazoides")
                .setVelocity(2)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(8.0))
                .build();

        Planet vulcano = new Planet.Builder()
                .setCivilization("Vulcanos")
                .setVelocity(3)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(18.0))
                .build();

        planets.add(ferengi);
        planets.add(betazoide);
        planets.add(vulcano);

        Weather weather = forecastContext.getForecast(90, planets);
        assertTrue(weather.getPeriod().equals(MeliForecastStrategy.Period.LLUVIA));

        planets.removeAll(planets);
    }

    @Test
    public void testGral() {

        forecastContext = new ForecastContext();
        forecastContext.setStrategy(new MeliForecastStrategy());
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

        for (int day = 1 ; day < 10*365 ; day++) {
            Weather weather = forecastContext.getForecast(day, planets);
            if (weather.getPeriod() != MeliForecastStrategy.Period.INVALIDO)
                System.out.println( weather.toString() );
        }

        planets.removeAll(planets);
    }
}
