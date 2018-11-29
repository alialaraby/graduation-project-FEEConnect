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

public class AddEventActivity extends AppCompatActivity {

    ImageView addEventImage;
    Button    btnAddEvent;
    EditText  addEventTitle, addEventDescription;

    private StorageReference  storageReference;
    private DatabaseReference databaseReference;

    private ProgressDialog    progressDialog;

    private static final int PICK_IMAGE = 8;

    private Uri imgUri = Uri.parse("android.resource://com.alisakralaraby.feeconnectdemo2/" + R.drawable.blank_image);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_event);

        addEventImage       = (ImageView) findViewById(R.id.addEventImg);
        btnAddEvent         = (Button) findViewById(R.id.btnAddEvent);
        addEventTitle       = (EditText) findViewById(R.id.addEventTitle);
        addEventDescription = (EditText) findViewById(R.id.addEventDescription);

        storageReference  = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Events");

        progressDialog = new ProgressDialog(this);

        addEventImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();
            }
        });

    }

    private void submitPost(){

        final String postTitle       = addEventTitle.getText().toString();
        final String postDescription = addEventDescription.getText().toString();

        if (TextUtils.isEmpty(postTitle)){

            addEventTitle.setError("must fill this field");
        }else if (TextUtils.isEmpty(postDescription)){
            addEventDescription.setError("must fill this field");
        }else {

            progressDialog.setMessage("uploading...");
            progressDialog.show();

            StorageReference postStorageReference = storageReference.child("Event_Images").child(imgUri.getLastPathSegment());
            postStorageReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri getImgUri = taskSnapshot.getDownloadUrl();
                    DatabaseReference postDarabaseReference = databaseReference.push();

                    postDarabaseReference.child("event_title").setValue(postTitle);
                    postDarabaseReference.child("event_description").setValue(postDescription);
                    postDarabaseReference.child("event_img_uri").setValue(getImgUri.toString());

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            imgUri = data.getData();
            addEventImage.setImageURI(imgUri);
        }
    }

}
