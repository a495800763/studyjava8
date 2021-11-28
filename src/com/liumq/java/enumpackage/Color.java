package com.liumq.java.enumpackage;

public enum Color {
    /**
     * 红色
     */
    RED(1, "red"),
    /**
     * 绿色
     */
    GREEN(2, "green");


    private int code;
    private String des;

    Color(int code, String des) {
        this.code = code;
        this.des = des;
    }
}
