package com.ptit.tranhoangminh.newsharefood.views.productViews.activities;

import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.ArrayList;

public interface ProductView {
    public void showProgress();

    public void hideProgress();

    public void displayProducts(ArrayList<Product> productArrayList);

    public void displayMessage(String message);
}
