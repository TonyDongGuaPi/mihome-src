package com.xiaomi.smarthome.framework.api.model;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class OneTimePasswordInfo {

    /* renamed from: a  reason: collision with root package name */
    private long f16455a;
    private long b;
    private long c;
    private String d;

    public OneTimePasswordInfo(long j, long j2, long j3, String str) {
        this.f16455a = j;
        this.b = j2;
        this.c = j3;
        this.d = str;
    }

    public long a() {
        return this.f16455a;
    }

    public void a(long j) {
        this.f16455a = j;
    }

    public long b() {
        return this.b;
    }

    public void b(long j) {
        this.b = j;
    }

    public long c() {
        return this.c;
    }

    public void c(long j) {
        this.c = j;
    }

    public String d() {
        return this.d;
    }

    public void a(String str) {
        this.d = str;
    }

    public String e() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.b * 1000);
        return "" + (instance.get(2) + 1);
    }

    public String f() {
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(this.b * 1000);
        int i = instance.get(5);
        return "" + i;
    }

    public String g() {
        return d(this.b);
    }

    public String h() {
        return d(this.c);
    }

    private String d(long j) {
        SimpleDateFormat simpleDateFormat;
        Calendar instance = Calendar.getInstance();
        instance.setTimeInMillis(j * 1000);
        if (Locale.getDefault().equals(Locale.CHINA)) {
            simpleDateFormat = new SimpleDateFormat("MM月dd日HH:mm", Locale.CHINA);
        } else {
            simpleDateFormat = new SimpleDateFormat("HH:mm MM/dd", Locale.UK);
        }
        return simpleDateFormat.format(instance.getTime());
    }

    public static String a(int i, int i2) {
        StringBuilder sb = new StringBuilder();
        sb.append(i);
        sb.append(":");
        if (i2 < 10) {
            sb.append("0" + i2);
        } else {
            sb.append(i2);
        }
        return sb.toString();
    }
}
