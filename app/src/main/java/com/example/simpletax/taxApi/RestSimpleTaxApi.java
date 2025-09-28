package com.example.simpletax.taxApi;

import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.TaxForm;

import java.util.Collections;
import java.util.List;

public class RestSimpleTaxApi implements SimpleTaxApi {

    SimpleTaxApiListener listener;

    public RestSimpleTaxApi(SimpleTaxApiListener listener) {
        this.listener = listener;
    }

    @Override
    public void loadInitialIncomeForms() {
        this.addIncomeForm(new IncomeForm("Marc Anthony Group", "BCTD", 40));
        this.addIncomeForm(new IncomeForm("Laughing Stock LTD", "WSET", 140));
        this.addIncomeForm(new IncomeForm("Earls", "3RL", 300));
        this.addIncomeForm(new IncomeForm("Upper Bench", "BCTD", 75));
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
    public void addDeductibleForm(DeductibleForm deductibleForm){

    }

    @Override
    public void addIncomeForm(IncomeForm incomeForm) {

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
