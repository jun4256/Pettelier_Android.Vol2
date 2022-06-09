package com.example.pettelier_androidvol2;

import static android.content.Context.NOTIFICATION_SERVICE;



import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

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

public class Admin_Login_Main extends Fragment {
    Button btn_add_cage, btn_admin_moniter, btn_admin_feed, btn_admin_fan_control;

    // 알람 객체 생성
    NotificationManager noti_manager;
    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private static String Chanel_id = "Chanel_1";
    private static String Chanel_Name = "Chanel_jb";
    private FragmentManager fm;
    private Admin_Login_Main home;
    int count = 1;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment = inflater.inflate(R.layout.activity_admin_login_main,container,false);
        btn_add_cage = fragment.findViewById(R.id.btn_add_cage);
        btn_admin_moniter = fragment.findViewById(R.id.btn_admin_moniter);
        btn_admin_feed = fragment.findViewById(R.id.btn_admin_feed);
        btn_admin_fan_control =fragment.findViewById(R.id.btn_admin_fan_control);
        getActivity().startService(new Intent(getContext(),MyService.class));
        // onCreate에 알림기능 ...추가!
        //showNoti1();

        btn_add_cage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),MyService.class);
                startActivity(intent);
            }
        });

        btn_admin_moniter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),Pet_register.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        btn_admin_feed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),feed_angle.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        btn_admin_fan_control.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(),fanPage.class);
                startActivity(intent);     // 나중에 parse uri해주셈
            }
        });

        return fragment;
    }

    // 알림기능 @
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public void showNoti1() {
//        requestQueue = Volley.newRequestQueue(getContext());    // this==getApplicationContext();
//        // 서버에 요청할 주소
//        String url = "http://210.223.239.212:8081/web/select_cage.do";
//
//
//
//        stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            // 응답데이터를 받아오는 곳
//            @Override
//            public void onResponse(String response) {
//                Log.v("이거맞냐", response);         //응답글자 수 보여짐,
//                if (response.length() > 0) {
//
//                    try {
//                        JSONArray jsonArray = new JSONArray(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
//                        for (int i = 0; i < jsonArray.length(); i++) {
//                            JSONObject jsonObject = jsonArray.getJSONObject(i);
//                            String cg_serial = jsonObject.getString("cg_serial");
//                            String cg_gas = jsonObject.getString("cg_gas");
//
//                            // ㅇㅇ아무튼 ..... ,ID 바꿔보기
//
//                            Log.v("타입", cg_serial);
//                            Log.v("가스테스터", cg_gas);
//                            // i번째 gas가 true
//                           if(cg_gas.equals("True")){
//
//                               String result = ""+cg_serial;
//                               Log.v("존나하기 싫다", result);
//
//
//                               noti_manager = (NotificationManager) getActivity().getSystemService(NOTIFICATION_SERVICE);
//
//                               NotificationCompat.Builder builder = null;
//                               if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//                                   noti_manager.createNotificationChannel(new NotificationChannel(
//                                           Chanel_id, Chanel_Name, NotificationManager.IMPORTANCE_DEFAULT
//                                   ));
//                                   //  준범  -get apk가 안먹히면 this로 고칠 것
//                                   builder = new NotificationCompat.Builder(getContext(), Chanel_id);
//                               } else{
//                                   builder = new NotificationCompat.Builder(getContext());
//                               }
//
//                               // 환풍기 관련해서 class로 넘어가게 해주세요 %%%ㅁ%
//                               Intent intent = new Intent(getContext(), fanPage.class);
//                               PendingIntent pendingIntent = PendingIntent.getActivity(getContext(),101, intent,
//                                       PendingIntent.FLAG_UPDATE_CURRENT);
//
//                               builder.setContentTitle("배변사항 검출");
//                               builder.setContentText(cg_serial+"배변검출");
//                               // 아이콘은 알아서 설정하기
//                               builder.setSmallIcon(android.R.drawable.ic_menu_view);
//                               builder.setAutoCancel(true);
//                               builder.setContentIntent(pendingIntent);
//                               Notification noti = builder.build();
//
//                                // json array를 i만큼 반복하잖아? 그럼
//                               // i만큼 채널 id주면서 알림 띄워주면 된다구!!!!!
//                               // 나 진짜 천잰가?
//                               noti_manager.notify(i, noti);
//
//
//
//
//
//                           }
//
//                        }
//
//                        //아이템 온클릭리스너 만들어주기
//
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                    Toast.makeText(getContext(), "??", Toast.LENGTH_SHORT).show();
//                } else {
//                    //로그인실패
//                    Toast.makeText(getContext(), "fail", Toast.LENGTH_SHORT).show();
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
//
//        };
//        stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
//        requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청
//
//    }
    }


