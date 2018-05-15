package com.ptit.tranhoangminh.newsharefood.views.AddEditProductViews.activities;

import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface AddEditProductView {
    public void showProgress();

    public void hideProgress();

    public void displayMessage(String message);

    public void displayEditProduct(ProductDetail productDetail, Bitmap bitmap);

    public void backActivity();
}
