package com.example.simpletax.TaxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.IncomeForm;

import java.util.Locale;

public class IncomeViewHolder extends TaxViewHolder<IncomeForm> {

    TaxFormAdapter.TaxFormAdapterListener listener;

    public IncomeViewHolder(@NonNull View view, TaxFormAdapter.TaxFormAdapterListener listener) {
        super(view);

        this.listener = listener;
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
