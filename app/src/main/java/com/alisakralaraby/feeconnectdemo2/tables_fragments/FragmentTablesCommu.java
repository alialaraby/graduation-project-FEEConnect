package com.alisakralaraby.feeconnectdemo2.tables_fragments;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.squareup.picasso.Picasso;

/**
 * Created by ali_a on 05/05/2017.
 */
public class FragmentTablesCommu extends Fragment {

    private StorageReference storageReference;
    private Toast toast;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tables_layout_comm, container, false);

        toast = Toast.makeText(getContext(), "getting images, Please wait...", Toast.LENGTH_LONG);
        toast.show();

        storageReference = FirebaseStorage.getInstance().getReference();

        final ImageView imgLecture = (ImageView) view.findViewById(R.id.imgLectsCommu);

        StorageReference reference1 = storageReference.child("tables/commu_lectures.JPG");
        reference1.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Picasso.with(getContext()).load(uri).into(imgLecture);
                toast.cancel();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getContext(), "something went wrong, check your connection", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
