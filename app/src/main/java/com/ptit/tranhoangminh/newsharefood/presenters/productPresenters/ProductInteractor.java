package com.ptit.tranhoangminh.newsharefood.presenters.productPresenters;

import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ptit.tranhoangminh.newsharefood.models.Product;
import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

import java.util.ArrayList;

/**
 * Created by Lãng on 26/4/2018.
 */

public class ProductInteractor {
    private DatabaseReference myRef = FirebaseDatabase.getInstance().getReference();
    private StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();
    private LoadProductsListener listener;

    public ProductInteractor(LoadProductsListener listener) {
        this.listener = listener;
    }

    public void createProductList(final String cate_id) {
        final ArrayList<Product> productList = new ArrayList<>();
        myRef.child("Products").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                productList.clear();
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot item : iterable) {
                    Product pd = item.getValue(Product.class);
                    if (pd.getParent_id().equals(cate_id)) {
                        productList.add(pd);
                    }
                }
                listener.onLoadProductsSuccess(productList);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onLoadProductsFailure("Failed to read products. " + databaseError.toException());
            }
        });
    }

    public void destroyProductOnFirebase(final String id, final String image_id) {
        myRef.child("Products").child(id).removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                destroyProductDetailOnFirebase(id, image_id);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onDestroyProductFailure("Failed to remove product. " + e.getMessage());
            }
        });
    }

    private void destroyProductDetailOnFirebase(final String id, final String image_id) {
        myRef.child("ProductDetail").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot item : iterable) {
                    ProductDetail pdDetail = item.getValue(ProductDetail.class);
                    if (pdDetail.getId().equals(id)) {
                        item.getRef().removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                destroyStorageOnFirebase(image_id);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                listener.onDestroyProductFailure("Failed to remove product detail. " + e.getMessage());
                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onDestroyProductFailure("Failed to read value. " + databaseError.toException());
            }
        });
    }

    private void destroyStorageOnFirebase(String id) {
        mStorageRef.child("Products").child(id).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void aVoid) {
                listener.onDestroyProductSuccess();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                listener.onDestroyProductFailure("Failed to remove storage. " + e.getMessage());
            }
        });
    }
}
