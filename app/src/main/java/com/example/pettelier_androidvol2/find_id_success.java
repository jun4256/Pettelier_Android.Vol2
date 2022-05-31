package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class find_id_success extends AppCompatActivity {

    private TextView finded_id;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private ImageView img_findid2;
    private View view2;
    private Button btn_f_s_login, btn_f_s_pwrs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id_success);

        finded_id = findViewById(R.id.finded_id);
        Intent intent = getIntent();
        String value = intent.getStringExtra("mb_id");

        finded_id.setText(value);


        btn_f_s_login = findViewById(R.id.btn_f_s_login);
        btn_f_s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);
            }
        });

        btn_f_s_pwrs = findViewById(R.id.btn_f_s_pwrs);
        btn_f_s_pwrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),pw_reset.class);
                startActivity(intent);
            }
        });

        //MemberVO info = loginCheck.info;
        //Log.v("myData", info.toString());
    }
}