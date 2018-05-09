package com.ptit.tranhoangminh.newsharefood.presenters.searchProductPresenters;

import android.content.Context;

import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.presenters.savedProductPresenters.SavedProductInteractor;
import com.ptit.tranhoangminh.newsharefood.views.SearchViews.SearchViewImp;
import com.ptit.tranhoangminh.newsharefood.views.savedProductViews.activities.SavedProductView;

import java.util.List;

/**
 * Created by Harrik on 5/9/2018.
 */

public class SearchProductPresenter implements LoadSearchProductListener {
    private SearchViewImp searchProductView;
    private SearchProductInteractor searchProductInteractor;

    public SearchProductPresenter(SearchViewImp searchProductView, Context context) {
        this.searchProductView = searchProductView;
        this.searchProductInteractor = new SearchProductInteractor(this, context);
    }

    public void loadAllSearchProducts(String cate_id,String name) {
        searchProductView.showProgress();
        searchProductInteractor.createSearchProductList(cate_id,name);
    }
    @Override
    public void onLoadSearchProductsSuccess(List<Product> productArrayList) {
        searchProductView.displaySearchProducts(productArrayList);
        searchProductView.hideProgress();
    }
    public void onLoadSearchProductsFailure(String message) {
        searchProductView.displayMessage(message);
        searchProductView.hideProgress();
    }
}
