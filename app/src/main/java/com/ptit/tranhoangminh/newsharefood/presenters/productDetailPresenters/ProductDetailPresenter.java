package com.ptit.tranhoangminh.newsharefood.presenters.productDetailPresenters;


import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;
import com.ptit.tranhoangminh.newsharefood.views.productDetailViews.activities.ProductDetailView;

public class ProductDetailPresenter implements LoadProductDetailListener {
    private ProductDetailView productDetailView;
    private ProductDetailInteractor productDetailInteractor;

    public ProductDetailPresenter(ProductDetailView productDetailView) {
        this.productDetailView = productDetailView;
        this.productDetailInteractor = new ProductDetailInteractor(this);
    }

    public void loadProductDetail(String id, String image_id) {
        productDetailView.showProgress();
        productDetailInteractor.createProductDetail(id, image_id);
    }

    @Override
    public void onLoadProductDetailSuccess(ProductDetail productDetail, Bitmap bitmap) {
        productDetailView.displayProductDetail(productDetail, bitmap);
        productDetailView.hideProgress();
    }

    @Override
    public void onLoadProductDetailFailure(String message) {
        productDetailView.displayMessage(message);
        productDetailView.hideProgress();
    }
}
