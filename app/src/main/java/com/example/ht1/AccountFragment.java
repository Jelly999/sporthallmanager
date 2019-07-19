package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class AccountFragment extends Fragment {
    private TextView uniAccess;
    private Button saveButton;
    private EditText phoneInput;
    private EditText emailInput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.account, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        uniAccess = view.findViewById(R.id.tUniAccess_account);
        saveButton = view.findViewById(R.id.bSave_account);
        phoneInput = view.findViewById(R.id.editPhone);
        emailInput = view.findViewById(R.id.editEmail);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                buttonClicked();
            }
        });
        String hasAccess = "LUTUKKA"; //TODO get has access from db
        uniAccess.setText(hasAccess);
    }
    private void toast(){
        Context context = getActivity();
        CharSequence text = "Input data please.";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context,text, duration);
        toast.show();
    }
    private void buttonClicked() {
        String phone = "'" + phoneInput.getText().toString() + "'";
        String email = "'" + emailInput.getText().toString() + "'";
        if (phone.length()>0){
            //System.out.println(phone);
            SqlManager.SQLuser.updateRow(Integer.toString(User.getCurrentUser().getUUID()), SqlTablenames.userTable.COLUMN_NAME_PHONE_NUMBER, phone);
        }
        if (email.length()>0 ){
            //System.out.println(email);
            SqlManager.SQLuser.updateRow(Integer.toString(User.getCurrentUser().getUUID()), SqlTablenames.userTable.COLUMN_NAME_EMAIL, email);
        }
        if (phone.length() == 0 && email.length() == 0){toast();}
    }

}


