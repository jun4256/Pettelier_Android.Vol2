package com.example.pettelier_androidvol2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

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

public class login extends AppCompatActivity {
    private EditText login_id,login_pw;
    private Button btn_login;
    private TextView tv_find,tv_join;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private FragmentManager fm;
    private Real_Main RM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_id = findViewById(R.id.login_id);
        login_pw = findViewById(R.id.login_pw);
        btn_login = findViewById(R.id.btn_login);
        tv_join = findViewById(R.id.tv_join);
        tv_find = findViewById(R.id.tv_find);
        Intent intent = getIntent();


        tv_join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),join.class);
                startActivity(intent);
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendRequest();
            }
        });

        tv_find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),find_id.class);
                startActivity(intent);
            }
        });
    }

    private void sendRequest() {
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://210.223.239.212:8081/web/andLogin.do";

        // 고은 :  172.30.1.28:8089
        // 시윤 : 59.0.129.176:8081
        // 준범 : 210.223.239.212:8081
        // 진관 : 220.80.165.82:8081

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response.length()+"");         //응답글자 수 보여짐,
                if(response.length() > 0) {
                    //로그인 성공
                    // 0은 로그인실패
                    try {
                        JSONObject jsonObject = new JSONObject(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
                        String id = jsonObject.getString("mb_id");
                        String pw = jsonObject.getString("mb_pw");
                        String nick = jsonObject.getString("mb_nick");
                        String name = jsonObject.getString("mb_name");
                        String phone = jsonObject.getString("mb_phone");
                        String address = jsonObject.getString("mb_address");
                        String joindate = jsonObject.getString("mb_joindate");
                        String type = jsonObject.getString("mb_type");

                        //로그인 성공시 After_Login_Main 으로 이동,
                        // MemberVO 만들어서 넘기기
                        //MemberVO vo = new MemberVO(id,pw,nick,name,phone,address,joindate,type);
                        loginCheck.info= new MemberVO(id,pw,nick,name,phone,address,joindate,type);
                        Log.v("check",loginCheck.info.getId());
                        if((loginCheck.info.getId()).equals("admin")){
                            Intent intent = new Intent(getApplicationContext(),Admin_Login_Main.class);
                            startActivity(intent);
                        }

                        else {
//                            fm.beginTransaction().replace(R.id.frame,RM).commit();
                            Intent intent = new Intent(getApplicationContext(), Main_Page.class);
                            //intent.putExtra("response", response);
                            startActivity(intent);
                            //Intent intent = new Intent(getApplicationContext(), Real_Main.class);

                            //startActivity(intent);
                            Toast.makeText(login.this, "로그인성공", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }else {
                    //로그인실패
                    Toast.makeText(login.this, "로그인실패", Toast.LENGTH_SHORT).show();
                }

            }
        }, new Response.ErrorListener(){
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

            // 보낼 데이터를 저장하는 곳 해쉬맵에 저장해서 보냄 - key,value
            @Override
            protected Map <String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = login_id.getText().toString();
                String pw = login_pw.getText().toString();


                params.put("mb_id", id);
                params.put("mb_pw",pw);
                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
    }
}