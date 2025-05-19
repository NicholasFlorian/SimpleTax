package com.example.simpletax.domain;

public class DeductibleForm extends TaxForm {

    private final double deductiblePercent;
    private final double max;

    public DeductibleForm(
        String name,
        String id,
        double deductiblePercent,
        double minGrossIncome
    ) {
        super(name, id);
        this.deductiblePercent = deductiblePercent;
        this.max = minGrossIncome;
    }

    public double getDeductiblePercent() {
        return deductiblePercent;
    }

    public double getMax() {
        return max;
    }
}
