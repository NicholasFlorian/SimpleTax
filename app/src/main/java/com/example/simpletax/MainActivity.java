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
import com.example.simpletax.domain.T4;
import com.example.simpletax.domain.T5;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements
        AddT4DialogFragment.AddT4DialogListener,
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
        taxForms.add(new T4("Marc Anthony Group", "MA1", 40));
        taxForms.add(new T4("Laughing Stock LTD", "L23", 140));
        taxForms.add(new T4("Earls", "3RL", 300));
        taxForms.add(new T4("Upper Bench", "UBN", 75));
        taxForms.add(new T5("Young Chef Initiative", "3RL", 50, 0.2, "200"));
        taxForms.add(new T5("WSET", "", 50, 0.40, "150"));

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
        AddT4DialogFragment dialog = new AddT4DialogFragment();
        dialog.show(getSupportFragmentManager(), "AddT4Dialog");
    }

    @Override
    public void onDialogAddClick(T4 t4) {
        taxForms.add(t4);
        taxFormAdapter.notifyItemInserted(taxForms.size() - 1);
    }

    @Override
    public void onTaxFormClick(int position) {
        TaxForm taxForm = taxForms.get(position);
        if (taxForm instanceof T4) {
            taxForms.remove(position);
            taxFormAdapter.notifyItemRemoved(position);
        }
    }
}
