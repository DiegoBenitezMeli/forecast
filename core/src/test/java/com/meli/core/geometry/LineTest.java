package com.meli.core.geometry;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class LineTest {

    @Test
    public void align() {
        Point a = new Point(2.0, 1.0);
        Point b = new Point(4.0, 2.0);
        Point c = new Point(6.0, 3.0);

        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Line ac = new Line(a, c);
        Line ca = new Line(c, a);
        Line bc = new Line(b, c);
        Line cb = new Line(c, b);

        assertTrue(ab.aligned(c));
        assertTrue(ba.aligned(c));
        assertTrue(ac.aligned(b));
        assertTrue(ca.aligned(b));
        assertTrue(bc.aligned(a));
        assertTrue(cb.aligned(a));
    }

    @Test
    public void verticalLines() {

        for (Double x = 0.0 ; x < 3 ; x++) {
            for (Double y = 0.0 ; y < 3 ; y++) {

                Point a = new Point(x, y);
                Point b = new Point(x, 0);
                Point c = new Point(x,  -y);

                Line ab = new Line(a, b);
                Line ba = new Line(b, a);
                Line ac = new Line(a, c);
                Line ca = new Line(c, a);
                Line bc = new Line(b, c);
                Line cb = new Line(c, b);

                assertTrue(ab.aligned(c));
                assertTrue(ba.aligned(c));
                assertTrue(ac.aligned(b));
                assertTrue(ca.aligned(b));
                assertTrue(bc.aligned(a));
                assertTrue(cb.aligned(a));

            }
        }

    }

    @Test
    public void horizontalLines() {
        for (Double x = 0.0 ; x < 3 ; x++) {
            for (Double y = 0.0 ; y < 3 ; y++) {

                Point a = new Point(x - 1, y);
                Point b = new Point(0, y);
                Point c = new Point(x + 1,  y);

                Line ab = new Line(a, b);
                Line ba = new Line(b, a);
                Line ac = new Line(a, c);
                Line ca = new Line(c, a);
                Line bc = new Line(b, c);
                Line cb = new Line(c, b);

                assertTrue(ab.aligned(c));
                assertTrue(ba.aligned(c));
                assertTrue(ac.aligned(b));
                assertTrue(ca.aligned(b));
                assertTrue(bc.aligned(a));
                assertTrue(cb.aligned(a));

            }
        }

    }

    @Test
    public void yZeroAlign() {
        Point a = new Point(500,0);
        Point b = new Point(1000,0);
        Point c = new Point(2000,0);

        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Line ac = new Line(a, c);
        Line ca = new Line(c, a);
        Line bc = new Line(b, c);
        Line cb = new Line(c, b);

        assertTrue(ab.aligned(c));
        assertTrue(ba.aligned(c));
        assertTrue(ac.aligned(b));
        assertTrue(ca.aligned(b));
        assertTrue(bc.aligned(a));
        assertTrue(cb.aligned(a));

    }

    @Test
    public void xZeroAlign() {
        Point a = new Point(0,500);
        Point b = new Point(0,1000);
        Point c = new Point(0,2000);

        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Line ac = new Line(a, c);
        Line ca = new Line(c, a);
        Line bc = new Line(b, c);
        Line cb = new Line(c, b);

        assertTrue(ab.aligned(c));
        assertTrue(ba.aligned(c));
        assertTrue(ac.aligned(b));
        assertTrue(ca.aligned(b));
        assertTrue(bc.aligned(a));
        assertTrue(cb.aligned(a));

    }

    @Test
    public void zeroAlign() {
        Point a = new Point(0,0);
        Point b = new Point(0,0);
        Point c = new Point(0,0);

        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Line ac = new Line(a, c);
        Line ca = new Line(c, a);
        Line bc = new Line(b, c);
        Line cb = new Line(c, b);

        assertTrue(ab.aligned(c));
        assertTrue(ba.aligned(c));
        assertTrue(ac.aligned(b));
        assertTrue(ca.aligned(b));
        assertTrue(bc.aligned(a));
        assertTrue(cb.aligned(a));

    }

    @Test
    public void notAlign() {
        Point a = new Point(2.0, 1.0);
        Point b = new Point(4.0, 1.0);
        Point c = new Point(6.0, 3.0);

        Line ab = new Line(a, b);
        Line ba = new Line(b, a);
        Line ac = new Line(a, c);
        Line ca = new Line(c, a);
        Line bc = new Line(b, c);
        Line cb = new Line(c, b);

        assertFalse(ab.aligned(c));
        assertFalse(ba.aligned(c));
        assertFalse(ac.aligned(b));
        assertFalse(ca.aligned(b));
        assertFalse(bc.aligned(a));
        assertFalse(cb.aligned(a));
    }

}

