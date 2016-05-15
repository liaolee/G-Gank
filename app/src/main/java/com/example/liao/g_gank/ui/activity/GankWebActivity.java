package com.example.liao.g_gank.ui.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.liao.g_gank.R;

/**
 * Created by liao on 2016/5/13.
 */
public class GankWebActivity extends BaseActivity {

    public static final String INTENT_URL_KEY = "GANK_WEB_URL";
    public static final String INTENT_TITLE_KEY = "GANK_WEB_TITLE";
    private WebView gankWebView;
    private Toolbar toolbar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gank_web);

        initView();
        initData();


    }

    private void initData() {

        Bundle bundle = getIntent().getExtras();
        String url = (String) bundle.get(INTENT_URL_KEY);
        String title = (String) bundle.get(INTENT_TITLE_KEY);
        gankWebView.loadUrl(url);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setTitle(title);


    }

    private void initView() {

        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        gankWebView = (WebView) findViewById(R.id.web_gank);

        WebSettings webSettings = gankWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        //断网情况下加载本地缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        gankWebView.setWebViewClient(new WebViewClient(){

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                gankWebView.loadUrl(url);


                return true;

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN) {
            switch (keyCode) {
                case KeyEvent.KEYCODE_BACK:
                    if (gankWebView.canGoBack()) {
                        gankWebView.goBack();
                    } else {
                        finish();
                    }
                    return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
