package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.GnssStatus;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import com.amap.location.common.log.ALLog;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@RequiresApi(api = 24)
public class db extends GnssStatus.Callback {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<a> f4682a = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public cz b;
    private Context c;
    private b d = new b(this);

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        private Handler f4683a;

        /* access modifiers changed from: package-private */
        public void a(int i, Object obj) {
            Message obtainMessage = this.f4683a.obtainMessage();
            obtainMessage.what = i;
            obtainMessage.obj = obj;
            obtainMessage.sendToTarget();
        }
    }

    private class b extends BroadcastReceiver {
        private GnssStatus.Callback b;

        public b(GnssStatus.Callback callback) {
            this.b = callback;
        }

        public void onReceive(Context context, Intent intent) {
            if (cr.a(context).a("gps")) {
                synchronized (db.this.f4682a) {
                    if (db.this.f4682a.size() > 0) {
                        try {
                            db.this.b.b(this.b);
                            db.this.b.a(this.b);
                        } catch (SecurityException e) {
                            ALLog.a("@_24_5_@", "卫星老接口权限异常", (Exception) e);
                        }
                    }
                }
            }
        }
    }

    public db(cz czVar, Context context) {
        this.b = czVar;
        this.c = context;
    }

    public void onFirstFix(int i) {
        synchronized (this.f4682a) {
            for (a a2 : this.f4682a) {
                a2.a(3, Integer.valueOf(i));
            }
        }
    }

    public void onSatelliteStatusChanged(GnssStatus gnssStatus) {
        synchronized (this.f4682a) {
            for (a a2 : this.f4682a) {
                a2.a(4, gnssStatus);
            }
        }
    }

    public void onStarted() {
        synchronized (this.f4682a) {
            for (a a2 : this.f4682a) {
                a2.a(1, (Object) null);
            }
        }
    }

    public void onStopped() {
        synchronized (this.f4682a) {
            for (a a2 : this.f4682a) {
                a2.a(2, (Object) null);
            }
        }
    }
}
