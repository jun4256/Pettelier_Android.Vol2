package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Admin_Login_Main extends AppCompatActivity {
    Button btn_add_cage, btn_admin_moniter, btn_admin_feed, btn_admin_fan_control;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login_main);
        btn_add_cage = findViewById(R.id.btn_add_cage);
        btn_admin_moniter = findViewById(R.id.btn_admin_moniter);
        btn_admin_feed  = findViewById(R.id.btn_admin_feed);
        btn_admin_fan_control = findViewById(R.id.btn_admin_fan_control);

        btn_add_cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(),Cage_Register.class);
                    startActivity(intent);
            }
        });


        btn_admin_moniter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_admin_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_admin_fan_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}