package com.example.pettelier_androidvol2;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class boardWrite extends AppCompatActivity {
    private Button reg_button, cle_button;
    private EditText board_title, content;
    private TextView writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_write);

        reg_button = findViewById(R.id.reg_button);
        cle_button = findViewById(R.id.cle_button);
        board_title = findViewById(R.id.board_title);
        content = findViewById(R.id.content);
        writer = findViewById(R.id.writer);

    }
}