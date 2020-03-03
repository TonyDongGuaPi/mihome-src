package org.apache.commons.lang.time;

import com.miui.tsmclient.util.StringUtils;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateFormatUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final FastDateFormat f3411a = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ss");
    public static final FastDateFormat b = FastDateFormat.getInstance("yyyy-MM-dd'T'HH:mm:ssZZ");
    public static final FastDateFormat c = FastDateFormat.getInstance("yyyy-MM-dd");
    public static final FastDateFormat d = FastDateFormat.getInstance("yyyy-MM-ddZZ");
    public static final FastDateFormat e = FastDateFormat.getInstance("'T'HH:mm:ss");
    public static final FastDateFormat f = FastDateFormat.getInstance("'T'HH:mm:ssZZ");
    public static final FastDateFormat g = FastDateFormat.getInstance(StringUtils.EXPECT_TIME_FORMAT);
    public static final FastDateFormat h = FastDateFormat.getInstance("HH:mm:ssZZ");
    public static final FastDateFormat i = FastDateFormat.getInstance("EEE, dd MMM yyyy HH:mm:ss Z", Locale.US);

    public static String a(long j, String str) {
        return a(new Date(j), str, DateUtils.f3412a, (Locale) null);
    }

    public static String a(Date date, String str) {
        return a(date, str, DateUtils.f3412a, (Locale) null);
    }

    public static String a(long j, String str, Locale locale) {
        return a(new Date(j), str, DateUtils.f3412a, locale);
    }

    public static String a(Date date, String str, Locale locale) {
        return a(date, str, DateUtils.f3412a, locale);
    }

    public static String b(long j, String str) {
        return a(new Date(j), str, (TimeZone) null, (Locale) null);
    }

    public static String b(Date date, String str) {
        return a(date, str, (TimeZone) null, (Locale) null);
    }

    public static String a(Calendar calendar, String str) {
        return a(calendar, str, (TimeZone) null, (Locale) null);
    }

    public static String a(long j, String str, TimeZone timeZone) {
        return a(new Date(j), str, timeZone, (Locale) null);
    }

    public static String a(Date date, String str, TimeZone timeZone) {
        return a(date, str, timeZone, (Locale) null);
    }

    public static String a(Calendar calendar, String str, TimeZone timeZone) {
        return a(calendar, str, timeZone, (Locale) null);
    }

    public static String b(long j, String str, Locale locale) {
        return a(new Date(j), str, (TimeZone) null, locale);
    }

    public static String b(Date date, String str, Locale locale) {
        return a(date, str, (TimeZone) null, locale);
    }

    public static String a(Calendar calendar, String str, Locale locale) {
        return a(calendar, str, (TimeZone) null, locale);
    }

    public static String a(long j, String str, TimeZone timeZone, Locale locale) {
        return a(new Date(j), str, timeZone, locale);
    }

    public static String a(Date date, String str, TimeZone timeZone, Locale locale) {
        return FastDateFormat.getInstance(str, timeZone, locale).format(date);
    }

    public static String a(Calendar calendar, String str, TimeZone timeZone, Locale locale) {
        return FastDateFormat.getInstance(str, timeZone, locale).format(calendar);
    }
}
