package com.unionpay.mobile.android.pboctransaction.nfc;

import android.text.TextUtils;
import android.util.Log;
import cn.com.fmsh.tsm.business.constants.Constants;
import com.unionpay.mobile.android.pboctransaction.e;
import com.unionpay.mobile.android.pboctransaction.nfc.b;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import miuipub.reflect.Field;

public final class a {
    protected static final byte[] d = {50, Constants.TagName.ORDER_BRIEF_INFO_LIST, Constants.TagName.TERMINAL_BACK_CONTENT, 89, Constants.TagName.SIM_SEID, 83, 89, 83, Constants.TagName.SIM_SEID, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_OS_VERSION, Constants.TagName.TERMINAL_BASEBAND_VERSION, 48, 49};

    /* renamed from: a  reason: collision with root package name */
    protected String f9646a = "UnionPay Card";
    com.unionpay.mobile.android.fully.a b;
    b.C0075b c;

    public a(com.unionpay.mobile.android.fully.a aVar, b.C0075b bVar) {
        this.b = aVar;
        this.c = bVar;
    }

    private static String a(String str, String str2) {
        int i;
        int i2;
        byte b2;
        byte b3;
        int i3;
        byte b4;
        int i4;
        int i5;
        int i6;
        byte b5;
        if (str == null) {
            return null;
        }
        byte[] a2 = e.a(str);
        int i7 = 0;
        while (i7 < a2.length) {
            int i8 = 1;
            int i9 = ((byte) (a2[i7] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i9];
            System.arraycopy(a2, i7, bArr, 0, i9);
            if (e.a(bArr, i9).compareToIgnoreCase(str2) == 0) {
                int i10 = i7 + i9;
                if (((byte) (a2[i10] & 128)) != Byte.MIN_VALUE) {
                    b3 = a2[i10];
                } else {
                    i8 = 1 + (a2[i10] & Byte.MAX_VALUE);
                    if (i8 == 2) {
                        b3 = a2[i10 + 1];
                    } else {
                        if (i8 == 3) {
                            i2 = (a2[i10 + 1] & 255) << 8;
                            b2 = a2[i10 + 2];
                        } else if (i8 == 4) {
                            i2 = ((a2[i10 + 1] & 255) << 16) | ((a2[i10 + 2] & 255) << 8);
                            b2 = a2[i10 + 3];
                        } else {
                            i = 0;
                            byte[] bArr2 = new byte[i];
                            System.arraycopy(a2, i10 + i8, bArr2, 0, i);
                            return e.a(bArr2, i);
                        }
                        i = i2 | (b2 & 255);
                        byte[] bArr22 = new byte[i];
                        System.arraycopy(a2, i10 + i8, bArr22, 0, i);
                        return e.a(bArr22, i);
                    }
                }
                i = b3 & 255;
                byte[] bArr222 = new byte[i];
                System.arraycopy(a2, i10 + i8, bArr222, 0, i);
                return e.a(bArr222, i);
            }
            if ((a2[i7] & 32) == 32) {
                i3 = i7 + i9;
                if (i3 < a2.length && ((byte) (a2[i3] & 128)) == Byte.MIN_VALUE) {
                    i8 = 1 + (a2[i3] & Byte.MAX_VALUE);
                }
            } else {
                i3 = i7 + i9;
                if (i3 >= a2.length || ((byte) (a2[i3] & 128)) != 0) {
                    i8 = i3 < a2.length ? (a2[i3] & Byte.MAX_VALUE) + 1 : 0;
                    if (i8 == 2 && (i6 = i3 + 1) < a2.length) {
                        b5 = a2[i6];
                    } else if (i8 != 3 || (i5 = i3 + 2) >= a2.length) {
                        b4 = (i8 != 4 || (i4 = i3 + 2) >= a2.length) ? 0 : ((a2[i4] & 255) << 8) | ((a2[i3 + 1] & 255) << 16) | (a2[i3 + 3] & 255);
                        i8 += b4;
                    } else {
                        b4 = (a2[i5] & 255) | ((a2[i3 + 1] & 255) << 8);
                        i8 += b4;
                    }
                } else {
                    b5 = a2[i3];
                }
                b4 = b5 & 255;
                i8 += b4;
            }
            i7 = i3 + i8;
        }
        return null;
    }

    private static List<String> a(String str) {
        ArrayList arrayList = new ArrayList();
        if (str == null) {
            return arrayList;
        }
        byte[] a2 = e.a(str);
        int i = 0;
        while (i < a2.length) {
            int i2 = 1;
            int i3 = ((byte) (a2[i] & 31)) == 31 ? 2 : 1;
            byte[] bArr = new byte[i3];
            System.arraycopy(a2, i, bArr, 0, i3);
            arrayList.add(e.a(bArr, i3));
            int i4 = i + i3;
            if (i4 < a2.length && ((byte) (a2[i4] & 128)) == Byte.MIN_VALUE) {
                i2 = 1 + (a2[i4] & Byte.MAX_VALUE);
            }
            i = i4 + i2;
        }
        return arrayList;
    }

    public static void b(String str, HashMap<String, String> hashMap) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("82");
        arrayList.add("9F36");
        arrayList.add("9F10");
        arrayList.add("9F26");
        arrayList.add("5F34");
        arrayList.add("57");
        arrayList.add("9F63");
        int i = 0;
        while (i < arrayList.size()) {
            try {
                String str2 = (String) arrayList.get(i);
                String a2 = a(str, str2);
                if (a2 != null) {
                    hashMap.put(str2, a2);
                }
                i++;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        StringBuffer stringBuffer = new StringBuffer(hashMap.get("5F34"));
        stringBuffer.insert(0, "0");
        hashMap.put("5F34", stringBuffer.toString());
        String str3 = hashMap.get("57");
        while (true) {
            if (!str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase("f")) {
                if (!str3.substring(str3.length() - 1, str3.length()).equalsIgnoreCase(Field.g)) {
                    break;
                }
            }
            str3 = str3.substring(0, str3.length() - 1);
        }
        hashMap.put("TD2", str3.toString());
        int indexOf = str3.indexOf(Field.h);
        String substring = str3.substring(0, indexOf);
        if (substring.endsWith(Field.g) || substring.endsWith("f")) {
            substring = substring.substring(0, substring.length() - 1);
        }
        hashMap.put("AN1", substring);
        hashMap.put("ED", str3.substring(indexOf + 1, indexOf + 5));
        hashMap.put("AMT", hashMap.get("9F02"));
        if (hashMap.containsKey("9F10") && ((byte) (e.a(hashMap.get("9F10"))[4] & 48)) == 32) {
            hashMap.put("9F27", "80");
        }
    }

    public final String a() {
        b.a a2 = this.c.a(d);
        if (!a2.b()) {
            return null;
        }
        String a3 = a(a2.toString(), "4F");
        if (!a3.startsWith("A000000333")) {
            return "noSupUnionpay";
        }
        b.a a4 = this.c.a(e.a(a3));
        if (!a4.b()) {
            return null;
        }
        return a4.toString();
    }

    public final String a(String str, HashMap<String, String> hashMap) {
        String a2 = a(str, "9F38");
        StringBuffer stringBuffer = new StringBuffer();
        for (String next : a(a2)) {
            Iterator<String> it = hashMap.keySet().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                String next2 = it.next();
                if (next.compareToIgnoreCase(next2) == 0) {
                    stringBuffer.append(hashMap.get(next2));
                    break;
                }
            }
        }
        b.C0075b bVar = this.c;
        byte[] a3 = e.a(stringBuffer.toString());
        ByteBuffer allocate = ByteBuffer.allocate(a3.length + 7);
        allocate.put(Byte.MIN_VALUE).put((byte) -88).put((byte) 0).put((byte) 0).put((byte) (a3.length + 2)).put((byte) -125).put((byte) a3.length).put(a3);
        Log.e("PBOC Transceive", b.C0075b.c(allocate.array()));
        b.a aVar = new b.a(bVar.b(allocate.array()));
        Log.e("PBOC receive", b.C0075b.c(aVar.a()));
        if (!aVar.b()) {
            return null;
        }
        String a4 = a(aVar.toString(), "9F10");
        if (TextUtils.isEmpty(a4) || ((byte) (e.a(a4)[4] & 48)) == 32) {
            return aVar.toString();
        }
        return null;
    }
}
