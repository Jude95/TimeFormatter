package com.jude.timeformatter;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Locale;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class TimeFormatterTest {

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void optimalDate() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.ENGLISH;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long simple = 1524041871;
        Assert.assertEquals("Apr 18, 2018", TimeFormatter.optimalDate(InstrumentationRegistry.getContext(), simple).toString());
    }

    @Test
    public void optimalDateOther() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.CHINA;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long simple = 1524041871;
        Assert.assertEquals("2018-4-18", TimeFormatter.optimalDate(InstrumentationRegistry.getContext(), simple).toString());
    }

    @Test
    public void optimalDetail() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.ENGLISH;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long simple = 1524041871;
        Assert.assertEquals("Apr 18, 2018 16:57:51", TimeFormatter.optimalDetail(InstrumentationRegistry.getContext(), simple).toString());
    }

    @Test
    public void optimalDetailOther() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.CHINA;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long simple = 1524041871;
        Assert.assertEquals("2018-4-18 16:57:51", TimeFormatter.optimalDetail(InstrumentationRegistry.getContext(), simple).toString());
    }

    @Test
    public void recentEnglish() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.ENGLISH;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long justnow = System.currentTimeMillis() / 1000;
        Assert.assertEquals("just now", TimeFormatter.recent(InstrumentationRegistry.getContext(), justnow).toString());
        long secondAgo = System.currentTimeMillis() / 1000 - 1;
        Assert.assertEquals("1 second ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), secondAgo).toString());
        long secondsAgo = System.currentTimeMillis() / 1000 - 10;
        Assert.assertEquals("10 seconds ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), secondsAgo).toString());
        long minuteAgo = System.currentTimeMillis() / 1000 - 60;
        Assert.assertEquals("1 minute ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), minuteAgo).toString());
        long minutesAgo = System.currentTimeMillis() / 1000 - 120;
        Assert.assertEquals("2 minutes ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), minutesAgo).toString());
        long hourAgo = System.currentTimeMillis() / 1000 - 3600;
        Assert.assertEquals("1 hour ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), hourAgo).toString());
        long hoursAgo = System.currentTimeMillis() / 1000 - 7200;
        Assert.assertEquals("2 hours ago", TimeFormatter.recent(InstrumentationRegistry.getContext(), hoursAgo).toString());
        long yesterday = System.currentTimeMillis() / 1000 - 3600 * 24;
        Assert.assertEquals("Yesterday. "+new TimeTransform(yesterday).pattern("HH:mm"), TimeFormatter.recent(InstrumentationRegistry.getContext(), yesterday).toString());
        long other = System.currentTimeMillis() / 1000 - 3600 * 48;
        Assert.assertEquals(new TimeTransform(other).pattern("yyyy-MM-dd HH:mm").toString(), TimeFormatter.recent(InstrumentationRegistry.getContext(), other).toString());
    }

    @Test
    public void recentChinese() {
        Configuration config = new Configuration(InstrumentationRegistry.getContext().getResources().getConfiguration());
        config.locale = Locale.CHINA;
        Resources res = InstrumentationRegistry.getContext().getResources();
        res.updateConfiguration(config, res.getDisplayMetrics());

        long justnow = System.currentTimeMillis() / 1000;
        Assert.assertEquals("刚刚", TimeFormatter.recent(InstrumentationRegistry.getContext(), justnow).toString());
        long secondAgo = System.currentTimeMillis() / 1000 - 1;
        Assert.assertEquals("1 秒前", TimeFormatter.recent(InstrumentationRegistry.getContext(), secondAgo).toString());
        long secondsAgo = System.currentTimeMillis() / 1000 - 10;
        Assert.assertEquals("10 秒前", TimeFormatter.recent(InstrumentationRegistry.getContext(), secondsAgo).toString());
        long minuteAgo = System.currentTimeMillis() / 1000 - 60;
        Assert.assertEquals("1 分钟前", TimeFormatter.recent(InstrumentationRegistry.getContext(), minuteAgo).toString());
        long minutesAgo = System.currentTimeMillis() / 1000 - 120;
        Assert.assertEquals("2 分钟前", TimeFormatter.recent(InstrumentationRegistry.getContext(), minutesAgo).toString());
        long hourAgo = System.currentTimeMillis() / 1000 - 3600;
        Assert.assertEquals("1 小时前", TimeFormatter.recent(InstrumentationRegistry.getContext(), hourAgo).toString());
        long hoursAgo = System.currentTimeMillis() / 1000 - 7200;
        Assert.assertEquals("2 小时前", TimeFormatter.recent(InstrumentationRegistry.getContext(), hoursAgo).toString());
        long yesterday = System.currentTimeMillis() / 1000 - 3600 * 24;
        Assert.assertEquals("昨天. "+new TimeTransform(yesterday).pattern("HH:mm"), TimeFormatter.recent(InstrumentationRegistry.getContext(), yesterday).toString());
        long other = System.currentTimeMillis() / 1000 - 3600 * 48;
        Assert.assertEquals(new TimeTransform(other).pattern("yyyy-MM-dd HH:mm").toString(), TimeFormatter.recent(InstrumentationRegistry.getContext(), other).toString());
    }


}