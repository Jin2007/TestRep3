package com.example.myapplication;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Human {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String secondName;
    public String sex;
    public String photo;

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
