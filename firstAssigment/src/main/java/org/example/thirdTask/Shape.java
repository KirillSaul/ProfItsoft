package org.example.thirdTask;

import java.util.Objects;

public class Shape {
    private final Integer volume;

    public Shape(Integer volume) {
        this.volume = volume;
    }

    public Integer getVolume() {
        return this.volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Shape shape = (Shape) o;
        return volume.equals(shape.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(volume);
    }

    @Override
    public String toString() {
        return "Shape{" +
                "volume=" + volume +
                '}';
    }
}
