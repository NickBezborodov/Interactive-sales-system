package org.example.model;

public class OrderResult {
    private final String companyName;
    private final double finalPrice;

    public OrderResult(String companyName, double finalPrice) {
        this.companyName = companyName;
        this.finalPrice = finalPrice;
    }

    public String getCompanyName() {
        return companyName;
    }

    public double getFinalPrice() {
        return finalPrice;
    }
}

