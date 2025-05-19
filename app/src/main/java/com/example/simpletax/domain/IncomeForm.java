package com.example.simpletax.domain;

public class IncomeForm extends TaxForm {

    private double amount;

    public IncomeForm(String name, String id, double amount) {
        super(name, id);
        this.amount = amount;
    }

    public double getAmount() {
        return amount;
    }
}
