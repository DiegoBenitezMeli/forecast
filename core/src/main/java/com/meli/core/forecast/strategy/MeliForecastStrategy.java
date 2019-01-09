package com.meli.core.forecast.strategy;

import com.meli.core.geometry.Line;
import com.meli.core.geometry.Point;
import com.meli.core.geometry.Triangle;
import com.meli.core.solar.system.Planet;
import com.meli.core.solar.system.Sun;
import com.meli.core.solar.system.Weather;

import java.util.List;

public class MeliForecastStrategy implements ForecastStrategy {

    public enum Period {
        INVALIDO,
        LLUVIA,
        SEQUIA,
        OPTIMO
    }

    private Sun sun;

    public MeliForecastStrategy() {
        sun = new Sun();
    }


    /**
     * A continuación el diagrama del sistema solar.
     * Premisas:
     * ● El planeta Ferengi se desplaza con una velocidad angular de 1 grados/día en sentido
     * horario
     * . Su distancia con respecto al sol es de 500Km.
     * ● El planeta Betasoide se desplaza con una velocidad angular de 3 grados/día en sentido
     * horario
     * . Su distancia con respecto al sol es de 2000Km.
     * ● El planeta Vulcano se desplaza con una velocidad angular de 5 grados/día en sentido
     * anti­horario
     * , su distancia con respecto al sol es de 1000Km.
     * ● Todas las órbitas son circulares.
     *
     * 1. Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
     * sistema solar experimenta un período de sequía.
     * 2. Cuando los tres planetas no están alineados, forman entre sí un triángulo. Es sabido que en el
     * momento en el que el sol se encuentra dentro del triángulo, el sistema solar experimenta un
     * período de lluvia, teniendo éste, un pico de intensidad cuando el perímetro del triángulo está en
     * su máx
     * 3. Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
     * alineados entre sí pero no están alineados con el sol.
     *
     * @param day
     * @param planets
     * @return
     */
    public Weather forecast(Integer day, List<Planet> planets)  {

        Point a = planets.get(0).getPosition(day, sun);
        Point b = planets.get(1).getPosition(day, sun);
        Point c = planets.get(2).getPosition(day, sun);

        Line ab = new Line(a,b);
        Weather weather = new Weather(day);

        if (ab.aligned(c)) {
            if (ab.aligned(sun.getPosition())) {
                /*
                 * Cuando los tres planetas están alineados entre sí y a su vez alineados con respecto al sol, el
                 * el sistema solar experimenta un período de sequía.*/
                weather.setPeriod(Period.SEQUIA);
            } else {
                /*
                 * Las condiciones óptimas de presión y temperatura se dan cuando los tres planetas están
                 * alineados entre sí pero no están alineados con el sol.
                 * */
                weather.setPeriod(Period.OPTIMO);
            }
        } else if (ab.isTriangle(c)) {
            Triangle triangle = new Triangle(a, b, c);
            if (triangle.contain(sun.getPosition())) {
                weather.setPerimeter(triangle.perimeter());
                weather.setPeriod(Period.LLUVIA);
            }
        }
        return weather;
    }
}
