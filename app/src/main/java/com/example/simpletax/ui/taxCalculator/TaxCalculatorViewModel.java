package com.example.simpletax.ui.taxCalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.simpletax.api.MockSimpleTaxApi;
import com.example.simpletax.domain.TaxForm;

import java.util.List;

public class TaxCalculatorViewModel extends ViewModel {

    private MockSimpleTaxApi simpleTaxApi;

    private MutableLiveData<Double> grossIncome;
    public LiveData<Double> getGrossIncome() { return grossIncome; }

    private MutableLiveData<Double> deductions;
    public LiveData<Double> getDeductions() { return deductions; }

    private MutableLiveData<Double> taxableIncome;
    public LiveData<Double> getTaxableIncome() { return taxableIncome; }

    private MutableLiveData<Double> netIncome;
    public LiveData<Double> getNetIncome() { return netIncome; }

    private MutableLiveData<List<TaxForm>> taxForms;
    public LiveData<List<TaxForm>> getTaxForms() { return taxForms; }


    public TaxCalculatorViewModel() {

        simpleTaxApi = new MockSimpleTaxApi();
        grossIncome = new MutableLiveData<>(0.0);
    }


}
