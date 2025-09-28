package com.example.simpletax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.TaxFormAdapter.TaxFormAdapter;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;
import com.example.simpletax.taxApi.MockSimpleTaxApi;

import java.util.Locale;

public class MainActivity extends AppCompatActivity implements
        AddIncomeDialogFragment.AddIncomeDialogListener,
        TaxFormAdapter.TaxFormAdapterListener,
        MockSimpleTaxApi.SimpleTaxApiListener
{

    RecyclerView taxFormRecyclerView;
    TaxFormAdapter taxFormAdapter;

    Button addT4Button;

    TextView grossIncomeText;
    TextView deductionsText;
    TextView taxableIncomeText;
    TextView netIncomeText;

    // replace by your API :)
    private MockSimpleTaxApi simpleTaxApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView and Adapter
        taxFormRecyclerView = findViewById(R.id.taxFormRecyclerView);
        taxFormRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taxFormRecyclerView.addItemDecoration(
                new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        simpleTaxApi = new MockSimpleTaxApi(this);
        taxFormAdapter = new TaxFormAdapter(simpleTaxApi.getTaxForms(), this);
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
        simpleTaxApi.fetchT5();
        simpleTaxApi.loadInitialIncomeForms();
    }

    public void showAddT4Dialog() {
        AddIncomeDialogFragment dialog = new AddIncomeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddT4Dialog");
    }

    @Override
    public void onDialogAddClick(IncomeForm incomeForm) {
        int insertedAt = simpleTaxApi.addIncomeForm(incomeForm);
        taxFormAdapter.notifyItemInserted(insertedAt);
    }

    @Override
    public void onTaxFormClick(int position) {
        TaxForm taxForm = simpleTaxApi.getTaxForms().get(position);
        if (taxForm instanceof IncomeForm) {
            simpleTaxApi.removeIncomeForm(position);
            taxFormAdapter.notifyItemRemoved(position);
        }
        else if(taxForm instanceof DeductibleForm) {
            simpleTaxApi.toggleDeductibleForm(position);
            taxFormAdapter.notifyItemChanged(position);
        }
        else {
            Toast.makeText(this, "This not a valid form", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaxFormsUpdated() {
        grossIncomeText.setText(
                String.format(Locale.CANADA, "%.2f", simpleTaxApi.getGrossIncome()));
        deductionsText.setText(
                String.format(Locale.CANADA, "%.2f", simpleTaxApi.getDeductions()));
        taxableIncomeText.setText(
                String.format(Locale.CANADA,"%.2f", simpleTaxApi.getTaxableIncome()));
        netIncomeText.setText(
                String.format(Locale.CANADA,"%.2f", simpleTaxApi.getNetIncome()));
    }
}
