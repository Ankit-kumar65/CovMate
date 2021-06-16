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



public class activity2 extends AppCompatActivity {
    private WebView mWebView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2);
        mWebView = (WebView) findViewById(R.id.b34);

        mWebView.setWebViewClient(new WebViewClient());
        mWebView.loadUrl("https://external.sprinklr.com/insights/explorer/dashboard/601b9e214c7a6b689d76f493/tab/19?id=DASHBOARD_601b9e214c7a6b689d76f493&tabId=19%2419_Oxygen%20Supplies&widgetId=608c475f011fb174c4e80954");

        mWebView.getSettings().setJavaScriptEnabled(true);


        mWebView.getSettings().setSupportMultipleWindows(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setDomStorageEnabled(true);






    }
}