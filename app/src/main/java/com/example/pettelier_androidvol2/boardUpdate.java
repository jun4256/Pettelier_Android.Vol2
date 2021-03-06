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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class boardUpdate extends AppCompatActivity {

    private Button btn_up_Board_cancel, btn_Board_update;
    private EditText up_board_title,up_board_content;
    private TextView up_writer;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    String seq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board_update);
        Intent intent = getIntent();
        BoardVO vo = (BoardVO) intent.getSerializableExtra("vo");

        seq = vo.getSeq();


        btn_Board_update = findViewById(R.id.btn_Board_update);
        btn_up_Board_cancel = findViewById(R.id.btn_up_Board_cancel);
        up_board_title = findViewById(R.id.up_board_title);
        up_board_content = findViewById(R.id.up_board_content);
        up_writer = findViewById(R.id.up_writer);
        if((loginCheck.info)!=null){
            up_writer.setText(loginCheck.info.getId());
        }

        up_board_content.setText(vo.getContent());
        up_board_title.setText(vo.getTitle());

        btn_Board_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendBoardUpdate();
            }
        });

        btn_up_Board_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




    }

    public void sendBoardUpdate() {
        //RequestQueue ?????? ??????
        requestQueue = Volley.newRequestQueue(this);    // this==getApplicationContext();

        // ????????? ????????? ??????
        String url = "http://59.0.129.176:8081/web/boardUpdate.do";

        // 1.??????????????? ?????? ????????????

        // ????????? ????????? ????????? ?????? ??????  ????????????  4???(????????????(get,post),??????url??????, new ?????????(???????????????)-???????????????????????? ?????????)
        stringRequest = new StringRequest(Request.Method.POST, url, new com.android.volley.Response.Listener<String>(){
            // ?????????????????? ???????????? ???
            // ????????? ????????? ???????????? ??? - ????????? ???????????? ??????????????? ?????????????????????
            @Override
            public void onResponse(String response) {
                Log.v("resultValue",response);
                Log.v("resultValue", response.length()+"");         //???????????? ??? ?????????,
                if(response.length() > 0) {
                    Intent intent = new Intent(getApplicationContext(),Main_Page.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), " ?????? ??????", Toast.LENGTH_SHORT).show();

                }else {
                    // ??????
                    Toast.makeText(getApplicationContext(), "?????? ??????", Toast.LENGTH_SHORT).show();
                }

            }
        }, new com.android.volley.Response.ErrorListener() {
            // ???????????? ?????? ????????? ??????
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        }){
            @Override //response??? UTF8??? ??????????????? ????????????
            protected com.android.volley.Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    String utf8String = new String(response.data, "UTF-8");
                    return com.android.volley.Response.success(utf8String, HttpHeaderParser.parseCacheHeaders(response));
                } catch (UnsupportedEncodingException e) {
                    // log error
                    return com.android.volley.Response.error(new ParseError(e));
                } catch (Exception e) {
                    // log error
                    return Response.error(new ParseError(e));
                }
            }

            // ?????? ???????????? ???????????? ??? ???????????? ???????????? ?????? - key,value
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String title = up_board_title.getText().toString();
                String content = up_board_content.getText().toString();
                String board_seq = seq;

                params.put("board_title", title);
                params.put("board_content",content);
                params.put("board_seq",board_seq);
                // key?????? ???????????? ????????? name??? ????????????

                return params;
            }
        };
        stringRequest.setTag("main");       //????????? ??????????????????????????? ??????????????? ????????? (??????????????????)
        requestQueue.add(stringRequest);        //?????? ?????? add??? ????????? ????????????

    }
}