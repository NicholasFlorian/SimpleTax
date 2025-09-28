package com.example.simpletax.taxApi;

import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.TaxForm;

import java.util.Collections;
import java.util.List;

public class RestSimpleTaxApi implements SimpleTaxApi {

    @Override
    public void loadInitialIncomeForms() {
        this.addIncomeForm(new IncomeForm("Marc Anthony Group", "MA1", 40));
        this.addIncomeForm(new IncomeForm("Laughing Stock LTD", "L23", 140));
        this.addIncomeForm(new IncomeForm("Earls", "3RL", 300));
        this.addIncomeForm(new IncomeForm("Upper Bench", "UBN", 75));
    }

    @Override
    public void fetchT5() {

    }

    @Override
    public List<IncomeForm> getIncomeForms() {
        return Collections.emptyList();
    }

    @Override
    public List<TaxForm> getTaxForms() {
        return Collections.emptyList();
    }

    @Override
    public int addIncomeForm(IncomeForm incomeForm) {
        return 0;
    }

    @Override
    public void removeIncomeForm(int position) {

    }

    @Override
    public void toggleDeductibleForm(int position) {

    }

    @Override
    public double getGrossIncome() {
        return 0;
    }

    @Override
    public double getDeductions() {
        return 0;
    }

    @Override
    public double getTaxableIncome() {
        return 0;
    }

    @Override
    public double getNetIncome() {
        return 0;
    }
}
