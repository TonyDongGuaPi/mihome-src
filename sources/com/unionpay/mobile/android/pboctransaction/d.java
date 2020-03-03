package com.unionpay.mobile.android.pboctransaction;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import cn.com.fmsh.communication.contants.Contants;
import cn.com.fmsh.script.constants.ScriptToolsConst;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.daimajia.numberprogressbar.BuildConfig;
import com.unionpay.mobile.android.fully.a;
import com.unionpay.mobile.android.model.c;
import com.unionpay.mobile.android.pboctransaction.samsung.f;
import com.unionpay.mobile.android.utils.k;
import com.unionpay.tsmservice.data.Constant;
import java.nio.ByteBuffer;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miuipub.reflect.Field;

public final class d {

    /* renamed from: a  reason: collision with root package name */
    public static String f9642a = "A0000003330101010000000000010000";
    public static String b = "A0000003330101020001050001000000";
    private static Date l = new Date(System.currentTimeMillis());
    private static String m = new SimpleDateFormat("yyyyMMddhhmmss").format(l);
    private static HashMap<String, String> o = new HashMap<>();
    private static d s = null;
    c c;
    a d;
    public boolean e = false;
    private String f = BuildConfig.VERSION_NAME;
    private byte g = 0;
    private byte h = 0;
    private byte i = 1;
    private boolean j = true;
    private boolean k = true;
    private String n = null;
    private final String p = "A0000003334355502D4D4F42494C45";
    private boolean q = false;
    private String r = "";

    public d(c cVar, a aVar, String str) {
        this.f = str;
        this.c = cVar;
        this.d = aVar;
    }

    private static String a(String str, String str2) {
        int i2;
        int i3;
        byte b2;
        byte b3;
        int i4;
        byte b4;
        int i5;
        int i6;
        int i7;
        byte b5;
        if (str == null) {
            return null;
        }
        byte[] a2 = e.a(str);
        int i8 = 0;
        while (i8 < a2.length) {
            int i9 = 1;
            int i10 = ((byte) (a2[i8] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i10];
            System.arraycopy(a2, i8, bArr, 0, i10);
            if (e.a(bArr, i10).compareToIgnoreCase(str2) == 0) {
                int i11 = i8 + i10;
                if (((byte) (a2[i11] & 128)) != Byte.MIN_VALUE) {
                    b3 = a2[i11];
                } else {
                    i9 = 1 + (a2[i11] & Byte.MAX_VALUE);
                    if (i9 == 2) {
                        b3 = a2[i11 + 1];
                    } else {
                        if (i9 == 3) {
                            i3 = (a2[i11 + 1] & 255) << 8;
                            b2 = a2[i11 + 2];
                        } else if (i9 == 4) {
                            i3 = ((a2[i11 + 1] & 255) << 16) | ((a2[i11 + 2] & 255) << 8);
                            b2 = a2[i11 + 3];
                        } else {
                            i2 = 0;
                            byte[] bArr2 = new byte[i2];
                            System.arraycopy(a2, i11 + i9, bArr2, 0, i2);
                            return e.a(bArr2, i2);
                        }
                        i2 = i3 | (b2 & 255);
                        byte[] bArr22 = new byte[i2];
                        System.arraycopy(a2, i11 + i9, bArr22, 0, i2);
                        return e.a(bArr22, i2);
                    }
                }
                i2 = b3 & 255;
                byte[] bArr222 = new byte[i2];
                System.arraycopy(a2, i11 + i9, bArr222, 0, i2);
                return e.a(bArr222, i2);
            }
            if ((a2[i8] & 32) == 32) {
                i4 = i8 + i10;
                if (i4 < a2.length && ((byte) (a2[i4] & 128)) == Byte.MIN_VALUE) {
                    i9 = 1 + (a2[i4] & Byte.MAX_VALUE);
                }
            } else {
                i4 = i8 + i10;
                if (i4 >= a2.length || ((byte) (a2[i4] & 128)) != 0) {
                    i9 = i4 < a2.length ? (a2[i4] & Byte.MAX_VALUE) + 1 : 0;
                    if (i9 == 2 && (i7 = i4 + 1) < a2.length) {
                        b5 = a2[i7];
                    } else if (i9 != 3 || (i6 = i4 + 2) >= a2.length) {
                        b4 = (i9 != 4 || (i5 = i4 + 2) >= a2.length) ? 0 : ((a2[i5] & 255) << 8) | ((a2[i4 + 1] & 255) << 16) | (a2[i4 + 3] & 255);
                        i9 += b4;
                    } else {
                        b4 = (a2[i6] & 255) | ((a2[i4 + 1] & 255) << 8);
                        i9 += b4;
                    }
                } else {
                    b5 = a2[i4];
                }
                b4 = b5 & 255;
                i9 += b4;
            }
            i8 = i4 + i9;
        }
        return null;
    }

    private static String a(String str, boolean z) {
        int i2;
        byte[] bArr;
        StringBuffer stringBuffer = new StringBuffer();
        byte[] bytes = str.toUpperCase().getBytes();
        int length = bytes.length;
        for (int i3 = 0; i3 < length; i3++) {
            stringBuffer.append(String.format("%02X", new Object[]{Byte.valueOf(bytes[i3])}));
        }
        int length2 = (stringBuffer.length() / 2) + (stringBuffer.length() % 2);
        if (!z) {
            int i4 = length2 % 8;
            i2 = i4 != 0 ? (8 - i4) + length2 : length2;
            bArr = new byte[i2];
            System.arraycopy(e.a(stringBuffer.toString()), 0, bArr, 0, length2);
        } else {
            int i5 = length2 + 1;
            int i6 = i5 % 8;
            if (i6 != 0) {
                i5 += 8 - i6;
            }
            bArr = new byte[i2];
            System.arraycopy(e.a(stringBuffer.toString()), 0, bArr, 0, length2);
            bArr[length2] = Byte.MIN_VALUE;
        }
        return e.a(bArr, i2);
    }

    private String a(byte[] bArr) {
        bArr[0] = (byte) (bArr[0] | this.g);
        byte[] a2 = this.c.a(bArr, (int) this.g);
        int length = a2 != null ? a2.length : 0;
        if (length >= 2 && a2[length - 2] == 97) {
            a2 = this.c.a(new byte[]{this.g, Constants.TagName.STATION_ENAME, 0, 0, a2[length - 1]}, (int) this.g);
            length = a2 != null ? a2.length : 0;
        }
        if (length >= 2 && a2[length - 2] == 108) {
            bArr[bArr.length - 1] = a2[length - 1];
            a2 = this.c.a(bArr, (int) this.g);
            length = a2 != null ? a2.length : 0;
        }
        if (length > 2) {
            int i2 = length - 2;
            if (a2[i2] == -112 && a2[length - 1] == 0) {
                return e.a(a2, i2);
            }
        }
        if (length == 2 && a2[length - 2] == -112 && a2[length - 1] == 0) {
            return e.a(a2, 2);
        }
        return null;
    }

    private String a(byte[] bArr, String str) {
        bArr[4] = (byte) (str.length() / 2);
        byte[] bArr2 = new byte[((str.length() / 2) + 5)];
        System.arraycopy(bArr, 0, bArr2, 0, 5);
        System.arraycopy(e.a(str), 0, bArr2, 5, str.length() / 2);
        return a(bArr2);
    }

    private static void a(String str, StringBuffer stringBuffer) {
        String str2 = o.get(str);
        String a2 = e.a(new byte[]{(byte) (str2.length() / 2)}, 1);
        stringBuffer.append(str);
        stringBuffer.append(a2);
        stringBuffer.append(str2);
    }

    private String b(String str) {
        if (this.c instanceof f) {
            return this.c.a(str);
        }
        this.g = this.h;
        if (str == null) {
            return null;
        }
        String a2 = e.a(new byte[]{Integer.valueOf(str.length() / 2).byteValue()});
        return a(e.a("00a40400" + a2 + str));
    }

    private void b(byte[] bArr) {
        int length = (m.length() / 2) + 1;
        byte[] bArr2 = new byte[length];
        System.arraycopy(e.a(m), 0, bArr2, 0, m.length() / 2);
        bArr2[length - 1] = Byte.MIN_VALUE;
        bArr[4] = (byte) bArr2.length;
        byte[] bArr3 = new byte[(bArr2.length + 5)];
        System.arraycopy(bArr, 0, bArr3, 0, 5);
        System.arraycopy(bArr2, 0, bArr3, 5, bArr2.length);
        a(bArr3);
    }

    private String c(String str) {
        byte[] a2;
        if (str == null || Contants.Message.PACKET_CODE_DEFAULT.equals(str)) {
            StringBuffer stringBuffer = new StringBuffer("80A800000b8309");
            for (String next : i("9F7A019F02065F2A02")) {
                Iterator<String> it = o.keySet().iterator();
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    String next2 = it.next();
                    if (next.compareToIgnoreCase(next2) == 0) {
                        stringBuffer.append(o.get(next2));
                        break;
                    }
                }
            }
            a2 = e.a(stringBuffer.toString());
        } else {
            k.c("uppay", "test for gongshang version 2");
            StringBuffer stringBuffer2 = new StringBuffer("");
            String a3 = a(str, "9F38");
            if (TextUtils.isEmpty(a3)) {
                ByteBuffer allocate = ByteBuffer.allocate(7);
                allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) 2).put((byte) -125).put((byte) 0);
                a2 = allocate.array();
            } else {
                for (String next3 : i(a3)) {
                    Iterator<String> it2 = o.keySet().iterator();
                    while (true) {
                        if (!it2.hasNext()) {
                            break;
                        }
                        String next4 = it2.next();
                        if (next3.compareToIgnoreCase(next4) == 0) {
                            stringBuffer2.append(o.get(next4));
                            break;
                        }
                    }
                }
                byte[] a4 = e.a(stringBuffer2.toString());
                ByteBuffer allocate2 = ByteBuffer.allocate(a4.length + 7);
                allocate2.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a4.length + 2)).put((byte) -125).put((byte) a4.length).put(a4);
                a2 = allocate2.array();
            }
        }
        return a(a2);
    }

    /* JADX WARNING: Removed duplicated region for block: B:7:0x0050 A[Catch:{ Exception -> 0x00b7 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.lang.String d(java.lang.String r14) {
        /*
            r13 = this;
            java.lang.String r0 = "80"
            java.lang.String r1 = "8C"
            java.lang.String r2 = "8D"
            java.lang.String r14 = a((java.lang.String) r14, (java.lang.String) r0)
            if (r14 != 0) goto L_0x000e
            r14 = 0
            return r14
        L_0x000e:
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o
            java.lang.String r3 = "82"
            r4 = 0
            r5 = 4
            java.lang.String r6 = r14.substring(r4, r5)
            r0.put(r3, r6)
            java.lang.String r14 = r14.substring(r5)
            byte[] r14 = com.unionpay.mobile.android.pboctransaction.e.a((java.lang.String) r14)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.lang.String r3 = "5A"
            r0.add(r3)
            java.lang.String r3 = "5F34"
            r0.add(r3)
            java.lang.String r3 = "9F1F"
            r0.add(r3)
            java.lang.String r3 = "57"
            r0.add(r3)
            java.lang.String r3 = "5F24"
            r0.add(r3)
            java.lang.String r3 = "9F10"
            r0.add(r3)
            r0.add(r1)
            r0.add(r2)
            r2 = 0
        L_0x004d:
            int r3 = r14.length     // Catch:{ Exception -> 0x00b7 }
            if (r2 >= r3) goto L_0x0097
            r3 = 5
            byte[] r3 = new byte[r3]     // Catch:{ Exception -> 0x00b7 }
            r3 = {0, -78, 0, 0, 0} // fill-array     // Catch:{ Exception -> 0x00b7 }
            byte[] r6 = new byte[r5]     // Catch:{ Exception -> 0x00b7 }
            java.lang.System.arraycopy(r14, r2, r6, r4, r5)     // Catch:{ Exception -> 0x00b7 }
            int r2 = r2 + 4
            r7 = 1
            byte r7 = r6[r7]     // Catch:{ Exception -> 0x00b7 }
        L_0x0060:
            r8 = 2
            byte r9 = r6[r8]     // Catch:{ Exception -> 0x00b7 }
            if (r7 > r9) goto L_0x004d
            r3[r5] = r4     // Catch:{ Exception -> 0x00b7 }
            r9 = 3
            byte r10 = r6[r4]     // Catch:{ Exception -> 0x00b7 }
            r10 = r10 & -8
            r10 = r10 | r5
            byte r10 = (byte) r10     // Catch:{ Exception -> 0x00b7 }
            r3[r9] = r10     // Catch:{ Exception -> 0x00b7 }
            r3[r8] = r7     // Catch:{ Exception -> 0x00b7 }
            int r7 = r7 + 1
            byte r7 = (byte) r7     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r8 = r13.a((byte[]) r3)     // Catch:{ Exception -> 0x00b7 }
            if (r8 == 0) goto L_0x0060
            java.util.Iterator r9 = r0.iterator()     // Catch:{ Exception -> 0x00b7 }
        L_0x007f:
            boolean r10 = r9.hasNext()     // Catch:{ Exception -> 0x00b7 }
            if (r10 == 0) goto L_0x0060
            java.lang.Object r10 = r9.next()     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r10 = (java.lang.String) r10     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r11 = a((java.lang.String) r8, (java.lang.String) r10)     // Catch:{ Exception -> 0x00b7 }
            if (r11 == 0) goto L_0x007f
            java.util.HashMap<java.lang.String, java.lang.String> r12 = o     // Catch:{ Exception -> 0x00b7 }
            r12.put(r10, r11)     // Catch:{ Exception -> 0x00b7 }
            goto L_0x007f
        L_0x0097:
            java.lang.StringBuffer r14 = new java.lang.StringBuffer     // Catch:{ Exception -> 0x00b7 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r2 = "5F34"
            java.lang.Object r0 = r0.get(r2)     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ Exception -> 0x00b7 }
            r14.<init>(r0)     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r0 = "0"
            r14.insert(r4, r0)     // Catch:{ Exception -> 0x00b7 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o     // Catch:{ Exception -> 0x00b7 }
            java.lang.String r2 = "5F34"
            java.lang.String r14 = r14.toString()     // Catch:{ Exception -> 0x00b7 }
            r0.put(r2, r14)     // Catch:{ Exception -> 0x00b7 }
            goto L_0x00bb
        L_0x00b7:
            r14 = move-exception
            r14.printStackTrace()
        L_0x00bb:
            java.util.HashMap<java.lang.String, java.lang.String> r14 = o
            java.lang.Object r14 = r14.get(r1)
            java.lang.String r14 = (java.lang.String) r14
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.d.d(java.lang.String):java.lang.String");
    }

    private static void d() {
        String str;
        String substring = m.substring(2, 8);
        long time = new Date(System.currentTimeMillis()).getTime();
        String valueOf = String.valueOf(time);
        if (valueOf.length() < 8) {
            str = String.format("%08d", new Object[]{Long.valueOf(time)});
        } else {
            str = valueOf.substring(valueOf.length() - 8, valueOf.length());
        }
        o.put("9F26", "");
        o.put("9F27", "80");
        o.put("9F10", "");
        o.put("9F37", str);
        o.put("9F36", "");
        o.put("95", "0000000800");
        o.put("9A", substring);
        o.put("9C", "45");
        o.put("9F02", Constant.DEFAULT_BALANCE);
        o.put("5F2A", "0156");
        o.put("82", "");
        o.put("9F1A", "0156");
        o.put("9F03", Constant.DEFAULT_BALANCE);
        o.put("9F33", "A04000");
        o.put("9F34", "420300");
        o.put("9F35", "34");
        o.put("9F1E", "3030303030303030");
        o.put("84", "");
        o.put("9F09", "0001");
        o.put("9F74", "");
        o.put("9F63", "");
        o.put("9F7A", "01");
        o.put("9F21", m.substring(8));
        o.put("9F4E", "0000000000000000000000000000000000000000");
        o.put("DF31", "0100000000");
        o.put("9F66", "36800000");
        o.put("DF60", "00");
    }

    private String e(String str) {
        StringBuffer stringBuffer = new StringBuffer("80AE800034");
        for (String next : i(str)) {
            Iterator<String> it = o.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next2 = it.next();
                if (next.compareToIgnoreCase(next2) == 0) {
                    stringBuffer.append(o.get(next2));
                    break;
                }
            }
        }
        String a2 = a(e.a(stringBuffer.toString()));
        if (a2 != null) {
            o.put("9F27", a2.substring(4, 6));
            o.put("9F36", a2.substring(6, 10));
            o.put("9F26", a2.substring(10, 26));
            o.put("9F10", a2.substring(26));
        }
        return a2;
    }

    private boolean e() {
        String str = o.get("5A");
        while (str.substring(str.length() - 1, str.length()).equalsIgnoreCase("f")) {
            str = str.substring(0, str.length() - 1);
        }
        String f2 = f(str);
        if (!(f2 == null || f2.length() == 0)) {
            o.put("AN1", f2);
            String f3 = f("00000001");
            if (!(f3 == null || f3.length() == 0)) {
                o.put("TID", f3);
                String f4 = f(o.get("9F02"));
                if (!(f4 == null || f4.length() == 0)) {
                    o.put("AMT", f4);
                    String f5 = f("156");
                    if (!(f5 == null || f5.length() == 0)) {
                        o.put("CUR", f5);
                        String str2 = o.get("57");
                        while (true) {
                            if (!str2.substring(str2.length() - 1, str2.length()).equalsIgnoreCase("f") && !str2.substring(str2.length() - 1, str2.length()).equalsIgnoreCase(Field.g)) {
                                break;
                            }
                            str2 = str2.substring(0, str2.length() - 1);
                        }
                        String f6 = f(str2);
                        if (f6 == null || f6.length() == 0) {
                            return false;
                        }
                        o.put("TD2", f6);
                        if (o.get("5F24") != null && o.get("5F24").length() == 6) {
                            String f7 = f(o.get("5F24").substring(0, 4));
                            if (f7 == null || f7.length() == 0) {
                                return false;
                            }
                            o.put("ED", f7);
                        }
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private String f() {
        this.g = this.i;
        String a2 = a(new byte[]{0, -80, -126, 0, 10});
        if (!(a2 == null || a2.length() == 0)) {
            o.put("CSN", a2);
        }
        this.g = this.h;
        return a2;
    }

    private String f(String str) {
        this.g = this.i;
        String a2 = a(str, false);
        b(new byte[]{Byte.MIN_VALUE, 26, 19, 1, 0});
        String a3 = a(new byte[]{Byte.MIN_VALUE, -6, 0, 0, 0}, a2);
        this.g = this.h;
        return a3;
    }

    private static Bundle g() {
        Bundle bundle = new Bundle();
        bundle.putString("action_resp_code", "0000");
        return bundle;
    }

    private String g(String str) {
        this.g = this.i;
        byte[] bArr = {Byte.MIN_VALUE, 26, 20, 1, 0};
        byte[] bArr2 = {Byte.MIN_VALUE, -6, 0, 0, 0};
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%02d", new Object[]{Integer.valueOf(str.length())}));
        sb.append(str);
        String sb2 = sb.toString();
        StringBuffer stringBuffer = new StringBuffer(sb2);
        for (int i2 = 0; i2 < 16 - sb2.length(); i2++) {
            stringBuffer.append(Field.g);
        }
        b(bArr);
        String a2 = a(bArr2, stringBuffer.toString());
        if (a2 != null) {
            o.put("PIN", a2);
        }
        this.g = this.h;
        return a2;
    }

    private String h(String str) {
        this.g = this.i;
        byte[] bArr = {Byte.MIN_VALUE, -6, 1, 0, 0};
        String a2 = a(str, true);
        b(new byte[]{Byte.MIN_VALUE, 26, 21, 1, 8});
        while (a2.length() > 448) {
            bArr[2] = 3;
            a(bArr, a2.substring(0, 448).toUpperCase());
            a2 = a2.substring(448);
        }
        bArr[2] = 1;
        if (Build.VERSION.SDK_INT <= 10) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e2) {
                e2.printStackTrace();
            }
        }
        String a3 = a(bArr, a2);
        k.c("uppay", "encryptMac in resp" + a3);
        if (a3 != null) {
            o.put("MAC", a3.toUpperCase());
        }
        this.g = this.h;
        return a3 != null ? a3.toUpperCase() : a3;
    }

    private static List<String> i(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        byte[] a2 = e.a(str);
        int i2 = 0;
        while (i2 < a2.length) {
            int i3 = 1;
            int i4 = ((byte) (a2[i2] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i4];
            System.arraycopy(a2, i2, bArr, 0, i4);
            arrayList.add(e.a(bArr, i4));
            int i5 = i2 + i4;
            if (i5 < a2.length && ((byte) (a2[i5] & 128)) == Byte.MIN_VALUE) {
                i3 = 1 + (a2[i5] & Byte.MAX_VALUE);
            }
            i2 = i5 + i3;
        }
        return arrayList;
    }

    /* JADX WARNING: Unknown top exception splitter block from list: {B:50:0x01b0=Splitter:B:50:0x01b0, B:46:0x01a2=Splitter:B:46:0x01a2, B:42:0x0194=Splitter:B:42:0x0194, B:54:0x01be=Splitter:B:54:0x01be} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.os.Bundle a(int r8, java.lang.String r9, java.util.HashMap<java.lang.String, java.lang.String> r10, java.lang.String r11) {
        /*
            r7 = this;
            monitor-enter(r7)
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "startUPCardPurchase() +++"
            com.unionpay.mobile.android.utils.k.c(r0, r1)     // Catch:{ all -> 0x01cc }
            android.os.Bundle r0 = g()     // Catch:{ all -> 0x01cc }
            java.lang.String r1 = ""
            com.unionpay.mobile.android.pboctransaction.c r2 = r7.c     // Catch:{ all -> 0x01cc }
            r2.b()     // Catch:{ all -> 0x01cc }
            java.lang.String r2 = r7.a()     // Catch:{ all -> 0x01cc }
            if (r2 == 0) goto L_0x01be
            int r2 = r2.length()     // Catch:{ all -> 0x01cc }
            if (r2 != 0) goto L_0x0021
            goto L_0x01be
        L_0x0021:
            java.lang.String r2 = "uppay"
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x01cc }
            java.lang.String r4 = " ************T1="
            r3.<init>(r4)     // Catch:{ all -> 0x01cc }
            java.lang.String r4 = m     // Catch:{ all -> 0x01cc }
            r3.append(r4)     // Catch:{ all -> 0x01cc }
            java.lang.String r3 = r3.toString()     // Catch:{ all -> 0x01cc }
            com.unionpay.mobile.android.utils.k.c(r2, r3)     // Catch:{ all -> 0x01cc }
            java.util.HashMap<java.lang.String, java.lang.String> r2 = o     // Catch:{ all -> 0x01cc }
            java.lang.String r3 = "PIN"
            r2.put(r3, r9)     // Catch:{ all -> 0x01cc }
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ all -> 0x01cc }
            java.lang.String r2 = "PIN"
            java.lang.Object r9 = r9.get(r2)     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = com.unionpay.mobile.android.utils.PreferenceUtils.decPrefData(r9, r11)     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = r7.g(r9)     // Catch:{ all -> 0x01cc }
            if (r9 == 0) goto L_0x01b0
            int r9 = r9.length()     // Catch:{ all -> 0x01cc }
            if (r9 != 0) goto L_0x0059
            goto L_0x01b0
        L_0x0059:
            java.lang.String r9 = "uppay"
            java.lang.StringBuilder r11 = new java.lang.StringBuilder     // Catch:{ all -> 0x01cc }
            java.lang.String r2 = " ************T2="
            r11.<init>(r2)     // Catch:{ all -> 0x01cc }
            java.lang.String r2 = m     // Catch:{ all -> 0x01cc }
            r11.append(r2)     // Catch:{ all -> 0x01cc }
            java.lang.String r11 = r11.toString()     // Catch:{ all -> 0x01cc }
            com.unionpay.mobile.android.utils.k.c(r9, r11)     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = m     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = r7.a((int) r8, (java.lang.String) r9)     // Catch:{ all -> 0x01cc }
            if (r8 == 0) goto L_0x01a2
            int r9 = r8.length()     // Catch:{ all -> 0x01cc }
            if (r9 != 0) goto L_0x007e
            goto L_0x01a2
        L_0x007e:
            java.lang.String r9 = r7.f()     // Catch:{ all -> 0x01cc }
            if (r9 == 0) goto L_0x0194
            int r9 = r9.length()     // Catch:{ all -> 0x01cc }
            if (r9 != 0) goto L_0x008c
            goto L_0x0194
        L_0x008c:
            r9 = 40
            r11 = 60
            java.lang.String r9 = r8.substring(r9, r11)     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = com.unionpay.mobile.android.pboctransaction.e.c(r9)     // Catch:{ all -> 0x01cc }
            r2 = 100
            java.lang.String r11 = r8.substring(r11, r2)     // Catch:{ all -> 0x01cc }
            r3 = 208(0xd0, float:2.91E-43)
            java.lang.String r2 = r8.substring(r2, r3)     // Catch:{ all -> 0x01cc }
            r3 = 216(0xd8, float:3.03E-43)
            r4 = 232(0xe8, float:3.25E-43)
            java.lang.String r8 = r8.substring(r3, r4)     // Catch:{ all -> 0x01cc }
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0165 }
            r3.<init>()     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r4 = "v"
            java.lang.String r5 = r7.f     // Catch:{ JSONException -> 0x0165 }
            r3.put(r4, r5)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r4 = "cmd"
            java.lang.String r5 = "pay"
            r3.put(r4, r5)     // Catch:{ JSONException -> 0x0165 }
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x0165 }
            r4.<init>()     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "params"
            r3.put(r5, r4)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "pay_type"
            java.lang.String r6 = "2"
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "pay_mode"
            java.lang.String r6 = "1"
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "bind"
            java.lang.String r6 = "no"
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "carrier_tp"
            java.lang.String r6 = "1"
            r4.put(r5, r6)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "track2_data"
            r4.put(r5, r11)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "track3_data"
            r4.put(r11, r2)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "csn"
            java.util.HashMap<java.lang.String, java.lang.String> r2 = o     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "CSN"
            java.lang.Object r2 = r2.get(r5)     // Catch:{ JSONException -> 0x0165 }
            r4.put(r11, r2)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "submit_time"
            java.lang.String r2 = m     // Catch:{ JSONException -> 0x0165 }
            r4.put(r11, r2)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "sp_id"
            java.lang.String r2 = "8889"
            r4.put(r11, r2)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "pin"
            java.util.HashMap<java.lang.String, java.lang.String> r2 = o     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r5 = "PIN"
            java.lang.Object r2 = r2.get(r5)     // Catch:{ JSONException -> 0x0165 }
            r4.put(r11, r2)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r11 = "pan"
            r4.put(r11, r9)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r9 = "dynamic_key_data"
            r4.put(r9, r8)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r8 = "carrier_app_tp"
            java.lang.String r9 = "1"
            r4.put(r8, r9)     // Catch:{ JSONException -> 0x0165 }
            if (r10 == 0) goto L_0x0160
            java.util.Set r8 = r10.keySet()     // Catch:{ JSONException -> 0x0165 }
            if (r8 == 0) goto L_0x0160
            java.util.Set r8 = r10.keySet()     // Catch:{ JSONException -> 0x0165 }
            int r8 = r8.size()     // Catch:{ JSONException -> 0x0165 }
            if (r8 <= 0) goto L_0x0160
            java.lang.String r8 = "pan"
            r10.remove(r8)     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r8 = "pin"
            r10.remove(r8)     // Catch:{ JSONException -> 0x0165 }
            java.util.Set r8 = r10.keySet()     // Catch:{ JSONException -> 0x0165 }
            java.util.Iterator r8 = r8.iterator()     // Catch:{ JSONException -> 0x0165 }
        L_0x014c:
            boolean r9 = r8.hasNext()     // Catch:{ JSONException -> 0x0165 }
            if (r9 == 0) goto L_0x0160
            java.lang.Object r9 = r8.next()     // Catch:{ JSONException -> 0x0165 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ JSONException -> 0x0165 }
            java.lang.Object r11 = r10.get(r9)     // Catch:{ JSONException -> 0x0165 }
            r4.put(r9, r11)     // Catch:{ JSONException -> 0x0165 }
            goto L_0x014c
        L_0x0160:
            java.lang.String r8 = r3.toString()     // Catch:{ JSONException -> 0x0165 }
            goto L_0x016a
        L_0x0165:
            r8 = move-exception
            r8.printStackTrace()     // Catch:{ all -> 0x01cc }
            r8 = r1
        L_0x016a:
            java.lang.String r9 = "uppay"
            java.lang.StringBuilder r10 = new java.lang.StringBuilder     // Catch:{ all -> 0x01cc }
            java.lang.String r11 = " ************T3="
            r10.<init>(r11)     // Catch:{ all -> 0x01cc }
            java.lang.String r11 = m     // Catch:{ all -> 0x01cc }
            r10.append(r11)     // Catch:{ all -> 0x01cc }
            java.lang.String r10 = r10.toString()     // Catch:{ all -> 0x01cc }
            com.unionpay.mobile.android.utils.k.c(r9, r10)     // Catch:{ all -> 0x01cc }
            com.unionpay.mobile.android.fully.a r9 = r7.d     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = r9.a(r8)     // Catch:{ all -> 0x01cc }
            java.lang.String r9 = "action_resp_message"
            r0.putString(r9, r8)     // Catch:{ all -> 0x01cc }
            com.unionpay.mobile.android.pboctransaction.c r8 = r7.c     // Catch:{ all -> 0x01cc }
            r8.c()     // Catch:{ all -> 0x01cc }
            d()     // Catch:{ all -> 0x01cc }
            monitor-exit(r7)
            return r0
        L_0x0194:
            com.unionpay.mobile.android.pboctransaction.c r8 = r7.c     // Catch:{ all -> 0x01cc }
            r8.c()     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = "action_resp_code"
            java.lang.String r9 = "10019"
            r0.putString(r8, r9)     // Catch:{ all -> 0x01cc }
            monitor-exit(r7)
            return r0
        L_0x01a2:
            com.unionpay.mobile.android.pboctransaction.c r8 = r7.c     // Catch:{ all -> 0x01cc }
            r8.c()     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = "action_resp_code"
            java.lang.String r9 = "10019"
            r0.putString(r8, r9)     // Catch:{ all -> 0x01cc }
            monitor-exit(r7)
            return r0
        L_0x01b0:
            com.unionpay.mobile.android.pboctransaction.c r8 = r7.c     // Catch:{ all -> 0x01cc }
            r8.c()     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = "action_resp_code"
            java.lang.String r9 = "10019"
            r0.putString(r8, r9)     // Catch:{ all -> 0x01cc }
            monitor-exit(r7)
            return r0
        L_0x01be:
            com.unionpay.mobile.android.pboctransaction.c r8 = r7.c     // Catch:{ all -> 0x01cc }
            r8.c()     // Catch:{ all -> 0x01cc }
            java.lang.String r8 = "action_resp_code"
            java.lang.String r9 = "10019"
            r0.putString(r8, r9)     // Catch:{ all -> 0x01cc }
            monitor-exit(r7)
            return r0
        L_0x01cc:
            r8 = move-exception
            monitor-exit(r7)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.d.a(int, java.lang.String, java.util.HashMap, java.lang.String):android.os.Bundle");
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:138|139|140|141) */
    /* JADX WARNING: Code restructure failed: missing block: B:139:?, code lost:
        r3.c.c();
        r5.putString("action_resp_code", com.unionpay.tsmservice.data.ResultCode.ERROR_INTERFACE_GET_SMS_AUTH_CODE);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x04fe, code lost:
        return r5;
     */
    /* JADX WARNING: Missing exception handler attribute for start block: B:138:0x04f1 */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x01a8  */
    /* JADX WARNING: Removed duplicated region for block: B:49:0x01af  */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:112:0x03d1=Splitter:B:112:0x03d1, B:150:0x051b=Splitter:B:150:0x051b, B:146:0x050d=Splitter:B:146:0x050d, B:142:0x04ff=Splitter:B:142:0x04ff} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized android.os.Bundle a(com.unionpay.mobile.android.pboctransaction.AppIdentification r4, java.lang.String r5, java.lang.String r6, java.util.HashMap<java.lang.String, java.lang.String> r7, java.util.HashMap<java.lang.String, java.lang.String> r8, java.lang.String r9) {
        /*
            r3 = this;
            monitor-enter(r3)
            com.unionpay.mobile.android.pboctransaction.c r0 = r3.c     // Catch:{ all -> 0x0529 }
            r0.b()     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "uppay"
            java.lang.String r1 = "startPBOCPurchase() +++"
            com.unionpay.mobile.android.utils.k.c(r0, r1)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o     // Catch:{ all -> 0x0529 }
            r0.clear()     // Catch:{ all -> 0x0529 }
            d()     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "PIN"
            r0.put(r1, r5)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r5 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "9F02"
            java.lang.String r1 = "trans_amt"
            java.lang.Object r1 = r7.get(r1)     // Catch:{ all -> 0x0529 }
            r5.put(r0, r1)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r5 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "9F1A"
            java.lang.String r1 = "0156"
            r5.put(r0, r1)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r5 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "5F2A"
            java.lang.String r1 = "trans currcy code"
            java.lang.Object r1 = r7.get(r1)     // Catch:{ all -> 0x0529 }
            r5.put(r0, r1)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r5 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "9C"
            java.lang.String r1 = "trans_type"
            java.lang.Object r7 = r7.get(r1)     // Catch:{ all -> 0x0529 }
            r5.put(r0, r7)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r4.a()     // Catch:{ all -> 0x0529 }
            java.lang.String r5 = "A000000333"
            boolean r5 = r4.startsWith(r5)     // Catch:{ all -> 0x0529 }
            if (r5 != 0) goto L_0x006a
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            android.os.Bundle r4 = g()     // Catch:{ all -> 0x0529 }
            java.lang.String r5 = "action_resp_code"
            java.lang.String r6 = "10019"
            r4.putString(r5, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r4
        L_0x006a:
            android.os.Bundle r5 = g()     // Catch:{ all -> 0x0529 }
            java.sql.Date r7 = new java.sql.Date     // Catch:{ all -> 0x0529 }
            long r0 = java.lang.System.currentTimeMillis()     // Catch:{ all -> 0x0529 }
            r7.<init>(r0)     // Catch:{ all -> 0x0529 }
            l = r7     // Catch:{ all -> 0x0529 }
            java.text.SimpleDateFormat r7 = new java.text.SimpleDateFormat     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "yyyyMMddHHmmss"
            r7.<init>(r0)     // Catch:{ all -> 0x0529 }
            java.sql.Date r0 = l     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = r7.format(r0)     // Catch:{ all -> 0x0529 }
            m = r7     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = new java.lang.String     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = m     // Catch:{ all -> 0x0529 }
            r7.<init>(r0)     // Catch:{ all -> 0x0529 }
            r3.n = r7     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.String r0 = "selectUPCard"
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = r3.a()     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "uppay"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r2 = "selectUPCard return: "
            r1.<init>(r2)     // Catch:{ all -> 0x0529 }
            r1.append(r7)     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = r1.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r0, r1)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x0199
            int r7 = r7.length()     // Catch:{ all -> 0x0529 }
            if (r7 != 0) goto L_0x00b9
            goto L_0x0199
        L_0x00b9:
            java.lang.String r7 = "uppay"
            java.lang.String r0 = "selectPBOC"
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.b((java.lang.String) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "selectPBOC return: "
            r0.<init>(r1)     // Catch:{ all -> 0x0529 }
            r0.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x0194
            int r7 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r7 != 0) goto L_0x00e1
            goto L_0x0194
        L_0x00e1:
            java.lang.String r7 = "uppay"
            java.lang.String r0 = "GPO"
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.c(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "gpo return: "
            r0.<init>(r1)     // Catch:{ all -> 0x0529 }
            r0.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x018f
            int r7 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r7 != 0) goto L_0x0109
            goto L_0x018f
        L_0x0109:
            java.lang.String r7 = "uppay"
            java.lang.String r0 = "CDOL1"
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.d(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "CDOL1 return: "
            r0.<init>(r1)     // Catch:{ all -> 0x0529 }
            r0.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x018a
            int r7 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r7 != 0) goto L_0x0130
            goto L_0x018a
        L_0x0130:
            java.lang.String r7 = "uppay"
            java.lang.String r0 = "GAC1"
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.e(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "GAC1 return: "
            r0.<init>(r1)     // Catch:{ all -> 0x0529 }
            r0.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x0185
            int r4 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x0157
            goto L_0x0185
        L_0x0157:
            java.lang.String r4 = "uppay"
            java.lang.String r7 = "csn"
            com.unionpay.mobile.android.utils.k.c(r4, r7)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.f()     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "csn return: "
            r0.<init>(r1)     // Catch:{ all -> 0x0529 }
            r0.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r0)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x017d
            int r4 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x019e
        L_0x017d:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10019"
        L_0x0181:
            r5.putString(r4, r7)     // Catch:{ all -> 0x0529 }
            goto L_0x019e
        L_0x0185:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10019"
            goto L_0x0181
        L_0x018a:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10019"
            goto L_0x0181
        L_0x018f:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10020"
            goto L_0x0181
        L_0x0194:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10019"
            goto L_0x0181
        L_0x0199:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r7 = "10019"
            goto L_0x0181
        L_0x019e:
            java.lang.String r4 = "action_resp_code"
            java.lang.String r4 = r5.getString(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "0000"
            if (r4 == r7) goto L_0x01af
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x01af:
            java.lang.String r4 = "uppay"
            java.lang.String r7 = "encryptPIN"
            com.unionpay.mobile.android.utils.k.c(r4, r7)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "PIN"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = com.unionpay.mobile.android.utils.PreferenceUtils.decPrefData(r4, r9)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.g(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "encryptPIN return:"
            r9.<init>(r0)     // Catch:{ all -> 0x0529 }
            r9.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r9)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x051b
            int r4 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x01e5
            goto L_0x051b
        L_0x01e5:
            java.lang.String r4 = "uppay"
            java.lang.String r7 = "encryptData"
            com.unionpay.mobile.android.utils.k.c(r4, r7)     // Catch:{ all -> 0x0529 }
            boolean r4 = r3.e()     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x0207
            java.lang.String r4 = "uppay"
            java.lang.String r6 = "encryptData false"
            com.unionpay.mobile.android.utils.k.c(r4, r6)     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = "action_resp_code"
            java.lang.String r6 = "10019"
            r5.putString(r4, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x0207:
            java.lang.String r4 = "uppay"
            java.lang.String r7 = "initDCData"
            com.unionpay.mobile.android.utils.k.c(r4, r7)     // Catch:{ all -> 0x0529 }
            java.lang.StringBuffer r4 = new java.lang.StringBuffer     // Catch:{ all -> 0x0529 }
            r4.<init>()     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F26"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F27"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F10"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F37"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F36"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "95"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9A"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9C"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F02"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "5F2A"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "82"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F1A"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F03"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F33"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F34"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F35"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "9F1E"
            a((java.lang.String) r7, (java.lang.StringBuffer) r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r4.toString()     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "DCD"
            r7.put(r9, r4)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "TID"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x050d
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "AMT"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x050d
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "CUR"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x050d
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "AN1"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x050d
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "CSN"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x050d
            java.util.HashMap<java.lang.String, java.lang.String> r4 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "5F34"
            java.lang.Object r4 = r4.get(r7)     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x02b1
            goto L_0x050d
        L_0x02b1:
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ all -> 0x0529 }
            r4.<init>()     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = m     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "TID"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x02d0
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "TID"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x02d0:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "AMT"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x02e5
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "AMT"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x02e5:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "CUR"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x02fa
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "CUR"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x02fa:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "AN1"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x030f
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "AN1"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x030f:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "AN1"
            java.util.HashMap<java.lang.String, java.lang.String> r0 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r1 = "5A"
            java.lang.Object r0 = r0.get(r1)     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = (java.lang.String) r0     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = com.unionpay.mobile.android.pboctransaction.e.c(r0)     // Catch:{ all -> 0x0529 }
            r7.put(r9, r0)     // Catch:{ all -> 0x0529 }
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "CSN"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x0339
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "CSN"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x0339:
            boolean r7 = r3.q     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x0352
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "ED"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x0352
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "ED"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x0352:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "5F34"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x0367
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "5F34"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x0367:
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "DCD"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            if (r7 == 0) goto L_0x037c
            java.util.HashMap<java.lang.String, java.lang.String> r7 = o     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = "DCD"
            java.lang.Object r7 = r7.get(r9)     // Catch:{ all -> 0x0529 }
            r4.add(r7)     // Catch:{ all -> 0x0529 }
        L_0x037c:
            java.lang.String r7 = ""
            java.util.Iterator r4 = r4.iterator()     // Catch:{ all -> 0x0529 }
        L_0x0382:
            boolean r9 = r4.hasNext()     // Catch:{ all -> 0x0529 }
            if (r9 == 0) goto L_0x03a5
            java.lang.Object r9 = r4.next()     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0529 }
            if (r9 == 0) goto L_0x0382
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            r0.<init>()     // Catch:{ all -> 0x0529 }
            r0.append(r7)     // Catch:{ all -> 0x0529 }
            r0.append(r9)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = " "
            r0.append(r7)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = r0.toString()     // Catch:{ all -> 0x0529 }
            goto L_0x0382
        L_0x03a5:
            java.lang.String r4 = r7.trim()     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.String r9 = "encryptMac"
            com.unionpay.mobile.android.utils.k.c(r7, r9)     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r3.h(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r7 = "uppay"
            java.lang.StringBuilder r9 = new java.lang.StringBuilder     // Catch:{ all -> 0x0529 }
            java.lang.String r0 = "encryptMac result"
            r9.<init>(r0)     // Catch:{ all -> 0x0529 }
            r9.append(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r9 = r9.toString()     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.utils.k.c(r7, r9)     // Catch:{ all -> 0x0529 }
            if (r4 == 0) goto L_0x04ff
            int r4 = r4.length()     // Catch:{ all -> 0x0529 }
            if (r4 != 0) goto L_0x03d1
            goto L_0x04ff
        L_0x03d1:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch:{ JSONException -> 0x04f1 }
            r4.<init>()     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r7 = "v"
            java.lang.String r9 = r3.f     // Catch:{ JSONException -> 0x04f1 }
            r4.put(r7, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r7 = "cmd"
            java.lang.String r9 = "pay"
            r4.put(r7, r9)     // Catch:{ JSONException -> 0x04f1 }
            org.json.JSONObject r7 = new org.json.JSONObject     // Catch:{ JSONException -> 0x04f1 }
            r7.<init>()     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "params"
            r4.put(r9, r7)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "pay_type"
            java.lang.String r0 = "2"
            r7.put(r9, r0)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "pay_mode"
            java.lang.String r0 = "1"
            r7.put(r9, r0)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "bind"
            java.lang.String r0 = "no"
            r7.put(r9, r0)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "carrier_tp"
            r7.put(r9, r6)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "icc_data"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "DCD"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "csn"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "CSN"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "card_seq_id"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "5F34"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            if (r9 == 0) goto L_0x0439
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "5F34"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ JSONException -> 0x04f1 }
            goto L_0x043b
        L_0x0439:
            java.lang.String r9 = ""
        L_0x043b:
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "submit_time"
            java.lang.String r9 = m     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "sp_id"
            java.lang.String r9 = "8889"
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "pin"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "PIN"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "pan"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "AN1"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "carrier_app_tp"
            java.lang.String r9 = "2"
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
            java.util.HashMap<java.lang.String, java.lang.String> r6 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "ED"
            java.lang.Object r6 = r6.get(r9)     // Catch:{ JSONException -> 0x04f1 }
            if (r6 == 0) goto L_0x0484
            java.lang.String r6 = "expire"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "ED"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
        L_0x0484:
            java.util.HashMap<java.lang.String, java.lang.String> r6 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = "TD2"
            java.lang.Object r6 = r6.get(r9)     // Catch:{ JSONException -> 0x04f1 }
            if (r6 == 0) goto L_0x049b
            java.lang.String r6 = "track2_data"
            java.util.HashMap<java.lang.String, java.lang.String> r9 = o     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r0 = "TD2"
            java.lang.Object r9 = r9.get(r0)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r6, r9)     // Catch:{ JSONException -> 0x04f1 }
        L_0x049b:
            if (r8 == 0) goto L_0x04d3
            java.util.Set r6 = r8.keySet()     // Catch:{ JSONException -> 0x04f1 }
            if (r6 == 0) goto L_0x04d3
            java.util.Set r6 = r8.keySet()     // Catch:{ JSONException -> 0x04f1 }
            int r6 = r6.size()     // Catch:{ JSONException -> 0x04f1 }
            if (r6 <= 0) goto L_0x04d3
            java.lang.String r6 = "pan"
            r8.remove(r6)     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "pin"
            r8.remove(r6)     // Catch:{ JSONException -> 0x04f1 }
            java.util.Set r6 = r8.keySet()     // Catch:{ JSONException -> 0x04f1 }
            java.util.Iterator r6 = r6.iterator()     // Catch:{ JSONException -> 0x04f1 }
        L_0x04bf:
            boolean r9 = r6.hasNext()     // Catch:{ JSONException -> 0x04f1 }
            if (r9 == 0) goto L_0x04d3
            java.lang.Object r9 = r6.next()     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ JSONException -> 0x04f1 }
            java.lang.Object r0 = r8.get(r9)     // Catch:{ JSONException -> 0x04f1 }
            r7.put(r9, r0)     // Catch:{ JSONException -> 0x04f1 }
            goto L_0x04bf
        L_0x04d3:
            java.lang.String r4 = r4.toString()     // Catch:{ JSONException -> 0x04f1 }
            java.lang.String r6 = "uppay"
            com.unionpay.mobile.android.utils.k.c(r6, r4)     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.fully.a r6 = r3.d     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = r6.a(r4)     // Catch:{ all -> 0x0529 }
            java.lang.String r6 = "action_resp_message"
            r5.putString(r6, r4)     // Catch:{ all -> 0x0529 }
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            d()     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x04f1:
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = "action_resp_code"
            java.lang.String r6 = "10019"
            r5.putString(r4, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x04ff:
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = "action_resp_code"
            java.lang.String r6 = "10019"
            r5.putString(r4, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x050d:
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = "action_resp_code"
            java.lang.String r6 = "10019"
            r5.putString(r4, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x051b:
            com.unionpay.mobile.android.pboctransaction.c r4 = r3.c     // Catch:{ all -> 0x0529 }
            r4.c()     // Catch:{ all -> 0x0529 }
            java.lang.String r4 = "action_resp_code"
            java.lang.String r6 = "10019"
            r5.putString(r4, r6)     // Catch:{ all -> 0x0529 }
            monitor-exit(r3)
            return r5
        L_0x0529:
            r4 = move-exception
            monitor-exit(r3)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.d.a(com.unionpay.mobile.android.pboctransaction.AppIdentification, java.lang.String, java.lang.String, java.util.HashMap, java.util.HashMap, java.lang.String):android.os.Bundle");
    }

    public final String a() {
        if (this.c instanceof f) {
            return this.c.a("A0000003334355502D4D4F42494C45");
        }
        this.g = this.i;
        return a(new byte[]{0, ScriptToolsConst.TagName.CommandMultiple, 4, 0, 15, ScriptToolsConst.TagName.CommandSingle, 0, 0, 3, 51, Constants.TagName.TERMINAL_BACK_INFO_TYPE, Constants.TagName.TERMINAL_BACK_CHILDREN_ID, Constants.TagName.ORDER_BRIEF_INFO_LIST, 45, 77, Constants.TagName.CP_NO, Constants.TagName.INVOICE_TOKEN, Constants.TagName.ORDER_BRIEF_INFO, 76, Constants.TagName.TERMINAL_MODEL_NUMBER});
    }

    public final String a(int i2, String str) {
        String str2;
        this.g = this.i;
        if (Integer.toHexString(i2).length() == 1) {
            str2 = "0" + Integer.toHexString(i2);
        } else {
            str2 = Integer.toHexString(i2);
        }
        return a(e.a("80F802" + str2 + "08" + str + "80"));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0033, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0035, code lost:
        return null;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final synchronized java.lang.String a(com.unionpay.mobile.android.pboctransaction.AppIdentification r3) {
        /*
            r2 = this;
            monitor-enter(r2)
            java.lang.String r3 = r3.a()     // Catch:{ all -> 0x0036 }
            d()     // Catch:{ all -> 0x0036 }
            r2.b((java.lang.String) r3)     // Catch:{ all -> 0x0036 }
            r3 = 0
            java.lang.String r0 = r2.c(r3)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0034
            int r1 = r0.length()     // Catch:{ all -> 0x0036 }
            if (r1 != 0) goto L_0x0019
            goto L_0x0034
        L_0x0019:
            java.lang.String r0 = r2.d(r0)     // Catch:{ all -> 0x0036 }
            if (r0 == 0) goto L_0x0032
            int r0 = r0.length()     // Catch:{ all -> 0x0036 }
            if (r0 != 0) goto L_0x0026
            goto L_0x0032
        L_0x0026:
            java.util.HashMap<java.lang.String, java.lang.String> r3 = o     // Catch:{ all -> 0x0036 }
            java.lang.String r0 = "5A"
            java.lang.Object r3 = r3.get(r0)     // Catch:{ all -> 0x0036 }
            java.lang.String r3 = (java.lang.String) r3     // Catch:{ all -> 0x0036 }
            monitor-exit(r2)
            return r3
        L_0x0032:
            monitor-exit(r2)
            return r3
        L_0x0034:
            monitor-exit(r2)
            return r3
        L_0x0036:
            r3 = move-exception
            monitor-exit(r2)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.mobile.android.pboctransaction.d.a(com.unionpay.mobile.android.pboctransaction.AppIdentification):java.lang.String");
    }

    public final String a(String str) {
        this.c.d();
        String b2 = b(str);
        this.c.d();
        return a(b2, "84");
    }

    public final ArrayList<c> b() {
        this.c.d();
        this.c.b();
        ArrayList<c> a2 = this.c.a(this);
        this.c.c();
        return a2;
    }

    public final String c() {
        this.g = this.i;
        return a(e.a("80F2000102"));
    }
}
