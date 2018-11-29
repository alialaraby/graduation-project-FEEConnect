package com.alisakralaraby.feeconnectdemo2.java_classes;

/**
 * Created by ali_a on 18/04/2017.
 */
public class Posts {

    private String post_description, post_img_uri, post_title;

    public Posts() {
    }

    public Posts(String post_description, String post_img_uri, String post_title) {
        this.post_description = post_description;
        this.post_img_uri = post_img_uri;
        this.post_title = post_title;
    }

    public String getPost_description() {
        return post_description;
    }

    public void setPost_description(String post_description) {
        this.post_description = post_description;
    }

    public String getPost_img_uri() {
        return post_img_uri;
    }

    public void setPost_img_uri(String post_img_uri) {
        this.post_img_uri = post_img_uri;
    }

    public String getPost_title() {
        return post_title;
    }

    public void setPost_title(String post_title) {
        this.post_title = post_title;
    }
}
