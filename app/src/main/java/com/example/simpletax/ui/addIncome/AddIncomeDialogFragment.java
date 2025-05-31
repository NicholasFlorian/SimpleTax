package com.example.simpletax.ui.addIncome;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.simpletax.R;
import com.example.simpletax.domain.IncomeForm;

public class AddIncomeDialogFragment extends DialogFragment {

    public interface AddIncomeDialogListener {
        void onDialogAddClick(IncomeForm incomeForm);
    }

    private AddIncomeDialogListener listener;

    EditText nameEditText;
    EditText amountEditText;
    EditText idEditText;
    Button addButton;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddIncomeDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context + " must implement AddT4DialogListener");
        }
    }

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View view = inflater.inflate(R.layout.dialog_add_income, container, false);

        nameEditText = view.findViewById(R.id.nameEditText);
        amountEditText = view.findViewById(R.id.amountEditText);
        idEditText = view.findViewById(R.id.idEditText);
        addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(v -> buildT4());

        return view;
    }

    private void buildT4() {
        String nameValue = nameEditText.getText().toString();
        String amountValue = amountEditText.getText().toString();
        String idValue = idEditText.getText().toString();

        if(nameValue.isEmpty()) {
            reportError("Name cannot be empty");
            return;
        }

        if(idValue.isEmpty()) {
            reportError("ID cannot be empty");
            return;
        }

        double amountDouble;

        try {
            amountDouble = Double.parseDouble(amountValue);
        } catch (NumberFormatException e) {
            reportError("Amount must be a valid number");
            return;
        }
        if(amountDouble <= 0) {
            reportError("Amount must be greater than 0");
            return;
        }
        listener.onDialogAddClick(new IncomeForm(nameValue, idValue.toUpperCase(), amountDouble));
        dismiss();
    }

    private void reportError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
