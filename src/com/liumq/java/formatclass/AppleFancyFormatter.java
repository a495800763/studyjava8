package com.liumq.java.formatclass;

import com.liumq.java.entity.Apple;
import com.liumq.java.interfacepackage.AppleFormatter;

public class AppleFancyFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        String characteristic = apple.getWeight() > 150 ? "hervy" : "light";
        return "A" + characteristic+" "+apple.getColor()+" apple";
    }
}
