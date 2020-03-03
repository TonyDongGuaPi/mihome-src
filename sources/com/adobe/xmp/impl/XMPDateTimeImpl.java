package com.adobe.xmp.impl;

import com.adobe.xmp.XMPDateTime;
import com.adobe.xmp.XMPException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

public class XMPDateTimeImpl implements XMPDateTime {

    /* renamed from: a  reason: collision with root package name */
    private int f688a = 0;
    private int b = 0;
    private int c = 0;
    private int d = 0;
    private int e = 0;
    private int f = 0;
    private TimeZone g = null;
    private int h;
    private boolean i = false;
    private boolean j = false;
    private boolean k = false;

    public XMPDateTimeImpl() {
    }

    public XMPDateTimeImpl(String str) throws XMPException {
        ISO8601Converter.a(str, this);
    }

    public XMPDateTimeImpl(Calendar calendar) {
        Date time = calendar.getTime();
        TimeZone timeZone = calendar.getTimeZone();
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeZone(timeZone);
        gregorianCalendar.setTime(time);
        this.f688a = gregorianCalendar.get(1);
        this.b = gregorianCalendar.get(2) + 1;
        this.c = gregorianCalendar.get(5);
        this.d = gregorianCalendar.get(11);
        this.e = gregorianCalendar.get(12);
        this.f = gregorianCalendar.get(13);
        this.h = gregorianCalendar.get(14) * 1000000;
        this.g = gregorianCalendar.getTimeZone();
        this.k = true;
        this.j = true;
        this.i = true;
    }

    public XMPDateTimeImpl(Date date, TimeZone timeZone) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(timeZone);
        gregorianCalendar.setTime(date);
        this.f688a = gregorianCalendar.get(1);
        this.b = gregorianCalendar.get(2) + 1;
        this.c = gregorianCalendar.get(5);
        this.d = gregorianCalendar.get(11);
        this.e = gregorianCalendar.get(12);
        this.f = gregorianCalendar.get(13);
        this.h = gregorianCalendar.get(14) * 1000000;
        this.g = timeZone;
        this.k = true;
        this.j = true;
        this.i = true;
    }

    public int a() {
        return this.f688a;
    }

    public void a(int i2) {
        this.f688a = Math.min(Math.abs(i2), 9999);
        this.i = true;
    }

    public void a(TimeZone timeZone) {
        this.g = timeZone;
        this.j = true;
        this.k = true;
    }

    public int b() {
        return this.b;
    }

    public void b(int i2) {
        if (i2 < 1) {
            this.b = 1;
        } else if (i2 > 12) {
            this.b = 12;
        } else {
            this.b = i2;
        }
        this.i = true;
    }

    public int c() {
        return this.c;
    }

    public void c(int i2) {
        if (i2 < 1) {
            this.c = 1;
        } else if (i2 > 31) {
            this.c = 31;
        } else {
            this.c = i2;
        }
        this.i = true;
    }

    public int compareTo(Object obj) {
        XMPDateTime xMPDateTime = (XMPDateTime) obj;
        long timeInMillis = l().getTimeInMillis() - xMPDateTime.l().getTimeInMillis();
        if (timeInMillis == 0) {
            timeInMillis = (long) (this.h - xMPDateTime.g());
        }
        return (int) Math.signum((float) timeInMillis);
    }

    public int d() {
        return this.d;
    }

    public void d(int i2) {
        this.d = Math.min(Math.abs(i2), 23);
        this.j = true;
    }

    public int e() {
        return this.e;
    }

    public void e(int i2) {
        this.e = Math.min(Math.abs(i2), 59);
        this.j = true;
    }

    public int f() {
        return this.f;
    }

    public void f(int i2) {
        this.f = Math.min(Math.abs(i2), 59);
        this.j = true;
    }

    public int g() {
        return this.h;
    }

    public void g(int i2) {
        this.h = i2;
        this.j = true;
    }

    public TimeZone h() {
        return this.g;
    }

    public boolean i() {
        return this.i;
    }

    public boolean j() {
        return this.j;
    }

    public boolean k() {
        return this.k;
    }

    public Calendar l() {
        GregorianCalendar gregorianCalendar = (GregorianCalendar) Calendar.getInstance(Locale.US);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        if (this.k) {
            gregorianCalendar.setTimeZone(this.g);
        }
        gregorianCalendar.set(1, this.f688a);
        gregorianCalendar.set(2, this.b - 1);
        gregorianCalendar.set(5, this.c);
        gregorianCalendar.set(11, this.d);
        gregorianCalendar.set(12, this.e);
        gregorianCalendar.set(13, this.f);
        gregorianCalendar.set(14, this.h / 1000000);
        return gregorianCalendar;
    }

    public String m() {
        return ISO8601Converter.a((XMPDateTime) this);
    }

    public String toString() {
        return m();
    }
}
