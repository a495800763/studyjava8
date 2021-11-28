package com.liumq.java.predicateclass;

import com.liumq.java.entity.Apple;
import com.liumq.java.interfacepackage.ApplePredicate;

public class AppleHeavyWeightPredicate implements ApplePredicate {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight()>150;
    }
}
