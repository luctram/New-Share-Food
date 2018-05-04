package com.ptit.tranhoangminh.newsharefood;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.ptit.tranhoangminh.newsharefood.views.categoryViews.activities.CategoryActivity;

/**
 * Created by Dell on 3/12/2018.
 */

public class Splashscreen extends AppCompatActivity {
    Button btnVaobep,btnDangNhap;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen);
        AnhXa();
        btnVaobep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu=new Intent(Splashscreen.this,CategoryActivity.class);
                startActivity(intentTrangChu);
            }
        });
    }

    private void AnhXa() {
        btnVaobep=findViewById(R.id.btnVaobep);
        btnDangNhap=findViewById(R.id.btnDangnhap);
    }

}
