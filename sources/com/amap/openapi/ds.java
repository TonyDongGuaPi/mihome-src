package com.amap.openapi;

import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import com.amap.location.common.network.IHttpClient;
import java.nio.charset.Charset;

public class ds {

    /* renamed from: a  reason: collision with root package name */
    private static Charset f4704a = Charset.forName("UTF-8");
    private ec b;
    private ee c;
    private ee d;
    private ee e;
    private ee f;
    private dt g;
    private IHttpClient h;

    public void a() {
        int c2 = this.g.c();
        if (this.b != null) {
            this.b.b(c2);
        }
        if (this.c != null) {
            this.c.a(c2);
        }
        if (this.e != null) {
            this.e.a(c2);
        }
        if (this.f != null) {
            this.f.a(c2);
        }
        if (this.d != null) {
            this.d.a(c2);
        }
    }

    public void a(Message message) {
        switch (message.arg1) {
            case 1:
                if (this.b != null) {
                    this.b.a(message.arg2);
                    return;
                }
                return;
            case 2:
                if (this.c != null) {
                    this.c.a(message.arg2, (byte[]) message.obj);
                    return;
                }
                return;
            case 3:
                if (this.e != null) {
                    this.e.a(message.arg2, (byte[]) message.obj);
                    return;
                }
                return;
            case 4:
                if (this.f != null) {
                    this.f.a(message.arg2, ((String) message.obj).getBytes(f4704a));
                    return;
                }
                return;
            case 5:
                if (this.d != null) {
                    this.d.a(message.arg2, (byte[]) message.obj);
                    return;
                }
                return;
            default:
                return;
        }
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0025, code lost:
        r0.b();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void a(@android.support.annotation.NonNull com.amap.openapi.dm r5) {
        /*
            r4 = this;
            boolean r0 = r5 instanceof com.amap.openapi.dn
            if (r0 == 0) goto L_0x003b
            com.amap.openapi.dt r0 = r4.g
            int r0 = r0.c()
            r1 = -1
            if (r0 != r1) goto L_0x000e
            return
        L_0x000e:
            com.amap.openapi.dn r5 = (com.amap.openapi.dn) r5
            int r0 = r5.b()
            switch(r0) {
                case 3: goto L_0x001f;
                case 4: goto L_0x0018;
                default: goto L_0x0017;
            }
        L_0x0017:
            goto L_0x0028
        L_0x0018:
            com.amap.openapi.ee r0 = r4.f
            if (r0 == 0) goto L_0x0028
            com.amap.openapi.ee r0 = r4.f
            goto L_0x0025
        L_0x001f:
            com.amap.openapi.ee r0 = r4.e
            if (r0 == 0) goto L_0x0028
            com.amap.openapi.ee r0 = r4.e
        L_0x0025:
            r0.b()
        L_0x0028:
            java.lang.Thread r0 = new java.lang.Thread
            com.amap.openapi.do r1 = new com.amap.openapi.do
            com.amap.openapi.dt r2 = r4.g
            com.amap.location.common.network.IHttpClient r3 = r4.h
            r1.<init>(r2, r5, r3)
            java.lang.String r5 = "command_thread"
            r0.<init>(r1, r5)
            r0.start()
        L_0x003b:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amap.openapi.ds.a(com.amap.openapi.dm):void");
    }

    public void a(@NonNull dt dtVar, @NonNull dk dkVar, @NonNull Looper looper) {
        this.g = dtVar;
        this.h = dkVar.f;
        if (dkVar.f4700a != null) {
            this.b = new ec();
            this.b.a(this.g, dkVar.f4700a, this.h, looper);
        }
        if (dkVar.b != null) {
            this.c = new ee();
            this.c.a(this.g, dkVar.b, this.h, 2, looper);
        }
        if (dkVar.c != null) {
            this.d = new ee();
            this.d.a(this.g, dkVar.c, this.h, 5, looper);
        }
        if (dkVar.d != null) {
            this.e = new ee();
            this.e.a(this.g, dkVar.d, this.h, 3, looper);
        }
        if (dkVar.e != null) {
            this.f = new ee();
            this.f.a(this.g, dkVar.e, this.h, 4, looper);
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.a();
        }
        if (this.c != null) {
            this.c.a();
        }
        if (this.e != null) {
            this.e.a();
        }
        if (this.f != null) {
            this.f.a();
        }
        if (this.d != null) {
            this.d.a();
        }
    }
}
