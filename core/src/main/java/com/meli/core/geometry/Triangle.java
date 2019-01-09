package com.meli.core.geometry;

import java.text.DecimalFormat;

public class Triangle {

    private final Point a;
    private final Point b;
    private final Point c;
    private double ab;
    private double bc;
    private double ca;

    public Triangle(final Point a, final Point b, final Point c) {
        this.a = a;
        this.b = b;
        this.c = c;
        ab = a.dist(b);
        bc = b.dist(c);
        ca = c.dist(a);
    }

    /**
     * @return
     */
    public Double perimeter() {
        DecimalFormat df = new DecimalFormat("#.##");
        return  Double.valueOf(df.format(ab + bc + ca));
    }

    /**
     *
     * @return
     */
    public Double area() {
        return Math.abs(a.getX() * b.getY() - a.getY() * b.getX() + b.getX() * c.getY() - b.getY() * c.getX() + c.getX() * a.getY() - c.getY() * a.getX()) / 2.0;
    }

    /**
     * Precondicion:
     * @param p
     * @return
     */
    public boolean contain(Point p) {
        Triangle abp = new Triangle(a, b, p);
        Triangle apc = new Triangle(a, p, c);
        Triangle bpc = new Triangle(b, p, c);

        return this.area().equals(abp.area() + apc.area() + bpc.area());
    }
}
