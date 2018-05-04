package com.ptit.tranhoangminh.newsharefood.reference;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

/**
 * Created by Lãng on 14/4/2018.
 */

public class FirebaseReference {

    final static String TAG = "FirebaseReference";

    public static void setImageFromFireBase(StorageReference mStorageRef, String prefix, String suffix, final ImageView img) {
        try {
            final File localFile = File.createTempFile(prefix, suffix);
            Log.d(TAG, "createTempFile: success!");
            mStorageRef.getFile(localFile)
                    .addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            final Bitmap[] bitmap = new Bitmap[1];
                            Log.d(TAG, "setImageFromFireBase: success!");
                            bitmap[0] = BitmapFactory.decodeFile(localFile.getAbsolutePath());
                            img.setImageBitmap(bitmap[0]);
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.e(TAG, "setImageFromFireBase: fail! " + exception.getMessage());
                }
            });
        } catch (IOException e) {
            Log.e(TAG, "createTempFile: fail! " + e.toString());
        }
    }

    public static void uploadImageToFirebase(final Activity activity, StorageReference mStorageRef, final ImageView img) {
        img.setDrawingCacheEnabled(true);
        img.buildDrawingCache();
        Bitmap bitmap = img.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] data = baos.toByteArray();
        UploadTask uploadTask = mStorageRef.putBytes(data);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                Log.d(TAG, "uploadImageToFirebase: success!");
                Uri downloadURL = taskSnapshot.getDownloadUrl();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.e(TAG, "uploadImageToFirebase: fail! " + e.getMessage());
                Toast.makeText(activity, "Không thể upload ảnh", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
