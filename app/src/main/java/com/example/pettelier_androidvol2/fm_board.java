package com.example.pettelier_androidvol2;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class fm_board extends Fragment {

    private TextView tv_board;
    private String[] board_drop = {"정보공유", "문의"};
    private ListView board_list;
    private ArrayList<QnaBoardVO> q_items = new ArrayList<>();    //어댑터에 들어갈 데이터
    private ArrayList<BoardVO> b_items = new ArrayList<>();    //어댑터에 들어갈 데이터

    private BoardAdapter adapter = new BoardAdapter(); //리스트뷰에 적용되는 보드어댑터
    private QnaAdapter qnaAdapter = new QnaAdapter(); //리스트뷰에 적용되는 보드어댑터
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private Button btn_write, btn_write2;
    private FragmentManager fm;


    @Override
    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View fragment = inflater.inflate(R.layout.board, container, false);
        //객체 찾아옴
        tv_board = fragment.findViewById(R.id.tv_board);
        board_list = fragment.findViewById(R.id.board_list);
        btn_write = fragment.findViewById(R.id.btn_write);
        btn_write2 = fragment.findViewById(R.id.btn_write2);
        fm = getActivity().getSupportFragmentManager();
        btn_write2.setVisibility(View.INVISIBLE);

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), boardWrite.class);
                startActivity(intent);
            }
        });

        btn_write2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), qnaBoardWrite.class);
                startActivity(intent);
            }
        });


        //spinner 객체 생성 (드롭다운)
        Spinner spinner = fragment.findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_dropdown_item_1line, board_drop);

        adapter.setDropDownViewResource((android.R.layout.simple_dropdown_item_1line));
        spinner.setAdapter(adapter);


        //드롭다운 선택시 텍스트뷰에 나타남
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                tv_board.setText(board_drop[i]);

                    if (board_drop[i].equals("정보공유")) {
                        getBoardData();

                    } else {
                        getQnA_BoardData();
                        btn_write.setVisibility(View.INVISIBLE);
                        btn_write2.setVisibility(View.VISIBLE);
                    }

                // Log.v("i",board_drop[i]);        텍스트로 찍힘

            }


            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                tv_board.setText("");

            }


        });


        return fragment;


    }


    // 문의게시판 리스트뷰
    public void getQnA_BoardData() {
        qnaAdapter.clear();

        qnaAdapter.notifyDataSetChanged();
        //RequestQueue 객체 생성
        requestQueue = Volley.newRequestQueue(getContext());    // this==getApplicationContext();

        // 서버에 요청할 주소
        String url = "http://59.0.129.176:8081/web/qnaList.do";

        // 1.객체만들고 요청 주소만듦

        // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            // 응답데이터를 받아오는 곳
            // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
            @Override
            public void onResponse(String response) {
                Log.v("resultValue", response);
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String qna_seq = jsonObject.getString("qna_seq");
                        String mb_id = jsonObject.getString("mb_id");
                        String qna_title = jsonObject.getString("qna_title");
                        String qna_content = jsonObject.getString("qna_content");
                        String qna_date = jsonObject.getString("qna_date");


                        //QnaBoardVO qvo = new QnaBoardVO(qna_seq,mb_id,qna_title,qna_content,qna_date);
                        //q_items.add(qvo);

                        qnaAdapter.addItem(qna_seq, mb_id, qna_title, qna_content, qna_date);

                    }


                    board_list.setAdapter(qnaAdapter);

                    qnaAdapter.notifyDataSetChanged();

                    board_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            QnaBoardVO qvo = (QnaBoardVO) adapterView.getItemAtPosition(i);
                            //Toast.makeText(getApplicationContext(), vo.toString(), Toast.LENGTH_SHORT).show();
                            //Toast.makeText(getContext(), qvo.toString(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getContext(), qnaContent.class);
                            intent.putExtra("qvo", qvo);
                            startActivity(intent);

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

            // 보낼 데이터를 저장하는 곳
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                // String c_manager_id = SharedPreference.getAttribute(getApplicationContext(), "m_id");
                // params.put("c_manager_id", c_manager_id);

                return params;
            }
        };
        stringRequest.setTag("main");
        requestQueue.add(stringRequest);
    }


    public void getBoardData() {
        adapter.clear();

            //RequestQueue 객체 생성
            requestQueue = Volley.newRequestQueue(getContext());    // this==getApplicationContext();

            // 서버에 요청할 주소
            String url = "http://59.0.129.176:8081/web/boardList.do";

            // 1.객체만들고 요청 주소만듦

            // 요청시 필요한 문자열 객체 생성  매개변수  4개(통신방식(get,post),요청url주소, new 리스너(익명클래스)-응답시필요한부분 작성함)
            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                // 응답데이터를 받아오는 곳
                // 응답시 데이터 받아오는 곳 - 통신이 잘됐다면 로그캣에서 확인하게출력함
                @Override
                public void onResponse(String response) {
                    Log.v("resultValue", response);
                    try {
                        JSONArray jsonArray = new JSONArray(response);
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject jsonObject = jsonArray.getJSONObject(i);

                            String seq = jsonObject.getString("board_seq");
                            String id = jsonObject.getString("mb_id");
                            String title = jsonObject.getString("board_title");
                            String content = jsonObject.getString("board_content");
                            //String viewcount = jsonObject.getString("board_viewcount");
                            String date = jsonObject.getString("board_date");

                            Log.v("d", seq);
                            adapter.addItem(seq, id, title, content, date);

                        }

                            board_list.setAdapter(adapter);

                            adapter.notifyDataSetChanged();


                        board_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                BoardVO vo = (BoardVO) adapterView.getItemAtPosition(i);
                                //Toast.makeText(getApplicationContext(), vo.toString(), Toast.LENGTH_SHORT).show();
                                //Toast.makeText(getContext(),vo.toString(),Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getContext(), boardContent.class);
                                intent.putExtra("vo", vo);
                                startActivity(intent);

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

                // 보낼 데이터를 저장하는 곳
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();

                    // String c_manager_id = SharedPreference.getAttribute(getApplicationContext(), "m_id");
                    // params.put("c_manager_id", c_manager_id);

                    return params;
                }
            };
            stringRequest.setTag("main");
            requestQueue.add(stringRequest);

    }
}
