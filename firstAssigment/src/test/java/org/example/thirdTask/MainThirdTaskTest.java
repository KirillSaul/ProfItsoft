package org.example.thirdTask;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MainThirdTaskTest {

    @Test
    void sortShapes() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Cylinder(4.0,5.0));
        shapes.add(new Cube(5.9));
        shapes.add(new Ball(6.6));
        shapes.add(null);

        List<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Cylinder(4.0,5.0));
        expectedShapes.add(new Cube(5.9));
        expectedShapes.add(new Ball(6.6));

        assertEquals(expectedShapes, MainThirdTask.sortShapes(shapes));
    }
}