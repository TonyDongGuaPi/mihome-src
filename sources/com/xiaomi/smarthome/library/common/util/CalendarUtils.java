package com.xiaomi.smarthome.library.common.util;

import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.miio.LanguageUtil;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class CalendarUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final long f18650a = 1000;
    public static final long b = 60000;
    public static final long c = 3600000;
    public static final long d = 86400000;
    private static Calendar e;
    private static Calendar f;

    private static synchronized void b() {
        synchronized (CalendarUtils.class) {
            if (e == null) {
                e = Calendar.getInstance();
                e.setTimeZone(TimeZone.getTimeZone("GMT+8"));
                f = Calendar.getInstance();
            }
        }
    }

    public static Calendar a() {
        b();
        return f;
    }

    public static long a(long j, int i, int i2, int i3) {
        b();
        e.setTimeInMillis(j + (((long) i) * 86400000));
        e.set(11, i2);
        e.set(12, i3);
        return e.getTimeInMillis();
    }

    public static boolean a(long j, long j2, int i) {
        b();
        if (j2 <= a(j, i, 0, 0) || j2 >= a(j, i, 24, 0)) {
            return false;
        }
        return true;
    }

    public static String a(long j) {
        Object obj;
        Object obj2;
        b();
        Calendar a2 = a();
        a2.setTimeInMillis(j);
        if (System.currentTimeMillis() - j < 86400000) {
            int i = a2.get(11);
            int i2 = a2.get(12);
            StringBuilder sb = new StringBuilder();
            sb.append(i < 10 ? "0" : "");
            sb.append(i);
            sb.append(":");
            sb.append(i2 < 10 ? "0" : "");
            sb.append(i2);
            return sb.toString();
        }
        int i3 = a2.get(2) + 1;
        int i4 = a2.get(5);
        StringBuilder sb2 = new StringBuilder();
        if (i3 < 10) {
            obj = "0" + i3;
        } else {
            obj = Integer.valueOf(i3);
        }
        sb2.append(obj);
        sb2.append("/");
        if (i4 < 10) {
            obj2 = "0" + i4;
        } else {
            obj2 = Integer.valueOf(i4);
        }
        sb2.append(obj2);
        return sb2.toString();
    }

    public static String b(long j) {
        long j2;
        b();
        TimeZone timeZone = e.getTimeZone();
        TimeZone timeZone2 = f.getTimeZone();
        if (timeZone == null || timeZone2 == null || timeZone.hasSameRules(timeZone2)) {
            j2 = j;
        } else {
            long currentTimeMillis = System.currentTimeMillis() / 1000;
            j2 = ((long) (timeZone.getOffset(currentTimeMillis) - timeZone2.getOffset(currentTimeMillis))) + j;
        }
        long currentTimeMillis2 = System.currentTimeMillis();
        Locale locale = CoreApi.a().I() == null ? Locale.getDefault() : CoreApi.a().I();
        if (!(locale == null || locale.equals(Locale.CHINA))) {
            if (a(currentTimeMillis2, j2, 0)) {
                return new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(new Timestamp(j));
            }
            return new SimpleDateFormat(LanguageUtil.c(locale)).format(new Timestamp(j));
        } else if (a(currentTimeMillis2, j2, 0)) {
            return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Timestamp(j));
        } else {
            if (a(currentTimeMillis2, j2, -1)) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.yesterday);
            }
            if (a(currentTimeMillis2, j2, -2)) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.before_yesterday);
            }
            Date date = new Date(j);
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            e.setTime(new Date(currentTimeMillis2));
            if (instance.get(1) != e.get(1)) {
                return new SimpleDateFormat(LanguageUtil.c(locale)).format(new Timestamp(j));
            }
            return "" + (instance.get(2) + 1) + XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.month) + instance.get(5) + XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.day2);
        }
    }

    public static String c(long j) {
        b();
        String format = new SimpleDateFormat(LanguageUtil.c(CoreApi.a().I())).format(new Timestamp(j));
        e.setTimeInMillis(j);
        return format + " " + (e.get(11) + ":" + e.get(12) + ":" + e.get(13));
    }

    public static String d(long j) {
        b();
        long currentTimeMillis = System.currentTimeMillis();
        if (a(currentTimeMillis, j, 0)) {
            return new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Timestamp(j));
        }
        if (a(currentTimeMillis, j, -1)) {
            String a2 = XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.yesterday);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.CHINA);
            return a2 + " " + simpleDateFormat.format(new Timestamp(j));
        } else if (!a(currentTimeMillis, j, -2)) {
            return new SimpleDateFormat("MM/dd HH:mm", Locale.CHINA).format(new Timestamp(j));
        } else {
            String a3 = XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.before_yesterday);
            SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("HH:mm", Locale.CHINA);
            return a3 + " " + simpleDateFormat2.format(new Timestamp(j));
        }
    }

    public static String[] e(long j) {
        b();
        long currentTimeMillis = System.currentTimeMillis();
        if (a(currentTimeMillis, j, 0)) {
            return new String[]{new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Timestamp(j))};
        } else if (a(currentTimeMillis, j, -1)) {
            return new String[]{XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.yesterday), new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Timestamp(j))};
        } else {
            return new String[]{new SimpleDateFormat(LanguageUtil.c(CoreApi.a().I())).format(new Timestamp(j)), new SimpleDateFormat("HH:mm", Locale.CHINA).format(new Timestamp(j))};
        }
    }

    public static int a(int i, int i2) {
        switch (i) {
            case 0:
            case 2:
            case 4:
            case 6:
            case 7:
            case 9:
            case 11:
                return 31;
            case 1:
                return ((i2 % 4 != 0 || i2 % 100 == 0) && i2 % 400 != 0) ? 28 : 29;
            case 3:
            case 5:
            case 8:
            case 10:
                return 30;
            default:
                throw new IllegalArgumentException("Invalid Month");
        }
    }

    public static boolean a(Date date, Date date2) {
        return date.getTime() / 86400000 == date2.getTime() / 86400000;
    }

    public static int b(Date date, Date date2) {
        return (int) ((date.getTime() - date2.getTime()) / 86400000);
    }

    public static int a(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(5);
    }

    public static int b(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(2) + 1;
    }

    public static Date a(Date date, int i) {
        return new Date(((date.getTime() / 1000) + (((long) i) * 24 * 3600)) * 1000);
    }

    public static String f(long j) {
        long timeInMillis = Calendar.getInstance(DateTimeHelper.f18665a).getTimeInMillis() - j;
        long b2 = DateTimeHelper.b();
        long a2 = DateTimeHelper.a(b2 - 60000);
        Locale I = CoreApi.a().I();
        if (j < b2) {
            if (j >= a2) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.yesterday);
            }
            return new SimpleDateFormat(LanguageUtil.c(I)).format(new Date(j));
        } else if (timeInMillis >= 3600000) {
            return new SimpleDateFormat("HH:mm").format(new Date(j));
        } else {
            int i = (int) ((timeInMillis / 1000) / 60);
            if (i <= 0) {
                return XMStringUtils.a(SHApplication.getAppContext(), (int) R.string.just_now);
            }
            return XMStringUtils.a(SHApplication.getAppContext(), (int) R.plurals.before_minute, i, (Object) Integer.valueOf(i));
        }
    }
}
