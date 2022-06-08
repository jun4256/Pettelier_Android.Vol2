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

public class boardContent extends AppCompatActivity {
    private TextView c_writer,c_board_title,c_board_content;
    private EditText input_cmt;
    private Button btn_cmt,btn_b_update,btn_b_del;
    private ListView cmt_view;
    private ArrayList<String> items = new ArrayList<>();
    private CommentAdapter adapter = new CommentAdapter(); //리스트뷰에 적용되는 코멘트 어댑터
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String boardSeq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_content);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");
        boardSeq =  vo.getSeq();
        String writer =vo.getId();
        Log.v("seq",vo.getSeq());


        c_writer = findViewById(R.id.c_writer);
        c_board_title = findViewById(R.id.c_board_title);
        c_board_content = findViewById(R.id.c_board_content);
        input_cmt = findViewById(R.id.input_cmt);
        cmt_view = findViewById(R.id.cmt_view);
        btn_cmt = findViewById(R.id.btn_cmt);
        btn_b_update = findViewById(R.id.btn_b_update);
        btn_b_del = findViewById(R.id.btn_b_del);

        //삭제, 수정 버튼 실행시 안보임
        btn_b_update.setVisibility(View.INVISIBLE);
        btn_b_del.setVisibility(View.INVISIBLE);

        //글작성자와 로그인 아이디가 같을때만 버튼 보여줌
        if((loginCheck.info.getId()).equals(writer)) {
            btn_b_del.setVisibility(View.VISIBLE);
            btn_b_update.setVisibility(View.VISIBLE);
        }

        c_writer.setText(vo.getId());
        c_board_title.setText(vo.getTitle());
        c_board_content.setText(vo.getContent());




        // 게시글 열면 댓글이 바로 나오게됨
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();
        // 서버에 요청할 주소
        String url = "http://172.30.1.28:8089/web/cmtList.do";


        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i< jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String cmt_seq = jsonObject.getString("cmt_seq");
                        String id = jsonObject.getString("mb_id");
                        String cmt_content = jsonObject.getString("cmt_content");
                        String cmt_date = jsonObject.getString("cmt_date");

                        Log.v("d",cmt_seq);
                        adapter.addItem(id, cmt_content, cmt_date);

                    }

                    cmt_view.setAdapter(adapter);

                    adapter.notifyDataSetChanged();


                    //댓글 삭제 기능? 추가할수도 안할수도..
                    cmt_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            CmtVO vo = (CmtVO) adapterView.getItemAtPosition(i);
                            //Toast.makeText(getApplicationContext(), vo.toString(), Toast.LENGTH_SHORT).show();
                            Toast.makeText(getApplicationContext(),vo.toString(),Toast.LENGTH_SHORT).show();

                            //Intent intent = new Intent(getApplicationContext(), boardContent.class);
                            //intent.putExtra("vo", vo);
                            //startActivity(intent);

                        }
                    });


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
                Map<String, String> params = new HashMap<String, String>();

                String board_seq = vo.getSeq();
                params.put("board_seq", board_seq);

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);




        btn_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertCmt();

            }
        });


        // 수정버튼
        btn_b_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),boardUpdate.class);
                intent.putExtra("vo",vo);
                startActivity(intent);

            }
        });


        // 삭제버튼
        btn_b_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boardDelete();

            }
        });



    }



    // 게시글 삭제
    public void boardDelete() {
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://172.30.1.28:8089/web/boardDelete.do";

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                Log.v("resultValue", response.length()+"");         //응답글자 수 보여짐,
                if(response.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(),fm_board.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "삭제 성공", Toast.LENGTH_SHORT).show();

                }else {
                    // 실패
                    Toast.makeText(getApplicationContext(), "삭제 실패", Toast.LENGTH_SHORT).show();
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

            // 보낼 데이터를 저장하는 곳 해쉬맵에 저장해서 보냄 - key,value
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String board_seq = boardSeq;

                params.put("board_seq", board_seq);

                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청

    }








    //댓글 서버로 보내는 메소드
    public void insertCmt(){

        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://172.30.1.28:8089/web/cmtInsert.do";

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                Log.v("resultValue", response.length()+"");         //응답글자 수 보여짐,
                if(response.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(),Main_Page.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "업로드 성공", Toast.LENGTH_SHORT).show();

                }else {
                    // 실패
                    Toast.makeText(getApplicationContext(), "업로드 실패", Toast.LENGTH_SHORT).show();
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

            // 보낼 데이터를 저장하는 곳 해쉬맵에 저장해서 보냄 - key,value
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();

                //String boardSeq = boardSeq;
                String cmtContent = input_cmt.getText().toString();
                String writer = loginCheck.info.getId();

                params.put("board_seq", boardSeq);
                params.put("cmt_content",cmtContent);
                params.put("mb_id",writer);

                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청

    }

}




