package com.example.lotterydemo.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotterydemo.Interface.JsonActivity;
import com.example.lotterydemo.JavaClass.NetworkWatcherActivity;
import com.example.lotterydemo.JavaClass.SSQSet;
import com.example.lotterydemo.R;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;

public class SSQResultActivity extends NetworkWatcherActivity implements JsonActivity {

    Button btnRet;

    TextView no,date,exdate,saleAmount,poolAmount;
    TextView red_1,red_2,red_3,red_4,red_5,red_6,blue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ssqresult);

        btnRet = (Button)findViewById(R.id.activity_ssqresult_return);

        no = (TextView)findViewById(R.id.activity_ssqresult_lottery_no);
        date = (TextView)findViewById(R.id.activity_ssqresult_lottery_date);
        exdate = (TextView)findViewById(R.id.activity_ssqresult_lottery_exdate);
        saleAmount = (TextView)findViewById(R.id.activity_ssqresult_lottery_sale_amount);
        poolAmount = (TextView)findViewById(R.id.activity_ssqresult_lottery_pool_amount);

        red_1 = (TextView)findViewById(R.id.activity_ssqresult_red_1);
        red_2 = (TextView)findViewById(R.id.activity_ssqresult_red_2);
        red_3 = (TextView)findViewById(R.id.activity_ssqresult_red_3);
        red_4 = (TextView)findViewById(R.id.activity_ssqresult_red_4);
        red_5 = (TextView)findViewById(R.id.activity_ssqresult_red_5);
        red_6 = (TextView)findViewById(R.id.activity_ssqresult_red_6);
        blue = (TextView)findViewById(R.id.activity_ssqresult_blue);

        //返回事件监听
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SSQResultActivity.super.finish();
            }
        });

        //检查网络
        if(isNetworkAvailable(SSQResultActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/query?key=" + getResources().getString(R.string.appkey) + "&lottery_id=ssq");
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
    public void showResponseOnUI(final TreeMap<String,Object> map) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                no.setText((String)map.get("no"));
                date.setText((String)map.get("date"));
                exdate.setText((String)map.get("exdate"));
                saleAmount.setText((String)map.get("sale_amount"));
                poolAmount.setText((String)map.get("pool_amount"));

                SSQSet ssqSet = new SSQSet((String)map.get("res"));

                red_1.setText(String.valueOf(ssqSet.getRed().get(0)));
                red_2.setText(String.valueOf(ssqSet.getRed().get(1)));
                red_3.setText(String.valueOf(ssqSet.getRed().get(2)));
                red_4.setText(String.valueOf(ssqSet.getRed().get(3)));
                red_5.setText(String.valueOf(ssqSet.getRed().get(4)));
                red_6.setText(String.valueOf(ssqSet.getRed().get(5)));
                blue.setText(String.valueOf(ssqSet.getBlue()));
            }
        });






    }
}
