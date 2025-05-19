package com.example.simpletax.TaxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.domain.T4;

public class T4ViewHolder extends TaxViewHolder<T4> {

    TaxFormAdapter.TaxFormAdapterListener listener;

    public T4ViewHolder(@NonNull View view, TaxFormAdapter.TaxFormAdapterListener listener) {
        super(view);

        this.listener = listener;
    }

    @Override
    public void bind(@NonNull T4 taxForm) {
        typeText.setText("T4");
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
