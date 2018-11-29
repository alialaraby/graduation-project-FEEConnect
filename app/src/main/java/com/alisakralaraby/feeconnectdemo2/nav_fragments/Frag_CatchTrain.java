package com.alisakralaraby.feeconnectdemo2.nav_fragments;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alisakralaraby.feeconnectdemo2.train_activities.MenoufBenhaActivity;
import com.alisakralaraby.feeconnectdemo2.train_activities.MenoufCairoActivity;
import com.alisakralaraby.feeconnectdemo2.train_activities.MenoufKafrZiatActivity;
import com.alisakralaraby.feeconnectdemo2.train_activities.MenoufTantaActivity;
import com.alisakralaraby.feeconnectdemo2.R;

/**
 * Created by ali_a on 05/04/2017.
 */
public class Frag_CatchTrain extends Fragment {


    CardView cardView1, cardView2, cardView3, cardView4;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        cardView1 = (CardView)getView().findViewById(R.id.cardview1);
        cardView2 = (CardView)getView().findViewById(R.id.cardview2);
        cardView3 = (CardView)getView().findViewById(R.id.cardview3);
        cardView4 = (CardView)getView().findViewById(R.id.cardview4);

        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MenoufCairoActivity.class));
            }
        });
        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MenoufTantaActivity.class));
            }
        });
        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MenoufBenhaActivity.class));
            }
        });
        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(getContext(), MenoufKafrZiatActivity.class));
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_catch_train, container, false);
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("catch train");
        return view;
    }

}
