package com.example.myapplication;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Male extends Fragment {

    List<Human> people = new ArrayList<>();
    final String TAG = "States";
    AdapterRecycler adapterRecycler;
    Handler handler;


    public Male() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_male, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Male is resumed");
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = App.getInstance().getDatabase();
                HumanDao humanDao = db.humanDao();
                people.clear();
                List<Human> humanMale = humanDao.getAll();
                for (Human human: humanMale){
                    if (human.getSex().equals("Male")){
                        people.add(human);
                    }
                }
                handler.post(updateProgress);
            }
        });
        thread.start();
    }

    Runnable updateProgress = new Runnable() {
        public void run() {
            adapterRecycler.notifyDataSetChanged();
        }
    };

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "Male is stopped");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "Male is paused");
    }
}
