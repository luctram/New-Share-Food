package com.ptit.tranhoangminh.newsharefood.views.savedProductViews.activities;

import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.models.ProductSQLite;

import java.util.List;

public interface SavedProductView {
    public void showProgress();

    public void hideProgress();

    public void displayMessage(String message);

    public void displaySavedProducts(List<ProductSQLite> savedProductList);
}
