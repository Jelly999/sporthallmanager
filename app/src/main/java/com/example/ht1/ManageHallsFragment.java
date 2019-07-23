package com.example.ht1;

import android.content.Context;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class ManageHallsFragment extends Fragment {
    private Button getReservations;
    private Button deleteHall;
    private Button enableHall;
    private Button disableHall;
    private Button saveButton;
    private EditText setNewHallname;
    private EditText setNewHallLocation;
    private EditText setNewHallType;
    private TextView viewReservations;
    Spinner HallSpinner;
    ArrayList spinnerList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.manage_hall, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        getReservations = view.findViewById(R.id.bGetReservations_MHalls);
        deleteHall = view.findViewById(R.id.bDeleteHall_MHalls);
        enableHall = view.findViewById(R.id.bEnableHall_MHalls);
        disableHall = view.findViewById(R.id.bDisableHall_MHalls);
        setNewHallname = view.findViewById(R.id.eNewHallname_MHalls);
        setNewHallLocation = view.findViewById(R.id.eNewHallLocation_MHalls);
        setNewHallType = view.findViewById(R.id.eNewHallType_MHalls);
        saveButton = view.findViewById(R.id.bSaveNewHall_MHall);
        getReservations.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewReservations();
            }
        });
        deleteHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteHall();
            }
        });
        enableHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableHall();
            }
        });
        disableHall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                disableHall();
            }
        });
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNewHall();
            }
        });
        HallSpinner = view.findViewById(R.id.Hallspinner_MHall);
        viewReservations = view.findViewById(R.id.tViewReservations_MHall);
        viewReservations.setMovementMethod(new ScrollingMovementMethod());
        updateHallSpinner();
    }


    public void saveNewHall() {
        String Hallname = setNewHallname.getText().toString();
        String HallLocation = setNewHallLocation.getText().toString();
        String HallType = setNewHallType.getText().toString();
        if (Hallname.length() > 0 && HallLocation.length() > 0 && HallType.length() > 0) {
            String[] HallData = {"'" + Hallname + "'", "'" + SqlManager.getUniUUid(HallLocation).toString() + "'", "'" + HallType + "'", "0"};
            SqlManager.SQLsporthall.insertRow(HallData);
        } else {
            toast("Please fill out all fields.");
        }
        updateHallSpinner();
    }


    private void updateHallSpinner() {
        spinnerList = (ArrayList) SqlManager.getSporthallsFromDatabase();

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getView().getContext(),R.layout.support_simple_spinner_dropdown_item,spinnerList);
        HallSpinner.setAdapter(adapter);
    }


    public void viewReservations(){
        SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd kk:mm");
        List<Sporthall> sporthalls = SqlManager.getSporthallsFromDatabase();
        for (Reservation reservation : SqlManager.getReservationsFromDatabase(sporthalls.get(HallSpinner.getSelectedItemPosition()))) {
            reservation.getAttenderList(reservation);
            viewReservations.append(reservation.getSporthall() + ", " + format.format(reservation.getStartDate().getTime()) + ", " + reservation.getSport() + ", " + reservation.getAttenderAmount() + "\n\n");
        }
    }


    public void deleteHall() {
        int pos = HallSpinner.getSelectedItemPosition();
        List<Integer> hall_uuid = SqlManager.getHallUUIDFromDatabase();
        if (hall_uuid.size() > 1) {
            SqlManager.SQLsporthall.removeRow(Integer.toString(hall_uuid.get(pos)));
            updateHallSpinner();
        }
    }


    public void enableHall() {
        int pos = HallSpinner.getSelectedItemPosition();
        List<Integer> hall_uuid = SqlManager.getHallUUIDFromDatabase();
        if (hall_uuid.size() > 0) {
            SqlManager.SQLsporthall.updateRow(Integer.toString(hall_uuid.get(pos)), "not_available","0");
            updateHallSpinner();
        }
    }


    public void disableHall() {
        int pos = HallSpinner.getSelectedItemPosition();
        List<Integer> hall_uuid = SqlManager.getHallUUIDFromDatabase();
        if (hall_uuid.size() > 0) {
            SqlManager.SQLsporthall.updateRow(Integer.toString(hall_uuid.get(pos)), "not_available", "1");
            updateHallSpinner();
        }
    }


    private void toast(String input) {
        Context context = getActivity();
        CharSequence text = input;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
