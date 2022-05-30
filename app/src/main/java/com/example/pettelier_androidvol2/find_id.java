package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class find_id extends AppCompatActivity {

    private EditText edt_findname, edt_findphone;
    private Button btn_f_id, btn_f_cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        edt_findname = findViewById(R.id.edt_findname);
        edt_findphone = findViewById(R.id.edt_findphone);

        btn_f_id = findViewById(R.id.btn_f_id);
        btn_f_cancel = findViewById(R.id.btn_f_cancel);

        btn_f_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        btn_f_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
    private void findid(){
        String name = edt_findname.getText().toString().trim();
        String phone = edt_findphone.getText().toString().trim();

        if(name.length()==0){
            Toast.makeText(this, "이름을 입력해주세요", Toast.LENGTH_SHORT).show();
            edt_findname.requestFocus();

            return;
        }
        if(phone.length()==0){
            Toast.makeText(this, "연락처를 입력해주세요", Toast.LENGTH_SHORT).show();
            edt_findphone.requestFocus();

            return;
        }


    }
}