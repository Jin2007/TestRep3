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

public class All extends Fragment {

    List<Human> people = new ArrayList<>();
    final String TAG = "States";
    AdapterRecycler adapterRecycler;
    AppDatabase db;
    HumanDao humanDao;
    Handler handler;

        public All() {
            // Required empty public constructor
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //Log.d(TAG, "All Tab: onCreate()");
            View view = inflater.inflate(R.layout.fragment_all, container, false);
            RecyclerView recyclerView = view.findViewById(R.id.list);
            adapterRecycler = new AdapterRecycler(view.getContext(), people);
            recyclerView.setAdapter(adapterRecycler);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        Log.d(TAG, "All Tab: onResume()");
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                db = App.getInstance().getDatabase();
                humanDao = db.humanDao();
                people.clear();
                people.addAll(humanDao.getAll());
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
        Log.d(TAG, "All is stopped");
    }

    @Override
    public void onPause(){
        super.onPause();
        Log.d(TAG, " All is paused");
    }
}
