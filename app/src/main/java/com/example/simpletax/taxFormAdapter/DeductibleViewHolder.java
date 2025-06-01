package com.example.simpletax.taxFormAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.DeductibleForm;

import java.util.Locale;

public class DeductibleViewHolder extends TaxViewHolder<DeductibleForm> {

    private final LifecycleOwner lifecycleOwner;

    TextView maxText;
    TextView deductibleText;

    public DeductibleViewHolder(@NonNull View view, LifecycleOwner owner) {
        super(view);

        deductibleText = view.findViewById(R.id.deductibleText);
        maxText = view.findViewById(R.id.maxText);
        this.lifecycleOwner = owner;
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

        if(taxForm.isEnabled()) {
            itemView.setBackgroundResource(R.color.red_100);
        } else {
            itemView.setBackgroundResource(R.color.red_grey);
        }

        itemView.setOnClickListener(v -> {
            /* TODO handle click for DeductibleForm
            if (listener != null) {
                int position = getAdapterPosition();

                if (position != RecyclerView.NO_POSITION) {
                    listener.onTaxFormClick(position);
                }
            }
             */
        });
    }
}
