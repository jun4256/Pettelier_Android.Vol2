package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class boardWrite extends AppCompatActivity {
    private Button btn_upload, btn_reset;
    private EditText board_title,content;
    private TextView writer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);



    }
}