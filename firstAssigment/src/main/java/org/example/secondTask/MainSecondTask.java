package org.example.secondTask;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

public class MainSecondTask {

    public static LinkedHashMap<String, Integer> countHashTag(List<String> stringsForCount) {
        Pattern pattern = Pattern.compile("#.+");
        Matcher matcher;
        Map<String, Integer> map = new LinkedHashMap<>();
        for (var string : stringsForCount) {
            if (nonNull(string)) {
                matcher = pattern.matcher(string);
                while (matcher.find()) {
                    if (map.containsKey(matcher.group())) {
                        map.put(matcher.group(), map.get(matcher.group()) + 1);
                    } else {
                        map.put(matcher.group(), 1);
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
