package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Admin_Login_Main extends Fragment {
    Button btn_add_cage, btn_admin_moniter, btn_admin_feed, btn_admin_fan_control;

    private FragmentManager fm;
    private Admin_Login_Main home;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_admin_login_main,container,false);
        btn_add_cage = fragment.findViewById(R.id.btn_add_cage);
        btn_admin_moniter = fragment.findViewById(R.id.btn_admin_moniter);
        btn_admin_feed = fragment.findViewById(R.id.btn_admin_feed);
        btn_admin_fan_control =fragment.findViewById(R.id.btn_admin_fan_control);


        btn_add_cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Cage_Register.class);
                startActivity(intent);
            }
        });

        btn_admin_moniter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Pet_register.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        btn_admin_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),fm_feedPage.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        btn_admin_fan_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),fanPage.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        return fragment;
    }



}