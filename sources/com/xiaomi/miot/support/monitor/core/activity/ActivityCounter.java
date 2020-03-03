package com.xiaomi.miot.support.monitor.core.activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityCounter {

    /* renamed from: a  reason: collision with root package name */
    private List<UIListener> f11457a = new ArrayList();
    private int b = 0;
    private String c = "";

    public interface UIListener {
        void a();
    }

    public void a(String str) {
        this.c = str;
        this.b++;
        if (b()) {
            e();
        }
    }

    public void a() {
        this.c = "";
        this.b--;
    }

    public boolean b() {
        return this.b > 0;
    }

    public String c() {
        return this.c;
    }

    public void d() {
        this.b = 0;
    }

    public void a(UIListener uIListener) {
        if (uIListener != null && !this.f11457a.contains(uIListener)) {
            this.f11457a.add(uIListener);
        }
    }

    public void b(UIListener uIListener) {
        if (uIListener != null && this.f11457a.contains(uIListener)) {
            this.f11457a.remove(uIListener);
        }
    }

    private void e() {
        if (!this.f11457a.isEmpty()) {
            for (UIListener a2 : this.f11457a) {
                a2.a();
            }
        }
    }
}
