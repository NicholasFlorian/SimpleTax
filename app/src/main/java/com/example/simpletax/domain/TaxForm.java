package com.example.simpletax.domain;

public abstract class TaxForm {

    private final String name;
    private final String id;
    private final double amount;

    public TaxForm(String name, String id, double amount) {
        this.id = id;
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

}
