package com.meli.core.solar.system;

import com.meli.core.geometry.Point;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;

import static org.junit.Assert.assertTrue;

public class PlanetTest {

    Planet planetClockwise;
    Planet planetCounterClockwise;
    Sun sun;

    @Before
    public void setUp() {
        sun = new Sun();
        planetClockwise = new Planet.Builder()
                .setVelocity(1)
                .setSence(Planet.Sence.CLOCKWISE)
                .setDistanceFromSun(Math.sqrt(2.0))
                .build();

        planetCounterClockwise = new Planet.Builder()
                .setVelocity(1)
                .setSence(Planet.Sence.COUNTERCLOCKWISE)
                .setDistanceFromSun(Math.sqrt(2.0))
                .build();
    }

    /**
     * Resultados esperados para un velidad angular de 1 grado por dia de un circulo de radio = raiz(2)
     * con sentido horario
     *  Dias (grados)   | (x, y) esperado
     *  ----------------------------------
     *     0 gr         | (raiz(2), 0)
     *    45 gr         | (1, 1)
     *    90 gr         | (0, raiz(2)
     *   135 gr         | (-1, 1)
     *   180 gr         | (-raiz(2), 0)
     *   225 gr         | (-1,-1)
     *   270 gr         | (0, -raiz(2))
     *   360 gr         | (raiz(2), 0)
     *   405 gr         | (1, 1)
     */
    @Test
    public void testPositionClockwide() {

        DecimalFormat df = new DecimalFormat("#.##");
        Double sqtr2 = Double.valueOf(df.format(Math.sqrt(2.0)));

        Point position0 = planetClockwise.getPosition(0, sun);
        assertTrue(new Point(sqtr2, 0).equals(position0));

        Point position45 = planetClockwise.getPosition(45, sun);
        assertTrue(new Point(1.0, 1.0).equals(position45));

        Point position90 = planetClockwise.getPosition(90, sun);
        assertTrue(new Point(0, sqtr2).equals(position90));

        Point position135 = planetClockwise.getPosition(135, sun);
        assertTrue(new Point(-1.0, 1.0).equals(position135));

        Point position180 = planetClockwise.getPosition(180, sun);
        assertTrue(new Point(-sqtr2, 0).equals(position180));

        Point position225 = planetClockwise.getPosition(225, sun);
        assertTrue(new Point(-1.0, -1.0).equals(position225));

        Point position270 = planetClockwise.getPosition(270, sun);
        assertTrue(new Point(-0.0, -sqtr2).equals(position270));

        Point position315 = planetClockwise.getPosition(315, sun);
        assertTrue(new Point(1, -1.0).equals(position315));

        Point position360 = planetClockwise.getPosition(360, sun);

        assertTrue(position0.equals(new Point(position360.getX(), Math.abs(position360.getY()))));

        Point position405 = planetClockwise.getPosition(405, sun);
        assertTrue(position405.equals(position45));

        Point position2_360 = planetClockwise.getPosition(2*360, sun);
        assertTrue(position2_360.equals(position360));
    }

    /**
     * Resultados esperados para un velidad angular de 1 grado por dia de un circulo de radio = raiz(2)
     * con sentido horario
     *  Dias (grados)   | (x, y) esperado
     *  ----------------------------------
     *     0 gr         | (raiz(2), 0)
     *    45 gr         | (1, -1)
     *    90 gr         | (0, -raiz(2))
     *   135 gr         | (-1,-1)
     *   180 gr         | (-raiz(2), 0)
     *   225 gr         | (-1, 1)
     *   270 gr         | (0, raiz(2)
     *   360 gr         | (raiz(2), 0)
     *   405 gr         |
     */
    @Test
    public void testPositionCounterClockwise() {

        DecimalFormat df = new DecimalFormat("#.##");
        Double sqtr2 = Double.valueOf(df.format(Math.sqrt(2.0)));

        Point position0 = planetCounterClockwise.getPosition(0, sun);
        assertTrue(new Point(sqtr2, 0).equals(position0));

        Point position45 = planetCounterClockwise.getPosition(45, sun);
        assertTrue(new Point(1.0, -1.0).equals(position45));

        Point position90 = planetCounterClockwise.getPosition(90, sun);
        assertTrue(new Point(0, -sqtr2).equals(position90));

        Point position135 = planetCounterClockwise.getPosition(135, sun);
        assertTrue(new Point(-1.0, -1.0).equals(position135));

        Point position180 = planetCounterClockwise.getPosition(180, sun);
        assertTrue(new Point(-sqtr2, -0.0).equals(position180));

        Point position225 = planetCounterClockwise.getPosition(225, sun);
        assertTrue(new Point(-1.0, 1.0).equals(position225));

        Point position270 = planetCounterClockwise.getPosition(270, sun);
        assertTrue(new Point(-0.0, sqtr2).equals(position270));

        Point position315 = planetCounterClockwise.getPosition(315, sun);
        assertTrue(new Point(1, 1).equals(position315));

        Point position360 = planetCounterClockwise.getPosition(360, sun);
        assertTrue(position0.equals(new Point(position360.getX(), Math.abs(position360.getY()))));

        Point position405 = planetCounterClockwise.getPosition(405, sun);
        assertTrue(position405.equals(position45));
    }

}
