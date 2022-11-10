package org.example.thirdTask;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class MainThirdTask {
    public static List<Shape> sortShapes(List<Shape> shapes) {
        return shapes.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparingInt(Shape::getVolume))
                .collect(Collectors.toList());
    }
}
