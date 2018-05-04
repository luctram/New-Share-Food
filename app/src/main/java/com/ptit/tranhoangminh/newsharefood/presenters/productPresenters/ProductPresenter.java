package com.ptit.tranhoangminh.newsharefood.presenters.productPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.views.productViews.activities.ProductView;

import java.util.ArrayList;

/**
 * Created by Lãng on 26/4/2018.
 */

public class ProductPresenter implements LoadProductsListener {
    private ProductView productView;
    private ProductInteractor productInteractor;
    private String cate_id;

    public ProductPresenter(ProductView productView) {
        this.productView = productView;
        this.productInteractor = new ProductInteractor(this);
    }

    public void loadProducts(String cate_id) {
        productView.showProgress();
        this.cate_id = cate_id;
        productInteractor.createProductList(cate_id);
    }

    @Override
    public void onLoadProductsSuccess(ArrayList<Product> productArrayList) {
        productView.displayProducts(productArrayList);
        productView.hideProgress();
    }

    @Override
    public void onLoadProductsFailure(String message) {
        productView.displayMessage(message);
        productView.hideProgress();
    }

    public void destroyProductOnFirebase(String id, String image_id) {
        productView.showProgress();
        productInteractor.destroyProductOnFirebase(id,image_id);
        //productInteractor.createProductList(cate_id);
    }

    @Override
    public void onDestroyProductSuccess() {
        productView.displayMessage("Xóa sản phẩm thành công");
        productView.hideProgress();
    }

    @Override
    public void onDestroyProductFailure(String message) {
        productView.displayMessage(message);
        productView.hideProgress();
    }
}
