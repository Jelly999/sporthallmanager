package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ManageUniFragment extends Fragment {

    Spinner UniversitiesSpinner;
    ArrayList spinnerList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_uni, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        UniversitiesSpinner = (Spinner) getView().findViewById(R.id.sporthallSpinner_createevent);
        updateUniversitiesSpinner();

    }

    private void updateUniversitiesSpinner() {
        spinnerList = SqlManager.getUniNameFromDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        UniversitiesSpinner.setAdapter(adapter);
    }
}
