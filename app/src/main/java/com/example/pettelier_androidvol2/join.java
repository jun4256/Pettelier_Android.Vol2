package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
        Intent intent = getIntent();

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_nick = findViewById(R.id.edt_nick);
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_adr = findViewById(R.id.edt_adr);
        edt_adr2 = findViewById(R.id.edt_adr2);

        findViewById(R.id.btn_join).setOnClickListener(v -> {
            doJoin();
        });

        findViewById(R.id.btn_cancel).setOnClickListener(v -> {
            finish();
        });
    }

    private void doJoin(){
        String joinId = edt_id.getText().toString().trim();
        String joinPw = edt_pw.getText().toString().trim();
        String joinNick = edt_nick.getText().toString().trim();
        String joinName = edt_name.getText().toString().trim();
        String joinPhone = edt_phone.getText().toString().trim();
        String joinAdr = edt_adr.getText().toString().trim();
        String joinAdr2 = edt_adr2.getText().toString().trim();

        if(joinId.length()==0){
            Toast.makeText(this,"아이디를 입력해주세요.",Toast.LENGTH_SHORT).show();
            edt_id.requestFocus();

            return;
        }

        if(joinPw.length()==0){
            Toast.makeText(this,"비밀번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
            edt_pw.requestFocus();

            return;
        }

        if(joinNick.length()==0) {
            Toast.makeText(this, "닉네임을 입력해주세요.", Toast.LENGTH_SHORT).show();
            edt_nick.requestFocus();

            return;
        }


        if(joinName.length()==0) {
            Toast.makeText(this, "이름을 입력해주세요.", Toast.LENGTH_SHORT).show();
            edt_name.requestFocus();

            return;
        }


        if(joinPhone.length()==0) {
            Toast.makeText(this, "휴대폰번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            edt_phone.requestFocus();

            return;
        }


        if(joinAdr.length()==0) {
            Toast.makeText(this, "주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
            edt_adr.requestFocus();

            return;
        }

        if(joinAdr2.length()==0) {
            Toast.makeText(this, "상세주소를 입력해주세요.", Toast.LENGTH_SHORT).show();
            edt_adr2.requestFocus();

            return;
        }

    }
}