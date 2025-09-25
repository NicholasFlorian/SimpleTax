package com.example.simpletax.taxFormAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.AsyncListDiffer;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.IncomeForm;
import com.example.simpletax.domain.DeductibleForm;
import com.example.simpletax.domain.TaxForm;

import java.util.List;
import java.util.Objects;

public class TaxFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int INCOME_VIEW = 0;
    private static final int DEDUCTIBLE_VIEW = 1;

    private final LifecycleOwner lifecycleOwner;
    private final AsyncListDiffer<TaxForm> taxFormDiffer;

    private static final DiffUtil.ItemCallback<TaxForm> DIFF_CALLBACK =
        new DiffUtil.ItemCallback<TaxForm>() {
            @Override
            public boolean areItemsTheSame(@NonNull TaxForm oldItem, @NonNull TaxForm newItem) {
                return oldItem.getUuid().equals(newItem.getUuid());
            }

            @Override
            public boolean areContentsTheSame(@NonNull TaxForm oldItem, @NonNull TaxForm newItem) {
                return oldItem.equals(newItem);
            }
        };

    public TaxFormAdapter(
        LifecycleOwner lifeCycleOwner
    ) {
        this.lifecycleOwner = lifeCycleOwner;
        this.taxFormDiffer = new AsyncListDiffer<>(this, DIFF_CALLBACK);
    }

    public void submitList(List<TaxForm> newTaxFormList) {
        taxFormDiffer.submitList(newTaxFormList);
    }

    @Override
    public int getItemViewType(int position) {
        TaxForm taxForm = Objects.requireNonNull(taxFormDiffer.getCurrentList().get(position));
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
                return new IncomeViewHolder(view, this);
            case DEDUCTIBLE_VIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.card_deductible_form, parent, false);
                return new DeductibleViewHolder(view, this);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaxForm taxForm = taxFormDiffer.getCurrentList().get(position);
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
        return taxFormDiffer.getCurrentList().size();
    }

    @Override
    public long getItemId(int position) {
        return taxFormDiffer.getCurrentList().get(position).hashCode();
    }
}
