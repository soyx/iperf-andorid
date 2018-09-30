package com.example.soyx.iperf;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
        System.loadLibrary("iperf-src");
        System.loadLibrary("iperf-lib");
    }

    Handler handler;

    Button startBtn;
    EditText editText;

    BandwidthThread bandwidthThread;



    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){

            }
        };

        bandwidthThread = new BandwidthThread(handler);
        new Thread(bandwidthThread).start();

        startBtn = findViewById(R.id.button);
        editText = findViewById(R.id.editText);
        //editText.setText("iperf -c 192.168.137.4 -i 1 -u -b 1000M");
        editText.setText("iperf -s -u -i 1");


        startBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startBtn.setEnabled(false);

                Message msg = new Message();
                msg.obj = editText.getText().toString();
                //msg.obj = "iperf -c 192.168.137.1 -i 1 -u -b 1000M";
                //msg.obj = "iperf -s -i 1";
                msg.what = bandwidthThread.CMD_MSG;

                bandwidthThread.revHandler.sendMessage(msg);
                startBtn.setEnabled(true);
                //iperfMain("iperf -c 192.168.137.5");
            }
        });

    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native int iperfMain(String cmd);
}
