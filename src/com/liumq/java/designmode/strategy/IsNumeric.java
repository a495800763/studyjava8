package com.liumq.java.designmode.strategy;

public class IsNumeric implements ValidationStrategy {
    @Override
    public boolean judgeIsValid(String e) {
        return e.matches("\\d+");
    }
}
