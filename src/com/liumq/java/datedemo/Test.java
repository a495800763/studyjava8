package com.liumq.java.datedemo;

import java.time.*;
import java.time.temporal.ChronoField;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
//        Date date = new Date(117,8,21);
//        System.out.println(date);
        useInstantTime();
    }

    public static void userLocalDate(){
        LocalDate date = LocalDate.of(2017, 9, 21);
        System.out.println(date);
        int year = date.getYear();
        Month month = date.getMonth();
        int dayOfMonth = date.getDayOfMonth();
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leapYear = date.isLeapYear();

        LocalDate now = LocalDate.now();
        System.out.println(now);
    }

    public static void  userLocalDate2(){
        LocalDate date = LocalDate.now();
        int year = date.get(ChronoField.YEAR);
        int month = date.get(ChronoField.MONTH_OF_YEAR);
        int day = date.get(ChronoField.DAY_OF_MONTH);

        int year1 = date.getYear();
        int monthValue = date.getMonthValue();
        int dayOfMonth = date.getDayOfMonth();
        int a  = 1;

    }

    public static void userLocalTime(){
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        LocalDate parseDate = LocalDate.parse("2021-11-21");
        LocalTime parseTime = LocalTime.parse("05:11:42");
        int b = 1 ;
    }

    public static void createLocalDateTime(){
        LocalDate date = LocalDate.of(2018,5,22);
        LocalTime time =LocalTime.of(13,34,26);
        LocalDateTime dt1 = LocalDateTime.of(2014, Month.SEPTEMBER, 21, 12, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(12, 56, 25);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        int b =4;


    }

    public static void useInstantTime(){
        Instant instant1 = Instant.ofEpochSecond(3);
        Instant instant2 = Instant.ofEpochSecond(3, 0);
        Instant instant3 = Instant.ofEpochSecond(2, 1_000_000_000);
        Instant instant4 = Instant.ofEpochSecond(4, -2_000_000_000);

        Instant now = Instant.now();
        int nano = now.getNano();


        int q= 3;
    }
}
