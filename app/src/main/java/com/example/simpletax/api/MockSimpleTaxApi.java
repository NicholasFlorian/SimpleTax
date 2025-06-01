package com.example.simpletax.api;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;
import com.example.simpletax.domain.TaxOutcome;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class MockSimpleTaxApi {

    private final HashMap<String, ArrayList<IncomeForm>> incomeFormMap;
    private final ArrayList<DeductibleForm> deductibleFormList;
    private final MutableLiveData<List<TaxForm>> taxFormList;

    private final MutableLiveData<TaxOutcome> taxOutcome;

    @Inject
    public MockSimpleTaxApi() {
        incomeFormMap = new HashMap<>();
        deductibleFormList = new ArrayList<>();
        taxFormList = new MutableLiveData<>(new ArrayList<>());

        taxOutcome = new MutableLiveData<>(new TaxOutcome(0, 0, 0, 0));

        loadInitialIncomeForms();
    }

    private List<IncomeForm> getIncomeForms() {
        return incomeFormMap.values().stream().flatMap(ArrayList::stream).toList();
    }

    private void updateList() {
        taxFormList.setValue(
            Stream.concat(
                deductibleFormList.stream(),
                getIncomeForms().stream()
            ).sorted(Comparator.comparing(TaxForm::getCreatedAt)).toList()
        );

        calculate();
    }

    private void calculate() {
        double grossIncome = 0;
        for(IncomeForm incomeForm : getIncomeForms()) {
            grossIncome += incomeForm.getAmount();
        }

        double deductions = calculateDeductions(grossIncome);
        double taxableIncome = grossIncome - deductions;
        double netIncome = taxableIncome * 0.85;

        taxOutcome.setValue(new TaxOutcome(grossIncome, deductions, taxableIncome, netIncome));
    }

    private double calculateDeductions(double grossIncome) {
        double deductions = 0;
        for(DeductibleForm deductibleForm : deductibleFormList) {

            if(!deductibleForm.isEnabled()){
                continue;
            }

            double totalTaxable;
            if(deductibleForm.getId().isEmpty()) {
                totalTaxable = Math.min(deductibleForm.getMax(), grossIncome);
            }
            else if (incomeFormMap.containsKey(deductibleForm.getId())) {
                ArrayList<IncomeForm> incomeForms = incomeFormMap.get(deductibleForm.getId());

                double idTotalIncome = 0;
                for (IncomeForm incomeForm : incomeForms) {
                    idTotalIncome += incomeForm.getAmount();
                }
                totalTaxable = Math.min(deductibleForm.getMax(), idTotalIncome);
            }
            else {
                continue;
            }

            double deductibleAmount = totalTaxable * deductibleForm.getDeductiblePercent();
            deductions += deductibleAmount;
        }
        return deductions;
    }

    private void loadInitialIncomeForms() {
        this.addIncomeForm(new IncomeForm("Marc Anthony Group", "MA1", 40));
        this.addIncomeForm(new IncomeForm("Laughing Stock LTD", "L23", 140));
        this.addIncomeForm(new IncomeForm("Earls", "3RL", 300));
        this.addIncomeForm(new IncomeForm("Upper Bench", "UBN", 75));
    }

    /* Public Facing Functions in the API */
    public void addIncomeForm(IncomeForm incomeForm) {
        if(incomeFormMap.containsKey(incomeForm.getId())) {
            Objects.requireNonNull(incomeFormMap.get(incomeForm.getId())).add(incomeForm);
        } else {
            ArrayList<IncomeForm> incomeForms = new ArrayList<>();
            incomeForms.add(incomeForm);
            incomeFormMap.put(incomeForm.getId(), incomeForms);
        }
        updateList();
    }

    public void fetchT5() {
        deductibleFormList.clear();
        deductibleFormList.add(new DeductibleForm("Young Chef Initiative", "3RL",  0.2, 200));
        deductibleFormList.add(new DeductibleForm("WSET", "",  0.40, 150));
        updateList();
    }

    public LiveData<List<TaxForm>> getTaxForms() {
        return taxFormList;
    }

    public LiveData<TaxOutcome> getTaxOutcome() {
        return taxOutcome;
    }
}

