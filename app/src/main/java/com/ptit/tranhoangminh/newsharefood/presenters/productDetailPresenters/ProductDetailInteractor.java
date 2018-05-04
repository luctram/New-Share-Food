package com.ptit.tranhoangminh.newsharefood.presenters.productDetailPresenters;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ptit.tranhoangminh.newsharefood.models.ProductDetail;

import java.io.File;
import java.io.IOException;

public class ProductDetailInteractor {
    private DatabaseReference myRef;
    private StorageReference mStorageRef;
    private LoadProductDetailListener listener;

    public ProductDetailInteractor(LoadProductDetailListener listener) {
        this.listener = listener;
        this.mStorageRef = FirebaseStorage.getInstance().getReference();
        this.myRef = FirebaseDatabase.getInstance().getReference();
    }

    public void createProductDetail(final String id, final String image_id) {
        myRef.child("ProductDetail").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Iterable<DataSnapshot> iterable = dataSnapshot.getChildren();
                for (DataSnapshot item : iterable) {
                    final ProductDetail pdetail = item.getValue(ProductDetail.class);
                    if (pdetail.getId().equals(id)) {
                        try {
                            final File localFile = File.createTempFile(image_id, ".png");
                            mStorageRef.child("Products").child(image_id).getFile(localFile)
                                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                                        @Override
                                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                                            final Bitmap[] bitmap = new Bitmap[1];
                                            bitmap[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                                            listener.onLoadProductDetailSuccess(pdetail, bitmap[0]);
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception exception) {
                                    listener.onLoadProductDetailFailure("Failed to read image. " + exception.getMessage());
                                }
                            });
                            return;
                        } catch (IOException e) {
                            listener.onLoadProductDetailFailure("Failed to create temp file. " + e.getMessage());
                        }
                    }
                }
                listener.onLoadProductDetailFailure("Failed to find product detail.");
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                listener.onLoadProductDetailFailure("Failed to read product detail. " + databaseError.toException());
            }
        });
    }
}
