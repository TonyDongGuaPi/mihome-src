package com.xiaomi.jr.reminder;

import com.google.gson.annotations.SerializedName;

public class ReminderInfo {
    @SerializedName("title")

    /* renamed from: a  reason: collision with root package name */
    private String f11010a;
    @SerializedName("description")
    private String b;
    @SerializedName("startTime")
    private long c;

    public String a() {
        return this.f11010a;
    }

    public void a(String str) {
        this.f11010a = str;
    }

    public String b() {
        return this.b;
    }

    public void b(String str) {
        this.b = str;
    }

    public long c() {
        return this.c;
    }

    public void a(long j) {
        this.c = j;
    }
}
