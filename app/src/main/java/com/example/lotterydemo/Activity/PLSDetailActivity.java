package com.example.lotterydemo.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotterydemo.Interface.JsonActivity;
import com.example.lotterydemo.JavaClass.NetworkWatcherActivity;
import com.example.lotterydemo.JavaClass.PLSSet;
import com.example.lotterydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PLSDetailActivity extends NetworkWatcherActivity implements JsonActivity {

    String lottery_no;

    Button btnRet;

    TextView no,date,exdate,saleAmount,poolAmount;
    TextView ball_1,ball_2,ball_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plsdetail);

        btnRet = (Button)findViewById(R.id.activity_plsdetail_return);

        no = (TextView)findViewById(R.id.activity_plsdetail_lottery_no);
        date = (TextView)findViewById(R.id.activity_plsdetail_lottery_date);
        exdate = (TextView)findViewById(R.id.activity_plsdetail_lottery_exdate);
        saleAmount = (TextView)findViewById(R.id.activity_plsdetail_lottery_sale_amount);
        poolAmount = (TextView)findViewById(R.id.activity_plsdetail_lottery_pool_amount);

        ball_1 = (TextView)findViewById(R.id.activity_plsdetail_ball_1);
        ball_2 = (TextView)findViewById(R.id.activity_plsdetail_ball_2);
        ball_3 = (TextView)findViewById(R.id.activity_plsdetail_ball_3);

        Intent intent = getIntent();
        lottery_no = intent.getStringExtra("no");

        //返回事件监听
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLSDetailActivity.super.finish();
            }
        });

        //检查网络
        if(isNetworkAvailable(PLSDetailActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/query?key=" + getResources().getString(R.string.appkey) + "&lottery_id=pls&lottery_no=" + lottery_no);
        }
    }

    @Override
    public void getJson(final String url) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{

                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder()
                            .url(url)
                            .build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    parseJSONWithJSONObject(responseData);
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void parseJSONWithJSONObject(String responseData) {
        try {
            JSONObject outerJsonObject = new JSONObject(responseData);
            JSONObject innerJsonObject = new JSONObject(outerJsonObject.getString("result"));
            TreeMap<String,Object> map = new TreeMap<>();
            map.put("no", innerJsonObject.getString("lottery_no"));
            map.put("res", innerJsonObject.getString("lottery_res"));
            map.put("date", innerJsonObject.getString("lottery_date"));
            map.put("exdate", innerJsonObject.getString("lottery_exdate"));
            map.put("sale_amount", innerJsonObject.getString("lottery_sale_amount"));
            map.put("pool_amount", innerJsonObject.getString("lottery_pool_amount"));

            showResponseOnUI(map);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void showResponseOnUI(final TreeMap<String, Object> map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                no.setText("第" + (String)map.get("no") + "期开奖");
                date.setText((String)map.get("date"));
                exdate.setText((String)map.get("exdate"));
                saleAmount.setText((String)map.get("sale_amount"));
                poolAmount.setText((String)map.get("pool_amount"));

                PLSSet plsSet = new PLSSet((String)map.get("res"));

                ball_1.setText(String.valueOf(plsSet.getBalls().get(0)));
                ball_2.setText(String.valueOf(plsSet.getBalls().get(1)));
                ball_3.setText(String.valueOf(plsSet.getBalls().get(2)));

            }
        });
    }
}
