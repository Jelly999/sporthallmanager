package com.example.ht1;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class CreateEventFragment extends Fragment {

    private Spinner sporthallSpinner;
    private EditText sportNameEdit;
    private Button setStartCalBut;
    private TextView completeStartCalText;
    private EditText setDurationEdit;
    private TextView completeEndCalText;
    private EditText setMaxParticipantEdit;
    private EditText setReoccuranceEdit;
    private Button exportAllEventsCSVButton;
    private Switch activeSwitch;

    private Context context;

    final Calendar calendarDMY = Calendar.getInstance();

    SimpleDateFormat formatDMY;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.createevent, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        context = this.getContext();
        formatDMY = new SimpleDateFormat("yyyy.MM.dd");

        sporthallSpinner = getView().findViewById(R.id.sporthallSpinner_createevent);
        sportNameEdit = view.findViewById(R.id.eSetSportName_create);
        setStartCalBut = view.findViewById(R.id.bSetStartTime_create);
        completeStartCalText = view.findViewById(R.id.CompleteStartDate_create);
        setDurationEdit = view.findViewById(R.id.eSetDurationHours_create);
        completeEndCalText = view.findViewById(R.id.CompleteEndDate_create);
        setMaxParticipantEdit = view.findViewById(R.id.eSetMaximumPart_create);
        setReoccuranceEdit = view.findViewById(R.id.eSetReoccuring_create);
        exportAllEventsCSVButton = view.findViewById(R.id.bExportToCSV_create);
        activeSwitch = view.findViewById(R.id.sRecurring_create);


        final DatePickerDialog.OnDateSetListener date_datepicker = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                calendarDMY.set(Calendar.YEAR, i);
                calendarDMY.set(Calendar.MONTH, i1);
                calendarDMY.set(Calendar.DAY_OF_MONTH, i2);
                setStartCalendar();
            }
        };
        setStartCalBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date_datepicker, calendarDMY.get(Calendar.YEAR),
                        calendarDMY.get(Calendar.MONTH), calendarDMY.get(Calendar.DAY_OF_MONTH)).show();
            }
        });


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

    private void setStartCalendar() {
        completeStartCalText.setText(formatDMY.format(calendarDMY.getTime()));
    }
}
