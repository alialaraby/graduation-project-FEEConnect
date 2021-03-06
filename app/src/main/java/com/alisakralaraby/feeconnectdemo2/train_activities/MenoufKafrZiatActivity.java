package com.alisakralaraby.feeconnectdemo2.train_activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class MenoufKafrZiatActivity extends AppCompatActivity {

    ImageView imgKafrZiat_Menouf, imgMenouf_KafrZiat;
    private StorageReference storageReference;
    private BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menouf_kafr_ziat);

        checkNetwork();

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();

        imgKafrZiat_Menouf = (ImageView) findViewById(R.id.kafrziat_menouf);
        imgMenouf_KafrZiat = (ImageView) findViewById(R.id.menouf_kafrziat);

        storageReference = FirebaseStorage.getInstance().getReference();

        getImages();
    }

    private void getImages() {

        StorageReference ref1 = storageReference.child("train_images/menouf_kafrziat.JPG");
        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imgMenouf_KafrZiat);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed, check connection", Toast.LENGTH_SHORT).show();
            }
        });

        StorageReference ref2 = storageReference.child("train_images/kafrziat_menouf.JPG");
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imgKafrZiat_Menouf);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed, check connection", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void checkNetwork() {

        if (broadcastReceiver == null) {

            broadcastReceiver = new BroadcastReceiver() {

                @Override
                public void onReceive(Context context, Intent intent) {

                    Bundle extras = intent.getExtras();

                    NetworkInfo info = (NetworkInfo) extras
                            .getParcelable("networkInfo");

                    NetworkInfo.State state = info.getState();

                    if (state == NetworkInfo.State.CONNECTED) {


                    } else {

                        Toast.makeText(getApplicationContext(), "check mobile network", Toast.LENGTH_LONG).show();

                    }

                }
            };

            final IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            registerReceiver(broadcastReceiver, intentFilter);
        }
    }
}
