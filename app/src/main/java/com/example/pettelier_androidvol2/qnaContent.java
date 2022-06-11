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

public class qnaContent extends AppCompatActivity {
    private TextView c_qna_title,c_qna_writer,c_qna_content;
    private Button btn_q_update,btn_q_del,btn_q_cmt;
    private EditText input_q_cmt;
    private ListView q_cmt_view;
    private ArrayList<String> items = new ArrayList<>();
    private QnaCmtAdapter qnaCmtAdapter = new QnaCmtAdapter(); //리스트뷰에 적용되는 코멘트 어댑터
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private String qnaSeq;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qna_content);
        Intent intent = getIntent();
        QnaBoardVO qvo = (QnaBoardVO) intent.getSerializableExtra("qvo");
        qnaSeq =  qvo.getQna_seq();
        String mb_id = qvo.getMb_id();

        c_qna_writer = findViewById(R.id.c_qna_writer);
        c_qna_title = findViewById(R.id.c_qna_title);
        c_qna_content = findViewById(R.id.c_qna_content);
        btn_q_update = findViewById(R.id.btn_q_update);
        btn_q_del = findViewById(R.id.btn_q_del);
        btn_q_cmt = findViewById(R.id.btn_q_cmt);
        input_q_cmt = findViewById(R.id.input_q_cmt);
        q_cmt_view = findViewById(R.id.q_cmt_view);

        btn_q_cmt.setVisibility(View.INVISIBLE);
        input_q_cmt.setVisibility(View.INVISIBLE);
        btn_q_update.setVisibility(View.INVISIBLE);
        btn_q_del.setVisibility(View.INVISIBLE);

        //글작성자와 로그인 아이디가 같을때만 버튼 보여줌
        if((loginCheck.info.getId()).equals(mb_id)) {
            btn_q_del.setVisibility(View.VISIBLE);
            btn_q_update.setVisibility(View.VISIBLE);
        }
        if(loginCheck.info.getId().equals("admin")){
            btn_q_cmt.setVisibility(View.VISIBLE);
            input_q_cmt.setVisibility(View.VISIBLE);


        }
        c_qna_writer.setText(qvo.getMb_id());
        c_qna_title.setText(qvo.getQna_title());
        c_qna_content.setText(qvo.getQna_content());

        btn_q_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),qnaUpdate.class);
                intent.putExtra("qvo",qvo);
                startActivity(intent);

            }
        });

        btn_q_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qnaDelete();

            }
        });

        //댓글입력 버튼
        btn_q_cmt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                qnacmtInput();
            }
        });


        // 게시글 열면 관리자의 댓글이 바로 나오게됨
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();
        // 서버에 요청할 주소
        String url = "http://192.168.43.220:8081/web/qnaCmtList.do";


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

                        // String cmt_seq = jsonObject.getString("qna_cmt_seq");
                        String qna_cmt_content = jsonObject.getString("qna_cmt_content");
                        String qna_cmt_date = jsonObject.getString("qna_cmt_date");


                        Log.v("qna_cmt_content",qna_cmt_content);

                        qnaCmtAdapter.addItem(qna_cmt_content,qna_cmt_date);

                    }

                    q_cmt_view.setAdapter(qnaCmtAdapter);

                    qnaCmtAdapter.notifyDataSetChanged();


                    //댓글 삭제 기능? 추가할수도 안할수도..
                    q_cmt_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            QnaCmtVO vo = (QnaCmtVO) adapterView.getItemAtPosition(i);
                            //Toast.makeText(getApplicationContext(), vo.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getApplicationContext(),vo.toString(),Toast.LENGTH_SHORT).show();

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

                String qna_seq = qvo.getQna_seq();
                params.put("qna_seq", qna_seq);

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);





    }

    public void qnaDelete() {
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://192.168.43.220:8081/web/qnaDelete.do";

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
                String qna_seq = qnaSeq;

                params.put("qna_seq", qna_seq);

                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청

    }



    //문의사항 댓글 서버로 보내는 메소드
    public void qnacmtInput() {
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://192.168.43.220:8081/web/qnaCmtInsert.do";

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
                String qna_cmt_content = input_q_cmt.getText().toString();

                params.put("qna_seq", qnaSeq);
                params.put("qna_cmt_content",qna_cmt_content);

                // key값은 서버에서 지정한 name과 동일하게

                return params;
            }
        };
        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청

    }


}
