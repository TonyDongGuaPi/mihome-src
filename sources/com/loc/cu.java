package com.loc;

import com.amap.api.location.AMapLocation;
import com.autonavi.aps.amapapi.model.AMapLocationServer;

public final class cu {

    /* renamed from: a  reason: collision with root package name */
    AMapLocationServer f6547a = null;
    long b = 0;
    long c = 0;
    int d = 0;
    long e = 0;
    AMapLocation f = null;
    long g = 0;
    private boolean h = true;

    private AMapLocationServer b(AMapLocationServer aMapLocationServer) {
        int i;
        if (fa.a(aMapLocationServer)) {
            if (!this.h || !er.b(aMapLocationServer.getTime())) {
                i = this.d;
            } else if (aMapLocationServer.getLocationType() == 5 || aMapLocationServer.getLocationType() == 6) {
                i = 4;
            }
            aMapLocationServer.setLocationType(i);
        }
        return aMapLocationServer;
    }

    public final AMapLocation a(AMapLocation aMapLocation) {
        if (!fa.a(aMapLocation)) {
            return aMapLocation;
        }
        long c2 = fa.c() - this.g;
        this.g = fa.c();
        if (c2 > 5000) {
            return aMapLocation;
        }
        if (this.f == null) {
            this.f = aMapLocation;
            return aMapLocation;
        } else if (1 != this.f.getLocationType() && !"gps".equalsIgnoreCase(this.f.getProvider())) {
            this.f = aMapLocation;
            return aMapLocation;
        } else if (this.f.getAltitude() == aMapLocation.getAltitude() && this.f.getLongitude() == aMapLocation.getLongitude()) {
            this.f = aMapLocation;
            return aMapLocation;
        } else {
            long abs = Math.abs(aMapLocation.getTime() - this.f.getTime());
            if (30000 < abs) {
                this.f = aMapLocation;
                return aMapLocation;
            } else if (fa.a(aMapLocation, this.f) > (((this.f.getSpeed() + aMapLocation.getSpeed()) * ((float) abs)) / 2000.0f) + ((this.f.getAccuracy() + aMapLocation.getAccuracy()) * 2.0f) + 3000.0f) {
                return this.f;
            } else {
                this.f = aMapLocation;
                return aMapLocation;
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0118, code lost:
        if (r11 < 30000) goto L_0x00fd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x012d, code lost:
        if ((r9 - r0.c) > 30000) goto L_0x00d3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final com.autonavi.aps.amapapi.model.AMapLocationServer a(com.autonavi.aps.amapapi.model.AMapLocationServer r20) {
        /*
            r19 = this;
            r0 = r19
            r1 = r20
            long r2 = com.loc.fa.c()
            long r4 = r0.e
            long r2 = r2 - r4
            r4 = 30000(0x7530, double:1.4822E-319)
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 <= 0) goto L_0x001c
            r0.f6547a = r1
            long r1 = com.loc.fa.c()
            r0.e = r1
        L_0x0019:
            com.autonavi.aps.amapapi.model.AMapLocationServer r1 = r0.f6547a
            return r1
        L_0x001c:
            long r2 = com.loc.fa.c()
            r0.e = r2
            com.autonavi.aps.amapapi.model.AMapLocationServer r2 = r0.f6547a
            boolean r2 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r2)
            if (r2 == 0) goto L_0x0056
            boolean r2 = com.loc.fa.a((com.autonavi.aps.amapapi.model.AMapLocationServer) r20)
            if (r2 != 0) goto L_0x0031
            goto L_0x0056
        L_0x0031:
            long r2 = r20.getTime()
            com.autonavi.aps.amapapi.model.AMapLocationServer r6 = r0.f6547a
            long r6 = r6.getTime()
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            r2 = 1133903872(0x43960000, float:300.0)
            if (r8 != 0) goto L_0x004a
            float r3 = r20.getAccuracy()
            int r3 = (r3 > r2 ? 1 : (r3 == r2 ? 0 : -1))
            if (r3 >= 0) goto L_0x004a
            return r1
        L_0x004a:
            java.lang.String r3 = r20.getProvider()
            java.lang.String r6 = "gps"
            boolean r3 = r3.equals(r6)
            if (r3 == 0) goto L_0x005f
        L_0x0056:
            long r2 = com.loc.fa.c()
            r0.b = r2
        L_0x005c:
            r0.f6547a = r1
            goto L_0x0019
        L_0x005f:
            int r3 = r20.c()
            com.autonavi.aps.amapapi.model.AMapLocationServer r6 = r0.f6547a
            int r6 = r6.c()
            if (r3 == r6) goto L_0x006c
            goto L_0x0056
        L_0x006c:
            java.lang.String r3 = r20.getBuildingId()
            com.autonavi.aps.amapapi.model.AMapLocationServer r6 = r0.f6547a
            java.lang.String r6 = r6.getBuildingId()
            boolean r3 = r3.equals(r6)
            if (r3 != 0) goto L_0x0087
            java.lang.String r3 = r20.getBuildingId()
            boolean r3 = android.text.TextUtils.isEmpty(r3)
            if (r3 != 0) goto L_0x0087
            goto L_0x0056
        L_0x0087:
            int r3 = r20.getLocationType()
            r0.d = r3
            com.autonavi.aps.amapapi.model.AMapLocationServer r3 = r0.f6547a
            float r3 = com.loc.fa.a((com.amap.api.location.AMapLocation) r1, (com.amap.api.location.AMapLocation) r3)
            com.autonavi.aps.amapapi.model.AMapLocationServer r6 = r0.f6547a
            float r6 = r6.getAccuracy()
            float r7 = r20.getAccuracy()
            float r8 = r7 - r6
            long r9 = com.loc.fa.c()
            long r11 = r0.b
            long r11 = r9 - r11
            r13 = 1120403456(0x42c80000, float:100.0)
            int r14 = (r6 > r13 ? 1 : (r6 == r13 ? 0 : -1))
            r15 = 1
            r16 = 0
            r17 = 1133871104(0x43958000, float:299.0)
            if (r14 > 0) goto L_0x00b9
            int r14 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r14 <= 0) goto L_0x00b9
            r14 = 1
            goto L_0x00ba
        L_0x00b9:
            r14 = 0
        L_0x00ba:
            int r18 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r18 <= 0) goto L_0x00c3
            int r18 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r18 <= 0) goto L_0x00c3
            goto L_0x00c4
        L_0x00c3:
            r15 = 0
        L_0x00c4:
            r4 = 0
            if (r14 != 0) goto L_0x011c
            if (r15 == 0) goto L_0x00cb
            goto L_0x011c
        L_0x00cb:
            int r13 = (r7 > r13 ? 1 : (r7 == r13 ? 0 : -1))
            if (r13 >= 0) goto L_0x00db
            int r13 = (r6 > r17 ? 1 : (r6 == r17 ? 0 : -1))
            if (r13 <= 0) goto L_0x00db
        L_0x00d3:
            r0.b = r9
            r0.f6547a = r1
            r0.c = r4
            goto L_0x0019
        L_0x00db:
            int r13 = (r7 > r17 ? 1 : (r7 == r17 ? 0 : -1))
            if (r13 > 0) goto L_0x00e1
            r0.c = r4
        L_0x00e1:
            r4 = 1092616192(0x41200000, float:10.0)
            int r4 = (r3 > r4 ? 1 : (r3 == r4 ? 0 : -1))
            if (r4 >= 0) goto L_0x0110
            double r3 = (double) r3
            r13 = 4591870180066957722(0x3fb999999999999a, double:0.1)
            int r5 = (r3 > r13 ? 1 : (r3 == r13 ? 0 : -1))
            if (r5 <= 0) goto L_0x0110
            r3 = 1084227584(0x40a00000, float:5.0)
            int r3 = (r7 > r3 ? 1 : (r7 == r3 ? 0 : -1))
            if (r3 <= 0) goto L_0x0110
            r2 = -1013579776(0xffffffffc3960000, float:-300.0)
            int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0105
        L_0x00fd:
            com.autonavi.aps.amapapi.model.AMapLocationServer r1 = r0.f6547a
            com.autonavi.aps.amapapi.model.AMapLocationServer r1 = r0.b(r1)
            goto L_0x005c
        L_0x0105:
            float r6 = r6 / r7
            r2 = 1073741824(0x40000000, float:2.0)
            int r2 = (r6 > r2 ? 1 : (r6 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x00fd
            r0.b = r9
            goto L_0x005c
        L_0x0110:
            int r2 = (r8 > r2 ? 1 : (r8 == r2 ? 0 : -1))
            if (r2 < 0) goto L_0x0056
            r2 = 30000(0x7530, double:1.4822E-319)
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 < 0) goto L_0x00fd
            goto L_0x0056
        L_0x011c:
            long r2 = r0.c
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0125
            r0.c = r9
            goto L_0x00fd
        L_0x0125:
            long r2 = r0.c
            long r2 = r9 - r2
            r6 = 30000(0x7530, double:1.4822E-319)
            int r8 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r8 <= 0) goto L_0x00fd
            goto L_0x00d3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.cu.a(com.autonavi.aps.amapapi.model.AMapLocationServer):com.autonavi.aps.amapapi.model.AMapLocationServer");
    }

    public final void a() {
        this.f6547a = null;
        this.b = 0;
        this.c = 0;
        this.f = null;
        this.g = 0;
    }

    public final void a(boolean z) {
        this.h = z;
    }
}
