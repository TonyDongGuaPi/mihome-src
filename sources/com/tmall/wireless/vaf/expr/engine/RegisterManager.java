package com.tmall.wireless.vaf.expr.engine;

import com.tmall.wireless.vaf.expr.engine.data.Data;

public class RegisterManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f9350a = 20;
    private static final String b = "RegisterManager_TMTEST";
    private Data[] c = new Data[20];

    public void a() {
        this.c = null;
    }

    public RegisterManager() {
        for (int i = 0; i < 20; i++) {
            this.c[i] = new Data();
        }
    }

    public Data a(int i) {
        if (i < 0 || i >= 20) {
            return null;
        }
        return this.c[i];
    }
}
