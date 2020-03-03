package com.mi.global.bbs.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUtils {
    public static String data2StrPattern(long j, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(new Date(j));
    }

    public static String data2Str(long j) {
        return data2StrPattern(j, "yyyy-MM-dd hh:mm");
    }

    public static String dateHMSStr(long j) {
        return data2StrPattern(j, "yyyy-MM-dd hh:mm:ss");
    }

    public static String localDateHMSStr(long j) {
        return localDataStrPattern(j, "yyyy-MM-dd HH:mm:ss");
    }

    public static String localDataStrPattern(long j, String str) {
        return new SimpleDateFormat(str, Locale.getDefault()).format(new Date(j));
    }

    public static String getTimeZoneName() {
        return TimeZone.getDefault().getDisplayName(false, 0);
    }

    public static String getTimeZoneId() {
        return TimeZone.getDefault().getID();
    }
}
