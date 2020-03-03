package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import com.xiaomi.smarthome.library.bluetooth.BluetoothContextManager;
import com.xiaomi.smarthome.library.bluetooth.Constants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.IBleConnectDispatcher;
import com.xiaomi.smarthome.library.bluetooth.connect.IBleConnectWorker;
import com.xiaomi.smarthome.library.bluetooth.connect.RuntimeChecker;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.GattResponseListener;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import java.util.UUID;

public abstract class BleRequest implements Handler.Callback, IBleConnectWorker, RuntimeChecker, GattResponseListener, IBleRequest {
    private static final boolean h = (!BluetoothContextManager.o());
    protected static final int j = 32;
    private RuntimeChecker i;
    protected UUID k;
    protected UUID l;
    protected byte[] m;
    protected BleResponser n;
    protected Bundle o = new Bundle();
    protected int p;
    protected String q;
    protected IBleConnectDispatcher r;
    protected IBleConnectWorker s;
    protected Handler t = new Handler(Looper.myLooper(), this);
    protected Handler u = new Handler(Looper.getMainLooper());
    protected boolean v;
    private boolean w;

    public abstract void i();

    /* access modifiers changed from: protected */
    public long p() {
        return 30000;
    }

    public BleRequest(BleResponser bleResponser) {
        this.n = bleResponser;
    }

    public int j() {
        return this.p;
    }

    public void a(int i2) {
        this.p = i2;
        a("key_code", i2);
    }

    public void a(BleResponser bleResponser) {
        this.n = bleResponser;
    }

    public String k() {
        return this.q;
    }

    public void a(String str) {
        this.q = str;
    }

    public void a(IBleConnectWorker iBleConnectWorker) {
        this.s = iBleConnectWorker;
    }

    public void b(final int i2) {
        if (!this.w) {
            this.w = true;
            this.u.post(new Runnable() {
                public void run() {
                    try {
                        if (BleRequest.this.n != null) {
                            BleRequest.this.n.a(i2, BleRequest.this.o);
                        }
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                }
            });
        }
    }

    public String toString() {
        return getClass().getSimpleName();
    }

    public void a(String str, int i2) {
        this.o.putInt(str, i2);
    }

    public int b(String str, int i2) {
        return this.o.getInt(str, i2);
    }

    public void a(String str, byte[] bArr) {
        this.o.putByteArray(str, bArr);
    }

    public void a(String str, Parcelable parcelable) {
        this.o.putParcelable(str, parcelable);
    }

    public Bundle l() {
        return this.o;
    }

    public BleResponser m() {
        return this.n;
    }

    /* access modifiers changed from: protected */
    public String n() {
        return Constants.a(e());
    }

    public boolean a() {
        return this.s.a();
    }

    public boolean d() {
        return this.s.d();
    }

    public int e() {
        return this.s.e();
    }

    public final void a(IBleConnectDispatcher iBleConnectDispatcher) {
        c();
        this.r = iBleConnectDispatcher;
        if (h) {
            BluetoothLog.e(String.format("Process %s, status = %s", new Object[]{getClass().getSimpleName(), n()}));
        }
        if (!BluetoothUtils.a()) {
            e(-4);
        } else if (!BluetoothUtils.b()) {
            e(-5);
        } else {
            try {
                a((GattResponseListener) this);
                i();
            } catch (Throwable th) {
                BluetoothLog.a(th);
                e(-15);
            }
        }
    }

    /* access modifiers changed from: protected */
    public void e(int i2) {
        if (h) {
            b(String.format("request complete: code = %d", new Object[]{Integer.valueOf(i2)}));
        }
        this.t.removeCallbacksAndMessages((Object) null);
        b((GattResponseListener) this);
        b(i2);
        this.r.a(this);
    }

    public void b() {
        if (h) {
            b(String.format("close gatt", new Object[0]));
        }
        this.s.b();
    }

    public boolean handleMessage(Message message) {
        if (message.what == 32) {
            this.v = true;
            b();
        }
        return true;
    }

    public void a(GattResponseListener gattResponseListener) {
        this.s.a(gattResponseListener);
    }

    public void b(GattResponseListener gattResponseListener) {
        this.s.b(gattResponseListener);
    }

    public boolean f() {
        return this.s.f();
    }

    public boolean a(UUID uuid, UUID uuid2) {
        return this.s.a(uuid, uuid2);
    }

    public boolean a(UUID uuid, UUID uuid2, byte[] bArr) {
        return this.s.a(uuid, uuid2, bArr);
    }

    public boolean b(UUID uuid, UUID uuid2, byte[] bArr) {
        return this.s.b(uuid, uuid2, bArr);
    }

    public boolean a(UUID uuid, UUID uuid2, boolean z) {
        return this.s.a(uuid, uuid2, z);
    }

    public boolean b(UUID uuid, UUID uuid2, boolean z) {
        return this.s.b(uuid, uuid2, z);
    }

    public boolean g() {
        return this.s.g();
    }

    public boolean c(int i2) {
        return this.s.c(i2);
    }

    public boolean d(int i2) {
        return this.s.d(i2);
    }

    public void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse) {
        this.s.a(uuid, uuid2, bleResponse);
    }

    /* access modifiers changed from: protected */
    public void b(String str) {
        BluetoothLog.c(String.format("%s %s >>> %s", new Object[]{getClass().getSimpleName(), k(), str}));
    }

    public void a(RuntimeChecker runtimeChecker) {
        this.i = runtimeChecker;
    }

    public void c() {
        this.i.c();
    }

    public void o() {
        c();
        if (h) {
            b(String.format("request canceled", new Object[0]));
        }
        this.t.removeCallbacksAndMessages((Object) null);
        b((GattResponseListener) this);
        b(-2);
    }

    public void a(boolean z) {
        if (!z) {
            e(this.v ? -7 : -1);
        }
    }

    /* access modifiers changed from: protected */
    public void q() {
        this.t.sendEmptyMessageDelayed(32, p());
    }

    /* access modifiers changed from: protected */
    public void r() {
        this.t.removeMessages(32);
    }

    public BleGattProfile h() {
        return this.s.h();
    }
}
