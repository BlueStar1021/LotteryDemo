package com.example.lotterydemo.Interface;

import java.util.TreeMap;

/**
 * json请求页
 * Created by dell on 2019/5/10.
 */

public interface JsonActivity {

    //获取Json数据
    public void getJson(String url);
    /*
    *
    * 参数介绍:
    * url    提供json数据的网络接口
    *
    * */

    //解析Json数据
    public void parseJSONWithJSONObject(String responseData);
    /*
    *
    * 参数介绍:
    * responseData      从网络接口获取到的json字符串，用于执行对应的解析操作
    *
    * */

    //更新渲染UI
    public void showResponseOnUI(TreeMap<String,Object> map);
    /*
    *
    * 参数介绍:
    * data      用于更新渲染UI的新数据
    *
    * */

}
