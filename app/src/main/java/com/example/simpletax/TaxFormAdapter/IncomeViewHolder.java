package com.example.simpletax.TaxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.IncomeForm;

public class IncomeViewHolder extends TaxViewHolder<IncomeForm> {

    TaxFormAdapter.TaxFormAdapterListener listener;

    public IncomeViewHolder(@NonNull View view, TaxFormAdapter.TaxFormAdapterListener listener) {
        super(view);

        this.listener = listener;
    }

    @Override
    public void bind(@NonNull IncomeForm taxForm) {
        nameText.setText(taxForm.getName());
        amountText.setText(String.valueOf(taxForm.getAmount()));
        idText.setText(taxForm.getId());

        itemView.setOnClickListener(v -> {
            if (listener != null) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    listener.onTaxFormClick(position);
                }
            }
        });
    }
}
