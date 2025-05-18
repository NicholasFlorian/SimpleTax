package com.example.simpletax.domain;

public class T5 extends TaxForm {

    private final double deductiblePercent;
    private final double max;

    public T5(
        String name,
        String id,
        double amount,
        double deductiblePercent,
        String minGrossIncome
    ) {
        super(name, id, amount);
        this.deductiblePercent = deductiblePercent;
        this.max = Double.parseDouble(minGrossIncome);
    }

    public double getDeductiblePercent() {
        return deductiblePercent;
    }

    public double getMax() {
        return max;
    }
}
