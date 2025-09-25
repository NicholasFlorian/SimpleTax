package com.example.simpletax.taxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.domain.IncomeForm;

import java.util.Locale;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class IncomeViewHolder extends TaxViewHolder<IncomeForm> {

    public IncomeViewHolder(@NonNull View view) {
        super(view);
    }

    @Override
    public void bind(@NonNull IncomeForm taxForm) {
        nameText.setText(taxForm.getName());
        amountText.setText(
                String.format(Locale.CANADA, "%.2f", taxForm.getAmount()));
        idText.setText(taxForm.getId());

        itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    listener.onTaxFormClick(position);
                }
            }
            return false;
        });
    }
}
