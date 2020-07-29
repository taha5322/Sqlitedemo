package com.siddiqui.sqlitedemo;

// Created by Taha Siddiqui
// 
public class CustomerModel {
    private int id;
    private String name;
    private int age;
    private boolean isActive;

    public CustomerModel(int id, String name, int age, boolean isActive) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.isActive = isActive;
    }
    public CustomerModel(){
    }

    //overide toString
    @Override
    public String toString(){
        return "CustomerMode{"+
                "\nid="+id+
                "\nname='"+name+"'"+
                "\nage=" +age+
                "\nisActive="+isActive+
                "\n}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }
}
