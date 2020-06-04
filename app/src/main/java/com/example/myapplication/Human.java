package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Human {

    @PrimaryKey
    public long id;
    public String name;
    public String secondName;
    public String sex;
    public String photo;

//    public Human(long id, String name, String secondName, String sex, String photoPath){
//
//        this.name=name;
//        this.secondName = secondName;
//        this.sex = sex;
//        this.photo = photoPath;
//
//    }


    public long getId() {
        return id;
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
}
