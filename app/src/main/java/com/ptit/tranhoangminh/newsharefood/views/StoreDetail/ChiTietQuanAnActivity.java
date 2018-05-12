package com.ptit.tranhoangminh.newsharefood.views.StoreDetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.adapters.AdapterComment;
import com.ptit.tranhoangminh.newsharefood.models.StoreModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ChiTietQuanAnActivity extends AppCompatActivity {
    TextView txtTenQuan, txtDiaChi, txtHinh, txtCheckin, txtThoiGianHD, txtTongBL, txtTongLuu, txtTrangThaiHD;
    ImageView imgHinhQuanAn;
    StoreModel storeModel;
    RecyclerView recyclerView;
    AdapterComment adapterComment;
    NestedScrollView nestedScrollViewCT;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chitiet_quanan_layout);
        AddControl();
        storeModel = getIntent().getParcelableExtra("store");

    }

    private void AddControl() {
        imgHinhQuanAn = findViewById(R.id.imgHinhChiTiet);
        txtDiaChi = findViewById(R.id.txtDiaChiCT);
        txtHinh = findViewById(R.id.txttongSoHinhAnhCT);
        txtCheckin = findViewById(R.id.txttongSoCheckInCT);
        txtThoiGianHD = findViewById(R.id.txtThoiGianHoatDong);
        txtTongBL = findViewById(R.id.txttongSoBinhLuanCT);
        txtTongLuu = findViewById(R.id.txtTongSoLuuLaiCT);
        txtTrangThaiHD = findViewById(R.id.txtTrangThaiHoatDong);
        txtTenQuan = findViewById(R.id.txtTenQuanAnCT);
        recyclerView = findViewById(R.id.recyclerBinhLuanChiTietQuanAn);
        nestedScrollViewCT=findViewById(R.id.netScrollViewCT);

    }

    @Override
    protected void onStart() {
        super.onStart();
        //xu li thoi gian mo/dong cua
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String current_time = simpleDateFormat.format(calendar.getTime());
        String open_time = storeModel.getGiomocua();
        String close_time = storeModel.getGiodongcua();

        try {
            Date date_current_time = simpleDateFormat.parse(current_time);
            Date date_open_time = simpleDateFormat.parse(open_time);
            Date date_close_time = simpleDateFormat.parse(close_time);
            if (date_current_time.after(date_open_time) && date_current_time.before(date_close_time)) {
                txtTrangThaiHD.setText("Đang mở cửa");
            } else {
                txtTrangThaiHD.setText("Đóng cửa");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        txtTenQuan.setText(storeModel.getTenquan());
        txtDiaChi.setText(storeModel.getBranchModelList().get(0).getDiachi());
        txtThoiGianHD.setText(storeModel.getGiomocua() + " - " + storeModel.getGiodongcua());
        txtHinh.setText(storeModel.getHinhanh().size() + "");
        txtTongBL.setText(storeModel.getCommentModelList().size() + "");

        //set image
        StorageReference storageReferenceImage = FirebaseStorage.getInstance().getReference().child("images").child(storeModel.getHinhanh().get(0));
        final long ONE_MEGABYTE = 1024 * 1024;
        storageReferenceImage.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>() {
            @Override
            public void onSuccess(byte[] bytes) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgHinhQuanAn.setImageBitmap(bitmap);
            }
        });
        //load comment
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapterComment = new AdapterComment(this, R.layout.custom_layout_comment, storeModel.getCommentModelList());
        recyclerView.setAdapter(adapterComment);
        adapterComment.notifyDataSetChanged();

        //scrollview luôn hiển thi trên cùng khi load xong data
        nestedScrollViewCT.smoothScrollBy(0,0);
    }
}
