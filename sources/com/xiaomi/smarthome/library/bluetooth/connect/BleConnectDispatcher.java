package com.xiaomi.smarthome.library.bluetooth.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleBatchWriteRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleConnectRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleIndicationRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleNotifyRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleReadRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleReadRssiRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleRequestMtuRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleUnindicationRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleUnnotifyRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleWriteNoRspFastRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleWriteNoRspRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.BleWriteRequest;
import com.xiaomi.smarthome.library.bluetooth.connect.request.IFastSchedule;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.common.util.ListUtils;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

public class BleConnectDispatcher implements Handler.Callback, IBleConnectDispatcher, RuntimeChecker {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18501a = 100;
    private static final int b = 18;
    private List<BleRequest> c = new LinkedList();
    private BleRequest d;
    private IBleConnectWorker e;
    private String f;
    private Handler g;

    public static BleConnectDispatcher a(String str) {
        return new BleConnectDispatcher(str);
    }

    private BleConnectDispatcher(String str) {
        this.f = str;
        this.e = new BleConnectWorker(str, this);
        this.g = new Handler(Looper.myLooper(), this);
    }

    public void a(BleConnectOptions bleConnectOptions, BleResponser bleResponser) {
        b(new BleConnectRequest(bleConnectOptions, bleResponser));
    }

    public void a() {
        c();
        BluetoothLog.e(String.format("start process disconnect", new Object[0]));
        if (this.d != null) {
            this.d.o();
            this.d = null;
        }
        for (BleRequest o : this.c) {
            o.o();
        }
        this.c.clear();
        this.e.b();
    }

    public void b() {
        c();
        this.e.f();
    }

    public void a(UUID uuid, UUID uuid2, BleResponser bleResponser) {
        b(new BleReadRequest(uuid, uuid2, bleResponser));
    }

    public void a(UUID uuid, UUID uuid2, byte[] bArr, BleResponser bleResponser) {
        b(new BleWriteRequest(uuid, uuid2, bArr, bleResponser));
    }

    public void b(UUID uuid, UUID uuid2, byte[] bArr, BleResponser bleResponser) {
        b(new BleWriteNoRspRequest(uuid, uuid2, bArr, bleResponser));
    }

    public void c(UUID uuid, UUID uuid2, byte[] bArr, BleResponser bleResponser) {
        b(new BleWriteNoRspFastRequest(uuid, uuid2, bArr, bleResponser));
    }

    public void a(UUID uuid, UUID uuid2, List<byte[]> list, BleResponser bleResponser) {
        b(new BleBatchWriteRequest(uuid, uuid2, list, bleResponser));
    }

    public void b(UUID uuid, UUID uuid2, BleResponser bleResponser) {
        b(new BleNotifyRequest(uuid, uuid2, bleResponser));
    }

    public void a(UUID uuid, UUID uuid2) {
        b(new BleUnnotifyRequest(uuid, uuid2));
    }

    public void c(UUID uuid, UUID uuid2, BleResponser bleResponser) {
        b(new BleIndicationRequest(uuid, uuid2, bleResponser));
    }

    public void b(UUID uuid, UUID uuid2) {
        b(new BleUnindicationRequest(uuid, uuid2));
    }

    public void a(BleResponser bleResponser) {
        b(new BleReadRssiRequest(bleResponser));
    }

    public void a(int i) {
        this.e.c(i);
    }

    public void a(int i, BleResponser bleResponser) {
        b(new BleRequestMtuRequest(i, bleResponser));
    }

    public void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse) {
        this.e.a(uuid, uuid2, bleResponse);
    }

    private void b(BleRequest bleRequest) {
        c();
        if (this.c.size() < 100) {
            bleRequest.a((RuntimeChecker) this);
            bleRequest.a(this.f);
            bleRequest.a(this.e);
            this.c.add(bleRequest);
        } else {
            bleRequest.b(-11);
        }
        a(10);
    }

    public void a(BleRequest bleRequest) {
        c();
        if (bleRequest == this.d) {
            this.d = null;
            a(10);
            return;
        }
        throw new IllegalStateException("request not match");
    }

    private void a(long j) {
        if (this.c.size() > 0 && (this.c.get(0) instanceof IFastSchedule)) {
            j = 0;
        }
        this.g.sendEmptyMessageDelayed(18, j);
    }

    private void d() {
        if (this.d == null && !ListUtils.a(this.c)) {
            this.d = this.c.remove(0);
            this.d.a((IBleConnectDispatcher) this);
        }
    }

    public void c() {
        if (Thread.currentThread() != this.g.getLooper().getThread()) {
            throw new IllegalStateException("Thread Context Illegal");
        }
    }

    public boolean handleMessage(Message message) {
        if (message.what != 18) {
            return true;
        }
        d();
        return true;
    }
}
