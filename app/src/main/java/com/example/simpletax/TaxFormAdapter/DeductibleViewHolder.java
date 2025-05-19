package com.example.simpletax.TaxFormAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.simpletax.R;
import com.example.simpletax.domain.DeductibleForm;

import java.util.Locale;

public class DeductibleViewHolder extends TaxViewHolder<DeductibleForm> {

    TextView maxText;
    TextView deductibleText;

    public DeductibleViewHolder(@NonNull View view) {
        super(view);

        deductibleText = view.findViewById(R.id.deductibleText);
        maxText = view.findViewById(R.id.maxText);
    }

    @Override
    public void bind(@NonNull DeductibleForm taxForm) {
        nameText.setText(taxForm.getName());
        idText.setText(taxForm.getId());
        maxText.setText(
                String.format(Locale.CANADA, "%.2f", taxForm.getMax()));
        deductibleText.setText(String.format(
                Locale.CANADA,
                "%.0f%%",
                taxForm.getDeductiblePercent() * 100)
        );

    }
}
