package com.xiaomi.smarthome.device.bluetooth.connect.combo;

import android.net.wifi.ScanResult;
import android.os.Handler;
import android.os.Looper;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;

public class BaseBleComboConnector implements IBleComboConnector {

    /* renamed from: a  reason: collision with root package name */
    protected ComboConnectResponse f15124a;
    protected Handler b = new Handler(Looper.getMainLooper());
    protected String c;
    protected int d;

    protected BaseBleComboConnector(ComboConnectResponse comboConnectResponse) {
        this.f15124a = comboConnectResponse;
    }

    public void a(final String str) {
        this.c = str;
        BluetoothLog.c(String.format("%s.onSearchComboAddress: mac = %s", new Object[]{getClass().getSimpleName(), str}));
        this.b.post(new Runnable() {
            public void run() {
                BaseBleComboConnector.this.f15124a.a(str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(final int i, final String str) {
        BluetoothLog.c(String.format("%s.onSendSSIDAndPassWd: code = %d", new Object[]{getClass().getSimpleName(), Integer.valueOf(i)}));
        this.b.post(new Runnable() {
            public void run() {
                BaseBleComboConnector.this.f15124a.a(i, str);
            }
        });
    }

    /* access modifiers changed from: protected */
    public void a(final int i) {
        BluetoothLog.c(String.format("%s.onNotifyStatus: status = %d", new Object[]{getClass().getSimpleName(), Integer.valueOf(i)}));
        this.d = i;
        this.b.post(new Runnable() {
            public void run() {
                BaseBleComboConnector.this.f15124a.d_(i);
            }
        });
    }

    public void a(ScanResult scanResult) {
        BluetoothLog.c(String.format("%s.searchComboAddress", new Object[]{getClass().getSimpleName()}));
    }

    public void a(String str, String str2) {
        BluetoothLog.c(String.format("%s.sendSSIDAndPassWd", new Object[]{getClass().getSimpleName()}));
    }

    public void a() {
        BluetoothLog.c(String.format("%s.openComboNotify", new Object[]{getClass().getSimpleName()}));
    }

    public void b() {
        BluetoothLog.c(String.format("%s.closeComboNotify", new Object[]{getClass().getSimpleName()}));
    }

    public void c() {
        BluetoothLog.c(String.format("%s.retryConnect", new Object[]{getClass().getSimpleName()}));
    }

    public void a(ComboRestoreResponse comboRestoreResponse) {
        BluetoothLog.c(String.format("%s.restore", new Object[]{getClass().getSimpleName()}));
    }

    public void d() {
        BluetoothLog.c(String.format("%s.release", new Object[]{getClass().getSimpleName()}));
        this.b.removeCallbacksAndMessages((Object) null);
    }
}
