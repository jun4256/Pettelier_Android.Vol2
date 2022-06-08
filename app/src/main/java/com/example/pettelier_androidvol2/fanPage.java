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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

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

public class fanPage extends AppCompatActivity {
    private ListView fan_list;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private fanAdapter adapter = new fanAdapter(); //리스트뷰에 적용되는 보드어댑터
    private ArrayList<String> items = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fan_page);
        Intent intent = getIntent();
        fan_list = findViewById(R.id.fan_list);


        // ID에 맞는 LIST 출력하는 partㅇㅇㅇ
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://210.223.239.212:8081/web/select_cage.do";

        // 1.객체만들고 요청 주소만듦

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("이거맞냐", response);         //응답글자 수 보여짐,
                if (response.length() > 0) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String cg_serial = jsonObject.getString("cg_serial");
                            String cg_motor = jsonObject.getString("cg_motor");
                            String cg_time = jsonObject.getString("cg_time");
                            CageVO cvo = new CageVO(cg_serial, cg_motor);
                            // ㅇㅇ아무튼 ..... ,ID 바꿔보기
                            adapter.addItem(cg_serial,cg_motor);
                            Log.v("타입", cg_serial);
                            Log.v("이름", cg_motor);

                        }

                        fan_list.setAdapter(adapter);

                        adapter.notifyDataSetChanged();

                        //아이템 온클릭리스너 만들어주기


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Toast.makeText(getApplicationContext(), "??", Toast.LENGTH_SHORT).show();
                } else {
                    //로그인실패
                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                }

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

            // 보낼 데이터를 저장하는 곳 해쉬맵에 저장해서 보냄 - key,value
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                String id = loginCheck.info.getId();
//                String cg_serial = loginCheck.cage_info.getCg_serial();
//                String cg_motor = loginCheck.cage_info.getCg_motor();
//                //params.put("mb_id", id);
////                params.put("cg_serial", cg_serial);
////                params.put("cg_motor", cg_motor);
//
//                // key값은 서버에서 지정한 name과 동일하게
//
//                return params;
//            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청


        fan_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                CageVO cvo = (CageVO) adapterView.getItemAtPosition(i);


            }
        });
    }




}