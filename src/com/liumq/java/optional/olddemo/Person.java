package com.liumq.java.optional.olddemo;

import java.util.Optional;

public class Person {
    private Optional<Car> car;

    private int age;


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Optional<Car> getCar() {
        return car;
    }

    public void setCar(Optional<Car> car) {
        this.car = car;
    }
}
