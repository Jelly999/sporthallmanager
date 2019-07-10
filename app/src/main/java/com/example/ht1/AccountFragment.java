package com.example.ht1;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

public class AccountFragment extends Fragment {



    public void saveInfo(View v){ //UI starts this
        String phone = getPhoneInput(getView());
        String email = getEmailInput(getView());
        if (phone != null && email == null){
            //input both do db
        }else if (phone != null){
            //input phone number to db
        }else if (email != null){
            //input email to db
        }else; //debugging
            System.out.println("failed");
    }


    public String getPhoneInput(View view){
        EditText value = view.findViewById(R.id.editPhone);
        String input = value.getText().toString();
        return input;
    }

    public String getEmailInput(View view){
        EditText value = view.findViewById(R.id.editEmail);
        String input = value.getText().toString();
        return input;
    }

}


