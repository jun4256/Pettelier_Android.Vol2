package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;

public class fm_board extends Fragment {

    private TextView tv_board;
    private String[] board_drop = {"정보공유","문의","당근"};
    private ListView board_list;
    private ArrayAdapter<String> boardAdapter;   //리스트뷰에 적용되는 보드어댑터
    private ArrayList<String> items = new ArrayList<>();    //어댑터에 들어갈 데이터
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Button btn_write;



    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View fragment = inflater.inflate(R.layout.board, container, false);

        //객체 찾아옴
        tv_board = fragment.findViewById(R.id.tv_board);
        board_list = fragment.findViewById(R.id.board_list);
        btn_write = fragment.findViewById(R.id.btn_write);


        //spinner 객체 생성 (드롭다운)
        Spinner spinner = fragment.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,board_drop);

        adapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        spinner.setAdapter(adapter);


        //드롭다운 선택시 텍스트뷰에 나타남
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_board.setText(board_drop[i]);






            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_board.setText("");

            }







        });
        return fragment;
    }
}
