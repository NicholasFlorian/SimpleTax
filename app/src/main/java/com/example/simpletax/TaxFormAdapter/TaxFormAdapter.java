package com.example.simpletax.TaxFormAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;

import java.util.List;

public class TaxFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int INCOME_VIEW = 0;
    private static final int DEDUCTIBLE_VIEW = 1;

    List<TaxForm> taxForms;

    public interface TaxFormAdapterListener {
        void onTaxFormClick(int position);
    }

    private final TaxFormAdapterListener listener;

    public TaxFormAdapter(List<TaxForm> taxForms, TaxFormAdapterListener listener) {
        this.taxForms = taxForms;
        this.listener = listener;
    }

    @Override
    public int getItemViewType(int position) {
        TaxForm taxForm = taxForms.get(position);
        if (taxForm instanceof IncomeForm) {
            return INCOME_VIEW;
        } else if (taxForm instanceof DeductibleForm) {
            return DEDUCTIBLE_VIEW;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType) {
            case INCOME_VIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_income_form, parent, false);
                return new IncomeViewHolder(view, listener);
            case DEDUCTIBLE_VIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_deductible_form, parent, false);
                return new DeductibleViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaxForm taxForm = taxForms.get(position);
        if (holder instanceof IncomeViewHolder) {
            ((IncomeViewHolder) holder).bind((IncomeForm) taxForm);
        } else if (holder instanceof DeductibleViewHolder) {
            ((DeductibleViewHolder) holder).bind((DeductibleForm) taxForm);
        } else {
            throw new IllegalArgumentException("Invalid view holder type");
        }
    }

    @Override
    public int getItemCount() {
        return taxForms.size();
    }
}
