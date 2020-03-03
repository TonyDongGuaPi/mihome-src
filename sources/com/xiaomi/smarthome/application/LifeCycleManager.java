package com.xiaomi.smarthome.application;

import java.util.ArrayList;
import java.util.List;

public class LifeCycleManager {

    /* renamed from: a  reason: collision with root package name */
    private static LifeCycleManager f1502a;
    private List<ApplicationLifeCycle> b = new ArrayList();

    public static LifeCycleManager a() {
        if (f1502a == null) {
            f1502a = new LifeCycleManager();
        }
        return f1502a;
    }

    public List<ApplicationLifeCycle> b() {
        return this.b;
    }
}
