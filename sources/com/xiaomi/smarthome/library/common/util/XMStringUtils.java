package com.xiaomi.smarthome.library.common.util;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.view.ViewTreeObserver;
import android.widget.TextView;
import com.xiaomi.smarthome.library.common.network.HanziToPinyin;
import com.xiaomi.smarthome.library.common.network.HanziToPinyinICS;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class XMStringUtils {
    public static boolean b(int i) {
        return i >= 0 && i <= 255;
    }

    public static String i(String str) {
        return str != null ? str : "";
    }

    public static String a(Object[] objArr) {
        return a(objArr, (String) null);
    }

    public static String a(Object[] objArr, char c) {
        if (objArr == null) {
            return null;
        }
        return a(objArr, c, 0, objArr.length);
    }

    public static String a(Object[] objArr, char c, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + 1));
        for (int i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                sb.append(c);
            }
            if (objArr[i4] != null) {
                sb.append(objArr[i4]);
            }
        }
        return sb.toString();
    }

    public static String a(Object[] objArr, String str) {
        if (objArr == null) {
            return null;
        }
        return a(objArr, str, 0, objArr.length);
    }

    public static String a(Object[] objArr, String str, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        if (str == null) {
            str = "";
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + str.length()));
        for (int i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                sb.append(str);
            }
            if (objArr[i4] != null) {
                sb.append(objArr[i4]);
            }
        }
        return sb.toString();
    }

    public static String a(Iterator<?> it, char c) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next.toString();
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            sb.append(c);
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static String a(Iterator<?> it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return next.toString();
        }
        StringBuilder sb = new StringBuilder(256);
        if (next != null) {
            sb.append(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                sb.append(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                sb.append(next2);
            }
        }
        return sb.toString();
    }

    public static String a(Collection<?> collection, char c) {
        if (collection == null) {
            return null;
        }
        return a(collection.iterator(), c);
    }

    public static String a(Collection<?> collection, String str) {
        if (collection == null) {
            return null;
        }
        return a(collection.iterator(), str);
    }

    public static String a(int i) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return sb.toString();
    }

    public static String a(String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        if (Build.VERSION.SDK_INT < 14) {
            ArrayList<HanziToPinyin.Token> a2 = HanziToPinyin.a().a(str);
            if (a2 != null && a2.size() > 0) {
                Iterator<HanziToPinyin.Token> it = a2.iterator();
                while (it.hasNext()) {
                    sb.append(it.next().g);
                    if (z) {
                        sb.append(" ");
                    }
                }
            }
        } else {
            ArrayList<HanziToPinyin.Token> a3 = HanziToPinyinICS.a().a(str);
            if (a3 != null && a3.size() > 0) {
                Iterator<HanziToPinyin.Token> it2 = a3.iterator();
                while (it2.hasNext()) {
                    sb.append(it2.next().g);
                    if (z) {
                        sb.append(" ");
                    }
                }
            }
        }
        if (TextUtils.isEmpty(sb)) {
            return str.toLowerCase(Locale.ENGLISH);
        }
        return sb.toString().toLowerCase(Locale.ENGLISH);
    }

    public static int a(String str) {
        if (TextUtils.isEmpty(str)) {
            return 0;
        }
        try {
            return str.getBytes("UTF-8").length;
        } catch (UnsupportedEncodingException unused) {
            return 0;
        }
    }

    public static boolean a(String str, String str2) {
        int i = 0;
        int i2 = 0;
        while (i < str2.length() && i2 < str.length()) {
            if (str2.charAt(i) == str.charAt(i2)) {
                i++;
                i2++;
            } else {
                i2++;
            }
        }
        if (i == str2.length()) {
            return true;
        }
        return false;
    }

    public static String a(byte[] bArr) {
        String str = "";
        for (byte b : bArr) {
            str = str + Integer.toString((b & 255) + 256, 16).substring(1);
        }
        return str;
    }

    public static byte[] b(String str) {
        int length = str.length();
        byte[] bArr = new byte[(length / 2)];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    public static String c(String str) {
        return TextUtils.isEmpty(str) ? "" : str;
    }

    public static int a(String str, int i) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException unused) {
            return i;
        }
    }

    public static String d(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(e(str));
            return String.format("%1$032X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] e(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String[] a(List<String> list) {
        String[] strArr = new String[list.size()];
        list.toArray(strArr);
        return strArr;
    }

    public static long[] b(List<Long> list) {
        long[] jArr = new long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            jArr[i] = list.get(i).longValue();
        }
        return jArr;
    }

    public static int[] c(List<Integer> list) {
        int[] iArr = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            iArr[i] = list.get(i).intValue();
        }
        return iArr;
    }

    public static String f(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(e(str));
            return String.format("%1$040X", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public static String g(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(e(str));
            return String.format("%1$040x", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Deprecated
    public static void a(TextView textView, String str, int i) {
        textView.setText(str);
        textView.getViewTreeObserver().addOnGlobalLayoutListener(new MyOnGlobalLayoutListener(textView, str, i));
    }

    public static int h(String str) {
        int i = 0;
        for (int i2 = 0; i2 < str.length(); i2++) {
            i = c(str, i2) ? i + 1 : i + 2;
        }
        return i;
    }

    public static String b(String str, int i) {
        int i2 = 0;
        for (int i3 = 0; i3 < str.length(); i3++) {
            i2 = c(str, i3) ? i2 + 1 : i2 + 2;
            if (i2 == i) {
                return str.substring(0, i3 + 1);
            }
            if (i2 > i) {
                return str.substring(0, i3);
            }
        }
        return str;
    }

    public static boolean c(String str, int i) {
        return b(Character.codePointAt(str, i));
    }

    public static String a(Context context, int i) {
        return context.getString(i);
    }

    public static String a(Context context, int i, int i2, Object obj) {
        return context.getResources().getQuantityString(i, i2, new Object[]{obj});
    }

    public static String[] b(Context context, int i) {
        return context.getResources().getStringArray(i);
    }

    static class MyOnGlobalLayoutListener implements ViewTreeObserver.OnGlobalLayoutListener {

        /* renamed from: a  reason: collision with root package name */
        TextView f18753a;
        String b;
        int c;

        public MyOnGlobalLayoutListener(TextView textView, String str, int i) {
            this.f18753a = textView;
            this.b = str;
            this.c = i;
        }

        public void onGlobalLayout() {
            this.f18753a.getViewTreeObserver().removeGlobalOnLayoutListener(this);
            if (this.f18753a.getLineCount() > this.c) {
                int lineEnd = this.f18753a.getLayout().getLineEnd(this.c - 1);
                this.f18753a.setText(this.b.subSequence(0, lineEnd - 3) + "...");
            }
        }
    }
}
