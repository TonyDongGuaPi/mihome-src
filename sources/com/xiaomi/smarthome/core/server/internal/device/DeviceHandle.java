package com.xiaomi.smarthome.core.server.internal.device;

import com.xiaomi.smarthome.core.server.internal.NetHandle;
import java.lang.ref.WeakReference;

public class DeviceHandle {

    /* renamed from: a  reason: collision with root package name */
    private final WeakReference<NetHandle> f14504a;

    public DeviceHandle(NetHandle netHandle) {
        this.f14504a = new WeakReference<>(netHandle);
    }

    public void a() {
        NetHandle netHandle = (NetHandle) this.f14504a.get();
        if (netHandle != null) {
            netHandle.a();
        }
    }
}
