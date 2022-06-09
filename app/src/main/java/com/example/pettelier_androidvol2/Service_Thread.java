package com.example.pettelier_androidvol2;


import android.os.Handler;
import android.os.Message;
public class Service_Thread extends Thread{
    Handler handler;
    boolean isRun = true;


    public Service_Thread(Handler handler){
        this.handler = handler;
    }

    public void stopForever(){
        synchronized (this){
            this.isRun = false;
        }
    }

    public void run() {
        // 반복적으로 수행할 작업 한다!
        while(isRun){
            handler.sendEmptyMessage(0);
            try{
                Thread.sleep(10000); // 10초 쉰다
            }catch (Exception e) {}
        }
    }

}
