package com.example.simpletax;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.domain.T4;
import com.example.simpletax.domain.T5;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;

public class TaxFormAdapter extends RecyclerView.Adapter<TaxViewHolder> {

    ArrayList<TaxForm> taxForms;

    public TaxFormAdapter(ArrayList<TaxForm> taxForms) {
        this.taxForms = taxForms;
    }

    @NonNull
    @Override
    public TaxViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tax_form, parent, false);

        return new TaxViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaxViewHolder holder, int position) {
        TaxForm taxForm = taxForms.get(position);

        holder.nameText.setText(taxForm.getName());
        holder.amountText.setText(String.valueOf(taxForm.getAmount()));
        holder.idText.setText(taxForm.getId());

        if(taxForm.getClass() == T4.class) {
            holder.deductibleText.setText("NA");
        }
        else if (taxForm.getClass() == T5.class) {
            holder.deductibleText.setText(
                    Double.toString(((T5)taxForm).getDeductiblePercent())
            );
        }
    }

    @Override
    public int getItemCount() {
        return taxForms.size();
    }
}
