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

import com.alisakralaraby.feeconnectdemo2.java_classes.CommunityMessage;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;
public class CommunityActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView)findViewById(R.id.communityRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Community_Messages");
        databaseReference.keepSynced(true);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabCommunity);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CommunityActivity.this, AddCommunityMessageActivity.class));
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<CommunityMessage, CommunityViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<CommunityMessage, CommunityViewHolder>(
                CommunityMessage.class,
                R.layout.community_item,
                CommunityViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(CommunityViewHolder viewHolder, CommunityMessage model, int position) {

                viewHolder.setTitle(model.getCommunity_title());
                viewHolder.setDescription(model.getCommunity_description());
                viewHolder.setImageUri(getApplicationContext(), model.getCommunity_img_uri());
            }
            @Override
            public CommunityMessage getItem(int position) {
                return super.getItem(super.getItemCount() - position - 1);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public static class CommunityViewHolder extends RecyclerView.ViewHolder{

        View view;
        public CommunityViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setTitle(String title){

            TextView community_title = (TextView)view.findViewById(R.id.communityItemTitle);
            community_title.setText(title);
        }

        public void setDescription(String description){

            TextView community_desc = (TextView)view.findViewById(R.id.communityItemDescription);
            community_desc.setText(description);
        }

        public void setImageUri(Context context, String imageUri){

            ImageView community_image = (ImageView)view.findViewById(R.id.communityItemImg);
            Picasso.with(context).load(imageUri).into(community_image);
        }

    }

}
