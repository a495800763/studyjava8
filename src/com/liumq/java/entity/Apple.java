package com.liumq.java.entity;

import com.liumq.java.enumpackage.Color;

public class Apple {

    private int weight;
    private Color color;

    public Apple(){}

    public Apple(int w,Color c){
        this.weight=w;
        this.color=c;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
