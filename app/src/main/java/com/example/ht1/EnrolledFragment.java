package com.example.ht1;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.ArrayList;

public class EnrolledFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.login, container, false);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        ArrayList<item> exampleList = new ArrayList<>();
        exampleList.add(new item(R.drawable.ic_android, "Line 1", "Line 2"));
        exampleList.add(new item(R.drawable.ic_android, "Line 3", "Line 4"));
        exampleList.add(new item(R.drawable.ic_android, "Line 5", "Line 6"));
        exampleList.add(new item(R.drawable.ic_android, "Line 7", "Line 8"));
        exampleList.add(new item(R.drawable.ic_android, "Line 9", "Line 10"));
        exampleList.add(new item(R.drawable.ic_android, "Line 11", "Line 12"));
        exampleList.add(new item(R.drawable.ic_android, "Line 13", "Line 14"));
        exampleList.add(new item(R.drawable.ic_android, "Line 15", "Line 16"));
        exampleList.add(new item(R.drawable.ic_android, "Line 17", "Line 18"));
        exampleList.add(new item(R.drawable.ic_android, "Line 19", "Line 20"));
        exampleList.add(new item(R.drawable.ic_android, "Line 21", "Line 22"));
        exampleList.add(new item(R.drawable.ic_android, "Line 23", "Line 24"));
        exampleList.add(new item(R.drawable.ic_android, "Line 25", "Line 26"));
        exampleList.add(new item(R.drawable.ic_android, "Line 27", "Line 28"));
        exampleList.add(new item(R.drawable.ic_android, "Line 29", "Line 30"));

        mRecyclerView = view.findViewById();
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ExampleAdapter(exampleList);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
