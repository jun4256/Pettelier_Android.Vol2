package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class find_id_fail extends AppCompatActivity {

    private Button btn_f_id, btn_f_f_join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_fail);

        btn_f_id = findViewById(R.id.btn_f_id);
        btn_f_f_join = findViewById(R.id.btn_f_f_join);

        btn_f_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),find_id.class);
                startActivity(intent);
            }
        });

        btn_f_f_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),join.class);
                startActivity(intent);
            }
        });
    }
}