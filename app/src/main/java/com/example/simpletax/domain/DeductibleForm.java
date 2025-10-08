package com.example.simpletax.domain;

public class DeductibleForm extends TaxForm {

    private final double deductiblePercent;
    private final double max;
    private boolean enabled;

    public DeductibleForm(
        String name,
        String id,
        double deductiblePercent,
        double minGrossIncome
    ) {
        super(name, id);
        this.deductiblePercent = deductiblePercent;
        this.max = minGrossIncome;
        this.enabled = true;
    }

    public double getDeductiblePercent() {
        return deductiblePercent;
    }

    public double getMax() {
        return max;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void flipEnabled() {
        this.enabled = !this.enabled;
    }

    public void setEnabled(boolean isEnabled) { this.enabled = isEnabled; };
}
