package com.example.lotterydemo.Interface;

import android.view.View;

/**
 * Created by dell on 2019/5/16.
 */

public interface DynamicBGArea {

    //设置区域背景
    public void setBackground(View view, int imageId);
    /*
    *
    * 参数介绍:
    * view              需要加载背景的控件
    * imageId           背景图片Id
    *
    * */

    //子线程动态加载
    public void createNewThread(View view, int imageId);
    /*
    *
    * 方法说明:
    * 调用此方法并在此方法内开启新的子线程来执行设置区域背景的方法
    *
    * 参数介绍:
    * view              需要加载背景的控件
    * imageId           背景图片Id
    *
    * */

}
