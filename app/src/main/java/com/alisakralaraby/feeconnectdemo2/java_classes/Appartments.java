package com.alisakralaraby.feeconnectdemo2.java_classes;

import com.google.firebase.database.DatabaseReference;

/**
 * Created by ali_a on 01/05/2017.
 */
public class Appartments {

    private String appartment_description, appartment_img_uri, appartment_title;

    public Appartments() {
    }

    public Appartments(String appartment_description, String appartment_img_uri, String appartment_title) {
        this.appartment_description = appartment_description;
        this.appartment_img_uri = appartment_img_uri;
        this.appartment_title = appartment_title;
    }

    public String getAppartment_description() {
        return appartment_description;
    }

    public void setAppartment_description(String appartment_description) {
        this.appartment_description = appartment_description;
    }

    public String getAppartment_img_uri() {
        return appartment_img_uri;
    }

    public void setAppartment_img_uri(String appartment_img_uri) {
        this.appartment_img_uri = appartment_img_uri;
    }

    public String getAppartment_title() {
        return appartment_title;
    }

    public void setAppartment_title(String appartment_title) {
        this.appartment_title = appartment_title;
    }
}
