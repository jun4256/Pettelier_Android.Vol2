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
import android.widget.Spinner;
import android.widget.TextView;

public class fm_board extends Fragment {

    private TextView tv_board;
    private String[] board_drop = {"정보공유","문의","당근"};



    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater,@Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View fragment = inflater.inflate(R.layout.board, container, false);

        //board 페이지에서 만든 각종 요소
        tv_board = fragment.findViewById(R.id.tv_board);
        Spinner spinner = fragment.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_dropdown_item_1line,board_drop);

        adapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        spinner.setAdapter(adapter);

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
