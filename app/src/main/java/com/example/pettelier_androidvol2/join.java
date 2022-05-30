package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class join extends AppCompatActivity {

    private EditText edt_id, edt_pw, edt_nick, edt_name, edt_phone, edt_adr, edt_adr2;
    private Button btn_confirm, btn_join, btn_cancel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);
        // id, pw, nick, name, phone, addr -> 회원가입
        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_nick = findViewById(R.id.edt_nick);
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_adr = findViewById(R.id.edt_adr);
        //edt_adr2 = findViewById(R.id.edt_adr2);

        // 중복확인
        btn_confirm = findViewById(R.id.btn_confirm);


        // 취소
        btn_cancel = findViewById(R.id.btn_cancel);


        //가입하기 버튼
        btn_join = findViewById(R.id.btn_join);
        btn_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String joinId = edt_id.getText().toString().trim();   // trim이 아마 공백제거
                String joinPw = edt_pw.getText().toString().trim();
                String joinNick = edt_nick.getText().toString().trim();
                String joinName = edt_name.getText().toString().trim();
                String joinPhone = edt_phone.getText().toString().trim();
                String joinAdr = edt_adr.getText().toString().trim();
                //String joinAdr2 = edt_adr2.getText().toString().trim();

                if(joinId.length()==0){  // id len이 0이면.
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_id.requestFocus();    // 그 칸으로 이동하는 기능
                    return;
                }
                if(joinPw.length()==0){
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_pw.requestFocus();
                    return;
                }
                if(joinNick.length()==0) {
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_nick.requestFocus();
                    return;
                }
                if(joinName.length()==0) {
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_name.requestFocus();
                    return;
                }
                if(joinPhone.length()==0) {
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_phone.requestFocus();
                    return;
                }
                if(joinAdr.length()==0) {
                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
                    edt_adr.requestFocus();
                    return;
                }

                else{
                    //  if 회원가입이 성공적이면 데이터를 db에 저장하고 로그인 페이지로 돌린다
                    //  else if 회원가입이 성공적이지 못하면 다시
                }
//                if(joinAdr2.length()==0) {
//                    Toast.makeText(getApplicationContext(),"아이디입력해주세요",Toast.LENGTH_SHORT).show();
//                    edt_adr2.requestFocus();
//
//                    return;
//                }
            }
        });
//        findViewById(R.id.btn_cancel).setOnClickListener(v -> {
//            finish();     --> 시스템 종료(앱 종료) 뒤로가기로 메소드 다시 만들 것
//        });
    }

//

}
