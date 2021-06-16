package com.sjk.covhelp3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;



public class activity4 extends AppCompatActivity {
    private WebView mWebView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity4);
        mWebView = (WebView) findViewById(R.id.b35);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://docs.google.com/forms/d/e/1FAIpQLSe1OsV3TIrCNOcicsO9apm31xvE84U9I-WXm334XHu1xPzzVw/viewform?vc=0&c=0&w=1&flr=0");

        mWebView.getSettings().setJavaScriptEnabled(true);


        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setDomStorageEnabled(true);






    }
}