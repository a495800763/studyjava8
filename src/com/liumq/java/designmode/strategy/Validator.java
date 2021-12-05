package com.liumq.java.designmode.strategy;

public class Validator {
    private ValidationStrategy strategy;

    public Validator(ValidationStrategy s){
        this.strategy=s;
    }

    public boolean isValid(String s){
        return strategy.judgeIsValid(s);
    }
}
