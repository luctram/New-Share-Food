package com.ptit.tranhoangminh.newsharefood.presenters.productPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.ArrayList;

/**
 * Created by Lãng on 26/4/2018.
 */

public interface LoadProductsListener {
    void onLoadProductsSuccess(ArrayList<Product> productArrayList);

    void onLoadProductsFailure(String message);

    void onDestroyProductSuccess();

    void onDestroyProductFailure(String message);
}
