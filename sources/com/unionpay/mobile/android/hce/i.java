package com.unionpay.mobile.android.hce;

import android.content.ComponentName;
import android.content.ServiceConnection;

final class i implements ServiceConnection {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ String f9568a;
    final /* synthetic */ String b;
    final /* synthetic */ f c;

    i(f fVar, String str, String str2) {
        this.c = fVar;
        this.f9568a = str;
        this.b = str2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onServiceConnected(android.content.ComponentName r9, android.os.IBinder r10) {
        /*
            r8 = this;
            com.unionpay.mobile.android.hce.f r9 = r8.c
            android.os.Handler r9 = r9.y
            java.lang.String r0 = r8.f9568a
            r1 = 2006(0x7d6, float:2.811E-42)
            r9.removeMessages(r1, r0)
            r9 = 0
            com.unionpay.mobile.android.hce.service.a r10 = com.unionpay.mobile.android.hce.service.a.C0072a.a(r10)     // Catch:{ Exception -> 0x0013 }
            goto L_0x0018
        L_0x0013:
            r10 = move-exception
            r10.printStackTrace()
            r10 = r9
        L_0x0018:
            if (r10 == 0) goto L_0x00aa
            com.unionpay.mobile.android.hce.f r0 = r8.c     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            java.lang.String r0 = r0.d     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            com.unionpay.mobile.android.hce.f r2 = r8.c     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            java.lang.String r2 = r2.e     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            com.unionpay.mobile.android.hce.b r3 = new com.unionpay.mobile.android.hce.b     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            r4 = 2003(0x7d3, float:2.807E-42)
            java.lang.String r5 = r8.f9568a     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            com.unionpay.mobile.android.hce.f r6 = r8.c     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            android.os.Handler r6 = r6.y     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            r3.<init>(r4, r5, r6)     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            java.lang.String r0 = r10.a(r0, r2, r3)     // Catch:{ RemoteException -> 0x005b, Exception -> 0x005f }
            com.unionpay.mobile.android.hce.f r9 = r8.c     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            android.os.Handler r9 = r9.y     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            java.lang.String r2 = r8.f9568a     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            android.os.Message r9 = r9.obtainMessage(r1, r2)     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            com.unionpay.mobile.android.hce.f r1 = r8.c     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            android.os.Handler r1 = r1.y     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            com.unionpay.mobile.android.hce.f r2 = r8.c     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            int r2 = r2.i     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            long r2 = (long) r2     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            r1.sendMessageDelayed(r9, r2)     // Catch:{ RemoteException -> 0x0056, Exception -> 0x0060 }
            goto L_0x0060
        L_0x0056:
            r9 = move-exception
            r7 = r0
            r0 = r9
            r9 = r7
            goto L_0x005c
        L_0x005b:
            r0 = move-exception
        L_0x005c:
            r0.printStackTrace()
        L_0x005f:
            r0 = r9
        L_0x0060:
            if (r0 == 0) goto L_0x00aa
            java.lang.String r9 = r8.b
            java.lang.String r9 = com.unionpay.mobile.android.hce.a.a(r0, r9)
            com.unionpay.mobile.android.hce.f r0 = r8.c
            java.util.concurrent.ConcurrentHashMap r0 = r0.v
            java.lang.String r1 = r8.f9568a
            java.lang.Object r0 = r0.get(r1)
            com.unionpay.mobile.android.hce.l r0 = (com.unionpay.mobile.android.hce.l) r0
            if (r0 != 0) goto L_0x007f
            com.unionpay.mobile.android.hce.l r0 = new com.unionpay.mobile.android.hce.l
            java.lang.String r1 = r8.f9568a
            r0.<init>(r1)
        L_0x007f:
            r0.a((java.lang.String) r9)
            r0.a((com.unionpay.mobile.android.hce.service.a) r10)
            r0.d()
            com.unionpay.mobile.android.hce.f r9 = r8.c
            java.util.concurrent.ConcurrentHashMap r9 = r9.v
            java.lang.String r10 = r8.f9568a
            r9.put(r10, r0)
            com.unionpay.mobile.android.hce.f r9 = r8.c
            android.os.Handler r9 = r9.y
            r10 = 2002(0x7d2, float:2.805E-42)
            java.lang.String r0 = r8.f9568a
            android.os.Message r9 = r9.obtainMessage(r10, r0)
            com.unionpay.mobile.android.hce.f r10 = r8.c
            android.os.Handler r10 = r10.y
            r10.sendMessage(r9)
        L_0x00aa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.hce.i.onServiceConnected(android.content.ComponentName, android.os.IBinder):void");
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        this.c.y.removeMessages(2006, this.f9568a);
        this.c.y.sendMessage(this.c.y.obtainMessage(2005, this.f9568a));
    }
}
