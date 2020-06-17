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

public class Female extends Fragment {

    List<Human> people = new ArrayList<>();
    final String TAG = "States";
    AdapterRecycler adapterRecycler;
    Handler handler;

    public Female() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_female, container, false);
        RecyclerView recyclerView = view.findViewById(R.id.list);
        adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "Female is resumed");
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase db = App.getInstance().getDatabase();
                HumanDao humanDao = db.humanDao();
                people.clear();
                List<Human> humanFemale = humanDao.getAll();
                for (Human human: humanFemale){
                    if (human.getSex().equals("Female")){
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
        Log.d(TAG, "Female is stopped");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, "Female is paused");
    }

//    @Override
//    public void onProfileClick(int position) {
//        people.get(position);
//    }
}
