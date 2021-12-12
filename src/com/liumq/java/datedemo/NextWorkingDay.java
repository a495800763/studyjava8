package com.liumq.java.datedemo;

import java.time.DayOfWeek;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

public class NextWorkingDay implements TemporalAdjuster {
    @Override
    public Temporal adjustInto(Temporal temporal) {
        //读取当前日期，是一周中的第几天（周几）
        DayOfWeek dow = DayOfWeek.of(temporal.get(ChronoField.DAY_OF_WEEK));
        //正常情况增加一天
        int dayToAdd=1;
        if(dow==DayOfWeek.FRIDAY){
            //如果式周五的话应该加3天
            dayToAdd=3;
        }else if(dow==DayOfWeek.SATURDAY){
            //如果当天式周六的话增加2天
            dayToAdd=2;
        }
        //返回当前日期plus dayToAdd ，单位是DAY
        return temporal.plus(dayToAdd, ChronoUnit.DAYS);
    }
}
