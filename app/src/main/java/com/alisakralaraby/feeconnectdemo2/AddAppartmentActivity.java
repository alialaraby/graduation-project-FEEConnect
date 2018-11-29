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

public class AddAppartmentActivity extends AppCompatActivity {

    private ImageView imgAddAprrt;
    private EditText txtAddAprrtTitle, txtAddAprrtDesc;
    private Button btnAddAprrt;

    private StorageReference storageReference;
    private DatabaseReference databaseReference;

    private static final int PICK_IMAGE = 5;
    private Uri imgUri = Uri.parse("android.resource://com.alisakralaraby.feeconnectdemo2/" + R.drawable.blank_image);;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appartment);

        imgAddAprrt      = (ImageView) findViewById(R.id.addApprtImg);
        txtAddAprrtDesc  = (EditText) findViewById(R.id.addAprrtDescription);
        txtAddAprrtTitle = (EditText) findViewById(R.id.addAprrtTitle);
        btnAddAprrt      = (Button) findViewById(R.id.btnAddAprrt);

        storageReference = FirebaseStorage.getInstance().getReference();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Appartments");

        progressDialog = new ProgressDialog(this);

        imgAddAprrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnAddAprrt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                submitPost();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK){

            imgUri = data.getData();
            imgAddAprrt.setImageURI(imgUri);
        }
    }

    private void submitPost() {

        final String postTitle = txtAddAprrtTitle.getText().toString();
        final String postDescription = txtAddAprrtDesc.getText().toString();

        if (TextUtils.isEmpty(postTitle)){

            txtAddAprrtTitle.setError("must fill this field");
        }else if (TextUtils.isEmpty(postDescription)){

            txtAddAprrtDesc.setError("must fill this field");
        }else {

            progressDialog.setMessage("uploading...");
            progressDialog.show();

            StorageReference postStorageReference = storageReference.child("Appartments_Images").child(imgUri.getLastPathSegment());
            postStorageReference.putFile(imgUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    Uri getImgUri = taskSnapshot.getDownloadUrl();
                    DatabaseReference postDarabaseReference = databaseReference.push();

                    postDarabaseReference.child("appartment_title").setValue(postTitle);
                    postDarabaseReference.child("appartment_description").setValue(postDescription);
                    postDarabaseReference.child("appartment_img_uri").setValue(getImgUri.toString());

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Done", Toast.LENGTH_SHORT).show();
                    finish();
                }
            });
        }
    }
}
