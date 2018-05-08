package com.ptit.tranhoangminh.newsharefood.presenters.savedProductDetailPresenters;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

public interface LoadSavedProductDetailListener {
    void onLoadSavedProductDetailSuccess(ProductDetail pDetail);

    void onLoadSavedProductDetailFailure(String message);
}
