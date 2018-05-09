package com.ptit.tranhoangminh.newsharefood.views.Register;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.ptit.tranhoangminh.newsharefood.R;
import com.ptit.tranhoangminh.newsharefood.models.MemberModel;
import com.ptit.tranhoangminh.newsharefood.presenters.registerPresenter.RegisterPresenterLogic;

/**
 * Created by Dell on 5/7/2018.
 */

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener,RegisterViewImp {
    Button btnDangKy;
    EditText edEmailDK,edPasswordDK,edNhapLaiPasswordDK;
    FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;
    RegisterPresenterLogic registerPresenterLogic;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki_layout);

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);

        btnDangKy = (Button) findViewById(R.id.btnClickDK);
        edEmailDK = (EditText) findViewById(R.id.edtEmailDK);
        edPasswordDK = (EditText) findViewById(R.id.edtPassDK);
        edNhapLaiPasswordDK = (EditText) findViewById(R.id.edtPass2);

        btnDangKy.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        progressDialog.setMessage("Loading...");
        progressDialog.setIndeterminate(true);

        progressDialog.show();

        final String email = edEmailDK.getText().toString();
        String matkhau = edPasswordDK.getText().toString();
        String nhaplaimatkhau = edNhapLaiPasswordDK.getText().toString();


        if(email.trim().length() == 0 ){

            Toast.makeText(this,"Email chua nhap",Toast.LENGTH_SHORT).show();
        }else if(matkhau.trim().length() == 0){

            Toast.makeText(this,"Pass chua nhap",Toast.LENGTH_SHORT).show();
        }else if(!nhaplaimatkhau.equals(matkhau)){
            Toast.makeText(this,"Pass2 phai giong pass1",Toast.LENGTH_SHORT).show();
        }else{
            firebaseAuth.createUserWithEmailAndPassword(email,matkhau).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        progressDialog.dismiss();
                        MemberModel memberModel=new MemberModel();
                        memberModel.setHoten(email);
                        memberModel.setHinhanh("user.png");
                        String uid=task.getResult().getUser().getUid();
                        registerPresenterLogic=new RegisterPresenterLogic(RegisterActivity.this);
                        registerPresenterLogic.AddInfoMember(memberModel,uid);
                        Toast.makeText(RegisterActivity.this,"Success",Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}
