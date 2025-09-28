package com.example.simpletax.taxApi;

import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class MockSimpleTaxApi implements SimpleTaxApi {

    HashMap<String, ArrayList<IncomeForm>> incomeFormMap;
    ArrayList<DeductibleForm> deductibleFormList;
    ArrayList<TaxForm> taxFormList;

    private double grossIncome;
    private double deductions;
    private double taxableIncome;
    private double netIncome;

    public interface SimpleTaxApiListener {
        void onTaxFormsUpdated();
    }

    SimpleTaxApiListener listener;

    public MockSimpleTaxApi(SimpleTaxApiListener listener) {
        incomeFormMap = new HashMap<>();
        deductibleFormList = new ArrayList<>();
        taxFormList = new ArrayList<>();

        grossIncome = 0;
        deductions = 0;
        taxableIncome = 0;
        netIncome = 0;

        this.listener = listener;
    }

    /* State Management Functions */
    private void updateList() {
        taxFormList.clear();
        taxFormList.addAll(deductibleFormList);
        taxFormList.addAll(getIncomeForms());
        calculate();
        listener.onTaxFormsUpdated();
    }

    private void calculate() {
        grossIncome = 0;
        for(IncomeForm incomeForm : getIncomeForms()) {
            grossIncome += incomeForm.getAmount();
        }

        calculateDeductions();
        taxableIncome = grossIncome - deductions;
        netIncome = grossIncome - taxableIncome * EFFECTIVE_TAX_RATE;
    }

    private void calculateDeductions() {
        deductions = 0;
        for(DeductibleForm deductibleForm : deductibleFormList) {

            if(!deductibleForm.isEnabled()){
                continue;
            }

            double totalTaxable = 0;
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
    }

    /* Public Facing Functions in the API */
    public void loadInitialIncomeForms() {
        this.addIncomeForm(new IncomeForm("Marc Anthony Group", "MA1", 40));
        this.addIncomeForm(new IncomeForm("Laughing Stock LTD", "L23", 140));
        this.addIncomeForm(new IncomeForm("Earls", "3RL", 300));
        this.addIncomeForm(new IncomeForm("Upper Bench", "UBN", 75));
    }

    public void fetchT5() {
        deductibleFormList.clear();
        deductibleFormList.add(new DeductibleForm("Young Chef Initiative", "3RL",  0.2, 200));
        deductibleFormList.add(new DeductibleForm("WSET", "",  0.40, 150));
        updateList();
    }

    public List<IncomeForm> getIncomeForms() {
        return incomeFormMap.values().stream().flatMap(ArrayList::stream).sorted(Comparator.comparing(TaxForm::getCreatedAt)).toList();
    }

    public List<TaxForm> getTaxForms() {
        return taxFormList;
    }

    public int addIncomeForm(IncomeForm incomeForm) {
        if(incomeFormMap.containsKey(incomeForm.getId())) {
            incomeFormMap.get(incomeForm.getId()).add(incomeForm);
        } else {
            ArrayList<IncomeForm> incomeForms = new ArrayList<>();
            incomeForms.add(incomeForm);
            incomeFormMap.put(incomeForm.getId(), incomeForms);
        }
        updateList();
        return taxFormList.size() - 1;
    }

    public void removeIncomeForm(int position) {
        TaxForm taxForm = taxFormList.get(position);
        if (taxForm instanceof IncomeForm) {
            String id = taxForm.getId();
            ArrayList<IncomeForm> incomeForms = incomeFormMap.get(id);
            if (incomeForms != null) {
                incomeForms.remove(taxForm);
                if (incomeForms.isEmpty()) {
                    incomeFormMap.remove(id);
                }
            }
        }
        updateList();
    }

    public void toggleDeductibleForm(int position) {
        DeductibleForm deductibleForm = (DeductibleForm) taxFormList.get(position);
        deductibleForm.flipEnabled();
        updateList();
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
