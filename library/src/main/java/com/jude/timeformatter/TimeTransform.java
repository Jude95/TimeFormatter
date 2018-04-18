package com.jude.timeformatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

public class TimeTransform {
    public static final int MINUTE = 60;
    public static final int HOUR = 3600;
    public static final int DAY = 86400;
    public static final int WEEK = 604800;


    Calendar currentTime;
    String pattern;
    DateFormatter formatter;
    Locale locale;

    public TimeTransform() {
        currentTime = new GregorianCalendar();
        currentTime.setTime(new Date());
    }

    public TimeTransform(long timestamp) {
        currentTime = new GregorianCalendar();
        currentTime.setTime(new Date(timestamp * 1000));
    }

    public TimeTransform(int year, int month, int day) {
        currentTime = new GregorianCalendar(year, month, day);
    }

    public int getDay() {
        return currentTime.get(Calendar.DATE);
    }

    public int getMonth() {
        return currentTime.get(Calendar.MONTH) + 1;
    }

    public int getYear() {
        return currentTime.get(Calendar.YEAR);
    }

    public long getTimestamp() {
        return currentTime.getTime().getTime() / 1000;
    }

    public TimeTransform locale(Locale locale) {
        this.locale = locale;
        return this;
    }

    public TimeTransform pattern(String pattern){
        this.pattern = pattern;
        this.formatter = null;
        return this;
    }

    public TimeTransform formatter(DateFormatter dateFormatter){
        this.formatter = dateFormatter;
        this.pattern = null;
        return this;
    }

    public String toString() {
        if (locale == null) {
            locale = Locale.getDefault();
        }

        if (formatter != null){
            long delta = (System.currentTimeMillis() - currentTime.getTime().getTime()) / 1000;
            return formatter.format(this, delta);
        }

        if (pattern == null){
            pattern = "yyyy.MM.dd HH:mm:ss";
        }
        SimpleDateFormat format = new SimpleDateFormat(pattern, locale);
        String date = format.format(currentTime.getTime());
        return date;
    }

}
