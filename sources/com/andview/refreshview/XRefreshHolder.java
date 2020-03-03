package com.andview.refreshview;

public class XRefreshHolder {

    /* renamed from: a  reason: collision with root package name */
    public int f4768a;

    public void a(int i) {
        this.f4768a += i;
    }

    public boolean a() {
        return this.f4768a > 0;
    }

    public boolean b() {
        return this.f4768a < 0;
    }

    public boolean b(int i) {
        return this.f4768a < (-i);
    }
}
