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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class pet_item_click_update extends AppCompatActivity {

    private  EditText dog_age2, dog_etc2;
    private Button btn_dog_updatae2;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_item_click_update);
        dog_age2 = findViewById(R.id.dog_age2);
        dog_etc2 = findViewById(R.id.dog_etc2);
        btn_dog_updatae2 = findViewById(R.id.btn_dog_update2);





        btn_dog_updatae2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });
    }



        private void sendRequest() {
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();
            Intent intent= getIntent();
            DogVO dvo = (DogVO) intent.getSerializableExtra("dvo");
            String dd= dvo.getDog_name();





        // 서버에 요청할 주소
        String url = "http://59.0.129.176:8081/web/dog_update.do";
            // 고은 : 172.30.1.28:8089
            // 시윤 : 59.0.129.176:8081
            // 준범 : 210.223.239.212:8081
            // 진관 : 220.80.165.82:8081

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);         //응답글자 수 보여짐,
                if (response.length() > 0) {
                    //로그인 성공
                    // 0은 로그인실패
                    try {
                        JSONObject jsonObject = new JSONObject(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
                        String mb_id = jsonObject.getString("mb_id");
                        String dog_type = jsonObject.getString("dog_type");
                        String dog_name = jsonObject.getString("dog_name");
                        String dog_age = jsonObject.getString("dog_age");
                        String dog_etc = jsonObject.getString("dog_etc");


                        // MemberVO 만들어서 넘기기
                        loginCheck.dog_info = new DogVO(mb_id,dog_type,dog_name,dog_age,dog_etc);
                        Intent intent = new Intent(getApplicationContext(), pet_regi_retouch.class);
                        startActivity(intent);


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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
               // age etc mb_id  DOg NANE도 필요하네받을 것
                String id = loginCheck.info.getId();
                String dog_name = dd;
                String age = dog_age2.getText().toString();
                String etc = dog_etc2.getText().toString();

                params.put("mb_id", id);
                params.put("dog_name", dog_name);
                params.put("dog_age", age);
                params.put("dog_etc", etc);

                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
    }




}