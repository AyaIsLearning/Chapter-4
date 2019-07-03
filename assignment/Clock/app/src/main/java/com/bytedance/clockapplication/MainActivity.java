package com.bytedance.clockapplication;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.bytedance.clockapplication.widget.Clock;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private View mRootView;
    private Clock mClockView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRootView = findViewById(R.id.root);
        mClockView = findViewById(R.id.clock);

        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mClockView.setShowAnalog(!mClockView.isShowAnalog());
            }
        });

///        timer.schedule(timerTask,0,1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    long start = System.currentTimeMillis();
                    try {
                        Message msg = new Message();
                        msg.what = 1;
                        mHandler.sendMessage(msg);
                        Thread.sleep(1000);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                   /* while((System.currentTimeMillis()-start)<=1000){
                        try {
                            Thread.sleep(2);
                        }catch (Exception e){}
                    }*/
                }
            }
        }).start();

    }


    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 1){
                mClockView.setShowAnalog(mClockView.isShowAnalog());
            }
            super.handleMessage(msg);
        }
    };

    /*Timer timer = new Timer();
    TimerTask timerTask = new TimerTask() {
        @Override
        public void run() {
            Message message = new Message();
            message.what = 1;
            mHandler.sendMessage(message);
        }
    };*/
}
