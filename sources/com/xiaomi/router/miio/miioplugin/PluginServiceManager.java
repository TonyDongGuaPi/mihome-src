package com.xiaomi.router.miio.miioplugin;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import com.xiaomi.router.miio.miioplugin.IPluginRequest;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.page.DeviceMoreActivity;
import com.xiaomi.smarthome.framework.plugin.PluginService;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class PluginServiceManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13052a = "param_did";
    public static final String b = "param_msg_type";
    public static final String c = "param_msg_params";
    public static final int d = 100;
    public static final int e = 101;
    public static final int f = 102;
    private static PluginServiceManager g = null;
    private static final String k = "com.xiaomi.smarthome.plugin.request";
    /* access modifiers changed from: private */
    public IPluginRequest h;
    /* access modifiers changed from: private */
    public volatile boolean i = false;
    /* access modifiers changed from: private */
    public List<WeakReference<BindServiceListener>> j = new ArrayList();
    private ServiceConnection l = new ServiceConnection() {
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            synchronized (PluginServiceManager.this) {
                IPluginRequest unused = PluginServiceManager.this.h = IPluginRequest.Stub.asInterface(iBinder);
                boolean unused2 = PluginServiceManager.this.i = false;
                for (WeakReference weakReference : PluginServiceManager.this.j) {
                    if (weakReference.get() != null) {
                        ((BindServiceListener) weakReference.get()).onBindService(PluginServiceManager.this.h);
                    }
                }
                PluginServiceManager.this.j.clear();
            }
        }

        public void onServiceDisconnected(ComponentName componentName) {
            boolean unused = PluginServiceManager.this.i = false;
            IPluginRequest unused2 = PluginServiceManager.this.h = null;
            for (WeakReference weakReference : PluginServiceManager.this.j) {
                if (weakReference.get() != null) {
                    ((BindServiceListener) weakReference.get()).onBindService(PluginServiceManager.this.h);
                }
            }
            PluginServiceManager.this.j.clear();
        }
    };

    public interface BindServiceListener {
        void onBindService(IPluginRequest iPluginRequest);
    }

    @Deprecated
    public static class Msg {

        /* renamed from: a  reason: collision with root package name */
        public int f13054a;
        public String b;
    }

    public static PluginServiceManager a() {
        if (g == null) {
            g = new PluginServiceManager();
        }
        return g;
    }

    private PluginServiceManager() {
    }

    @Deprecated
    public synchronized void a(BindServiceListener bindServiceListener) {
        this.j.add(new WeakReference(bindServiceListener));
    }

    @Deprecated
    public synchronized void b(BindServiceListener bindServiceListener) {
        for (int size = this.j.size() - 1; size >= 0; size--) {
            if (this.j.get(size).get() == bindServiceListener) {
                this.j.remove(size);
            }
        }
    }

    public IPluginRequest b() {
        if (this.h == null) {
            SHApplication.getAppContext().bindService(new Intent(SHApplication.getAppContext(), PluginService.class), this.l, 1);
            this.i = true;
        }
        return this.h;
    }

    public synchronized void c(BindServiceListener bindServiceListener) {
        if (this.h != null) {
            if (bindServiceListener != null) {
                bindServiceListener.onBindService(this.h);
            }
        } else if (this.i) {
            this.j.add(new WeakReference(bindServiceListener));
        } else {
            SHApplication.getAppContext().bindService(new Intent(SHApplication.getAppContext(), PluginService.class), this.l, 1);
            this.i = true;
        }
    }

    public void c() {
        if (this.h != null) {
            SHApplication.getAppContext().unbindService(this.l);
            this.h = null;
        }
    }

    @Deprecated
    public void a(Activity activity, String str, ArrayList<String> arrayList, boolean z, int i2) {
        Intent intent = new Intent();
        intent.setClassName(activity.getPackageName(), DeviceMoreActivity.class.getName());
        intent.putExtra("did", str);
        intent.putStringArrayListExtra("menus", arrayList);
        intent.putExtra("useDefaultMenus", z);
        activity.startActivityForResult(intent, i2);
        activity.overridePendingTransition(0, 0);
    }

    @Deprecated
    public static Msg a(Intent intent) {
        Bundle extras = intent.getExtras();
        if (extras == null) {
            return null;
        }
        int i2 = extras.getInt(b);
        String string = extras.getString(c);
        Msg msg = new Msg();
        msg.f13054a = i2;
        msg.b = string;
        return msg;
    }
}
