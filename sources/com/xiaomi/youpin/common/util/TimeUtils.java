package com.xiaomi.youpin.common.util;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.media.ExifInterface;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public final class TimeUtils {
    @SuppressLint({"SimpleDateFormat"})

    /* renamed from: a  reason: collision with root package name */
    private static final DateFormat f23282a = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final String[] b = {"猴", "鸡", "狗", "猪", "鼠", "牛", "虎", "兔", "龙", "蛇", "马", "羊"};
    private static final int[] c = {20, 19, 21, 21, 21, 22, 23, 23, 23, 24, 23, 22};
    private static final String[] d = {"水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "魔羯座"};

    private static long g(long j, int i) {
        return j * ((long) i);
    }

    private TimeUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    public static String a(long j) {
        return a(j, f23282a);
    }

    public static String a(long j, @NonNull DateFormat dateFormat) {
        return dateFormat.format(new Date(j));
    }

    public static long a(String str) {
        return a(str, f23282a);
    }

    public static long a(String str, @NonNull DateFormat dateFormat) {
        try {
            return dateFormat.parse(str).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
            return -1;
        }
    }

    public static Date b(String str) {
        return b(str, f23282a);
    }

    public static Date b(String str, @NonNull DateFormat dateFormat) {
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String a(Date date) {
        return a(date, f23282a);
    }

    public static String a(Date date, @NonNull DateFormat dateFormat) {
        return dateFormat.format(date);
    }

    public static long b(Date date) {
        return date.getTime();
    }

    public static Date b(long j) {
        return new Date(j);
    }

    public static long a(String str, String str2, int i) {
        return a(str, str2, f23282a, i);
    }

    public static long a(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        return h(Math.abs(a(str, dateFormat) - a(str2, dateFormat)), i);
    }

    public static long a(Date date, Date date2, int i) {
        return h(Math.abs(b(date) - b(date2)), i);
    }

    public static long a(long j, long j2, int i) {
        return h(Math.abs(j - j2), i);
    }

    public static String b(String str, String str2, int i) {
        return i(Math.abs(a(str, f23282a) - a(str2, f23282a)), i);
    }

    public static String b(String str, String str2, @NonNull DateFormat dateFormat, int i) {
        return i(Math.abs(a(str, dateFormat) - a(str2, dateFormat)), i);
    }

    public static String b(Date date, Date date2, int i) {
        return i(Math.abs(b(date) - b(date2)), i);
    }

    public static String b(long j, long j2, int i) {
        return i(Math.abs(j - j2), i);
    }

    public static long a() {
        return System.currentTimeMillis();
    }

    public static String b() {
        return a(System.currentTimeMillis(), f23282a);
    }

    public static String a(@NonNull DateFormat dateFormat) {
        return a(System.currentTimeMillis(), dateFormat);
    }

    public static Date c() {
        return new Date();
    }

    public static long a(String str, int i) {
        return a(b(), str, f23282a, i);
    }

    public static long a(String str, @NonNull DateFormat dateFormat, int i) {
        return a(a(dateFormat), str, dateFormat, i);
    }

    public static long a(Date date, int i) {
        return a(new Date(), date, i);
    }

    public static long a(long j, int i) {
        return a(System.currentTimeMillis(), j, i);
    }

    public static String b(String str, int i) {
        return b(b(), str, f23282a, i);
    }

    public static String b(String str, @NonNull DateFormat dateFormat, int i) {
        return b(a(dateFormat), str, dateFormat, i);
    }

    public static String b(Date date, int i) {
        return b(c(), date, i);
    }

    public static String b(long j, int i) {
        return b(System.currentTimeMillis(), j, i);
    }

    public static String c(String str) {
        return c(str, f23282a);
    }

    public static String c(String str, @NonNull DateFormat dateFormat) {
        return c(a(str, dateFormat));
    }

    public static String c(Date date) {
        return c(date.getTime());
    }

    public static String c(long j) {
        long currentTimeMillis = System.currentTimeMillis() - j;
        if (currentTimeMillis < 0) {
            return String.format("%tc", new Object[]{Long.valueOf(j)});
        } else if (currentTimeMillis < 1000) {
            return "刚刚";
        } else {
            if (currentTimeMillis < 60000) {
                return String.format(Locale.getDefault(), "%d秒前", new Object[]{Long.valueOf(currentTimeMillis / 1000)});
            } else if (currentTimeMillis < 3600000) {
                return String.format(Locale.getDefault(), "%d分钟前", new Object[]{Long.valueOf(currentTimeMillis / 60000)});
            } else {
                long d2 = d();
                if (j >= d2) {
                    return String.format("今天%tR", new Object[]{Long.valueOf(j)});
                } else if (j >= d2 - 86400000) {
                    return String.format("昨天%tR", new Object[]{Long.valueOf(j)});
                } else {
                    return String.format("%tF", new Object[]{Long.valueOf(j)});
                }
            }
        }
    }

    private static long d() {
        Calendar instance = Calendar.getInstance();
        instance.set(11, 0);
        instance.set(13, 0);
        instance.set(12, 0);
        instance.set(14, 0);
        return instance.getTimeInMillis();
    }

    public static long c(long j, long j2, int i) {
        return j + g(j2, i);
    }

    public static long a(String str, long j, int i) {
        return a(str, f23282a, j, i);
    }

    public static long a(String str, @NonNull DateFormat dateFormat, long j, int i) {
        return a(str, dateFormat) + g(j, i);
    }

    public static long a(Date date, long j, int i) {
        return b(date) + g(j, i);
    }

    public static String d(long j, long j2, int i) {
        return a(j, f23282a, j2, i);
    }

    public static String a(long j, @NonNull DateFormat dateFormat, long j2, int i) {
        return a(j + g(j2, i), dateFormat);
    }

    public static String b(String str, long j, int i) {
        return b(str, f23282a, j, i);
    }

    public static String b(String str, @NonNull DateFormat dateFormat, long j, int i) {
        return a(a(str, dateFormat) + g(j, i), dateFormat);
    }

    public static String b(Date date, long j, int i) {
        return a(date, f23282a, j, i);
    }

    public static String a(Date date, @NonNull DateFormat dateFormat, long j, int i) {
        return a(b(date) + g(j, i), dateFormat);
    }

    public static Date e(long j, long j2, int i) {
        return b(j + g(j2, i));
    }

    public static Date c(String str, long j, int i) {
        return c(str, f23282a, j, i);
    }

    public static Date c(String str, @NonNull DateFormat dateFormat, long j, int i) {
        return b(a(str, dateFormat) + g(j, i));
    }

    public static Date c(Date date, long j, int i) {
        return b(b(date) + g(j, i));
    }

    public static long c(long j, int i) {
        return c(a(), j, i);
    }

    public static String d(long j, int i) {
        return a(j, f23282a, i);
    }

    public static String a(long j, @NonNull DateFormat dateFormat, int i) {
        return a(a(), dateFormat, j, i);
    }

    public static Date e(long j, int i) {
        return e(a(), j, i);
    }

    public static boolean d(String str) {
        return d(a(str, f23282a));
    }

    public static boolean d(String str, @NonNull DateFormat dateFormat) {
        return d(a(str, dateFormat));
    }

    public static boolean d(Date date) {
        return d(date.getTime());
    }

    public static boolean d(long j) {
        long d2 = d();
        return j >= d2 && j < d2 + 86400000;
    }

    public static boolean e(String str) {
        return e(b(str, f23282a));
    }

    public static boolean e(String str, @NonNull DateFormat dateFormat) {
        return e(b(str, dateFormat));
    }

    public static boolean e(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return a(instance.get(1));
    }

    public static boolean e(long j) {
        return e(b(j));
    }

    public static boolean a(int i) {
        return (i % 4 == 0 && i % 100 != 0) || i % 400 == 0;
    }

    public static String f(String str) {
        return f(b(str, f23282a));
    }

    public static String f(String str, @NonNull DateFormat dateFormat) {
        return f(b(str, dateFormat));
    }

    public static String f(Date date) {
        return new SimpleDateFormat(ExifInterface.LONGITUDE_EAST, Locale.CHINA).format(date);
    }

    public static String f(long j) {
        return f(new Date(j));
    }

    public static String g(String str) {
        return g(b(str, f23282a));
    }

    public static String g(String str, @NonNull DateFormat dateFormat) {
        return g(b(str, dateFormat));
    }

    public static String g(Date date) {
        return new SimpleDateFormat("EEEE", Locale.US).format(date);
    }

    public static String g(long j) {
        return g(new Date(j));
    }

    @Deprecated
    public static int h(String str) {
        return h(b(str, f23282a));
    }

    @Deprecated
    public static int h(String str, @NonNull DateFormat dateFormat) {
        return h(b(str, dateFormat));
    }

    @Deprecated
    public static int h(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(7);
    }

    @Deprecated
    public static int h(long j) {
        return h(b(j));
    }

    @Deprecated
    public static int i(String str) {
        return i(b(str, f23282a));
    }

    @Deprecated
    public static int i(String str, @NonNull DateFormat dateFormat) {
        return i(b(str, dateFormat));
    }

    @Deprecated
    public static int i(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(4);
    }

    @Deprecated
    public static int i(long j) {
        return i(b(j));
    }

    @Deprecated
    public static int j(String str) {
        return j(b(str, f23282a));
    }

    @Deprecated
    public static int j(String str, @NonNull DateFormat dateFormat) {
        return j(b(str, dateFormat));
    }

    @Deprecated
    public static int j(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(3);
    }

    @Deprecated
    public static int j(long j) {
        return j(b(j));
    }

    public static int c(String str, int i) {
        return c(b(str, f23282a), i);
    }

    public static int c(String str, @NonNull DateFormat dateFormat, int i) {
        return c(b(str, dateFormat), i);
    }

    public static int c(Date date, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return instance.get(i);
    }

    public static int f(long j, int i) {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j);
        return instance.get(i);
    }

    public static String k(String str) {
        return k(b(str, f23282a));
    }

    public static String k(String str, @NonNull DateFormat dateFormat) {
        return k(b(str, dateFormat));
    }

    public static String k(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return b[instance.get(1) % 12];
    }

    public static String k(long j) {
        return k(b(j));
    }

    public static String b(int i) {
        return b[i % 12];
    }

    public static String l(String str) {
        return l(b(str, f23282a));
    }

    public static String l(String str, @NonNull DateFormat dateFormat) {
        return l(b(str, dateFormat));
    }

    public static String l(Date date) {
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        return a(instance.get(2) + 1, instance.get(5));
    }

    public static String l(long j) {
        return l(b(j));
    }

    public static String a(int i, int i2) {
        String[] strArr = d;
        int i3 = i - 1;
        if (i2 < c[i3]) {
            i3 = (i + 10) % 12;
        }
        return strArr[i3];
    }

    private static long h(long j, int i) {
        return j / ((long) i);
    }

    private static String i(long j, int i) {
        if (j < 0 || i <= 0) {
            return null;
        }
        int min = Math.min(i, 5);
        String[] strArr = {"天", "小时", "分钟", "秒", "毫秒"};
        if (j == 0) {
            return 0 + strArr[min - 1];
        }
        StringBuilder sb = new StringBuilder();
        int[] iArr = {86400000, 3600000, 60000, 1000, 1};
        for (int i2 = 0; i2 < min; i2++) {
            if (j >= ((long) iArr[i2])) {
                long j2 = j / ((long) iArr[i2]);
                j -= ((long) iArr[i2]) * j2;
                sb.append(j2);
                sb.append(strArr[i2]);
            }
        }
        return sb.toString();
    }
}
