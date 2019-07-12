package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

// TODO Fragment
public class ManageHallsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_hall, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
    }


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
