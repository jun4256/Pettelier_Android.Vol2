package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class feedPage extends Fragment {

    private ListView cageFeedList;
    private ArrayAdapter<String> feedAdapter;   //리스트뷰에 적용되는 먹이 배급 어댑터
    private ArrayList<String> feedList = new ArrayList<>();    //어댑터에 들어갈 데이터
    private RequestQueue requestQueue;
    private StringRequest stringRequest;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_feed_page, container, false);

        return fragment;
    }
}