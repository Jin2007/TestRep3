package com.example.myapplication;

import android.net.Uri;

public class Human {

    private String name;
    private String secondName;
    private String sex;
    private String photo;

    public Human(String name, String secondName, String sex, String photoPath){

        this.name=name;
        this.secondName = secondName;
        this.sex = sex;
        this.photo = photoPath;

    }

    public String getName() {
        return this.name;
    }

    public String getSecondName() {
        return this.secondName;
    }

    public String getSex(){
        return this.sex;
    }

    public String getPhoto() {
        return photo;
    }

    //    public void setName(String name) {
//        this.name = name;
//    }
}
