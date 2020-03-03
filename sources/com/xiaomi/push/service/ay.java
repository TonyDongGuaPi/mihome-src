package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import com.xiaomi.channel.commonutils.logger.b;
import com.xiaomi.push.ab;
import com.xiaomi.push.l;
import com.xiaomi.stat.c.c;
import java.util.ArrayList;
import java.util.List;

public class ay {

    /* renamed from: a  reason: collision with root package name */
    private static ay f12895a;

    /* renamed from: a  reason: collision with other field name */
    private static String f306a;

    /* renamed from: a  reason: collision with other field name */
    private Context f307a;

    /* renamed from: a  reason: collision with other field name */
    private Messenger f308a;

    /* renamed from: a  reason: collision with other field name */
    private List<Message> f309a = new ArrayList();

    /* renamed from: a  reason: collision with other field name */
    private boolean f310a = false;
    /* access modifiers changed from: private */
    public Messenger b;
    /* access modifiers changed from: private */

    /* renamed from: b  reason: collision with other field name */
    public boolean f311b = false;

    private ay(Context context) {
        this.f307a = context.getApplicationContext();
        this.f308a = new Messenger(new az(this, Looper.getMainLooper()));
        if (a()) {
            b.c("use miui push service");
            this.f310a = true;
        }
    }

    private Message a(Intent intent) {
        Message obtain = Message.obtain();
        obtain.what = 17;
        obtain.obj = intent;
        return obtain;
    }

    public static ay a(Context context) {
        if (f12895a == null) {
            f12895a = new ay(context);
        }
        return f12895a;
    }

    /* renamed from: a  reason: collision with other method in class */
    private synchronized void m314a(Intent intent) {
        if (this.f311b) {
            Message a2 = a(intent);
            if (this.f309a.size() >= 50) {
                this.f309a.remove(0);
            }
            this.f309a.add(a2);
            return;
        } else if (this.b == null) {
            Context context = this.f307a;
            ba baVar = new ba(this);
            Context context2 = this.f307a;
            context.bindService(intent, baVar, 1);
            this.f311b = true;
            this.f309a.clear();
            this.f309a.add(a(intent));
        } else {
            try {
                this.b.send(a(intent));
            } catch (RemoteException unused) {
                this.b = null;
                this.f311b = false;
            }
        }
        return;
    }

    private boolean a() {
        if (ab.f) {
            return false;
        }
        try {
            PackageInfo packageInfo = this.f307a.getPackageManager().getPackageInfo(c.f23036a, 4);
            return packageInfo != null && packageInfo.versionCode >= 104;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: a  reason: collision with other method in class */
    public boolean m315a(Intent intent) {
        try {
            if (l.a() || Build.VERSION.SDK_INT < 26) {
                this.f307a.startService(intent);
                return true;
            }
            a(intent);
            return true;
        } catch (Exception e) {
            b.a((Throwable) e);
            return false;
        }
    }
}
