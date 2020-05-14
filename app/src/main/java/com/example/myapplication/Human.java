package com.example.myapplication;

public class Human {

    private String name;
    private String secondName;
    private String sex;

    public Human(String name, String secondName, String sex){

        this.name=name;
        this.secondName = secondName;
        this.sex = sex;

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
