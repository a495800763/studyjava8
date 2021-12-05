package com.liumq.java.designmode.strategy;

public class Main {
    public static void main(String[] args) {
        Validator numericValidator = new Validator(new IsNumeric());
        Validator lowerCaseValidator = new Validator(new IsAllLowerCase());
        System.out.println(numericValidator.isValid("aaaa"));
        System.out.println(lowerCaseValidator.isValid("bbbb"));
    }
}
