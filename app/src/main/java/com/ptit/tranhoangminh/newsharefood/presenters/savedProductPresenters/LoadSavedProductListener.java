package com.ptit.tranhoangminh.newsharefood.presenters.savedProductPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.List;

public interface LoadSavedProductListener {
    void onLoadSavedProductsSuccess(List<Product> productArrayList);

    void onLoadSavedProductsFailure(String message);
}
