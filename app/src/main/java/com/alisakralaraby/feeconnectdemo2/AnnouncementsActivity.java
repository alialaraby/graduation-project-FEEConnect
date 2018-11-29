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

import com.alisakralaraby.feeconnectdemo2.java_classes.Posts;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class AnnouncementsActivity extends AppCompatActivity {

    /**
     * created by ali sakr alaraby
     */

    //declaring the views and the firebase
    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_announcements);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();
        //setting up the recycler view
        recyclerView = (RecyclerView)findViewById(R.id.announcementsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        //getting instance from the firebase database
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");
        databaseReference.keepSynced(true);

        //getting the current user
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){

            //comparing the current user id with this id if equal show the FAB button
            String userId = firebaseUser.getUid();
            if (userId.equals("hDeiz1qUy4d6jKhAM28jUaM7suv2")){

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AddPostActivity.class));
                    }
                });
            }
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    //setting up the recycler view adapter to the firebase recycler adapter
    // and populate it items from the view holder
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Posts, PostViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Posts, PostViewHolder>(
                Posts.class,
                R.layout.announcement_item,
                PostViewHolder.class,
                databaseReference
        ) {
            @Override
            protected void populateViewHolder(PostViewHolder viewHolder, Posts model, int position) {
                viewHolder.setTitle(model.getPost_title());
                viewHolder.setDescription(model.getPost_description());
                viewHolder.setImageUri(getApplicationContext(), model.getPost_img_uri());
            }

            @Override
            public Posts getItem(int position) {
                return super.getItem(super.getItemCount() - position - 1);
            }
        };

        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    //the post information are placed in this view holder
    public static class PostViewHolder extends RecyclerView.ViewHolder{

        View view;
        public PostViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setTitle(String title){

            TextView post_title = (TextView)view.findViewById(R.id.postItemTitle);
            post_title.setText(title);
        }

        public void setDescription(String description){

            TextView post_desc = (TextView)view.findViewById(R.id.postItemDescription);
            post_desc.setText(description);
        }

        public void setImageUri(Context context, String imageUri){

            ImageView post_image = (ImageView)view.findViewById(R.id.postItemImg);
            Picasso.with(context).load(imageUri).into(post_image);
        }

    }
}
