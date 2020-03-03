package com.loc;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Messenger;
import android.text.TextUtils;
import com.amap.api.location.APSServiceBase;
import com.loc.n;
import com.xiaomi.miot.support.monitor.core.activity.ActivityInfo;

public final class o implements APSServiceBase {

    /* renamed from: a  reason: collision with root package name */
    n f6626a = null;
    Context b = null;
    Messenger c = null;

    public o(Context context) {
        this.b = context.getApplicationContext();
        this.f6626a = new n(this.b);
    }

    public final IBinder onBind(Intent intent) {
        n nVar = this.f6626a;
        String stringExtra = intent.getStringExtra("a");
        if (!TextUtils.isEmpty(stringExtra)) {
            v.a(nVar.e, stringExtra);
        }
        nVar.f6622a = intent.getStringExtra("b");
        u.a(nVar.f6622a);
        String stringExtra2 = intent.getStringExtra("d");
        if (!TextUtils.isEmpty(stringExtra2)) {
            x.a(stringExtra2);
        }
        er.f6594a = intent.getBooleanExtra("f", true);
        n nVar2 = this.f6626a;
        if ("true".equals(intent.getStringExtra("as")) && nVar2.d != null) {
            nVar2.d.sendEmptyMessageDelayed(9, 100);
        }
        this.c = new Messenger(this.f6626a.d);
        return this.c.getBinder();
    }

    public final void onCreate() {
        try {
            n.c();
            this.f6626a.j = fa.c();
            this.f6626a.k = fa.b();
            n nVar = this.f6626a;
            nVar.i = new ey();
            nVar.b = new n.b("amapLocCoreThread");
            nVar.b.setPriority(5);
            nVar.b.start();
            nVar.d = new n.a(nVar.b.getLooper());
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "onCreate");
        }
    }

    public final void onDestroy() {
        try {
            if (this.f6626a != null) {
                this.f6626a.d.sendEmptyMessage(11);
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", ActivityInfo.TYPE_STR_ONDESTROY);
        }
    }

    public final int onStartCommand(Intent intent, int i, int i2) {
        return 0;
    }
}
