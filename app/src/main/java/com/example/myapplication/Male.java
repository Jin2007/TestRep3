package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Male extends Fragment {
    List<Human> people = new ArrayList<>();

    public Male() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_male, container, false);
//        for (Human e: people){
//            if (e.getSex().equals("Male")){
//
//            }
//        }
        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        AdapterRecycler adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    public void setInitialData() {
        people.add(new Human("Roman", "Starshiy", "Male"));
        people.add(new Human ("Roman", "Mladshiy", "Male"));
    }
}
