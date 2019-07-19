package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class ManageUniFragment extends Fragment {

    Spinner UniversitiesSpinner;
    ArrayList spinnerList;
    TextView UniName;
    TextView UniAddress;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_uni, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button AddNewUniButton = view.findViewById(R.id.bAddNewUni_MUni);
        Button DeleteUniButton = view.findViewById(R.id.bDeleteUni_MUni);
        UniversitiesSpinner = view.findViewById(R.id.Unispinner_MUni);
        UniAddress = view.findViewById(R.id.eNewUniAddress_MUni);
        UniName = view.findViewById(R.id.eSetNewUniName_MUni);
        updateUniversitiesSpinner();
        AddNewUniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CreateNewUni();
            }
        });
        DeleteUniButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteOldUni();
            }
        });
    }


    public void CreateNewUni() {
        String university_name;
        String university_address;
        if (UniName.getText().length() != 0 && UniAddress.getText().length() != 0){
            university_name = "'" + UniName.getText().toString() + "'";
            university_address = "'" + UniAddress.getText().toString() + "'";
            SqlManager.SQLuniversities.insertRow(university_name, university_address);
            updateUniversitiesSpinner();
        }

    }

    public void DeleteOldUni() {
        int pos = UniversitiesSpinner.getSelectedItemPosition();
        List<Integer> uni_uuid = SqlManager.getUniUUIDFromDatabase();
        if (uni_uuid.size() > 1) {
            SqlManager.SQLuniversities.removeRow(Integer.toString(uni_uuid.get(pos)));
            updateUniversitiesSpinner();
        }
    }

    private void updateUniversitiesSpinner() {
        spinnerList = SqlManager.getUniNameFromDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        UniversitiesSpinner.setAdapter(adapter);
    }
}
