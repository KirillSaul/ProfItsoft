package org.example.secondTask;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClassUtilityTest {

    @Test
    void loadFromProperties() {
        Item item = ClassUtility.loadFromProperties(Item.class, Path.of("files/secondTask/properties.txt"));
        assertEquals(item.getDataTimeField(), Instant.parse("2022-12-29T16:30:00Z"));
        assertEquals(item.getIntField(), 8);
        assertEquals(item.getIntegerField(), 50);
        assertEquals(item.getStringField(), "my string");
    }
}