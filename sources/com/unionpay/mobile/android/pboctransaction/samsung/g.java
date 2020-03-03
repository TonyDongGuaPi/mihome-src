package com.unionpay.mobile.android.pboctransaction.samsung;

import android.os.Handler;

final class g implements Handler.Callback {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ f f9659a;

    g(f fVar) {
        this.f9659a = fVar;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v31, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v33, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v41, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v42, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v43, resolved type: com.unionpay.mobile.android.model.a} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v44, resolved type: java.util.ArrayList} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v26, resolved type: com.unionpay.mobile.android.model.a} */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean handleMessage(android.os.Message r12) {
        /*
            r11 = this;
            int r0 = r12.what
            r1 = 0
            r2 = 4
            r3 = 1
            if (r0 == r3) goto L_0x01db
            r4 = 1000(0x3e8, float:1.401E-42)
            if (r0 == r4) goto L_0x01c1
            r4 = 1018(0x3fa, float:1.427E-42)
            if (r0 == r4) goto L_0x018a
            switch(r0) {
                case 3: goto L_0x017d;
                case 4: goto L_0x016b;
                default: goto L_0x0012;
            }
        L_0x0012:
            switch(r0) {
                case 1011: goto L_0x0148;
                case 1012: goto L_0x012c;
                case 1013: goto L_0x011c;
                case 1014: goto L_0x0074;
                case 1015: goto L_0x0032;
                case 1016: goto L_0x0017;
                default: goto L_0x0015;
            }
        L_0x0015:
            goto L_0x01f6
        L_0x0017:
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            android.os.Handler r12 = r12.G
            r12.removeMessages(r2)
            java.lang.String r12 = "uppay-spay"
            java.lang.String r0 = "check spay support"
        L_0x0024:
            com.unionpay.mobile.android.utils.k.c(r12, r0)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            com.unionpay.mobile.android.pboctransaction.samsung.f$a r12 = r12.m
            r12.a(r3)
            goto L_0x01f6
        L_0x0032:
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.G
            r0.removeMessages(r2)
            java.lang.String r0 = "uppay-spay"
            java.lang.String r2 = "get spay list call back"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            java.lang.Object r12 = r12.obj
            com.unionpay.tsmservice.data.VirtualCardInfo r12 = (com.unionpay.tsmservice.data.VirtualCardInfo) r12
            com.unionpay.mobile.android.model.a r0 = new com.unionpay.mobile.android.model.a
            r3 = 32
            com.unionpay.tsmservice.AppID r2 = r12.getAppID()
            java.lang.String r4 = r2.getAppAid()
            java.lang.String r5 = ""
            java.lang.String r6 = r12.getCardNo()
            r7 = 1
            r2 = r0
            r2.<init>(r3, r4, r5, r6, r7)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            android.os.Handler r12 = r12.o
            r2 = 2000(0x7d0, float:2.803E-42)
        L_0x0065:
            android.os.Message r12 = r12.obtainMessage(r2, r0)
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.o
            r0.sendMessage(r12)
            goto L_0x01f6
        L_0x0074:
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.G
            r0.removeMessages(r2)
            java.lang.String r0 = "uppay"
            java.lang.String r2 = "list success"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.o
            if (r0 == 0) goto L_0x01f6
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.Object r12 = r12.obj
            com.unionpay.tsmservice.result.GetSeAppListResult r12 = (com.unionpay.tsmservice.result.GetSeAppListResult) r12
            com.unionpay.tsmservice.data.SeAppListItem[] r12 = r12.getSeAppList()
            if (r12 == 0) goto L_0x0112
            int r2 = r12.length
            if (r2 <= 0) goto L_0x0112
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r2 = 0
        L_0x00a4:
            int r4 = r12.length
            if (r2 >= r4) goto L_0x0112
            r4 = r12[r2]
            if (r4 == 0) goto L_0x010f
            r4 = r12[r2]
            com.unionpay.tsmservice.data.AppDetail r4 = r4.getAppDetail()
            if (r4 == 0) goto L_0x010f
            r4 = r12[r2]
            com.unionpay.tsmservice.data.AppDetail r4 = r4.getAppDetail()
            com.unionpay.tsmservice.AppID r4 = r4.getAppID()
            if (r4 == 0) goto L_0x010f
            r4 = r12[r2]
            com.unionpay.tsmservice.data.AppDetail r4 = r4.getAppDetail()
            com.unionpay.tsmservice.AppID r4 = r4.getAppID()
            java.lang.String r4 = r4.getAppAid()
            if (r4 == 0) goto L_0x00e7
            int r5 = r4.length()
            r6 = 16
            if (r5 <= r6) goto L_0x00e7
            r5 = 14
            java.lang.String r4 = r4.substring(r5, r6)
            java.lang.String r5 = "06"
            boolean r4 = r5.equalsIgnoreCase(r4)
            if (r4 != 0) goto L_0x00e7
            r4 = 0
            goto L_0x00e8
        L_0x00e7:
            r4 = 1
        L_0x00e8:
            if (r4 != 0) goto L_0x010f
            com.unionpay.mobile.android.model.a r4 = new com.unionpay.mobile.android.model.a
            r6 = 1
            r5 = r12[r2]
            com.unionpay.tsmservice.data.AppDetail r5 = r5.getAppDetail()
            com.unionpay.tsmservice.AppID r5 = r5.getAppID()
            java.lang.String r7 = r5.getAppAid()
            java.lang.String r8 = ""
            r5 = r12[r2]
            com.unionpay.tsmservice.data.AppDetail r5 = r5.getAppDetail()
            java.lang.String r9 = r5.getMpan()
            r10 = 1
            r5 = r4
            r5.<init>(r6, r7, r8, r9, r10)
            r0.add(r4)
        L_0x010f:
            int r2 = r2 + 1
            goto L_0x00a4
        L_0x0112:
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            android.os.Handler r12 = r12.G
            r2 = 8
            goto L_0x0065
        L_0x011c:
            java.lang.String r12 = "uppay"
            java.lang.String r0 = "close channel success"
            com.unionpay.mobile.android.utils.k.c(r12, r0)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            java.lang.String r0 = "success"
            java.lang.String unused = r12.x = r0
            goto L_0x01f6
        L_0x012c:
            java.lang.String r0 = "uppay"
            java.lang.String r2 = "apdu success version 3.3.1"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.G
            r2 = 3
            r0.removeMessages(r2)
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            java.lang.Object r12 = r12.obj
            java.lang.String r12 = (java.lang.String) r12
            java.lang.String unused = r0.v = r12
            goto L_0x01f6
        L_0x0148:
            java.lang.String r0 = "uppay"
            java.lang.String r2 = "channel success"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            java.lang.Object r12 = r12.obj
            android.os.Bundle r12 = (android.os.Bundle) r12
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            java.lang.String r2 = "channel"
            java.lang.String r2 = r12.getString(r2)
            java.lang.String unused = r0.s = r2
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            java.lang.String r2 = "apdu"
            java.lang.String r12 = r12.getString(r2)
            java.lang.String unused = r0.t = r12
            goto L_0x01f6
        L_0x016b:
            java.lang.String r0 = "uppay"
            java.lang.String r2 = "timeout"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            int r12 = r12.arg1
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            java.lang.String r2 = ""
            com.unionpay.mobile.android.pboctransaction.samsung.f.a(r0, r12, r2)
            goto L_0x01f6
        L_0x017d:
            java.lang.String r12 = "uppay-spay"
            java.lang.String r0 = "send apdu time out"
            com.unionpay.mobile.android.utils.k.c(r12, r0)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            boolean unused = r12.u = true
            goto L_0x01f6
        L_0x018a:
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.G
            r0.removeMessages(r2)
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            java.lang.Object r12 = r12.obj
            android.os.Bundle r12 = (android.os.Bundle) r12
            java.lang.String r2 = "KEY_SUCCESS_VENDOR"
            boolean r12 = r12.getBoolean(r2)
            boolean unused = r0.E = r12
            java.lang.String r12 = "uppay-spay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "mIsVendorStateReady: "
            r0.<init>(r2)
            com.unionpay.mobile.android.pboctransaction.samsung.f r2 = r11.f9659a
            boolean r2 = r2.E
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            com.unionpay.mobile.android.utils.k.c(r12, r0)
            java.lang.String r12 = "uppay-spay"
            java.lang.String r0 = "get vendor pay status"
            goto L_0x0024
        L_0x01c1:
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            android.os.Handler r12 = r12.G
            r12.removeMessages(r2)
            java.lang.String r12 = "uppay"
            java.lang.String r0 = "init success"
            com.unionpay.mobile.android.utils.k.c(r12, r0)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            r12.a((boolean) r3)
            com.unionpay.mobile.android.pboctransaction.samsung.f r12 = r11.f9659a
            r12.h = r3
            goto L_0x01f6
        L_0x01db:
            com.unionpay.mobile.android.pboctransaction.samsung.f r0 = r11.f9659a
            android.os.Handler r0 = r0.G
            r0.removeMessages(r2)
            java.lang.String r0 = "uppay"
            java.lang.String r2 = "msg error"
            com.unionpay.mobile.android.utils.k.c(r0, r2)
            int r0 = r12.arg1
            java.lang.Object r12 = r12.obj
            java.lang.String r12 = (java.lang.String) r12
            com.unionpay.mobile.android.pboctransaction.samsung.f r2 = r11.f9659a
            com.unionpay.mobile.android.pboctransaction.samsung.f.a(r2, r0, r12)
        L_0x01f6:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.samsung.g.handleMessage(android.os.Message):boolean");
    }
}
