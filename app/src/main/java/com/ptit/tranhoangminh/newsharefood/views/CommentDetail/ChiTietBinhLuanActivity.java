package com.ptit.tranhoangminh.newsharefood.views.CommentDetail;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.adapters.AdpaterRecycleViewImageComment;
import com.ptit.tranhoangminh.newsharefood.models.CommentModel;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ChiTietBinhLuanActivity extends AppCompatActivity {
    CircleImageView circleImageView;
    TextView txtTieuDe, txtNoidung, txtDiem;
    RecyclerView recyclerView;
    CommentModel commentModel;
    List<Bitmap> bitmapList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.custom_layout_comment);
        AddControl();
        commentModel = getIntent().getParcelableExtra("comment_model");
        LoadComment();

    }

    private void LoadComment() {
        txtTieuDe.setText(commentModel.getTieude());
        txtNoidung.setText(commentModel.getNoidung());
        txtDiem.setText(commentModel.getChamdiem() + "");
        setHinhAnhBinhLuan(circleImageView, commentModel.getMemberModel().getHinhanh());

        //duyệt mảng string hình comment-- bỏ vào list bitmap--truyền qua adapter recycle view image commment
        for (String linkhinh : commentModel.getListImageComment()) {
            StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("images").child(linkhinh);
            long ONE_MEGABYTE = 1024 * 1024;
            storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
                @Override
                public void onSuccess(byte[] bytes) {
                    Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                    bitmapList.add(bitmap);
                    if (bitmapList.size() == commentModel.getListImageComment().size()) {
                        AdpaterRecycleViewImageComment adapter = new AdpaterRecycleViewImageComment(ChiTietBinhLuanActivity.this, R.layout.custom_layout_image_comment, bitmapList, commentModel,true);
                        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(ChiTietBinhLuanActivity.this, 2);
                        recyclerView.setLayoutManager(layoutManager);
                        recyclerView.setAdapter(adapter);
                        adapter.notifyDataSetChanged();
                    }

                }
            });
        }
    }

    //load hình từ firebase
    private void setHinhAnhBinhLuan(final CircleImageView circleImageView, String linkhinh) {
        StorageReference storageHinhUser = FirebaseStorage.getInstance().getReference().child("thanhvien").child(linkhinh);
        long ONE_MEGABYTE = 1024 * 1024;
        storageHinhUser.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                circleImageView.setImageBitmap(bitmap);
            }
        });
    }

    private void AddControl() {
        circleImageView = (CircleImageView) findViewById(R.id.imgUser);
        txtTieuDe = (TextView) findViewById(R.id.txtTieude);
        txtNoidung = (TextView) findViewById(R.id.txtNoidung);
        txtDiem = (TextView) findViewById(R.id.txtDiem1);
        recyclerView = findViewById(R.id.recycleViewHinhBL);
        bitmapList = new ArrayList<>();
    }
}
