package org.example.thirdTask;

import java.util.Objects;

public class Cylinder implements Shape {
    private final Double square;
    private final Double height;

    public Cylinder(Double square, Double height) {
        this.square = square;
        this.height = height;
    }

    @Override
    public Double getVolume() {
        return square * height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cylinder cylinder = (Cylinder) o;
        return square.equals(cylinder.square) && height.equals(cylinder.height);
    }

    @Override
    public int hashCode() {
        return Objects.hash(square, height);
    }

    @Override
    public String toString() {
        return "Cylinder{" +
                "volume=" + getVolume() +
                '}';
    }
}
