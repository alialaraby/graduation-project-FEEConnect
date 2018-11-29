package com.alisakralaraby.feeconnectdemo2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.java_classes.Appartments;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

public class FindAnAppartmentActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private DatabaseReference databaseReference;
    private FirebaseUser firebaseUser;

    private LinearLayout findAppartmentDetailedLinearLayout;
    private ImageView findAppartmentDetailedImgView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_an_appartment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findAppartmentDetailedLinearLayout = (LinearLayout) findViewById(R.id.findAppartmentDetailedLinearLayout);
        findAppartmentDetailedImgView = (ImageView) findViewById(R.id.findAppartmentDetailedImgView);

        Toast.makeText(getApplicationContext(), "getting images, please wait...", Toast.LENGTH_LONG).show();

        recyclerView = (RecyclerView)findViewById(R.id.findAppartmentRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(FindAnAppartmentActivity.this));

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Appartments");
        databaseReference.keepSynced(true);

        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        if (firebaseUser != null){

            String userId = firebaseUser.getUid();
            if (userId.equals("hDeiz1qUy4d6jKhAM28jUaM7suv2")){

                FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fabAppartment);
                fab.setVisibility(View.VISIBLE);
                fab.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(getApplicationContext(), AddAppartmentActivity.class));
                    }
                });
            }
        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseRecyclerAdapter<Appartments, ApprtViewHolder> firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Appartments, ApprtViewHolder>(
                Appartments.class,
                R.layout.appartments_item,
                ApprtViewHolder.class,
                databaseReference
        ) {

            DatabaseReference reference = databaseReference.getRef().child("appartment_contactnumber");
            @Override
            protected void populateViewHolder(ApprtViewHolder viewHolder, Appartments model, int position) {
                viewHolder.setTitle(model.getAppartment_title());
                viewHolder.setDescription(model.getAppartment_description());
                viewHolder.setImageUri(getApplicationContext(), model.getAppartment_img_uri());

                viewHolder.view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                });
            }
            @Override
            public Appartments getItem(int position) {
                return super.getItem(super.getItemCount() - position - 1);
            }
        };
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    public static class ApprtViewHolder extends RecyclerView.ViewHolder{

        View view;
        public ApprtViewHolder(View itemView) {
            super(itemView);
            view = itemView;
        }

        public void setTitle(String title){

            TextView post_title = (TextView)view.findViewById(R.id.apprtItemTitle);
            post_title.setText(title);
        }
        public void setDescription(String description){

            TextView post_desc = (TextView)view.findViewById(R.id.apprtItemDescription);
            post_desc.setText(description);
        }
        public void setImageUri(Context context, String imageUri){

            ImageView post_image = (ImageView)view.findViewById(R.id.apprtItemImg);
            Picasso.with(context).load(imageUri).into(post_image);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.appartments_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_community){
            startActivity(new Intent(getApplicationContext(), CommunityActivity.class));
        }
        return true;
    }
}
