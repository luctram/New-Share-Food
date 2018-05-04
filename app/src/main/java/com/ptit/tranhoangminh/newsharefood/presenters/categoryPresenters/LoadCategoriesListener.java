package com.ptit.tranhoangminh.newsharefood.presenters.categoryPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Category;

import java.util.ArrayList;

/**
 * Created by Lãng on 26/4/2018.
 */

public interface LoadCategoriesListener {
    void onLoadCategoriesSuccess(ArrayList<Category> cateList);

    void onLoadCategoriesFailure(String massage);
}
