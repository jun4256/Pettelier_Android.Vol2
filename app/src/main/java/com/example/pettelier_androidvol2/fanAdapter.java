package com.example.pettelier_androidvol2;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class fanAdapter extends BaseAdapter {
    private RequestQueue requestQueue;
    private StringRequest stringRequest;

    private ArrayList<CageVO> items = new ArrayList<CageVO>();
    public void addItem(String cg_serial, String cg_motor, int i) {
        CageVO cvo = new CageVO(cg_serial, cg_motor,i);
        items.add(cvo);
    }


    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // R.layout.보드리스트 이런식으로 수정해야함  // d아아ㅏㅇ
            view = inflater.inflate(R.layout.fan_custom_page, viewGroup, false);
        }


        TextView ctv_cage_serial = view.findViewById(R.id.ctv_cage_serial);
        Button btn_fan_state = view.findViewById(R.id.btn_fan_state);
        CageVO cvo = items.get(i);
        ctv_cage_serial.setText(cvo.getCg_serial());
        btn_fan_state.setText(cvo.getCg_motor());

      btn_fan_state.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              sendRequest();
              if(cvo.getCg_motor().equals("ON")){
                  cvo.setCg_motor("OFF");
                  btn_fan_state.setText("OFF");
              }else{
                  cvo.setCg_motor("ON");
                  btn_fan_state.setText("ON");
              }
          }

          private void sendRequest() {
              //RequestQueue 객체 생성
              requestQueue = Volley.newRequestQueue(context.getApplicationContext());    // this==getApplicationContext();


              // 서버에 요청할 주소
              String url = "http://210.223.239.212:8081/web/fanControl.do";
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
                              String cg_motor = jsonObject.getString("cg_motor");
                              String cg_serial = jsonObject.getString("cg_serial");




                              // MemberVO 만들어서 넘기기
                              loginCheck.cage_info = new CageVO(cg_serial,cg_motor,1);


                          } catch (JSONException e) {
                              e.printStackTrace();
                          }
                      } else {
                          //로그인실패
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
                      String fan = btn_fan_state.getText().toString();
                      if(fan.equals("OFF")){
                          fan="ON";
                      } else {
                          fan = "OFF";
                      }
                      params.put("fan", fan);


                      return params;
                  }
              };
              stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
              requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
          }



      });



      //온클릭 리스너 종료









        return view;
    }
}
