package com.loc;

import android.annotation.SuppressLint;
import android.net.wifi.ScanResult;
import android.text.TextUtils;
import com.xiaomi.youpin.network.annotation.Encoding;
import java.util.ArrayList;

@SuppressLint({"NewApi"})
public final class eq {
    protected static String J;
    protected static String L;
    protected String A = null;
    protected String B = null;
    protected ArrayList<ed> C = new ArrayList<>();
    protected String D = null;
    protected String E = null;
    protected ArrayList<ScanResult> F = new ArrayList<>();
    protected String G = null;
    protected String H = null;
    protected byte[] I = null;
    protected String K = null;
    protected String M = null;
    protected String N = null;
    private byte[] O = null;
    private int P = 0;

    /* renamed from: a  reason: collision with root package name */
    public String f6593a = "1";
    protected short b = 0;
    protected String c = null;
    protected String d = null;
    protected String e = null;
    protected String f = null;
    protected String g = null;
    public String h = null;
    public String i = null;
    protected String j = null;
    protected String k = null;
    protected String l = null;
    protected String m = null;
    protected String n = null;
    protected String o = null;
    protected String p = null;
    protected String q = null;
    protected String r = null;
    protected String s = null;
    protected String t = null;
    protected String u = null;
    protected String v = null;
    protected String w = null;
    protected String x = null;
    protected String y = null;
    protected int z = 0;

    private static int a(String str, byte[] bArr, int i2) {
        try {
            if (TextUtils.isEmpty(str)) {
                bArr[i2] = 0;
                return i2 + 1;
            }
            byte[] bytes = str.getBytes(Encoding.GBK);
            int length = bytes.length;
            if (length > 127) {
                length = 127;
            }
            bArr[i2] = (byte) length;
            int i3 = i2 + 1;
            System.arraycopy(bytes, 0, bArr, i3, length);
            return i3 + length;
        } catch (Throwable th) {
            es.a(th, "Req", "copyContentWithByteLen");
            bArr[i2] = 0;
        }
    }

    private String a(String str, int i2) {
        String[] split = this.B.split("\\*")[i2].split(",");
        if ("lac".equals(str)) {
            return split[0];
        }
        if ("cellid".equals(str)) {
            return split[1];
        }
        if ("signal".equals(str)) {
            return split[2];
        }
        return null;
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0022 A[Catch:{ Throwable -> 0x0010 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private byte[] a(java.lang.String r7) {
        /*
            r6 = this;
            java.lang.String r0 = ":"
            java.lang.String[] r0 = r7.split(r0)
            r1 = 6
            byte[] r2 = new byte[r1]
            r3 = 0
            if (r0 == 0) goto L_0x0012
            int r4 = r0.length     // Catch:{ Throwable -> 0x0010 }
            if (r4 == r1) goto L_0x001e
            goto L_0x0012
        L_0x0010:
            r0 = move-exception
            goto L_0x0041
        L_0x0012:
            java.lang.String[] r0 = new java.lang.String[r1]     // Catch:{ Throwable -> 0x0010 }
            r4 = 0
        L_0x0015:
            if (r4 >= r1) goto L_0x001e
            java.lang.String r5 = "0"
            r0[r4] = r5     // Catch:{ Throwable -> 0x0010 }
            int r4 = r4 + 1
            goto L_0x0015
        L_0x001e:
            r1 = 0
        L_0x001f:
            int r4 = r0.length     // Catch:{ Throwable -> 0x0010 }
            if (r1 >= r4) goto L_0x005a
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0010 }
            int r4 = r4.length()     // Catch:{ Throwable -> 0x0010 }
            r5 = 2
            if (r4 <= r5) goto L_0x0033
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0010 }
            java.lang.String r4 = r4.substring(r3, r5)     // Catch:{ Throwable -> 0x0010 }
            r0[r1] = r4     // Catch:{ Throwable -> 0x0010 }
        L_0x0033:
            r4 = r0[r1]     // Catch:{ Throwable -> 0x0010 }
            r5 = 16
            int r4 = java.lang.Integer.parseInt(r4, r5)     // Catch:{ Throwable -> 0x0010 }
            byte r4 = (byte) r4     // Catch:{ Throwable -> 0x0010 }
            r2[r1] = r4     // Catch:{ Throwable -> 0x0010 }
            int r1 = r1 + 1
            goto L_0x001f
        L_0x0041:
            java.lang.String r1 = "Req"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "getMacBa "
            r2.<init>(r3)
            r2.append(r7)
            java.lang.String r7 = r2.toString()
            com.loc.es.a(r0, r1, r7)
            java.lang.String r7 = "00:00:00:00:00:00"
            byte[] r2 = r6.a(r7)
        L_0x005a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eq.a(java.lang.String):byte[]");
    }

    private String b(String str) {
        String str2 = this.A;
        if (!str2.contains(str + ">")) {
            return "0";
        }
        String str3 = this.A;
        int indexOf = str3.indexOf(str + ">");
        String str4 = this.A;
        return this.A.substring(indexOf + str.length() + 1, str4.indexOf("</" + str));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(29:0|(1:2)|3|(1:5)|6|(9:8|(2:10|11)|14|15|(1:19)|20|(2:22|23)|26|(1:31))(1:32)|(3:33|34|35)|38|(2:40|(1:42)(1:43))(1:44)|45|(3:47|48|64)(1:65)|66|(1:71)(1:70)|72|(2:(2:75|(7:77|(1:79)|82|83|84|(1:88)|89))|(1:93))(2:94|(1:96))|97|(1:99)|100|101|102|(1:104)|105|106|(1:108)|109|110|(1:112)|113|115) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:105:0x035c */
    /* JADX WARNING: Missing exception handler attribute for start block: B:109:0x036a */
    /* JADX WARNING: Removed duplicated region for block: B:108:0x0364 A[Catch:{ Throwable -> 0x036a }] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x0372 A[Catch:{ Throwable -> 0x0378 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void a(android.content.Context r29, boolean r30, boolean r31, com.loc.ee r32, com.loc.eg r33, android.net.ConnectivityManager r34, java.lang.String r35, java.lang.String r36) {
        /*
            r28 = this;
            r1 = r28
            java.lang.String r0 = "0"
            java.lang.String r2 = "0"
            java.lang.String r3 = "0"
            java.lang.String r4 = "0"
            java.lang.String r5 = "0"
            java.lang.String r6 = com.loc.u.f(r29)
            int r7 = com.loc.fa.g()
            java.lang.String r8 = ""
            java.lang.String r9 = ""
            java.lang.String r10 = ""
            r11 = r36
            r1.K = r11
            java.lang.String r11 = "api_serverSDK_130905"
            java.lang.String r12 = "S128DF1572465B890OE3F7A13167KLEI"
            if (r31 != 0) goto L_0x0028
            java.lang.String r11 = "UC_nlp_20131029"
            java.lang.String r12 = "BKZCHMBBSSUK7U8GLUKHBB56CCFF78U"
        L_0x0028:
            java.lang.StringBuilder r13 = new java.lang.StringBuilder
            r13.<init>()
            int r14 = r32.e()
            int r15 = r32.f()
            android.telephony.TelephonyManager r16 = r32.g()
            r17 = r8
            java.util.ArrayList r8 = r32.a()
            r18 = r9
            java.util.ArrayList r9 = r32.b()
            r19 = r10
            java.util.ArrayList r10 = r33.c()
            r20 = r7
            r7 = 2
            if (r15 != r7) goto L_0x0052
            java.lang.String r0 = "1"
        L_0x0052:
            r21 = r0
            if (r16 == 0) goto L_0x00ae
            java.lang.String r0 = com.loc.es.d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0070
            java.lang.String r0 = com.loc.x.u(r29)     // Catch:{ Throwable -> 0x0065 }
            com.loc.es.d = r0     // Catch:{ Throwable -> 0x0065 }
            goto L_0x0070
        L_0x0065:
            r0 = move-exception
            java.lang.String r7 = "Aps"
            r22 = r6
            java.lang.String r6 = "getApsReq part4"
            com.loc.es.a(r0, r7, r6)
            goto L_0x0072
        L_0x0070:
            r22 = r6
        L_0x0072:
            java.lang.String r0 = com.loc.es.d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            r6 = 29
            if (r0 == 0) goto L_0x0084
            int r0 = android.os.Build.VERSION.SDK_INT
            if (r0 >= r6) goto L_0x0084
            java.lang.String r0 = "888888888888888"
            com.loc.es.d = r0
        L_0x0084:
            java.lang.String r0 = com.loc.es.e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x009b
            java.lang.String r0 = r16.getSubscriberId()     // Catch:{ SecurityException -> 0x009b, Throwable -> 0x0093 }
            com.loc.es.e = r0     // Catch:{ SecurityException -> 0x009b, Throwable -> 0x0093 }
            goto L_0x009b
        L_0x0093:
            r0 = move-exception
            java.lang.String r7 = "Aps"
            java.lang.String r6 = "getApsReq part2"
            com.loc.es.a(r0, r7, r6)
        L_0x009b:
            java.lang.String r0 = com.loc.es.e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00b0
            int r0 = android.os.Build.VERSION.SDK_INT
            r6 = 29
            if (r0 >= r6) goto L_0x00b0
            java.lang.String r0 = "888888888888888"
            com.loc.es.e = r0
            goto L_0x00b0
        L_0x00ae:
            r22 = r6
        L_0x00b0:
            android.net.NetworkInfo r0 = r34.getActiveNetworkInfo()     // Catch:{ Throwable -> 0x00b7 }
            r23 = r0
            goto L_0x00c2
        L_0x00b7:
            r0 = move-exception
            r7 = r0
            java.lang.String r0 = "Aps"
            java.lang.String r6 = "getApsReq part"
            com.loc.es.a(r7, r0, r6)
            r23 = 0
        L_0x00c2:
            boolean r0 = r33.a((android.net.ConnectivityManager) r34)
            int r6 = com.loc.fa.a((android.net.NetworkInfo) r23)
            r7 = -1
            if (r6 == r7) goto L_0x00d9
            java.lang.String r6 = com.loc.fa.b((android.telephony.TelephonyManager) r16)
            if (r0 == 0) goto L_0x00d6
            java.lang.String r7 = "2"
            goto L_0x00dd
        L_0x00d6:
            java.lang.String r7 = "1"
            goto L_0x00dd
        L_0x00d9:
            r6 = r17
            r7 = r18
        L_0x00dd:
            boolean r16 = r8.isEmpty()
            r24 = r7
            if (r16 != 0) goto L_0x0228
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            switch(r15) {
                case 1: goto L_0x0180;
                case 2: goto L_0x00f5;
                default: goto L_0x00ed;
            }
        L_0x00ed:
            r26 = r5
            r25 = r6
            r27 = r19
            goto L_0x021d
        L_0x00f5:
            r15 = 0
            java.lang.Object r8 = r8.get(r15)
            com.loc.ed r8 = (com.loc.ed) r8
            r25 = r6
            int r6 = r7.length()
            r7.delete(r15, r6)
            java.lang.String r6 = "<mcc>"
            r7.append(r6)
            int r6 = r8.f6578a
            r7.append(r6)
            java.lang.String r6 = "</mcc>"
            r7.append(r6)
            java.lang.String r6 = "<sid>"
            r7.append(r6)
            int r6 = r8.g
            r7.append(r6)
            java.lang.String r6 = "</sid>"
            r7.append(r6)
            java.lang.String r6 = "<nid>"
            r7.append(r6)
            int r6 = r8.h
            r7.append(r6)
            java.lang.String r6 = "</nid>"
            r7.append(r6)
            java.lang.String r6 = "<bid>"
            r7.append(r6)
            int r6 = r8.i
            r7.append(r6)
            java.lang.String r6 = "</bid>"
            r7.append(r6)
            int r6 = r8.f
            if (r6 <= 0) goto L_0x0167
            int r6 = r8.e
            if (r6 <= 0) goto L_0x0167
            java.lang.String r6 = "<lon>"
            r7.append(r6)
            int r6 = r8.f
            r7.append(r6)
            java.lang.String r6 = "</lon>"
            r7.append(r6)
            java.lang.String r6 = "<lat>"
            r7.append(r6)
            int r6 = r8.e
            r7.append(r6)
            java.lang.String r6 = "</lat>"
            r7.append(r6)
        L_0x0167:
            java.lang.String r6 = "<signal>"
            r7.append(r6)
            int r6 = r8.j
            r7.append(r6)
            java.lang.String r6 = "</signal>"
            r7.append(r6)
            java.lang.String r6 = r7.toString()
            r26 = r5
            r27 = r6
            goto L_0x021d
        L_0x0180:
            r25 = r6
            r6 = 0
            java.lang.Object r15 = r8.get(r6)
            com.loc.ed r15 = (com.loc.ed) r15
            r26 = r5
            int r5 = r7.length()
            r7.delete(r6, r5)
            java.lang.String r5 = "<mcc>"
            r7.append(r5)
            int r5 = r15.f6578a
            r7.append(r5)
            java.lang.String r5 = "</mcc>"
            r7.append(r5)
            java.lang.String r5 = "<mnc>"
            r7.append(r5)
            int r5 = r15.b
            r7.append(r5)
            java.lang.String r5 = "</mnc>"
            r7.append(r5)
            java.lang.String r5 = "<lac>"
            r7.append(r5)
            int r5 = r15.c
            r7.append(r5)
            java.lang.String r5 = "</lac>"
            r7.append(r5)
            java.lang.String r5 = "<cellid>"
            r7.append(r5)
            int r5 = r15.d
            r7.append(r5)
            java.lang.String r5 = "</cellid>"
            r7.append(r5)
            java.lang.String r5 = "<signal>"
            r7.append(r5)
            int r5 = r15.j
            r7.append(r5)
            java.lang.String r5 = "</signal>"
            r7.append(r5)
            java.lang.String r5 = r7.toString()
            r15 = 1
        L_0x01e2:
            int r6 = r8.size()
            if (r15 >= r6) goto L_0x021b
            java.lang.Object r6 = r8.get(r15)
            com.loc.ed r6 = (com.loc.ed) r6
            r27 = r5
            int r5 = r6.c
            r13.append(r5)
            java.lang.String r5 = ","
            r13.append(r5)
            int r5 = r6.d
            r13.append(r5)
            java.lang.String r5 = ","
            r13.append(r5)
            int r5 = r6.j
            r13.append(r5)
            int r5 = r8.size()
            r6 = 1
            int r5 = r5 - r6
            if (r15 >= r5) goto L_0x0216
            java.lang.String r5 = "*"
            r13.append(r5)
        L_0x0216:
            int r15 = r15 + 1
            r5 = r27
            goto L_0x01e2
        L_0x021b:
            r27 = r5
        L_0x021d:
            int r5 = r7.length()
            r6 = 0
            r7.delete(r6, r5)
            r5 = r27
            goto L_0x022e
        L_0x0228:
            r26 = r5
            r25 = r6
            r5 = r19
        L_0x022e:
            r6 = r14 & 4
            r7 = 4
            if (r6 != r7) goto L_0x0244
            boolean r6 = r9.isEmpty()
            if (r6 != 0) goto L_0x0244
            java.util.ArrayList<com.loc.ed> r6 = r1.C
            r6.clear()
            java.util.ArrayList<com.loc.ed> r6 = r1.C
            r6.addAll(r9)
            goto L_0x0249
        L_0x0244:
            java.util.ArrayList<com.loc.ed> r6 = r1.C
            r6.clear()
        L_0x0249:
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r7 = r33
            boolean r8 = r7.p
            if (r8 == 0) goto L_0x02b8
            if (r0 == 0) goto L_0x02a7
            android.net.wifi.WifiInfo r0 = r33.g()
            boolean r7 = com.loc.eg.a((android.net.wifi.WifiInfo) r0)
            if (r7 == 0) goto L_0x02a7
            java.lang.String r7 = r0.getBSSID()
            r6.append(r7)
            java.lang.String r7 = ","
            r6.append(r7)
            int r7 = r0.getRssi()
            r8 = -128(0xffffffffffffff80, float:NaN)
            if (r7 >= r8) goto L_0x0276
        L_0x0274:
            r7 = 0
            goto L_0x027b
        L_0x0276:
            r8 = 127(0x7f, float:1.78E-43)
            if (r7 <= r8) goto L_0x027b
            goto L_0x0274
        L_0x027b:
            r6.append(r7)
            java.lang.String r7 = ","
            r6.append(r7)
            java.lang.String r7 = r0.getSSID()
            r8 = 32
            java.lang.String r0 = r0.getSSID()     // Catch:{ Exception -> 0x0295 }
            java.lang.String r9 = "UTF-8"
            byte[] r0 = r0.getBytes(r9)     // Catch:{ Exception -> 0x0295 }
            int r0 = r0.length     // Catch:{ Exception -> 0x0295 }
            goto L_0x0297
        L_0x0295:
            r0 = 32
        L_0x0297:
            if (r0 < r8) goto L_0x029c
            java.lang.String r7 = "unkwn"
        L_0x029c:
            java.lang.String r0 = "*"
            java.lang.String r8 = "."
            java.lang.String r0 = r7.replace(r0, r8)
            r6.append(r0)
        L_0x02a7:
            if (r10 == 0) goto L_0x02c4
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            if (r0 == 0) goto L_0x02c4
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            r0.clear()
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            r0.addAll(r10)
            goto L_0x02c4
        L_0x02b8:
            r33.d()
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            if (r0 == 0) goto L_0x02c4
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            r0.clear()
        L_0x02c4:
            r0 = 0
            r1.b = r0
            if (r30 != 0) goto L_0x02d0
            short r0 = r1.b
            r7 = 2
            r0 = r0 | r7
            short r0 = (short) r0
            r1.b = r0
        L_0x02d0:
            r1.c = r11
            r1.d = r12
            java.lang.String r0 = com.loc.fa.e()
            r1.f = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r7 = "android"
            r0.<init>(r7)
            java.lang.String r7 = com.loc.fa.f()
            r0.append(r7)
            java.lang.String r0 = r0.toString()
            r1.g = r0
            java.lang.String r0 = com.loc.fa.b((android.content.Context) r29)
            r1.h = r0
            r7 = r21
            r1.i = r7
            r1.j = r2
            java.lang.String r0 = "0"
            r1.k = r0
            r1.l = r3
            r1.m = r4
            r2 = r26
            r1.n = r2
            r2 = r22
            r1.o = r2
            java.lang.String r0 = com.loc.es.d
            r1.p = r0
            java.lang.String r0 = com.loc.es.e
            r1.q = r0
            java.lang.String r0 = java.lang.String.valueOf(r20)
            r1.s = r0
            java.lang.String r0 = com.loc.fa.j(r29)
            r1.t = r0
            java.lang.String r0 = "4.7.1"
            r1.v = r0
            r2 = r35
            r1.w = r2
            java.lang.String r0 = ""
            r1.u = r0
            r0 = r25
            r1.x = r0
            r7 = r24
            r1.y = r7
            r1.z = r14
            r1.A = r5
            java.lang.String r0 = r13.toString()
            r1.B = r0
            java.lang.String r0 = r32.k()
            r1.D = r0
            java.lang.String r0 = com.loc.eg.k()
            r1.G = r0
            java.lang.String r0 = r6.toString()
            r1.E = r0
            java.lang.String r0 = J     // Catch:{ Throwable -> 0x035c }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x035c }
            if (r0 == 0) goto L_0x035c
            java.lang.String r0 = com.loc.x.g(r29)     // Catch:{ Throwable -> 0x035c }
            J = r0     // Catch:{ Throwable -> 0x035c }
        L_0x035c:
            java.lang.String r0 = L     // Catch:{ Throwable -> 0x036a }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x036a }
            if (r0 == 0) goto L_0x036a
            java.lang.String r0 = com.loc.x.a((android.content.Context) r29)     // Catch:{ Throwable -> 0x036a }
            L = r0     // Catch:{ Throwable -> 0x036a }
        L_0x036a:
            java.lang.String r0 = r1.N     // Catch:{ Throwable -> 0x0378 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0378 }
            if (r0 == 0) goto L_0x0378
            java.lang.String r0 = com.loc.x.h(r29)     // Catch:{ Throwable -> 0x0378 }
            r1.N = r0     // Catch:{ Throwable -> 0x0378 }
        L_0x0378:
            int r0 = r13.length()
            r2 = 0
            r13.delete(r2, r0)
            int r0 = r6.length()
            r6.delete(r2, r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eq.a(android.content.Context, boolean, boolean, com.loc.ee, com.loc.eg, android.net.ConnectivityManager, java.lang.String, java.lang.String):void");
    }

    /* JADX WARNING: Removed duplicated region for block: B:140:0x02c4  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x02f5  */
    /* JADX WARNING: Removed duplicated region for block: B:150:0x0369  */
    /* JADX WARNING: Removed duplicated region for block: B:162:0x03c4  */
    /* JADX WARNING: Removed duplicated region for block: B:176:0x03fb A[ADDED_TO_REGION] */
    /* JADX WARNING: Removed duplicated region for block: B:214:0x04fa  */
    /* JADX WARNING: Removed duplicated region for block: B:216:0x04fe  */
    /* JADX WARNING: Removed duplicated region for block: B:222:0x051f  */
    /* JADX WARNING: Removed duplicated region for block: B:224:0x0524  */
    /* JADX WARNING: Removed duplicated region for block: B:249:0x05a7  */
    /* JADX WARNING: Removed duplicated region for block: B:250:0x05ac  */
    /* JADX WARNING: Removed duplicated region for block: B:285:0x0661 A[Catch:{ Throwable -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:287:0x0664 A[Catch:{ Throwable -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:288:0x0667 A[Catch:{ Throwable -> 0x0674 }] */
    /* JADX WARNING: Removed duplicated region for block: B:297:0x0685 A[Catch:{ Throwable -> 0x06a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:301:0x0696 A[SYNTHETIC, Splitter:B:301:0x0696] */
    /* JADX WARNING: Removed duplicated region for block: B:318:0x06c2  */
    /* JADX WARNING: Removed duplicated region for block: B:319:0x06c6  */
    /* JADX WARNING: Removed duplicated region for block: B:322:0x06d4  */
    /* JADX WARNING: Removed duplicated region for block: B:325:0x06ee  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final byte[] a() {
        /*
            r21 = this;
            r1 = r21
            java.lang.String r0 = r1.f6593a
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = ""
            r1.f6593a = r0
        L_0x000e:
            java.lang.String r0 = r1.c
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x001a
            java.lang.String r0 = ""
            r1.c = r0
        L_0x001a:
            java.lang.String r0 = r1.d
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0026
            java.lang.String r0 = ""
            r1.d = r0
        L_0x0026:
            java.lang.String r0 = r1.e
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0032
            java.lang.String r0 = ""
            r1.e = r0
        L_0x0032:
            java.lang.String r0 = r1.f
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x003e
            java.lang.String r0 = ""
            r1.f = r0
        L_0x003e:
            java.lang.String r0 = r1.g
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x004a
            java.lang.String r0 = ""
            r1.g = r0
        L_0x004a:
            java.lang.String r0 = r1.h
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0056
            java.lang.String r0 = ""
            r1.h = r0
        L_0x0056:
            java.lang.String r0 = r1.i
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0062
            java.lang.String r0 = ""
            r1.i = r0
        L_0x0062:
            java.lang.String r0 = r1.j
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x006f
        L_0x006a:
            java.lang.String r0 = "0"
            r1.j = r0
            goto L_0x0084
        L_0x006f:
            java.lang.String r0 = "0"
            java.lang.String r2 = r1.j
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0084
            java.lang.String r0 = "2"
            java.lang.String r2 = r1.j
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0084
            goto L_0x006a
        L_0x0084:
            java.lang.String r0 = r1.k
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0091
        L_0x008c:
            java.lang.String r0 = "0"
            r1.k = r0
            goto L_0x00a6
        L_0x0091:
            java.lang.String r0 = "0"
            java.lang.String r2 = r1.k
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x00a6
            java.lang.String r0 = "1"
            java.lang.String r2 = r1.k
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x00a6
            goto L_0x008c
        L_0x00a6:
            java.lang.String r0 = r1.l
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00b2
            java.lang.String r0 = ""
            r1.l = r0
        L_0x00b2:
            java.lang.String r0 = r1.m
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00be
            java.lang.String r0 = ""
            r1.m = r0
        L_0x00be:
            java.lang.String r0 = r1.n
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00ca
            java.lang.String r0 = ""
            r1.n = r0
        L_0x00ca:
            java.lang.String r0 = r1.o
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00d6
            java.lang.String r0 = ""
            r1.o = r0
        L_0x00d6:
            java.lang.String r0 = r1.p
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00e2
            java.lang.String r0 = ""
            r1.p = r0
        L_0x00e2:
            java.lang.String r0 = r1.q
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00ee
            java.lang.String r0 = ""
            r1.q = r0
        L_0x00ee:
            java.lang.String r0 = r1.r
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x00fa
            java.lang.String r0 = ""
            r1.r = r0
        L_0x00fa:
            java.lang.String r0 = r1.s
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0106
            java.lang.String r0 = ""
            r1.s = r0
        L_0x0106:
            java.lang.String r0 = r1.t
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0112
            java.lang.String r0 = ""
            r1.t = r0
        L_0x0112:
            java.lang.String r0 = r1.u
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x011e
            java.lang.String r0 = ""
            r1.u = r0
        L_0x011e:
            java.lang.String r0 = r1.v
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x012a
            java.lang.String r0 = ""
            r1.v = r0
        L_0x012a:
            java.lang.String r0 = r1.w
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0136
            java.lang.String r0 = ""
            r1.w = r0
        L_0x0136:
            java.lang.String r0 = r1.x
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0142
            java.lang.String r0 = ""
            r1.x = r0
        L_0x0142:
            java.lang.String r0 = r1.y
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x014f
        L_0x014a:
            java.lang.String r0 = "0"
            r1.y = r0
            goto L_0x0164
        L_0x014f:
            java.lang.String r0 = "1"
            java.lang.String r2 = r1.y
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0164
            java.lang.String r0 = "2"
            java.lang.String r2 = r1.y
            boolean r0 = r0.equals(r2)
            if (r0 != 0) goto L_0x0164
            goto L_0x014a
        L_0x0164:
            int r0 = r1.z
            r2 = 1
            r3 = 0
            if (r0 <= 0) goto L_0x0171
            r4 = 15
            if (r0 <= r4) goto L_0x016f
            goto L_0x0171
        L_0x016f:
            r0 = 1
            goto L_0x0172
        L_0x0171:
            r0 = 0
        L_0x0172:
            if (r0 != 0) goto L_0x0176
            r1.z = r3
        L_0x0176:
            java.lang.String r0 = r1.A
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x0182
            java.lang.String r0 = ""
            r1.A = r0
        L_0x0182:
            java.lang.String r0 = r1.B
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x018e
            java.lang.String r0 = ""
            r1.B = r0
        L_0x018e:
            java.lang.String r0 = r1.E
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x019a
            java.lang.String r0 = ""
            r1.E = r0
        L_0x019a:
            java.lang.String r0 = r1.G
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01a6
            java.lang.String r0 = ""
            r1.G = r0
        L_0x01a6:
            java.lang.String r0 = r1.H
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01b2
            java.lang.String r0 = ""
            r1.H = r0
        L_0x01b2:
            java.lang.String r0 = J
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01be
            java.lang.String r0 = ""
            J = r0
        L_0x01be:
            byte[] r0 = r1.I
            if (r0 != 0) goto L_0x01c6
            byte[] r0 = new byte[r3]
            r1.I = r0
        L_0x01c6:
            java.lang.String r0 = r1.N
            boolean r0 = android.text.TextUtils.isEmpty(r0)
            if (r0 == 0) goto L_0x01d2
            java.lang.String r0 = ""
            r1.N = r0
        L_0x01d2:
            r4 = 2
            byte[] r5 = new byte[r4]
            r6 = 4
            byte[] r7 = new byte[r6]
            byte[] r0 = r1.I
            r8 = 4096(0x1000, float:5.74E-42)
            if (r0 == 0) goto L_0x01e3
            byte[] r0 = r1.I
            int r0 = r0.length
            int r0 = r0 + r2
            int r8 = r8 + r0
        L_0x01e3:
            byte[] r0 = r1.O
            if (r0 == 0) goto L_0x01ef
            int r0 = r1.P
            if (r8 <= r0) goto L_0x01ec
            goto L_0x01ef
        L_0x01ec:
            byte[] r0 = r1.O
            goto L_0x01f5
        L_0x01ef:
            byte[] r0 = new byte[r8]
            r1.O = r0
            r1.P = r8
        L_0x01f5:
            r8 = r0
            java.lang.String r0 = r1.f6593a
            byte r0 = com.loc.fa.i((java.lang.String) r0)
            r8[r3] = r0
            short r0 = r1.b
            r9 = 0
            byte[] r0 = com.loc.fa.a((int) r0, (byte[]) r9)
            int r10 = r0.length
            java.lang.System.arraycopy(r0, r3, r8, r2, r10)
            int r0 = r0.length
            int r0 = r0 + r2
            java.lang.String r10 = r1.c
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.d
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.o
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.e
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.f
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.g
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.u
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.h
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.p
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.q
            int r10 = a(r10, r8, r0)
            java.lang.String r0 = r1.t     // Catch:{ Throwable -> 0x0265 }
            boolean r0 = android.text.TextUtils.isEmpty(r0)     // Catch:{ Throwable -> 0x0265 }
            if (r0 == 0) goto L_0x0252
            r8[r10] = r3     // Catch:{ Throwable -> 0x0265 }
            goto L_0x026f
        L_0x0252:
            java.lang.String r0 = r1.t     // Catch:{ Throwable -> 0x0265 }
            byte[] r0 = r1.a(r0)     // Catch:{ Throwable -> 0x0265 }
            int r11 = r0.length     // Catch:{ Throwable -> 0x0265 }
            byte r11 = (byte) r11     // Catch:{ Throwable -> 0x0265 }
            r8[r10] = r11     // Catch:{ Throwable -> 0x0265 }
            int r10 = r10 + 1
            int r11 = r0.length     // Catch:{ Throwable -> 0x0265 }
            java.lang.System.arraycopy(r0, r3, r8, r10, r11)     // Catch:{ Throwable -> 0x0265 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x0265 }
            int r10 = r10 + r0
            goto L_0x0270
        L_0x0265:
            r0 = move-exception
            java.lang.String r11 = "Req"
            java.lang.String r12 = "buildV4Dot219"
            com.loc.es.a(r0, r11, r12)
            r8[r10] = r3
        L_0x026f:
            int r10 = r10 + r2
        L_0x0270:
            java.lang.String r0 = r1.v
            int r0 = a(r0, r8, r10)
            java.lang.String r10 = r1.w
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = J
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = L
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.x
            int r0 = a(r10, r8, r0)
            java.lang.String r10 = r1.y
            byte r10 = java.lang.Byte.parseByte(r10)
            r8[r0] = r10
            int r0 = r0 + r2
            java.lang.String r10 = r1.j
            byte r10 = java.lang.Byte.parseByte(r10)
            r8[r0] = r10
            int r0 = r0 + r2
            int r10 = r1.z
            r11 = 3
            r10 = r10 & r11
            int r12 = r1.z
            byte r12 = (byte) r12
            r8[r0] = r12
            int r0 = r0 + r2
            r12 = -128(0xffffffffffffff80, float:NaN)
            r13 = 127(0x7f, float:1.78E-43)
            if (r10 == r2) goto L_0x02b2
            if (r10 != r4) goto L_0x03c9
        L_0x02b2:
            java.lang.String r14 = "mcc"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            if (r10 != r2) goto L_0x02f5
            java.lang.String r14 = "mnc"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "lac"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "cellid"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.c((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
        L_0x02f3:
            int r0 = r0 + r14
            goto L_0x0348
        L_0x02f5:
            if (r10 != r4) goto L_0x0348
            java.lang.String r14 = "sid"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "nid"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "bid"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.b((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "lon"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.c((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            int r0 = r0 + r14
            java.lang.String r14 = "lat"
            java.lang.String r14 = r1.b(r14)
            byte[] r14 = com.loc.fa.c((java.lang.String) r14)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r14 = r14.length
            goto L_0x02f3
        L_0x0348:
            java.lang.String r14 = "signal"
            java.lang.String r14 = r1.b(r14)
            int r14 = java.lang.Integer.parseInt(r14)
            if (r14 <= r13) goto L_0x0357
        L_0x0355:
            r14 = 0
            goto L_0x035a
        L_0x0357:
            if (r14 >= r12) goto L_0x035a
            goto L_0x0355
        L_0x035a:
            byte r14 = (byte) r14
            r8[r0] = r14
            int r0 = r0 + r2
            byte[] r14 = com.loc.fa.a((int) r3, (byte[]) r5)
            int r15 = r14.length
            java.lang.System.arraycopy(r14, r3, r8, r0, r15)
            int r0 = r0 + r4
            if (r10 != r2) goto L_0x03c4
            java.lang.String r10 = r1.B
            boolean r10 = android.text.TextUtils.isEmpty(r10)
            if (r10 == 0) goto L_0x0376
            r8[r0] = r3
        L_0x0373:
            int r0 = r0 + 1
            goto L_0x03c9
        L_0x0376:
            java.lang.String r10 = r1.B
            java.lang.String r14 = "\\*"
            java.lang.String[] r10 = r10.split(r14)
            int r10 = r10.length
            byte r14 = (byte) r10
            r8[r0] = r14
            int r0 = r0 + 1
            r14 = r0
            r0 = 0
        L_0x0386:
            if (r0 >= r10) goto L_0x03c2
            java.lang.String r15 = "lac"
            java.lang.String r15 = r1.a(r15, r0)
            byte[] r15 = com.loc.fa.b((java.lang.String) r15)
            int r9 = r15.length
            java.lang.System.arraycopy(r15, r3, r8, r14, r9)
            int r9 = r15.length
            int r14 = r14 + r9
            java.lang.String r9 = "cellid"
            java.lang.String r9 = r1.a(r9, r0)
            byte[] r9 = com.loc.fa.c((java.lang.String) r9)
            int r15 = r9.length
            java.lang.System.arraycopy(r9, r3, r8, r14, r15)
            int r9 = r9.length
            int r14 = r14 + r9
            java.lang.String r9 = "signal"
            java.lang.String r9 = r1.a(r9, r0)
            int r9 = java.lang.Integer.parseInt(r9)
            if (r9 <= r13) goto L_0x03b7
        L_0x03b5:
            r9 = 0
            goto L_0x03ba
        L_0x03b7:
            if (r9 >= r12) goto L_0x03ba
            goto L_0x03b5
        L_0x03ba:
            byte r9 = (byte) r9
            r8[r14] = r9
            int r14 = r14 + r2
            int r0 = r0 + 1
            r9 = 0
            goto L_0x0386
        L_0x03c2:
            r0 = r14
            goto L_0x03c9
        L_0x03c4:
            if (r10 != r4) goto L_0x03c9
            r8[r0] = r3
            goto L_0x0373
        L_0x03c9:
            java.lang.String r9 = r1.D
            r10 = 8
            if (r9 == 0) goto L_0x03eb
            int r14 = r1.z
            r14 = r14 & r10
            if (r14 != r10) goto L_0x03eb
            java.lang.String r14 = "GBK"
            byte[] r9 = r9.getBytes(r14)     // Catch:{ Exception -> 0x03eb }
            int r14 = r9.length     // Catch:{ Exception -> 0x03eb }
            r15 = 60
            int r14 = java.lang.Math.min(r14, r15)     // Catch:{ Exception -> 0x03eb }
            byte r15 = (byte) r14     // Catch:{ Exception -> 0x03eb }
            r8[r0] = r15     // Catch:{ Exception -> 0x03eb }
            int r0 = r0 + 1
            java.lang.System.arraycopy(r9, r3, r8, r0, r14)     // Catch:{ Exception -> 0x03eb }
            int r0 = r0 + r14
            goto L_0x03ee
        L_0x03eb:
            r8[r0] = r3
            int r0 = r0 + r2
        L_0x03ee:
            java.util.ArrayList<com.loc.ed> r9 = r1.C
            int r14 = r9.size()
            int r15 = r1.z
            r15 = r15 & r6
            r16 = 4617315517961601024(0x4014000000000000, double:5.0)
            if (r15 != r6) goto L_0x0513
            if (r14 <= 0) goto L_0x0513
            java.lang.Object r15 = r9.get(r3)
            com.loc.ed r15 = (com.loc.ed) r15
            boolean r15 = r15.p
            if (r15 != 0) goto L_0x0409
            int r14 = r14 + -1
        L_0x0409:
            byte r15 = (byte) r14
            r8[r0] = r15
            int r0 = r0 + r2
            r15 = r0
            r0 = 0
        L_0x040f:
            if (r0 >= r14) goto L_0x0517
            java.lang.Object r18 = r9.get(r0)
            r10 = r18
            com.loc.ed r10 = (com.loc.ed) r10
            boolean r12 = r10.p
            if (r12 == 0) goto L_0x050a
            int r12 = r10.k
            if (r12 == r2) goto L_0x0484
            int r12 = r10.k
            if (r12 == r11) goto L_0x0484
            int r12 = r10.k
            if (r12 != r6) goto L_0x042a
            goto L_0x0484
        L_0x042a:
            int r12 = r10.k
            if (r12 != r4) goto L_0x04c2
            int r12 = r10.k
            byte r12 = (byte) r12
            boolean r4 = r10.n
            if (r4 == 0) goto L_0x0438
            r4 = r12 | 8
            byte r12 = (byte) r4
        L_0x0438:
            r8[r15] = r12
            int r15 = r15 + 1
            int r4 = r10.f6578a
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.g
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.h
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.i
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.f
            byte[] r4 = com.loc.fa.b((int) r4, (byte[]) r7)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.e
            byte[] r4 = com.loc.fa.b((int) r4, (byte[]) r7)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            goto L_0x04c1
        L_0x0484:
            int r4 = r10.k
            byte r4 = (byte) r4
            boolean r12 = r10.n
            if (r12 == 0) goto L_0x048e
            r4 = r4 | 8
            byte r4 = (byte) r4
        L_0x048e:
            r8[r15] = r4
            int r15 = r15 + 1
            int r4 = r10.f6578a
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.b
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.c
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            int r4 = r10.d
            byte[] r4 = com.loc.fa.b((int) r4, (byte[]) r7)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
        L_0x04c1:
            int r15 = r15 + r4
        L_0x04c2:
            int r4 = r10.j
            if (r4 <= r13) goto L_0x04c9
        L_0x04c6:
            r4 = 99
            goto L_0x04ce
        L_0x04c9:
            r12 = -128(0xffffffffffffff80, float:NaN)
            if (r4 >= r12) goto L_0x04ce
            goto L_0x04c6
        L_0x04ce:
            byte r4 = (byte) r4
            r8[r15] = r4
            int r15 = r15 + r2
            short r4 = r10.l
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r12 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r12)
            int r4 = r4.length
            int r15 = r15 + r4
            java.lang.String r4 = "5.1"
            java.lang.Double r4 = java.lang.Double.valueOf(r4)
            double r19 = r4.doubleValue()
            int r4 = (r19 > r16 ? 1 : (r19 == r16 ? 0 : -1))
            if (r4 < 0) goto L_0x050a
            int r4 = r10.k
            if (r4 == r11) goto L_0x04f4
            int r4 = r10.k
            if (r4 != r6) goto L_0x050a
        L_0x04f4:
            int r4 = r10.o
            r10 = 32767(0x7fff, float:4.5916E-41)
            if (r4 <= r10) goto L_0x04fc
            r4 = 32767(0x7fff, float:4.5916E-41)
        L_0x04fc:
            if (r4 >= 0) goto L_0x0500
            r4 = 32767(0x7fff, float:4.5916E-41)
        L_0x0500:
            byte[] r4 = com.loc.fa.a((int) r4, (byte[]) r5)
            int r10 = r4.length
            java.lang.System.arraycopy(r4, r3, r8, r15, r10)
            int r4 = r4.length
            int r15 = r15 + r4
        L_0x050a:
            int r0 = r0 + 1
            r4 = 2
            r10 = 8
            r12 = -128(0xffffffffffffff80, float:NaN)
            goto L_0x040f
        L_0x0513:
            r8[r0] = r3
            int r15 = r0 + 1
        L_0x0517:
            java.lang.String r0 = r1.E
            int r0 = r0.length()
            if (r0 != 0) goto L_0x0524
            r8[r15] = r3
        L_0x0521:
            int r15 = r15 + r2
            goto L_0x0599
        L_0x0524:
            r8[r15] = r2
            int r15 = r15 + r2
            java.lang.String r0 = r1.E     // Catch:{ Throwable -> 0x0579 }
            java.lang.String r4 = ","
            java.lang.String[] r4 = r0.split(r4)     // Catch:{ Throwable -> 0x0579 }
            r0 = r4[r3]     // Catch:{ Throwable -> 0x0579 }
            byte[] r0 = r1.a(r0)     // Catch:{ Throwable -> 0x0579 }
            int r6 = r0.length     // Catch:{ Throwable -> 0x0579 }
            java.lang.System.arraycopy(r0, r3, r8, r15, r6)     // Catch:{ Throwable -> 0x0579 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x0579 }
            int r15 = r15 + r0
            r6 = 2
            r0 = r4[r6]     // Catch:{ Throwable -> 0x0553 }
            java.lang.String r6 = "GBK"
            byte[] r0 = r0.getBytes(r6)     // Catch:{ Throwable -> 0x0553 }
            int r6 = r0.length     // Catch:{ Throwable -> 0x0553 }
            if (r6 <= r13) goto L_0x0549
            r6 = 127(0x7f, float:1.78E-43)
        L_0x0549:
            byte r7 = (byte) r6     // Catch:{ Throwable -> 0x0553 }
            r8[r15] = r7     // Catch:{ Throwable -> 0x0553 }
            int r15 = r15 + 1
            java.lang.System.arraycopy(r0, r3, r8, r15, r6)     // Catch:{ Throwable -> 0x0553 }
            int r15 = r15 + r6
            goto L_0x055f
        L_0x0553:
            r0 = move-exception
            java.lang.String r6 = "Req"
            java.lang.String r7 = "buildV4Dot214"
            com.loc.es.a(r0, r6, r7)     // Catch:{ Throwable -> 0x0579 }
            r8[r15] = r3     // Catch:{ Throwable -> 0x0579 }
            int r15 = r15 + 1
        L_0x055f:
            r0 = r4[r2]     // Catch:{ Throwable -> 0x0579 }
            int r0 = java.lang.Integer.parseInt(r0)     // Catch:{ Throwable -> 0x0579 }
            if (r0 <= r13) goto L_0x0569
        L_0x0567:
            r0 = 0
            goto L_0x056e
        L_0x0569:
            r4 = -128(0xffffffffffffff80, float:NaN)
            if (r0 >= r4) goto L_0x056e
            goto L_0x0567
        L_0x056e:
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ Throwable -> 0x0579 }
            byte r0 = java.lang.Byte.parseByte(r0)     // Catch:{ Throwable -> 0x0579 }
            r8[r15] = r0     // Catch:{ Throwable -> 0x0579 }
            goto L_0x0521
        L_0x0579:
            r0 = move-exception
            java.lang.String r4 = "Req"
            java.lang.String r6 = "buildV4Dot216"
            com.loc.es.a(r0, r4, r6)
            java.lang.String r0 = "00:00:00:00:00:00"
            byte[] r0 = r1.a(r0)
            int r4 = r0.length
            java.lang.System.arraycopy(r0, r3, r8, r15, r4)
            int r0 = r0.length
            int r15 = r15 + r0
            r8[r15] = r3
            int r15 = r15 + r2
            java.lang.String r0 = "0"
            byte r0 = java.lang.Byte.parseByte(r0)
            r8[r15] = r0
            goto L_0x0521
        L_0x0599:
            java.util.ArrayList<android.net.wifi.ScanResult> r0 = r1.F
            int r4 = r0.size()
            r6 = 25
            int r4 = java.lang.Math.min(r4, r6)
            if (r4 != 0) goto L_0x05ac
            r8[r15] = r3
            int r15 = r15 + r2
            goto L_0x0651
        L_0x05ac:
            byte r6 = (byte) r4
            r8[r15] = r6
            int r15 = r15 + r2
            int r6 = com.loc.fa.d()
            r7 = 17
            if (r6 < r7) goto L_0x05ba
            r6 = 1
            goto L_0x05bb
        L_0x05ba:
            r6 = 0
        L_0x05bb:
            r9 = 0
            if (r6 == 0) goto L_0x05c6
            long r9 = com.loc.fa.c()
            r11 = 1000(0x3e8, double:4.94E-321)
            long r9 = r9 / r11
        L_0x05c6:
            r7 = 0
        L_0x05c7:
            if (r7 >= r4) goto L_0x0641
            java.lang.Object r11 = r0.get(r7)
            android.net.wifi.ScanResult r11 = (android.net.wifi.ScanResult) r11
            java.lang.String r12 = r11.BSSID
            byte[] r12 = r1.a(r12)
            int r14 = r12.length
            java.lang.System.arraycopy(r12, r3, r8, r15, r14)
            int r12 = r12.length
            int r15 = r15 + r12
            java.lang.String r12 = r11.SSID     // Catch:{ Exception -> 0x05f0 }
            java.lang.String r14 = "GBK"
            byte[] r12 = r12.getBytes(r14)     // Catch:{ Exception -> 0x05f0 }
            int r14 = r12.length     // Catch:{ Exception -> 0x05f0 }
            byte r14 = (byte) r14     // Catch:{ Exception -> 0x05f0 }
            r8[r15] = r14     // Catch:{ Exception -> 0x05f0 }
            int r15 = r15 + 1
            int r14 = r12.length     // Catch:{ Exception -> 0x05f0 }
            java.lang.System.arraycopy(r12, r3, r8, r15, r14)     // Catch:{ Exception -> 0x05f0 }
            int r12 = r12.length     // Catch:{ Exception -> 0x05f0 }
            int r15 = r15 + r12
            goto L_0x05f3
        L_0x05f0:
            r8[r15] = r3
            int r15 = r15 + r2
        L_0x05f3:
            int r12 = r11.level
            if (r12 <= r13) goto L_0x05fb
            r12 = 0
            r14 = -128(0xffffffffffffff80, float:NaN)
            goto L_0x0600
        L_0x05fb:
            r14 = -128(0xffffffffffffff80, float:NaN)
            if (r12 >= r14) goto L_0x0600
            r12 = 0
        L_0x0600:
            java.lang.String r12 = java.lang.String.valueOf(r12)
            byte r12 = java.lang.Byte.parseByte(r12)
            r8[r15] = r12
            int r15 = r15 + r2
            if (r6 == 0) goto L_0x061d
            long r13 = r11.timestamp
            r19 = 1000000(0xf4240, double:4.940656E-318)
            long r13 = r13 / r19
            r19 = 1
            long r13 = r13 + r19
            long r13 = r9 - r13
            int r12 = (int) r13
            if (r12 >= 0) goto L_0x061e
        L_0x061d:
            r12 = 0
        L_0x061e:
            r13 = 65535(0xffff, float:9.1834E-41)
            if (r12 <= r13) goto L_0x0626
            r12 = 65535(0xffff, float:9.1834E-41)
        L_0x0626:
            byte[] r12 = com.loc.fa.a((int) r12, (byte[]) r5)
            int r13 = r12.length
            java.lang.System.arraycopy(r12, r3, r8, r15, r13)
            int r12 = r12.length
            int r15 = r15 + r12
            int r11 = r11.frequency
            byte[] r11 = com.loc.fa.a((int) r11, (byte[]) r5)
            int r12 = r11.length
            java.lang.System.arraycopy(r11, r3, r8, r15, r12)
            int r11 = r11.length
            int r15 = r15 + r11
            int r7 = r7 + 1
            r13 = 127(0x7f, float:1.78E-43)
            goto L_0x05c7
        L_0x0641:
            java.lang.String r0 = r1.G
            int r0 = java.lang.Integer.parseInt(r0)
            byte[] r0 = com.loc.fa.a((int) r0, (byte[]) r5)
            int r4 = r0.length
            java.lang.System.arraycopy(r0, r3, r8, r15, r4)
            int r0 = r0.length
            int r15 = r15 + r0
        L_0x0651:
            r8[r15] = r3
            int r15 = r15 + r2
            java.lang.String r0 = r1.H     // Catch:{ Throwable -> 0x0674 }
            java.lang.String r4 = "GBK"
            byte[] r9 = r0.getBytes(r4)     // Catch:{ Throwable -> 0x0674 }
            int r0 = r9.length     // Catch:{ Throwable -> 0x0674 }
            r4 = 127(0x7f, float:1.78E-43)
            if (r0 <= r4) goto L_0x0662
            r9 = 0
        L_0x0662:
            if (r9 != 0) goto L_0x0667
            r8[r15] = r3     // Catch:{ Throwable -> 0x0674 }
            goto L_0x0676
        L_0x0667:
            int r0 = r9.length     // Catch:{ Throwable -> 0x0674 }
            byte r0 = (byte) r0     // Catch:{ Throwable -> 0x0674 }
            r8[r15] = r0     // Catch:{ Throwable -> 0x0674 }
            int r15 = r15 + 1
            int r0 = r9.length     // Catch:{ Throwable -> 0x0674 }
            java.lang.System.arraycopy(r9, r3, r8, r15, r0)     // Catch:{ Throwable -> 0x0674 }
            int r0 = r9.length     // Catch:{ Throwable -> 0x0674 }
            int r15 = r15 + r0
            goto L_0x0677
        L_0x0674:
            r8[r15] = r3
        L_0x0676:
            int r15 = r15 + r2
        L_0x0677:
            r2 = 2
            byte[] r0 = new byte[r2]
            r0 = {0, 0} // fill-array
            java.lang.String r2 = r1.K     // Catch:{ Throwable -> 0x06a6 }
            boolean r2 = android.text.TextUtils.isEmpty(r2)     // Catch:{ Throwable -> 0x06a6 }
            if (r2 != 0) goto L_0x068f
            java.lang.String r0 = r1.K     // Catch:{ Throwable -> 0x06a6 }
            int r0 = r0.length()     // Catch:{ Throwable -> 0x06a6 }
            byte[] r0 = com.loc.fa.a((int) r0, (byte[]) r5)     // Catch:{ Throwable -> 0x06a6 }
        L_0x068f:
            r4 = 2
            java.lang.System.arraycopy(r0, r3, r8, r15, r4)     // Catch:{ Throwable -> 0x06a6 }
            int r15 = r15 + r4
            if (r2 != 0) goto L_0x06a4
            java.lang.String r0 = r1.K     // Catch:{ Throwable -> 0x06a4 }
            java.lang.String r2 = "GBK"
            byte[] r0 = r0.getBytes(r2)     // Catch:{ Throwable -> 0x06a4 }
            int r2 = r0.length     // Catch:{ Throwable -> 0x06a4 }
            java.lang.System.arraycopy(r0, r3, r8, r15, r2)     // Catch:{ Throwable -> 0x06a4 }
            int r0 = r0.length     // Catch:{ Throwable -> 0x06a4 }
            int r15 = r15 + r0
        L_0x06a4:
            r2 = 2
            goto L_0x06a8
        L_0x06a6:
            r2 = 2
            int r15 = r15 + r2
        L_0x06a8:
            byte[] r0 = new byte[r2]
            r0 = {0, 0} // fill-array
            byte[] r0 = com.loc.fa.a((int) r3, (byte[]) r5)     // Catch:{ Throwable -> 0x06b4 }
            java.lang.System.arraycopy(r0, r3, r8, r15, r2)     // Catch:{ Throwable -> 0x06b4 }
        L_0x06b4:
            int r15 = r15 + r2
            byte[] r0 = new byte[r2]
            r0 = {0, 0} // fill-array
            java.lang.System.arraycopy(r0, r3, r8, r15, r2)     // Catch:{ Throwable -> 0x06bd }
        L_0x06bd:
            int r15 = r15 + r2
            byte[] r0 = r1.I
            if (r0 == 0) goto L_0x06c6
            byte[] r0 = r1.I
            int r0 = r0.length
            goto L_0x06c7
        L_0x06c6:
            r0 = 0
        L_0x06c7:
            r2 = 0
            byte[] r2 = com.loc.fa.a((int) r0, (byte[]) r2)
            int r4 = r2.length
            java.lang.System.arraycopy(r2, r3, r8, r15, r4)
            int r2 = r2.length
            int r15 = r15 + r2
            if (r0 <= 0) goto L_0x06e0
            byte[] r0 = r1.I
            byte[] r2 = r1.I
            int r2 = r2.length
            java.lang.System.arraycopy(r0, r3, r8, r15, r2)
            byte[] r0 = r1.I
            int r0 = r0.length
            int r15 = r15 + r0
        L_0x06e0:
            java.lang.String r0 = "5.1"
            java.lang.Double r0 = java.lang.Double.valueOf(r0)
            double r4 = r0.doubleValue()
            int r0 = (r4 > r16 ? 1 : (r4 == r16 ? 0 : -1))
            if (r0 < 0) goto L_0x06f8
            r8[r15] = r3
            int r15 = r15 + 1
            java.lang.String r0 = r1.N
            int r15 = a(r0, r8, r15)
        L_0x06f8:
            byte[] r0 = new byte[r15]
            java.lang.System.arraycopy(r8, r3, r0, r3, r15)
            java.util.zip.CRC32 r2 = new java.util.zip.CRC32
            r2.<init>()
            r2.update(r0)
            long r4 = r2.getValue()
            byte[] r2 = com.loc.fa.a((long) r4)
            int r4 = r15 + 8
            byte[] r4 = new byte[r4]
            java.lang.System.arraycopy(r0, r3, r4, r3, r15)
            r5 = 8
            java.lang.System.arraycopy(r2, r3, r4, r15, r5)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loc.eq.a():byte[]");
    }
}
