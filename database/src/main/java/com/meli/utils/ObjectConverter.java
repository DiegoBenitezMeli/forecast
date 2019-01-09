package com.meli.utils;

import com.meli.core.solar.system.Planet;

public class ObjectConverter {

    public static Planet dbPlanet2PlanetVO(com.meli.database.entity.Planet planet) {

        Planet.Builder builder = new Planet.Builder()
                .setCivilization(planet.getCivilization())
                .setDistanceFromSun(planet.getDistanceFromSun())
                .setSence(Planet.Sence.valueOf(planet.getSence()))
                .setVelocity(planet.getVelocity());

        return builder.build();

    }
}
