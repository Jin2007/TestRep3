package com.example.myapplication;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Female extends Fragment {
    List<Human> people = new ArrayList<>();

    public Female() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_all, container, false);
        setInitialData();
        RecyclerView recyclerView = view.findViewById(R.id.list);
        AdapterRecycler adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    public void setInitialData() {
        people.add(new Human("Vasiliya", "Pupkina", "Female"));
        people.add(new Human ("Vitalia", "Boherova", "Female"));
    }
}
