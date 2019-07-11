package com.example.ht1;

import android.view.View;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

// TODO Fragment
public class ManageHallsFragment extends Fragment {


    public String getNewHallname(View view){
        EditText value = view.findViewById(R.id.editNewHallname);
        String input = value.getText().toString();
        return input;
    }
    public String getNewHallLocation(View view){
        EditText value = view.findViewById(R.id.editNewHallLocation);
        String input = value.getText().toString();
        return input;
    }
    public String getNewHallType(View view){
        EditText value = view.findViewById(R.id.editNewUniName);
        String input = value.getText().toString();
        return input;
    }
}
