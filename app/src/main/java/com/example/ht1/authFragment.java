package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.Random;

public class authFragment extends Fragment {
    private TextView randomint;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.authentication, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


}

    // Checks if authentication code is correct
    public boolean authenticated(View view){
        String randomintvalue = getRandomNumberString();
        TextView randomint = view.findViewById(R.id.text_randint);
        randomint.setText(randomintvalue);
        EditText value = view.findViewById(R.id.edit_inputint);
        String input = value.getText().toString();
        if (randomintvalue == input){
            return true;
        } else;
            return false;
    }

    // Generates 6 digit interger
    public static String getRandomNumberString() {
        // It will generate 6 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999);

        // this will convert any number sequence into 6 character.
        return String.format("%06d", number);
    }
}
