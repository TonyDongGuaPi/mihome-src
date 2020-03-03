package org.cybergarage.http;

import java.util.Calendar;
import java.util.TimeZone;

public class Date {
    private static final String[] MONTH_STRING = {"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    private static final String[] WEEK_STRING = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
    private Calendar cal;

    public Date(Calendar calendar) {
        this.cal = calendar;
    }

    public Calendar getCalendar() {
        return this.cal;
    }

    public int getHour() {
        return getCalendar().get(11);
    }

    public int getMinute() {
        return getCalendar().get(12);
    }

    public int getSecond() {
        return getCalendar().get(13);
    }

    public static final Date getLocalInstance() {
        return new Date(Calendar.getInstance());
    }

    public static final Date getInstance() {
        return new Date(Calendar.getInstance(TimeZone.getTimeZone("GMT")));
    }

    public static final String toDateString(int i) {
        if (i >= 10) {
            return Integer.toString(i);
        }
        return "0" + Integer.toString(i);
    }

    public static final String toMonthString(int i) {
        int i2 = i + 0;
        return (i2 < 0 || i2 >= 12) ? "" : MONTH_STRING[i2];
    }

    public static final String toWeekString(int i) {
        int i2 = i - 1;
        return (i2 < 0 || i2 >= 7) ? "" : WEEK_STRING[i2];
    }

    public static final String toTimeString(int i) {
        String str = "";
        if (i < 10) {
            str = str + "0";
        }
        return str + Integer.toString(i);
    }

    public String getDateString() {
        Calendar calendar = getCalendar();
        return toWeekString(calendar.get(7)) + ", " + toTimeString(calendar.get(5)) + " " + toMonthString(calendar.get(2)) + " " + Integer.toString(calendar.get(1)) + " " + toTimeString(calendar.get(11)) + ":" + toTimeString(calendar.get(12)) + ":" + toTimeString(calendar.get(13)) + " GMT";
    }

    public String getTimeString() {
        Calendar calendar = getCalendar();
        StringBuilder sb = new StringBuilder();
        sb.append(toDateString(calendar.get(11)));
        sb.append(calendar.get(13) % 2 == 0 ? ":" : " ");
        sb.append(toDateString(calendar.get(12)));
        return sb.toString();
    }
}
