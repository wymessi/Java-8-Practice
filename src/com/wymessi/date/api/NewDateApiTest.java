package com.wymessi.date.api;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import static java.time.temporal.TemporalAdjusters.*;

/**
 * Created by wy on 2019/2/1
 */
public class NewDateApiTest {
    
    public static void main(String[] args) {
        //获得日期对象
        LocalDate date = LocalDate.of(2019,2, 1);
        System.out.println(date); //2019-02-01

        int year = date.getYear();
        System.out.println(year);//2019

        Month month = date.getMonth();
        System.out.println(month);//FEBRUARY

        int day = date.getDayOfMonth();
        System.out.println(day);//1

        DayOfWeek dow = date.getDayOfWeek();
        System.out.println(dow);//FRIDAY

        int len = date.lengthOfMonth();
        System.out.println(len);//28

        boolean leap = date.isLeapYear();
        System.out.println(leap);//是否是闰年 false

        //取得当前日期
        LocalDate today = LocalDate.now();

        int y = date.get(ChronoField.YEAR);
        int m = date.get(ChronoField.MONTH_OF_YEAR);
        int d = date.get(ChronoField.DAY_OF_MONTH);
        System.out.println(y + "-"+ m +"-"+ d);

        //创建LocalTime并读取其值
        LocalTime time = LocalTime.of(13, 45, 20);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.println(hour + ":"+ minute +":"+ second);

        LocalDate parsedate = LocalDate.parse("2019-02-01");
        LocalTime parsetime = LocalTime.parse("13:45:20");

        System.out.println(parsedate.toString() + " " + parsetime.toString());

        LocalDateTime dt1 = LocalDateTime.of(2019, Month.FEBRUARY, 1, 13, 45, 20);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 20);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        /*LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();*/

        //withXXX修改日期属性
        LocalDate date1 = LocalDate.of(2014, 3, 18);
        LocalDate date2 = date1.withYear(2011);
        LocalDate date3 = date2.withDayOfMonth(25);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 9);
        System.out.println(date4);

        //以相对方式修改LocalDate对象的属性
        LocalDate date5 = LocalDate.of(2014, 3, 18);
        LocalDate date6 = date5.plusWeeks(1);
        LocalDate date7 = date6.minusYears(3);
        LocalDate date8 = date7.plus(6, ChronoUnit.MONTHS);
        System.out.println(date8);

        //使用 TemporalAdjuster 为函数式接口，可自定义
        LocalDate date9 = LocalDate.of(2014, 3, 18);
        LocalDate date10 = date9.with(nextOrSame(DayOfWeek.SUNDAY));
        LocalDate date11 = date10.with(lastDayOfMonth());
        System.out.println(date11);

        //格式化操作
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);
        System.out.println(s1);
        System.out.println(s2);

        LocalDate d1 = LocalDate.parse("20190201",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate d2 = LocalDate.parse("2019-02-01",
                DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(d1);
        System.out.println(d2);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate ld = LocalDate.of(2019, 2, 1);
        String formattedDate = date1.format(formatter);
        LocalDate ld2 = LocalDate.parse(formattedDate, formatter);

        System.out.println(formattedDate);
        System.out.println(ld2);
    }
}
