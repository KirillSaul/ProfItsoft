package org.example.thirdTask;

import java.util.Objects;

public class Ball implements Shape {
    private final Double radius;

    public Ball(Double radius) {
        this.radius = radius;
    }

    @Override
    public Double getVolume() {
        return (4 / 3) * Math.PI * Math.pow(radius, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ball ball = (Ball) o;
        return radius.equals(ball.radius);
    }

    @Override
    public int hashCode() {
        return Objects.hash(radius);
    }

    @Override
    public String toString() {
        return "Ball{" +
                "volume=" + getVolume() +
                '}';
    }
}
