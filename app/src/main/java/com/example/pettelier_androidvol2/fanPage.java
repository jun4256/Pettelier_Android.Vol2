package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.ToggleButton;

public class fanPage extends AppCompatActivity {
    private TextView tv_cage1,tv_cage2,tv_cage3,
            tv_cage4,tv_cage5,tv_cage6;
    private ToggleButton toggleFan1,toggleFan2,toggleFan3,toggleFan4,toggleFan5,toggleFan6;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_fan_page);
        }

}