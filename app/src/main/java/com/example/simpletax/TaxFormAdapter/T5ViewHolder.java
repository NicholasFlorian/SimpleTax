package com.example.simpletax.TaxFormAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.simpletax.R;
import com.example.simpletax.domain.T5;

public class T5ViewHolder extends TaxViewHolder<T5> {

    TextView maxText;
    TextView deductibleText;

    public T5ViewHolder(@NonNull View view) {
        super(view);

        deductibleText = view.findViewById(R.id.deductibleText);
        maxText = view.findViewById(R.id.maxText);
    }

    @Override
    public void bind(@NonNull T5 taxForm) {
        typeText.setText("T5");
        nameText.setText(taxForm.getName());
        amountText.setText(String.valueOf(taxForm.getAmount()));
        idText.setText(taxForm.getId());
        maxText.setText(String.valueOf(taxForm.getMax()));
        deductibleText.setText(String.valueOf(taxForm.getDeductiblePercent()));
    }
}
