package org.example.thirdTask;

import java.util.Objects;

public class Cube implements Shape {
    private final Double length;

    public Cube(Double length) {
        this.length = length;
    }

    @Override
    public Double getVolume() {
        return Math.pow(length, 3);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cube cube = (Cube) o;
        return length.equals(cube.length);
    }

    @Override
    public int hashCode() {
        return Objects.hash(length);
    }

    @Override
    public String toString() {
        return "Cube{" +
                "volume=" + getVolume() +
                '}';
    }
}
