package com.example.simpletax.taxApi;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class RestSimpleTaxApi implements SimpleTaxApi {

    SimpleTaxApiListener listener;

    DeductibleService deductibleService;

    HashMap<String, ArrayList<IncomeForm>> incomeFormMap;
    ArrayList<DeductibleForm> deductibleFormList;
    ArrayList<TaxForm> taxFormList;

    double grossIncome;
    double deductions;
    double taxableIncome;
    double netIncome;

    public RestSimpleTaxApi(SimpleTaxApiListener listener) {
        this.listener = listener;

        Retrofit retrofit = Client.getRetrofitClient();
        deductibleService = retrofit.create(DeductibleService.class);

        incomeFormMap = new HashMap<>();
        deductibleFormList = new ArrayList<>();
        taxFormList = new ArrayList<>();

        grossIncome = 0;
        deductions = 0;
        taxableIncome = 0;
        netIncome = 0;
    }

    private void updateList(int position) {
        taxFormList.clear();
        taxFormList.addAll(deductibleFormList);
        taxFormList.addAll(getIncomeForms());
        calculate();
        listener.onTaxFormsUpdated(position);
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

    /* -------------------------------------------------------------------------------------------*/

    @Override
    public void loadInitialIncomeForms() {
        this.addIncomeForm(new IncomeForm("Marc Anthony Group", "BCTD", 500));
        this.addIncomeForm(new IncomeForm("Laughing Stock LTD", "WSET", 350));
        this.addIncomeForm(new IncomeForm("Earls", "3RL", 500));
        this.addIncomeForm(new IncomeForm("Upper Bench", "BCTD", 100));
    }

    @Override
    public void fetchT5() {
        Call<List<DeductibleForm>> deductibleCall = deductibleService.getDeductibles();
        deductibleCall.enqueue(new Callback<List<DeductibleForm>>() {
            @Override
            public void onResponse(
                @NonNull Call<List<DeductibleForm>> call,
                @NonNull Response<List<DeductibleForm>> response
            ) {
                if(!response.isSuccessful() || response.body() == null){
                    Log.e("REST SimpleTaxApi onResponse Error", response.message());
                    return;
                }
                Log.d("REST SimpleTaxApi onResponse Error", response.message());
                for(DeductibleForm deductibleForm: response.body()) {
                    deductibleForm.setEnabled(true);
                    addDeductibleForm(deductibleForm);
                }
            }

            @Override
            public void onFailure(
                @NonNull Call<List<DeductibleForm>> call,
                @NonNull Throwable t
            ) {
                Log.e("REST SimpleTaxApi onFailure", t.getMessage());
            }
        });
    }

    @Override
    public List<IncomeForm> getIncomeForms() {
        return incomeFormMap
                .values()
                .stream()
                .flatMap(ArrayList::stream)
                .sorted(Comparator.comparing(TaxForm::getCreatedAt))
                .toList();
    }

    @Override
    public List<TaxForm> getTaxForms() {
        return taxFormList;
    }

    @Override
    public void addDeductibleForm(DeductibleForm deductibleForm){
        deductibleFormList.add(deductibleForm);
        updateList(deductibleFormList.size() - 1);
    }

    @Override
    public void addIncomeForm(IncomeForm incomeForm) {
        if(incomeFormMap.containsKey(incomeForm.getId())) {
            incomeFormMap.get(incomeForm.getId()).add(incomeForm);
        } else {
            ArrayList<IncomeForm> incomeForms = new ArrayList<>();
            incomeForms.add(incomeForm);
            incomeFormMap.put(incomeForm.getId(), incomeForms);
        }

        updateList(taxFormList.size() - 1);
    }

    @Override
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
        updateList(position);
    }

    @Override
    public void toggleDeductibleForm(int position) {
        DeductibleForm deductibleForm = (DeductibleForm) taxFormList.get(position);
        deductibleForm.flipEnabled();
        updateList(position);
    }

    @Override
    public double getGrossIncome() {
        return grossIncome;
    }

    @Override
    public double getDeductions() {
        return deductions;
    }

    @Override
    public double getTaxableIncome() {
        return taxableIncome;
    }

    @Override
    public double getNetIncome() {
        return netIncome;
    }
}
