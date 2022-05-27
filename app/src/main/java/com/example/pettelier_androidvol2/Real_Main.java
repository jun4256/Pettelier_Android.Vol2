package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Real_Main extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.go_home);
    }

    // 웹 페이지 띄우기
    public void onyoutube1(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/CHUCHU7325"));
        startActivity(intent);
    }

    public void onyoutube2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/hahahaYouTube"));
        startActivity(intent);
    }

    public void onyoutube3(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/mochamilk"));
        startActivity(intent);
    }
}