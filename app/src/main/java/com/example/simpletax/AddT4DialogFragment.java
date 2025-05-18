package com.example.simpletax;

import android.app.AlertDialog;
import android.app.Dialog;
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

import com.example.simpletax.domain.T4;

public class AddT4DialogFragment extends DialogFragment {

    EditText nameEditText;
    EditText amountEditText;
    EditText idEditText;

    Button addButton;

    public interface AddT4DialogListener {
        void onDialogAddClick(T4 t4);
    }

    private AddT4DialogListener listener;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            listener = (AddT4DialogListener) context;
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
        View view = inflater.inflate(R.layout.dialog_add_t4, container, false);

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
        listener.onDialogAddClick(new T4(nameValue, idValue.toUpperCase(), amountDouble));
        dismiss();
    }

    private void reportError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }
}
