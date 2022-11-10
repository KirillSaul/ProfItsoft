package org.example.thirdTask;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainThirdTaskTest {

    @Test
    void sortShapes() {
        List<Shape> shapes = new ArrayList<>();
        shapes.add(new Cylinder(49));
        shapes.add(new Cube(5));
        shapes.add(new Cylinder(123));
        shapes.add(new Ball(2));
        shapes.add(new Ball(66));
        shapes.add(null);

        List<Shape> expectedShapes = new ArrayList<>();
        expectedShapes.add(new Ball(2));
        expectedShapes.add(new Cube(5));
        expectedShapes.add(new Cylinder(49));
        expectedShapes.add(new Ball(66));
        expectedShapes.add(new Cylinder(123));

        assertEquals(expectedShapes, MainThirdTask.sortShapes(shapes));
    }
}