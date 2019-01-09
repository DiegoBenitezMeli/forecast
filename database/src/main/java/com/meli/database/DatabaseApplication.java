package com.meli.database;


import com.meli.core.forecast.strategy.ForecastContext;
import com.meli.core.forecast.strategy.MeliForecastStrategy;
import com.meli.core.solar.system.Weather;
import com.meli.database.entity.Forecast;
import com.meli.database.entity.Planet;
import com.meli.database.repository.ForecastRepository;
import com.meli.database.repository.PlanetRepository;
import com.meli.utils.ObjectConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootApplication
@EnableJpaRepositories("com.meli.database.repository")
public class DatabaseApplication implements CommandLineRunner {

    @Autowired
    ForecastRepository forecastRepository;

    @Autowired
    PlanetRepository planetRepository;


    List<com.meli.core.solar.system.Planet> planets;

    /**
     *      * Premisas:
     *      * ●
     *      * El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
     *      * horario. Su distancia con respecto al sol es de 500Km.
     *      * ● El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
     *      * horario. Su distancia con respecto al sol es de 2000Km.
     *      * ● El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
     *      * anti­horario, su distancia con respecto al sol es de 1000Km.
     *      * ● Todas las órbitas son circulares.
     *      * @return
     */
    private void loadPlanets() {

        Planet ferengi = new Planet();
        ferengi.setCivilization("Ferengi");
        ferengi.setDistanceFromSun(500.0);
        ferengi.setSence(com.meli.core.solar.system.Planet.Sence.CLOCKWISE.name());
        ferengi.setVelocity(1);

        Planet betaziode = new Planet();
        betaziode.setCivilization("Betazoide");
        betaziode.setDistanceFromSun(2000.0);
        betaziode.setSence(com.meli.core.solar.system.Planet.Sence.CLOCKWISE.name());
        betaziode.setVelocity(3);

        Planet vulcano = new Planet();
        vulcano.setCivilization("Vulcano");
        vulcano.setDistanceFromSun(1000.0);
        vulcano.setSence(com.meli.core.solar.system.Planet.Sence.COUNTERCLOCKWISE.name());
        vulcano.setVelocity(5);

        planetRepository.deleteAll();

        planetRepository.save(ferengi);
        planetRepository.save(betaziode);
        planetRepository.save(vulcano);

        planets = new ArrayList<>();

        planets.add(ObjectConverter.dbPlanet2PlanetVO(ferengi));
        planets.add(ObjectConverter.dbPlanet2PlanetVO(betaziode));
        planets.add(ObjectConverter.dbPlanet2PlanetVO(vulcano));

    }

    private void loadTenYears(Integer from, Integer to) {

        forecastRepository.deleteAll();

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        ForecastContext forecastContext = new ForecastContext();
        forecastContext.setStrategy(new MeliForecastStrategy());

        for (int day = from ; day < to ; day++) {
            Forecast forecast = new Forecast();
            forecast.setDayNumber(day);
            forecast.setDayTimestamp(new Timestamp((cal.getTimeInMillis())));
            Weather weather = forecastContext.getForecast(day, planets);
            forecast.setWeather(weather.getPeriod().name());
            forecast.setPerimeter(weather.getPerimeter());

            cal.add(Calendar.DAY_OF_WEEK, 1);

            forecastRepository.save(forecast);
        }
    }




    public static void main(String[] args) {
		SpringApplication.run(DatabaseApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
        loadPlanets();

        loadTenYears(1, 10*365);

    }

}

