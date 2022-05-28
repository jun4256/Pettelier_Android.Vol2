package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class fm_feedPage extends Fragment {
    private TextView tv_cage1,tv_feedtime1,tv_cage2,tv_feedtime2,tv_cage3,tv_feedtime3,
            tv_cage4,tv_feedtime4,tv_cage5,tv_feedtime5,tv_cage6,tv_feedtime6;
    private Button btn_feed1,btn_feed2,btn_feed3,btn_feed4,btn_feed5,btn_feed6;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_feed_page, container, false);



        return fragment;
    }
}