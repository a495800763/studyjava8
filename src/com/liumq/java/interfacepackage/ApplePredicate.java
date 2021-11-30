package com.liumq.java.interfacepackage;

import com.liumq.java.entity.Apple;

import java.util.function.Predicate;

public interface ApplePredicate {
    boolean test(Apple apple);
}
