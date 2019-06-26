package com.example.lotterydemo.Interface;

import android.webkit.WebView;

/**
 * Created by dell on 2019/5/16.
 */

public interface WebPageActivity {

    //更新WebView的内容
    public void updateWebView(String url, WebView webView);
    /*
    *
    * 参数介绍:
    * url       webview显示的网址
    * webview   需要更新的Webview对象
    *
    * */

}
