package com.example.baicanhan_nguyenphuongdong;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.baicanhan_nguyenphuongdong.model.User;

public class InfomationActivity extends AppCompatActivity {
    private ImageButton fb, youtube, phone, sms;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infomation);
        initView();

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/pdong.99/"));
                startActivity(intent);
            }
        });
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC7jF-Aoykswc2fNR475vIvA"));
                startActivity(intent);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:0949971615"));
                startActivity(intent);
            }
        });
        sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("sms:"+"0949971615"));
                intent.putExtra("sms_body", "Can I take a date with you?");
                startActivity(intent);
            }
        });
    }

    private void initView() {
        fb = findViewById(R.id.facebook);
        youtube = findViewById(R.id.youtube);
        phone = findViewById(R.id.phone);
        sms = findViewById(R.id.sms);
    }
}