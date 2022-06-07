package com.example.pettelier_androidvol2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class petAdapter extends BaseAdapter {
    private ArrayList<DogVO> items = new ArrayList<>();
    public void addItem(String dog_name, String dog_type, String dog_age) {
        DogVO dvo = new DogVO(dog_name,dog_type,dog_age);
        items.add(dvo);
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
            view = inflater.inflate(R.layout.pet_regi_retouch_custom, viewGroup, false);
        }


        TextView rtv_dog_name = view.findViewById(R.id.rtv_dog_name);
        TextView rtv_dog_type = view.findViewById(R.id.rtv_dog_type);
        TextView rtv_dog_age = view.findViewById(R.id.rtv_dog_age);


        DogVO dvo = items.get(i);

        rtv_dog_name.setText(dvo.getDog_name());
        rtv_dog_type.setText(dvo.getDog_type());
        rtv_dog_age.setText(dvo.getDog_age());




        return view;
    }
}
