package com.liumq.java.datedemo;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;
import java.util.function.UnaryOperator;

/**
 * 使用工厂方法加lambda 表达式的方式实现TemporalAdjuester接口
 */
public class TemporalLambda {
    public static void main(String[] args) {
        TemporalAdjuster nextWorkingDay = TemporalAdjusters.ofDateAdjuster(localDate -> {
            //读取当前日期，是一周中的第几天（周几）
            DayOfWeek dow = DayOfWeek.of(localDate.get(ChronoField.DAY_OF_WEEK));
            //正常情况增加一天
            int dayToAdd = 1;
            if (dow == DayOfWeek.FRIDAY) {
                //如果式周五的话应该加3天
                dayToAdd = 3;
            } else if (dow == DayOfWeek.SATURDAY) {
                //如果当天式周六的话增加2天
                dayToAdd = 2;
            }
            //返回当前日期plus dayToAdd ，单位是DAY
            return localDate.plus(dayToAdd, ChronoUnit.DAYS);
        });


        LocalDate date = LocalDate.now();
        LocalDate date2 = date.with(nextWorkingDay);
        System.out.println(date2);
    }
}
