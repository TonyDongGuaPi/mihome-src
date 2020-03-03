package com.huawei.hms.api.internal;

import java.util.List;

public class g {
    private static g b = new g();

    /* renamed from: a  reason: collision with root package name */
    private int f5861a = 1;

    public static g a() {
        return b;
    }

    public int a(List<Integer> list) {
        if (list == null || list.isEmpty()) {
            this.f5861a = 1;
            return this.f5861a;
        }
        if (!list.contains(2)) {
            this.f5861a = list.get(list.size() - 1).intValue();
        } else {
            this.f5861a = 2;
        }
        return this.f5861a;
    }

    public int b() {
        return this.f5861a;
    }
}
