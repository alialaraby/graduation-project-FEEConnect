package com.alisakralaraby.feeconnectdemo2;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddPostActivity extends AppCompatActivity {

    //declaring the views and the firebase references
    ImageView addPostImage;
    Button btnAddPost;
    EditText addPostTitle, addPostDescription;
    private StorageReference storageReference;
    private DatabaseReference databaseReference;
    private ProgressDialog progressDialog;

    //request code for picking an image from the device
    private static final int PICK_IMAGE = 1;
    //assigning the uri a default value to prevent errors
    private Uri imgUri = Uri.parse("android.resource://com.alisakralaraby.feeconnectdemo2/" + R.drawable.blank_image);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        //assigning the views with the xml views and setting up the firebase references
        addPostImage = (ImageView)findViewById(R.id.addPostImg);
        addPostTitle = (EditText)findViewById(R.id.addPostTitle);
        addPostDescription = (EditText)findViewById(R.id.addPostDescription);
        btnAddPost = (Button)findViewById(R.id.btnAddPost);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Posts");

        progressDialog = new ProgressDialog(this);

        //choose image from the device
        addPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        //add the post to firebase database
        btnAddPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();
            }
        });
    }

    //connecting the firebase database and storage to save the post details and images
    private void submitPost(){

        final String postTitle = addPostTitle.getText().toString();
        final String postDescription = addPostDescription.getText().toString();

        //in case title and descriptions fields are empty
        if (TextUtils.isEmpty(postTitle)){
            addPostTitle.setError("must fill this field");
        }else if (TextUtils.isEmpty(postDescription)){
            addPostDescription.setError("must fill this field");
        }else{

            progressDialog.setMessage("uploading...");
            progressDialog.show();

            StorageReference postStorageReference = storageReference.child("Post_Images").child(imgUri.getLastPathSegment());
            postStorageReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri getImgUri = taskSnapshot.getDownloadUrl();
                    DatabaseReference postDarabaseReference = databaseReference.push();

                    postDarabaseReference.child("post_title").setValue(postTitle);
                    postDarabaseReference.child("post_description").setValue(postDescription);
                    postDarabaseReference.child("post_img_uri").setValue(getImgUri.toString());

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }

    //taking the image selected from the device
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            imgUri = data.getData();
            addPostImage.setImageURI(imgUri);
        }
    }
}
