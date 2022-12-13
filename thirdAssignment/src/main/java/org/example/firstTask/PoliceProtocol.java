package org.example.firstTask;

import java.math.BigDecimal;
import java.util.Objects;

public class PoliceProtocol {
    private final String type;
    private BigDecimal fineAmount;

    public PoliceProtocol(String type, BigDecimal fineAmount) {
        this.type = type;
        this.fineAmount = fineAmount;
    }

    public String getType() {
        return type;
    }

    public BigDecimal getFineAmount() {
        return fineAmount;
    }

    public void setFineAmount(BigDecimal fineAmount) {
        this.fineAmount = fineAmount;
    }

    public void addFineAmount(BigDecimal fineAmount) {
        this.fineAmount = this.fineAmount.add(fineAmount);
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
