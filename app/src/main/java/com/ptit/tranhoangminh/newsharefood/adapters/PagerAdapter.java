package com.ptit.tranhoangminh.newsharefood.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.ptit.tranhoangminh.newsharefood.views.fragment.Tab1Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Tab2Fragment;

public class PagerAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PagerAdapter(FragmentManager fm, int NumberOfTabs) {
        super(fm);
        this.numOfTabs = NumberOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Tab1Fragment tab1 = new Tab1Fragment();
                return tab1;

            case 1:
                Tab2Fragment tab2 = new Tab2Fragment();
                return  tab2;

            default:
                return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
