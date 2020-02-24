package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Human> people = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInitialData();

        RecyclerView recyclerView = findViewById(R.id.list);

        MyAdapter adapter = new MyAdapter(this, people);

        recyclerView.setAdapter(adapter);
    }

    private void setInitialData() {

        people.add(new Human("Vasiliy", "Pupkin"));
        people.add(new Human("Roman", "Starshiy"));
        people.add(new Human ("Roman", "Mladshiy"));
        people.add(new Human ("Vitalka", "Boxer"));
    }
}
