package com.ptit.tranhoangminh.newsharefood.presenters.searchProductPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.List;

/**
 * Created by Harrik on 5/9/2018.
 */

public interface LoadSearchProductListener {
    void onLoadSearchProductsSuccess(List<Product> productArrayList);
    void onLoadSearchProductsFailure(String message);
}
