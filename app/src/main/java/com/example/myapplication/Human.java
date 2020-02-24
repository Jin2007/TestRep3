package com.example.myapplication;

class Human {

    private String name;
    private String secondName;

    public Human(String name, String company){

        this.name=name;
        this.secondName = company;
    }

    public String getName() {
        return this.name;
    }

    public String getSecondName() {
        return this.secondName;
    }
}
