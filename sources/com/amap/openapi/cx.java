package com.amap.openapi;

import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;

public class cx {

    /* renamed from: a  reason: collision with root package name */
    LocationListener f4673a;
    long b;
    float c;
    private Handler d;
    private long e;

    public cx(LocationListener locationListener, long j, float f, Looper looper) {
        this.f4673a = locationListener;
        this.b = j;
        this.c = f;
        this.d = new Handler(looper == null ? Looper.getMainLooper() : looper) {
            public void handleMessage(Message message) {
                switch (message.what) {
                    case 1:
                        cx.this.f4673a.onLocationChanged(new Location((Location) message.obj));
                        return;
                    case 2:
                        cx.this.f4673a.onStatusChanged((String) message.obj, message.arg1, message.getData());
                        return;
                    case 3:
                        cx.this.f4673a.onProviderEnabled((String) message.obj);
                        return;
                    case 4:
                        cx.this.f4673a.onProviderDisabled((String) message.obj);
                        return;
                    default:
                        return;
                }
            }
        };
    }

    /* access modifiers changed from: package-private */
    public void a(Location location, float f) {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        if (f > this.c || elapsedRealtime - this.e > this.b) {
            this.e = elapsedRealtime;
            this.d.obtainMessage(1, location).sendToTarget();
        }
    }

    /* access modifiers changed from: package-private */
    public void a(String str, int i, Bundle bundle) {
        Message obtainMessage = this.d.obtainMessage(2, str);
        obtainMessage.arg1 = i;
        obtainMessage.sendToTarget();
    }

    /* access modifiers changed from: package-private */
    public void a(String str, boolean z) {
        this.d.obtainMessage(z ? 3 : 4, str).sendToTarget();
    }
}
