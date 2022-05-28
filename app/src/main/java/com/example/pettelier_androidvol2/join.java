package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class join extends AppCompatActivity {

    private EditText edt_id, edt_pw, edt_nick, edt_name, edt_phone, edt_adr, edt_adr2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setTitle("회원가입");

        edt_id = findViewById(R.id.edt_id);
        edt_pw = findViewById(R.id.edt_pw);
        edt_nick = findViewById(R.id.edt_nick);
        edt_name = findViewById(R.id.edt_name);
        edt_phone = findViewById(R.id.edt_phone);
        edt_adr = findViewById(R.id.edt_adr);
        edt_adr2 = findViewById(R.id.edt_adr2);
    }
}