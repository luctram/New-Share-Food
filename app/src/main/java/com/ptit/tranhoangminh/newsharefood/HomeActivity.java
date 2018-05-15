package com.ptit.tranhoangminh.newsharefood;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ptit.tranhoangminh.newsharefood.views.fragment.Home1Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home2Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home3Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home4Fragment;

public class HomeActivity extends AppCompatActivity {

    Button btnhome1, btnhome2, btnhome3, btnhome4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnhome1 = (Button) findViewById(R.id.btnhome1);
        btnhome2 = (Button) findViewById(R.id.btnhome2);
        btnhome3 = (Button) findViewById(R.id.btnhome3);
        btnhome4 = (Button) findViewById(R.id.btnhome4);

    }

    public void AddFragment(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        switch (view.getId()) {
            case R.id.btnhome1:
                fragment = new Home1Fragment();
                break;
            case R.id.btnhome2:
                fragment = new Home2Fragment();
                break;
            case R.id.btnhome3:
                fragment = new Home3Fragment();
                break;
            case R.id.btnhome4:
                fragment = new Home4Fragment();
                break;
        }

        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }
}
