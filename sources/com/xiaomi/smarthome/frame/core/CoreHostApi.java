package com.xiaomi.smarthome.frame.core;

import android.os.Bundle;
import android.os.RemoteException;
import com.xiaomi.smarthome.core.server.IServerCallback;
import com.xiaomi.smarthome.frame.server_compact.ServerBean;
import java.util.Locale;

public abstract class CoreHostApi {
    public abstract void a() throws RemoteException;

    public abstract void a(int i, int i2, String str) throws RemoteException;

    public abstract void a(Bundle bundle);

    public abstract void a(ServerBean serverBean, ServerBean serverBean2) throws RemoteException;

    public abstract void a(String str, String str2, boolean z, String str3, IServerCallback iServerCallback) throws RemoteException;

    public abstract void a(Locale locale, Locale locale2) throws RemoteException;

    public abstract void b() throws RemoteException;

    public abstract void c() throws RemoteException;

    public abstract void d() throws RemoteException;

    public abstract void e() throws RemoteException;

    public abstract void f() throws RemoteException;
}
