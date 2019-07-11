package com.example.ht1;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

//TODO: Fragment
public class ManageUsersFragment extends Fragment {




    public String getNewUsername(View view){
        EditText value = view.findViewById(R.id.editNewUniName);
        String input = value.getText().toString();
        return input;
    }
    public String getNewUserpassword(View view){
        EditText value = view.findViewById(R.id.editNewUniAddr);
        String input = value.getText().toString();
        return input;
    }


}
