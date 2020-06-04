package com.example.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;
import java.util.List;

public class Female extends Fragment {

    List<Human> people = new ArrayList<>();
    //DBHelper dbHelper;
    AppDatabase db;
    HumanDao humanDao;
    //final String TAG = "States";
    AdapterRecycler adapterRecycler;
    Handler handler;

    public Female() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //Log.d(TAG, "Female Tab: onCreate()");
        View view = inflater.inflate(R.layout.fragment_female, container, false);
        //dbHelper = new DBHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.list);
        adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                //Log.d(TAG, "Female Tab: onResume()");
                //SQLiteDatabase db = dbHelper.getWritableDatabase();
                //Cursor c = db.query("mytable", null, null, null, null, null, null);
                db = App.getInstance().getDatabase();
                humanDao = db.humanDao();
                people.clear();
                //people.addAll(humanDao.getAll());
                List<Human> humanFemale = humanDao.getAll();
                for (Human human: humanFemale){
                    if (human.getSex().equals("Female")){
                        people.add(human);
                    }
                }
//                if (c.moveToFirst()) {
//                    int nameColIndex = c.getColumnIndex("name");
//                    int surnameColIndex = c.getColumnIndex("surname");
//                    int sexColIndex = c.getColumnIndex("sex");
//                    int photoColIndex = c.getColumnIndex("uri");
//                    do {
//                        if (c.getString(sexColIndex) == null) {
//                            return;
//                        } else if (c.getString(sexColIndex).equals("Female")) {
//                            Human human = new Human(c.getString(nameColIndex), c.getString(surnameColIndex),
//                                    c.getString(sexColIndex), c.getString(photoColIndex));
//                            people.add(human);
//                        }
//                    } while (c.moveToNext());
//                }
                handler.post(updateProgress);
//                c.close();
//                dbHelper.close();
            }
        });
        thread.start();
    }

    Runnable updateProgress = new Runnable() {
        public void run() {
            adapterRecycler.notifyDataSetChanged();
        }
    };
}
