package com.xiaomi.phonenum.phone;

import android.annotation.SuppressLint;
import android.content.Context;

public class KKPhoneInfo extends PhoneInfo {
    private final int c = -1;

    public int a() {
        return 1;
    }

    public int a(int i) {
        return -1;
    }

    public boolean a(int i, long j) throws InterruptedException {
        return true;
    }

    KKPhoneInfo(Context context) {
        super(context);
    }

    public boolean b(int i) {
        return this.f12575a.getDataState() == 2;
    }

    public boolean c(int i) {
        return this.f12575a.isNetworkRoaming();
    }

    public String d(int i) {
        return this.f12575a.getNetworkOperator();
    }

    public int e(int i) {
        return this.f12575a.getPhoneType();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"HardwareIds"})
    public String f(int i) {
        return this.f12575a.getSimSerialNumber();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"HardwareIds"})
    public String g(int i) {
        return this.f12575a.getSubscriberId();
    }

    /* access modifiers changed from: protected */
    public String h(int i) {
        return this.f12575a.getSimOperator();
    }

    /* access modifiers changed from: protected */
    @SuppressLint({"HardwareIds"})
    public String i(int i) {
        return this.f12575a.getLine1Number();
    }
}
