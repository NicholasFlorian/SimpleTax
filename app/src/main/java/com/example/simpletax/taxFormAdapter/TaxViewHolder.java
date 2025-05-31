package com.example.simpletax.taxFormAdapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpletax.R;
import com.example.simpletax.domain.TaxForm;

public abstract class TaxViewHolder<T extends TaxForm> extends RecyclerView.ViewHolder {

    TextView typeText;
    TextView nameText;
    TextView amountText;
    TextView idText;

    public TaxViewHolder(@NonNull View view) {
        super(view);
        typeText = view.findViewById(R.id.typeText);
        nameText = view.findViewById(R.id.nameText);
        amountText = view.findViewById(R.id.amountText);
        idText = view.findViewById(R.id.idText);
    }

    public abstract void bind(@NonNull T taxForm);
}
