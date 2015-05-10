package com.project.bitcoupon.bitcoupon.controllers;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.project.bitcoupon.bitcoupon.R;
import com.project.bitcoupon.bitcoupon.models.Coupon;
import com.project.bitcoupon.bitcoupon.singletons.UserData;

public class PayPalActivity extends BaseActivity  {

    private int userId;
    private int couponId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_pal);

        userId = UserData.getInstance().getId();
        Intent it = getIntent();

        couponId = it.getIntExtra("couponId", 0);
        String couponIdString = "" +  couponId ;

        String url = getString(R.string.IP) +
                ":9000/api/mobileCheckout/" + couponIdString + "/" + userId;


        /**
         * This is webView for application
         */
        WebView webView = (WebView) findViewById(R.id.web_view_payment);
        WebSettings settings = webView.getSettings();
        settings.setSupportZoom(true);
        settings.setBuiltInZoomControls(true);
        settings.setJavaScriptEnabled(true);
        webView.loadUrl(url);


        // If user decide to go on another web page keep the user inside the app, and If a user has successfully finished his buying, returned him to new activity
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (Uri.parse(url).getHost().equals( "http://10.0.82.13:9000/api/backToMobile")) {
                    Intent i = new Intent(PayPalActivity.this, UserProfileActivity.class);
                    startActivity(i);
                    return false;
                }

                if(url.contains("http://10.0.82.13:9000/api/backToMobile")){
                    Intent i = new Intent(PayPalActivity.this, UserProfileActivity.class);
                    startActivity(i);
                }

                if (Uri.parse(url).getHost().equals("http://10.0.82.13:9000")) {
                    Intent j = new Intent(PayPalActivity.this, CouponActivity.class);
                    startActivity(j);
                }

                view.loadUrl(url);
                return true;
            }
        });
    }
}
