package com.alipay.deviceid.module.x;

import android.content.Context;

public final class m {

    /* renamed from: a  reason: collision with root package name */
    public String f932a;
    public String b;
    public String c;
    public String d;
    public String e;
    public String f;
    public String g;
    public String h;
    public String i;
    public String j;
    volatile int k = 0;
    private Context l;

    private m() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:62:0x00d5 A[Catch:{ Throwable -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00d6 A[Catch:{ Throwable -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x00f0 A[Catch:{ Exception -> 0x0190 }] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x01c9 A[Catch:{ Exception -> 0x01f6 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.alipay.deviceid.module.x.m a(android.content.Context r13) {
        /*
            com.alipay.deviceid.module.x.m r0 = new com.alipay.deviceid.module.x.m
            r0.<init>()
            r0.l = r13
            r1 = 0
            r2 = 1
            r3 = 2
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r13.getSystemService(r4)     // Catch:{ Throwable -> 0x00cb }
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch:{ Throwable -> 0x00cb }
            int r5 = r4.getPhoneType()     // Catch:{ Throwable -> 0x00cb }
            if (r5 != r3) goto L_0x001a
            r5 = 2
            goto L_0x001b
        L_0x001a:
            r5 = 1
        L_0x001b:
            java.lang.String r6 = ""
            java.lang.String r7 = ""
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            r10 = 3
            if (r5 != r3) goto L_0x0072
            android.telephony.CellLocation r5 = r4.getCellLocation()     // Catch:{ Exception -> 0x0064 }
            android.telephony.cdma.CdmaCellLocation r5 = (android.telephony.cdma.CdmaCellLocation) r5     // Catch:{ Exception -> 0x0064 }
            if (r5 == 0) goto L_0x0070
            int r11 = r5.getNetworkId()     // Catch:{ Exception -> 0x0064 }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0064 }
            java.lang.String r4 = r4.getNetworkOperator()     // Catch:{ Exception -> 0x0061 }
            if (r4 == 0) goto L_0x0049
            java.lang.String r9 = ""
            boolean r9 = r4.equals(r9)     // Catch:{ Exception -> 0x0061 }
            if (r9 != 0) goto L_0x0049
            java.lang.String r4 = r4.substring(r1, r10)     // Catch:{ Exception -> 0x0061 }
            r6 = r4
        L_0x0049:
            int r4 = r5.getSystemId()     // Catch:{ Exception -> 0x0061 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x0061 }
            int r5 = r5.getBaseStationId()     // Catch:{ Exception -> 0x005d }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x005d }
            r8 = r5
            r9 = r11
            goto L_0x00c3
        L_0x005d:
            r5 = move-exception
            r7 = r4
            r4 = r5
            goto L_0x0062
        L_0x0061:
            r4 = move-exception
        L_0x0062:
            r9 = r11
            goto L_0x0065
        L_0x0064:
            r4 = move-exception
        L_0x0065:
            java.lang.String r5 = "e"
            java.lang.String r10 = "e"
            java.lang.Throwable r4 = r4.fillInStackTrace()     // Catch:{ Throwable -> 0x00cb }
            android.util.Log.e(r5, r10, r4)     // Catch:{ Throwable -> 0x00cb }
        L_0x0070:
            r4 = r7
            goto L_0x00c3
        L_0x0072:
            android.telephony.CellLocation r5 = r4.getCellLocation()     // Catch:{ Exception -> 0x00b6 }
            android.telephony.gsm.GsmCellLocation r5 = (android.telephony.gsm.GsmCellLocation) r5     // Catch:{ Exception -> 0x00b6 }
            if (r5 == 0) goto L_0x0070
            java.lang.String r11 = r4.getNetworkOperator()     // Catch:{ Exception -> 0x00b6 }
            if (r11 == 0) goto L_0x009f
            java.lang.String r12 = ""
            boolean r11 = r11.equals(r12)     // Catch:{ Exception -> 0x00b6 }
            if (r11 != 0) goto L_0x009f
            java.lang.String r11 = r4.getNetworkOperator()     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r11 = r11.substring(r1, r10)     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r4 = r4.getNetworkOperator()     // Catch:{ Exception -> 0x009c }
            r6 = 5
            java.lang.String r4 = r4.substring(r10, r6)     // Catch:{ Exception -> 0x009c }
            r7 = r4
            r6 = r11
            goto L_0x009f
        L_0x009c:
            r4 = move-exception
            r6 = r11
            goto L_0x00b7
        L_0x009f:
            int r4 = r5.getCid()     // Catch:{ Exception -> 0x00b6 }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x00b6 }
            int r5 = r5.getLac()     // Catch:{ Exception -> 0x00b2 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x00b2 }
            r8 = r4
            r9 = r5
            goto L_0x0070
        L_0x00b2:
            r5 = move-exception
            r8 = r4
            r4 = r5
            goto L_0x00b7
        L_0x00b6:
            r4 = move-exception
        L_0x00b7:
            java.lang.String r5 = "e"
            java.lang.String r10 = "e"
            java.lang.Throwable r4 = r4.fillInStackTrace()     // Catch:{ Throwable -> 0x00cb }
            android.util.Log.e(r5, r10, r4)     // Catch:{ Throwable -> 0x00cb }
            goto L_0x0070
        L_0x00c3:
            r0.g = r6     // Catch:{ Throwable -> 0x00cb }
            r0.h = r4     // Catch:{ Throwable -> 0x00cb }
            r0.i = r8     // Catch:{ Throwable -> 0x00cb }
            r0.j = r9     // Catch:{ Throwable -> 0x00cb }
        L_0x00cb:
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r13.getSystemService(r4)     // Catch:{ Throwable -> 0x00e0 }
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch:{ Throwable -> 0x00e0 }
            if (r4 != 0) goto L_0x00d6
            goto L_0x00e0
        L_0x00d6:
            com.alipay.deviceid.module.x.m$1 r5 = new com.alipay.deviceid.module.x.m$1     // Catch:{ Throwable -> 0x00e0 }
            r5.<init>(r0, r4)     // Catch:{ Throwable -> 0x00e0 }
            r6 = 256(0x100, float:3.59E-43)
            r4.listen(r5, r6)     // Catch:{ Throwable -> 0x00e0 }
        L_0x00e0:
            java.lang.String r4 = "location"
            java.lang.Object r4 = r13.getSystemService(r4)     // Catch:{ Exception -> 0x0190 }
            android.location.LocationManager r4 = (android.location.LocationManager) r4     // Catch:{ Exception -> 0x0190 }
            java.lang.String r5 = "network"
            boolean r5 = r4.isProviderEnabled(r5)     // Catch:{ Exception -> 0x0190 }
            if (r5 == 0) goto L_0x0134
            com.alipay.deviceid.module.x.n r12 = new com.alipay.deviceid.module.x.n     // Catch:{ Exception -> 0x0190 }
            r12.<init>()     // Catch:{ Exception -> 0x0190 }
            java.lang.String r6 = "network"
            r7 = 300000(0x493e0, double:1.482197E-318)
            r9 = 0
            android.os.Looper r11 = android.os.Looper.getMainLooper()     // Catch:{ Exception -> 0x0190 }
            r5 = r4
            r10 = r12
            r5.requestLocationUpdates(r6, r7, r9, r10, r11)     // Catch:{ Exception -> 0x0190 }
            r4.removeUpdates(r12)     // Catch:{ Exception -> 0x0190 }
            java.lang.String r5 = "network"
            android.location.Location r4 = r4.getLastKnownLocation(r5)     // Catch:{ Exception -> 0x0190 }
            if (r4 == 0) goto L_0x0134
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190 }
            r1.<init>()     // Catch:{ Exception -> 0x0190 }
            double r5 = r4.getLatitude()     // Catch:{ Exception -> 0x0190 }
            r1.append(r5)     // Catch:{ Exception -> 0x0190 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0190 }
            r0.b = r1     // Catch:{ Exception -> 0x0190 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190 }
            r1.<init>()     // Catch:{ Exception -> 0x0190 }
            double r4 = r4.getLongitude()     // Catch:{ Exception -> 0x0190 }
            r1.append(r4)     // Catch:{ Exception -> 0x0190 }
            java.lang.String r1 = r1.toString()     // Catch:{ Exception -> 0x0190 }
            r0.f932a = r1     // Catch:{ Exception -> 0x0190 }
            r1 = 1
        L_0x0134:
            java.lang.String r4 = "phone"
            java.lang.Object r4 = r13.getSystemService(r4)     // Catch:{ Exception -> 0x0190 }
            android.telephony.TelephonyManager r4 = (android.telephony.TelephonyManager) r4     // Catch:{ Exception -> 0x0190 }
            if (r1 != 0) goto L_0x019c
            int r1 = r4.getPhoneType()     // Catch:{ Exception -> 0x0190 }
            if (r1 != r3) goto L_0x019c
            android.telephony.CellLocation r1 = r4.getCellLocation()     // Catch:{ Exception -> 0x0190 }
            android.telephony.cdma.CdmaCellLocation r1 = (android.telephony.cdma.CdmaCellLocation) r1     // Catch:{ Exception -> 0x0190 }
            if (r1 == 0) goto L_0x019c
            java.lang.String r3 = r0.b     // Catch:{ Exception -> 0x0190 }
            boolean r3 = com.alipay.deviceid.module.x.e.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0190 }
            if (r3 == 0) goto L_0x019c
            java.lang.String r3 = r0.f932a     // Catch:{ Exception -> 0x0190 }
            boolean r3 = com.alipay.deviceid.module.x.e.a((java.lang.String) r3)     // Catch:{ Exception -> 0x0190 }
            if (r3 == 0) goto L_0x019c
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190 }
            r3.<init>()     // Catch:{ Exception -> 0x0190 }
            int r4 = r1.getBaseStationLatitude()     // Catch:{ Exception -> 0x0190 }
            double r4 = (double) r4
            r6 = 4669142098048450560(0x40cc200000000000, double:14400.0)
            java.lang.Double.isNaN(r4)
            double r4 = r4 / r6
            r3.append(r4)     // Catch:{ Exception -> 0x0190 }
            java.lang.String r3 = r3.toString()     // Catch:{ Exception -> 0x0190 }
            r0.b = r3     // Catch:{ Exception -> 0x0190 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0190 }
            r3.<init>()     // Catch:{ Exception -> 0x0190 }
            int r1 = r1.getBaseStationLongitude()     // Catch:{ Exception -> 0x0190 }
            double r4 = (double) r1
            java.lang.Double.isNaN(r4)
            double r4 = r4 / r6
            r3.append(r4)     // Catch:{ Exception -> 0x0190 }
            java.lang.String r1 = r3.toString()     // Catch:{ Exception -> 0x0190 }
            r0.f932a = r1     // Catch:{ Exception -> 0x0190 }
            goto L_0x019c
        L_0x0190:
            r1 = move-exception
            java.lang.String r3 = "e"
            java.lang.String r4 = "e"
            java.lang.Throwable r1 = r1.fillInStackTrace()
            android.util.Log.e(r3, r4, r1)
        L_0x019c:
            java.lang.String r1 = "connectivity"
            java.lang.Object r1 = r13.getSystemService(r1)     // Catch:{ Exception -> 0x01f6 }
            android.net.ConnectivityManager r1 = (android.net.ConnectivityManager) r1     // Catch:{ Exception -> 0x01f6 }
            android.net.NetworkInfo r1 = r1.getNetworkInfo(r2)     // Catch:{ Exception -> 0x01f6 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f6 }
            r2.<init>()     // Catch:{ Exception -> 0x01f6 }
            boolean r1 = r1.isConnected()     // Catch:{ Exception -> 0x01f6 }
            r2.append(r1)     // Catch:{ Exception -> 0x01f6 }
            java.lang.String r1 = r2.toString()     // Catch:{ Exception -> 0x01f6 }
            r0.e = r1     // Catch:{ Exception -> 0x01f6 }
            java.lang.String r1 = "wifi"
            java.lang.Object r13 = r13.getSystemService(r1)     // Catch:{ Exception -> 0x01f6 }
            android.net.wifi.WifiManager r13 = (android.net.wifi.WifiManager) r13     // Catch:{ Exception -> 0x01f6 }
            boolean r1 = r13.isWifiEnabled()     // Catch:{ Exception -> 0x01f6 }
            if (r1 == 0) goto L_0x0202
            android.net.wifi.WifiInfo r13 = r13.getConnectionInfo()     // Catch:{ Exception -> 0x01f6 }
            java.lang.String r1 = r13.getBSSID()     // Catch:{ Exception -> 0x01f6 }
            r0.c = r1     // Catch:{ Exception -> 0x01f6 }
            java.lang.String r1 = r13.getSSID()     // Catch:{ Exception -> 0x01f6 }
            byte[] r1 = r1.getBytes()     // Catch:{ Exception -> 0x01f6 }
            r2 = 8
            java.lang.String r1 = android.util.Base64.encodeToString(r1, r2)     // Catch:{ Exception -> 0x01f6 }
            r0.d = r1     // Catch:{ Exception -> 0x01f6 }
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x01f6 }
            r1.<init>()     // Catch:{ Exception -> 0x01f6 }
            int r13 = r13.getRssi()     // Catch:{ Exception -> 0x01f6 }
            r1.append(r13)     // Catch:{ Exception -> 0x01f6 }
            java.lang.String r13 = r1.toString()     // Catch:{ Exception -> 0x01f6 }
            r0.f = r13     // Catch:{ Exception -> 0x01f6 }
            goto L_0x0202
        L_0x01f6:
            r13 = move-exception
            java.lang.String r1 = "e"
            java.lang.String r2 = "e"
            java.lang.Throwable r13 = r13.fillInStackTrace()
            android.util.Log.e(r1, r2, r13)
        L_0x0202:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alipay.deviceid.module.x.m.a(android.content.Context):com.alipay.deviceid.module.x.m");
    }
}
