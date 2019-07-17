package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class CreateEventFragment extends Fragment {

    Spinner sporthallSpinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.createevent, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        sporthallSpinner = (Spinner) getView().findViewById(R.id.sporthallSpinner_createevent);
        updateSporthallSpinner();
    }

    private void updateSporthallSpinner() {
        List<String> spinnerList = new ArrayList<>();

        for (Sporthall sporthall : ReservationManager.sporthallsList) {
            if (!sporthall.getDisabled()) {
                spinnerList.add(sporthall.getName());
            }
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        sporthallSpinner.setAdapter(adapter);
    }
}
