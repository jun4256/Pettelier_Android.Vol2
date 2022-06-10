package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class After_Login_Main extends Fragment {

    private Button btn_nick,Main_btn3,Main_btn4;
    private FragmentManager fm;
    private ImageButton onyoutube1,onyoutube2,onyoutube3,
            info10, info9,info8,info7,info6,info5,info4,info3,info2;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_after_login_main,container,false);
        btn_nick = fragment.findViewById(R.id.btn_nick);
        Main_btn3 = fragment.findViewById(R.id.Main_btn3);
        Main_btn4 = fragment.findViewById(R.id.Main_btn4);

        onyoutube1 = fragment.findViewById(R.id.onyoutube1);
        onyoutube2 = fragment.findViewById(R.id.onyoutube2);
        onyoutube3 = fragment.findViewById(R.id.onyoutube3);

        info10 = fragment.findViewById(R.id.info10);
        info9 = fragment.findViewById(R.id.info9);
        info8 = fragment.findViewById(R.id.info8);
        info7 = fragment.findViewById(R.id.info7);
        info6 = fragment.findViewById(R.id.info6);
        info5 = fragment.findViewById(R.id.info5);
        info4 = fragment.findViewById(R.id.info4);
        info3 = fragment.findViewById(R.id.info3);
        info2 = fragment.findViewById(R.id.info2);

        Main_btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Pet_register.class);
                startActivity(intent);
            }
        });

        Main_btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://192.168.43.183:81/stream"));
                startActivity(intent);
            }
        });

        if((loginCheck.info)!=null){
            btn_nick.setText(loginCheck.info.getNick());

        }
        onyoutube1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/CHUCHU7325"));
                startActivity(intent);
            }
        });

        onyoutube2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/hahahaYouTube"));
                startActivity(intent);
            }
        });

        onyoutube3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/mochamilk"));
                startActivity(intent);
            }
        });

        info10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://animalmagazine.co.kr/bbs/board.php?bo_table=magazine&wr_id=239&sca=%EC%9D%B8%ED%8F%AC"));
                startActivity(intent);
            }
        });

        info9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://witkorea.kr/board/knowhow/1568"));
                startActivity(intent);
            }
        });

        info8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://bongus-studio.com/"));
                startActivity(intent);
            }
        });

        info7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.k-health.com/news/articleView.html?idxno=55651"));
                startActivity(intent);
            }
        });

        info6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://myanimals.co.kr/animals/the-benefits-of-children-and-pets-living-together/"));
                startActivity(intent);
            }
        });

        info5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://blog.naver.com/chgg01/222521362582"));
                startActivity(intent);
            }
        });

        info4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://hangang.seoul.go.kr/archives/61023"));
                startActivity(intent);
            }
        });

        info3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://stylezineblog.com/4619"));
                startActivity(intent);
            }
        });

        info2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://kfem.or.kr/?p=211296"));
                startActivity(intent);
            }
        });
        return fragment;
    }




}