package com.example.lotterydemo.Activity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.lotterydemo.Interface.QuickJumpActivity;
import com.example.lotterydemo.R;

import java.util.Timer;
import java.util.TimerTask;

public class WelcomeActivity extends AppCompatActivity implements QuickJumpActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        jumpToNext(0, 2500, MainActivity.class);
    }


    @Override
    public void jumpToNext(final int stillExist, int timeForJump, final Class<?> cls) {

        //设置2.5秒后从欢迎页跳转至首页
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(WelcomeActivity.this, cls);
                startActivity(intent);

                if(stillExist != 1){
                    WelcomeActivity.super.finish();                                                 //销毁
                }
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, timeForJump);                                                          //延时

    }
}
