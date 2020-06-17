package com.example.myapplication;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import com.google.android.material.tabs.TabLayout;

import java.util.List;


public class MainActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    ActionBar actionBar;

    List<Human> people;
    AdapterRecycler adapterRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabLayout);
        viewPager = findViewById(R.id.viewPager);
        actionBar = getSupportActionBar();

        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Male"));
        tabLayout.addTab(tabLayout.newTab().setText("Female"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final AdapterFragment adapterFragment = new AdapterFragment(this, getSupportFragmentManager(),
                tabLayout.getTabCount());
        viewPager.setAdapter(adapterFragment);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
    }

    void showDialogMethod(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Ты че, дурак?!")
                .setPositiveButton(R.string.fire, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, final int id) {
                        // Use mListRowPosition for clicked list row...


                        Thread thread = new Thread(new Runnable() {
                            @Override
                            public void run() {
                                AppDatabase db = App.getInstance().getDatabase();
                                HumanDao humanDao = db.humanDao();
                                people.addAll(humanDao.getAll());
                                humanDao.delete(people.get(position));
                                people.remove(position);
                            }
                        });
                        thread.start();
                        adapterRecycler.notifyItemRemoved(id);
                        adapterRecycler.notifyDataSetChanged();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                });
        // Create the AlertDialog object
        builder.setCancelable(false);
        builder.create();
        builder.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.item1:
                Intent intent = new Intent(this, ActivityTwo.class);
                startActivityForResult(intent,1);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}