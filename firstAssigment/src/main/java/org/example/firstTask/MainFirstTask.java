package org.example.firstTask;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class MainFirstTask {
    public static List<Integer> arraySort(List<Integer> numbers) {
        return numbers
                .stream()
                .filter(number -> nonNull(number) && number >= 0)
                .sorted(Collections.reverseOrder())
                .collect(Collectors.toList());
    }
}
