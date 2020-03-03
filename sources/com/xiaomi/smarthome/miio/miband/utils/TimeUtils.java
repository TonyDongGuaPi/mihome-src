package com.xiaomi.smarthome.miio.miband.utils;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeUtils {
    public static long a(String str, String str2) {
        return new SimpleDateFormat(str2, Locale.CHINA).parse(str, new ParsePosition(0)).getTime();
    }

    public static String a(Date date, String str) {
        return new SimpleDateFormat(str, Locale.CHINA).format(date);
    }
}
