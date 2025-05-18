package com.example.simpletax.TaxFormAdapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.example.simpletax.domain.T4;

public class T4ViewHolder extends TaxViewHolder<T4> {

    public T4ViewHolder(@NonNull View view) {
        super(view);
    }

    @Override
    public void bind(@NonNull T4 taxForm) {
        typeText.setText("T4");
        nameText.setText(taxForm.getName());
        amountText.setText(String.valueOf(taxForm.getAmount()));
        idText.setText(taxForm.getId());
    }
}
