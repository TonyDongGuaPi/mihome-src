package com.loc;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.HandlerThread;
import android.telephony.CellIdentityCdma;
import android.telephony.CellIdentityGsm;
import android.telephony.CellIdentityLte;
import android.telephony.CellIdentityWcdma;
import android.telephony.CellInfoCdma;
import android.telephony.CellInfoGsm;
import android.telephony.CellInfoLte;
import android.telephony.CellInfoWcdma;
import android.telephony.CellLocation;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import com.mi.mistatistic.sdk.controller.Utils;
import com.taobao.weex.common.Constants;
import com.xiaomi.smarthome.auth.AuthCode;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public final class ee {

    /* renamed from: a  reason: collision with root package name */
    int f6579a = 0;
    ArrayList<ed> b = new ArrayList<>();
    TelephonyManager c = null;
    long d = 0;
    CellLocation e;
    boolean f = false;
    PhoneStateListener g = null;
    String h = null;
    boolean i = false;
    StringBuilder j = null;
    HandlerThread k = null;
    private Context l;
    private String m = null;
    private ArrayList<ed> n = new ArrayList<>();
    private int o = AuthCode.n;
    private ec p = null;
    private Object q;
    private int r = 0;
    /* access modifiers changed from: private */
    public long s = 0;
    /* access modifiers changed from: private */
    public boolean t = false;
    /* access modifiers changed from: private */
    public Object u = new Object();

    class a extends HandlerThread {
        public a(String str) {
            super(str);
        }

        /* access modifiers changed from: protected */
        /* JADX WARNING: Can't wrap try/catch for region: R(5:3|4|(6:6|(1:8)(1:11)|9|10|14|(2:16|17)(1:18))|19|20) */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x0044 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void onLooperPrepared() {
            /*
                r6 = this;
                super.onLooperPrepared()     // Catch:{ Throwable -> 0x0049 }
                com.loc.ee r0 = com.loc.ee.this     // Catch:{ Throwable -> 0x0049 }
                java.lang.Object r0 = r0.u     // Catch:{ Throwable -> 0x0049 }
                monitor-enter(r0)     // Catch:{ Throwable -> 0x0049 }
                com.loc.ee r1 = com.loc.ee.this     // Catch:{ all -> 0x0046 }
                boolean r1 = r1.t     // Catch:{ all -> 0x0046 }
                if (r1 != 0) goto L_0x0044
                com.loc.ee r1 = com.loc.ee.this     // Catch:{ all -> 0x0046 }
                com.loc.ee$1 r2 = new com.loc.ee$1     // Catch:{ all -> 0x0046 }
                r2.<init>()     // Catch:{ all -> 0x0046 }
                r1.g = r2     // Catch:{ all -> 0x0046 }
                java.lang.String r2 = "android.telephony.PhoneStateListener"
                r3 = 0
                int r4 = com.loc.fa.d()     // Catch:{ all -> 0x0046 }
                r5 = 7
                if (r4 >= r5) goto L_0x002c
                java.lang.String r4 = "LISTEN_SIGNAL_STRENGTH"
            L_0x0027:
                int r2 = com.loc.ew.b(r2, r4)     // Catch:{ Throwable -> 0x002f }
                goto L_0x0030
            L_0x002c:
                java.lang.String r4 = "LISTEN_SIGNAL_STRENGTHS"
                goto L_0x0027
            L_0x002f:
                r2 = 0
            L_0x0030:
                r3 = 16
                if (r2 != 0) goto L_0x003c
                android.telephony.TelephonyManager r2 = r1.c     // Catch:{ Throwable -> 0x0044 }
                android.telephony.PhoneStateListener r1 = r1.g     // Catch:{ Throwable -> 0x0044 }
                r2.listen(r1, r3)     // Catch:{ Throwable -> 0x0044 }
                goto L_0x0044
            L_0x003c:
                android.telephony.TelephonyManager r4 = r1.c     // Catch:{ Throwable -> 0x0044 }
                android.telephony.PhoneStateListener r1 = r1.g     // Catch:{ Throwable -> 0x0044 }
                r2 = r2 | r3
                r4.listen(r1, r2)     // Catch:{ Throwable -> 0x0044 }
            L_0x0044:
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                return
            L_0x0046:
                r1 = move-exception
                monitor-exit(r0)     // Catch:{ all -> 0x0046 }
                throw r1     // Catch:{ Throwable -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a.onLooperPrepared():void");
        }

        /* JADX WARNING: Code restructure failed: missing block: B:4:?, code lost:
            r3.f6581a.c.listen(r3.f6581a.g, 0);
            r3.f6581a.g = null;
            quit();
         */
        /* JADX WARNING: Code restructure failed: missing block: B:6:?, code lost:
            return;
         */
        /* JADX WARNING: Code restructure failed: missing block: B:7:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0004 */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void run() {
            /*
                r3 = this;
                super.run()     // Catch:{ Throwable -> 0x0004 }
                return
            L_0x0004:
                com.loc.ee r0 = com.loc.ee.this     // Catch:{ Throwable -> 0x0018 }
                android.telephony.TelephonyManager r0 = r0.c     // Catch:{ Throwable -> 0x0018 }
                com.loc.ee r1 = com.loc.ee.this     // Catch:{ Throwable -> 0x0018 }
                android.telephony.PhoneStateListener r1 = r1.g     // Catch:{ Throwable -> 0x0018 }
                r2 = 0
                r0.listen(r1, r2)     // Catch:{ Throwable -> 0x0018 }
                com.loc.ee r0 = com.loc.ee.this     // Catch:{ Throwable -> 0x0018 }
                r1 = 0
                r0.g = r1     // Catch:{ Throwable -> 0x0018 }
                r3.quit()     // Catch:{ Throwable -> 0x0018 }
            L_0x0018:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a.run():void");
        }
    }

    public ee(Context context) {
        Context context2;
        String str;
        this.l = context;
        if (this.c == null) {
            this.c = (TelephonyManager) fa.a(this.l, "phone");
        }
        if (this.c != null) {
            try {
                this.f6579a = c(this.c.getCellLocation());
            } catch (SecurityException e2) {
                this.h = e2.getMessage();
            } catch (Throwable th) {
                this.h = null;
                es.a(th, "CgiManager", "CgiManager");
                this.f6579a = 0;
            }
            try {
                this.r = r();
                switch (this.r) {
                    case 1:
                        context2 = this.l;
                        str = "phone_msim";
                        break;
                    case 2:
                        context2 = this.l;
                        str = "phone2";
                        break;
                    default:
                        context2 = this.l;
                        str = "phone2";
                        break;
                }
                this.q = fa.a(context2, str);
            } catch (Throwable unused) {
            }
            if (this.k == null) {
                this.k = new a("listenerPhoneStateThread");
                this.k.start();
            }
        }
        this.p = new ec();
    }

    private CellLocation a(Object obj, String str, Object... objArr) {
        if (obj == null) {
            return null;
        }
        try {
            Object a2 = ew.a(obj, str, objArr);
            CellLocation cellLocation = a2 != null ? (CellLocation) a2 : null;
            if (b(cellLocation)) {
                return cellLocation;
            }
            return null;
        } catch (Throwable unused) {
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0079, code lost:
        r2 = null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00aa A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x00ab A[RETURN] */
    @android.annotation.SuppressLint({"NewApi"})
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.telephony.CellLocation a(java.util.List<android.telephony.CellInfo> r11) {
        /*
            r10 = this;
            r0 = 0
            if (r11 == 0) goto L_0x00ac
            boolean r1 = r11.isEmpty()
            if (r1 == 0) goto L_0x000b
            goto L_0x00ac
        L_0x000b:
            r1 = 0
        L_0x000c:
            int r2 = r11.size()
            if (r1 >= r2) goto L_0x0079
            java.lang.Object r2 = r11.get(r1)
            android.telephony.CellInfo r2 = (android.telephony.CellInfo) r2
            if (r2 == 0) goto L_0x0076
            boolean r3 = r2.isRegistered()     // Catch:{ Throwable -> 0x0076 }
            boolean r4 = r2 instanceof android.telephony.CellInfoCdma     // Catch:{ Throwable -> 0x0076 }
            if (r4 == 0) goto L_0x0034
            android.telephony.CellInfoCdma r2 = (android.telephony.CellInfoCdma) r2     // Catch:{ Throwable -> 0x0076 }
            android.telephony.CellIdentityCdma r4 = r2.getCellIdentity()     // Catch:{ Throwable -> 0x0076 }
            boolean r4 = a((android.telephony.CellIdentityCdma) r4)     // Catch:{ Throwable -> 0x0076 }
            if (r4 != 0) goto L_0x002f
            goto L_0x0076
        L_0x002f:
            com.loc.ed r2 = r10.a((android.telephony.CellInfoCdma) r2, (boolean) r3)     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007a
        L_0x0034:
            boolean r4 = r2 instanceof android.telephony.CellInfoGsm     // Catch:{ Throwable -> 0x0076 }
            if (r4 == 0) goto L_0x004a
            android.telephony.CellInfoGsm r2 = (android.telephony.CellInfoGsm) r2     // Catch:{ Throwable -> 0x0076 }
            android.telephony.CellIdentityGsm r4 = r2.getCellIdentity()     // Catch:{ Throwable -> 0x0076 }
            boolean r4 = a((android.telephony.CellIdentityGsm) r4)     // Catch:{ Throwable -> 0x0076 }
            if (r4 != 0) goto L_0x0045
            goto L_0x0076
        L_0x0045:
            com.loc.ed r2 = a((android.telephony.CellInfoGsm) r2, (boolean) r3)     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007a
        L_0x004a:
            boolean r4 = r2 instanceof android.telephony.CellInfoWcdma     // Catch:{ Throwable -> 0x0076 }
            if (r4 == 0) goto L_0x0060
            android.telephony.CellInfoWcdma r2 = (android.telephony.CellInfoWcdma) r2     // Catch:{ Throwable -> 0x0076 }
            android.telephony.CellIdentityWcdma r4 = r2.getCellIdentity()     // Catch:{ Throwable -> 0x0076 }
            boolean r4 = a((android.telephony.CellIdentityWcdma) r4)     // Catch:{ Throwable -> 0x0076 }
            if (r4 != 0) goto L_0x005b
            goto L_0x0076
        L_0x005b:
            com.loc.ed r2 = a((android.telephony.CellInfoWcdma) r2, (boolean) r3)     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007a
        L_0x0060:
            boolean r4 = r2 instanceof android.telephony.CellInfoLte     // Catch:{ Throwable -> 0x0076 }
            if (r4 == 0) goto L_0x0079
            android.telephony.CellInfoLte r2 = (android.telephony.CellInfoLte) r2     // Catch:{ Throwable -> 0x0076 }
            android.telephony.CellIdentityLte r4 = r2.getCellIdentity()     // Catch:{ Throwable -> 0x0076 }
            boolean r4 = a((android.telephony.CellIdentityLte) r4)     // Catch:{ Throwable -> 0x0076 }
            if (r4 != 0) goto L_0x0071
            goto L_0x0076
        L_0x0071:
            com.loc.ed r2 = a((android.telephony.CellInfoLte) r2, (boolean) r3)     // Catch:{ Throwable -> 0x0076 }
            goto L_0x007a
        L_0x0076:
            int r1 = r1 + 1
            goto L_0x000c
        L_0x0079:
            r2 = r0
        L_0x007a:
            if (r2 == 0) goto L_0x00a4
            int r11 = r2.k     // Catch:{ Throwable -> 0x00a2 }
            r1 = 2
            if (r11 != r1) goto L_0x0095
            android.telephony.cdma.CdmaCellLocation r11 = new android.telephony.cdma.CdmaCellLocation     // Catch:{ Throwable -> 0x00a2 }
            r11.<init>()     // Catch:{ Throwable -> 0x00a2 }
            int r4 = r2.i     // Catch:{ Throwable -> 0x00a8 }
            int r5 = r2.e     // Catch:{ Throwable -> 0x00a8 }
            int r6 = r2.f     // Catch:{ Throwable -> 0x00a8 }
            int r7 = r2.g     // Catch:{ Throwable -> 0x00a8 }
            int r8 = r2.h     // Catch:{ Throwable -> 0x00a8 }
            r3 = r11
            r3.setCellLocationData(r4, r5, r6, r7, r8)     // Catch:{ Throwable -> 0x00a8 }
            goto L_0x00a8
        L_0x0095:
            android.telephony.gsm.GsmCellLocation r11 = new android.telephony.gsm.GsmCellLocation     // Catch:{ Throwable -> 0x00a2 }
            r11.<init>()     // Catch:{ Throwable -> 0x00a2 }
            int r1 = r2.c     // Catch:{ Throwable -> 0x00a5 }
            int r2 = r2.d     // Catch:{ Throwable -> 0x00a5 }
            r11.setLacAndCid(r1, r2)     // Catch:{ Throwable -> 0x00a5 }
            goto L_0x00a5
        L_0x00a2:
            r11 = r0
            goto L_0x00a8
        L_0x00a4:
            r11 = r0
        L_0x00a5:
            r9 = r0
            r0 = r11
            r11 = r9
        L_0x00a8:
            if (r11 != 0) goto L_0x00ab
            return r0
        L_0x00ab:
            return r11
        L_0x00ac:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a(java.util.List):android.telephony.CellLocation");
    }

    private static ed a(int i2, boolean z, int i3, int i4, int i5, int i6, int i7) {
        ed edVar = new ed(i2, z);
        edVar.f6578a = i3;
        edVar.b = i4;
        edVar.c = i5;
        edVar.d = i6;
        edVar.j = i7;
        return edVar;
    }

    @SuppressLint({"NewApi"})
    private ed a(CellInfoCdma cellInfoCdma, boolean z) {
        int i2;
        int i3;
        int i4;
        CellIdentityCdma cellIdentity = cellInfoCdma.getCellIdentity();
        String[] a2 = fa.a(this.c);
        try {
            i4 = Integer.parseInt(a2[0]);
            try {
                i2 = Integer.parseInt(a2[1]);
                i3 = i4;
            } catch (Throwable unused) {
                i3 = i4;
                i2 = 0;
                ed a3 = a(2, z, i3, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
                a3.g = cellIdentity.getSystemId();
                a3.h = cellIdentity.getNetworkId();
                a3.i = cellIdentity.getBasestationId();
                a3.e = cellIdentity.getLatitude();
                a3.f = cellIdentity.getLongitude();
                return a3;
            }
        } catch (Throwable unused2) {
            i4 = 0;
            i3 = i4;
            i2 = 0;
            ed a32 = a(2, z, i3, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
            a32.g = cellIdentity.getSystemId();
            a32.h = cellIdentity.getNetworkId();
            a32.i = cellIdentity.getBasestationId();
            a32.e = cellIdentity.getLatitude();
            a32.f = cellIdentity.getLongitude();
            return a32;
        }
        ed a322 = a(2, z, i3, i2, 0, 0, cellInfoCdma.getCellSignalStrength().getCdmaDbm());
        a322.g = cellIdentity.getSystemId();
        a322.h = cellIdentity.getNetworkId();
        a322.i = cellIdentity.getBasestationId();
        a322.e = cellIdentity.getLatitude();
        a322.f = cellIdentity.getLongitude();
        return a322;
    }

    @SuppressLint({"NewApi"})
    private static ed a(CellInfoGsm cellInfoGsm, boolean z) {
        CellIdentityGsm cellIdentity = cellInfoGsm.getCellIdentity();
        return a(1, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoGsm.getCellSignalStrength().getDbm());
    }

    @SuppressLint({"NewApi"})
    private static ed a(CellInfoLte cellInfoLte, boolean z) {
        CellIdentityLte cellIdentity = cellInfoLte.getCellIdentity();
        ed a2 = a(3, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getTac(), cellIdentity.getCi(), cellInfoLte.getCellSignalStrength().getDbm());
        a2.o = cellIdentity.getPci();
        return a2;
    }

    @SuppressLint({"NewApi"})
    private static ed a(CellInfoWcdma cellInfoWcdma, boolean z) {
        CellIdentityWcdma cellIdentity = cellInfoWcdma.getCellIdentity();
        ed a2 = a(4, z, cellIdentity.getMcc(), cellIdentity.getMnc(), cellIdentity.getLac(), cellIdentity.getCid(), cellInfoWcdma.getCellSignalStrength().getDbm());
        a2.o = cellIdentity.getPsc();
        return a2;
    }

    private static ed a(NeighboringCellInfo neighboringCellInfo, String[] strArr) {
        try {
            ed edVar = new ed(1, false);
            edVar.f6578a = Integer.parseInt(strArr[0]);
            edVar.b = Integer.parseInt(strArr[1]);
            edVar.c = ew.b(neighboringCellInfo, "getLac", new Object[0]);
            edVar.d = neighboringCellInfo.getCid();
            edVar.j = fa.a(neighboringCellInfo.getRssi());
            return edVar;
        } catch (Throwable th) {
            es.a(th, "CgiManager", "getGsm");
            return null;
        }
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v3, resolved type: java.util.List} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(android.telephony.CellLocation r7, java.lang.String[] r8, boolean r9) {
        /*
            r6 = this;
            if (r7 == 0) goto L_0x0098
            android.telephony.TelephonyManager r0 = r6.c
            if (r0 != 0) goto L_0x0008
            goto L_0x0098
        L_0x0008:
            java.util.ArrayList<com.loc.ed> r0 = r6.b
            r0.clear()
            boolean r0 = r6.b((android.telephony.CellLocation) r7)
            if (r0 != 0) goto L_0x0014
            return
        L_0x0014:
            r0 = 1
            r6.f6579a = r0
            r1 = 0
            java.util.ArrayList<com.loc.ed> r2 = r6.b
            android.telephony.gsm.GsmCellLocation r7 = (android.telephony.gsm.GsmCellLocation) r7
            com.loc.ed r3 = new com.loc.ed
            r3.<init>(r0, r0)
            r4 = 0
            r5 = r8[r4]
            int r5 = com.loc.fa.g((java.lang.String) r5)
            r3.f6578a = r5
            r0 = r8[r0]
            int r0 = com.loc.fa.g((java.lang.String) r0)
            r3.b = r0
            int r0 = r7.getLac()
            r3.c = r0
            int r7 = r7.getCid()
            r3.d = r7
            int r7 = r6.o
            r3.j = r7
            r2.add(r3)
            if (r9 == 0) goto L_0x0048
            return
        L_0x0048:
            int r7 = android.os.Build.VERSION.SDK_INT
            r9 = 28
            if (r7 > r9) goto L_0x005b
            android.telephony.TelephonyManager r7 = r6.c
            java.lang.String r9 = "getNeighboringCellInfo"
            java.lang.Object[] r0 = new java.lang.Object[r4]
            java.lang.Object r7 = com.loc.ew.a(r7, r9, r0)
            r1 = r7
            java.util.List r1 = (java.util.List) r1
        L_0x005b:
            if (r1 == 0) goto L_0x0098
            boolean r7 = r1.isEmpty()
            if (r7 == 0) goto L_0x0064
            goto L_0x0098
        L_0x0064:
            java.util.Iterator r7 = r1.iterator()
        L_0x0068:
            boolean r9 = r7.hasNext()
            if (r9 == 0) goto L_0x0098
            java.lang.Object r9 = r7.next()
            android.telephony.NeighboringCellInfo r9 = (android.telephony.NeighboringCellInfo) r9
            if (r9 == 0) goto L_0x0068
            int r0 = r9.getLac()
            int r1 = r9.getCid()
            boolean r0 = a((int) r0, (int) r1)
            if (r0 == 0) goto L_0x0068
            com.loc.ed r9 = a((android.telephony.NeighboringCellInfo) r9, (java.lang.String[]) r8)
            if (r9 == 0) goto L_0x0068
            java.util.ArrayList<com.loc.ed> r0 = r6.b
            boolean r0 = r0.contains(r9)
            if (r0 != 0) goto L_0x0068
            java.util.ArrayList<com.loc.ed> r0 = r6.b
            r0.add(r9)
            goto L_0x0068
        L_0x0098:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a(android.telephony.CellLocation, java.lang.String[], boolean):void");
    }

    static /* synthetic */ void a(ee eeVar, int i2) {
        if (i2 == -113) {
            eeVar.o = AuthCode.n;
            return;
        }
        eeVar.o = i2;
        switch (eeVar.f6579a) {
            case 1:
            case 2:
                if (eeVar.b != null && !eeVar.b.isEmpty()) {
                    try {
                        eeVar.b.get(0).j = eeVar.o;
                        return;
                    } catch (Throwable unused) {
                        return;
                    }
                } else {
                    return;
                }
            default:
                return;
        }
    }

    private static boolean a(int i2) {
        return (i2 == -1 || i2 == 0 || i2 > 65535) ? false : true;
    }

    private static boolean a(int i2, int i3) {
        return (i2 == -1 || i2 == 0 || i2 > 65535 || i3 == -1 || i3 == 0 || i3 == 65535 || i3 >= 268435455) ? false : true;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityCdma cellIdentityCdma) {
        return cellIdentityCdma != null && cellIdentityCdma.getSystemId() > 0 && cellIdentityCdma.getNetworkId() >= 0 && cellIdentityCdma.getBasestationId() >= 0;
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityGsm cellIdentityGsm) {
        return cellIdentityGsm != null && a(cellIdentityGsm.getLac()) && b(cellIdentityGsm.getCid());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityLte cellIdentityLte) {
        return cellIdentityLte != null && a(cellIdentityLte.getTac()) && b(cellIdentityLte.getCi());
    }

    @SuppressLint({"NewApi"})
    private static boolean a(CellIdentityWcdma cellIdentityWcdma) {
        return cellIdentityWcdma != null && a(cellIdentityWcdma.getLac()) && b(cellIdentityWcdma.getCid());
    }

    private static boolean b(int i2) {
        return (i2 == -1 || i2 == 0 || i2 == 65535 || i2 >= 268435455) ? false : true;
    }

    private boolean b(CellLocation cellLocation) {
        boolean a2 = a(cellLocation);
        if (!a2) {
            this.f6579a = 0;
        }
        return a2;
    }

    private int c(CellLocation cellLocation) {
        if (this.i || cellLocation == null) {
            return 0;
        }
        if (cellLocation instanceof GsmCellLocation) {
            return 1;
        }
        try {
            Class.forName("android.telephony.cdma.CdmaCellLocation");
            return 2;
        } catch (Throwable th) {
            es.a(th, Utils.b, "getCellLocT");
            return 0;
        }
    }

    private CellLocation n() {
        if (this.c != null) {
            try {
                CellLocation cellLocation = this.c.getCellLocation();
                this.h = null;
                if (b(cellLocation)) {
                    this.e = cellLocation;
                    return cellLocation;
                }
            } catch (SecurityException e2) {
                this.h = e2.getMessage();
            } catch (Throwable th) {
                this.h = null;
                es.a(th, "CgiManager", "getCellLocation");
            }
        }
        return null;
    }

    @SuppressLint({"NewApi"})
    private CellLocation o() {
        TelephonyManager telephonyManager = this.c;
        CellLocation cellLocation = null;
        if (telephonyManager == null) {
            return null;
        }
        CellLocation n2 = n();
        if (b(n2)) {
            return n2;
        }
        if (fa.d() >= 18) {
            try {
                cellLocation = a(telephonyManager.getAllCellInfo());
            } catch (SecurityException e2) {
                this.h = e2.getMessage();
            }
        }
        if (cellLocation != null) {
            return cellLocation;
        }
        CellLocation a2 = a((Object) telephonyManager, "getCellLocationExt", 1);
        if (a2 != null) {
            return a2;
        }
        CellLocation a3 = a((Object) telephonyManager, "getCellLocationGemini", 1);
        if (a3 != null) {
        }
        return a3;
    }

    private CellLocation p() {
        Object obj = this.q;
        CellLocation cellLocation = null;
        if (obj == null) {
            return null;
        }
        try {
            Class<?> q2 = q();
            if (q2.isInstance(obj)) {
                Object cast = q2.cast(obj);
                CellLocation a2 = a((Object) cast, "getCellLocation", new Object[0]);
                if (a2 != null) {
                    return a2;
                }
                try {
                    CellLocation a3 = a((Object) cast, "getCellLocation", 1);
                    if (a3 != null) {
                        return a3;
                    }
                    try {
                        a2 = a((Object) cast, "getCellLocationGemini", 1);
                        if (a2 != null) {
                            return a2;
                        }
                        cellLocation = a((Object) cast, "getAllCellInfo", 1);
                        if (cellLocation != null) {
                            return cellLocation;
                        }
                    } catch (Throwable th) {
                        th = th;
                        cellLocation = a3;
                        es.a(th, "CgiManager", "getSim2Cgi");
                        return cellLocation;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    cellLocation = a2;
                    es.a(th, "CgiManager", "getSim2Cgi");
                    return cellLocation;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            es.a(th, "CgiManager", "getSim2Cgi");
            return cellLocation;
        }
        return cellLocation;
    }

    private Class<?> q() {
        String str;
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        switch (this.r) {
            case 0:
                str = "android.telephony.TelephonyManager";
                break;
            case 1:
                str = "android.telephony.MSimTelephonyManager";
                break;
            case 2:
                str = "android.telephony.TelephonyManager2";
                break;
            default:
                str = null;
                break;
        }
        try {
            return systemClassLoader.loadClass(str);
        } catch (Throwable th) {
            es.a(th, "CgiManager", "getSim2TmClass");
            return null;
        }
    }

    private int r() {
        try {
            Class.forName("android.telephony.MSimTelephonyManager");
            this.r = 1;
        } catch (Throwable unused) {
        }
        if (this.r == 0) {
            try {
                Class.forName("android.telephony.TelephonyManager2");
                this.r = 2;
            } catch (Throwable unused2) {
            }
        }
        return this.r;
    }

    public final ArrayList<ed> a() {
        return this.b;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:174:0x02ac, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x02ad, code lost:
        r9.h = r10.getMessage();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x02b3, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x00df, code lost:
        if (r10 == false) goto L_0x00e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x016c, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:?, code lost:
        com.loc.es.a(r10, "CgiManager", "hdlCdmaLocChange");
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:151:0x025d */
    /* JADX WARNING: Removed duplicated region for block: B:151:0x025d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x0261 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:160:0x0281 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x0285 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:174:0x02ac A[ExcHandler: SecurityException (r10v1 'e' java.lang.SecurityException A[CUSTOM_DECLARE]), Splitter:B:0:0x0000] */
    /* JADX WARNING: Removed duplicated region for block: B:177:0x0089 A[EDGE_INSN: B:177:0x0089->B:40:0x0089 ?: BREAK  , SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x003d A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0047 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0050 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0085 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0093 A[Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(boolean r10, boolean r11) {
        /*
            r9 = this;
            android.content.Context r0 = r9.l     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r0 = com.loc.fa.a((android.content.Context) r0)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r9.i = r0     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r0 = r9.i     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r1 = 1
            r2 = 0
            if (r0 == 0) goto L_0x0010
        L_0x000e:
            r0 = 0
            goto L_0x0020
        L_0x0010:
            long r3 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            long r5 = r9.d     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r0 = 0
            long r3 = r3 - r5
            r5 = 10000(0x2710, double:4.9407E-320)
            int r0 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r0 >= 0) goto L_0x001f
            goto L_0x000e
        L_0x001f:
            r0 = 1
        L_0x0020:
            if (r0 != 0) goto L_0x002a
            java.util.ArrayList<com.loc.ed> r0 = r9.b     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r0 = r0.isEmpty()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 == 0) goto L_0x027d
        L_0x002a:
            boolean r0 = r9.i     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r3 = 0
            if (r0 != 0) goto L_0x006b
            android.telephony.TelephonyManager r0 = r9.c     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 == 0) goto L_0x006b
            android.telephony.CellLocation r0 = r9.o()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r4 = r9.b((android.telephony.CellLocation) r0)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r4 != 0) goto L_0x0041
            android.telephony.CellLocation r0 = r9.p()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x0041:
            boolean r4 = r9.b((android.telephony.CellLocation) r0)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r4 == 0) goto L_0x0050
            r9.e = r0     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            long r4 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r9.s = r4     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            goto L_0x006b
        L_0x0050:
            long r4 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            long r6 = r9.s     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r0 = 0
            long r4 = r4 - r6
            r6 = 60000(0xea60, double:2.9644E-319)
            int r0 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r0 <= 0) goto L_0x006b
            r9.e = r3     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            java.util.ArrayList<com.loc.ed> r0 = r9.b     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r0.clear()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            java.util.ArrayList<com.loc.ed> r0 = r9.n     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r0.clear()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x006b:
            boolean r0 = r9.f     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 != 0) goto L_0x0089
            android.telephony.CellLocation r0 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 != 0) goto L_0x0089
            if (r11 == 0) goto L_0x0089
            r11 = 0
        L_0x0076:
            r4 = 10
            java.lang.Thread.sleep(r4)     // Catch:{ InterruptedException -> 0x007c }
            goto L_0x0080
        L_0x007c:
            r0 = move-exception
            r0.printStackTrace()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x0080:
            int r11 = r11 + r1
            android.telephony.CellLocation r0 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 != 0) goto L_0x0089
            r0 = 50
            if (r11 < r0) goto L_0x0076
        L_0x0089:
            r9.f = r1     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            android.telephony.CellLocation r11 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r11 = r9.b((android.telephony.CellLocation) r11)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r11 == 0) goto L_0x017a
            android.telephony.TelephonyManager r11 = r9.c     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            java.lang.String[] r11 = com.loc.fa.a((android.telephony.TelephonyManager) r11)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            android.telephony.CellLocation r0 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            int r0 = r9.c(r0)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            switch(r0) {
                case 1: goto L_0x0175;
                case 2: goto L_0x00a4;
                default: goto L_0x00a2;
            }     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x00a2:
            goto L_0x017a
        L_0x00a4:
            android.telephony.CellLocation r0 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r0 == 0) goto L_0x017a
            java.util.ArrayList<com.loc.ed> r4 = r9.b     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r4.clear()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            int r4 = com.loc.fa.d()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r5 = 5
            if (r4 < r5) goto L_0x017a
            java.lang.Object r4 = r9.q     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r4 == 0) goto L_0x00e3
            java.lang.Class r4 = r0.getClass()     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            java.lang.String r5 = "mGsmCellLoc"
            java.lang.reflect.Field r4 = r4.getDeclaredField(r5)     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            boolean r5 = r4.isAccessible()     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            if (r5 != 0) goto L_0x00cb
            r4.setAccessible(r1)     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
        L_0x00cb:
            java.lang.Object r4 = r4.get(r0)     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            android.telephony.gsm.GsmCellLocation r4 = (android.telephony.gsm.GsmCellLocation) r4     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            if (r4 == 0) goto L_0x00de
            boolean r5 = r9.b((android.telephony.CellLocation) r4)     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            if (r5 == 0) goto L_0x00de
            r9.a((android.telephony.CellLocation) r4, (java.lang.String[]) r11, (boolean) r10)     // Catch:{ Throwable -> 0x00de, SecurityException -> 0x02ac }
            r10 = 1
            goto L_0x00df
        L_0x00de:
            r10 = 0
        L_0x00df:
            if (r10 == 0) goto L_0x00e3
            goto L_0x017a
        L_0x00e3:
            boolean r10 = r9.b((android.telephony.CellLocation) r0)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 != 0) goto L_0x00eb
            goto L_0x017a
        L_0x00eb:
            r10 = 2
            r9.f6579a = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            com.loc.ed r4 = new com.loc.ed     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.<init>(r10, r1)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r10 = r11[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.f6578a = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r10 = r11[r1]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = java.lang.Integer.parseInt(r10)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.b = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            java.lang.String r10 = "getSystemId"
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = com.loc.ew.b(r0, r10, r11)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.g = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            java.lang.String r10 = "getNetworkId"
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = com.loc.ew.b(r0, r10, r11)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.h = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            java.lang.String r10 = "getBaseStationId"
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = com.loc.ew.b(r0, r10, r11)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.i = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = r9.o     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.j = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            java.lang.String r10 = "getBaseStationLatitude"
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = com.loc.ew.b(r0, r10, r11)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.e = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            java.lang.String r10 = "getBaseStationLongitude"
            java.lang.Object[] r11 = new java.lang.Object[r2]     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = com.loc.ew.b(r0, r10, r11)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.f = r10     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r10 = r4.e     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            int r11 = r4.f     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 != r11) goto L_0x0144
            int r10 = r4.e     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 <= 0) goto L_0x0144
            goto L_0x0145
        L_0x0144:
            r1 = 0
        L_0x0145:
            int r10 = r4.e     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 < 0) goto L_0x015a
            int r10 = r4.f     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 < 0) goto L_0x015a
            int r10 = r4.e     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r11 = 2147483647(0x7fffffff, float:NaN)
            if (r10 == r11) goto L_0x015a
            int r10 = r4.f     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 == r11) goto L_0x015a
            if (r1 == 0) goto L_0x015e
        L_0x015a:
            r4.e = r2     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r4.f = r2     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
        L_0x015e:
            java.util.ArrayList<com.loc.ed> r10 = r9.b     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            boolean r10 = r10.contains(r4)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            if (r10 != 0) goto L_0x017a
            java.util.ArrayList<com.loc.ed> r10 = r9.b     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            r10.add(r4)     // Catch:{ Throwable -> 0x016c, SecurityException -> 0x02ac }
            goto L_0x017a
        L_0x016c:
            r10 = move-exception
            java.lang.String r11 = "CgiManager"
            java.lang.String r0 = "hdlCdmaLocChange"
            com.loc.es.a(r10, r11, r0)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            goto L_0x017a
        L_0x0175:
            android.telephony.CellLocation r0 = r9.e     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r9.a((android.telephony.CellLocation) r0, (java.lang.String[]) r11, (boolean) r10)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x017a:
            int r10 = com.loc.fa.d()     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            r11 = 18
            if (r10 < r11) goto L_0x025d
            android.telephony.TelephonyManager r10 = r9.c     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            if (r10 == 0) goto L_0x025d
            java.util.ArrayList<com.loc.ed> r10 = r9.n     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            com.loc.ec r11 = r9.p     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            android.telephony.TelephonyManager r0 = r9.c     // Catch:{ SecurityException -> 0x0195, Throwable -> 0x025d }
            java.util.List r0 = r0.getAllCellInfo()     // Catch:{ SecurityException -> 0x0195, Throwable -> 0x025d }
            r9.h = r3     // Catch:{ SecurityException -> 0x0193, Throwable -> 0x025d }
            goto L_0x019d
        L_0x0193:
            r1 = move-exception
            goto L_0x0197
        L_0x0195:
            r1 = move-exception
            r0 = r3
        L_0x0197:
            java.lang.String r1 = r1.getMessage()     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            r9.h = r1     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
        L_0x019d:
            if (r0 == 0) goto L_0x024c
            int r1 = r0.size()     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            if (r1 == 0) goto L_0x024c
            if (r10 == 0) goto L_0x01aa
            r10.clear()     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
        L_0x01aa:
            r3 = 0
        L_0x01ab:
            if (r3 >= r1) goto L_0x024c
            java.lang.Object r4 = r0.get(r3)     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            android.telephony.CellInfo r4 = (android.telephony.CellInfo) r4     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            if (r4 == 0) goto L_0x0248
            boolean r5 = r4.isRegistered()     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            boolean r6 = r4 instanceof android.telephony.CellInfoCdma     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            r7 = 65535(0xffff, double:3.23786E-319)
            if (r6 == 0) goto L_0x01e2
            android.telephony.CellInfoCdma r4 = (android.telephony.CellInfoCdma) r4     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            android.telephony.CellIdentityCdma r6 = r4.getCellIdentity()     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            boolean r6 = a((android.telephony.CellIdentityCdma) r6)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 != 0) goto L_0x01ce
            goto L_0x0248
        L_0x01ce:
            com.loc.ed r4 = r9.a((android.telephony.CellInfoCdma) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = r11.a((com.loc.ed) r4)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = java.lang.Math.min(r7, r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            short r5 = (short) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            r4.l = r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
        L_0x01de:
            r10.add(r4)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            goto L_0x0248
        L_0x01e2:
            boolean r6 = r4 instanceof android.telephony.CellInfoGsm     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 == 0) goto L_0x0204
            android.telephony.CellInfoGsm r4 = (android.telephony.CellInfoGsm) r4     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            android.telephony.CellIdentityGsm r6 = r4.getCellIdentity()     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            boolean r6 = a((android.telephony.CellIdentityGsm) r6)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 != 0) goto L_0x01f3
            goto L_0x0248
        L_0x01f3:
            com.loc.ed r4 = a((android.telephony.CellInfoGsm) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = r11.a((com.loc.ed) r4)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = java.lang.Math.min(r7, r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            short r5 = (short) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            r4.l = r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            goto L_0x01de
        L_0x0204:
            boolean r6 = r4 instanceof android.telephony.CellInfoWcdma     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 == 0) goto L_0x0226
            android.telephony.CellInfoWcdma r4 = (android.telephony.CellInfoWcdma) r4     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            android.telephony.CellIdentityWcdma r6 = r4.getCellIdentity()     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            boolean r6 = a((android.telephony.CellIdentityWcdma) r6)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 != 0) goto L_0x0215
            goto L_0x0248
        L_0x0215:
            com.loc.ed r4 = a((android.telephony.CellInfoWcdma) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = r11.a((com.loc.ed) r4)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = java.lang.Math.min(r7, r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            short r5 = (short) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            r4.l = r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            goto L_0x01de
        L_0x0226:
            boolean r6 = r4 instanceof android.telephony.CellInfoLte     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 == 0) goto L_0x0248
            android.telephony.CellInfoLte r4 = (android.telephony.CellInfoLte) r4     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            android.telephony.CellIdentityLte r6 = r4.getCellIdentity()     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            boolean r6 = a((android.telephony.CellIdentityLte) r6)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            if (r6 != 0) goto L_0x0237
            goto L_0x0248
        L_0x0237:
            com.loc.ed r4 = a((android.telephony.CellInfoLte) r4, (boolean) r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = r11.a((com.loc.ed) r4)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            long r5 = java.lang.Math.min(r7, r5)     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            int r5 = (int) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            short r5 = (short) r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            r4.l = r5     // Catch:{ Throwable -> 0x0248, SecurityException -> 0x02ac }
            goto L_0x01de
        L_0x0248:
            int r3 = r3 + 1
            goto L_0x01ab
        L_0x024c:
            if (r10 == 0) goto L_0x025d
            int r0 = r10.size()     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            if (r0 <= 0) goto L_0x025d
            int r0 = r9.f6579a     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            r0 = r0 | 4
            r9.f6579a = r0     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
            r11.a((java.util.ArrayList<? extends com.loc.ed>) r10)     // Catch:{ Throwable -> 0x025d, SecurityException -> 0x02ac }
        L_0x025d:
            android.telephony.TelephonyManager r10 = r9.c     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r10 == 0) goto L_0x0277
            android.telephony.TelephonyManager r10 = r9.c     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            java.lang.String r10 = r10.getNetworkOperator()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r9.m = r10     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            java.lang.String r10 = r9.m     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r10 = android.text.TextUtils.isEmpty(r10)     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r10 != 0) goto L_0x0277
            int r10 = r9.f6579a     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r10 = r10 | 8
            r9.f6579a = r10     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x0277:
            long r10 = com.loc.fa.c()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r9.d = r10     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x027d:
            boolean r10 = r9.i     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r10 == 0) goto L_0x0285
            r9.i()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            return
        L_0x0285:
            int r10 = r9.f6579a     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            r10 = r10 & 3
            switch(r10) {
                case 1: goto L_0x0298;
                case 2: goto L_0x028d;
                default: goto L_0x028c;
            }     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x028c:
            goto L_0x02a2
        L_0x028d:
            java.util.ArrayList<com.loc.ed> r10 = r9.b     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r10 = r10.isEmpty()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r10 == 0) goto L_0x02a2
            r9.f6579a = r2     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            goto L_0x02a2
        L_0x0298:
            java.util.ArrayList<com.loc.ed> r10 = r9.b     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            boolean r10 = r10.isEmpty()     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
            if (r10 == 0) goto L_0x02a2
            r9.f6579a = r2     // Catch:{ SecurityException -> 0x02ac, Throwable -> 0x02a3 }
        L_0x02a2:
            return
        L_0x02a3:
            r10 = move-exception
            java.lang.String r11 = "CgiManager"
            java.lang.String r0 = "refresh"
            com.loc.es.a(r10, r11, r0)
            return
        L_0x02ac:
            r10 = move-exception
            java.lang.String r10 = r10.getMessage()
            r9.h = r10
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.a(boolean, boolean):void");
    }

    /* access modifiers changed from: package-private */
    public final boolean a(CellLocation cellLocation) {
        String str;
        String str2;
        if (cellLocation == null) {
            return false;
        }
        switch (c(cellLocation)) {
            case 1:
                try {
                    GsmCellLocation gsmCellLocation = (GsmCellLocation) cellLocation;
                    return a(gsmCellLocation.getLac(), gsmCellLocation.getCid());
                } catch (Throwable th) {
                    th = th;
                    str2 = "CgiManager";
                    str = "cgiUseful Cgi.I_GSM_T";
                    break;
                }
            case 2:
                try {
                    return ew.b(cellLocation, "getSystemId", new Object[0]) > 0 && ew.b(cellLocation, "getNetworkId", new Object[0]) >= 0 && ew.b(cellLocation, "getBaseStationId", new Object[0]) >= 0;
                } catch (Throwable th2) {
                    th = th2;
                    str2 = "CgiManager";
                    str = "cgiUseful Cgi.I_CDMA_T";
                    break;
                }
                break;
            default:
                return true;
        }
        es.a(th, str2, str);
        return true;
    }

    public final ArrayList<ed> b() {
        return this.n;
    }

    public final ed c() {
        if (this.i) {
            return null;
        }
        ArrayList<ed> arrayList = this.b;
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    public final ed d() {
        if (this.i) {
            return null;
        }
        ArrayList<ed> arrayList = this.n;
        if (arrayList.size() > 0) {
            return arrayList.get(0);
        }
        return null;
    }

    public final int e() {
        return this.f6579a;
    }

    public final int f() {
        return this.f6579a & 3;
    }

    public final TelephonyManager g() {
        return this.c;
    }

    public final void h() {
        this.p.a();
        this.s = 0;
        synchronized (this.u) {
            this.t = true;
        }
        if (!(this.c == null || this.g == null)) {
            try {
                this.c.listen(this.g, 0);
            } catch (Throwable th) {
                es.a(th, "CgiManager", Constants.Event.SLOT_LIFECYCLE.DESTORY);
            }
        }
        this.g = null;
        if (this.k != null) {
            this.k.quit();
            this.k = null;
        }
        this.o = AuthCode.n;
        this.c = null;
        this.q = null;
    }

    /* access modifiers changed from: package-private */
    public final void i() {
        this.h = null;
        this.e = null;
        this.f6579a = 0;
        this.b.clear();
        this.n.clear();
    }

    public final String j() {
        return this.h;
    }

    public final String k() {
        return this.m;
    }

    public final String l() {
        if (this.i) {
            i();
        }
        if (this.j == null) {
            this.j = new StringBuilder();
        } else {
            this.j.delete(0, this.j.length());
        }
        if ((this.f6579a & 3) == 1) {
            for (int i2 = 1; i2 < this.b.size(); i2++) {
                StringBuilder sb = this.j;
                sb.append("#");
                sb.append(this.b.get(i2).b);
                StringBuilder sb2 = this.j;
                sb2.append("|");
                sb2.append(this.b.get(i2).c);
                StringBuilder sb3 = this.j;
                sb3.append("|");
                sb3.append(this.b.get(i2).d);
            }
        }
        if (this.j.length() > 0) {
            this.j.deleteCharAt(0);
        }
        return this.j.toString();
    }

    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:10:0x001f */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0037 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:24:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean m() {
        /*
            r3 = this;
            r0 = 1
            android.telephony.TelephonyManager r1 = r3.c     // Catch:{ Throwable -> 0x001f }
            if (r1 == 0) goto L_0x001f
            android.telephony.TelephonyManager r1 = r3.c     // Catch:{ Throwable -> 0x001f }
            java.lang.String r1 = r1.getSimOperator()     // Catch:{ Throwable -> 0x001f }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x001f }
            if (r1 != 0) goto L_0x0012
            return r0
        L_0x0012:
            android.telephony.TelephonyManager r1 = r3.c     // Catch:{ Throwable -> 0x001f }
            java.lang.String r1 = r1.getSimCountryIso()     // Catch:{ Throwable -> 0x001f }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ Throwable -> 0x001f }
            if (r1 != 0) goto L_0x001f
            return r0
        L_0x001f:
            android.content.Context r1 = r3.l     // Catch:{ Throwable -> 0x0038 }
            android.net.NetworkInfo r1 = com.loc.fa.c((android.content.Context) r1)     // Catch:{ Throwable -> 0x0038 }
            int r1 = com.loc.fa.a((android.net.NetworkInfo) r1)     // Catch:{ Throwable -> 0x0038 }
            if (r1 == 0) goto L_0x0037
            r2 = 4
            if (r1 == r2) goto L_0x0037
            r2 = 2
            if (r1 == r2) goto L_0x0037
            r2 = 5
            if (r1 == r2) goto L_0x0037
            r2 = 3
            if (r1 != r2) goto L_0x0038
        L_0x0037:
            return r0
        L_0x0038:
            r0 = 0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.ee.m():boolean");
    }
}
