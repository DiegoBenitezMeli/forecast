package com.meli.core.solar.system;

import com.meli.core.geometry.Point;

public class Sun {

    private final Point position;

    public Sun() {
        position = new Point(0,0);
    }

    public Point getPosition() {
        return position;
    }
}
