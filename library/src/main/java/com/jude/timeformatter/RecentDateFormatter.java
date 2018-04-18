package com.jude.timeformatter;

import android.content.Context;

class RecentDateFormatter implements DateFormatter {
    private Context context;
    private String datePattern;
    private String timePattern;

    public RecentDateFormatter(Context context) {
        this.context = context;
    }

    public void setDatePattern(String datePattern) {
        this.datePattern = datePattern;
    }

    public void setTimePattern(String timePattern) {
        this.timePattern = timePattern;
    }

    @Override
    public String format(TimeTransform date, long delta) {
        if (delta >= 0) {
            if (delta == 0){
                return context.getResources().getString(R.string.timeformatter_now);
            }else if (delta / TimeTransform.MINUTE < 1) {
                return context.getResources().getQuantityString(R.plurals.timeformatter_seconds_ago, (int) delta, (int) delta);
            } else if (delta / TimeTransform.HOUR < 1) {
                int minutes = (int) (delta / TimeTransform.MINUTE);
                return context.getResources().getQuantityString(R.plurals.timeformatter_minutes_ago, minutes,minutes);
            } else if (delta / TimeTransform.DAY < 2 && new TimeTransform().getDay() == date.getDay()) {
                int hours = (int) (delta / TimeTransform.HOUR);
                return context.getResources().getQuantityString(R.plurals.timeformatter_hours_ago, hours, hours);
            } else if (delta / TimeTransform.DAY < 3 && new TimeTransform().getDay() == new TimeTransform(date.getTimestamp() + TimeTransform.DAY).getDay()) {
                return context.getResources().getString(R.string.timeformatter_yesterday, date.pattern(timePattern));
            }
        }
        return date.pattern(datePattern).toString() +" "+ date.pattern(timePattern).toString();
    }
}