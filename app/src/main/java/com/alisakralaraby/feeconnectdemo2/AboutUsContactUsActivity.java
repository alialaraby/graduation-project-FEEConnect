package com.alisakralaraby.feeconnectdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AboutUsContactUsActivity extends AppCompatActivity {

    private WebView contactUsAboutUsWebView;
    private String aboutUsString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us_contact_us);

        contactUsAboutUsWebView = (WebView) findViewById(R.id.contactUsAboutUswebView);

        aboutUsString = getResources().getString(R.string.aboutUs);

        savedInstanceState = getIntent().getExtras();
        String value = savedInstanceState.getString("communicateKey");
        if (value.equals("about_us")){

            contactUsAboutUsWebView.loadDataWithBaseURL(null, aboutUsString, "text/html", "utf-8", null);
        }else if (value.equals("contact_us")){

            contactUsAboutUsWebView.setWebViewClient(new WebViewClient());
            contactUsAboutUsWebView.loadUrl("https://www.facebook.com/FEEConnect/");
        }else if(value.equals("regulation")){

            contactUsAboutUsWebView.setWebViewClient(new WebViewClient());
            contactUsAboutUsWebView.loadUrl("https://drive.google.com/drive/folders/0B3NhLsv9NrvEN1ZYSDc3QVZ0cHc");
        }else if(value.equals("FEE_SCC")){

            contactUsAboutUsWebView.setWebViewClient(new WebViewClient());
            contactUsAboutUsWebView.loadUrl("http://mu.menofia.edu.eg/FEE/FEE_SCC/SUHome/ar");
        }else if(value.equals("E-Library")){

            contactUsAboutUsWebView.setWebViewClient(new WebViewClient());
            contactUsAboutUsWebView.loadUrl("http://mu.menofia.edu.eg/fee/fee_lib/LibraryHome/ar");
        }
    }
}
