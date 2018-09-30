package com.example.soyx.iperf;


import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class BandwidthThread implements Runnable {

    public final int CMD_MSG = 0x001;


    /**************native函数********************/
    public native int iperfMain(String cmd);

    Handler revHandler;
    Handler sendHandler;

    public BandwidthThread(Handler uiHandler) { sendHandler = uiHandler;}

    @SuppressLint("HandlerLeak")
    @Override
    public void run() {
        //构建当前线程Lopper
        Looper.prepare();
        revHandler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                //接收Message
                if(msg.what == CMD_MSG){
                    //命令消息
                    String cmd = msg.obj.toString();
                    iperfMain(cmd);

                }
            }
        };
        Looper.loop();
    }
}
