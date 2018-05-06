package com.ptit.tranhoangminh.newsharefood.views.productDetailViews.activities;

import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface ProductDetailView {
    public void showProgress();

    public void hideProgress();

    public void displayMessage(String message);

    public void displayProductDetail(ProductDetail productDetail, Bitmap bitmap);

    public void setLike(int i);

    public void setCheckedLike(boolean b);
}
