package com.xiaomi.smarthome.lite.scene;

import java.util.ArrayList;
import java.util.List;

public class HomeSceneViewHoldManager {

    /* renamed from: a  reason: collision with root package name */
    private static HomeSceneViewHoldManager f19380a;
    private List<Object> b = new ArrayList();
    private int c = 0;
    private boolean d = false;
    private boolean e = false;

    public void f() {
    }

    private HomeSceneViewHoldManager() {
    }

    public List<Object> a() {
        return this.b;
    }

    public int b() {
        return this.c;
    }

    public boolean c() {
        return this.d;
    }

    public boolean d() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void b(boolean z) {
        this.d = z;
    }

    public static HomeSceneViewHoldManager e() {
        if (f19380a == null) {
            f19380a = new HomeSceneViewHoldManager();
        }
        return f19380a;
    }
}
