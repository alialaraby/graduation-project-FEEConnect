package com.alisakralaraby.feeconnectdemo2.java_classes;

/**
 * Created by ali_a on 05/05/2017.
 */
public class Events {

    private String event_description, event_img_uri, event_title;

    public Events() {
    }

    public Events(String event_description, String event_img_uri, String event_title) {
        this.event_description = event_description;
        this.event_img_uri = event_img_uri;
        this.event_title = event_title;
    }

    public String getEvent_description() {
        return event_description;
    }

    public void setEvent_description(String event_description) {
        this.event_description = event_description;
    }

    public String getEvent_img_uri() {
        return event_img_uri;
    }

    public void setEvent_img_uri(String event_img_uri) {
        this.event_img_uri = event_img_uri;
    }

    public String getEvent_title() {
        return event_title;
    }

    public void setEvent_title(String event_title) {
        this.event_title = event_title;
    }
}
