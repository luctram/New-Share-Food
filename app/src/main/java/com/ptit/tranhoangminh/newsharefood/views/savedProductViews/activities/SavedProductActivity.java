package com.ptit.tranhoangminh.newsharefood.views.savedProductViews.activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.adapters.ProductAdapter;
import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.presenters.savedProductPresenters.SavedProductPresenter;
import com.ptit.tranhoangminh.newsharefood.views.savedProductDetailViews.activities.SavedProductDetailActivity;

import java.util.ArrayList;
import java.util.List;

public class SavedProductActivity extends AppCompatActivity implements SavedProductView {
    private List<Product> productArrayList;
    private ProductAdapter myAdapter;
    private GridView gridView;
    private ProgressBar pgbSavedProduct;
    private SavedProductPresenter savedProductPresenter;
    private Toolbar toolbar;

    @SuppressLint("RestrictedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.products_layout);
        setControls();

        initPresenter();
        savedProductPresenter.loadAllSavedProducts();

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MÓN ĂN ĐÃ LƯU");
        toolbar.setTitleTextColor(Color.BLACK);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setEvents();
    }

    void setEvents() {
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent detailProductIntent = new Intent(SavedProductActivity.this, SavedProductDetailActivity.class);
                detailProductIntent.putExtra("objectKey", productArrayList.get(position));
                startActivity(detailProductIntent);
            }
        });
    }

    private void setControls() {
        gridView = findViewById(R.id.gridview);
        pgbSavedProduct = findViewById(R.id.progressBarProduct);
        productArrayList = new ArrayList<>();
        toolbar = findViewById(R.id.toolbarLoaiMonAn);
    }

    private void initPresenter(){
        savedProductPresenter = new SavedProductPresenter(this, this);
    }

    @Override
    public void showProgress() {
        pgbSavedProduct.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pgbSavedProduct.setVisibility(View.GONE);
    }

    @Override
    public void displayMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displaySavedProducts(List<Product> savedProductList) {
        this.productArrayList = savedProductList;
        this.myAdapter = new ProductAdapter(this, R.layout.dong_products, productArrayList);
        gridView.setAdapter(myAdapter);
    }

}
