package com.example.infotech.Activities;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.infotech.R;
import com.squareup.picasso.Picasso;

public class NewsDetails extends AppCompatActivity {
    private ImageView detailedImg;
    private TextView detailedTitle, detailedDesc, detailedContent;
    private Context context;
    private String imgUrl, title, desc, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        if(Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blackShaded));
        }
        imgUrl = getIntent().getStringExtra("NewsPoster");
        title = getIntent().getStringExtra("title");
        desc = getIntent().getStringExtra("description");
        content = getIntent().getStringExtra("content");

        detailedImg = findViewById(R.id.detailedImg);
        detailedTitle = findViewById(R.id.detailedTitle);
        detailedDesc =  findViewById(R.id.detailedDesc);
        detailedContent =  findViewById(R.id.detailedContent);
//
        Picasso.get().load(imgUrl).into(detailedImg);
        detailedTitle.setText(title);
        detailedDesc.setText(desc);
        detailedContent.setText(content);
    }
}