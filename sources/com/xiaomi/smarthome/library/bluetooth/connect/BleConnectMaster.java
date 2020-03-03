package com.xiaomi.smarthome.library.bluetooth.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadRssiResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse2;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyInterceptor;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyUtils;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothLog;
import com.xiaomi.smarthome.library.bluetooth.utils.BluetoothUtils;
import com.xiaomi.smarthome.library.common.util.MessageHandlerThread;
import java.lang.reflect.Method;
import java.util.List;
import java.util.UUID;

public class BleConnectMaster implements Handler.Callback, IBleConnectMaster, ProxyInterceptor {

    /* renamed from: a  reason: collision with root package name */
    private static final int f18503a = 120000;
    private static final int b = 15000;
    private static final int c = 256;
    /* access modifiers changed from: private */
    public MessageHandlerThread d;
    private Handler e = new Handler(Looper.getMainLooper());
    /* access modifiers changed from: private */
    public Handler f;
    private String g;
    /* access modifiers changed from: private */
    public BleConnectDispatcher h;
    private long i;

    private BleConnectMaster(String str) {
        this.g = str;
    }

    static IBleConnectMaster a(String str) {
        e();
        BleConnectMaster bleConnectMaster = new BleConnectMaster(str);
        return (IBleConnectMaster) ProxyUtils.a((Object) bleConnectMaster, (Class<?>) IBleConnectMaster.class, (ProxyInterceptor) bleConnectMaster);
    }

    /* access modifiers changed from: private */
    public static void e() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("");
        }
    }

    /* access modifiers changed from: private */
    public void f() {
        if (this.h == null) {
            this.h = BleConnectDispatcher.a(this.g);
        }
    }

    private void g() {
        this.e.post(new Runnable() {
            public void run() {
                BleConnectMaster.e();
                if (BleConnectMaster.this.d != null) {
                    BleConnectMaster.this.d.quit();
                    MessageHandlerThread unused = BleConnectMaster.this.d = null;
                    Handler unused2 = BleConnectMaster.this.f = null;
                    BleConnectDispatcher unused3 = BleConnectMaster.this.h = null;
                }
            }
        });
    }

    private void h() {
        e();
        if (this.d == null) {
            this.d = new MessageHandlerThread(String.format("BleConnectMaster(%s)", new Object[]{this.g}));
            this.d.start();
            this.f = new Handler(this.d.getLooper(), this);
        }
    }

    private void i() {
        this.e.post(new Runnable() {
            public void run() {
                if (BleConnectMaster.this.f != null) {
                    BleConnectMaster.this.f.sendEmptyMessageDelayed(256, 15000);
                }
            }
        });
    }

    private void j() {
        if (System.currentTimeMillis() - this.i <= 120000 || BluetoothUtils.c(this.g)) {
            i();
        } else {
            g();
        }
    }

    public void a(BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse) {
        this.h.a(bleConnectOptions, BleResponser.a(bleConnectResponse));
    }

    public void a() {
        this.h.a();
    }

    public void a(UUID uuid, UUID uuid2, BleReadResponse bleReadResponse) {
        this.h.a(uuid, uuid2, BleResponser.a(bleReadResponse));
    }

    public void a(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        if (bleWriteResponse instanceof BleWriteResponse2) {
            b(uuid, uuid2, bArr, bleWriteResponse);
        } else {
            this.h.a(uuid, uuid2, bArr, BleResponser.a(bleWriteResponse));
        }
    }

    public void b(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        this.h.b(uuid, uuid2, bArr, BleResponser.a(bleWriteResponse));
    }

    public void a(UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse) {
        this.h.b(uuid, uuid2, BleResponser.a(bleNotifyResponse));
    }

    public void a(UUID uuid, UUID uuid2) {
        this.h.a(uuid, uuid2);
    }

    public void b(UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse) {
        this.h.c(uuid, uuid2, BleResponser.a(bleNotifyResponse));
    }

    public void b(UUID uuid, UUID uuid2) {
        this.h.b(uuid, uuid2);
    }

    public void a(BleReadRssiResponse bleReadRssiResponse) {
        this.h.a(BleResponser.a(bleReadRssiResponse));
    }

    public void b() {
        this.h.b();
    }

    public void a(int i2) {
        this.h.a(i2);
    }

    public void a(int i2, BleRequestMtuResponse bleRequestMtuResponse) {
        this.h.a(i2, BleResponser.a(bleRequestMtuResponse));
    }

    public void c(UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        this.h.c(uuid, uuid2, bArr, BleResponser.a(bleWriteResponse));
    }

    public void a(String str, UUID uuid, UUID uuid2, List<byte[]> list, BleWriteResponse bleWriteResponse) {
        this.h.a(uuid, uuid2, list, BleResponser.a(bleWriteResponse));
    }

    public void a(UUID uuid, UUID uuid2, BleResponse<Void> bleResponse) {
        this.h.a(uuid, uuid2, bleResponse);
    }

    private void a(long j) {
        this.i = j;
    }

    public Handler c() {
        e();
        if (this.f == null) {
            h();
        }
        return this.f;
    }

    private void k() {
        BleConnectObserver.a().a(this.g);
    }

    public boolean a(Object obj, Method method, Object[] objArr) {
        e();
        final BleConnectMaster bleConnectMaster = (BleConnectMaster) obj;
        bleConnectMaster.a(System.currentTimeMillis());
        bleConnectMaster.k();
        final Method method2 = method;
        final Object obj2 = obj;
        final Object[] objArr2 = objArr;
        bleConnectMaster.c().post(new Runnable() {
            public void run() {
                try {
                    bleConnectMaster.f();
                    method2.invoke(obj2, objArr2);
                } catch (Exception e2) {
                    BluetoothLog.b((Throwable) e2);
                }
            }
        });
        return true;
    }

    public boolean handleMessage(Message message) {
        if (message.what != 256) {
            return true;
        }
        j();
        return true;
    }
}
