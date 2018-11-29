package com.alisakralaraby.feeconnectdemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.java_classes.Events;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class EventsActivity extends AppCompatActivity {

    /**
     *created by ali sakr alaraby
     */

    // this activity code is similar to the announcements activity code
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView)findViewById(R.id.eventsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));



        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");
        databaseReference.keepSynced(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){

            String userId = firebaseUser.getUid();
            if (userId.equals("hDeiz1qUy4d6jKhAM28jUaM7suv2")){

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabEvents);
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AddEventActivity.class));
                    }
                });
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Events, EventViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Events, EventViewHolder>(
                Events.class,
                R.layout.event_item,
                EventViewHolder.class,
                databaseReference
        ) {
            @Override
            public void populateViewHolder(EventViewHolder viewHolder, Events model, int position) {

                viewHolder.setTitle(model.getEvent_title());
                viewHolder.setDescription(model.getEvent_description());
                viewHolder.setImageUri(getApplicationContext(), model.getEvent_img_uri());
            }

            @Override
            public Events getItem(int position) {
                return super.getItem(super.getItemCount() - position - 1);
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }

    public static class EventViewHolder extends RecyclerView.ViewHolder{

        View view;
        public EventViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setTitle(String title){

            TextView event_title = (TextView)view.findViewById(R.id.eventItemTitle);
            event_title.setText(title);
        }

        public void setDescription(String description){

            TextView event_desc = (TextView)view.findViewById(R.id.eventItemDescription);
            event_desc.setText(description);
        }

        public void setImageUri(Context context, String imageUri){

            ImageView event_image = (ImageView)view.findViewById(R.id.eventItemImg);
            Picasso.with(context).load(imageUri).into(event_image);
        }

    }

}
