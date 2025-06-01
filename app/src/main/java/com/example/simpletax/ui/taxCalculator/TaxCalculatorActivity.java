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

        taxCalculatorViewModel.getTaxForms().observe(this, taxForms ->
            taxFormAdapter.submitList(taxForms)
        );

        taxCalculatorViewModel.getTaxOutcome().observe(this, taxOutcome -> {
            grossIncomeText.setText(
                String.format(Locale.CANADA, "%.2f", taxOutcome.getGrossIncome()));
            deductionsText.setText(
                String.format(Locale.CANADA, "%.2f", taxOutcome.getDeductions()));
            taxableIncomeText.setText(
                String.format(Locale.CANADA, "%.2f", taxOutcome.getTaxableIncome()));
            netIncomeText.setText(
                String.format(Locale.CANADA, "%.2f", taxOutcome.getNetIncome()));
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Fetch T5 data when the activity resumes
        taxCalculatorViewModel.fetchT5();
    }

    public void showAddT4Dialog() {
        AddIncomeDialogFragment dialog = new AddIncomeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddT4Dialog");
    }
}
