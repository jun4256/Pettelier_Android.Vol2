package com.example.pettelier_androidvol2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

    public class BoardAdapter extends BaseAdapter {
        private FragmentManager fm;

        private ArrayList<BoardVO> items = new ArrayList<BoardVO>();

        public void addItem(String seq, String id, String title, String content, String date) {
            BoardVO vo = new BoardVO(seq, id, title, content, date);
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
                view = inflater.inflate(R.layout.board_custom, viewGroup, false);
            }

            TextView custom_tvSeq = view.findViewById(R.id.custom_tvSeq);
            TextView custom_tvTitle = view.findViewById(R.id.custom_tvTitle);
            TextView custom_tvWriter = view.findViewById(R.id.custom_tvWriter);
            TextView custom_tvDate = view.findViewById(R.id.custom_tvDate);

            BoardVO vo = items.get(i);

            custom_tvSeq.setText(vo.getSeq());
            custom_tvTitle.setText(vo.getTitle());
            custom_tvWriter.setText(vo.getId());
            custom_tvDate.setText(vo.getDate());


            return view;
        }
    }

