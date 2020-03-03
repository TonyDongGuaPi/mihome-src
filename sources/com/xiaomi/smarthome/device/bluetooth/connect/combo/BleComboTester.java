package com.xiaomi.smarthome.device.bluetooth.connect.combo;

import android.net.wifi.ScanResult;
import android.support.v4.util.Pair;
import com.xiaomi.smarthome.device.bluetooth.utils.ConfigUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class BleComboTester extends BaseBleComboConnector {
    private int e;
    /* access modifiers changed from: private */
    public int f;
    private int g;
    private List<Pair<Integer, Integer>> h;

    public BleComboTester(ComboConnectResponse comboConnectResponse) {
        super(comboConnectResponse);
        e();
    }

    private void e() {
        File a2 = ConfigUtils.a("combo.txt");
        if (a2 == null || !a2.exists() || !a2.isFile()) {
            BluetoothLog.e(String.format("config file %s not exist!", new Object[]{a2.getAbsolutePath()}));
            return;
        }
        BufferedReader a3 = ConfigUtils.a(a2);
        String[] a4 = ConfigUtils.a(a3, " ");
        this.c = a4[0].trim();
        if (!this.c.contains(":")) {
            this.c = "";
        }
        this.e = Integer.parseInt(a4[1]);
        BluetoothLog.c(String.format("mComboAddress = %s, mSearchAddressDuration = %d", new Object[]{this.c, Integer.valueOf(this.e)}));
        String[] a5 = ConfigUtils.a(a3, " ");
        this.f = Integer.parseInt(a5[0]);
        this.g = Integer.parseInt(a5[1]);
        BluetoothLog.c(String.format("mSendPwdCode = %d, mSendPwdDuration = %d", new Object[]{Integer.valueOf(this.f), Integer.valueOf(this.g)}));
        this.h = new ArrayList();
        while (true) {
            String[] a6 = ConfigUtils.a(a3, " ");
            if (a6 != null) {
                int parseInt = Integer.parseInt(a6[0]);
                int parseInt2 = Integer.parseInt(a6[1]);
                BluetoothLog.c(String.format(">>> notify = %d, duration = %d", new Object[]{Integer.valueOf(parseInt), Integer.valueOf(parseInt2)}));
                this.h.add(new Pair(Integer.valueOf(parseInt), Integer.valueOf(parseInt2)));
            } else {
                return;
            }
        }
    }

    public void a(ScanResult scanResult) {
        super.a(scanResult);
        this.b.postDelayed(new Runnable() {
            public void run() {
                BleComboTester.this.a(BleComboTester.this.c);
            }
        }, (long) this.e);
    }

    public void a(String str, String str2) {
        super.a(str, str2);
        this.b.postDelayed(new Runnable() {
            public void run() {
                BleComboTester.this.a(BleComboTester.this.f, "");
            }
        }, (long) this.g);
    }

    public void a() {
        super.a();
        f();
    }

    public void b() {
        super.b();
        this.b.removeCallbacksAndMessages((Object) null);
    }

    private void f() {
        int i = 0;
        for (Pair next : this.h) {
            final int intValue = ((Integer) next.first).intValue();
            i += ((Integer) next.second).intValue();
            this.b.postDelayed(new Runnable() {
                public void run() {
                    BleComboTester.this.a(intValue);
                }
            }, (long) i);
        }
    }

    public void c() {
        super.c();
    }
}
