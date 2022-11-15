package org.example.secondTask;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class MainSecondTaskTest {

    @Test
    void countHashTag() {
        List<String> hashTags = new ArrayList<>();
        hashTags.add("mustIgnored#test1");
        hashTags.add("#test1#test1");
        hashTags.add("mustIgnored#test1");
        hashTags.add("mustIgnored#test1#test2");
        hashTags.add("#test1#test2");
        hashTags.add("#test2#test2");
        hashTags.add("mustIgnored#test3");
        hashTags.add("mustIgnored#test3#test3");
        hashTags.add("#test3");
        hashTags.add("mustIgnored#test3");
        hashTags.add("#test4");
        hashTags.add("#test4");
        hashTags.add("#test5");
        hashTags.add("#test6");
        hashTags.add("#test7");
        hashTags.add(null);

        Map<String, Integer> expectedValues = new LinkedHashMap<>();
        expectedValues.put("test1", 5);
        expectedValues.put("test3", 4);
        expectedValues.put("test2", 3);
        expectedValues.put("test4", 2);
        expectedValues.put("test5", 1);

        assertEquals(expectedValues, MainSecondTask.countHashTag(hashTags));
    }
}