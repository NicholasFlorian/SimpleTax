package com.example.simpletax;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TaxViewHolder extends RecyclerView.ViewHolder {

    TextView typeText;
    TextView nameText;
    TextView amountText;
    TextView deductibleText;
    TextView idText;


    public TaxViewHolder(@NonNull View view) {
        super(view);

        typeText = view.findViewById(R.id.typeText);
        nameText = view.findViewById(R.id.name);
        amountText = view.findViewById(R.id.amountText);
        deductibleText = view.findViewById(R.id.deductibleText);
        idText = view.findViewById(R.id.idText);
    }
}
