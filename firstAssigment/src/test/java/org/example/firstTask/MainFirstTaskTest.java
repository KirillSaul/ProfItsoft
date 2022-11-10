package org.example.firstTask;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainFirstTaskTest {

    @Test
    void arraySort() {
        List<Integer> integers = new ArrayList<>();
        integers.add(4);
        integers.add(-1);
        integers.add(3);
        integers.add(null);
        integers.add(0);
        integers.add(-45);

        assertEquals(List.of(4, 3, 0), MainFirstTask.arraySort(integers));
    }
}