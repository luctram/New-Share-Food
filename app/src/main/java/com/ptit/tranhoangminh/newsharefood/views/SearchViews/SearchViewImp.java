package com.ptit.tranhoangminh.newsharefood.views.SearchViews;

import com.ptit.tranhoangminh.newsharefood.models.Product;

import java.util.List;

/**
 * Created by Harrik on 5/9/2018.
 */

public interface SearchViewImp {
    public void showProgress();
    public void hideProgress();
    public void displayMessage(String message);

    public void displaySearchProducts(List<Product> searchProductList);
}
