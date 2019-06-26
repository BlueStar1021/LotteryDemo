package com.example.lotterydemo.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lotterydemo.Fragment.InfoFragment;
import com.example.lotterydemo.Fragment.MemoFragment;
import com.example.lotterydemo.Fragment.PastFragment;
import com.example.lotterydemo.Fragment.ResultFragment;
import com.example.lotterydemo.Interface.FragActivity;
import com.example.lotterydemo.R;

public class MainActivity extends AppCompatActivity implements FragActivity {

    TextView topBarText;
    Button setting;
    Button btnFrag1,btnFrag2,btnFrag3,btnFrag4;

    //检测当前fragment
    int fragmentWatcher = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        topBarText = (TextView)findViewById(R.id.activity_main_topbartext);
        setting = (Button)findViewById(R.id.activity_main_setting);
        btnFrag1 = (Button)findViewById(R.id.activity_main_btnfrag1);
        btnFrag2 = (Button)findViewById(R.id.activity_main_btnfrag2);
        btnFrag3 = (Button)findViewById(R.id.activity_main_btnfrag3);
        btnFrag4 = (Button)findViewById(R.id.activity_main_btnfrag4);

        //初始化碎片按钮
        btnFrag1.setBackgroundResource(R.drawable.btn_fragment_1_selected);
        btnFrag2.setBackgroundResource(R.drawable.btn_fragment_2_unselected);
        btnFrag3.setBackgroundResource(R.drawable.btn_fragment_3_unselected);
        btnFrag4.setBackgroundResource(R.drawable.btn_fragment_4_unselected);

        //初始化碎片内容
        replaceFragment(R.id.activity_main_fragmentarea, new InfoFragment());
        //初始化标题
        topBarText.setText("资讯");

        //碎片按钮切换动作
        btnFrag1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentWatcher != 0){
                    btnFrag1.setBackgroundResource(R.drawable.btn_fragment_1_selected);
                    btnFrag2.setBackgroundResource(R.drawable.btn_fragment_2_unselected);
                    btnFrag3.setBackgroundResource(R.drawable.btn_fragment_3_unselected);
                    btnFrag4.setBackgroundResource(R.drawable.btn_fragment_4_unselected);

                    //碎片切换
                    replaceFragment(R.id.activity_main_fragmentarea, new InfoFragment());
                    //切换标题
                    topBarText.setText("资讯");
                    fragmentWatcher = 0;
                }
            }
        });

        btnFrag2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentWatcher != 1){
                    btnFrag1.setBackgroundResource(R.drawable.btn_fragment_1_unselected);
                    btnFrag2.setBackgroundResource(R.drawable.btn_fragment_2_selected);
                    btnFrag3.setBackgroundResource(R.drawable.btn_fragment_3_unselected);
                    btnFrag4.setBackgroundResource(R.drawable.btn_fragment_4_unselected);

                    //碎片切换
                    replaceFragment(R.id.activity_main_fragmentarea, new ResultFragment());
                    //切换标题
                    topBarText.setText("开奖结果");
                    fragmentWatcher = 1;
                }
            }
        });

        btnFrag3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentWatcher != 2){
                    btnFrag1.setBackgroundResource(R.drawable.btn_fragment_1_unselected);
                    btnFrag2.setBackgroundResource(R.drawable.btn_fragment_2_unselected);
                    btnFrag3.setBackgroundResource(R.drawable.btn_fragment_3_selected);
                    btnFrag4.setBackgroundResource(R.drawable.btn_fragment_4_unselected);

                    //碎片切换
                    replaceFragment(R.id.activity_main_fragmentarea, new PastFragment());
                    //切换标题
                    topBarText.setText("往期开奖");
                    fragmentWatcher = 2;
                }
            }
        });

        btnFrag4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(fragmentWatcher != 3){
                    btnFrag1.setBackgroundResource(R.drawable.btn_fragment_1_unselected);
                    btnFrag2.setBackgroundResource(R.drawable.btn_fragment_2_unselected);
                    btnFrag3.setBackgroundResource(R.drawable.btn_fragment_3_unselected);
                    btnFrag4.setBackgroundResource(R.drawable.btn_fragment_4_selected);

                    //碎片切换
                    replaceFragment(R.id.activity_main_fragmentarea, new MemoFragment());
                    //切换标题
                    topBarText.setText("备忘录");
                    fragmentWatcher = 3;
                }
            }
        });

    }

    @Override
    public void replaceFragment(int resourceViewId, Fragment fragment) {
        //用来切换碎片
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(resourceViewId, fragment);
        transaction.commit();
    }
}
