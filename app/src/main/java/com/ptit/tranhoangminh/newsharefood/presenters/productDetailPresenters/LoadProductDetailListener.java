package com.ptit.tranhoangminh.newsharefood.presenters.productDetailPresenters;

import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface LoadProductDetailListener {
    void onLoadProductDetailSuccess(ProductDetail productDetail, Bitmap bitmap);

    void onLoadProductDetailFailure(String message);
}
