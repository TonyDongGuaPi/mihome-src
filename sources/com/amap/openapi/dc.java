package com.amap.openapi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.location.GpsStatus;
import android.os.Handler;
import android.os.Message;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class dc implements GpsStatus.Listener {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public final List<b> f4685a = new CopyOnWriteArrayList();
    /* access modifiers changed from: private */
    public cz b;
    private Context c;
    private a d = new a(this);

    private class a extends BroadcastReceiver {
        private GpsStatus.Listener b;

        public a(GpsStatus.Listener listener) {
            this.b = listener;
        }

        public void onReceive(Context context, Intent intent) {
            if (cr.a(context).a("gps")) {
                synchronized (dc.this.f4685a) {
                    if (dc.this.f4685a.size() > 0) {
                        dc.this.b.b(this.b);
                        dc.this.b.a(this.b);
                    }
                }
            }
        }
    }

    private static class b {

        /* renamed from: a  reason: collision with root package name */
        private Handler f4687a;

        /* access modifiers changed from: package-private */
        public void a(int i) {
            Message obtainMessage = this.f4687a.obtainMessage();
            obtainMessage.arg1 = i;
            obtainMessage.sendToTarget();
        }
    }

    public dc(cz czVar, Context context) {
        this.b = czVar;
        this.c = context;
    }

    public void onGpsStatusChanged(int i) {
        synchronized (this.f4685a) {
            for (b a2 : this.f4685a) {
                a2.a(i);
            }
        }
    }
}
