package com.example.lotterydemo.Interface;

import android.app.Activity;

/**
 * 欢迎页
 * Created by dell on 2019/5/10.
 */

public interface QuickJumpActivity {

    //跳转函数
    public void jumpToNext(int stillExist, int timeForJump, Class<?> cls);
    /*
    *
    * 参数介绍:
    * stillExist    为1则Activity仍存在,否则销毁此Activity使其不再可见
    * timeForJump   设置跳转延时，以毫秒为单位
    * cls           需要跳转的目标Activity.class
    *
    * */

}
