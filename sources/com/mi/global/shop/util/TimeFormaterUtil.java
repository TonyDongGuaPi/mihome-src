package com.mi.global.shop.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class TimeFormaterUtil {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7112a = 3600000;
    private static final long b = 86400000;

    public static String a(long j) {
        return new SimpleDateFormat("HH:mm", Locale.ENGLISH).format(new Date(j));
    }
}
