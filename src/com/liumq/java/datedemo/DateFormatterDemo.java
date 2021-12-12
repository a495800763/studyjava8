package com.liumq.java.datedemo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DateFormatterDemo {
    public static void main(String[] args) {
        LocalDateTimeFormatter();
    }

    public static void DateTimeFormatterTest1(){
        LocalDate date = LocalDate.of(2014, 3, 18);
        String basicIsoDateFormat = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String isoLocalDateFormat = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(basicIsoDateFormat);
        System.out.println(isoLocalDateFormat);
    }

    public static void DateTimeFormatterTest2(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String dateFormat1 = date1.format(formatter);
        //使用字符串和formatter重新解析之
        LocalDate date2 = LocalDate.parse(dateFormat1, formatter);

        System.out.println(date1);
        System.out.println(dateFormat1);
        System.out.println(date2);

    }

    public static void LocalDateTimeFormatter(){
        //创建一个本地化的datetimeformatter
        DateTimeFormatter italianFormatter = DateTimeFormatter.ofPattern("d. MMMM yyyy", Locale.CHINESE);
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        String formatDate = date1.format(italianFormatter);
        System.out.println(formatDate);
        LocalDate date2 = LocalDate.parse(formatDate, italianFormatter);
        System.out.println(date2);

    }

}
