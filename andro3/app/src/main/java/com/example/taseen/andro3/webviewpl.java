package com.example.taseen.andro3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class webviewpl extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webviewpl);

        String site = "http://www.planetica.tk";
        WebView browser = (WebView) findViewById(R.id.webView);
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(site);
    }
}
