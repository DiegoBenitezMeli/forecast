package com.meli.core.solar.system;

import com.meli.core.geometry.Point;

import java.text.DecimalFormat;

/*

Civilization
Vulcanos -> Vulcano
Ferengis -> Ferengi
Betazoides -> Betazoide

Planet
Name        | velocity | sence     | distanceFromSun
-----------------------------------------------------
Ferengi     | 1 gr/dia | clockwise | 500 kms
Betazoide   | 3 gr/dia | clockwise | 2000 kms
Vulcano     | 5 gr/dia | counterClockwise | 1000kms

*/

public class Planet {

    public static class Builder {
        private String civilization;
        private Integer velocity;
        private Sence sence;
        private Double distanceFromSun;

        public Builder setCivilization(String civilization) {
            this.civilization = civilization;
            return this;
        }

        public Builder setVelocity(Integer velocity) {
            this.velocity = velocity;
            return this;
        }

        public Builder setSence(Sence sence) {
            this.sence = sence;
            return this;
        }

        public Builder setDistanceFromSun(Double distanceFromSun) {
            this.distanceFromSun = distanceFromSun;
            return this;
        }

        public Planet build() {
            Planet planet = new Planet(this.civilization, this.velocity, this.sence, this.distanceFromSun);
            return planet;
        }
    }

    public enum Sence {
        CLOCKWISE,
        COUNTERCLOCKWISE
    }

    final private String civilization;

    final private Integer velocity;

    final private Sence sence;

    final private Double distanceFromSun;


    public Planet(final String civilization, final Integer velocity, final Sence sence, final Double distanceFromSun) {
        this.civilization = civilization;
        this.velocity = velocity;
        this.sence = sence;
        this.distanceFromSun = distanceFromSun;
    }

    /**
     * p = [x0 + r * cos(w*t)] X + [y0 + r * sen(w*t)] Y
     *      | x(t) = x0 + r * cos(wt)
     *  P = |
     *      | y(t) = y0 + r * sen(wt)
     *
     * siendo (x0, y0) el centro del circulo (eje), r su radio, t el tiempo y w la velocidad.
     *
     * @param days
     * @return
     */
    public Point getPosition(int days, Sun sun)  {
        DecimalFormat df = new DecimalFormat("#.##");
        Double x = null;
        Double y = null;

        if (this.sence.equals(Sence.COUNTERCLOCKWISE)) {
            x = sun.getPosition().getX() + this.distanceFromSun * Math.cos(Math.toRadians(this.velocity) * (-days));
            y = sun.getPosition().getY() + this.distanceFromSun * Math.sin(Math.toRadians(this.velocity) * (-days));
        }

        if (this.sence.equals(Sence.CLOCKWISE)) {
            x = sun.getPosition().getX() + this.distanceFromSun * Math.cos(Math.toRadians(this.velocity) * days);
            y = sun.getPosition().getY() + this.distanceFromSun * Math.sin(Math.toRadians(this.velocity) * days);
        }

        x = Double.valueOf(df.format(x));
        y = Double.valueOf(df.format(y));

        return new Point(x, y);
    }

}
