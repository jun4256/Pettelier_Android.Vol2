package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class fm_board extends Fragment {

    private TextView tv_board;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View fragment = inflater.inflate(R.layout.board, container, false);

        //board 페이지에서 만든 각종 요소
        tv_board = fragment.findViewById(R.id.tv_board);

        return fragment;
    }
}
