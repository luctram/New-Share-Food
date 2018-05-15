package com.ptit.tranhoangminh.newsharefood.presenters.addEditProductPresenters;

import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface LoadAddEditProductListener {
    void onLoadEditProductSuccess(ProductDetail productDetail, Bitmap bitmap);

    void onLoadEditProductFailure(String message);

    void onPushNewProductSuccess();

    void onPushNewProductFailure(String message);

    void onSetOldProductSuccess();

    void onSetOldProductFailure(String message);

    void onCancel();
}
