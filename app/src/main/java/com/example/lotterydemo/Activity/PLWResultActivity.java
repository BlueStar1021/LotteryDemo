package com.example.lotterydemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotterydemo.Interface.JsonActivity;
import com.example.lotterydemo.JavaClass.NetworkWatcherActivity;
import com.example.lotterydemo.JavaClass.PLWSet;
import com.example.lotterydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PLWResultActivity extends NetworkWatcherActivity implements JsonActivity {

    Button btnRet;

    TextView no,date,exdate,saleAmount,poolAmount;
    TextView ball_1,ball_2,ball_3,ball_4,ball_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plwresult);

        btnRet = (Button)findViewById(R.id.activity_plwresult_return);

        no = (TextView)findViewById(R.id.activity_plwresult_lottery_no);
        date = (TextView)findViewById(R.id.activity_plwresult_lottery_date);
        exdate = (TextView)findViewById(R.id.activity_plwresult_lottery_exdate);
        saleAmount = (TextView)findViewById(R.id.activity_plwresult_lottery_sale_amount);
        poolAmount = (TextView)findViewById(R.id.activity_plwresult_lottery_pool_amount);

        ball_1 = (TextView)findViewById(R.id.activity_plwresult_ball_1);
        ball_2 = (TextView)findViewById(R.id.activity_plwresult_ball_2);
        ball_3 = (TextView)findViewById(R.id.activity_plwresult_ball_3);
        ball_4 = (TextView)findViewById(R.id.activity_plwresult_ball_4);
        ball_5 = (TextView)findViewById(R.id.activity_plwresult_ball_5);

        //返回事件监听
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PLWResultActivity.super.finish();
            }
        });

        //检查网络
        if(isNetworkAvailable(PLWResultActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/query?key=" + getResources().getString(R.string.appkey) + "&lottery_id=plw");
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
                no.setText((String)map.get("no"));
                date.setText((String)map.get("date"));
                exdate.setText((String)map.get("exdate"));
                saleAmount.setText((String)map.get("sale_amount"));
                poolAmount.setText((String)map.get("pool_amount"));

                PLWSet plwSet = new PLWSet((String)map.get("res"));

                ball_1.setText(String.valueOf(plwSet.getBalls().get(0)));
                ball_2.setText(String.valueOf(plwSet.getBalls().get(1)));
                ball_3.setText(String.valueOf(plwSet.getBalls().get(2)));
                ball_4.setText(String.valueOf(plwSet.getBalls().get(3)));
                ball_5.setText(String.valueOf(plwSet.getBalls().get(4)));


            }
        });
    }
}
