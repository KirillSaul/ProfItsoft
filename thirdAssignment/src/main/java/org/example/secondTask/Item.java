package org.example.secondTask;

import java.time.Instant;

public class Item {
   private Instant test;

    public Instant getTest() {
        return test;
    }

    public void setTest(Instant test) {
        this.test = test;
    }

    @Override
    public String toString() {
        return "Item{" +
                "test='" + test + '\'' +
                '}';
    }
}
