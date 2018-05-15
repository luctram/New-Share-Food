package com.ptit.tranhoangminh.newsharefood;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ptit.tranhoangminh.newsharefood.views.fragment.Home1Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home2Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home3Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.Home4Fragment;
import com.ptit.tranhoangminh.newsharefood.views.fragment.HomeSearchFragment;

public class HomeActivity extends AppCompatActivity {

    Button btnhome1, btnhome2, btnhome3, btnhome4;
    EditText edtSearch;
    ViewPager viewPager;

    String textSearch = "";
    Boolean mIsEmpty = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        btnhome1 = (Button) findViewById(R.id.btnhome1);
        btnhome2 = (Button) findViewById(R.id.btnhome2);
        btnhome3 = (Button) findViewById(R.id.btnhome3);
        btnhome4 = (Button) findViewById(R.id.btnhome4);
        edtSearch = (EditText) findViewById(R.id.edtfind);
        viewPager = (ViewPager) findViewById(R.id.vp);

        btnhome1.performClick();

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    textSearch = s.toString();
                    mIsEmpty = false;
                    AddFragment(null);
                }
                else {
                    mIsEmpty = true;
                    AddFragment(null);
                }
            }
        });

    }

    public void AddFragment(View view) {
        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = null;

        if(view==null){
            fragment = new HomeSearchFragment();

            if(mIsEmpty == false) {
                Bundle bundle = new Bundle();
                bundle.putString("keySearch", textSearch);
                fragment.setArguments(bundle);
            }

        } else {
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
        }

        fragmentTransaction.replace(R.id.frameContent, fragment);
        fragmentTransaction.commit();
    }
}
