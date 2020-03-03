package com.xiaomi.smarthome.core.server;

import android.os.IBinder;
import com.xiaomi.smarthome.core.client.IClientApi;

public class ClientRecord {

    /* renamed from: a  reason: collision with root package name */
    private IClientApi f14001a;
    private int b;
    private int c;
    private String d;
    private String[] e;
    private IBinder.DeathRecipient f;
    private long g;

    public synchronized IClientApi a() {
        return this.f14001a;
    }

    public synchronized String b() {
        return this.d;
    }

    public synchronized long c() {
        return this.g;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(IClientApi iClientApi, int i, int i2, String str, String[] strArr, long j) {
        this.f14001a = iClientApi;
        this.b = i;
        this.c = i2;
        this.d = str;
        this.e = strArr;
        this.g = j;
    }

    /* access modifiers changed from: package-private */
    public synchronized void a(IBinder.DeathRecipient deathRecipient) {
        this.f = deathRecipient;
    }
}
