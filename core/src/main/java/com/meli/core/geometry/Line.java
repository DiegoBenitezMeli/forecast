package com.meli.core.geometry;

public class Line {

    private final Point a;
    private final Point b;

    public Line(final Point a, final Point b) {
        this.a = a;
        this.b = b;
    }

    public boolean aligned(Point p) {
        return (a.getY() - b.getY()) * (a.getX() - p.getX()) == (a.getY() - p.getY()) * (a.getX() - b.getX());
    }

    public boolean isTriangle(Point p) {
        return !this.aligned(p);
    }
}
