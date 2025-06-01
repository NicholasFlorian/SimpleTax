package com.example.simpletax.domain;

public class TaxOutcome {

    private final double grossIncome;
    private final double deductions;
    private final double taxableIncome;
    private final double netIncome;

    public TaxOutcome(double grossIncome, double deductions, double taxableIncome, double netIncome) {
        this.grossIncome = grossIncome;
        this.deductions = deductions;
        this.taxableIncome = taxableIncome;
        this.netIncome = netIncome;
    }

    public double getGrossIncome() {
        return grossIncome;
    }

    public double getDeductions() {
        return deductions;
    }

    public double getTaxableIncome() {
        return taxableIncome;
    }

    public double getNetIncome() {
        return netIncome;
    }
}
