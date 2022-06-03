package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class Setting extends Fragment {

    private Button re_member_btn, re_pet_btn, add_pet_btn;
    private FragmentManager fm;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.setting, container, false);

        //객체 찾아옴
        re_member_btn = fragment.findViewById(R.id.re_member_btn);
        re_pet_btn = fragment.findViewById(R.id.re_pet_btn);
        add_pet_btn = fragment.findViewById(R.id.add_pet_btn);
        fm = getActivity().getSupportFragmentManager();

        re_member_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), MemberUpdate.class);
                startActivity(intent);
            }
        });

        re_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), pet_regi_retouch.class);
                startActivity(intent);
            }
        });


        add_pet_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), Pet_register.class);
                startActivity(intent);
            }
        });


        return fragment;
    }
}