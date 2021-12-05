package com.liumq.java.designmode.strategy;

public class IsAllLowerCase implements  ValidationStrategy {
    @Override
    public boolean judgeIsValid(String e) {
        return e.matches("[a-z]+");
    }
}
