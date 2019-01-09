package com.meli.core.geometry;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TriangleTest {


    @Test
    public void invalidTriangle() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 2);
        Point c = new Point(3, 3 );

        Triangle triangle = new Triangle(a, b, c);

        Double area = triangle.area();
        assertTrue(area == 0);

        Line ab = new Line(a, b);
        assertFalse(ab.isTriangle(c));
    }

    @Test
    public void isATriangle() {
        Point a = new Point(1, 1);
        Point b = new Point(2, 4);
        Point c = new Point(3, 3 );

        Triangle triangle = new Triangle(a, b, c);
        assertTrue(new Line(a, b).isTriangle(c));
    }

}
