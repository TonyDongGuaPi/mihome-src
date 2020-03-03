package com.lidroid.xutils.db.table;

import android.text.TextUtils;
import java.util.Date;
import java.util.HashMap;

public class DbModel {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, String> f6326a = new HashMap<>();

    public String a(String str) {
        return this.f6326a.get(str);
    }

    public int b(String str) {
        return Integer.valueOf(this.f6326a.get(str)).intValue();
    }

    public boolean c(String str) {
        String str2 = this.f6326a.get(str);
        if (str2 != null) {
            return str2.length() == 1 ? "1".equals(str2) : Boolean.valueOf(str2).booleanValue();
        }
        return false;
    }

    public double d(String str) {
        return Double.valueOf(this.f6326a.get(str)).doubleValue();
    }

    public float e(String str) {
        return Float.valueOf(this.f6326a.get(str)).floatValue();
    }

    public long f(String str) {
        return Long.valueOf(this.f6326a.get(str)).longValue();
    }

    public Date g(String str) {
        return new Date(Long.valueOf(this.f6326a.get(str)).longValue());
    }

    public java.sql.Date h(String str) {
        return new java.sql.Date(Long.valueOf(this.f6326a.get(str)).longValue());
    }

    public void a(String str, String str2) {
        this.f6326a.put(str, str2);
    }

    public HashMap<String, String> a() {
        return this.f6326a;
    }

    public boolean i(String str) {
        return TextUtils.isEmpty(this.f6326a.get(str));
    }
}
