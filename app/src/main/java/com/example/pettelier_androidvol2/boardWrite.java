package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class boardWrite extends Fragment {
    private Button btn_upload, btn_reset;
    private EditText board_title,content;
    private TextView writer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_board_write, container, false);

        //객체찾아오기

        board_title = fragment.findViewById(R.id.board_title);
        content = fragment.findViewById(R.id.content);
        btn_upload = fragment.findViewById(R.id.btn_upload);
        btn_reset = fragment.findViewById(R.id.btn_reset);
        writer = fragment.findViewById(R.id.writer);


        return fragment;
    }
}