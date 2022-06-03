package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

public class MemberUpdate extends AppCompatActivity {
    private TextView update_id;
    private EditText update_pw,update_nick,update_name,update_phone,update_adr;
    private Button btn_update,btn_updateCancel;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_update);


        update_id = findViewById(R.id.update_id);
        update_pw = findViewById(R.id.update_pw);
        update_nick = findViewById(R.id.update_nick);
        update_name = findViewById(R.id.update_name);
        update_phone = findViewById(R.id.update_phone);
        update_adr = findViewById(R.id.update_adr);
        btn_update = findViewById(R.id.btn_update);
        btn_updateCancel = findViewById(R.id.btn_updateCancel);

        if((loginCheck.info)!=null){
            update_id.setText(loginCheck.info.getId()+"님의 정보");

        }

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                memberUpdate();
            }
        });
        btn_updateCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Setting.class);
            }
        });

    }

    public void memberUpdate() {

        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://172.30.1.28:8089/web/updateService.do";


        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            // 응답데이터를 받아오는 곳
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response.length()+"");         //응답글자 수 보여짐,
                if(response.length() > 0) {
                    //로그인 성공
                    // 0은 로그인실패
                    String id = loginCheck.info.getId();
                    String pw = update_pw.getText().toString();
                    String nick = update_nick.getText().toString();
                    String name = update_name.getText().toString();
                    String phone = update_phone.getText().toString();
                    String adr = update_adr.getText().toString();


                    loginCheck.info= new MemberVO(id,pw,nick,name,phone,adr);
                    Intent intent = new Intent(getApplicationContext(),After_Login_Main.class);
                    //intent.putExtra("vo",vo);
                    startActivity(intent);

                    Toast.makeText(getApplicationContext(),"수정 완료!",Toast.LENGTH_SHORT).show();


                }else {
                    //로그인실패
                    Toast.makeText(getApplicationContext(), "수정 실패...", Toast.LENGTH_SHORT).show();
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
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = loginCheck.info.getId();
                String pw = update_pw.getText().toString();
                String nick = update_nick.getText().toString();
                String name = update_name.getText().toString();
                String phone = update_phone.getText().toString();
                String adr = update_adr.getText().toString();


                params.put("mb_id", id);
                params.put("mb_pw",pw);
                params.put("mb_nick",nick);
                params.put("mb_name",name);
                params.put("mb_phone",phone);
                params.put("mb_address",adr);
                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
    }



}