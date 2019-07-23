package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class MainMenuFragment  extends Fragment {

    private TextView userProfileName;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.main_menu, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        userProfileName = view.findViewById(R.id.tUserProfileName_menu);
        setUserProfileName();
    }


    private void setUserProfileName() {
        userProfileName.setText(User.getCurrentUser().getUserName());
    }

}