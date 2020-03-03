package com.xiaomi.push;

import com.xiaomi.push.cd;

public class bz extends cd.d {

    /* renamed from: a  reason: collision with root package name */
    protected String f12663a = "MessageDeleteJob";

    public bz(String str, String str2, String[] strArr, String str3) {
        super(str, str2, strArr);
        this.f12663a = str3;
    }

    public static bz a(String str) {
        return new bz(str, "status = ?", new String[]{String.valueOf(2)}, "a job build to delete uploaded job");
    }
}
