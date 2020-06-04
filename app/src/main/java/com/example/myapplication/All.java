package com.example.myapplication;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
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
    //DBHelper dbHelper;
    AppDatabase db;
    HumanDao humanDao;
    //final String TAG = "States";
    AdapterRecycler adapterRecycler;
    Handler handler;

        public All() {
            // Required empty public constructor
        }
        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            //Log.d(TAG, "All Tab: onCreate()");
            View view = inflater.inflate(R.layout.fragment_all, container, false);
            //dbHelper = new DBHelper(getContext());
            RecyclerView recyclerView = view.findViewById(R.id.list);
            adapterRecycler = new AdapterRecycler(view.getContext(), people);
            recyclerView.setAdapter(adapterRecycler);
            return view;
        }

    @Override
    public void onResume() {
        super.onResume();
        //Log.d(TAG, "All Tab: onResume()");
        handler = new Handler();

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {

                //SQLiteDatabase db = dbHelper.getWritableDatabase();
                //Cursor c = db.query("mytable", null, null, null, null, null, null);
                db = App.getInstance().getDatabase();
                humanDao = db.humanDao();
                people.clear();
                people.addAll(humanDao.getAll());
                //db.delete("mytable", null, null);

//                if (c.moveToFirst()) {
//                    int nameColIndex = c.getColumnIndex("name");
//                    int surnameColIndex = c.getColumnIndex("surname");
//                    int sexColIndex = c.getColumnIndex("sex");
//                    int photoColIndex = c.getColumnIndex("uri");
//                    do {
//                        Human human = new Human(c.getString(nameColIndex), c.getString(surnameColIndex),
//                                c.getString(sexColIndex), c.getString(photoColIndex));
//                        people.add(human);
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
