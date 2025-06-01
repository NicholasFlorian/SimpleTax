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

    @Override
    public boolean equals(Object obj) {
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        IncomeForm that = (IncomeForm) obj;

        return Double.compare(that.amount, amount) == 0;
    }
}
