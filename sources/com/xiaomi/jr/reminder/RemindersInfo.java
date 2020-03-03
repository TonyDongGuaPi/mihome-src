package com.xiaomi.jr.reminder;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class RemindersInfo {
    @SerializedName("organizer")

    /* renamed from: a  reason: collision with root package name */
    private String f11019a;
    @SerializedName("priorMinutes")
    private long b;
    @SerializedName("reminders")
    private List<ReminderInfo> c;

    public String a() {
        return this.f11019a;
    }

    public void a(String str) {
        this.f11019a = str;
    }

    public long b() {
        return this.b;
    }

    public void a(long j) {
        this.b = j;
    }

    public List<ReminderInfo> c() {
        return this.c;
    }

    public void a(List<ReminderInfo> list) {
        this.c = list;
    }
}
