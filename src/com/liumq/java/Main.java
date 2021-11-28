package com.liumq.java;

import com.liumq.java.entity.Apple;
import com.liumq.java.enumpackage.Color;
import com.liumq.java.formatclass.AppleFancyFormatter;
import com.liumq.java.formatclass.AppleSimpleFormatter;
import com.liumq.java.interfacepackage.AppleFormatter;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Apple> apples = new ArrayList<>();
        apples.add(new Apple(200, Color.GREEN));
        apples.add(new Apple(145, Color.RED));
        apples.add(new Apple(165, Color.RED));
        apples.add(new Apple(150, Color.GREEN));
        apples.add(new Apple(123, Color.GREEN));

        prettyPrintApple(apples,new AppleFancyFormatter());

    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter formatter) {
        for (Apple apple : inventory) {
            String output = formatter.accept(apple);
            System.out.println(output);
        }
    }
}
