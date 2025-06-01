package com.example.simpletax.ui.taxCalculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.taxFormAdapter.TaxFormAdapter;
import com.example.simpletax.ui.addIncome.AddIncomeDialogFragment;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class TaxCalculatorActivity extends AppCompatActivity {

    TaxCalculatorViewModel taxCalculatorViewModel;

    private RecyclerView taxFormRecyclerView;
    private TaxFormAdapter taxFormAdapter;

    private Button addT4Button;

    private TextView grossIncomeText;
    private TextView deductionsText;
    private TextView taxableIncomeText;
    private TextView netIncomeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taxCalculatorViewModel = new ViewModelProvider(this).get(TaxCalculatorViewModel.class);

        // Initialize the RecyclerView and Adapter
        taxFormRecyclerView = findViewById(R.id.taxFormRecyclerView);
        taxFormRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taxFormRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        taxFormAdapter = new TaxFormAdapter(this);
        taxFormRecyclerView.setAdapter(taxFormAdapter);

        addT4Button = findViewById(R.id.addT4Button);
        addT4Button.setOnClickListener(v -> showAddT4Dialog());

        grossIncomeText = findViewById(R.id.grossIncomeText);
        taxableIncomeText = findViewById(R.id.taxableIncomeText);
        netIncomeText = findViewById(R.id.netIncomeText);
        deductionsText = findViewById(R.id.deductionsText);

        /*
         * These functions within simpleTaxAPI call onUpdated() so they must be called
         * after the text views are initialized
         */
        /* TODO call these functions in the ViewModel
        simpleTaxApi.fetchT5();
        simpleTaxApi.loadInitialIncomeForms();
        */

        taxCalculatorViewModel.getTaxForms().observe(this, taxForms ->
            taxFormAdapter.submitList(taxForms)
        );

        taxCalculatorViewModel.getGrossIncome().observe(this, grossIncome -> {
            grossIncomeText.setText(String.format(Locale.CANADA, "%.2f", grossIncome));
        });

        taxCalculatorViewModel.getDeductions().observe(this, deductions -> {
            deductionsText.setText(String.format(Locale.CANADA, "%.2f", deductions));
        });

        taxCalculatorViewModel.getTaxableIncome().observe(this, taxableIncome -> {
            taxableIncomeText.setText(String.format(Locale.CANADA, "%.2f", taxableIncome));
        });

        taxCalculatorViewModel.getNetIncome().observe(this, netIncome -> {
            netIncomeText.setText(String.format(Locale.CANADA, "%.2f", netIncome));
        });
    }

    public void showAddT4Dialog() {
        AddIncomeDialogFragment dialog = new AddIncomeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddT4Dialog");
    }
}
