package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class authFragment extends Fragment {

    private EditText inputField;
    private TextView randomintText;
    private Button confirmButton;

    private String authCode;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.authentication, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        inputField = view.findViewById(R.id.edit_inputint_auth);
        randomintText = view.findViewById(R.id.text_randint_auth);
        confirmButton = view.findViewById(R.id.bConfirm_auth);

        setRandomIntegers();

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
    }

    private void buttonClicked() {
        if (checkIfAuthMatch(inputField.getText().toString())) {
            System.out.println("Auth accepted");

            ((MainActivity)getActivity()).launchMainMenu();
        } else {
            System.out.println("Auth denied");
        }
    }

    private void setRandomIntegers() {
        Random rnd = new Random();
        String authText = "";

        for (int i = 0; i < 6; i++) {
            authText += Integer.toString(rnd.nextInt(9));
        }
        authCode = authText;

        randomintText.setText(authCode);
    }

    private boolean checkIfAuthMatch(String input) {
        if (input.length() == 0) {
            return true; //TODO REMOVE ONCE ALL IS GOOD
        }
        return input.equals(authCode);
    }
}
