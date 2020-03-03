package com.xiaomi.smarthome.library.bluetooth.connect.request;

import android.os.Message;
import android.os.Parcelable;
import com.taobao.weex.el.parse.Operators;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.BluetoothConstants;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattProfile;
import com.xiaomi.smarthome.library.bluetooth.connect.BleGattService;
import com.xiaomi.smarthome.library.bluetooth.connect.BleResponser;
import com.xiaomi.smarthome.library.bluetooth.connect.listener.ServiceDiscoverListener;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ListUtils;

public class BleConnectRequest extends BleRequest implements ServiceDiscoverListener {
    private static final int h = 1;
    private static final int i = 2;
    private static final int w = 3;
    private static final int x = 4;
    private static final int y = 5;
    private int A;
    private int B;
    private BleConnectOptions z;

    public BleConnectRequest(BleConnectOptions bleConnectOptions, BleResponser bleResponser) {
        super(bleResponser);
        this.z = bleConnectOptions == null ? new BleConnectOptions.Builder().a() : bleConnectOptions;
    }

    public void i() {
        s();
    }

    private void s() {
        this.t.removeCallbacksAndMessages((Object) null);
        this.B = 0;
        int e = e();
        if (e != 0) {
            if (e == 2) {
                y();
            } else if (e == 19) {
                D();
            }
        } else if (!t()) {
            e(-6);
            b();
        } else {
            this.t.sendEmptyMessageDelayed(3, (long) this.z.c());
        }
    }

    private boolean t() {
        this.A++;
        return a();
    }

    private boolean u() {
        this.B++;
        return d();
    }

    private void v() {
        if (this.A < this.z.a() + 1) {
            z();
        } else {
            e(-6);
        }
    }

    private void w() {
        if (this.B < this.z.b() + 1) {
            A();
            return;
        }
        e(-6);
        b();
    }

    private void x() {
        BluetoothLog.c(String.format("onServiceDiscoverFailed", new Object[0]));
        f();
        this.t.sendEmptyMessageDelayed(5, 2000);
    }

    private void y() {
        BluetoothLog.c(String.format("processDiscoverService, status = %s", new Object[]{n()}));
        int e = e();
        if (e == 0) {
            v();
        } else if (e != 2) {
            if (e == 19) {
                D();
            }
        } else if (!u()) {
            x();
        } else {
            this.t.sendEmptyMessageDelayed(4, (long) this.z.d());
        }
    }

    private void z() {
        b(String.format("retry connect later", new Object[0]));
        this.t.removeCallbacksAndMessages((Object) null);
        this.t.sendEmptyMessageDelayed(1, 2000);
    }

    private void A() {
        b(String.format("retry discover service later", new Object[0]));
        this.t.removeCallbacksAndMessages((Object) null);
        this.t.sendEmptyMessageDelayed(2, 2000);
    }

    private void B() {
        b(String.format("connect timeout", new Object[0]));
        e(-6);
        b();
    }

    private void C() {
        b(String.format("service discover timeout", new Object[0]));
        e(-6);
        b();
    }

    public boolean handleMessage(Message message) {
        switch (message.what) {
            case 1:
                s();
                break;
            case 2:
                y();
                break;
            case 3:
                B();
                break;
            case 4:
                C();
                break;
            case 5:
                w();
                break;
        }
        return super.handleMessage(message);
    }

    public String toString() {
        return "BleConnectRequest{options=" + this.z + Operators.BLOCK_END;
    }

    public void a(boolean z2) {
        c();
        this.t.removeMessages(3);
        if (z2) {
            this.t.sendEmptyMessageDelayed(2, 300);
            return;
        }
        this.t.removeCallbacksAndMessages((Object) null);
        v();
    }

    public void a(int i2, BleGattProfile bleGattProfile) {
        c();
        this.t.removeMessages(4);
        if (i2 != 0) {
            x();
        } else if (!a(bleGattProfile)) {
            BluetoothLog.b(String.format("checkService return false", new Object[0]));
            x();
        } else {
            D();
        }
    }

    private boolean a(BleGattProfile bleGattProfile) {
        if (bleGattProfile == null || ListUtils.a(bleGattProfile.a())) {
            return false;
        }
        BleGattService a2 = bleGattProfile.a(BluetoothConstants.i);
        if (a2 != null) {
            return !ListUtils.a(a2.b());
        }
        return true;
    }

    private void D() {
        BleGattProfile h2 = h();
        if (h2 != null) {
            a("key_gatt_profile", (Parcelable) h2);
        }
        e(0);
    }
}
