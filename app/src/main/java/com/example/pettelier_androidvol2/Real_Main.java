package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;


public class Real_Main extends AppCompatActivity {

    private Button btn_Mainlogin,Main_btn1,Main_btn2;
    private TextView welcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);
        Intent intent = getIntent();

        btn_Mainlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });


        Main_btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),join.class);
                startActivity(intent);
            }
        });

        Main_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),find_id.class);
                startActivity(intent);
            }
        });

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

    public void info10(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://animalmagazine.co.kr/bbs/board.php?bo_table=magazine&wr_id=239&sca=%EC%9D%B8%ED%8F%AC"));
        startActivity(intent);
    }

    public void info9(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://witkorea.kr/board/knowhow/1568"));
        startActivity(intent);
    }

    public void info8(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bongus-studio.com/"));
        startActivity(intent);
    }

    public void info7(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.k-health.com/news/articleView.html?idxno=55651"));
        startActivity(intent);
    }

    public void info6(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myanimals.co.kr/animals/the-benefits-of-children-and-pets-living-together/"));
        startActivity(intent);
    }

    public void info5(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.naver.com/chgg01/222521362582"));
        startActivity(intent);
    }

    public void info4(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hangang.seoul.go.kr/archives/61023"));
        startActivity(intent);
    }

    public void info3(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stylezineblog.com/4619"));
        startActivity(intent);
    }

    public void info2(View view){
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://kfem.or.kr/?p=211296"));
        startActivity(intent);
    }
}