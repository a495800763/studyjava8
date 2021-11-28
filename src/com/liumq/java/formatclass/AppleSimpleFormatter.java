package com.liumq.java.formatclass;

import com.liumq.java.entity.Apple;
import com.liumq.java.interfacepackage.AppleFormatter;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of" + apple.getWeight()+" g";
    }
}
