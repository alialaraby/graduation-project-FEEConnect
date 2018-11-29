package com.alisakralaraby.feeconnectdemo2.java_classes;

/**
 * Created by ali_a on 26/04/2017.
 */
public class Emails {

    private String name, email;

    public Emails() {
    }

    public Emails(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return "email : " + getEmail();
    }
}
