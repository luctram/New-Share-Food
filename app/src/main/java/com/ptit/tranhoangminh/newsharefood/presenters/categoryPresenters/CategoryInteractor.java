package com.ptit.tranhoangminh.newsharefood.presenters.categoryPresenters;

import com.ptit.tranhoangminh.newsharefood.models.Category;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Lãng on 26/4/2018.
 */

public class CategoryInteractor {
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    private LoadCategoriesListener listener;

    public CategoryInteractor(LoadCategoriesListener listener) {
        this.listener = listener;
    }

    public void createCategoryList() {
        final ArrayList<Category> categoriesList = new ArrayList<>();
        myRef.child("Categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                categoriesList.clear();
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot item : iterable) {
                    Category cate = item.getValue(Category.class);
                    categoriesList.add(cate);
                }
                listener.onLoadCategoriesSuccess(categoriesList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onLoadCategoriesFailure("Failed to read categories. " + databaseError.toException());
            }
        });
    }
}
