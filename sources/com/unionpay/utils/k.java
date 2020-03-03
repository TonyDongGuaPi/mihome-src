package com.unionpay.utils;

import java.util.Locale;

public class k {
    private static k g;

    /* renamed from: a  reason: collision with root package name */
    public String f9847a = "";
    public String b = "";
    public String c = "";
    public String d = "";
    public String e = "";
    public String f = "";

    public static k a() {
        if (g == null) {
            g = Locale.getDefault().toString().startsWith("zh") ? new l() : new m();
        }
        return g;
    }
}
