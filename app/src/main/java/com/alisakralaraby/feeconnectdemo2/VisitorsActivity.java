package com.alisakralaraby.feeconnectdemo2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class VisitorsActivity extends AppCompatActivity implements View.OnClickListener{

    /**
     * created by ali sakr alaraby
     */

    //declaration part for views
    private RelativeLayout visitorsRelativeLayout;
    private WebView webViewVisitors;
    private String aboutFEE, aboutFEEDepatments;
    private Button btnAboutFEE, btnaboutFEEDepartments;
    private boolean exit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visitors);

        //connecting the views in the xml
        visitorsRelativeLayout = (RelativeLayout) findViewById(R.id.visitorsRelativeLayout);
        webViewVisitors = (WebView) findViewById(R.id.webViewVisitors);
        btnAboutFEE = (Button) findViewById(R.id.btnAboutFEE);
        btnaboutFEEDepartments = (Button) findViewById(R.id.btnAboutFEEDepartments);

        //assigning the string values in the string resources
        aboutFEE = getString(R.string.about_fee);
        aboutFEEDepatments = getString(R.string.about_fee_departments);

        //assigning the buttons onclick listeners
        btnAboutFEE.setOnClickListener(this);
        btnaboutFEEDepartments.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            //open the string aboutFee to the users
            case R.id.btnAboutFEE :
                visitorsRelativeLayout.setVisibility(View.INVISIBLE);
                webViewVisitors.setVisibility(View.VISIBLE);
                webViewVisitors.loadDataWithBaseURL(null, aboutFEE, "text/html", "utf-8", null);
                exit = false;
                break;
            //open the string aboutFeeDepartments to the users
            case R.id.btnAboutFEEDepartments :
                visitorsRelativeLayout.setVisibility(View.INVISIBLE);
                webViewVisitors.setVisibility(View.VISIBLE);
                webViewVisitors.loadDataWithBaseURL(null, aboutFEEDepatments, "text/html", "utf-8", null);
                exit = false;
                break;
        }
    }

    @Override
    public void onBackPressed() {

        //move between visitors main page and the buttons contents in case of one back pressed
        if (exit == false){
            visitorsRelativeLayout.setVisibility(View.VISIBLE);
            webViewVisitors.setVisibility(View.INVISIBLE);
            exit = true;
        }else {

            //exit this activity in case of 2 backs pressed
            finish();
        }
    }
}
