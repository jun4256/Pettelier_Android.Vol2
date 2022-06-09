package com.example.pettelier_androidvol2;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MatchingAdapter extends BaseAdapter {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private ArrayList<MatchingVO> items = new ArrayList<MatchingVO>();

    public void clear(){
        items.clear();
    }

    public void addItem(String mb_id, String cg_serial) {
        MatchingVO mvo = new MatchingVO(mb_id, cg_serial);
        items.add(mvo);
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
            view = inflater.inflate(R.layout.matching_custom, viewGroup, false);
        }


        TextView match_cage_serial = view.findViewById(R.id.match_cage_serial);
        EditText match_idInput = view.findViewById(R.id.match_idInput);

        MatchingVO mvo = items.get(i);

        match_cage_serial.setText(mvo.getCg_serial());
        match_idInput.setText(mvo.getMb_id());


        return view;
    }
}


