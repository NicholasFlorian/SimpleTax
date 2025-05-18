package com.example.simpletax.TaxFormAdapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.T4;
import com.example.simpletax.domain.T5;
import com.example.simpletax.domain.TaxForm;

import java.util.ArrayList;

public class TaxFormAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int T4_VIEW = 0;
    private static final int T5_VIEW = 1;

    ArrayList<TaxForm> taxForms;

    public TaxFormAdapter(ArrayList<TaxForm> taxForms) {
        this.taxForms = taxForms;
    }

    @Override
    public int getItemViewType(int position) {
        TaxForm taxForm = taxForms.get(position);
        if (taxForm instanceof T4) {
            return T4_VIEW;
        } else if (taxForm instanceof T5) {
            return T5_VIEW;
        }
        return -1;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        switch(viewType) {
            case T4_VIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.t4_form, parent, false);
                return new T4ViewHolder(view);
            case T5_VIEW:
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.t5_form, parent, false);
                return new T5ViewHolder(view);
            default:
                throw new IllegalArgumentException("Invalid view type");
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        TaxForm taxForm = taxForms.get(position);
        if (holder instanceof T4ViewHolder) {
            ((T4ViewHolder) holder).bind((T4) taxForm);
        } else if (holder instanceof T5ViewHolder) {
            ((T5ViewHolder) holder).bind((T5) taxForm);
        } else {
            throw new IllegalArgumentException("Invalid view holder type");
        }
    }

    @Override
    public int getItemCount() {
        return taxForms.size();
    }
}
