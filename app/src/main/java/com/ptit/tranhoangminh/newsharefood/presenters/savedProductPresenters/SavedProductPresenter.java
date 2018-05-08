package com.ptit.tranhoangminh.newsharefood.presenters.savedProductPresenters;

import android.content.Context;

import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.views.savedProductViews.activities.SavedProductView;

import java.util.List;

public class SavedProductPresenter implements LoadSavedProductListener {
    private SavedProductView savedProductView;
    private SavedProductInteractor savedProductInteractor;

    public SavedProductPresenter(SavedProductView savedProductView, Context context) {
        this.savedProductView = savedProductView;
        this.savedProductInteractor = new SavedProductInteractor(this, context);
    }

    public void loadAllSavedProducts() {
        savedProductView.showProgress();
        savedProductInteractor.createSavedProductList();
    }

    @Override
    public void onLoadSavedProductsSuccess(List<Product> savedProductList) {
        savedProductView.displaySavedProducts(savedProductList);
        savedProductView.hideProgress();
    }

    @Override
    public void onLoadSavedProductsFailure(String message) {
        savedProductView.displayMessage(message);
        savedProductView.hideProgress();
    }
}
