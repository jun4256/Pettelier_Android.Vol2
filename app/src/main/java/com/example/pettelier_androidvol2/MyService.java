package com.example.pettelier_androidvol2;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;

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

public class MyService extends Service {

    private RequestQueue requestQueue;
    private StringRequest stringRequest;
    private static String Chanel_id = "Chanel_1";
    private static String Chanel_Name = "Chanel_jb";

    // 알림 매니저 /김준범은 noti_manager
    NotificationManager Notifi_M;

    // 내가 만든 서비스 쓰레드
    Service_Thread thread;

    // 알림  // 나는142라인 notiBuilder로 선언 (알림파트에 직접)
    Notification Notifi;


    public MyService() {
    }
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
       // throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }




//    @Override
//    public void onCreate(){
//        super.onCreate();
//        // 최초 1번 생성되었을떄 실행하는 함수
//    }

    @Override   // 백그라운드에서 실행되는 동작들이 들어가는 곳
    public int onStartCommand(Intent intent, int flags, int startId){

        // 프래그먼트에선 바꿔줘야한다  getSys쪽...
        Notifi_M =(NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);

        myServiceHandler handler = new myServiceHandler();
        thread = new Service_Thread(handler);
        thread.start();
        return START_STICKY;
    }

    @Override     // 서비스가 종료될때 할 작업
    public void onDestroy(){
        thread.stopForever();
        thread = null; // 쓰레기 값 생성. 빠르게 회수
    }

    class myServiceHandler extends Handler{
        @Override
        public void handleMessage(android.os.Message msg){
            requestQueue = Volley.newRequestQueue(getApplicationContext());    // this==getApplicationContext();
            // 서버에 요청할 주소
            String url = "http://59.0.129.176:8081/web/select_cage.do";

//            // 알림 매니저 /김준범은 noti_manager
//            NotificationManager Notifi_M;
//
//            // 내가 만든 서비스 쓰레드
//            Service_Thread thread;
//
//            // 알림  // 나는142라인 notiBuilder로 선언 (알림파트에 직접)
//            Notification Notifi;



            stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                // 응답데이터를 받아오는 곳
                @Override
                public void onResponse(String response) {
                    Log.v("이거맞냐", response);         //응답글자 수 보여짐,
                    if (response.length() > 0) {

                        try {
                            JSONArray jsonArray = new JSONArray(response);   //response가 JSON타입이 아닐 수 있어서 예외처리 해주기
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                String cg_serial = jsonObject.getString("cg_serial");
                                String cg_gas = jsonObject.getString("cg_gas");

                                // ㅇㅇ아무튼 ..... ,ID 바꿔보기

                                Log.v("타입", cg_serial);
                                Log.v("가스테스터", cg_gas);
                                // i번째 gas가 true
                                if(cg_gas.equals("1")){

                                    String result = ""+cg_serial;
                                    Log.v("존나하기 싫다", result);


                                    Notifi_M = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

                                    NotificationCompat.Builder builder = null;
                                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                                        Notifi_M.createNotificationChannel(new NotificationChannel(
                                                Chanel_id, Chanel_Name, NotificationManager.IMPORTANCE_DEFAULT
                                        ));
                                        //  준범  -get apk가 안먹히면 this로 고칠 것
                                        builder = new NotificationCompat.Builder(getApplicationContext(), Chanel_id);
                                    } else{
                                        builder = new NotificationCompat.Builder(getApplicationContext());
                                    }

                                    // 환풍기 관련해서 class로 넘어가게 해주세요 %%%ㅁ%
                                    Intent intent = new Intent(getApplicationContext(), fanPage.class);
                                    PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(),101, intent,
                                            PendingIntent.FLAG_UPDATE_CURRENT);

                                    builder.setContentTitle("배변사항 검출");
                                    builder.setContentText(cg_serial+"배변검출");
                                    // 아이콘은 알아서 설정하기
                                    builder.setSmallIcon(android.R.drawable.ic_menu_view);
                                    builder.setAutoCancel(true);
                                    builder.setDefaults(Notification.DEFAULT_SOUND);
                                    builder.setContentIntent(pendingIntent);
                                    Notification noti = builder.build();

                                    // json array를 i만큼 반복하잖아? 그럼
                                    // i만큼 채널 id주면서 알림 띄워주면 된다구!!!!!
                                    // 나 진짜 천잰가?

                                    Notifi_M.notify(i, noti);
                                    Toast.makeText(getApplicationContext(), "배변 검출", Toast.LENGTH_SHORT).show();
                                }

                            }

                            //아이템 온클릭리스너 만들어주기


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
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


            };
            stringRequest.setTag("main");       //구분자 어떤클라이언트에서 요청했는지 나타냄 (중요하지않음)
            requestQueue.add(stringRequest);        //실행 요청 add에 담으면 자동요청



//            Intent intent = new Intent(MyService.this, Admin_Login_Main.class);
//            // 팬딩인텐트 this part는  FM에서 다를 수 있음 변경사항 주의
//            PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
//
//
//            // 알림 제목/내용/아이콘/페이지 이동
//            Notifi = new Notification.Builder(getApplicationContext())
//                    .setContentTitle("배변사항 Test2")
//                    .setContentText("배변검출 Test2")
//                    // 아이콘
//                    .setSmallIcon(com.google.android.material.R.drawable.abc_ic_star_black_36dp)
//                    .setContentIntent(pendingIntent)
//                    .build();

//            // 소리 추가
//            Notifi.defaults = Notification.DEFAULT_SOUND;
//
//            // 알림은 한번만 내자 ^^
//            Notifi.flags = Notification.FLAG_ONLY_ALERT_ONCE;
//
//            // 확인하면 자동으로 알림이 제거!!!
//            Notifi.flags = Notification.FLAG_AUTO_CANCEL;
//
//            Notifi_M.notify(777, Notifi);
//
//            Toast.makeText(MyService.this, "백알람?", Toast.LENGTH_SHORT).show();


        }
    }

}