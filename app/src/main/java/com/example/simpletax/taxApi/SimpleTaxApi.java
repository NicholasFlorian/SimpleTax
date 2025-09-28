package com.example.simpletax.taxApi;

import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.TaxForm;

import java.util.List;

public interface SimpleTaxApi {

    double EFFECTIVE_TAX_RATE = 0.25;

    void loadInitialIncomeForms();

    void fetchT5();

    List<IncomeForm> getIncomeForms();

    List<TaxForm> getTaxForms();

    int addIncomeForm(IncomeForm incomeForm);

    void removeIncomeForm(int position);

    void toggleDeductibleForm(int position);

    double getGrossIncome();

    double getDeductions();

    double getTaxableIncome();

    double getNetIncome();

}
