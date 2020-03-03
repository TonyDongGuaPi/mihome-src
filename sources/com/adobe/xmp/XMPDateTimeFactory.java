package com.adobe.xmp;

import com.adobe.xmp.impl.XMPDateTimeImpl;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;

public final class XMPDateTimeFactory {

    /* renamed from: a  reason: collision with root package name */
    private static final TimeZone f679a = TimeZone.getTimeZone("UTC");

    private XMPDateTimeFactory() {
    }

    public static XMPDateTime a() {
        return new XMPDateTimeImpl();
    }

    public static XMPDateTime a(int i, int i2, int i3) {
        XMPDateTimeImpl xMPDateTimeImpl = new XMPDateTimeImpl();
        xMPDateTimeImpl.a(i);
        xMPDateTimeImpl.b(i2);
        xMPDateTimeImpl.c(i3);
        return xMPDateTimeImpl;
    }

    public static XMPDateTime a(int i, int i2, int i3, int i4, int i5, int i6, int i7) {
        XMPDateTimeImpl xMPDateTimeImpl = new XMPDateTimeImpl();
        xMPDateTimeImpl.a(i);
        xMPDateTimeImpl.b(i2);
        xMPDateTimeImpl.c(i3);
        xMPDateTimeImpl.d(i4);
        xMPDateTimeImpl.e(i5);
        xMPDateTimeImpl.f(i6);
        xMPDateTimeImpl.g(i7);
        return xMPDateTimeImpl;
    }

    public static XMPDateTime a(XMPDateTime xMPDateTime) {
        Calendar l = xMPDateTime.l();
        l.setTimeZone(TimeZone.getDefault());
        return new XMPDateTimeImpl(l);
    }

    public static XMPDateTime a(String str) throws XMPException {
        return new XMPDateTimeImpl(str);
    }

    public static XMPDateTime a(Calendar calendar) {
        return new XMPDateTimeImpl(calendar);
    }

    public static XMPDateTime b() {
        return new XMPDateTimeImpl((Calendar) new GregorianCalendar());
    }

    public static XMPDateTime b(XMPDateTime xMPDateTime) {
        long timeInMillis = xMPDateTime.l().getTimeInMillis();
        GregorianCalendar gregorianCalendar = new GregorianCalendar(f679a);
        gregorianCalendar.setGregorianChange(new Date(Long.MIN_VALUE));
        gregorianCalendar.setTimeInMillis(timeInMillis);
        return new XMPDateTimeImpl((Calendar) gregorianCalendar);
    }

    public static XMPDateTime c(XMPDateTime xMPDateTime) {
        long timeInMillis = xMPDateTime.l().getTimeInMillis();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();
        gregorianCalendar.setTimeInMillis(timeInMillis);
        return new XMPDateTimeImpl((Calendar) gregorianCalendar);
    }
}
