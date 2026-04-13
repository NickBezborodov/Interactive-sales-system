package org.example.model;

import java.time.LocalDateTime;

public class Order {

    private final LocalDateTime timestamp;
    private final String companyName;
    private final int quantityKg;

    public Order(LocalDateTime timestamp, String companyName, int quantityKg) {
        this.timestamp = timestamp;
        this.companyName = companyName;
        this.quantityKg = quantityKg;
    }

    public String getCompanyName() {
        return companyName;
    }

    public int getQuantityKg() {
        return quantityKg;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
