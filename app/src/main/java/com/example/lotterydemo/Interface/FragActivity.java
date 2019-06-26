package com.example.lotterydemo.Interface;

import android.support.v4.app.Fragment;

/**
 * 碎片切换页
 * Created by dell on 2019/5/15.
 */

public interface FragActivity {

    //碎片切换
    public void replaceFragment(int resourceViewId, Fragment fragment);
    /*
    *
    * 参数介绍:
    * resourceViewId    切换区域的id号
    * fragment          用于切换的新碎片对象
    *
    * */

}
