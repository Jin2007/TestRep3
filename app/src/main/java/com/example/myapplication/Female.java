package com.example.myapplication;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class Female extends Fragment {

    List<Human> people = new ArrayList<>();
    final String TAG = "States";
    AdapterRecycler adapterRecycler;
    AppDatabase db;

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

        db = App.getInstance().getDatabase();
        LiveData<List<Human>> modelLiveData = db.humanDao().getAll();
        modelLiveData.observe(this, new Observer<List<Human>>() {
            @Override
            public void onChanged(List<Human> humans) {
                people.clear();
                List<Human> femaleList = new ArrayList<>();
                femaleList.addAll(humans);
                for (Human h: femaleList){
                    if (h.sex.equals("Female")){
                        people.add(h);
                    }
                }
                adapterRecycler.notifyDataSetChanged();
                Log.d(TAG, "Female OnChanged");
            }
        });
    }

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
}
