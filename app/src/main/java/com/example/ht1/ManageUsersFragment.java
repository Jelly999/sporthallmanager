package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

//TODO: Fragment
public class ManageUsersFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_users, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }



    public String getNewUsername(View view){
        EditText value = view.findViewById(R.id.eSetUserName_MUser);
        String input = value.getText().toString();
        return input;
    }
    public String getNewUserpassword(View view){
        EditText value = view.findViewById(R.id.eSetUserPassword_MUser);
        String input = value.getText().toString();
        return input;
    }


}
