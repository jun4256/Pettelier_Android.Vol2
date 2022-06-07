package com.example.pettelier_androidvol2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;

public class CommentAdapter extends BaseAdapter {

    private ArrayList<CmtVO> items = new ArrayList<CmtVO>();

    public void addItem(String cmt_seq, String id, String title, String date) {
        CmtVO vo = new CmtVO(cmt_seq, id, title, date);
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
            view = inflater.inflate(R.layout.cmt_custom, viewGroup, false);
        }

        TextView cmt_tvSeq = view.findViewById(R.id.cmt_tvSeq);
        TextView cmt_tvContent = view.findViewById(R.id.cmt_tvContent);
        TextView cmt_tvWriter = view.findViewById(R.id.cmt_tvWriter);
        TextView cmt_tvDate = view.findViewById(R.id.cmt_tvDate);

        CmtVO vo = items.get(i);

        cmt_tvSeq.setText(vo.getCmt_seq());
        cmt_tvContent.setText(vo.getCmt_content());
        cmt_tvWriter.setText(vo.getMb_id());
        cmt_tvDate.setText(vo.getCmt_date());

        return view;
    }
}

