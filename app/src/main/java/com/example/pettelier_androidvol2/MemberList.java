package com.example.pettelier_androidvol2;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

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

public class MemberList extends AppCompatActivity {

    private ListView listview; // 리스트뷰 객체
    private ArrayAdapter<String> adapter; // 리스트뷰에 적용되는 어댑터

    // 어댑터에 들어갈 데이터
    private ArrayList<String> items = new ArrayList<String>();

    // 서버통신에 필요한것들
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_list);

        // listview = findViewById(R.id.listview);

        sendRequest();

    }

    public void sendRequest(){
        // RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        // 서버에 요청할 주소
        String url = "http://192.168.43.220:8081/web/memberList.do";

        // 요청시 필요한 문자열 객체
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);

                // try catch하는법 : ctrl + alt + T
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for(int i =0; i< jsonArray.length(); i++){
                        JSONObject jo = jsonArray.getJSONObject(i);

                        //jsonObject에는 회원정보가 담겨있다
                        // 회원들의 정보를 리스트뷰에 보이게 하쇼
                        // ex)  pbk-1234-호두아빠-010-4611-5278

                        String id = jo.getString("id");
                        String pw = jo.getString("pw");
                        String nick = jo.getString("nick");
                        String name = jo.getString("name");
                        String phone = jo.getString("phone");
                        String address = jo.getString("address");

                        items.add((i+1)+"."+id+" "+pw+" "+nick+" "+name+" "+phone+" "+address);
                    }
                    adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
                    listview.setAdapter(adapter);


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            // 서버와의 연동 에러시 출력
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }){
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
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        stringRequest.setTag("main"); //구분자 (안써도됨.)
        requestQueue.add(stringRequest);
    }
}