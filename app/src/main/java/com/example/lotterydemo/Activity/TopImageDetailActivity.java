package com.example.lotterydemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lotterydemo.Interface.DynamicBGArea;
import com.example.lotterydemo.R;

public class TopImageDetailActivity extends AppCompatActivity implements DynamicBGArea {

    //控件集
    Button btnReturn;               //顶栏中的返回按钮
    TextView textarea;              //页面唯一编辑区域

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_image_detail);

        //绑定控件
        btnReturn = (Button)findViewById(R.id.activity_top_image_detail_return);
        textarea = (TextView)findViewById(R.id.activity_top_image_detail_textarea);

        createNewThread(textarea, R.drawable.singletext_bg);

        //动作监听
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TopImageDetailActivity.super.finish();
            }
        });

    }


    @Override
    public void setBackground(View view, int imageId) {
        view.setBackgroundResource(imageId);
    }

    @Override
    public void createNewThread(final View view, final int imageId) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                setBackground(view, imageId);
            }
        });
    }
}
