package com.example.simpletax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.TaxFormAdapter.TaxFormAdapter;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddIncomeDialogFragment.AddIncomeDialogListener,
        TaxFormAdapter.TaxFormAdapterListener
{

    RecyclerView taxFormRecyclerView;
    TaxFormAdapter taxFormAdapter;

    Button addT4Button;

    TextView grossIncomeText;
    TextView taxableIncomeText;
    TextView netIncomeText;

    // replace by your API :)
    ArrayList<TaxForm> taxForms;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize the RecyclerView and Adapter
        taxForms = new ArrayList<>();
        taxForms.add(new DeductibleForm("Young Chef Initiative", "3RL", 50, 0.2, "200"));
        taxForms.add(new DeductibleForm("WSET", "", 50, 0.40, "150"));
        taxForms.add(new IncomeForm("Marc Anthony Group", "MA1", 40));
        taxForms.add(new IncomeForm("Laughing Stock LTD", "L23", 140));
        taxForms.add(new IncomeForm("Earls", "3RL", 300));
        taxForms.add(new IncomeForm("Upper Bench", "UBN", 75));

        taxFormRecyclerView = findViewById(R.id.taxFormRecyclerView);
        taxFormRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taxFormRecyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        taxFormAdapter = new TaxFormAdapter(taxForms, this);
        taxFormRecyclerView.setAdapter(taxFormAdapter);

        addT4Button = findViewById(R.id.addT4Button);
        addT4Button.setOnClickListener(v -> showAddT4Dialog());

        grossIncomeText = findViewById(R.id.grossIncomeText);
        taxableIncomeText = findViewById(R.id.taxableIncomeText);
        netIncomeText = findViewById(R.id.netIncomeText);
    }

    public void showAddT4Dialog() {
        AddIncomeDialogFragment dialog = new AddIncomeDialogFragment();
        dialog.show(getSupportFragmentManager(), "AddT4Dialog");
    }

    @Override
    public void onDialogAddClick(IncomeForm incomeForm) {
        taxForms.add(incomeForm);
        taxFormAdapter.notifyItemInserted(taxForms.size() - 1);
    }

    @Override
    public void onTaxFormClick(int position) {
        TaxForm taxForm = taxForms.get(position);
        if (taxForm instanceof IncomeForm) {
            taxForms.remove(position);
            taxFormAdapter.notifyItemRemoved(position);
        }
    }
}
