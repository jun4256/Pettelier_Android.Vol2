package com.example.pettelier_androidvol2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class boardWrite extends Fragment {
    private Button reg_button, cle_button;
    private EditText board_title,content;
    private TextView writer;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_board_write, container, false);

        //객체찾아오기

        board_title = fragment.findViewById(R.id.board_title);
        content = fragment.findViewById(R.id.content);
        reg_button = fragment.findViewById(R.id.reg_button);
        cle_button = fragment.findViewById(R.id.cle_button);
        writer = fragment.findViewById(R.id.writer);


        return fragment;
    }
}