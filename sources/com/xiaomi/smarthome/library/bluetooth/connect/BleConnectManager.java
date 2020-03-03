package com.xiaomi.smarthome.library.bluetooth.connect;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import com.xiaomi.smarthome.library.bluetooth.BleConnectOptions;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleConnectResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleNotifyResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleReadRssiResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleRequestMtuResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleResponse;
import com.xiaomi.smarthome.library.bluetooth.connect.response.BleWriteResponse;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyBulk;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyInterceptor;
import com.xiaomi.smarthome.library.bluetooth.proxy.ProxyUtils;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class BleConnectManager implements Handler.Callback, IBleConnectManager, ProxyInterceptor {
    private static IBleConnectManager b;

    /* renamed from: a  reason: collision with root package name */
    private HashMap<String, IBleConnectMaster> f18502a = new HashMap<>();
    private Handler c = new Handler(Looper.getMainLooper(), this);

    private BleConnectManager() {
    }

    public static IBleConnectManager a() {
        if (b == null) {
            synchronized (BleConnectManager.class) {
                if (b == null) {
                    BleConnectManager bleConnectManager = new BleConnectManager();
                    b = (IBleConnectManager) ProxyUtils.b(bleConnectManager, IBleConnectManager.class, bleConnectManager);
                }
            }
        }
        return b;
    }

    private IBleConnectMaster c(String str) {
        IBleConnectMaster iBleConnectMaster = this.f18502a.get(str);
        if (iBleConnectMaster != null) {
            return iBleConnectMaster;
        }
        IBleConnectMaster a2 = BleConnectMaster.a(str);
        this.f18502a.put(str, a2);
        return a2;
    }

    public void a(String str, BleConnectResponse bleConnectResponse) {
        a(str, (BleConnectOptions) null, bleConnectResponse);
    }

    public void a(String str, BleConnectOptions bleConnectOptions, BleConnectResponse bleConnectResponse) {
        if (!TextUtils.isEmpty(str)) {
            c(str).a(bleConnectOptions, bleConnectResponse);
        }
    }

    public void a(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(str).a();
        }
    }

    public void a(String str, UUID uuid, UUID uuid2, BleReadResponse bleReadResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).a(uuid, uuid2, bleReadResponse);
        }
    }

    public void a(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null && bArr != null) {
            c(str).a(uuid, uuid2, bArr, bleWriteResponse);
        }
    }

    public void b(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null && bArr != null) {
            c(str).b(uuid, uuid2, bArr, bleWriteResponse);
        }
    }

    public void a(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).a(uuid, uuid2, bleNotifyResponse);
        }
    }

    public void a(String str, UUID uuid, UUID uuid2) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).a(uuid, uuid2);
        }
    }

    public void b(String str, UUID uuid, UUID uuid2, BleNotifyResponse bleNotifyResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).b(uuid, uuid2, bleNotifyResponse);
        }
    }

    public void b(String str, UUID uuid, UUID uuid2) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).b(uuid, uuid2);
        }
    }

    public void a(String str, BleReadRssiResponse bleReadRssiResponse) {
        if (!TextUtils.isEmpty(str)) {
            c(str).a(bleReadRssiResponse);
        }
    }

    public void b() {
        for (IBleConnectMaster next : this.f18502a.values()) {
            if (next != null) {
                next.a();
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            c(str).b();
        }
    }

    public void a(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            c(str).a(i);
        }
    }

    public void a(String str, int i, BleRequestMtuResponse bleRequestMtuResponse) {
        if (!TextUtils.isEmpty(str)) {
            c(str).a(i, bleRequestMtuResponse);
        }
    }

    public void c(String str, UUID uuid, UUID uuid2, byte[] bArr, BleWriteResponse bleWriteResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null && bArr != null) {
            c(str).c(uuid, uuid2, bArr, bleWriteResponse);
        }
    }

    public void a(String str, UUID uuid, UUID uuid2, List<byte[]> list, BleWriteResponse bleWriteResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null && list != null) {
            c(str).a(str, uuid, uuid2, list, bleWriteResponse);
        }
    }

    public void a(String str, UUID uuid, UUID uuid2, BleResponse<Void> bleResponse) {
        if (!TextUtils.isEmpty(str) && uuid != null && uuid2 != null) {
            c(str).a(uuid, uuid2, bleResponse);
        }
    }

    public boolean handleMessage(Message message) {
        ProxyBulk.a(message.obj);
        return true;
    }

    public boolean a(Object obj, Method method, Object[] objArr) {
        this.c.obtainMessage(0, new ProxyBulk(obj, method, objArr)).sendToTarget();
        return true;
    }
}
