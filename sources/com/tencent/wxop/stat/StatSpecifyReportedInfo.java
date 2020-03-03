package com.tencent.wxop.stat;

import com.taobao.weex.el.parse.Operators;

public class StatSpecifyReportedInfo {

    /* renamed from: a  reason: collision with root package name */
    private String f9269a = null;
    private String b = null;
    private String c = null;
    private boolean d = false;
    private boolean e = false;

    public void a(String str) {
        this.c = str;
    }

    public void a(boolean z) {
        this.d = z;
    }

    public boolean a() {
        return this.d;
    }

    public String b() {
        return this.c;
    }

    public void b(String str) {
        this.f9269a = str;
    }

    public void b(boolean z) {
        this.e = z;
    }

    public String c() {
        return this.f9269a;
    }

    public void c(String str) {
        this.b = str;
    }

    public String d() {
        return this.b;
    }

    public boolean e() {
        return this.e;
    }

    public String toString() {
        return "StatSpecifyReportedInfo [appKey=" + this.f9269a + ", installChannel=" + this.b + ", version=" + this.c + ", sendImmediately=" + this.d + ", isImportant=" + this.e + Operators.ARRAY_END_STR;
    }
}
