package com.ptit.tranhoangminh.newsharefood;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ContactActivity extends AppCompatActivity {

    EditText edtSub, edtMes;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);

        edtSub = (EditText) findViewById(R.id.edtSubject);
        edtMes = (EditText) findViewById(R.id.edtMessage);
        btnSend = (Button) findViewById(R.id.btnSendMail);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String toEmail = "sharefood@gmail.com";
                String subject = edtSub.getText().toString().trim();
                String message = edtMes.getText().toString().trim();

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] { toEmail });
                email.putExtra(Intent.EXTRA_SUBJECT, subject);
                email.putExtra(Intent.EXTRA_TEXT, message);

                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose app to send mail:"));
            }
        });
    }
}
