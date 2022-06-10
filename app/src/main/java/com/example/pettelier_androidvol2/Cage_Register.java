package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class Cage_Register extends AppCompatActivity {
    Button btn_regist;
    EditText edt_date, edt_serial;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cage_register);
        edt_date = findViewById(R.id.edt_regi_name);
        edt_serial= findViewById(R.id.edt_regi_type);
        btn_regist = findViewById(R.id.btn_regist);

       btn_regist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               ss();
           }
       });



    }


    public void ss() {

        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://59.0.129.176:8081/web/Register.do";
        // 고은 : 218.149.140.51:8089
        // 시윤 : 59.0.129.176:8081
        // 준범 : 210.223.239.212:8081
        // 진관 : 220.80.165.82:8081

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                Log.v("resultValue", response.length() + "");         //응답글자 수 보여짐,
                if (response.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(), Main_Page.class);
                    startActivity(intent);


                } else {
                    // 실패
                    Toast.makeText(getApplicationContext(), "등록 실패", Toast.LENGTH_SHORT).show();
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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String regi_date = edt_date.getText().toString();
                String regi_serial = edt_serial.getText().toString();
                String cg_state = loginCheck.info.getId();
                params.put("cg_state",cg_state);
                params.put("cg_serial", regi_serial);
                params.put("cg_date", regi_date);
                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청

    }




}