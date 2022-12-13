package org.example.secondTask;

import java.util.Objects;

public class PoliceProtocol {
    private final String type;
    private Double fineAmount;

    public PoliceProtocol(String type, Double fineAmount) {
        this.type = type;
        this.fineAmount = fineAmount;
    }

    public String getType() {
        return type;
    }

    public Double getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(Double fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void addFineAmount(Double fineAmount) {
        this.fineAmount += fineAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PoliceProtocol policeProtocol = (PoliceProtocol) o;
        return type.equals(policeProtocol.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
