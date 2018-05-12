package com.ptit.tranhoangminh.newsharefood.models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ProductSQLite extends Product {
    private byte[] imageByte;

    public ProductSQLite() {
    }

    public ProductSQLite(String id, String parent_id, String name, String image, byte[] imageByte) {
        super(id, parent_id, name, image);
        this.imageByte = imageByte;
    }

    public byte[] getBitmap() {
        return imageByte;
    }

    public void setBitmap(byte[] bitmap) {
        this.imageByte = bitmap;
    }

    public Bitmap getByteasBitmap() {
        return BitmapFactory.decodeByteArray(imageByte, 0, imageByte.length);
    }
}
