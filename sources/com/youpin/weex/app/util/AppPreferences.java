package com.youpin.weex.app.util;

import android.os.Bundle;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AppPreferences {

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, String> f2542a = new HashMap<>(20);
    private Bundle b;

    public void a(Bundle bundle) {
        this.b = bundle;
    }

    public void a(String str, String str2) {
        this.f2542a.put(str.toLowerCase(Locale.ENGLISH), str2);
    }

    public void a(String str, boolean z) {
        a(str, "" + z);
    }

    public void a(String str, int i) {
        a(str, "" + i);
    }

    public void a(String str, double d) {
        a(str, "" + d);
    }

    public Map<String, String> a() {
        return this.f2542a;
    }

    public boolean b(String str, boolean z) {
        String str2 = this.f2542a.get(str.toLowerCase(Locale.ENGLISH));
        return str2 != null ? Boolean.parseBoolean(str2) : z;
    }

    public boolean a(String str) {
        return b(str, (String) null) != null;
    }

    public int b(String str, int i) {
        String str2 = this.f2542a.get(str.toLowerCase(Locale.ENGLISH));
        return str2 != null ? (int) Long.decode(str2).longValue() : i;
    }

    public double b(String str, double d) {
        String str2 = this.f2542a.get(str.toLowerCase(Locale.ENGLISH));
        return str2 != null ? Double.valueOf(str2).doubleValue() : d;
    }

    public String b(String str, String str2) {
        String str3 = this.f2542a.get(str.toLowerCase(Locale.ENGLISH));
        return str3 != null ? str3 : str2;
    }
}
