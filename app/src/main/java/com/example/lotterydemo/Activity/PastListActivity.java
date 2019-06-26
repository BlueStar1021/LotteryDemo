package com.example.lotterydemo.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lotterydemo.Interface.JsonActivity;
import com.example.lotterydemo.JavaClass.Lottery;
import com.example.lotterydemo.JavaClass.LotteryAdapter;
import com.example.lotterydemo.JavaClass.NetworkWatcherActivity;
import com.example.lotterydemo.JavaClass.PLSSet;
import com.example.lotterydemo.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.TreeMap;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PastListActivity extends NetworkWatcherActivity implements JsonActivity {

    int page;                                                                                      //当前已加载页数
    String lotteryKind;                                                                            //当前彩票类型
    ArrayList<Lottery> lotteries = new ArrayList<>();

    TextView title;
    Button btnRet;
    ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_list);

        title = (TextView)findViewById(R.id.activity_past_list_news_name);
        btnRet = (Button)findViewById(R.id.activity_past_list_return);
        list = (ListView)findViewById(R.id.activity_past_list_listview);



        //返回事件监听
        btnRet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PastListActivity.super.finish();
            }
        });

        //列表单击前往详情页
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(lotteryKind){
                    case "ssq":
                        Intent intentForSSQ = new Intent(PastListActivity.this, SSQDetailActivity.class);
                        intentForSSQ.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForSSQ);
                        break;
                    case "dlt":
                        Intent intentForDLT = new Intent(PastListActivity.this, DLTDetailActivity.class);
                        intentForDLT.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForDLT);
                        break;
                    case "qlc":
                        Intent intentForQLC = new Intent(PastListActivity.this, QLCDetailActivity.class);
                        intentForQLC.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForQLC);
                        break;
                    case "fcsd":
                        Intent intentForFCSD = new Intent(PastListActivity.this, FCSDDetailActivity.class);
                        intentForFCSD.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForFCSD);
                        break;
                    case "qxc":
                        Intent intentForQXC = new Intent(PastListActivity.this, QXCDetailActivity.class);
                        intentForQXC.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForQXC);
                        break;
                    case "pls":
                        Intent intentForPLS = new Intent(PastListActivity.this, PLSDetailActivity.class);
                        intentForPLS.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForPLS);
                        break;
                    case "plw":
                        Intent intentForPLW = new Intent(PastListActivity.this, PLWDetailActivity.class);
                        intentForPLW.putExtra("no",lotteries.get(position).getNo());
                        startActivity(intentForPLW);
                        break;
                }
            }
        });

        //列表下拉追加内容
        list.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch(scrollState){
                    //当不滚动时
                    case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                        //判断滚动到底部
                        if (view.getLastVisiblePosition() == (view.getCount() - 1)) {
                            //更新数据(保存当前的浏览位置)

                            updateList();

                        }
                        break;

                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        //确定加载的彩票类型
        Intent intent = getIntent();
        lotteryKind = intent.getStringExtra("lotteryKind");

        //当前已加载页数置为0
        page = 0;

        //修改title的内容为当前彩票类型
        switch(lotteryKind){
            case "ssq":
                title.setText("双色球");
                break;
            case "dlt":
                title.setText("超级大乐透");
                break;
            case "qlc":
                title.setText("七乐彩");
                break;
            case "fcsd":
                title.setText("福彩3D");
                break;
            case "qxc":
                title.setText("七星彩");
                break;
            case "pls":
                title.setText("排列三");
                break;
            case "plw":
                title.setText("排列五");
                break;
        }



        //检查网络
        if(isNetworkAvailable(PastListActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/history?key=" + getResources().getString(R.string.appkey) + "&lottery_id=" + lotteryKind + "&page_size=50&page=" + String.valueOf(page + 1));
            page++;                                                                                //当前已加载页数加1

        }

    }

    @Override
    public void getJson(final String url) {
        new Thread(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void parseJSONWithJSONObject(String responseData) {
        try {
            JSONObject outerJsonObject = new JSONObject(responseData);
            JSONObject innerJsonObject = new JSONObject(outerJsonObject.getString("result"));
            JSONArray resultList = new JSONArray(innerJsonObject.getJSONArray("lotteryResList").toString());



            ArrayList<Lottery> arrayForNewLotteries = new ArrayList<>();

            for(int i = 0 ; i < resultList.length() ; i++){
                JSONObject lottery = resultList.getJSONObject(i);



                Lottery newLottery = new Lottery();
                newLottery.setNo(lottery.getString("lottery_no"));
                newLottery.setDate(lottery.getString("lottery_date"));



                arrayForNewLotteries.add(newLottery);

            }

            TreeMap<String,Object> map = new TreeMap<>();
            map.put("array", arrayForNewLotteries);



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

                ArrayList<Lottery> extraLotteries = (ArrayList<Lottery>) map.get("array");

                for(Lottery x:extraLotteries){
                    lotteries.add(x);
                }

                //填入数据
                LotteryAdapter adapter = new LotteryAdapter(PastListActivity.this, R.layout.past_list_item, lotteries);
                list.setAdapter(adapter);

            }
        });
    }

    public void updateList(){
        //检查网络
        if(isNetworkAvailable(PastListActivity.this) == false){
            //网络不可用
            Toast.makeText(this, "网络状况不良，请连接数据后重试", Toast.LENGTH_SHORT).show();
        }else{
            //网络可用
            getJson("http://apis.juhe.cn/lottery/history?key=" + getResources().getString(R.string.appkey) + "&lottery_id=" + lotteryKind + "&page_size=50&page=" + String.valueOf(page + 1));
            page++;                                                                                //当前已加载页数加1

        }
    }


}
