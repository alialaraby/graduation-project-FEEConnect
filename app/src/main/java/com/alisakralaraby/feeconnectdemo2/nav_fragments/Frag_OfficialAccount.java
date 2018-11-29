package com.alisakralaraby.feeconnectdemo2.nav_fragments;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.alisakralaraby.feeconnectdemo2.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
public class Frag_OfficialAccount extends Fragment {

    private DatabaseReference databaseReference;
    EditText officialAccountEditName;
    private ArrayList<String> emailsList = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.frag_official_account, container, false);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("accounts");


        String officialAccountHtml = getString(R.string.official_account_html);
        WebView officialAccountWebview = (WebView)view.findViewById(R.id.official_account_webView);


        officialAccountEditName = (EditText)view.findViewById(R.id.official_account_edittext);
        Button officialAccountBtnName = (Button)view.findViewById(R.id.official_account_button);
        final TextView officialAccountTextEmail = (TextView)view.findViewById(R.id.official_account_textview);
        officialAccountBtnName.setOnClickListener(new View.OnClickListener() {

            String name = officialAccountEditName.getText().toString();
            @Override
            public void onClick(View v) {
                if (officialAccountEditName.getText().toString().equals("")){

                    officialAccountEditName.setError("يجب ادخال الاسم ثلاثي");
                }else {

                    databaseReference.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {

                            databaseReference.orderByChild("id").equalTo(officialAccountEditName.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    try {
                                        String value = dataSnapshot.getValue().toString();
                                        String[] array = value.split(",");
                                        String[] subArray = array[2].split("=");
                                        Log.i("aaa", Arrays.toString(subArray));
                                        officialAccountTextEmail.setText(subArray[1]);
                                    }catch (Exception e){
                                        Toast.makeText(getContext(), "can`t find value", Toast.LENGTH_SHORT).show();
                                    }
                                }
                                @Override
                                public void onCancelled(DatabaseError databaseError) {
                                    Toast.makeText(getContext(), "can`t find value", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });


        officialAccountWebview.loadDataWithBaseURL(null, officialAccountHtml, "text/html", "utf-8", null);


        return view;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("UR FEE Official Account");
    }
}
