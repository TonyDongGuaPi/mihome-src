package com.xiaomi.smarthome.framework.plugin;

import android.os.RemoteException;
import com.xiaomi.router.miio.miioplugin.IPluginCallback;

public class Mission {

    /* renamed from: a  reason: collision with root package name */
    public String f17132a;
    public String b;
    public IPluginCallback c;
    public int d;

    public void c() {
    }

    public void d() {
    }

    public String a() {
        return getClass().getSimpleName();
    }

    public Mission(String str, int i, String str2, IPluginCallback iPluginCallback) {
        this.f17132a = str;
        this.b = str2;
        this.c = iPluginCallback;
    }

    public void b() throws Exception {
        try {
            this.c.onRequestFailed(-9999, "");
        } catch (RemoteException unused) {
        }
    }

    public int e() {
        return this.d;
    }
}
