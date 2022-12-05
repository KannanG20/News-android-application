package com.example.infotech.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.infotech.R;

public class StarterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_screen);
        if(Build.VERSION.SDK_INT >= 21){
            Window window = this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.blackShaded));
        }
        Thread welcomeThread = new Thread(){
            @Override
            public void run(){
                try{
                    super.run();
                    sleep(5000);
                }catch (Exception e){

                } finally {
                    Intent intent = new Intent(StarterScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        welcomeThread.start();
    }
}