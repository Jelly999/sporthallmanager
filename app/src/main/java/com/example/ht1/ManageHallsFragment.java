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

public class ManageHallsFragment extends Fragment {
    private Button getReservations;
    private Button deleteHall;
    private Button enableHall;
    private Button disableHall;
    private Button saveButton;
    private EditText setNewHallname;
    private EditText setNewHallLocation;
    private EditText setNewHallType;

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
    }

    public void saveNewHall() {
        String Hallname = setNewHallname.getText().toString();
        String HallLocation = setNewHallLocation.getText().toString();
        String HallType = setNewHallType.getText().toString();
        if (Hallname.length() > 0 && HallLocation.length() > 0 && HallType.length() > 0) {
            //TODO input to database
        } else {
            toast("Please fill out all fields.");
        }
    }
    public void viewReservations(){
        //TODO get reservations from selected hall and view in text view
    }

    public void deleteHall() {
        //TODO database change
    }

    public void enableHall() {
        //TODO database change
    }

    public void disableHall() {
        //TODO database change}
    }

    private void toast(String input) {
        Context context = getActivity();
        CharSequence text = input;
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

}
