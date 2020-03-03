package com.tencent.smtt.sdk;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import com.tencent.smtt.sdk.QbSdk;

final class j extends Handler {

    /* renamed from: a  reason: collision with root package name */
    final /* synthetic */ QbSdk.PreInitCallback f9181a;
    final /* synthetic */ Context b;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    j(Looper looper, QbSdk.PreInitCallback preInitCallback, Context context) {
        super(looper);
        this.f9181a = preInitCallback;
        this.b = context;
    }

    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0017, code lost:
        r2.onViewInitFinished(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void handleMessage(android.os.Message r2) {
        /*
            r1 = this;
            int r2 = r2.what
            switch(r2) {
                case 1: goto L_0x001e;
                case 2: goto L_0x0010;
                case 3: goto L_0x0006;
                default: goto L_0x0005;
            }
        L_0x0005:
            goto L_0x0039
        L_0x0006:
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            if (r2 == 0) goto L_0x0039
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            r2.onCoreInitFinished()
            goto L_0x0039
        L_0x0010:
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            if (r2 == 0) goto L_0x001a
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            r0 = 0
        L_0x0017:
            r2.onViewInitFinished(r0)
        L_0x001a:
            com.tencent.smtt.utils.TbsLog.writeLogToDisk()
            goto L_0x0039
        L_0x001e:
            boolean r2 = com.tencent.smtt.sdk.QbSdk.j
            if (r2 == 0) goto L_0x0031
            com.tencent.smtt.sdk.bt r2 = com.tencent.smtt.sdk.bt.a()
            com.tencent.smtt.sdk.bu r2 = r2.c()
            if (r2 == 0) goto L_0x0031
            android.content.Context r0 = r1.b
            r2.a((android.content.Context) r0)
        L_0x0031:
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            if (r2 == 0) goto L_0x001a
            com.tencent.smtt.sdk.QbSdk$PreInitCallback r2 = r1.f9181a
            r0 = 1
            goto L_0x0017
        L_0x0039:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tencent.smtt.sdk.j.handleMessage(android.os.Message):void");
    }
}
