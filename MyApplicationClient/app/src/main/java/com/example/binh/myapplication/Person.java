package com.example.binh.myapplication;

/**
 * Created by binh on 1/11/2016.
 */
public class Person {
    private String name, age;

    public Person(String name, String age) {
        this.setName(name);
        this.setAge(age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String toString() {
        return "Name: " + name + " - " + "Age: " + age;
    }
}
