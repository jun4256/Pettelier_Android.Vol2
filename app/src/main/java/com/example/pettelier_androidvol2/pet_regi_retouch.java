package com.example.pettelier_androidvol2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class pet_regi_retouch extends AppCompatActivity {
    private ArrayList<String> items = new ArrayList<String>();
    private ListView listview;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private petAdapter adapter = new petAdapter(); //리스트뷰에 적용되는 보드어댑터
    private TextView tv_mb, tv_type, tv_dog_name, tv_etc;

    private int cnt = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_regi_retouch);
        Intent intent =getIntent();
        listview = findViewById(R.id.listview);
        tv_mb = findViewById(R.id.tv_mb);
        tv_type = findViewById(R.id.tv_type);
        tv_dog_name = findViewById(R.id.tv_dog_name);
        tv_etc = findViewById(R.id.tv_etc);

        // 개 정보 수정 버튼
//        btn_dog_update = findViewById(R.id.btn_dog_update);
//        btn_dog_update.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                sendRequest();
//            }
//        });



        // ID에 맞는 LIST 출력하는 partㅇㅇㅇ
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://210.223.239.212:8081/web/dog_select.do";
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
                Log.v("123", response);         //응답글자 수 보여짐,
                if (response.length() > 0) {

                    try {
                        JSONArray jsonArray = new JSONArray(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
                        for(int i=0; i<jsonArray.length(); i++){
                            JSONObject jsonObject = jsonArray.getJSONObject(i);
                            String id = jsonObject.getString("mb_id");
                            String dog_type = jsonObject.getString("dog_type");
                            String dog_name = jsonObject.getString("dog_name");
                            String dog_age = jsonObject.getString("dog_age");
                            String dog_etc = jsonObject.getString("dog_etc");

                            //  어댑터!!!!!!!!!!
                            // ㅇㅇ아무튼 ..... ,ID 바꿔보기

                            adapter.addItem(dog_name,dog_type,dog_age);
                            Log.v("id", id);
                            Log.v("타입", dog_type);
                            Log.v("이름", dog_name);
                            Log.v("나이", dog_age);
                            Log.v("기타", dog_etc);


                        }

                        listview.setAdapter(adapter);

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
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String id = loginCheck.info.getId();
                params.put("mb_id", id);


                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                DogVO dvo = (DogVO) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getApplicationContext(), pet_item_click_update.class);
                intent.putExtra("dvo", dvo);
                startActivity(intent);
            }
        });

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                DogVO dvo = (DogVO) adapterView.getItemAtPosition(i);
                items.remove(i);
                Toast.makeText(getApplication(), "정보가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                return true; // true로 하면 onclick과 longclick이 동시 실행 안됨
                // false로 하면 동시 실행될 수 있음 (안 좋음)
            }
        });

    }





////  onCreate 종료














//    private void sendRequest() {
//        //RequestQueue 객체 생성
//        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();
//
//        // 서버에 요청할 주소
//        String url = "http://210.223.239.212:8081/web/dog_update.do";
//
//        // 1.객체만들고 요청 주소만듦
//
//        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            // 응답데이터를 받아오는 곳
//            @Override
//            public void onResponse(String response) {
//                Log.v("resultValue", response);         //응답글자 수 보여짐,
//                if (response.length() > 0) {
//                    //로그인 성공
//                    // 0은 로그인실패
//                    try {
//                        JSONObject jsonObject = new JSONObject(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
//                        String mb_id = jsonObject.getString("mb_id");
//                        String dog_type = jsonObject.getString("dog_type");
//                        String dog_name = jsonObject.getString("dog_name");
//                        String dog_age = jsonObject.getString("dog_age");
//                        String dog_etc = jsonObject.getString("dog_etc");
//
//
//                        // MemberVO 만들어서 넘기기
//                        loginCheck.dog_info = new DogVO(mb_id,dog_name, dog_type, dog_age, dog_etc);
//                        Intent intent = new Intent(getApplicationContext(), After_Login_Main.class);
//                        startActivity(intent);
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(getApplicationContext(), "??", Toast.LENGTH_SHORT).show();
//                } else {
//                    //로그인실패
//                    Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            // 서버와의 연동 에러시 출력
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                error.printStackTrace();
//            }
//        }) {
//            @Override //response를 UTF8로 변경해주는 소스코드
//            protected Response<String> parseNetworkResponse(NetworkResponse response) {
//                try {
//                    String utf8String = new String(response.data, "UTF-8");
//                    return Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
//                } catch (UnsupportedEncodingException e) {
//                    // log error
//                    return Response.error(new ParseError(e));
//                } catch (Exception e) {
//                    // log error
//                    return Response.error(new ParseError(e));
//                }
//            }
//
//            // 보낼 데이터를 저장하는 곳 해쉬맵에 저장해서 보냄 - key,value
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> params = new HashMap<>();
//                //String id = login_id.getText().toString();
//                String id = loginCheck.info.getId();
//                String name = dog_name.getText().toString();
//                String type = dog_type.getText().toString();
//                String age = dog_age.getText().toString();
//                String etc = dog_etc.getText().toString();
//
//                params.put("mb_id", id);
//                params.put("dog_name", name);
//                params.put("dog_type", type);
//                params.put("dog_age", age);
//                params.put("dog_etc", etc);
//
//                // key값은 서버에서 지정한 name과 동일하게
//
//                return params;
//            }
//        };
//        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
//        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
//    }
}


