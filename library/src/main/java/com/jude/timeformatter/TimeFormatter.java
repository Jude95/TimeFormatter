package com.jude.timeformatter;

import android.content.Context;

import java.util.Locale;

public class TimeFormatter {

    public static TimeTransform optimalDate(Context context, long timestamp) {
        Locale locale = context.getResources().getConfiguration().locale;
        TimeTransform timeTransform = new TimeTransform(timestamp);
        timeTransform.locale(locale);
        if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return timeTransform.pattern("MMM d, yyyy");
        } else {
            return timeTransform.pattern("yyyy-M-dd");
        }
    }

    public static TimeTransform optimalDetail(Context context, long timestamp) {
        Locale locale = context.getResources().getConfiguration().locale;
        TimeTransform timeTransform = new TimeTransform(timestamp);
        timeTransform.locale(locale);
        if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            return timeTransform.pattern("MMM d, yyyy HH:mm:ss");
        } else {
            return timeTransform.pattern("yyyy-M-dd HH:mm:ss");
        }
    }

    public static TimeTransform recent(Context context, long timestamp) {
        Locale locale = context.getResources().getConfiguration().locale;
        RecentDateFormatter recentDateFormatter = new RecentDateFormatter(context);
        if (locale.getLanguage().equals(Locale.ENGLISH.getLanguage())) {
            recentDateFormatter.setDatePattern("MMM d, yyyy");
            recentDateFormatter.setTimePattern("HH:mm");
        } else {
            recentDateFormatter.setDatePattern("yyyy-MM-dd");
            recentDateFormatter.setTimePattern("HH:mm");
        }
        return new TimeTransform(timestamp).formatter(recentDateFormatter);
    }

}
