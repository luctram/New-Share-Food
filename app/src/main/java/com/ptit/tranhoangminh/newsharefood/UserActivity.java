

package com.ptit.tranhoangminh.newsharefood;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.ptit.tranhoangminh.newsharefood.adapters.PagerAdapter;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Tab1Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Tab2Fragment;

public class UserActivity extends AppCompatActivity implements Tab1Fragment.OnFragmentInteractionListener, Tab2Fragment.OnFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("Tab1"));
        tabLayout.addTab(tabLayout.newTab().setText("Tab2"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (true) {

                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//cc
            }
        });

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
