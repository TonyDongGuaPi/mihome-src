package com.mi.global.shop.util;

import java.text.SimpleDateFormat;

public class TimeFormater {

    /* renamed from: a  reason: collision with root package name */
    private static final long f7111a = 3600000;
    private static final long b = 86400000;
    private static final long c = 2592000000L;

    public static String a(long j) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd MMM yyyy");
        long j2 = j * 1000;
        Long valueOf = Long.valueOf(System.currentTimeMillis() - j2);
        if (valueOf.longValue() < 3600000) {
            return "Just now";
        }
        if (valueOf.longValue() >= 3600000 && valueOf.longValue() < 7200000) {
            return (valueOf.longValue() / 3600000) + " hour ago";
        } else if (valueOf.longValue() >= 7200000 && valueOf.longValue() < 86400000) {
            return (valueOf.longValue() / 3600000) + " hours ago";
        } else if (valueOf.longValue() >= 86400000 && valueOf.longValue() < 172800000) {
            return (valueOf.longValue() / 86400000) + " day ago";
        } else if (valueOf.longValue() < 172800000 || valueOf.longValue() >= 432000000) {
            return simpleDateFormat.format(Long.valueOf(j2));
        } else {
            return (valueOf.longValue() / 86400000) + " days ago";
        }
    }

    public static String b(long j) {
        return new SimpleDateFormat("MM/dd HH:mm").format(Long.valueOf(j * 1000));
    }
}
