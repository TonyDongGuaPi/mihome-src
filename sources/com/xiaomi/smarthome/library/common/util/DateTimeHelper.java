package com.xiaomi.smarthome.library.common.util;

import android.text.TextUtils;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import org.xml.sax.SAXException;

public class DateTimeHelper {

    /* renamed from: a  reason: collision with root package name */
    public static final TimeZone f18665a = TimeZone.getTimeZone("Asia/Shanghai");
    public static final long b = 86400000;
    public static final long c = 3600000;
    public static final long d = 60000;
    public static final long e = 1440;
    public static final long f = 60;
    private static final String g = "common/DateTimeHelper";

    public static long a() {
        return Calendar.getInstance(f18665a).getTimeInMillis();
    }

    public static long b() {
        return a(a());
    }

    public static long a(long j) {
        return j - (j % 86400000);
    }

    public static long b(long j) {
        return (j - (j % 86400000)) + 86400000;
    }

    public static long c() {
        return c(a());
    }

    public static long c(long j) {
        return (j - a(j)) / 60000;
    }

    public static long d() {
        return d(a());
    }

    public static long d(long j) {
        return c(j) % 60;
    }

    public static long a(String str) throws SAXException {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        try {
            gregorianCalendar.setTime(new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(str));
            gregorianCalendar.setTimeZone(f18665a);
            return gregorianCalendar.getTimeInMillis();
        } catch (ParseException e2) {
            Log.e(g, "Failed to parse date", e2);
            return -1;
        }
    }

    public static String a(Date date) {
        switch (date.getDay()) {
            case 0:
                return "周日";
            case 1:
                return "周一";
            case 2:
                return "周二";
            case 3:
                return "周三";
            case 4:
                return "周四";
            case 5:
                return "周五";
            default:
                return "周六";
        }
    }

    public static String b(String str) {
        return new SimpleDateFormat(str).format(new Date(System.currentTimeMillis()));
    }
}
