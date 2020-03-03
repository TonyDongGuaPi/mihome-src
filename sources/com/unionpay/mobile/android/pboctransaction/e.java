package com.unionpay.mobile.android.pboctransaction;

import cn.com.fmsh.communication.contants.Contants;
import com.coloros.mcssdk.c.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import miuipub.reflect.Field;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    static HashMap<String, String> f9643a = new f();

    private static String a(String str, String str2) {
        if (str != null && str.length() > str2.length()) {
            while (str.substring(str.length() - str2.length(), str.length()).equalsIgnoreCase(str2)) {
                str = str.substring(0, str.length() - str2.length());
            }
        }
        return str;
    }

    public static final String a(byte[] bArr) {
        return a(bArr, bArr.length);
    }

    public static final String a(byte[] bArr, int i) {
        StringBuilder sb = new StringBuilder("");
        if (bArr == null || i <= 0) {
            return null;
        }
        for (int i2 = 0; i2 < i; i2++) {
            String hexString = Integer.toHexString(bArr[i2] & 255);
            if (hexString.length() < 2) {
                sb.append(0);
            }
            sb.append(hexString);
        }
        return sb.toString().toUpperCase();
    }

    private static boolean a(ArrayList<AppIdentification> arrayList, String str) {
        if (str == null) {
            return false;
        }
        Iterator<AppIdentification> it = arrayList.iterator();
        while (it.hasNext()) {
            if (it.next().a().equalsIgnoreCase(str)) {
                return false;
            }
        }
        return true;
    }

    public static final byte[] a(String str) {
        if (str == null || str.equals("")) {
            return null;
        }
        String upperCase = str.toUpperCase();
        int length = upperCase.length() / 2;
        char[] charArray = upperCase.toCharArray();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            int i2 = i * 2;
            bArr[i] = (byte) (((byte) a.f.indexOf(charArray[i2 + 1])) | (((byte) a.f.indexOf(charArray[i2])) << 4));
        }
        return bArr;
    }

    public static final ArrayList<AppIdentification> b(String str) {
        if (str == null || str.length() <= 4) {
            return null;
        }
        ArrayList<AppIdentification> arrayList = new ArrayList<>();
        String substring = str.substring(str.length() - 4);
        if (substring != null && substring.equalsIgnoreCase(Contants.Message.PACKET_CODE_DEFAULT)) {
            int i = 0;
            String substring2 = str.substring(0, str.length() - 4);
            while (substring2 != null && substring2.length() > 0 && i != -1) {
                String str2 = "";
                int indexOf = substring2.indexOf("61", i);
                if (indexOf == -1) {
                    break;
                }
                int i2 = indexOf + 2;
                int i3 = indexOf + 4;
                String substring3 = substring2.substring(i2, i3);
                int i4 = indexOf + 6;
                String substring4 = substring2.substring(i3, i4);
                if ((Integer.parseInt(substring4, 16) * 2) + i4 < substring2.length()) {
                    str2 = substring2.substring(i4, (Integer.parseInt(substring4, 16) * 2) + i4);
                }
                i = (Integer.parseInt(substring3, 16) * 2) + i2;
                if (i > substring2.length()) {
                    i = i2;
                }
                String trim = str2.trim();
                if (trim.length() > 8 && !trim.equalsIgnoreCase("A0000003334355502D4D4F42494C45") && a(arrayList, trim)) {
                    arrayList.add(new AppIdentification(trim, (String) null));
                }
            }
        }
        return arrayList;
    }

    public static String c(String str) {
        return a(str, Field.g);
    }

    public static String d(String str) {
        return a(str, "00");
    }

    public static String e(String str) {
        return f9643a.containsKey(str) ? f9643a.get(str) : "";
    }
}
