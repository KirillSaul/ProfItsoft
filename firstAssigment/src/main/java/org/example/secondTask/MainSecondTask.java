package org.example.secondTask;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class MainSecondTask {

    public static LinkedHashMap<String, Integer> countHashTag(List<String> stringsForCount) {
        Map<String, Integer> map = new LinkedHashMap<>();
        Set<String> hashTags;
        for (var string : stringsForCount) {
            if (nonNull(string)) {
                hashTags = Arrays.stream(string.substring(string.indexOf("#")).split("#")).collect(Collectors.toSet());
                for (var hashTag : hashTags) {
                    if (!hashTag.isBlank()) {
                        if (map.containsKey(hashTag)) {
                            map.put(hashTag, map.get(hashTag) + 1);
                        } else {
                            map.put(hashTag, 1);
                        }
                    }
                }
            }
        }
        return map
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .limit(5)
                .collect(Collectors
                        .toMap(Map.Entry::getKey,
                                Map.Entry::getValue,
                                (x, y) -> y,
                                LinkedHashMap::new)
                );
    }
}
