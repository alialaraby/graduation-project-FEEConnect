package com.alisakralaraby.feeconnectdemo2.train_activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

public class MenoufBenhaActivity extends AppCompatActivity {

    ImageView imgBenha_Menouf, imgMenouf_Benha;
    private StorageReference storageReference;
    private BroadcastReceiver broadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menouf_benha);

        checkNetwork();

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();

        imgBenha_Menouf = (ImageView)findViewById(R.id.benha_menouf);
        imgMenouf_Benha = (ImageView)findViewById(R.id.menouf_benha);

        storageReference = FirebaseStorage.getInstance().getReference();

        getImages();

        imgBenha_Menouf.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                AlertDialog.Builder downloadDialog = new AlertDialog.Builder(MenoufBenhaActivity.this);
                downloadDialog.setTitle("Download..??")
                .setMessage("do you like to download the image ?")
                .setPositiveButton("download", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getApplicationContext(), "image clicked", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("no",null);

                downloadDialog.show();
                return false;
            }
        });
    }

    private void getImages(){

        StorageReference ref1 = storageReference.child("train_images/menouf_benha.JPG");
        ref1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imgMenouf_Benha);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "Failed, check connection", Toast.LENGTH_SHORT).show();
            }
        });

        StorageReference ref2 = storageReference.child("train_images/benha_menouf.JPG");
        ref2.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getApplicationContext()).load(uri).into(imgBenha_Menouf);
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
