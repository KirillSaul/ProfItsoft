package org.example.secondTask;

import java.time.Instant;

public class Item {
    @Property(format = "dd.MM.yyyy HH:mm")
    private Instant dataTimeField;
    @Property(name = "prefix.intFieldAnnotation")
    private int intField;
    @Property(name = "Integer")
    private Integer integerField;
    private String stringField;

    public Instant getDataTimeField() {
        return dataTimeField;
    }

    public void setDataTimeField(Instant dataTimeField) {
        this.dataTimeField = dataTimeField;
    }

    public int getIntField() {
        return intField;
    }

    public void setIntField(int intField) {
        this.intField = intField;
    }

    public Integer getIntegerField() {
        return integerField;
    }

    public void setIntegerField(Integer integerField) {
        this.integerField = integerField;
    }

    public String getStringField() {
        return stringField;
    }

    public void setStringField(String stringField) {
        this.stringField = stringField;
    }

    @Override
    public String toString() {
        return "Item{" +
                "dataTimeField=" + dataTimeField +
                ", intField=" + intField +
                ", integerField=" + integerField +
                ", stringField='" + stringField + '\'' +
                '}';
    }
}
