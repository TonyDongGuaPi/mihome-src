package com.amap.openapi;

import android.location.GpsStatus;
import android.location.OnNmeaMessageListener;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class cy {

    /* renamed from: a  reason: collision with root package name */
    private final List<a> f4675a = new CopyOnWriteArrayList();
    private cz b;
    private OnNmeaMessageListener c;
    private GpsStatus.NmeaListener d;

    private static class a {

        /* renamed from: a  reason: collision with root package name */
        cs f4678a;
        private Handler b;

        /* renamed from: com.amap.openapi.cy$a$a  reason: collision with other inner class name */
        private static class C0040a extends Handler {

            /* renamed from: a  reason: collision with root package name */
            private cs f4679a;

            C0040a(cs csVar, Looper looper) {
                super(looper);
                this.f4679a = csVar;
            }

            public void handleMessage(Message message) {
                Bundle data = message.getData();
                this.f4679a.a(data.getLong("timestamp"), data.getString("nmea"));
            }
        }

        a(cs csVar, Looper looper) {
            this.f4678a = csVar;
            this.b = new C0040a(this.f4678a, looper == null ? Looper.getMainLooper() : looper);
        }

        /* access modifiers changed from: package-private */
        public void a(long j, String str) {
            Message obtainMessage = this.b.obtainMessage();
            obtainMessage.getData().putLong("timestamp", j);
            obtainMessage.getData().putString("nmea", str);
            obtainMessage.sendToTarget();
        }

        /* access modifiers changed from: package-private */
        public boolean a(cs csVar, Looper looper) {
            if (looper == null) {
                looper = Looper.getMainLooper();
            }
            return this.f4678a == csVar && this.b.getLooper() == looper;
        }
    }

    public cy(cz czVar) {
        this.b = czVar;
        if (Build.VERSION.SDK_INT >= 24) {
            this.c = new OnNmeaMessageListener() {
                public void onNmeaMessage(String str, long j) {
                    cy.this.a(j, str);
                }
            };
        } else {
            this.d = new GpsStatus.NmeaListener() {
                public void onNmeaReceived(long j, String str) {
                    cy.this.a(j, str);
                }
            };
        }
    }

    private a b(cs csVar) {
        for (a next : this.f4675a) {
            if (next.f4678a == csVar) {
                return next;
            }
        }
        return null;
    }

    public void a(long j, String str) {
        synchronized (this.f4675a) {
            for (a a2 : this.f4675a) {
                a2.a(j, str);
            }
        }
    }

    public void a(cs csVar) {
        if (csVar != null) {
            synchronized (this.f4675a) {
                a b2 = b(csVar);
                if (b2 != null) {
                    this.f4675a.remove(b2);
                    if (this.f4675a.size() == 0) {
                        if (Build.VERSION.SDK_INT >= 24) {
                            if (this.c != null) {
                                this.b.a(this.c);
                            }
                        } else if (this.d != null) {
                            this.b.a(this.d);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:24:0x004d, code lost:
        return r0;
     */
    @android.support.annotation.RequiresPermission("android.permission.ACCESS_FINE_LOCATION")
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean a(com.amap.openapi.cs r5, android.os.Looper r6) {
        /*
            r4 = this;
            r0 = 0
            if (r5 != 0) goto L_0x0004
            return r0
        L_0x0004:
            java.util.List<com.amap.openapi.cy$a> r1 = r4.f4675a
            monitor-enter(r1)
            com.amap.openapi.cy$a r2 = r4.b(r5)     // Catch:{ all -> 0x0050 }
            if (r2 == 0) goto L_0x0013
            boolean r5 = r2.a((com.amap.openapi.cs) r5, (android.os.Looper) r6)     // Catch:{ all -> 0x0050 }
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r5
        L_0x0013:
            com.amap.openapi.cy$a r2 = new com.amap.openapi.cy$a     // Catch:{ all -> 0x0050 }
            r2.<init>(r5, r6)     // Catch:{ all -> 0x0050 }
            java.util.List<com.amap.openapi.cy$a> r5 = r4.f4675a     // Catch:{ all -> 0x0050 }
            r5.add(r2)     // Catch:{ all -> 0x0050 }
            java.util.List<com.amap.openapi.cy$a> r5 = r4.f4675a     // Catch:{ all -> 0x0050 }
            int r5 = r5.size()     // Catch:{ all -> 0x0050 }
            r3 = 1
            if (r5 != r3) goto L_0x004e
            int r5 = android.os.Build.VERSION.SDK_INT     // Catch:{ all -> 0x0050 }
            r3 = 24
            if (r5 < r3) goto L_0x0039
            android.location.OnNmeaMessageListener r5 = r4.c     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0045
            com.amap.openapi.cz r5 = r4.b     // Catch:{ all -> 0x0050 }
            android.location.OnNmeaMessageListener r0 = r4.c     // Catch:{ all -> 0x0050 }
            boolean r0 = r5.a((android.location.OnNmeaMessageListener) r0, (android.os.Looper) r6)     // Catch:{ all -> 0x0050 }
            goto L_0x0045
        L_0x0039:
            android.location.GpsStatus$NmeaListener r5 = r4.d     // Catch:{ all -> 0x0050 }
            if (r5 == 0) goto L_0x0045
            com.amap.openapi.cz r5 = r4.b     // Catch:{ all -> 0x0050 }
            android.location.GpsStatus$NmeaListener r0 = r4.d     // Catch:{ all -> 0x0050 }
            boolean r0 = r5.a((android.location.GpsStatus.NmeaListener) r0, (android.os.Looper) r6)     // Catch:{ all -> 0x0050 }
        L_0x0045:
            if (r0 != 0) goto L_0x004c
            java.util.List<com.amap.openapi.cy$a> r5 = r4.f4675a     // Catch:{ all -> 0x0050 }
            r5.remove(r2)     // Catch:{ all -> 0x0050 }
        L_0x004c:
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r0
        L_0x004e:
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            return r3
        L_0x0050:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0050 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.cy.a(com.amap.openapi.cs, android.os.Looper):boolean");
    }
}
