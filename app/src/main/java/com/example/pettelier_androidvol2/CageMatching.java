package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CageMatching extends AppCompatActivity {
    private ListView cage_list;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private MatchingAdapter adapter = new MatchingAdapter();
    private ArrayList<String> items = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cage_matching);
        cage_list = findViewById(R.id.cage_list);

        // adapter.clear();

        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://59.0.129.176:8081/web/matchingList.do";

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String mb_id = jsonObject.getString("mb_id");
                        String cg_serial = jsonObject.getString("cg_serial");

                        adapter.addItem(mb_id,cg_serial);
                        Log.v("짬",mb_id);
                        Log.v("집 보내주세요",cg_serial);


                    }

                    cage_list.setAdapter(adapter);

                    adapter.notifyDataSetChanged();



                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), "케이지매칭", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }) {
            @Override //response를 UTF8로 변경해주는 소스코드
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                // String c_manager_id = SharedPreference.getAttribute(getApplicationContext(), "m_id");
                // params.put("c_manager_id", c_manager_id);

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);



    }


}