package com.example.pettelier_androidvol2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class fanAdapter extends BaseAdapter {
    private ArrayList<CageVO> items = new ArrayList<CageVO>();
    public void addItem(String cg_serial, String cg_motor) {
        CageVO cvo = new CageVO(cg_serial, cg_motor);
        items.add(cvo);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // R.layout.보드리스트 이런식으로 수정해야함  // d아아ㅏㅇ
            view = inflater.inflate(R.layout.fan_custom_page, viewGroup, false);
        }


        TextView ctv_cage_serial = view.findViewById(R.id.ctv_cage_serial);
        Button btn_fan_state = view.findViewById(R.id.btn_fan_state);



        CageVO cvo = items.get(i);

        ctv_cage_serial.setText(cvo.getCg_serial());
        btn_fan_state.setText(cvo.getCg_motor());


        return view;
    }
}
