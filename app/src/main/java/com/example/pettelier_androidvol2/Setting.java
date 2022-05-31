package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Setting extends AppCompatActivity {

    private Button re_member_btn, re_pet_btn, add_pet_btn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);

        re_member_btn = findViewById(R.id.re_member_btn);
        re_pet_btn = findViewById(R.id.re_pet_btn);
        add_pet_btn = findViewById(R.id.add_pet_btn);

        re_member_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),new_pw_reset.class);
                startActivity(intent);
            }
        });

        re_pet_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),pet_regi_retouch.class);
                startActivity(intent);
            }
        });

        add_pet_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),Pet_register.class);
                startActivity(intent);
            }
        });
    }

}