package com.alisakralaraby.feeconnectdemo2.java_classes;

/**
 * Created by ali_a on 05/05/2017.
 */
public class CommunityMessage {

    private String community_description, community_img_uri, community_title;

    public CommunityMessage() {
    }

    public CommunityMessage(String community_description, String community_title, String community_img_uri) {
        this.community_description = community_description;
        this.community_title = community_title;
        this.community_img_uri = community_img_uri;
    }

    public String getCommunity_description() {
        return community_description;
    }

    public void setCommunity_description(String community_description) {
        this.community_description = community_description;
    }

    public String getCommunity_img_uri() {
        return community_img_uri;
    }

    public void setCommunity_img_uri(String community_img_uri) {
        this.community_img_uri = community_img_uri;
    }

    public String getCommunity_title() {
        return community_title;
    }

    public void setCommunity_title(String community_title) {
        this.community_title = community_title;
    }
}
