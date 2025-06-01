package com.example.simpletax.ui.taxCalculator;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.simpletax.api.MockSimpleTaxApi;
import com.example.simpletax.domain.TaxForm;
import com.example.simpletax.domain.TaxOutcome;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class TaxCalculatorViewModel extends ViewModel {

    private MockSimpleTaxApi simpleTaxApi;

    @Inject
    public TaxCalculatorViewModel(MockSimpleTaxApi simpleTaxApi) {
        this.simpleTaxApi = simpleTaxApi;
    }

    public void fetchT5() {
        simpleTaxApi.fetchT5();
    }

    public LiveData<List<TaxForm>> getTaxForms() {
        return simpleTaxApi.getTaxForms();
    }

    public LiveData<TaxOutcome> getTaxOutcome() {
        return simpleTaxApi.getTaxOutcome();
    }
}
