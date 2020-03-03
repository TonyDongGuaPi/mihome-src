package com.loc;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.text.TextUtils;
import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClientOption;
import com.autonavi.aps.amapapi.model.AMapLocationServer;
import com.mi.global.shop.model.Tags;
import com.payu.custombrowser.util.CBConstant;
import com.taobao.weex.el.parse.Operators;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

public final class n {
    static boolean g = false;
    /* access modifiers changed from: private */
    public r A = null;
    private boolean B = true;
    private String C = "";
    private final int D = 5000;
    private String E = "jsonp1";

    /* renamed from: a  reason: collision with root package name */
    String f6622a = null;
    b b = null;
    AMapLocation c = null;
    a d = null;
    Context e = null;
    cs f = null;
    HashMap<Messenger, Long> h = new HashMap<>();
    ey i = null;
    long j = 0;
    long k = 0;
    String l = null;
    AMapLocationClientOption m = null;
    AMapLocationClientOption n = new AMapLocationClientOption();
    ServerSocket o = null;
    boolean p = false;
    Socket q = null;
    boolean r = false;
    c s = null;
    private boolean t = false;
    private boolean u = false;
    private long v = 0;
    private long w = 0;
    private AMapLocationServer x = null;
    private long y = 0;
    private int z = 0;

    public class a extends Handler {
        public a(Looper looper) {
            super(looper);
        }

        /* JADX WARNING: No exception handlers in catch block: Catch:{  } */
        /* JADX WARNING: Removed duplicated region for block: B:20:0x003c A[Catch:{ Throwable -> 0x0055 }] */
        /* JADX WARNING: Removed duplicated region for block: B:33:0x0069 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:34:0x006b A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:35:0x0072 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:36:0x0079 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:37:0x0085 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:38:0x0091 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x009d A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:40:0x00a8 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:41:0x00b3 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:46:0x00c8 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:59:0x00f6 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* JADX WARNING: Removed duplicated region for block: B:60:0x0101 A[Catch:{ Throwable -> 0x00eb, Throwable -> 0x010f }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void handleMessage(android.os.Message r8) {
            /*
                r7 = this;
                r0 = 1
                r1 = 0
                android.os.Bundle r2 = r8.getData()     // Catch:{ Throwable -> 0x005a }
                android.os.Messenger r3 = r8.replyTo     // Catch:{ Throwable -> 0x0057 }
                if (r2 == 0) goto L_0x0064
                boolean r4 = r2.isEmpty()     // Catch:{ Throwable -> 0x0055 }
                if (r4 != 0) goto L_0x0064
                java.lang.String r4 = "c"
                java.lang.String r4 = r2.getString(r4)     // Catch:{ Throwable -> 0x0055 }
                com.loc.n r5 = com.loc.n.this     // Catch:{ Throwable -> 0x0055 }
                java.lang.String r6 = r5.l     // Catch:{ Throwable -> 0x0055 }
                boolean r6 = android.text.TextUtils.isEmpty(r6)     // Catch:{ Throwable -> 0x0055 }
                if (r6 == 0) goto L_0x0028
                android.content.Context r6 = r5.e     // Catch:{ Throwable -> 0x0055 }
                java.lang.String r6 = com.loc.es.b(r6)     // Catch:{ Throwable -> 0x0055 }
                r5.l = r6     // Catch:{ Throwable -> 0x0055 }
            L_0x0028:
                boolean r6 = android.text.TextUtils.isEmpty(r4)     // Catch:{ Throwable -> 0x0055 }
                if (r6 != 0) goto L_0x0039
                java.lang.String r5 = r5.l     // Catch:{ Throwable -> 0x0055 }
                boolean r4 = r4.equals(r5)     // Catch:{ Throwable -> 0x0055 }
                if (r4 != 0) goto L_0x0037
                goto L_0x0039
            L_0x0037:
                r4 = 1
                goto L_0x003a
            L_0x0039:
                r4 = 0
            L_0x003a:
                if (r4 != 0) goto L_0x0064
                int r4 = r8.what     // Catch:{ Throwable -> 0x0055 }
                if (r4 != r0) goto L_0x0054
                r4 = 2102(0x836, float:2.946E-42)
                com.loc.ey.a((java.lang.String) r1, (int) r4)     // Catch:{ Throwable -> 0x0055 }
                java.lang.String r4 = "invalid handlder scode!!!#1002"
                com.autonavi.aps.amapapi.model.AMapLocationServer r4 = com.loc.n.a(10, (java.lang.String) r4)     // Catch:{ Throwable -> 0x0055 }
                com.loc.n r5 = com.loc.n.this     // Catch:{ Throwable -> 0x0055 }
                java.lang.String r6 = r4.l()     // Catch:{ Throwable -> 0x0055 }
                r5.a((android.os.Messenger) r3, (com.amap.api.location.AMapLocation) r4, (java.lang.String) r6, 0)     // Catch:{ Throwable -> 0x0055 }
            L_0x0054:
                return
            L_0x0055:
                r4 = move-exception
                goto L_0x005d
            L_0x0057:
                r4 = move-exception
                r3 = r1
                goto L_0x005d
            L_0x005a:
                r4 = move-exception
                r2 = r1
                r3 = r2
            L_0x005d:
                java.lang.String r5 = "ApsServiceCore"
                java.lang.String r6 = "ActionHandler handlerMessage"
                com.loc.es.a(r4, r5, r6)     // Catch:{ Throwable -> 0x010f }
            L_0x0064:
                int r4 = r8.what     // Catch:{ Throwable -> 0x010f }
                switch(r4) {
                    case 0: goto L_0x0101;
                    case 1: goto L_0x00f6;
                    case 2: goto L_0x00c8;
                    case 3: goto L_0x00b3;
                    case 4: goto L_0x00a8;
                    case 5: goto L_0x009d;
                    case 6: goto L_0x0069;
                    case 7: goto L_0x0091;
                    case 8: goto L_0x0069;
                    case 9: goto L_0x0085;
                    case 10: goto L_0x0079;
                    case 11: goto L_0x0072;
                    case 12: goto L_0x006b;
                    default: goto L_0x0069;
                }     // Catch:{ Throwable -> 0x010f }
            L_0x0069:
                goto L_0x010b
            L_0x006b:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.h.remove(r3)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x0072:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.b()     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x0079:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Messenger) r3, (android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x0085:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.a((com.loc.n) r0, (android.os.Messenger) r3)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x0091:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.c(r0)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x009d:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.b((com.loc.n) r0)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x00a8:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.a((com.loc.n) r0)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x00b3:
                if (r2 == 0) goto L_0x00c7
                boolean r0 = r2.isEmpty()     // Catch:{ Throwable -> 0x010f }
                if (r0 == 0) goto L_0x00bc
                goto L_0x00c7
            L_0x00bc:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r1)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a()     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x00c7:
                return
            L_0x00c8:
                if (r2 == 0) goto L_0x00f5
                boolean r2 = r2.isEmpty()     // Catch:{ Throwable -> 0x010f }
                if (r2 == 0) goto L_0x00d1
                goto L_0x00f5
            L_0x00d1:
                com.loc.n r2 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r2.a((android.os.Bundle) r1)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r1 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                boolean r2 = r1.r     // Catch:{ Throwable -> 0x00eb }
                if (r2 != 0) goto L_0x010b
                com.loc.n$c r2 = new com.loc.n$c     // Catch:{ Throwable -> 0x00eb }
                r2.<init>()     // Catch:{ Throwable -> 0x00eb }
                r1.s = r2     // Catch:{ Throwable -> 0x00eb }
                com.loc.n$c r2 = r1.s     // Catch:{ Throwable -> 0x00eb }
                r2.start()     // Catch:{ Throwable -> 0x00eb }
                r1.r = r0     // Catch:{ Throwable -> 0x00eb }
                goto L_0x010b
            L_0x00eb:
                r0 = move-exception
                java.lang.String r1 = "ApsServiceCore"
                java.lang.String r2 = "startSocket"
                com.loc.es.a(r0, r1, r2)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x00f5:
                return
            L_0x00f6:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.b(r0, r3, r2)     // Catch:{ Throwable -> 0x010f }
                goto L_0x010b
            L_0x0101:
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                r0.a((android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
                com.loc.n r0 = com.loc.n.this     // Catch:{ Throwable -> 0x010f }
                com.loc.n.a((com.loc.n) r0, (android.os.Messenger) r3, (android.os.Bundle) r2)     // Catch:{ Throwable -> 0x010f }
            L_0x010b:
                super.handleMessage(r8)     // Catch:{ Throwable -> 0x010f }
                return
            L_0x010f:
                r8 = move-exception
                java.lang.String r0 = "actionHandler"
                java.lang.String r1 = "handleMessage"
                com.loc.es.a(r8, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.n.a.handleMessage(android.os.Message):void");
        }
    }

    class b extends HandlerThread {
        public b(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        public final void onLooperPrepared() {
            try {
                r unused = n.this.A = new r(n.this.e);
            } catch (Throwable th) {
                es.a(th, "APSManager$ActionThread", "onLooperPrepared");
                return;
            }
            n.this.f = new cs();
            super.onLooperPrepared();
        }

        public final void run() {
            try {
                super.run();
            } catch (Throwable th) {
                es.a(th, "APSManager$ActionThread", "run");
            }
        }
    }

    class c extends Thread {
        c() {
        }

        public final void run() {
            try {
                if (!n.this.p) {
                    n.this.p = true;
                    n.this.o = new ServerSocket(43689);
                }
                while (n.this.p && n.this.o != null) {
                    n.this.q = n.this.o.accept();
                    n.a(n.this, n.this.q);
                }
            } catch (Throwable th) {
                es.a(th, "ApsServiceCore", "run");
            }
            super.run();
        }
    }

    public n(Context context) {
        this.e = context;
    }

    /* access modifiers changed from: private */
    public static AMapLocationServer a(int i2, String str) {
        try {
            AMapLocationServer aMapLocationServer = new AMapLocationServer("");
            aMapLocationServer.setErrorCode(i2);
            aMapLocationServer.setLocationDetail(str);
            return aMapLocationServer;
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "newInstanceAMapLoc");
            return null;
        }
    }

    /* access modifiers changed from: private */
    public void a(Bundle bundle) {
        try {
            if (!this.t) {
                es.a(this.e);
                if (bundle != null) {
                    this.n = es.a(bundle.getBundle("optBundle"));
                }
                this.f.a(this.e);
                this.f.a();
                a(this.n);
                this.f.b();
                this.t = true;
                this.B = true;
                this.C = "";
            }
        } catch (Throwable th) {
            this.B = false;
            this.C = th.getMessage();
            es.a(th, "ApsServiceCore", "init");
        }
    }

    private void a(Messenger messenger) {
        try {
            cs.b(this.e);
            if (er.k()) {
                Bundle bundle = new Bundle();
                bundle.putBoolean("ngpsAble", er.m());
                a(messenger, 7, bundle);
                er.l();
            }
            if (er.t()) {
                Bundle bundle2 = new Bundle();
                bundle2.putBoolean("installMockApp", true);
                a(messenger, 9, bundle2);
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "initAuth");
        }
    }

    private static void a(Messenger messenger, int i2, Bundle bundle) {
        if (messenger != null) {
            try {
                Message obtain = Message.obtain();
                obtain.setData(bundle);
                obtain.what = i2;
                messenger.send(obtain);
            } catch (Throwable th) {
                es.a(th, "ApsServiceCore", "sendMessage");
            }
        }
    }

    /* access modifiers changed from: private */
    public void a(Messenger messenger, AMapLocation aMapLocation, String str, long j2) {
        Bundle bundle = new Bundle();
        bundle.setClassLoader(AMapLocation.class.getClassLoader());
        bundle.putParcelable("loc", aMapLocation);
        bundle.putString(CBConstant.NB, str);
        bundle.putLong("netUseTime", j2);
        this.h.put(messenger, Long.valueOf(fa.c()));
        a(messenger, 1, bundle);
    }

    private void a(AMapLocationClientOption aMapLocationClientOption) {
        try {
            if (this.f != null) {
                this.f.a(aMapLocationClientOption);
            }
            if (aMapLocationClientOption != null) {
                g = aMapLocationClientOption.isKillProcess();
                if (!(this.m == null || (aMapLocationClientOption.isOffset() == this.m.isOffset() && aMapLocationClientOption.isNeedAddress() == this.m.isNeedAddress() && aMapLocationClientOption.isLocationCacheEnable() == this.m.isLocationCacheEnable() && this.m.getGeoLanguage() == aMapLocationClientOption.getGeoLanguage()))) {
                    this.w = 0;
                    this.c = null;
                }
                this.m = aMapLocationClientOption;
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "setExtra");
        }
    }

    static /* synthetic */ void a(n nVar) {
        try {
            if (nVar.z < er.b()) {
                nVar.z++;
                nVar.f.e();
                nVar.d.sendEmptyMessageDelayed(4, 2000);
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "doGpsFusion");
        }
    }

    static /* synthetic */ void a(n nVar, Messenger messenger) {
        try {
            nVar.a(messenger);
            er.e(nVar.e);
            try {
                nVar.f.h();
            } catch (Throwable unused) {
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "doCallOtherSer");
        }
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(8:8|9|10|11|12|(1:16)|17|18) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x001e */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.loc.n r2, android.os.Messenger r3, android.os.Bundle r4) {
        /*
            if (r4 == 0) goto L_0x004e
            boolean r0 = r4.isEmpty()     // Catch:{ Throwable -> 0x0046 }
            if (r0 == 0) goto L_0x0009
            goto L_0x004e
        L_0x0009:
            boolean r0 = r2.u     // Catch:{ Throwable -> 0x0046 }
            if (r0 == 0) goto L_0x000e
            return
        L_0x000e:
            r0 = 1
            r2.u = r0     // Catch:{ Throwable -> 0x0046 }
            r2.a((android.os.Messenger) r3)     // Catch:{ Throwable -> 0x0046 }
            android.content.Context r3 = r2.e     // Catch:{ Throwable -> 0x0046 }
            com.loc.er.e(r3)     // Catch:{ Throwable -> 0x0046 }
            com.loc.cs r3 = r2.f     // Catch:{ Throwable -> 0x001e }
            r3.g()     // Catch:{ Throwable -> 0x001e }
        L_0x001e:
            r2.d()     // Catch:{ Throwable -> 0x0046 }
            long r0 = r2.y     // Catch:{ Throwable -> 0x0046 }
            boolean r3 = com.loc.er.a((long) r0)     // Catch:{ Throwable -> 0x0046 }
            if (r3 == 0) goto L_0x0042
            java.lang.String r3 = "1"
            java.lang.String r0 = "isCacheLoc"
            java.lang.String r4 = r4.getString(r0)     // Catch:{ Throwable -> 0x0046 }
            boolean r3 = r3.equals(r4)     // Catch:{ Throwable -> 0x0046 }
            if (r3 == 0) goto L_0x0042
            long r3 = com.loc.fa.c()     // Catch:{ Throwable -> 0x0046 }
            r2.y = r3     // Catch:{ Throwable -> 0x0046 }
            com.loc.cs r3 = r2.f     // Catch:{ Throwable -> 0x0046 }
            r3.e()     // Catch:{ Throwable -> 0x0046 }
        L_0x0042:
            r2.f()     // Catch:{ Throwable -> 0x0046 }
            return
        L_0x0046:
            r2 = move-exception
            java.lang.String r3 = "ApsServiceCore"
            java.lang.String r4 = "doInitAuth"
            com.loc.es.a(r2, r3, r4)
        L_0x004e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.n.a(com.loc.n, android.os.Messenger, android.os.Bundle):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:107:?, code lost:
        r2.close();
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0109, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:?, code lost:
        com.loc.es.a(r7, "ApsServiceCore", "invokeSocketLocation part3");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0112, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0113, code lost:
        com.loc.es.a(r6, "ApsServiceCore", "invokeSocketLocation part4");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x011a, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0029, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:?, code lost:
        com.loc.es.a(r6, "ApsServiceCore", "invokeSocketLocation part3");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0031, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0032, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:?, code lost:
        r2.close();
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0053, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        com.loc.es.a(r7, "ApsServiceCore", "invokeSocketLocation part3");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x00a7, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:?, code lost:
        r2.close();
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x00c8, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:?, code lost:
        com.loc.es.a(r7, "ApsServiceCore", "invokeSocketLocation part3");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x00ea, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Exception block dominator not found, dom blocks: [B:13:0x0022, B:24:0x0039, B:34:0x004c, B:46:0x0064, B:66:0x00ae, B:76:0x00c1, B:98:0x00f1, B:106:0x0102] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ void a(com.loc.n r6, java.net.Socket r7) {
        /*
            if (r7 != 0) goto L_0x0003
            return
        L_0x0003:
            int r0 = com.loc.es.f     // Catch:{ Throwable -> 0x0112 }
            r1 = 0
            java.io.BufferedReader r2 = new java.io.BufferedReader     // Catch:{ Throwable -> 0x0062, all -> 0x005e }
            java.io.InputStreamReader r3 = new java.io.InputStreamReader     // Catch:{ Throwable -> 0x0062, all -> 0x005e }
            java.io.InputStream r4 = r7.getInputStream()     // Catch:{ Throwable -> 0x0062, all -> 0x005e }
            java.lang.String r5 = "UTF-8"
            r3.<init>(r4, r5)     // Catch:{ Throwable -> 0x0062, all -> 0x005e }
            r2.<init>(r3)     // Catch:{ Throwable -> 0x0062, all -> 0x005e }
            r6.a((java.io.BufferedReader) r2)     // Catch:{ Throwable -> 0x005c }
            java.lang.String r3 = r6.e()     // Catch:{ Throwable -> 0x005c }
            com.loc.es.f = r0     // Catch:{ Throwable -> 0x0112 }
            r6.b((java.lang.String) r3)     // Catch:{ Throwable -> 0x0034 }
            r2.close()     // Catch:{ Throwable -> 0x0029 }
            r7.close()     // Catch:{ Throwable -> 0x0029 }
            return
        L_0x0029:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
            com.loc.es.a(r6, r7, r0)     // Catch:{ Throwable -> 0x0112 }
            return
        L_0x0032:
            r6 = move-exception
            goto L_0x004c
        L_0x0034:
            r6 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part2"
            com.loc.es.a(r6, r0, r1)     // Catch:{ all -> 0x0032 }
            r2.close()     // Catch:{ Throwable -> 0x0043 }
            r7.close()     // Catch:{ Throwable -> 0x0043 }
            return
        L_0x0043:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
            com.loc.es.a(r6, r7, r0)     // Catch:{ Throwable -> 0x0112 }
            return
        L_0x004c:
            r2.close()     // Catch:{ Throwable -> 0x0053 }
            r7.close()     // Catch:{ Throwable -> 0x0053 }
            goto L_0x005b
        L_0x0053:
            r7 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part3"
            com.loc.es.a(r7, r0, r1)     // Catch:{ Throwable -> 0x0112 }
        L_0x005b:
            throw r6     // Catch:{ Throwable -> 0x0112 }
        L_0x005c:
            r3 = move-exception
            goto L_0x0064
        L_0x005e:
            r3 = move-exception
            r2 = r1
            goto L_0x00d5
        L_0x0062:
            r3 = move-exception
            r2 = r1
        L_0x0064:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x00d4 }
            r4.<init>()     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = r6.E     // Catch:{ all -> 0x00d4 }
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = "&&"
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = r6.E     // Catch:{ all -> 0x00d4 }
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = "({'package':'"
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = r6.f6622a     // Catch:{ all -> 0x00d4 }
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r5 = "','error_code':1,'error':'params error'})"
            r4.append(r5)     // Catch:{ all -> 0x00d4 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x00d4 }
            java.lang.String r1 = "ApsServiceCore"
            java.lang.String r5 = "invokeSocketLocation"
            com.loc.es.a(r3, r1, r5)     // Catch:{ all -> 0x00d1 }
            com.loc.es.f = r0     // Catch:{ Throwable -> 0x0112 }
            r6.b((java.lang.String) r4)     // Catch:{ Throwable -> 0x00a9 }
            r2.close()     // Catch:{ Throwable -> 0x009e }
            r7.close()     // Catch:{ Throwable -> 0x009e }
            return
        L_0x009e:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
            com.loc.es.a(r6, r7, r0)     // Catch:{ Throwable -> 0x0112 }
            return
        L_0x00a7:
            r6 = move-exception
            goto L_0x00c1
        L_0x00a9:
            r6 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part2"
            com.loc.es.a(r6, r0, r1)     // Catch:{ all -> 0x00a7 }
            r2.close()     // Catch:{ Throwable -> 0x00b8 }
            r7.close()     // Catch:{ Throwable -> 0x00b8 }
            return
        L_0x00b8:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
            com.loc.es.a(r6, r7, r0)     // Catch:{ Throwable -> 0x0112 }
            return
        L_0x00c1:
            r2.close()     // Catch:{ Throwable -> 0x00c8 }
            r7.close()     // Catch:{ Throwable -> 0x00c8 }
            goto L_0x00d0
        L_0x00c8:
            r7 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part3"
            com.loc.es.a(r7, r0, r1)     // Catch:{ Throwable -> 0x0112 }
        L_0x00d0:
            throw r6     // Catch:{ Throwable -> 0x0112 }
        L_0x00d1:
            r3 = move-exception
            r1 = r4
            goto L_0x00d5
        L_0x00d4:
            r3 = move-exception
        L_0x00d5:
            com.loc.es.f = r0     // Catch:{ Throwable -> 0x0112 }
            r6.b((java.lang.String) r1)     // Catch:{ Throwable -> 0x00ec }
            r2.close()     // Catch:{ Throwable -> 0x00e1 }
            r7.close()     // Catch:{ Throwable -> 0x00e1 }
            goto L_0x0101
        L_0x00e1:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
        L_0x00e6:
            com.loc.es.a(r6, r7, r0)     // Catch:{ Throwable -> 0x0112 }
            goto L_0x0101
        L_0x00ea:
            r6 = move-exception
            goto L_0x0102
        L_0x00ec:
            r6 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part2"
            com.loc.es.a(r6, r0, r1)     // Catch:{ all -> 0x00ea }
            r2.close()     // Catch:{ Throwable -> 0x00fb }
            r7.close()     // Catch:{ Throwable -> 0x00fb }
            goto L_0x0101
        L_0x00fb:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part3"
            goto L_0x00e6
        L_0x0101:
            throw r3     // Catch:{ Throwable -> 0x0112 }
        L_0x0102:
            r2.close()     // Catch:{ Throwable -> 0x0109 }
            r7.close()     // Catch:{ Throwable -> 0x0109 }
            goto L_0x0111
        L_0x0109:
            r7 = move-exception
            java.lang.String r0 = "ApsServiceCore"
            java.lang.String r1 = "invokeSocketLocation part3"
            com.loc.es.a(r7, r0, r1)     // Catch:{ Throwable -> 0x0112 }
        L_0x0111:
            throw r6     // Catch:{ Throwable -> 0x0112 }
        L_0x0112:
            r6 = move-exception
            java.lang.String r7 = "ApsServiceCore"
            java.lang.String r0 = "invokeSocketLocation part4"
            com.loc.es.a(r6, r7, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.n.a(com.loc.n, java.net.Socket):void");
    }

    private void a(BufferedReader bufferedReader) throws Exception {
        String[] split;
        String[] split2;
        String[] split3;
        String readLine = bufferedReader.readLine();
        int i2 = 30000;
        if (readLine != null && readLine.length() > 0 && (split = readLine.split(" ")) != null && split.length > 1 && (split2 = split[1].split("\\?")) != null && split2.length > 1 && (split3 = split2[1].split(com.alipay.sdk.sys.a.b)) != null && split3.length > 0) {
            int i3 = 30000;
            for (String split4 : split3) {
                String[] split5 = split4.split("=");
                if (split5 != null && split5.length > 1) {
                    if ("to".equals(split5[0])) {
                        i3 = fa.g(split5[1]);
                    }
                    if ("callback".equals(split5[0])) {
                        this.E = split5[1];
                    }
                }
            }
            i2 = i3;
        }
        es.f = i2;
    }

    private AMapLocationClientOption b(Bundle bundle) {
        AMapLocationClientOption a2 = es.a(bundle.getBundle("optBundle"));
        a(a2);
        try {
            String string = bundle.getString("d");
            if (!TextUtils.isEmpty(string)) {
                x.a(string);
            }
        } catch (Throwable th) {
            es.a(th, "APSManager", "parseBundle");
        }
        return a2;
    }

    static /* synthetic */ void b(n nVar) {
        cs csVar;
        try {
            if (er.e()) {
                csVar = nVar.f;
            } else {
                if (!fa.e(nVar.e)) {
                    csVar = nVar.f;
                }
                nVar.d.sendEmptyMessageDelayed(5, (long) (er.d() * 1000));
            }
            csVar.e();
            nVar.d.sendEmptyMessageDelayed(5, (long) (er.d() * 1000));
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "doOffFusion");
        }
    }

    static /* synthetic */ void b(n nVar, Messenger messenger, Bundle bundle) {
        String str;
        AMapLocation aMapLocation;
        n nVar2 = nVar;
        Messenger messenger2 = messenger;
        Bundle bundle2 = bundle;
        if (bundle2 != null) {
            try {
                if (!bundle.isEmpty()) {
                    AMapLocationClientOption b2 = nVar.b(bundle2);
                    if (nVar2.h.containsKey(messenger) && !b2.isOnceLocation()) {
                        if (fa.c() - nVar2.h.get(messenger).longValue() < 800) {
                            return;
                        }
                    }
                    AMapLocation aMapLocation2 = null;
                    if (!nVar2.B) {
                        nVar2.x = a(9, "init error : " + nVar2.C + "#0901");
                        nVar.a(messenger, (AMapLocation) nVar2.x, nVar2.x.l(), 0);
                        ey.a((String) null, 2091);
                        return;
                    }
                    long c2 = fa.c();
                    long j2 = 0;
                    if (!fa.a(nVar2.x) || c2 - nVar2.w >= 600) {
                        ex exVar = new ex();
                        exVar.a(fa.c());
                        nVar2.x = nVar2.f.d();
                        if (nVar2.x.getLocationType() == 6 || nVar2.x.getLocationType() == 5) {
                            j2 = nVar2.x.k();
                        }
                        exVar.a(nVar2.x);
                        nVar2.x = nVar2.f.a(nVar2.x);
                        long j3 = j2;
                        exVar.b(fa.c());
                        if (fa.a(nVar2.x)) {
                            nVar2.w = fa.c();
                        }
                        if (nVar2.x == null) {
                            nVar2.x = a(8, "loc is null#0801");
                        }
                        if (nVar2.x != null) {
                            String l2 = nVar2.x.l();
                            aMapLocation2 = nVar2.x.clone();
                            str = l2;
                        } else {
                            str = null;
                        }
                        try {
                            aMapLocation = (!b2.isLocationCacheEnable() || nVar2.A == null) ? aMapLocation2 : nVar2.A.a(aMapLocation2, str, b2.getLastLocationLifeCycle());
                        } catch (Throwable th) {
                            es.a(th, "ApsServiceCore", "fixLastLocation");
                            aMapLocation = aMapLocation2;
                        }
                        nVar.a(messenger, aMapLocation, str, j3);
                        ey.a(nVar2.e, exVar);
                    } else {
                        nVar.a(messenger, (AMapLocation) nVar2.x, nVar2.x.l(), 0);
                    }
                    nVar.a(messenger);
                    if (er.u()) {
                        nVar.d();
                    }
                    if (er.a(nVar2.y) && nVar2.x != null && (nVar2.x.getLocationType() == 2 || nVar2.x.getLocationType() == 4 || nVar2.x.getLocationType() == 9)) {
                        nVar2.y = fa.c();
                        nVar2.f.e();
                    }
                    nVar.f();
                }
            } catch (Throwable th2) {
                es.a(th2, "ApsServiceCore", "doLocation");
            }
        }
    }

    private void b(String str) throws UnsupportedEncodingException, IOException {
        PrintStream printStream = new PrintStream(this.q.getOutputStream(), true, "UTF-8");
        printStream.println("HTTP/1.0 200 OK");
        printStream.println("Content-Length:" + str.getBytes("UTF-8").length);
        printStream.println();
        printStream.println(str);
        printStream.close();
    }

    public static void c() {
        g = false;
    }

    static /* synthetic */ void c(n nVar) {
        try {
            if (er.a(nVar.e, nVar.v)) {
                nVar.v = fa.b();
                nVar.f.e();
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "doNGps");
        }
    }

    private void d() {
        try {
            this.d.removeMessages(4);
            if (er.a()) {
                this.d.sendEmptyMessage(4);
            }
            this.d.removeMessages(5);
            if (er.c() && er.d() > 2) {
                this.d.sendEmptyMessage(5);
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "checkConfig");
        }
    }

    private String e() {
        StringBuilder sb;
        String str;
        long currentTimeMillis = System.currentTimeMillis();
        if (fa.e(this.e)) {
            sb = new StringBuilder();
            sb.append(this.E);
            sb.append(Operators.AND);
            sb.append(this.E);
            sb.append("({'package':'");
            sb.append(this.f6622a);
            str = "','error_code':36,'error':'app is background'})";
        } else {
            if (this.x == null || currentTimeMillis - this.x.getTime() > 5000) {
                try {
                    this.x = this.f.d();
                } catch (Throwable th) {
                    es.a(th, "ApsServiceCore", "getSocketLocResult");
                }
            }
            if (this.x == null) {
                sb = new StringBuilder();
                sb.append(this.E);
                sb.append(Operators.AND);
                sb.append(this.E);
                sb.append("({'package':'");
                sb.append(this.f6622a);
                str = "','error_code':8,'error':'unknown error'})";
            } else if (this.x.getErrorCode() != 0) {
                sb = new StringBuilder();
                sb.append(this.E);
                sb.append(Operators.AND);
                sb.append(this.E);
                sb.append("({'package':'");
                sb.append(this.f6622a);
                sb.append("','error_code':");
                sb.append(this.x.getErrorCode());
                sb.append(",'error':'");
                sb.append(this.x.getErrorInfo());
                str = "'})";
            } else {
                sb = new StringBuilder();
                sb.append(this.E);
                sb.append(Operators.AND);
                sb.append(this.E);
                sb.append("({'package':'");
                sb.append(this.f6622a);
                sb.append("','error_code':0,'error':'','location':{'y':");
                sb.append(this.x.getLatitude());
                sb.append(",'precision':");
                sb.append(this.x.getAccuracy());
                sb.append(",'x':");
                sb.append(this.x.getLongitude());
                str = "},'version_code':'4.7.1','version':'4.7.1'})";
            }
        }
        sb.append(str);
        return sb.toString();
    }

    private void f() {
        try {
            if (this.f != null) {
                this.f.k();
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "startColl");
        }
    }

    public final void a() {
        try {
            if (this.q != null) {
                this.q.close();
            }
        } catch (Throwable th) {
            es.a(th, "ApsServiceCore", "doStopScocket 1");
        }
        try {
            if (this.o != null) {
                this.o.close();
            }
        } catch (Throwable th2) {
            es.a(th2, "ApsServiceCore", "doStopScocket 2");
        }
        try {
            if (this.s != null) {
                this.s.interrupt();
            }
        } catch (Throwable unused) {
        }
        this.s = null;
        this.o = null;
        this.p = false;
        this.r = false;
    }

    /* access modifiers changed from: package-private */
    public final void a(Messenger messenger, Bundle bundle) {
        float f2;
        if (bundle != null) {
            try {
                if (!bundle.isEmpty()) {
                    if (er.q()) {
                        double d2 = bundle.getDouble(Tags.Nearby.LAT);
                        double d3 = bundle.getDouble(Tags.Nearby.LON);
                        b(bundle);
                        if (this.c != null) {
                            f2 = fa.a(new double[]{d2, d3, this.c.getLatitude(), this.c.getLongitude()});
                            if (f2 < ((float) (er.r() * 3))) {
                                Bundle bundle2 = new Bundle();
                                bundle2.setClassLoader(AMapLocation.class.getClassLoader());
                                bundle2.putInt("I_MAX_GEO_DIS", er.r() * 3);
                                bundle2.putInt("I_MIN_GEO_DIS", er.r());
                                bundle2.putParcelable("loc", this.c);
                                a(messenger, 6, bundle2);
                            }
                        } else {
                            f2 = -1.0f;
                        }
                        if (f2 == -1.0f || f2 > ((float) er.r())) {
                            a(bundle);
                            this.c = this.f.a(d2, d3);
                        }
                    }
                }
            } catch (Throwable th) {
                es.a(th, "ApsServiceCore", "doLocationGeo");
            }
        }
    }

    /* JADX WARNING: Exception block dominator not found, dom blocks: [] */
    /* JADX WARNING: Missing exception handler attribute for start block: B:14:0x0031 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void b() {
        /*
            r12 = this;
            java.util.HashMap<android.os.Messenger, java.lang.Long> r0 = r12.h     // Catch:{ Throwable -> 0x00a2 }
            r0.clear()     // Catch:{ Throwable -> 0x00a2 }
            r0 = 0
            r12.h = r0     // Catch:{ Throwable -> 0x00a2 }
            com.loc.cs r1 = r12.f     // Catch:{ Throwable -> 0x00a2 }
            if (r1 == 0) goto L_0x0011
            android.content.Context r1 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            com.loc.cs.b((android.content.Context) r1)     // Catch:{ Throwable -> 0x00a2 }
        L_0x0011:
            com.loc.n$a r1 = r12.d     // Catch:{ Throwable -> 0x00a2 }
            if (r1 == 0) goto L_0x001a
            com.loc.n$a r1 = r12.d     // Catch:{ Throwable -> 0x00a2 }
            r1.removeCallbacksAndMessages(r0)     // Catch:{ Throwable -> 0x00a2 }
        L_0x001a:
            com.loc.n$b r1 = r12.b     // Catch:{ Throwable -> 0x00a2 }
            r2 = 0
            if (r1 == 0) goto L_0x003a
            int r1 = android.os.Build.VERSION.SDK_INT     // Catch:{ Throwable -> 0x00a2 }
            r3 = 18
            if (r1 < r3) goto L_0x0037
            com.loc.n$b r1 = r12.b     // Catch:{ Throwable -> 0x0031 }
            java.lang.Class<android.os.HandlerThread> r3 = android.os.HandlerThread.class
            java.lang.String r4 = "quitSafely"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x0031 }
            com.loc.ew.a((java.lang.Object) r1, (java.lang.Class<?>) r3, (java.lang.String) r4, (java.lang.Object[]) r5)     // Catch:{ Throwable -> 0x0031 }
            goto L_0x003a
        L_0x0031:
            com.loc.n$b r1 = r12.b     // Catch:{ Throwable -> 0x00a2 }
        L_0x0033:
            r1.quit()     // Catch:{ Throwable -> 0x00a2 }
            goto L_0x003a
        L_0x0037:
            com.loc.n$b r1 = r12.b     // Catch:{ Throwable -> 0x00a2 }
            goto L_0x0033
        L_0x003a:
            r12.b = r0     // Catch:{ Throwable -> 0x00a2 }
            r12.d = r0     // Catch:{ Throwable -> 0x00a2 }
            com.loc.r r1 = r12.A     // Catch:{ Throwable -> 0x00a2 }
            if (r1 == 0) goto L_0x0049
            com.loc.r r1 = r12.A     // Catch:{ Throwable -> 0x00a2 }
            r1.c()     // Catch:{ Throwable -> 0x00a2 }
            r12.A = r0     // Catch:{ Throwable -> 0x00a2 }
        L_0x0049:
            r12.a()     // Catch:{ Throwable -> 0x00a2 }
            r12.t = r2     // Catch:{ Throwable -> 0x00a2 }
            r12.u = r2     // Catch:{ Throwable -> 0x00a2 }
            com.loc.cs r0 = r12.f     // Catch:{ Throwable -> 0x00a2 }
            r0.f()     // Catch:{ Throwable -> 0x00a2 }
            android.content.Context r0 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            com.loc.ey.a((android.content.Context) r0)     // Catch:{ Throwable -> 0x00a2 }
            com.loc.ey r0 = r12.i     // Catch:{ Throwable -> 0x00a2 }
            if (r0 == 0) goto L_0x0093
            long r0 = r12.j     // Catch:{ Throwable -> 0x00a2 }
            r2 = 0
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0093
            long r0 = r12.k     // Catch:{ Throwable -> 0x00a2 }
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r4 == 0) goto L_0x0093
            long r0 = com.loc.fa.c()     // Catch:{ Throwable -> 0x00a2 }
            long r2 = r12.j     // Catch:{ Throwable -> 0x00a2 }
            r4 = 0
            long r10 = r0 - r2
            com.loc.ey r0 = r12.i     // Catch:{ Throwable -> 0x00a2 }
            android.content.Context r1 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            int r6 = r0.c(r1)     // Catch:{ Throwable -> 0x00a2 }
            com.loc.ey r0 = r12.i     // Catch:{ Throwable -> 0x00a2 }
            android.content.Context r1 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            int r7 = r0.d(r1)     // Catch:{ Throwable -> 0x00a2 }
            android.content.Context r5 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            long r8 = r12.k     // Catch:{ Throwable -> 0x00a2 }
            com.loc.ey.a(r5, r6, r7, r8, r10)     // Catch:{ Throwable -> 0x00a2 }
            com.loc.ey r0 = r12.i     // Catch:{ Throwable -> 0x00a2 }
            android.content.Context r1 = r12.e     // Catch:{ Throwable -> 0x00a2 }
            r0.e(r1)     // Catch:{ Throwable -> 0x00a2 }
        L_0x0093:
            com.loc.aq.b()     // Catch:{ Throwable -> 0x00a2 }
            boolean r0 = g     // Catch:{ Throwable -> 0x00a2 }
            if (r0 == 0) goto L_0x00a1
            int r0 = android.os.Process.myPid()     // Catch:{ Throwable -> 0x00a2 }
            android.os.Process.killProcess(r0)     // Catch:{ Throwable -> 0x00a2 }
        L_0x00a1:
            return
        L_0x00a2:
            r0 = move-exception
            java.lang.String r1 = "ApsServiceCore"
            java.lang.String r2 = "threadDestroy"
            com.loc.es.a(r0, r1, r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.n.b():void");
    }
}
