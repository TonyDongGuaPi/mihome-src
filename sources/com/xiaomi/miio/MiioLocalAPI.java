package com.xiaomi.miio;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MiioLocalAPI {

    /* renamed from: a  reason: collision with root package name */
    public static final String f11133a = "miio-localapi";
    public static final String b = "{\"id\":1,\"method\":\"miIO.info\",\"params\":\"\"}";
    private static final int c = 54321;
    private static final int d = 65535;
    private static final int e = 700;
    private static final int f = 2000;
    private static final int g = 3;
    private static final String h = "UTF-8";
    private static final int i = 32;
    private static final long j = -1;
    private static final int k = -1;
    private static final byte[] l = "ffffffffffffffffffffffffffffffff".getBytes();
    private static final byte[] m = "00000000000000000000000000000000".getBytes();
    private static final int n = 5007;
    private static final String o = "miio";
    private static final String p = "224.126.%s.%s";
    private static final int q = 30;
    private static boolean r = false;
    private static String s = null;
    private static final int t = Runtime.getRuntime().availableProcessors();
    private static final int u = (t + 1);
    private static final ExecutorService v = Executors.newFixedThreadPool(u);

    private static boolean a(long j2) {
        return ((int) ((j2 >> 32) & -1)) != 0;
    }

    private static void a(DatagramSocket datagramSocket, InetAddress inetAddress, byte[] bArr) throws IOException {
        datagramSocket.send(new DatagramPacket(bArr, bArr.length, inetAddress, c));
    }

    private static void a(MulticastSocket multicastSocket, String str) throws IOException {
        InetAddress byName = InetAddress.getByName(str);
        multicastSocket.joinGroup(byName);
        multicastSocket.send(new DatagramPacket("miio".getBytes(), "miio".length(), byName, n));
        multicastSocket.leaveGroup(byName);
    }

    private static void a(String str, MulticastSocket multicastSocket, byte[] bArr, boolean z) throws IOException, InterruptedException {
        a(multicastSocket, String.format(str, new Object[]{0, Integer.valueOf(bArr.length + 1)}));
        int i2 = 0;
        while (i2 < bArr.length) {
            int i3 = i2 + 1;
            a(multicastSocket, String.format(str, new Object[]{Integer.valueOf(i3), Integer.valueOf(bArr[i2] & 255)}));
            if (i2 != 0 && i2 % 10 == 0) {
                Thread.sleep(4);
                a(multicastSocket, String.format(str, new Object[]{0, Integer.valueOf(bArr.length + 1)}));
            }
            if (z) {
                Thread.sleep(4);
            }
            i2 = i3;
        }
    }

    private static String a(DatagramSocket datagramSocket, byte[] bArr, int i2) throws IOException {
        DatagramPacket datagramPacket = new DatagramPacket(bArr, bArr.length);
        datagramSocket.setSoTimeout(i2);
        datagramSocket.receive(datagramPacket);
        return datagramPacket.getAddress().getHostAddress();
    }

    public static void a(final MiioLocalResponse miioLocalResponse) {
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(miioLocalResponse);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0072 A[Catch:{ all -> 0x0080 }] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0083  */
    /* JADX WARNING: Removed duplicated region for block: B:29:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(com.xiaomi.miio.MiioLocalResponse r8) {
        /*
            r0 = 0
            java.net.MulticastSocket r1 = new java.net.MulticastSocket     // Catch:{ Exception -> 0x0043, all -> 0x0040 }
            r1.<init>()     // Catch:{ Exception -> 0x0043, all -> 0x0040 }
            r0 = 0
            r2 = 0
        L_0x0008:
            int r3 = r2 + 1
            r4 = 30
            if (r2 >= r4) goto L_0x0030
            java.lang.String r2 = "224.126.%s.%s"
            r4 = 2
            java.lang.Object[] r4 = new java.lang.Object[r4]     // Catch:{ Exception -> 0x002e }
            java.lang.Integer r5 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x002e }
            r4[r0] = r5     // Catch:{ Exception -> 0x002e }
            r5 = 1
            java.lang.Integer r6 = java.lang.Integer.valueOf(r5)     // Catch:{ Exception -> 0x002e }
            r4[r5] = r6     // Catch:{ Exception -> 0x002e }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ Exception -> 0x002e }
            a((java.net.MulticastSocket) r1, (java.lang.String) r2)     // Catch:{ Exception -> 0x002e }
            r4 = 10
            java.lang.Thread.sleep(r4)     // Catch:{ Exception -> 0x002e }
            r2 = r3
            goto L_0x0008
        L_0x002e:
            r0 = move-exception
            goto L_0x0047
        L_0x0030:
            if (r8 == 0) goto L_0x003c
            com.xiaomi.miio.MiioLocalResult r0 = new com.xiaomi.miio.MiioLocalResult     // Catch:{ Exception -> 0x002e }
            com.xiaomi.miio.MiioLocalErrorCode r2 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ Exception -> 0x002e }
            r0.<init>(r2)     // Catch:{ Exception -> 0x002e }
            r8.a(r0)     // Catch:{ Exception -> 0x002e }
        L_0x003c:
            r1.close()
            goto L_0x007f
        L_0x0040:
            r8 = move-exception
            r1 = r0
            goto L_0x0081
        L_0x0043:
            r1 = move-exception
            r7 = r1
            r1 = r0
            r0 = r7
        L_0x0047:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x0080 }
            r2.<init>()     // Catch:{ all -> 0x0080 }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x0080 }
            r3.<init>(r2)     // Catch:{ all -> 0x0080 }
            r0.printStackTrace(r3)     // Catch:{ all -> 0x0080 }
            java.lang.String r3 = "miio-localapi"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x0080 }
            r4.<init>()     // Catch:{ all -> 0x0080 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0080 }
            r4.append(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x0080 }
            r4.append(r0)     // Catch:{ all -> 0x0080 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x0080 }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x0080 }
            if (r8 == 0) goto L_0x007c
            com.xiaomi.miio.MiioLocalResult r0 = new com.xiaomi.miio.MiioLocalResult     // Catch:{ all -> 0x0080 }
            com.xiaomi.miio.MiioLocalErrorCode r2 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x0080 }
            r0.<init>(r2)     // Catch:{ all -> 0x0080 }
            r8.a(r0)     // Catch:{ all -> 0x0080 }
        L_0x007c:
            if (r1 == 0) goto L_0x007f
            goto L_0x003c
        L_0x007f:
            return
        L_0x0080:
            r8 = move-exception
        L_0x0081:
            if (r1 == 0) goto L_0x0086
            r1.close()
        L_0x0086:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.c(com.xiaomi.miio.MiioLocalResponse):void");
    }

    public static void a(String str, String str2, String str3, String str4, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str5, str6, str7, str8, miioLocalResponse2);
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str6, str7, str8, str9, str10, miioLocalResponse2);
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str7, str8, str9, str10, str11, str12, miioLocalResponse2);
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, long j2, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str5 = str;
        final String str6 = str2;
        final String str7 = str3;
        final String str8 = str4;
        final long j3 = j2;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str5, str6, str7, str8, j3, miioLocalResponse2);
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, long j2, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str3;
        final String str9 = str4;
        final String str10 = str5;
        final long j3 = j2;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str6, str7, str8, str9, str10, j3, miioLocalResponse2);
            }
        });
    }

    public static void a(String str, String str2, String str3, String str4, String str5, String str6, long j2, MiioLocalResponse miioLocalResponse) {
        r = false;
        final String str7 = str;
        final String str8 = str2;
        final String str9 = str3;
        final String str10 = str4;
        final String str11 = str5;
        final String str12 = str6;
        final long j3 = j2;
        final MiioLocalResponse miioLocalResponse2 = miioLocalResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str7, str8, str9, str10, str11, str12, j3, miioLocalResponse2);
            }
        });
    }

    public static void a() {
        r = true;
    }

    public static void a(String str) {
        if (str != null && str.length() == 2) {
            s = str.toLowerCase();
        }
    }

    private static byte[] b(String str) {
        byte[] bArr = new byte[6];
        String[] split = str.toLowerCase().split(":");
        int length = split.length;
        int i2 = 0;
        int i3 = 0;
        while (i2 < length) {
            bArr[i3] = (byte) Integer.parseInt(split[i2], 16);
            i2++;
            i3++;
        }
        return bArr;
    }

    public enum WifiEnc {
        OPEN(1, ""),
        WEP_PSK(2, "[WEP]"),
        WEP_SHARED(3, "[WEP]"),
        WPA_TKIP_PSK(4, "[WPA-PSK-TKIP]"),
        WPA_AES_PSK(5, "[WPA-PSK-CCMP]"),
        WPA2_AES_PSK(6, "[WPA2-PSK-CCMP]"),
        WPA2_TKIP_PSK(7, "[WPA2-PSK-TKIP]"),
        WPA2_MIXED_PSK(8, "[WPA2-PSK-CCMP+TKIP]"),
        WPA2_MIXED_PSK1(8, "[WPA2-PSK-TKIP+CCMP]"),
        WPA_MIXED_PSK(9, "[WPA-PSK-CCMP+TKIP]"),
        WPA_MIXED_PSK1(9, "[WPA-PSK-TKIP+CCMP]");
        
        private String capability;
        private byte type;

        public byte getType() {
            return this.type;
        }

        public void setType(byte b) {
            this.type = b;
        }

        public String getCapability() {
            return this.capability;
        }

        public void setCapability(String str) {
            this.capability = str;
        }

        private WifiEnc(int i, String str) {
            this.type = (byte) i;
            this.capability = str;
        }
    }

    private static byte c(String str) {
        if (str.contains(WifiEnc.WPA2_MIXED_PSK.getCapability())) {
            return WifiEnc.WPA2_MIXED_PSK.getType();
        }
        if (str.contains(WifiEnc.WPA2_MIXED_PSK1.getCapability())) {
            return WifiEnc.WPA2_MIXED_PSK1.getType();
        }
        if (str.contains(WifiEnc.WPA2_AES_PSK.getCapability())) {
            return WifiEnc.WPA2_AES_PSK.getType();
        }
        if (str.contains(WifiEnc.WPA2_TKIP_PSK.getCapability())) {
            return WifiEnc.WPA2_TKIP_PSK.getType();
        }
        if (str.contains(WifiEnc.WPA_MIXED_PSK.getCapability())) {
            return WifiEnc.WPA_MIXED_PSK.getType();
        }
        if (str.contains(WifiEnc.WPA_MIXED_PSK1.getCapability())) {
            return WifiEnc.WPA_MIXED_PSK1.getType();
        }
        if (str.contains(WifiEnc.WPA_AES_PSK.getCapability())) {
            return WifiEnc.WPA_AES_PSK.getType();
        }
        if (str.contains(WifiEnc.WPA_TKIP_PSK.getCapability())) {
            return WifiEnc.WPA_TKIP_PSK.getType();
        }
        if (str.contains(WifiEnc.WEP_PSK.getCapability())) {
            return WifiEnc.WEP_PSK.getType();
        }
        if (str.contains(WifiEnc.WEP_SHARED.getCapability())) {
            return WifiEnc.WEP_SHARED.getType();
        }
        if (str.length() <= 0 || str.replaceAll("\\[WPS\\]", "").replaceAll("\\[ESS\\]", "").length() != 0) {
            return 0;
        }
        return WifiEnc.OPEN.getType();
    }

    private static String d(String str) {
        return "224." + ((Integer.parseInt("0000" + str, 16) % 124) + 127) + ".%s.%s";
    }

    /* JADX WARNING: Removed duplicated region for block: B:102:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x01cc A[Catch:{ Exception -> 0x01e3, all -> 0x01e1 }] */
    /* JADX WARNING: Removed duplicated region for block: B:86:0x0215 A[Catch:{ all -> 0x01e6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:89:0x0228  */
    /* JADX WARNING: Removed duplicated region for block: B:92:0x0235  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static void a(java.lang.String r19, java.lang.String r20, java.lang.String r21, java.lang.String r22, java.lang.String r23, java.lang.String r24, long r25, long r27, com.xiaomi.miio.MiioLocalResponse r29) {
        /*
            r1 = r24
            r2 = r25
            r6 = r29
            java.lang.String r7 = "miio-localapi"
            java.lang.String r8 = "start kuailian"
            android.util.Log.e(r7, r8)
            r7 = 0
            java.net.MulticastSocket r8 = new java.net.MulticastSocket     // Catch:{ Exception -> 0x01e9 }
            r8.<init>()     // Catch:{ Exception -> 0x01e9 }
            java.lang.String r7 = "UTF-8"
            r9 = r19
            byte[] r7 = r9.getBytes(r7)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r9 = "UTF-8"
            r10 = r20
            byte[] r9 = r10.getBytes(r9)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r10 = r7.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r10 = r10 + r11
            r11 = 1
            int r10 = r10 + r11
            if (r21 == 0) goto L_0x0034
            if (r22 == 0) goto L_0x0034
            byte r15 = c((java.lang.String) r22)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r15 == 0) goto L_0x0034
            r15 = 1
            goto L_0x0035
        L_0x0034:
            r15 = 0
        L_0x0035:
            int r10 = r10 + 9
            r16 = 0
            int r18 = (r27 > r16 ? 1 : (r27 == r16 ? 0 : -1))
            if (r18 == 0) goto L_0x0047
            int r10 = r10 + 6
            boolean r18 = a((long) r27)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r18 == 0) goto L_0x0047
            int r10 = r10 + 6
        L_0x0047:
            java.lang.String r18 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r11 = 2
            if (r18 == 0) goto L_0x0056
            java.lang.String r18 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r12 = r18.length()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r12 != r11) goto L_0x0056
            int r10 = r10 + 4
        L_0x0056:
            byte[] r10 = new byte[r10]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r12 = 0
        L_0x0059:
            int r11 = r7.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r12 >= r11) goto L_0x0063
            byte r11 = r7[r12]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r12] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r12 = r12 + 1
            goto L_0x0059
        L_0x0063:
            int r7 = r12 + 1
            r11 = 0
            r10[r12] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r11 = r7
            r7 = 0
        L_0x006a:
            int r12 = r9.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r7 >= r12) goto L_0x0076
            byte r12 = r9[r7]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r7 = r7 + 1
            int r11 = r11 + 1
            goto L_0x006a
        L_0x0076:
            r7 = 6
            if (r15 == 0) goto L_0x009e
            int r9 = r11 + 1
            r12 = 0
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte[] r11 = b((java.lang.String) r21)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r12 = r9
            r9 = 0
        L_0x0084:
            int r13 = r11.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r9 >= r13) goto L_0x0090
            byte r13 = r11[r9]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r12] = r13     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r9 = r9 + 1
            int r12 = r12 + 1
            goto L_0x0084
        L_0x0090:
            int r9 = r12 + 1
            r11 = 0
            r10[r12] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            byte r12 = c((java.lang.String) r22)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r9] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            goto L_0x00c0
        L_0x009e:
            int r9 = r11 + 1
            r12 = 0
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte[] r11 = new byte[r7]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r11 = {0, 0, 0, 0, 0, 0} // fill-array     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r12 = r9
            r9 = 0
        L_0x00aa:
            int r13 = r11.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r9 >= r13) goto L_0x00b6
            byte r13 = r11[r9]     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r12] = r13     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r9 = r9 + 1
            int r12 = r12 + 1
            goto L_0x00aa
        L_0x00b6:
            int r9 = r12 + 1
            r11 = 0
            r10[r12] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            r12 = -1
            r10[r9] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
        L_0x00c0:
            int r9 = (r27 > r16 ? 1 : (r27 == r16 ? 0 : -1))
            if (r9 == 0) goto L_0x0133
            int r9 = r11 + 1
            r12 = 16
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            r10[r9] = r7     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r9 = r11 + 1
            r13 = 24
            long r13 = r27 >> r13
            r15 = 255(0xff, double:1.26E-321)
            long r13 = r13 & r15
            int r13 = (int) r13     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r13 = (byte) r13     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r11] = r13     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            long r12 = r27 >> r12
            long r12 = r12 & r15
            int r12 = (int) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r12 = (byte) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r9] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r9 = r11 + 1
            r12 = 8
            long r12 = r27 >> r12
            long r12 = r12 & r15
            int r12 = (int) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r12 = (byte) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            long r12 = r27 & r15
            int r12 = (int) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r12 = (byte) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r9] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            boolean r9 = a((long) r27)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r9 == 0) goto L_0x0133
            int r9 = r11 + 1
            r12 = 18
            r10[r11] = r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r9 + 1
            r10[r9] = r7     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r7 = r11 + 1
            r9 = 56
            long r12 = r27 >> r9
            long r12 = r12 & r15
            int r9 = (int) r12     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r9 = (byte) r9     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r11] = r9     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r9 = r7 + 1
            r11 = 48
            long r11 = r27 >> r11
            long r11 = r11 & r15
            int r11 = (int) r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r11 = (byte) r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r7] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r7 = r9 + 1
            r11 = 40
            long r11 = r27 >> r11
            long r11 = r11 & r15
            int r11 = (int) r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r11 = (byte) r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r9] = r11     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r11 = r7 + 1
            r9 = 32
            long r4 = r27 >> r9
            long r4 = r4 & r15
            int r4 = (int) r4     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r4 = (byte) r4     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r7] = r4     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
        L_0x0133:
            java.lang.String r4 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r5 = 4
            if (r4 == 0) goto L_0x0162
            java.lang.String r4 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r4 = r4.length()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r7 = 2
            if (r4 != r7) goto L_0x0162
            int r4 = r11 + 1
            r7 = 17
            r10[r11] = r7     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r7 = r4 + 1
            r10[r4] = r5     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            int r4 = r7 + 1
            java.lang.String r9 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r11 = 0
            char r9 = r9.charAt(r11)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r9 = (byte) r9     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r7] = r9     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r7 = s     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r9 = 1
            char r7 = r7.charAt(r9)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte r7 = (byte) r7     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r10[r4] = r7     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            goto L_0x0163
        L_0x0162:
            r11 = 0
        L_0x0163:
            if (r23 == 0) goto L_0x0180
            if (r1 != 0) goto L_0x0180
            int r4 = r23.length()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r4 != r5) goto L_0x0180
            java.lang.String r1 = r23.toLowerCase()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r4 = "UTF-8"
            byte[] r1 = r1.getBytes(r4)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte[] r1 = com.xiaomi.miio.JNIBridge.smencryptpk(r10, r2, r1)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r0 = d(r23)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            goto L_0x01a5
        L_0x0180:
            if (r23 == 0) goto L_0x019f
            if (r1 == 0) goto L_0x019f
            int r4 = r23.length()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r4 != r5) goto L_0x019f
            int r4 = r24.length()     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r4 != r5) goto L_0x019f
            java.lang.String r4 = "UTF-8"
            byte[] r1 = r1.getBytes(r4)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            byte[] r1 = com.xiaomi.miio.JNIBridge.smencryptpk(r10, r2, r1)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r0 = d(r23)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            goto L_0x01a5
        L_0x019f:
            byte[] r1 = com.xiaomi.miio.JNIBridge.smencrypt(r10, r2)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            java.lang.String r0 = "224.126.%s.%s"
        L_0x01a5:
            int r2 = r1.length     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r3 = 254(0xfe, float:3.56E-43)
            if (r2 <= r3) goto L_0x01b5
            com.xiaomi.miio.MiioLocalResult r0 = new com.xiaomi.miio.MiioLocalResult     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            com.xiaomi.miio.MiioLocalErrorCode r1 = com.xiaomi.miio.MiioLocalErrorCode.MSG_TOO_LONG     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r6.a(r0)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            goto L_0x01d6
        L_0x01b5:
            int r2 = r11 + 1
            r3 = 30
            if (r11 >= r3) goto L_0x01ca
            boolean r3 = r     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            if (r3 != 0) goto L_0x01ca
            r3 = 1
            a((java.lang.String) r0, (java.net.MulticastSocket) r8, (byte[]) r1, (boolean) r3)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r4 = 50
            java.lang.Thread.sleep(r4)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r11 = r2
            goto L_0x01b5
        L_0x01ca:
            if (r6 == 0) goto L_0x01d6
            com.xiaomi.miio.MiioLocalResult r0 = new com.xiaomi.miio.MiioLocalResult     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            com.xiaomi.miio.MiioLocalErrorCode r1 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r0.<init>(r1)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
            r6.a(r0)     // Catch:{ Exception -> 0x01e3, all -> 0x01e1 }
        L_0x01d6:
            java.lang.String r0 = "miio-localapi"
            java.lang.String r1 = "end kuailian"
            android.util.Log.e(r0, r1)
            r8.close()
            goto L_0x022b
        L_0x01e1:
            r0 = move-exception
            goto L_0x022c
        L_0x01e3:
            r0 = move-exception
            r7 = r8
            goto L_0x01ea
        L_0x01e6:
            r0 = move-exception
            r8 = r7
            goto L_0x022c
        L_0x01e9:
            r0 = move-exception
        L_0x01ea:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ all -> 0x01e6 }
            r1.<init>()     // Catch:{ all -> 0x01e6 }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x01e6 }
            r2.<init>(r1)     // Catch:{ all -> 0x01e6 }
            r0.printStackTrace(r2)     // Catch:{ all -> 0x01e6 }
            java.lang.String r2 = "miio-localapi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e6 }
            r3.<init>()     // Catch:{ all -> 0x01e6 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x01e6 }
            r3.append(r0)     // Catch:{ all -> 0x01e6 }
            java.lang.String r0 = r1.toString()     // Catch:{ all -> 0x01e6 }
            r3.append(r0)     // Catch:{ all -> 0x01e6 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x01e6 }
            android.util.Log.e(r2, r0)     // Catch:{ all -> 0x01e6 }
            if (r6 == 0) goto L_0x021f
            com.xiaomi.miio.MiioLocalResult r0 = new com.xiaomi.miio.MiioLocalResult     // Catch:{ all -> 0x01e6 }
            com.xiaomi.miio.MiioLocalErrorCode r1 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x01e6 }
            r0.<init>(r1)     // Catch:{ all -> 0x01e6 }
            r6.a(r0)     // Catch:{ all -> 0x01e6 }
        L_0x021f:
            java.lang.String r0 = "miio-localapi"
            java.lang.String r1 = "end kuailian"
            android.util.Log.e(r0, r1)
            if (r7 == 0) goto L_0x022b
            r7.close()
        L_0x022b:
            return
        L_0x022c:
            java.lang.String r1 = "miio-localapi"
            java.lang.String r2 = "end kuailian"
            android.util.Log.e(r1, r2)
            if (r8 == 0) goto L_0x0238
            r8.close()
        L_0x0238:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.a(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, long, long, com.xiaomi.miio.MiioLocalResponse):void");
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, String str5, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, str5, (String) null, 0, 0, miioLocalResponse);
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, String str5, String str6, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, str5, str6, 1, 0, miioLocalResponse);
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, (String) null, (String) null, System.currentTimeMillis(), 0, miioLocalResponse);
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, String str5, long j2, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, str5, (String) null, 0, j2, miioLocalResponse);
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, String str5, String str6, long j2, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, str5, str6, 1, j2, miioLocalResponse);
    }

    /* access modifiers changed from: private */
    public static void c(String str, String str2, String str3, String str4, long j2, MiioLocalResponse miioLocalResponse) {
        a(str, str2, str3, str4, (String) null, (String) null, System.currentTimeMillis(), j2, miioLocalResponse);
    }

    public static void a(final InetAddress inetAddress, final MiioLocalDeviceListResponse miioLocalDeviceListResponse) {
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(inetAddress, miioLocalDeviceListResponse);
            }
        });
    }

    public static void a(final InetAddress inetAddress, final MiioLocalDeviceResponse miioLocalDeviceResponse) {
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(inetAddress, miioLocalDeviceResponse);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x008d A[Catch:{ all -> 0x005e }] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0099  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x009f  */
    /* JADX WARNING: Removed duplicated region for block: B:36:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.net.InetAddress r10, com.xiaomi.miio.MiioLocalDeviceResponse r11) {
        /*
            r0 = 0
            java.net.DatagramSocket r1 = new java.net.DatagramSocket     // Catch:{ Exception -> 0x0061 }
            r1.<init>()     // Catch:{ Exception -> 0x0061 }
            r0 = 1
            r1.setReuseAddress(r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r1.setBroadcast(r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r3 = -1
            r5 = -1
            byte[] r6 = l     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r7 = 0
            r2 = r0
            r2.<init>(r3, r5, r6, r7)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            byte[] r0 = com.xiaomi.miio.JNIBridge.hencrypt(r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            a((java.net.DatagramSocket) r1, (java.net.InetAddress) r10, (byte[]) r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
        L_0x0020:
            r10 = 65535(0xffff, float:9.1834E-41)
            byte[] r10 = new byte[r10]     // Catch:{ SocketTimeoutException -> 0x0049 }
            r0 = 700(0x2bc, float:9.81E-43)
            java.lang.String r9 = a((java.net.DatagramSocket) r1, (byte[]) r10, (int) r0)     // Catch:{ SocketTimeoutException -> 0x0049 }
            com.xiaomi.miio.JNIBridge$MiioMsg r10 = com.xiaomi.miio.JNIBridge.hdecrypt(r10)     // Catch:{ SocketTimeoutException -> 0x0049 }
            if (r11 == 0) goto L_0x0020
            com.xiaomi.miio.MiioLocalDeviceResult r0 = new com.xiaomi.miio.MiioLocalDeviceResult     // Catch:{ SocketTimeoutException -> 0x0049 }
            com.xiaomi.miio.MiioLocalErrorCode r3 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ SocketTimeoutException -> 0x0049 }
            r4 = 0
            long r5 = r10.did     // Catch:{ SocketTimeoutException -> 0x0049 }
            int r7 = r10.stamp     // Catch:{ SocketTimeoutException -> 0x0049 }
            java.lang.String r8 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x0049 }
            byte[] r10 = r10.token     // Catch:{ SocketTimeoutException -> 0x0049 }
            r8.<init>(r10)     // Catch:{ SocketTimeoutException -> 0x0049 }
            r2 = r0
            r2.<init>(r3, r4, r5, r7, r8, r9)     // Catch:{ SocketTimeoutException -> 0x0049 }
            r11.a(r0)     // Catch:{ SocketTimeoutException -> 0x0049 }
            goto L_0x0020
        L_0x0049:
            if (r11 == 0) goto L_0x0055
            com.xiaomi.miio.MiioLocalDeviceResult r10 = new com.xiaomi.miio.MiioLocalDeviceResult     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            com.xiaomi.miio.MiioLocalErrorCode r0 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r10.<init>(r0)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
            r11.a(r10)     // Catch:{ Exception -> 0x005b, all -> 0x0059 }
        L_0x0055:
            r1.close()
            goto L_0x009c
        L_0x0059:
            r10 = move-exception
            goto L_0x009d
        L_0x005b:
            r10 = move-exception
            r0 = r1
            goto L_0x0062
        L_0x005e:
            r10 = move-exception
            r1 = r0
            goto L_0x009d
        L_0x0061:
            r10 = move-exception
        L_0x0062:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ all -> 0x005e }
            r1.<init>()     // Catch:{ all -> 0x005e }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x005e }
            r2.<init>(r1)     // Catch:{ all -> 0x005e }
            r10.printStackTrace(r2)     // Catch:{ all -> 0x005e }
            java.lang.String r2 = "miio-localapi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x005e }
            r3.<init>()     // Catch:{ all -> 0x005e }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x005e }
            r3.append(r10)     // Catch:{ all -> 0x005e }
            java.lang.String r10 = r1.toString()     // Catch:{ all -> 0x005e }
            r3.append(r10)     // Catch:{ all -> 0x005e }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x005e }
            android.util.Log.e(r2, r10)     // Catch:{ all -> 0x005e }
            if (r11 == 0) goto L_0x0097
            com.xiaomi.miio.MiioLocalDeviceResult r10 = new com.xiaomi.miio.MiioLocalDeviceResult     // Catch:{ all -> 0x005e }
            com.xiaomi.miio.MiioLocalErrorCode r1 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x005e }
            r10.<init>(r1)     // Catch:{ all -> 0x005e }
            r11.a(r10)     // Catch:{ all -> 0x005e }
        L_0x0097:
            if (r0 == 0) goto L_0x009c
            r0.close()
        L_0x009c:
            return
        L_0x009d:
            if (r1 == 0) goto L_0x00a2
            r1.close()
        L_0x00a2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.c(java.net.InetAddress, com.xiaomi.miio.MiioLocalDeviceResponse):void");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b1 A[Catch:{ all -> 0x0082 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00bd  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:42:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void c(java.net.InetAddress r11, com.xiaomi.miio.MiioLocalDeviceListResponse r12) {
        /*
            r0 = 0
            java.net.DatagramSocket r1 = new java.net.DatagramSocket     // Catch:{ Exception -> 0x0085 }
            r1.<init>()     // Catch:{ Exception -> 0x0085 }
            r0 = 1
            r1.setReuseAddress(r0)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r1.setBroadcast(r0)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ Throwable -> 0x0060 }
            r3 = -1
            r5 = -1
            byte[] r6 = l     // Catch:{ Throwable -> 0x0060 }
            r7 = 0
            r2 = r0
            r2.<init>(r3, r5, r6, r7)     // Catch:{ Throwable -> 0x0060 }
            byte[] r0 = com.xiaomi.miio.JNIBridge.hencrypt(r0)     // Catch:{ Throwable -> 0x0060 }
            a((java.net.DatagramSocket) r1, (java.net.InetAddress) r11, (byte[]) r0)     // Catch:{ Throwable -> 0x0060 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r11.<init>()     // Catch:{ Exception -> 0x007f, all -> 0x007d }
        L_0x0025:
            r0 = 65535(0xffff, float:9.1834E-41)
            byte[] r0 = new byte[r0]     // Catch:{ SocketTimeoutException -> 0x0050 }
            r2 = 700(0x2bc, float:9.81E-43)
            java.lang.String r10 = a((java.net.DatagramSocket) r1, (byte[]) r0, (int) r2)     // Catch:{ SocketTimeoutException -> 0x0050 }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = com.xiaomi.miio.JNIBridge.hdecrypt(r0)     // Catch:{ SocketTimeoutException -> 0x0050 }
            com.xiaomi.miio.MiioLocalRpcResult r2 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x0050 }
            com.xiaomi.miio.MiioLocalErrorCode r4 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ SocketTimeoutException -> 0x0050 }
            r5 = 0
            long r6 = r0.did     // Catch:{ SocketTimeoutException -> 0x0050 }
            int r8 = r0.stamp     // Catch:{ SocketTimeoutException -> 0x0050 }
            java.lang.String r3 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x0050 }
            byte[] r0 = r0.token     // Catch:{ SocketTimeoutException -> 0x0050 }
            r3.<init>(r0)     // Catch:{ SocketTimeoutException -> 0x0050 }
            java.lang.String r9 = r3.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x0050 }
            r3 = r2
            r3.<init>(r4, r5, r6, r8, r9, r10)     // Catch:{ SocketTimeoutException -> 0x0050 }
            r11.add(r2)     // Catch:{ SocketTimeoutException -> 0x0050 }
            goto L_0x0025
        L_0x0050:
            if (r12 == 0) goto L_0x005c
            com.xiaomi.miio.MiioLocalDeviceListResult r0 = new com.xiaomi.miio.MiioLocalDeviceListResult     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            com.xiaomi.miio.MiioLocalErrorCode r2 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r0.<init>(r2, r11)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r12.a(r0)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
        L_0x005c:
            r1.close()
            goto L_0x00c0
        L_0x0060:
            r11 = move-exception
            r11.printStackTrace()     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            java.lang.String r0 = "miio-localapi"
            java.lang.String r11 = r11.toString()     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            android.util.Log.e(r0, r11)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            if (r12 == 0) goto L_0x0079
            com.xiaomi.miio.MiioLocalDeviceListResult r11 = new com.xiaomi.miio.MiioLocalDeviceListResult     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            com.xiaomi.miio.MiioLocalErrorCode r0 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r11.<init>(r0)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
            r12.a(r11)     // Catch:{ Exception -> 0x007f, all -> 0x007d }
        L_0x0079:
            r1.close()
            return
        L_0x007d:
            r11 = move-exception
            goto L_0x00c1
        L_0x007f:
            r11 = move-exception
            r0 = r1
            goto L_0x0086
        L_0x0082:
            r11 = move-exception
            r1 = r0
            goto L_0x00c1
        L_0x0085:
            r11 = move-exception
        L_0x0086:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch:{ all -> 0x0082 }
            r1.<init>()     // Catch:{ all -> 0x0082 }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x0082 }
            r2.<init>(r1)     // Catch:{ all -> 0x0082 }
            r11.printStackTrace(r2)     // Catch:{ all -> 0x0082 }
            java.lang.String r2 = "miio-localapi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0082 }
            r3.<init>()     // Catch:{ all -> 0x0082 }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x0082 }
            r3.append(r11)     // Catch:{ all -> 0x0082 }
            java.lang.String r11 = r1.toString()     // Catch:{ all -> 0x0082 }
            r3.append(r11)     // Catch:{ all -> 0x0082 }
            java.lang.String r11 = r3.toString()     // Catch:{ all -> 0x0082 }
            android.util.Log.e(r2, r11)     // Catch:{ all -> 0x0082 }
            if (r12 == 0) goto L_0x00bb
            com.xiaomi.miio.MiioLocalDeviceListResult r11 = new com.xiaomi.miio.MiioLocalDeviceListResult     // Catch:{ all -> 0x0082 }
            com.xiaomi.miio.MiioLocalErrorCode r1 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x0082 }
            r11.<init>(r1)     // Catch:{ all -> 0x0082 }
            r12.a(r11)     // Catch:{ all -> 0x0082 }
        L_0x00bb:
            if (r0 == 0) goto L_0x00c0
            r0.close()
        L_0x00c0:
            return
        L_0x00c1:
            if (r1 == 0) goto L_0x00c6
            r1.close()
        L_0x00c6:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.c(java.net.InetAddress, com.xiaomi.miio.MiioLocalDeviceListResponse):void");
    }

    /* access modifiers changed from: private */
    public static synchronized void c(String str, String str2, long j2, String str3, int i2, MiioLocalRpcResponse miioLocalRpcResponse) {
        synchronized (MiioLocalAPI.class) {
            a(str, str2, j2, str3, i2, 2000, miioLocalRpcResponse);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0067, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0089, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00d7, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x00da, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x00db, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x00dd, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x00de, code lost:
        r3 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0110, code lost:
        r1.onResponse(new com.xiaomi.miio.MiioLocalRpcResult(com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION).a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x011e, code lost:
        if (r3 == null) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:?, code lost:
        r3.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0150, code lost:
        r1.onResponse(new com.xiaomi.miio.MiioLocalRpcResult(com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT).a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x015e, code lost:
        if (r3 == null) goto L_0x0161;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x0162, code lost:
        return;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x0087 A[Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }, LOOP:0: B:17:0x0058->B:32:0x0087, LOOP_END] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00d7 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x000c] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x00da A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:6:0x000c] */
    /* JADX WARNING: Removed duplicated region for block: B:54:0x0110 A[Catch:{ all -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:62:0x0150 A[Catch:{ all -> 0x00e0 }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x0165 A[SYNTHETIC, Splitter:B:67:0x0165] */
    /* JADX WARNING: Removed duplicated region for block: B:74:0x0089 A[SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:51:0x00e5=Splitter:B:51:0x00e5, B:59:0x0125=Splitter:B:59:0x0125} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:56:0x0120=Splitter:B:56:0x0120, B:41:0x00d2=Splitter:B:41:0x00d2} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static synchronized void a(java.lang.String r22, java.lang.String r23, long r24, java.lang.String r26, int r27, int r28, com.xiaomi.miio.MiioLocalRpcResponse r29) {
        /*
            r1 = r29
            java.lang.Class<com.xiaomi.miio.MiioLocalAPI> r2 = com.xiaomi.miio.MiioLocalAPI.class
            monitor-enter(r2)
            r3 = 0
            java.net.DatagramSocket r4 = new java.net.DatagramSocket     // Catch:{ SocketTimeoutException -> 0x0124, Exception -> 0x00e4 }
            r4.<init>()     // Catch:{ SocketTimeoutException -> 0x0124, Exception -> 0x00e4 }
            r0 = 1
            r4.setReuseAddress(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.net.InetAddress r5 = java.net.InetAddress.getByName(r22)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r0 = "UTF-8"
            r6 = r26
            byte[] r12 = r6.getBytes(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            int r0 = r12.length     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r6 = 32
            if (r0 == r6) goto L_0x003a
            java.lang.String r0 = "ERROR"
            java.lang.String r3 = "Token size error"
            android.util.Log.e(r0, r3)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            com.xiaomi.miio.MiioLocalErrorCode r3 = com.xiaomi.miio.MiioLocalErrorCode.PERMISSION_DENIED     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r0.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r0 = r0.a()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r4.close()     // Catch:{ all -> 0x0169 }
            monitor-exit(r2)
            return
        L_0x003a:
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r6 = "UTF-8"
            r7 = r23
            byte[] r11 = r7.getBytes(r6)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r6 = r0
            r7 = r24
            r9 = r27
            r10 = r12
            r6.<init>(r7, r9, r10, r11)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            byte[] r6 = com.xiaomi.miio.JNIBridge.encrypt(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r0 = 65535(0xffff, float:9.1834E-41)
            byte[] r7 = new byte[r0]     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r0 = 0
            r8 = 0
        L_0x0058:
            r0 = 3
            if (r8 >= r0) goto L_0x008a
            a((java.net.DatagramSocket) r4, (java.net.InetAddress) r5, (byte[]) r6)     // Catch:{ SocketTimeoutException -> 0x0069, Exception -> 0x00da, all -> 0x00d7 }
            r9 = r28
            java.lang.String r0 = a((java.net.DatagramSocket) r4, (byte[]) r7, (int) r9)     // Catch:{ SocketTimeoutException -> 0x0067, Exception -> 0x00da, all -> 0x00d7 }
            r21 = r0
            goto L_0x008c
        L_0x0067:
            r0 = move-exception
            goto L_0x006c
        L_0x0069:
            r0 = move-exception
            r9 = r28
        L_0x006c:
            java.lang.String r10 = "miio-localapi"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r11.<init>()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r13 = "rpc data time out, retry "
            r11.append(r13)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            int r13 = r8 + 1
            r11.append(r13)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r11 = r11.toString()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            android.util.Log.e(r10, r11)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r10 = 2
            if (r8 == r10) goto L_0x0089
            r8 = r13
            goto L_0x0058
        L_0x0089:
            throw r0     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
        L_0x008a:
            r21 = r3
        L_0x008c:
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = com.xiaomi.miio.JNIBridge.decrypt(r7, r12)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            byte[] r3 = r0.message     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            if (r3 == 0) goto L_0x00c2
            if (r1 == 0) goto L_0x00c2
            com.xiaomi.miio.MiioLocalRpcResult r3 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            com.xiaomi.miio.MiioLocalErrorCode r15 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r5 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            byte[] r6 = r0.message     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r5.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            long r6 = r0.did     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            int r8 = r0.stamp     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r9 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            byte[] r0 = r0.token     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r9.<init>(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r20 = r9.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r14 = r3
            r16 = r5
            r17 = r6
            r19 = r8
            r14.<init>(r15, r16, r17, r19, r20, r21)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r0 = r3.a()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            goto L_0x00d2
        L_0x00c2:
            if (r1 == 0) goto L_0x00d2
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            com.xiaomi.miio.MiioLocalErrorCode r3 = com.xiaomi.miio.MiioLocalErrorCode.DEVICE_EXCEPTION     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r0.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            java.lang.String r0 = r0.a()     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x00dd, Exception -> 0x00da, all -> 0x00d7 }
        L_0x00d2:
            r4.close()     // Catch:{ all -> 0x0169 }
            goto L_0x0161
        L_0x00d7:
            r0 = move-exception
            goto L_0x0163
        L_0x00da:
            r0 = move-exception
            r3 = r4
            goto L_0x00e5
        L_0x00dd:
            r0 = move-exception
            r3 = r4
            goto L_0x0125
        L_0x00e0:
            r0 = move-exception
            r4 = r3
            goto L_0x0163
        L_0x00e4:
            r0 = move-exception
        L_0x00e5:
            java.io.StringWriter r4 = new java.io.StringWriter     // Catch:{ all -> 0x00e0 }
            r4.<init>()     // Catch:{ all -> 0x00e0 }
            java.io.PrintWriter r5 = new java.io.PrintWriter     // Catch:{ all -> 0x00e0 }
            r5.<init>(r4)     // Catch:{ all -> 0x00e0 }
            r0.printStackTrace(r5)     // Catch:{ all -> 0x00e0 }
            java.lang.String r5 = "miio-localapi"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e0 }
            r6.<init>()     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e0 }
            r6.append(r0)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x00e0 }
            r6.append(r0)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00e0 }
            android.util.Log.e(r5, r0)     // Catch:{ all -> 0x00e0 }
            if (r1 == 0) goto L_0x011e
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x00e0 }
            com.xiaomi.miio.MiioLocalErrorCode r4 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x00e0 }
            r0.<init>(r4)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x00e0 }
            r1.onResponse(r0)     // Catch:{ all -> 0x00e0 }
        L_0x011e:
            if (r3 == 0) goto L_0x0161
        L_0x0120:
            r3.close()     // Catch:{ all -> 0x0169 }
            goto L_0x0161
        L_0x0124:
            r0 = move-exception
        L_0x0125:
            java.io.StringWriter r4 = new java.io.StringWriter     // Catch:{ all -> 0x00e0 }
            r4.<init>()     // Catch:{ all -> 0x00e0 }
            java.io.PrintWriter r5 = new java.io.PrintWriter     // Catch:{ all -> 0x00e0 }
            r5.<init>(r4)     // Catch:{ all -> 0x00e0 }
            r0.printStackTrace(r5)     // Catch:{ all -> 0x00e0 }
            java.lang.String r5 = "miio-localapi"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ all -> 0x00e0 }
            r6.<init>()     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00e0 }
            r6.append(r0)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x00e0 }
            r6.append(r0)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00e0 }
            android.util.Log.e(r5, r0)     // Catch:{ all -> 0x00e0 }
            if (r1 == 0) goto L_0x015e
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x00e0 }
            com.xiaomi.miio.MiioLocalErrorCode r4 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ all -> 0x00e0 }
            r0.<init>(r4)     // Catch:{ all -> 0x00e0 }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x00e0 }
            r1.onResponse(r0)     // Catch:{ all -> 0x00e0 }
        L_0x015e:
            if (r3 == 0) goto L_0x0161
            goto L_0x0120
        L_0x0161:
            monitor-exit(r2)
            return
        L_0x0163:
            if (r4 == 0) goto L_0x016b
            r4.close()     // Catch:{ all -> 0x0169 }
            goto L_0x016b
        L_0x0169:
            r0 = move-exception
            goto L_0x016c
        L_0x016b:
            throw r0     // Catch:{ all -> 0x0169 }
        L_0x016c:
            monitor-exit(r2)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.a(java.lang.String, java.lang.String, long, java.lang.String, int, int, com.xiaomi.miio.MiioLocalRpcResponse):void");
    }

    public static void a(String str, String str2, long j2, String str3, int i2, MiioLocalRpcResponse miioLocalRpcResponse) {
        final String str4 = str;
        final String str5 = str2;
        final long j3 = j2;
        final String str6 = str3;
        final int i3 = i2;
        final MiioLocalRpcResponse miioLocalRpcResponse2 = miioLocalRpcResponse;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str4, str5, j3, str6, i3, miioLocalRpcResponse2);
            }
        });
    }

    public static void a(String str, String str2, long j2, String str3, MiioLocalRpcResponse miioLocalRpcResponse) {
        a(str, str2, j2, str3, miioLocalRpcResponse, 2000);
    }

    public static void a(String str, String str2, long j2, String str3, MiioLocalRpcResponse miioLocalRpcResponse, int i2) {
        final String str4 = str;
        final String str5 = str2;
        final long j3 = j2;
        final String str6 = str3;
        final MiioLocalRpcResponse miioLocalRpcResponse2 = miioLocalRpcResponse;
        final int i3 = i2;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.c(str4, str5, j3, str6, miioLocalRpcResponse2, i3);
            }
        });
    }

    public static void a(final String str, final String str2, final MiioLocalRpcResponse miioLocalRpcResponse) {
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.b(str, str2, miioLocalRpcResponse);
            }
        });
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0072, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0073, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0076, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0077, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00db, code lost:
        if (r1 != null) goto L_0x00dd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x011b, code lost:
        if (r1 != null) goto L_0x00dd;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0072 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:8:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0076 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:8:0x0012] */
    /* JADX WARNING: Removed duplicated region for block: B:48:0x00cd A[Catch:{ all -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:56:0x010d A[Catch:{ all -> 0x009e }] */
    /* JADX WARNING: Removed duplicated region for block: B:61:0x0122 A[SYNTHETIC, Splitter:B:61:0x0122] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:45:0x00a2=Splitter:B:45:0x00a2, B:53:0x00e2=Splitter:B:53:0x00e2} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void c(java.lang.String r12, java.lang.String r13, long r14, java.lang.String r16, com.xiaomi.miio.MiioLocalRpcResponse r17, int r18) {
        /*
            r9 = r17
            java.lang.Class<com.xiaomi.miio.MiioLocalAPI> r10 = com.xiaomi.miio.MiioLocalAPI.class
            monitor-enter(r10)
            r1 = 0
            int r0 = com.xiaomi.miio.MiioTimestampCache.a(r14)     // Catch:{ SocketTimeoutException -> 0x00e1, Exception -> 0x00a1 }
            if (r0 >= 0) goto L_0x007d
            java.net.DatagramSocket r2 = new java.net.DatagramSocket     // Catch:{ SocketTimeoutException -> 0x00e1, Exception -> 0x00a1 }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x00e1, Exception -> 0x00a1 }
            r0 = 1
            r2.setReuseAddress(r0)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r4 = -1
            r6 = -1
            byte[] r7 = l     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r8 = 0
            r3 = r0
            r3.<init>(r4, r6, r7, r8)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            byte[] r1 = com.xiaomi.miio.JNIBridge.hencrypt(r0)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r0 = 65535(0xffff, float:9.1834E-41)
            byte[] r3 = new byte[r0]     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r0 = 0
            r4 = 0
        L_0x002c:
            r0 = 3
            if (r4 >= r0) goto L_0x0064
            java.net.InetAddress r0 = java.net.InetAddress.getByName(r12)     // Catch:{ SocketTimeoutException -> 0x003c, Exception -> 0x0076, all -> 0x0072 }
            a((java.net.DatagramSocket) r2, (java.net.InetAddress) r0, (byte[]) r1)     // Catch:{ SocketTimeoutException -> 0x003c, Exception -> 0x0076, all -> 0x0072 }
            r0 = 2000(0x7d0, float:2.803E-42)
            a((java.net.DatagramSocket) r2, (byte[]) r3, (int) r0)     // Catch:{ SocketTimeoutException -> 0x003c, Exception -> 0x0076, all -> 0x0072 }
            goto L_0x0064
        L_0x003c:
            r0 = move-exception
            java.lang.String r5 = "miio-localapi"
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            java.lang.String r7 = "rpc timestamp time out, retry "
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            int r7 = r4 + 1
            r6.append(r7)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            java.lang.String r8 = " body:"
            r6.append(r8)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r8 = r13
            r6.append(r13)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            java.lang.String r6 = r6.toString()     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            android.util.Log.e(r5, r6)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r5 = 2
            if (r4 == r5) goto L_0x0063
            r4 = r7
            goto L_0x002c
        L_0x0063:
            throw r0     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
        L_0x0064:
            r8 = r13
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = com.xiaomi.miio.JNIBridge.hdecrypt(r3)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            int r0 = r0.stamp     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r3 = r14
            com.xiaomi.miio.MiioTimestampCache.a(r14, r0)     // Catch:{ SocketTimeoutException -> 0x0079, Exception -> 0x0076, all -> 0x0072 }
            r6 = r0
            r11 = r2
            goto L_0x0081
        L_0x0072:
            r0 = move-exception
            r1 = r2
            goto L_0x0120
        L_0x0076:
            r0 = move-exception
            r1 = r2
            goto L_0x00a2
        L_0x0079:
            r0 = move-exception
            r1 = r2
            goto L_0x00e2
        L_0x007d:
            r8 = r13
            r3 = r14
            r6 = r0
            r11 = r1
        L_0x0081:
            r1 = r12
            r2 = r13
            r3 = r14
            r5 = r16
            r7 = r18
            r8 = r17
            a((java.lang.String) r1, (java.lang.String) r2, (long) r3, (java.lang.String) r5, (int) r6, (int) r7, (com.xiaomi.miio.MiioLocalRpcResponse) r8)     // Catch:{ SocketTimeoutException -> 0x009b, Exception -> 0x0098, all -> 0x0094 }
            if (r11 == 0) goto L_0x011e
            r11.close()     // Catch:{ all -> 0x0126 }
            goto L_0x011e
        L_0x0094:
            r0 = move-exception
            r1 = r11
            goto L_0x0120
        L_0x0098:
            r0 = move-exception
            r1 = r11
            goto L_0x00a2
        L_0x009b:
            r0 = move-exception
            r1 = r11
            goto L_0x00e2
        L_0x009e:
            r0 = move-exception
            goto L_0x0120
        L_0x00a1:
            r0 = move-exception
        L_0x00a2:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x009e }
            r2.<init>()     // Catch:{ all -> 0x009e }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x009e }
            r3.<init>(r2)     // Catch:{ all -> 0x009e }
            r0.printStackTrace(r3)     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "miio-localapi"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r4.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x009e }
            r4.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x009e }
            r4.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x009e }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x00db
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x009e }
            com.xiaomi.miio.MiioLocalErrorCode r2 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x009e }
            r0.<init>(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x009e }
            r9.onResponse(r0)     // Catch:{ all -> 0x009e }
        L_0x00db:
            if (r1 == 0) goto L_0x011e
        L_0x00dd:
            r1.close()     // Catch:{ all -> 0x0126 }
            goto L_0x011e
        L_0x00e1:
            r0 = move-exception
        L_0x00e2:
            java.io.StringWriter r2 = new java.io.StringWriter     // Catch:{ all -> 0x009e }
            r2.<init>()     // Catch:{ all -> 0x009e }
            java.io.PrintWriter r3 = new java.io.PrintWriter     // Catch:{ all -> 0x009e }
            r3.<init>(r2)     // Catch:{ all -> 0x009e }
            r0.printStackTrace(r3)     // Catch:{ all -> 0x009e }
            java.lang.String r3 = "miio-localapi"
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x009e }
            r4.<init>()     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x009e }
            r4.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x009e }
            r4.append(r0)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x009e }
            android.util.Log.e(r3, r0)     // Catch:{ all -> 0x009e }
            if (r9 == 0) goto L_0x011b
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x009e }
            com.xiaomi.miio.MiioLocalErrorCode r2 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ all -> 0x009e }
            r0.<init>(r2)     // Catch:{ all -> 0x009e }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x009e }
            r9.onResponse(r0)     // Catch:{ all -> 0x009e }
        L_0x011b:
            if (r1 == 0) goto L_0x011e
            goto L_0x00dd
        L_0x011e:
            monitor-exit(r10)
            return
        L_0x0120:
            if (r1 == 0) goto L_0x0128
            r1.close()     // Catch:{ all -> 0x0126 }
            goto L_0x0128
        L_0x0126:
            r0 = move-exception
            goto L_0x0129
        L_0x0128:
            throw r0     // Catch:{ all -> 0x0126 }
        L_0x0129:
            monitor-exit(r10)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.c(java.lang.String, java.lang.String, long, java.lang.String, com.xiaomi.miio.MiioLocalRpcResponse, int):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0074, code lost:
        r10 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0075, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0078, code lost:
        r10 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0079, code lost:
        r1 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00bb, code lost:
        if (r1 == null) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x00fb, code lost:
        if (r1 == null) goto L_0x00fe;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0074 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0078 A[ExcHandler: Exception (e java.lang.Exception), Splitter:B:6:0x000a] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00ad A[Catch:{ all -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00ed A[Catch:{ all -> 0x007e }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x0102 A[SYNTHETIC, Splitter:B:47:0x0102] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:31:0x0082=Splitter:B:31:0x0082, B:39:0x00c2=Splitter:B:39:0x00c2} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b(java.lang.String r10, java.lang.String r11, com.xiaomi.miio.MiioLocalRpcResponse r12) {
        /*
            java.lang.Class<com.xiaomi.miio.MiioLocalAPI> r0 = com.xiaomi.miio.MiioLocalAPI.class
            monitor-enter(r0)
            r1 = 0
            java.net.DatagramSocket r2 = new java.net.DatagramSocket     // Catch:{ SocketTimeoutException -> 0x00c1, Exception -> 0x0081 }
            r2.<init>()     // Catch:{ SocketTimeoutException -> 0x00c1, Exception -> 0x0081 }
            r1 = 1
            r2.setReuseAddress(r1)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            com.xiaomi.miio.JNIBridge$MiioMsg r1 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r4 = -1
            r6 = -1
            byte[] r7 = l     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r8 = 0
            r3 = r1
            r3.<init>(r4, r6, r7, r8)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            byte[] r1 = com.xiaomi.miio.JNIBridge.hencrypt(r1)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r3 = 65535(0xffff, float:9.1834E-41)
            byte[] r3 = new byte[r3]     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r4 = 0
        L_0x0023:
            r5 = 3
            if (r4 >= r5) goto L_0x005a
            java.net.InetAddress r5 = java.net.InetAddress.getByName(r10)     // Catch:{ SocketTimeoutException -> 0x0033, Exception -> 0x0078, all -> 0x0074 }
            a((java.net.DatagramSocket) r2, (java.net.InetAddress) r5, (byte[]) r1)     // Catch:{ SocketTimeoutException -> 0x0033, Exception -> 0x0078, all -> 0x0074 }
            r5 = 2000(0x7d0, float:2.803E-42)
            a((java.net.DatagramSocket) r2, (byte[]) r3, (int) r5)     // Catch:{ SocketTimeoutException -> 0x0033, Exception -> 0x0078, all -> 0x0074 }
            goto L_0x005a
        L_0x0033:
            r5 = move-exception
            java.lang.String r6 = "miio-localapi"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r7.<init>()     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            java.lang.String r8 = "rpc timestamp time out, retry "
            r7.append(r8)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            int r8 = r4 + 1
            r7.append(r8)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            java.lang.String r9 = " body:"
            r7.append(r9)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r7.append(r11)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            java.lang.String r7 = r7.toString()     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            android.util.Log.e(r6, r7)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r6 = 2
            if (r4 == r6) goto L_0x0059
            r4 = r8
            goto L_0x0023
        L_0x0059:
            throw r5     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
        L_0x005a:
            com.xiaomi.miio.JNIBridge$MiioMsg r1 = com.xiaomi.miio.JNIBridge.hdecrypt(r3)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            long r5 = r1.did     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            java.lang.String r7 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            byte[] r3 = r1.token     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r7.<init>(r3)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            int r8 = r1.stamp     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r3 = r10
            r4 = r11
            r9 = r12
            c((java.lang.String) r3, (java.lang.String) r4, (long) r5, (java.lang.String) r7, (int) r8, (com.xiaomi.miio.MiioLocalRpcResponse) r9)     // Catch:{ SocketTimeoutException -> 0x007b, Exception -> 0x0078, all -> 0x0074 }
            r2.close()     // Catch:{ all -> 0x0106 }
            goto L_0x00fe
        L_0x0074:
            r10 = move-exception
            r1 = r2
            goto L_0x0100
        L_0x0078:
            r10 = move-exception
            r1 = r2
            goto L_0x0082
        L_0x007b:
            r10 = move-exception
            r1 = r2
            goto L_0x00c2
        L_0x007e:
            r10 = move-exception
            goto L_0x0100
        L_0x0081:
            r10 = move-exception
        L_0x0082:
            java.io.StringWriter r11 = new java.io.StringWriter     // Catch:{ all -> 0x007e }
            r11.<init>()     // Catch:{ all -> 0x007e }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x007e }
            r2.<init>(r11)     // Catch:{ all -> 0x007e }
            r10.printStackTrace(r2)     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "miio-localapi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007e }
            r3.<init>()     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x007e }
            r3.append(r10)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x007e }
            r3.append(r10)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x007e }
            android.util.Log.e(r2, r10)     // Catch:{ all -> 0x007e }
            if (r12 == 0) goto L_0x00bb
            com.xiaomi.miio.MiioLocalRpcResult r10 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x007e }
            com.xiaomi.miio.MiioLocalErrorCode r11 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x007e }
            r10.<init>(r11)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r10.a()     // Catch:{ all -> 0x007e }
            r12.onResponse(r10)     // Catch:{ all -> 0x007e }
        L_0x00bb:
            if (r1 == 0) goto L_0x00fe
        L_0x00bd:
            r1.close()     // Catch:{ all -> 0x0106 }
            goto L_0x00fe
        L_0x00c1:
            r10 = move-exception
        L_0x00c2:
            java.io.StringWriter r11 = new java.io.StringWriter     // Catch:{ all -> 0x007e }
            r11.<init>()     // Catch:{ all -> 0x007e }
            java.io.PrintWriter r2 = new java.io.PrintWriter     // Catch:{ all -> 0x007e }
            r2.<init>(r11)     // Catch:{ all -> 0x007e }
            r10.printStackTrace(r2)     // Catch:{ all -> 0x007e }
            java.lang.String r2 = "miio-localapi"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x007e }
            r3.<init>()     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x007e }
            r3.append(r10)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r11.toString()     // Catch:{ all -> 0x007e }
            r3.append(r10)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r3.toString()     // Catch:{ all -> 0x007e }
            android.util.Log.e(r2, r10)     // Catch:{ all -> 0x007e }
            if (r12 == 0) goto L_0x00fb
            com.xiaomi.miio.MiioLocalRpcResult r10 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x007e }
            com.xiaomi.miio.MiioLocalErrorCode r11 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ all -> 0x007e }
            r10.<init>(r11)     // Catch:{ all -> 0x007e }
            java.lang.String r10 = r10.a()     // Catch:{ all -> 0x007e }
            r12.onResponse(r10)     // Catch:{ all -> 0x007e }
        L_0x00fb:
            if (r1 == 0) goto L_0x00fe
            goto L_0x00bd
        L_0x00fe:
            monitor-exit(r0)
            return
        L_0x0100:
            if (r1 == 0) goto L_0x0108
            r1.close()     // Catch:{ all -> 0x0106 }
            goto L_0x0108
        L_0x0106:
            r10 = move-exception
            goto L_0x0109
        L_0x0108:
            throw r10     // Catch:{ all -> 0x0106 }
        L_0x0109:
            monitor-exit(r0)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.b(java.lang.String, java.lang.String, com.xiaomi.miio.MiioLocalRpcResponse):void");
    }

    public static Cancelable a(String str, MiioLocalRpcResponse miioLocalRpcResponse, int i2) {
        return a(str, miioLocalRpcResponse, 2000, i2);
    }

    public static Cancelable a(String str, MiioLocalRpcResponse miioLocalRpcResponse, int i2, int i3) {
        Cancelable cancelable = new Cancelable();
        final String str2 = str;
        final MiioLocalRpcResponse miioLocalRpcResponse2 = miioLocalRpcResponse;
        final int i4 = i2;
        final int i5 = i3;
        final Cancelable cancelable2 = cancelable;
        v.execute(new Runnable() {
            public void run() {
                MiioLocalAPI.b(str2, miioLocalRpcResponse2, i4, i5, cancelable2);
            }
        });
        return cancelable;
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0029, code lost:
        if (r5 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0074, code lost:
        if (new java.lang.String(l).equals(new java.lang.String(r0.token).toLowerCase()) == false) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0076, code lost:
        r1.onResponse(new com.xiaomi.miio.MiioLocalRpcResult(com.xiaomi.miio.MiioLocalErrorCode.PERMISSION_DENIED).a());
        android.util.Log.e("ERROR", "get token error");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x008c, code lost:
        r1.onResponse(new com.xiaomi.miio.MiioLocalRpcResult(com.xiaomi.miio.MiioLocalErrorCode.SUCCESS, "{\"id\":1,\"result\":{\"token\":\"" + new java.lang.String(r0.token).toLowerCase() + "\", \"did\":\"" + r0.did + "\"}}", r0.did, r0.stamp, new java.lang.String(r0.token).toLowerCase(), r15).a());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:?, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00de, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x00e1, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x00e2, code lost:
        r7 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00e6, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00e7, code lost:
        r7 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x012d, code lost:
        if (r5 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0131, code lost:
        if (r5 != null) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:?, code lost:
        r5.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0175, code lost:
        if (r5 != null) goto L_0x002b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x0179, code lost:
        if (r5 != null) goto L_0x0133;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:?, code lost:
        java.lang.Thread.sleep(2000);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:?, code lost:
        android.util.Log.i(com.hannto.printservice.hanntoprintservice.entity.PrinterParmater.l, "get_token InterruptedException");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x018e, code lost:
        r6.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x0192, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x0196, code lost:
        throw r0;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00de A[ExcHandler: all (th java.lang.Throwable), Splitter:B:18:0x0035] */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x011d A[ADDED_TO_REGION, Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x0165 A[ADDED_TO_REGION, Catch:{ all -> 0x00eb }] */
    /* JADX WARNING: Removed duplicated region for block: B:67:0x018e A[Catch:{ InterruptedException -> 0x0183, all -> 0x0192 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static synchronized void b(java.lang.String r16, com.xiaomi.miio.MiioLocalRpcResponse r17, int r18, int r19, com.xiaomi.miio.MiioLocalAPI.Cancelable r20) {
        /*
            r1 = r17
            java.lang.Class<com.xiaomi.miio.MiioLocalAPI> r2 = com.xiaomi.miio.MiioLocalAPI.class
            monitor-enter(r2)
            r0 = 0
            r3 = 1
            r4 = r19
            if (r4 >= r3) goto L_0x000e
            r5 = r0
            r4 = 1
            goto L_0x000f
        L_0x000e:
            r5 = r0
        L_0x000f:
            if (r4 <= 0) goto L_0x0197
            int r4 = r4 + -1
            boolean r0 = r20.f11147a     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            if (r0 == 0) goto L_0x0030
            if (r1 == 0) goto L_0x0029
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            com.xiaomi.miio.MiioLocalErrorCode r6 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            r0.<init>(r6)     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            java.lang.String r0 = r0.a()     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
        L_0x0029:
            if (r5 == 0) goto L_0x0197
        L_0x002b:
            r5.close()     // Catch:{ all -> 0x0192 }
            goto L_0x0197
        L_0x0030:
            java.net.DatagramSocket r6 = new java.net.DatagramSocket     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            r6.<init>()     // Catch:{ SocketTimeoutException -> 0x0137, Exception -> 0x00ef }
            r6.setReuseAddress(r3)     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = new com.xiaomi.miio.JNIBridge$MiioMsg     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            r8 = -1
            r10 = -1
            byte[] r11 = l     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            r12 = 0
            r7 = r0
            r7.<init>(r8, r10, r11, r12)     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            byte[] r0 = com.xiaomi.miio.JNIBridge.hencrypt(r0)     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            java.net.InetAddress r5 = java.net.InetAddress.getByName(r16)     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            a((java.net.DatagramSocket) r6, (java.net.InetAddress) r5, (byte[]) r0)     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            r0 = 65535(0xffff, float:9.1834E-41)
            byte[] r0 = new byte[r0]     // Catch:{ SocketTimeoutException -> 0x00e6, Exception -> 0x00e1, all -> 0x00de }
            r7 = r18
            java.lang.String r15 = a((java.net.DatagramSocket) r6, (byte[]) r0, (int) r7)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            com.xiaomi.miio.JNIBridge$MiioMsg r0 = com.xiaomi.miio.JNIBridge.hdecrypt(r0)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r5 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            byte[] r8 = l     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r5.<init>(r8)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r8 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            byte[] r9 = r0.token     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8.<init>(r9)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r8 = r8.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            boolean r5 = r5.equals(r8)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            if (r5 == 0) goto L_0x008c
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            com.xiaomi.miio.MiioLocalErrorCode r5 = com.xiaomi.miio.MiioLocalErrorCode.PERMISSION_DENIED     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r0.<init>(r5)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r0 = r0.a()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r0 = "ERROR"
            java.lang.String r5 = "get token error"
            android.util.Log.e(r0, r5)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            goto L_0x00d5
        L_0x008c:
            com.xiaomi.miio.MiioLocalRpcResult r5 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            com.xiaomi.miio.MiioLocalErrorCode r9 = com.xiaomi.miio.MiioLocalErrorCode.SUCCESS     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8.<init>()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = "{\"id\":1,\"result\":{\"token\":\""
            r8.append(r10)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            byte[] r11 = r0.token     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r10.<init>(r11)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = r10.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8.append(r10)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = "\", \"did\":\""
            r8.append(r10)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            long r10 = r0.did     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8.append(r10)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = "\"}}"
            r8.append(r10)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r10 = r8.toString()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            long r11 = r0.did     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            int r13 = r0.stamp     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r8 = new java.lang.String     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            byte[] r0 = r0.token     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8.<init>(r0)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r14 = r8.toLowerCase()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r8 = r5
            r8.<init>(r9, r10, r11, r13, r14, r15)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            java.lang.String r0 = r5.a()     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
            r1.onResponse(r0)     // Catch:{ SocketTimeoutException -> 0x00dc, Exception -> 0x00da, all -> 0x00de }
        L_0x00d5:
            r6.close()     // Catch:{ all -> 0x0192 }
            goto L_0x0197
        L_0x00da:
            r0 = move-exception
            goto L_0x00e4
        L_0x00dc:
            r0 = move-exception
            goto L_0x00e9
        L_0x00de:
            r0 = move-exception
            goto L_0x018c
        L_0x00e1:
            r0 = move-exception
            r7 = r18
        L_0x00e4:
            r5 = r6
            goto L_0x00f2
        L_0x00e6:
            r0 = move-exception
            r7 = r18
        L_0x00e9:
            r5 = r6
            goto L_0x013a
        L_0x00eb:
            r0 = move-exception
            r6 = r5
            goto L_0x018c
        L_0x00ef:
            r0 = move-exception
            r7 = r18
        L_0x00f2:
            java.io.StringWriter r6 = new java.io.StringWriter     // Catch:{ all -> 0x00eb }
            r6.<init>()     // Catch:{ all -> 0x00eb }
            java.io.PrintWriter r8 = new java.io.PrintWriter     // Catch:{ all -> 0x00eb }
            r8.<init>(r6)     // Catch:{ all -> 0x00eb }
            r0.printStackTrace(r8)     // Catch:{ all -> 0x00eb }
            java.lang.String r8 = "miio-localapi"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00eb }
            r9.<init>()     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00eb }
            r9.append(r0)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00eb }
            r9.append(r0)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r9.toString()     // Catch:{ all -> 0x00eb }
            android.util.Log.e(r8, r0)     // Catch:{ all -> 0x00eb }
            if (r1 == 0) goto L_0x0131
            if (r4 > 0) goto L_0x0131
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x00eb }
            com.xiaomi.miio.MiioLocalErrorCode r3 = com.xiaomi.miio.MiioLocalErrorCode.EXCEPTION     // Catch:{ all -> 0x00eb }
            r0.<init>(r3)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x00eb }
            r1.onResponse(r0)     // Catch:{ all -> 0x00eb }
            if (r5 == 0) goto L_0x0197
            goto L_0x002b
        L_0x0131:
            if (r5 == 0) goto L_0x017c
        L_0x0133:
            r5.close()     // Catch:{ all -> 0x0192 }
            goto L_0x017c
        L_0x0137:
            r0 = move-exception
            r7 = r18
        L_0x013a:
            java.io.StringWriter r6 = new java.io.StringWriter     // Catch:{ all -> 0x00eb }
            r6.<init>()     // Catch:{ all -> 0x00eb }
            java.io.PrintWriter r8 = new java.io.PrintWriter     // Catch:{ all -> 0x00eb }
            r8.<init>(r6)     // Catch:{ all -> 0x00eb }
            r0.printStackTrace(r8)     // Catch:{ all -> 0x00eb }
            java.lang.String r8 = "miio-localapi"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x00eb }
            r9.<init>()     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00eb }
            r9.append(r0)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r6.toString()     // Catch:{ all -> 0x00eb }
            r9.append(r0)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r9.toString()     // Catch:{ all -> 0x00eb }
            android.util.Log.e(r8, r0)     // Catch:{ all -> 0x00eb }
            if (r1 == 0) goto L_0x0179
            if (r4 > 0) goto L_0x0179
            com.xiaomi.miio.MiioLocalRpcResult r0 = new com.xiaomi.miio.MiioLocalRpcResult     // Catch:{ all -> 0x00eb }
            com.xiaomi.miio.MiioLocalErrorCode r3 = com.xiaomi.miio.MiioLocalErrorCode.TIMEOUT     // Catch:{ all -> 0x00eb }
            r0.<init>(r3)     // Catch:{ all -> 0x00eb }
            java.lang.String r0 = r0.a()     // Catch:{ all -> 0x00eb }
            r1.onResponse(r0)     // Catch:{ all -> 0x00eb }
            if (r5 == 0) goto L_0x0197
            goto L_0x002b
        L_0x0179:
            if (r5 == 0) goto L_0x017c
            goto L_0x0133
        L_0x017c:
            r8 = 2000(0x7d0, double:9.88E-321)
            java.lang.Thread.sleep(r8)     // Catch:{ InterruptedException -> 0x0183 }
            goto L_0x000f
        L_0x0183:
            java.lang.String r0 = "sleep"
            java.lang.String r6 = "get_token InterruptedException"
            android.util.Log.i(r0, r6)     // Catch:{ all -> 0x0192 }
            goto L_0x000f
        L_0x018c:
            if (r6 == 0) goto L_0x0194
            r6.close()     // Catch:{ all -> 0x0192 }
            goto L_0x0194
        L_0x0192:
            r0 = move-exception
            goto L_0x0195
        L_0x0194:
            throw r0     // Catch:{ all -> 0x0192 }
        L_0x0195:
            monitor-exit(r2)
            throw r0
        L_0x0197:
            monitor-exit(r2)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.miio.MiioLocalAPI.b(java.lang.String, com.xiaomi.miio.MiioLocalRpcResponse, int, int, com.xiaomi.miio.MiioLocalAPI$Cancelable):void");
    }

    public static class Cancelable {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public boolean f11147a;

        public void a() {
            this.f11147a = true;
        }
    }
}
