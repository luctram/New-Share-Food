package com.ptit.tranhoangminh.newsharefood.presenters.productDetailPresenters;


import android.content.Context;
import android.graphics.Bitmap;

import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;
import com.ptit.tranhoangminh.newsharefood.views.productDetailViews.activities.ProductDetailView;

public class ProductDetailPresenter implements LoadProductDetailListener {
    private ProductDetailView productDetailView;
    private ProductDetailInteractor productDetailInteractor;

    public ProductDetailPresenter(ProductDetailView productDetailView, Context context) {
        this.productDetailView = productDetailView;
        this.productDetailInteractor = new ProductDetailInteractor(this, context);
    }

    public void loadProductDetail(String id, String image_id) {
        productDetailView.showProgress();
        productDetailInteractor.createProductDetail(id, image_id);
        productDetailView.setCheckedLike(productDetailInteractor.isExistItemSQlite(id));
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

    public void liked(Product product, ProductDetail pDetail, Bitmap bitmap) {
        productDetailInteractor.addProductSqlite(product, pDetail, bitmap);
        productDetailInteractor.addLike(product.getId());
    }

    public void unLike(String id) {
        productDetailInteractor.removeProductSqlite(id);
        productDetailInteractor.removeLike(id);
    }

    @Override
    public void onLikeSuccess(int i) {
        productDetailView.setLike(i);
    }

    @Override
    public void onLikeFailure(String message) {
        productDetailView.displayMessage(message);
    }

}
