package com.xiaomi.smarthome.core.server.internal.bluetooth;

import android.os.Bundle;

public abstract class BleSearchRunnable implements Runnable {

    /* renamed from: a  reason: collision with root package name */
    protected int f14104a;
    protected Bundle b;

    public BleSearchRunnable(int i, Bundle bundle) {
        this.f14104a = i;
        this.b = bundle;
    }
}
