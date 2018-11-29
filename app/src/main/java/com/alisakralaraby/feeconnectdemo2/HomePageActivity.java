package com.alisakralaraby.feeconnectdemo2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomePageActivity extends AppCompatActivity {

    /**
     * created by ali sakr alaraby
     */
    Button btnVisitor, btnStudent;

    //true to exit
    private boolean exit;

    //true if there is internet connection
    private boolean connected = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        Log.i("exit", String.valueOf(exit));
        //check if the wifi and internet data are enabled if not set connected to false and launch the alert dialog
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.DISCONNECTED
                && connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.DISCONNECTED ){

            //alert dialog to notify user there is no connection and recommend the user to enable connection
            AlertDialog.Builder builder = new AlertDialog.Builder(HomePageActivity.this);
            builder.setTitle("No Internet Connection");
            builder.setMessage("We Recommend You Enable Your Data or Connect to a WIFI");
            builder.setNegativeButton("Ignore", null);
            builder.setPositiveButton("Enable", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //go to phone settings in case user click enable to enable the data or wifi
                    startActivity(new Intent(Settings.ACTION_SETTINGS));
                }
            });
            builder.show();


        }

        if (getIntent().getBooleanExtra("EXIT", false))
        {
            finish();
        }

        btnVisitor = (Button)findViewById(R.id.btnVisitor);
        btnStudent = (Button)findViewById(R.id.btnStudent);

        //go to visitor activity when clicked
        btnVisitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(HomePageActivity.this, VisitorsActivity.class));
            }
        });

        //go to main activity when clicked
        btnStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomePageActivity.this, MainActivity.class));
            }
        });
    }

    //notify user to click another back click in 3 seconds to confirm exit the app
    //check the exit value if false keep inside the app if true it exits
    @Override
    public void onBackPressed() {
        //check the exit value, terminate if true
        if (exit){
            finish();
            Log.i("exit", String.valueOf(exit));
        }else {
            //change exit value to true and wait 3 seconds for confirmation from user, if he clickes again in 3 seconds period
            //exit value is true and it terminates the app
            Toast.makeText(getApplicationContext(), "Press again to exit", Toast.LENGTH_LONG).show();
            exit = true;
            Log.i("exit", String.valueOf(exit));
            //handler that keeps the the value true for 3 seconds ,after 3 seconds the value is false and the app is still open
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {

                    exit = false;
                    Log.i("exit", String.valueOf(exit));
                }
            }, 3 * 1000);
        }
    }
}
