package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class new_pw_reset extends AppCompatActivity {

    private EditText edt_newpw, edt_pwck;
    private Button btn_new_pwrs;

    private RequestQueue requestQueue;
    private StringRequest stringRequest;


    private String mb_id;  // 변경할 아이디 변수 선언


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_pw_reset);

        Intent intent = getIntent();
        mb_id = intent.getStringExtra("mb_id");
        edt_newpw = findViewById(R.id.edt_newpw);
        edt_pwck = findViewById(R.id.edt_pwck);

        btn_new_pwrs = findViewById(R.id.btn_new_pwrs);
        btn_new_pwrs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newp = edt_newpw.getText().toString().trim();
                String pwck = edt_pwck.getText().toString().trim();


                if(newp.length()==0){
                    Toast.makeText(new_pw_reset.this, "변경할 비밀번호를 입력하세요", Toast.LENGTH_SHORT).show();
                    edt_newpw.requestFocus();
                    return;
                }

                if(pwck.length()==0){
                    Toast.makeText(new_pw_reset.this, "비밀번호 확인을 위해 한번 더 입력하세요", Toast.LENGTH_SHORT).show();
                    edt_pwck.requestFocus();
                    return;
                }
                sendRequest();

                Intent intent = new Intent(getApplicationContext(),login.class);
                startActivity(intent);

            }
        });
    }
    private void sendRequest(){
        requestQueue = Volley.newRequestQueue(this);

        String url = "http://59.0.129.176:8081/web/new_pwrs.do";

        // 고은 : 218.149.140.51:8089
        // 시윤 : 59.0.129.176:8081
        // 준범 : 210.223.239.212:8081
        // 진관 : 220.80.165.82:8081

        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.length() > 0) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        String id = jsonObject.getString("mb_id");
                        String pw = jsonObject.getString("mb_pw");
                        String nick = jsonObject.getString("mb_nick");
                        String name = jsonObject.getString("mb_name");
                        String phone = jsonObject.getString("mb_phone");
                        String address = jsonObject.getString("mb_address");
                        String joindate = jsonObject.getString("mb_joindate");
                        String type = jsonObject.getString("mb_type");

                        loginCheck.info= new MemberVO(name,pw,nick,id,phone,address,joindate,type);
                        Intent intent = new Intent(getApplicationContext(),new_pw_reset.class);
                        startActivity(intent);


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {

                }
            }
        }, new Response.ErrorListener(){
            public void onErrorResponse(VolleyError error){
                error.printStackTrace();
            }
    }){
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

                String newp = edt_newpw.getText().toString().trim();
                String pwck = edt_pwck.getText().toString().trim();

                params.put("mb_id",mb_id ); // 콘솔창 출력& 변경된 비밀번호에 맞는 아이디 값을 보내주기 위한 메소드

                if(newp.equals(pwck))
                {
                    params.put("mb_pw", newp);
                }

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
    }
}