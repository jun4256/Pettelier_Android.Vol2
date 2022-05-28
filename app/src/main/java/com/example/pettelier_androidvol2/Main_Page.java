package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Main_Page extends AppCompatActivity {
    private    VideoView vv;           // 비디오 나중에 작성

    private BottomNavigationView navi;   // 하단 네비게이션 바
    private fm_board board;   //프라그먼트 보드 (게시판 페이지 만든거 이름변경 하세요)
    private fm_home home;     // 프라그먼트 홈 ( 홈 페이지 만든거에 맞춰 이름변경 하세요)
    private fm_setting setting;   // 이거는 내가 만들었음
    private FragmentManager fm;  // fragment 객체 관리
    private Button btn_feed;    // Feed 밥주기 버튼


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        vv=findViewById(R.id.vv);                // 동영상 추후 변경
        Uri viedeoURI = Uri.parse("ddaf.mp4");   // 동영상 추후 변경

        board = new fm_board();   // 네비바 게시판
        home = new fm_home();     // 네비바   홈
        setting = new fm_setting();  // 네비바 세팅
        fm = getSupportFragmentManager();   // 프라그먼트 매니저 셋팅


        navi = findViewById(R.id.navi);      //하단 네비바
        navi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){
                    case R.id.board:
                        fm.beginTransaction().replace(R.id.frame,board).commit();
                        Toast.makeText(getApplicationContext(), "게시판", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.go_home:  // 홈레이아웃은 진관씨가  만든 걸로 바꿉니다
                        fm.beginTransaction().replace(R.id.frame,home).commit();
                        Toast.makeText(getApplicationContext(), "홈", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.setting:
                        fm.beginTransaction().replace(R.id.frame,setting).commit();
                        Toast.makeText(getApplicationContext(), "설정", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });



    }
}