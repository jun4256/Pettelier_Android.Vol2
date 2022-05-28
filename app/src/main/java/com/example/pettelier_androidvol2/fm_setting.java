package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class fm_setting extends Fragment {

    private TextView tv_setting;
    private ListView listView;
    private ArrayList<String> items = new ArrayList<String>();
    private ArrayAdapter<String> adapter;
    private int stop=1;


    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View fragment = inflater.inflate(R.layout.setting, container, false);
        tv_setting = fragment.findViewById(R.id.tv_setting);
        listView = fragment.findViewById(R.id.listview);

        if(stop==1){
        items.add("1.내 정보 수정");
        items.add("2.개 정보 수정");
        items.add("3.개 추가 ");
        stop=stop-1;
        }

        adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1,items);

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


                String num = (String) adapterView.getItemAtPosition(i);

                Log.v("i",num);

                if(num=="2.개 정보 수정"){  // Manifest에 activity 클래스 추가해주고 쓰세요 ^^
                    Intent intent = new Intent(getContext(),pet_regi_retouch.class);
                    startActivity(intent);
                }

                if(num=="1.내 정보 수정"){     //임시로 넣음 아무튼 일단 됨 .,
                    Intent intent = new Intent(getContext(),fm_board.class);
                    startActivity(intent);
                }
            }
        });


        return fragment;
    }

}