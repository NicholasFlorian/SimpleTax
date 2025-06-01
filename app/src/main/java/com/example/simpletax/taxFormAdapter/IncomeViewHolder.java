package com.example.simpletax.taxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.domain.IncomeForm;

import java.util.Locale;

public class IncomeViewHolder extends TaxViewHolder<IncomeForm> {

    private final LifecycleOwner lifecycleOwner;

    public IncomeViewHolder(@NonNull View view, LifecycleOwner lifecycleOwner) {
        super(view);

        this.lifecycleOwner = lifecycleOwner;
    }

    @Override
    public void bind(@NonNull IncomeForm taxForm) {
        nameText.setText(taxForm.getName());
        amountText.setText(
                String.format(Locale.CANADA, "%.2f", taxForm.getAmount()));
        idText.setText(taxForm.getId());

        itemView.setOnLongClickListener(v -> {
            /* TODO handle long click for IncomeForm
            if (listener != null) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    listener.onTaxFormClick(position);
                }
            }
            */
            return false;
        });
    }
}
