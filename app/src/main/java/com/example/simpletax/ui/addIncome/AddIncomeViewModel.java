package com.example.simpletax.ui.addIncome;


import androidx.lifecycle.ViewModel;

import com.example.simpletax.api.MockSimpleTaxApi;
import com.example.simpletax.domain.IncomeForm;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AddIncomeViewModel extends ViewModel {

    private final MockSimpleTaxApi simpleTaxApi;

    @Inject
    public AddIncomeViewModel(MockSimpleTaxApi simpleTaxApi) {
        this.simpleTaxApi = simpleTaxApi;
    }

    public void addIncome(IncomeForm incomeForm) {
        simpleTaxApi.addIncomeForm(incomeForm);
    }

}
