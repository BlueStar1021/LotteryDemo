package com.example.lotterydemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotterydemo.Interface.JsonActivity;
import com.example.lotterydemo.JavaClass.NetworkWatcherActivity;
import com.example.lotterydemo.JavaClass.QLCSet;
import com.example.lotterydemo.JavaClass.QXCSet;
import com.example.lotterydemo.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class QXCResultActivity extends NetworkWatcherActivity implements JsonActivity {

    Button btnRet;

    TextView no,date,exdate,saleAmount,poolAmount;
    TextView ball_1,ball_2,ball_3,ball_4,ball_5,ball_6,ball_7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qxcresult);

        btnRet = (Button)findViewById(R.id.activity_qxcresult_return);

        no = (TextView)findViewById(R.id.activity_qxcresult_lottery_no);
        date = (TextView)findViewById(R.id.activity_qxcresult_lottery_date);
        exdate = (TextView)findViewById(R.id.activity_qxcresult_lottery_exdate);
        saleAmount = (TextView)findViewById(R.id.activity_qxcresult_lottery_sale_amount);
        poolAmount = (TextView)findViewById(R.id.activity_qxcresult_lottery_pool_amount);

        ball_1 = (TextView)findViewById(R.id.activity_qxcresult_ball_1);
        ball_2 = (TextView)findViewById(R.id.activity_qxcresult_ball_2);
        ball_3 = (TextView)findViewById(R.id.activity_qxcresult_ball_3);
        ball_4 = (TextView)findViewById(R.id.activity_qxcresult_ball_4);
        ball_5 = (TextView)findViewById(R.id.activity_qxcresult_ball_5);
        ball_6 = (TextView)findViewById(R.id.activity_qxcresult_ball_6);
        ball_7 = (TextView)findViewById(R.id.activity_qxcresult_ball_7);

        //返回事件监听
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QXCResultActivity.super.finish();
            }
        });

        //检查网络
        if(isNetworkAvailable(QXCResultActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/query?key=" + getResources().getString(R.string.appkey) + "&lottery_id=qxc");
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
            TreeMap<String,String> map = new TreeMap<>();
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
    public void showResponseOnUI(final TreeMap<String, String> map) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                no.setText(map.get("no"));
                date.setText(map.get("date"));
                exdate.setText(map.get("exdate"));
                saleAmount.setText(map.get("sale_amount"));
                poolAmount.setText(map.get("pool_amount"));

                QXCSet qxcSet = new QXCSet(map.get("res"));

                ball_1.setText(String.valueOf(qxcSet.getBalls().get(0)));
                ball_2.setText(String.valueOf(qxcSet.getBalls().get(1)));
                ball_3.setText(String.valueOf(qxcSet.getBalls().get(2)));
                ball_4.setText(String.valueOf(qxcSet.getBalls().get(3)));
                ball_5.setText(String.valueOf(qxcSet.getBalls().get(4)));
                ball_6.setText(String.valueOf(qxcSet.getBalls().get(5)));
                ball_7.setText(String.valueOf(qxcSet.getBalls().get(6)));
            }
        });
    }
}
