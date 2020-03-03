package com.xiaomi.smarthome.device.choosedevice;

import java.util.ArrayList;
import java.util.List;

public class CollapseStateRecord {
    private static CollapseStateRecord b;

    /* renamed from: a  reason: collision with root package name */
    private final List<String> f15338a = new ArrayList();

    public static CollapseStateRecord a() {
        if (b == null) {
            b = new CollapseStateRecord();
        }
        return b;
    }

    public void a(String str) {
        if (!this.f15338a.contains(str)) {
            this.f15338a.add(str);
        }
    }

    public boolean b(String str) {
        return this.f15338a.contains(str);
    }

    public void b() {
        b = null;
    }
}
