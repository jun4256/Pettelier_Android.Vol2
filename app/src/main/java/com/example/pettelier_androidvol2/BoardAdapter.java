package com.example.pettelier_androidvol2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

    public class BoardAdapter extends BaseAdapter {

        private ArrayList<BoardVO> items = new ArrayList<BoardVO>();

        public void addItem(String id, String title, String date) {
            BoardVO vo = new BoardVO(id, title, date);
            items.add(vo);
        }


        @Override
        public int getCount() {
            // 어댑터가 가지고 있는 아이템의 개수를 알려주는 메소드
            return items.size();
        }

        @Override
        public Object getItem(int i) {
            // 어댑터에게 해당 i 번째의 아이템을 요청하는 메소드
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            // Adapter가 가지고 있는 item만큼 만들어놓은 xml에 틀에 맞게 넣어주는 메소드
            // 필수 구현

            // 1. kakao.xml 불러오기
            Context context = viewGroup.getContext();

            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                // R.layout.보드리스트 이런식으로 수정해야함
                view = inflater.inflate(R.layout.board, viewGroup, false);
            }

            TextView custom_tv1 = view.findViewById(R.id.custom_tv1);
            TextView custom_tv2 = view.findViewById(R.id.custom_tv2);
            TextView custom_tv3 = view.findViewById(R.id.custom_tv3);
            TextView custom_tv4 = view.findViewById(R.id.custom_tv4);

            BoardVO vo = items.get(i);

            custom_tv1.setText(String.valueOf(i));
            custom_tv2.setText(vo.getTitle());
            custom_tv3.setText(vo.getId());
            custom_tv4.setText(vo.getDate());

            return view;
        }
    }

