package com.liumq.java.enumpackage;

public enum Brand {
    Audi(1,"奥迪"),
    BMW(2,"宝马"),
    Mazda(3,"马自达"),
    VolksWagen(4,"大众"),
    Tesla(5,"特斯拉"),
    Honda(6,"本田"),
    Toyota(7,"丰田");


    private int code;
    private String des;

    Brand(int code, String des) {
        this.code = code;
        this.des = des;
    }
}
