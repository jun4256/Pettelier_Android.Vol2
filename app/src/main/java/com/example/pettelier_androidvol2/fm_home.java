package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

public class fm_home extends Fragment {
    private TextView tv_home;
    private ImageButton ClientFragment1_youtube1, ClientFragment1_youtube2,ClientFragment1_youtube3;

    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.activity_real_main, container, false);
        //tv_home = fragment.findViewById(R.id.tv_home);
        ClientFragment1_youtube1 =fragment.findViewById(R.id.onyoutube1);
        ClientFragment1_youtube2 =fragment.findViewById(R.id.onyoutube2);
        ClientFragment1_youtube3 = fragment.findViewById(R.id.onyoutube3);

        ClientFragment1_youtube1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/CHUCHU7325"));
                startActivity(intent);

            }
        });
        ClientFragment1_youtube2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/hahahaYouTube"));
                startActivity(intent);
            }
        });
        ClientFragment1_youtube3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/c/mochamilk"));
                startActivity(intent);
            }
        });

        return fragment;
    }


}