package com.example.myapplication;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class AdapterFragment extends FragmentStatePagerAdapter {
    Context context;
    int totalTabs;

    public AdapterFragment(Context c, FragmentManager fm, int totalTabs) {
        super(fm);
        context = c;
        this.totalTabs = totalTabs;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                All allFragment = new All();
                return allFragment;
            case 1:
                Male maleFragment = new Male();
                return maleFragment;
            case 2:
                Female femFragment = new Female();
                return femFragment;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return totalTabs;
    }
}
