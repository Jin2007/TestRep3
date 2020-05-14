package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    DBHelper dbHelper;

    public Male() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_male, container, false);
        dbHelper = new DBHelper(getContext());
        RecyclerView recyclerView = view.findViewById(R.id.list);
        AdapterRecycler adapterRecycler = new AdapterRecycler(view.getContext(), people);
        recyclerView.setAdapter(adapterRecycler);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.query("mytable", null, null, null, null, null, null);
        people.clear();

        if (c.moveToFirst()) {
            int nameColIndex = c.getColumnIndex("name");
            int surnameColIndex = c.getColumnIndex("surname");
            int sexColIndex = c.getColumnIndex("sex");
            do {
                if (c.getString(sexColIndex) == null) {
                    return;
                } else if (c.getString(sexColIndex).equals("Male")) {
                    Human human = new Human(c.getString(nameColIndex), c.getString(surnameColIndex), c.getString(sexColIndex));
                    people.add(human);
                }
            } while (c.moveToNext());
        }
        c.close();
        dbHelper.close();
    }
}
