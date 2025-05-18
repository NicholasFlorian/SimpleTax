package com.example.simpletax;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.domain.T4;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
        taxForms.add(new T4("T4", "131", 20000));
        taxForms.add(new T4("T4", "132", 30000));
        taxForms.add(new T4("T4", "133", 40000));

        taxFormRecyclerView = findViewById(R.id.taxFormRecyclerView);
        taxFormRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        taxFormAdapter = new TaxFormAdapter(taxForms);
        taxFormRecyclerView.setAdapter(taxFormAdapter);

        addT4Button = findViewById(R.id.addT4Button);

        grossIncomeText = findViewById(R.id.grossIncomeText);
        taxableIncomeText = findViewById(R.id.taxableIncomeText);
        netIncomeText = findViewById(R.id.netIncomeText);
    }

    // Add your methods and logic here
}
