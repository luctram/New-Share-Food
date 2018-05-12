package com.ptit.tranhoangminh.newsharefood.presenters.productDetailPresenters;

import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.MemberModel;
import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface LoadProductDetailListener {
    void onLoadProductDetailSuccess(ProductDetail productDetail);

    void onLoadProgressedFailure(String message);

    void onLoadImageProductDetailSuccess(Bitmap bitmap);

    void onLoadOwnerDetailSuccess(MemberModel memberModel);

    void onLoadImageOwnerSuccess(Bitmap bitmap);

    void onSaveSuccess(int i);

    void onLoadUnProgressFailure(String message);
}
