package com.xiaomi.smarthome.library.common.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final long f18666a = 60000;
    public static final long b = 3600000;
    public static final long c = 86400000;
    public static final long d = 604800000;
    public static Date e = new Date(Long.MAX_VALUE);

    public static boolean a(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return a(instance, instance2);
    }

    public static boolean a(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) == calendar2.get(0) && calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean b(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (instance.get(0) == instance2.get(0) && instance.get(1) == instance2.get(1)) {
            return true;
        }
        return false;
    }

    public static boolean c(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (instance.get(0) == instance2.get(0) && instance.get(1) == instance2.get(1) && instance.get(2) == instance2.get(2)) {
            return true;
        }
        return false;
    }

    public static boolean d(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        if (instance.get(0) == instance2.get(0) && instance.get(1) == instance2.get(1) && instance.get(3) == instance2.get(3)) {
            return true;
        }
        return false;
    }

    public static boolean a(Date date) {
        return a(date, Calendar.getInstance().getTime());
    }

    public static boolean a(Calendar calendar) {
        return a(calendar, Calendar.getInstance());
    }

    public static boolean e(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return instance2.get(6) - instance.get(6) == 1;
    }

    public static boolean f(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return b(instance, instance2);
    }

    public static boolean b(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) < calendar2.get(0)) {
            return true;
        } else {
            if (calendar.get(0) > calendar2.get(0)) {
                return false;
            }
            if (calendar.get(1) < calendar2.get(1)) {
                return true;
            }
            if (calendar.get(1) > calendar2.get(1) || calendar.get(6) >= calendar2.get(6)) {
                return false;
            }
            return true;
        }
    }

    public static boolean g(Date date, Date date2) {
        if (date == null || date2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        return c(instance, instance2);
    }

    public static boolean c(Calendar calendar, Calendar calendar2) {
        if (calendar == null || calendar2 == null) {
            throw new IllegalArgumentException("The dates must not be null");
        } else if (calendar.get(0) < calendar2.get(0)) {
            return false;
        } else {
            if (calendar.get(0) > calendar2.get(0)) {
                return true;
            }
            if (calendar.get(1) < calendar2.get(1)) {
                return false;
            }
            if (calendar.get(1) > calendar2.get(1) || calendar.get(6) > calendar2.get(6)) {
                return true;
            }
            return false;
        }
    }

    public static boolean a(Date date, int i) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return a(instance, i);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean a(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.add(6, i);
            return c(calendar, instance) && !c(calendar, instance2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean b(Date date, int i) {
        if (date != null) {
            Calendar instance = Calendar.getInstance();
            instance.setTime(date);
            return b(instance, i);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static boolean b(Calendar calendar, int i) {
        if (calendar != null) {
            Calendar instance = Calendar.getInstance();
            Calendar instance2 = Calendar.getInstance();
            instance2.add(6, -i);
            return b(calendar, instance) && !b(calendar, instance2);
        }
        throw new IllegalArgumentException("The date must not be null");
    }

    public static Date b(Date date) {
        return c(date);
    }

    public static Date c(Date date) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 0);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime();
    }

    public static Date d(Date date) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(12, 0);
        instance.set(13, 0);
        instance.set(14, 0);
        return instance.getTime();
    }

    public static boolean e(Date date) {
        if (date == null) {
            return false;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        if (instance.get(11) <= 0 && instance.get(12) <= 0 && instance.get(13) <= 0 && instance.get(14) <= 0) {
            return false;
        }
        return true;
    }

    public static Date f(Date date) {
        if (date == null) {
            return null;
        }
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        instance.set(11, 23);
        instance.set(12, 59);
        instance.set(13, 59);
        instance.set(14, 999);
        return instance.getTime();
    }

    public static Date h(Date date, Date date2) {
        if (date == null && date2 == null) {
            return null;
        }
        if (date == null) {
            return date2;
        }
        return (date2 != null && !date.after(date2)) ? date2 : date;
    }

    public static Date i(Date date, Date date2) {
        if (date == null && date2 == null) {
            return null;
        }
        if (date == null) {
            return date2;
        }
        return (date2 != null && !date.before(date2)) ? date2 : date;
    }

    public static int j(Date date, Date date2) {
        int i;
        Calendar instance = Calendar.getInstance();
        instance.setTime(date);
        Calendar instance2 = Calendar.getInstance();
        instance2.setTime(date2);
        int i2 = instance.get(6);
        int i3 = instance2.get(6);
        boolean z = true;
        int i4 = instance.get(1);
        int i5 = instance2.get(1);
        if (i4 == i5) {
            return i3 - i2;
        }
        if (i4 > i5) {
            i = i2 - i3;
        } else {
            i = i3 - i2;
            z = false;
            int i6 = i4;
            i4 = i5;
            i5 = i6;
        }
        while (i5 < i4) {
            i = ((i5 % 4 != 0 || i5 % 100 == 0) && i5 % 400 != 0) ? i + 365 : i + 366;
            i5++;
        }
        return z ? -i : i;
    }
}
