package com.xiaomi.push;

import java.util.Map;

public class fv implements Cloneable {

    /* renamed from: a  reason: collision with root package name */
    public static String f12745a = "wcc-ml-test10.bj";
    public static final String b = ae.b;
    public static String c = null;

    /* renamed from: a  reason: collision with other field name */
    private int f80a;

    /* renamed from: a  reason: collision with other field name */
    private fy f81a;

    /* renamed from: a  reason: collision with other field name */
    private boolean f82a = fu.f12743a;

    /* renamed from: b  reason: collision with other field name */
    private boolean f83b = true;
    private String d;
    private String e;
    private String f;

    public fv(Map<String, Integer> map, int i, String str, fy fyVar) {
        a(map, i, str, fyVar);
    }

    public static final String a() {
        return c != null ? c : ab.a() ? "sandbox.xmpush.xiaomi.com" : ab.b() ? b : "app.chat.xiaomi.net";
    }

    public static final void a(String str) {
        c = str;
    }

    private void a(Map<String, Integer> map, int i, String str, fy fyVar) {
        this.f80a = i;
        this.d = str;
        this.f81a = fyVar;
    }

    /* renamed from: a  reason: collision with other method in class */
    public int m123a() {
        return this.f80a;
    }

    public void a(boolean z) {
        this.f82a = z;
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m124a() {
        return this.f82a;
    }

    /* renamed from: a  reason: collision with other method in class */
    public byte[] m125a() {
        return null;
    }

    public String b() {
        return this.f;
    }

    public void b(String str) {
        this.f = str;
    }

    public String c() {
        if (this.e == null) {
            this.e = a();
        }
        return this.e;
    }

    public void c(String str) {
        this.e = str;
    }
}
