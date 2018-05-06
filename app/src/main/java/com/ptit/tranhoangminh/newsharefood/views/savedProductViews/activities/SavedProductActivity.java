package com.ptit.tranhoangminh.newsharefood.views.savedProductViews.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.adapters.ProductAdapter;
import com.ptit.tranhoangminh.newsharefood.database.DatabaseHelper;
import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.ArrayList;

public class SavedProductActivity extends AppCompatActivity  {
    private ArrayList<Product> productArrayList = new ArrayList<>();
    private ProductAdapter myAdapter;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_layout);
        gridView = findViewById(R.id.gridview);
        DatabaseHelper db = new DatabaseHelper(this);

        this.productArrayList.addAll(db.getAllProducts());
        this.myAdapter = new ProductAdapter(this, R.layout.dong_products, productArrayList);
        gridView.setAdapter(myAdapter);
    }
}
