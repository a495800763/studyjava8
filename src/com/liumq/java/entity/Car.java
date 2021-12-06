package com.liumq.java.entity;

import com.liumq.java.enumpackage.Brand;
import com.liumq.java.enumpackage.Color;

public class Car {
    private Brand brand;
    private Color color;
    private String name;

    public Car() {
    }

    public Car(Brand brand, Color color, String name) {
        this.brand = brand;
        this.color = color;
        this.name = name;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand=" + brand +
                ", color=" + color +
                ", name='" + name + '\'' +
                '}';
    }
}
