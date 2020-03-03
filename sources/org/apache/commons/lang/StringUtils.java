package org.apache.commons.lang;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.Locale;
import org.apache.commons.lang.text.StrBuilder;

public class StringUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final String f3373a = "";
    public static final int b = -1;
    private static final int c = 8192;

    public static String L(String str, String str2) {
        return str == null ? str2 : str;
    }

    public static String P(String str) {
        return str == null ? "" : str;
    }

    public static boolean a(String str) {
        return str == null || str.length() == 0;
    }

    public static boolean b(String str) {
        return !a(str);
    }

    public static boolean c(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean d(String str) {
        return !c(str);
    }

    public static String e(String str) {
        return str == null ? "" : str.trim();
    }

    public static String f(String str) {
        if (str == null) {
            return null;
        }
        return str.trim();
    }

    public static String g(String str) {
        String f = f(str);
        if (a(f)) {
            return null;
        }
        return f;
    }

    public static String h(String str) {
        return str == null ? "" : str.trim();
    }

    public static String i(String str) {
        return a(str, (String) null);
    }

    public static String j(String str) {
        if (str == null) {
            return null;
        }
        String a2 = a(str, (String) null);
        if (a2.length() == 0) {
            return null;
        }
        return a2;
    }

    public static String k(String str) {
        return str == null ? "" : a(str, (String) null);
    }

    public static String a(String str, String str2) {
        if (a(str)) {
            return str;
        }
        return c(b(str, str2), str2);
    }

    public static String b(String str, String str2) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        int i = 0;
        if (str2 == null) {
            while (i != length && Character.isWhitespace(str.charAt(i))) {
                i++;
            }
        } else if (str2.length() == 0) {
            return str;
        } else {
            while (i != length && str2.indexOf(str.charAt(i)) != -1) {
                i++;
            }
        }
        return str.substring(i);
    }

    public static String c(String str, String str2) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        if (str2 == null) {
            while (length != 0 && Character.isWhitespace(str.charAt(length - 1))) {
                length--;
            }
        } else if (str2.length() == 0) {
            return str;
        } else {
            while (length != 0 && str2.indexOf(str.charAt(length - 1)) != -1) {
                length--;
            }
        }
        return str.substring(0, length);
    }

    public static String[] a(String[] strArr) {
        return a(strArr, (String) null);
    }

    public static String[] a(String[] strArr, String str) {
        int length;
        if (strArr == null || (length = strArr.length) == 0) {
            return strArr;
        }
        String[] strArr2 = new String[length];
        for (int i = 0; i < length; i++) {
            strArr2[i] = a(strArr[i], str);
        }
        return strArr2;
    }

    public static boolean d(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equals(str2);
    }

    public static boolean e(String str, String str2) {
        if (str == null) {
            return str2 == null;
        }
        return str.equalsIgnoreCase(str2);
    }

    public static int a(String str, char c2) {
        if (a(str)) {
            return -1;
        }
        return str.indexOf(c2);
    }

    public static int a(String str, char c2, int i) {
        if (a(str)) {
            return -1;
        }
        return str.indexOf(c2, i);
    }

    public static int f(String str, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        return str.indexOf(str2);
    }

    public static int a(String str, String str2, int i) {
        return a(str, str2, i, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:11:0x001e  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int a(java.lang.String r3, java.lang.String r4, int r5, boolean r6) {
        /*
            r0 = -1
            if (r3 == 0) goto L_0x0033
            if (r4 == 0) goto L_0x0033
            if (r5 > 0) goto L_0x0008
            goto L_0x0033
        L_0x0008:
            int r1 = r4.length()
            r2 = 0
            if (r1 != 0) goto L_0x0016
            if (r6 == 0) goto L_0x0015
            int r2 = r3.length()
        L_0x0015:
            return r2
        L_0x0016:
            if (r6 == 0) goto L_0x001c
            int r0 = r3.length()
        L_0x001c:
            if (r6 == 0) goto L_0x0025
            int r0 = r0 + -1
            int r0 = r3.lastIndexOf(r4, r0)
            goto L_0x002b
        L_0x0025:
            int r0 = r0 + 1
            int r0 = r3.indexOf(r4, r0)
        L_0x002b:
            if (r0 >= 0) goto L_0x002e
            return r0
        L_0x002e:
            int r2 = r2 + 1
            if (r2 < r5) goto L_0x001c
            return r0
        L_0x0033:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.a(java.lang.String, java.lang.String, int, boolean):int");
    }

    public static int b(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return -1;
        }
        if (str2.length() != 0 || i < str.length()) {
            return str.indexOf(str2, i);
        }
        return str.length();
    }

    public static int g(String str, String str2) {
        return c(str, str2, 0);
    }

    public static int c(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return -1;
        }
        if (i < 0) {
            i = 0;
        }
        int length = (str.length() - str2.length()) + 1;
        if (i > length) {
            return -1;
        }
        if (str2.length() == 0) {
            return i;
        }
        while (i < length) {
            if (str.regionMatches(true, i, str2, 0, str2.length())) {
                return i;
            }
            i++;
        }
        return -1;
    }

    public static int b(String str, char c2) {
        if (a(str)) {
            return -1;
        }
        return str.lastIndexOf(c2);
    }

    public static int b(String str, char c2, int i) {
        if (a(str)) {
            return -1;
        }
        return str.lastIndexOf(c2, i);
    }

    public static int h(String str, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        return str.lastIndexOf(str2);
    }

    public static int d(String str, String str2, int i) {
        return a(str, str2, i, true);
    }

    public static int e(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return -1;
        }
        return str.lastIndexOf(str2, i);
    }

    public static int i(String str, String str2) {
        if (str == null || str2 == null) {
            return -1;
        }
        return f(str, str2, str.length());
    }

    public static int f(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return -1;
        }
        if (i > str.length() - str2.length()) {
            i = str.length() - str2.length();
        }
        if (i < 0) {
            return -1;
        }
        if (str2.length() == 0) {
            return i;
        }
        while (i >= 0) {
            if (str.regionMatches(true, i, str2, 0, str2.length())) {
                return i;
            }
            i--;
        }
        return -1;
    }

    public static boolean c(String str, char c2) {
        if (!a(str) && str.indexOf(c2) >= 0) {
            return true;
        }
        return false;
    }

    public static boolean j(String str, String str2) {
        return (str == null || str2 == null || str.indexOf(str2) < 0) ? false : true;
    }

    public static boolean k(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        int length = str2.length();
        int length2 = str.length() - length;
        for (int i = 0; i <= length2; i++) {
            if (str.regionMatches(true, i, str2, 0, length)) {
                return true;
            }
        }
        return false;
    }

    public static int a(String str, char[] cArr) {
        if (a(str) || ArrayUtils.e(cArr)) {
            return -1;
        }
        int length = str.length();
        int i = length - 1;
        int length2 = cArr.length;
        int i2 = length2 - 1;
        int i3 = 0;
        while (i3 < length) {
            char charAt = str.charAt(i3);
            for (int i4 = 0; i4 < length2; i4++) {
                if (cArr[i4] == charAt && (i3 >= i || i4 >= i2 || !CharUtils.m(charAt) || cArr[i4 + 1] == str.charAt(i3 + 1))) {
                    return i3;
                }
            }
            i3++;
        }
        return -1;
    }

    public static int l(String str, String str2) {
        if (a(str) || a(str2)) {
            return -1;
        }
        return a(str, str2.toCharArray());
    }

    public static boolean b(String str, char[] cArr) {
        if (a(str) || ArrayUtils.e(cArr)) {
            return false;
        }
        int length = str.length();
        int length2 = cArr.length;
        int i = length - 1;
        int i2 = length2 - 1;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            for (int i4 = 0; i4 < length2; i4++) {
                if (cArr[i4] == charAt) {
                    if (!CharUtils.m(charAt) || i4 == i2) {
                        return true;
                    }
                    if (i3 < i && cArr[i4 + 1] == str.charAt(i3 + 1)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean m(String str, String str2) {
        if (str2 == null) {
            return false;
        }
        return b(str, str2.toCharArray());
    }

    public static int c(String str, char[] cArr) {
        if (a(str) || ArrayUtils.e(cArr)) {
            return -1;
        }
        int length = str.length();
        int i = length - 1;
        int length2 = cArr.length;
        int i2 = length2 - 1;
        int i3 = 0;
        while (i3 < length) {
            char charAt = str.charAt(i3);
            int i4 = 0;
            while (i4 < length2) {
                if (cArr[i4] != charAt || (i3 < i && i4 < i2 && CharUtils.m(charAt) && cArr[i4 + 1] != str.charAt(i3 + 1))) {
                    i4++;
                } else {
                    i3++;
                }
            }
            return i3;
        }
        return -1;
    }

    public static int n(String str, String str2) {
        if (a(str) || a(str2)) {
            return -1;
        }
        int length = str.length();
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            boolean z = str2.indexOf(charAt) >= 0;
            int i2 = i + 1;
            if (i2 < length && CharUtils.m(charAt)) {
                char charAt2 = str.charAt(i2);
                if (z && str2.indexOf(charAt2) < 0) {
                    return i;
                }
            } else if (!z) {
                return i;
            }
            i = i2;
        }
        return -1;
    }

    public static boolean d(String str, char[] cArr) {
        if (cArr == null || str == null) {
            return false;
        }
        if (str.length() == 0) {
            return true;
        }
        if (cArr.length != 0 && c(str, cArr) == -1) {
            return true;
        }
        return false;
    }

    public static boolean o(String str, String str2) {
        if (str == null || str2 == null) {
            return false;
        }
        return d(str, str2.toCharArray());
    }

    public static boolean e(String str, char[] cArr) {
        if (str == null || cArr == null) {
            return true;
        }
        int length = str.length();
        int i = length - 1;
        int length2 = cArr.length;
        int i2 = length2 - 1;
        for (int i3 = 0; i3 < length; i3++) {
            char charAt = str.charAt(i3);
            for (int i4 = 0; i4 < length2; i4++) {
                if (cArr[i4] == charAt) {
                    if (!CharUtils.m(charAt) || i4 == i2) {
                        return false;
                    }
                    if (i3 < i && cArr[i4 + 1] == str.charAt(i3 + 1)) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean p(String str, String str2) {
        if (str == null || str2 == null) {
            return true;
        }
        return e(str, str2.toCharArray());
    }

    public static int a(String str, String[] strArr) {
        int indexOf;
        if (str == null || strArr == null) {
            return -1;
        }
        int i = Integer.MAX_VALUE;
        for (String str2 : strArr) {
            if (!(str2 == null || (indexOf = str.indexOf(str2)) == -1 || indexOf >= i)) {
                i = indexOf;
            }
        }
        if (i == Integer.MAX_VALUE) {
            return -1;
        }
        return i;
    }

    public static int b(String str, String[] strArr) {
        int lastIndexOf;
        int i = -1;
        if (str == null || strArr == null) {
            return -1;
        }
        for (String str2 : strArr) {
            if (str2 != null && (lastIndexOf = str.lastIndexOf(str2)) > i) {
                i = lastIndexOf;
            }
        }
        return i;
    }

    public static String a(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            i += str.length();
        }
        if (i < 0) {
            i = 0;
        }
        if (i > str.length()) {
            return "";
        }
        return str.substring(i);
    }

    public static String a(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (i2 < 0) {
            i2 += str.length();
        }
        if (i < 0) {
            i += str.length();
        }
        if (i2 > str.length()) {
            i2 = str.length();
        }
        if (i > i2) {
            return "";
        }
        if (i < 0) {
            i = 0;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        return str.substring(i, i2);
    }

    public static String b(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            return "";
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(0, i);
    }

    public static String c(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i < 0) {
            return "";
        }
        if (str.length() <= i) {
            return str;
        }
        return str.substring(str.length() - i);
    }

    public static String b(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (i2 < 0 || i > str.length()) {
            return "";
        }
        if (i < 0) {
            i = 0;
        }
        int i3 = i2 + i;
        if (str.length() <= i3) {
            return str.substring(i);
        }
        return str.substring(i, i3);
    }

    public static String q(String str, String str2) {
        if (a(str) || str2 == null) {
            return str;
        }
        if (str2.length() == 0) {
            return "";
        }
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        return str.substring(0, indexOf);
    }

    public static String r(String str, String str2) {
        int indexOf;
        if (a(str)) {
            return str;
        }
        if (str2 == null || (indexOf = str.indexOf(str2)) == -1) {
            return "";
        }
        return str.substring(indexOf + str2.length());
    }

    public static String s(String str, String str2) {
        int lastIndexOf;
        if (a(str) || a(str2) || (lastIndexOf = str.lastIndexOf(str2)) == -1) {
            return str;
        }
        return str.substring(0, lastIndexOf);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0010, code lost:
        r0 = r3.lastIndexOf(r4);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String t(java.lang.String r3, java.lang.String r4) {
        /*
            boolean r0 = a((java.lang.String) r3)
            if (r0 == 0) goto L_0x0007
            return r3
        L_0x0007:
            boolean r0 = a((java.lang.String) r4)
            if (r0 == 0) goto L_0x0010
            java.lang.String r3 = ""
            return r3
        L_0x0010:
            int r0 = r3.lastIndexOf(r4)
            r1 = -1
            if (r0 == r1) goto L_0x002d
            int r1 = r3.length()
            int r2 = r4.length()
            int r1 = r1 - r2
            if (r0 != r1) goto L_0x0023
            goto L_0x002d
        L_0x0023:
            int r4 = r4.length()
            int r0 = r0 + r4
            java.lang.String r3 = r3.substring(r0)
            return r3
        L_0x002d:
            java.lang.String r3 = ""
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.t(java.lang.String, java.lang.String):java.lang.String");
    }

    public static String u(String str, String str2) {
        return a(str, str2, str2);
    }

    public static String a(String str, String str2, String str3) {
        int indexOf;
        int indexOf2;
        if (str == null || str2 == null || str3 == null || (indexOf = str.indexOf(str2)) == -1 || (indexOf2 = str.indexOf(str3, str2.length() + indexOf)) == -1) {
            return null;
        }
        return str.substring(indexOf + str2.length(), indexOf2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0032, code lost:
        r5 = r5 + r3;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String[] b(java.lang.String r7, java.lang.String r8, java.lang.String r9) {
        /*
            r0 = 0
            if (r7 == 0) goto L_0x0058
            boolean r1 = a((java.lang.String) r8)
            if (r1 != 0) goto L_0x0058
            boolean r1 = a((java.lang.String) r9)
            if (r1 == 0) goto L_0x0010
            goto L_0x0058
        L_0x0010:
            int r1 = r7.length()
            if (r1 != 0) goto L_0x0019
            java.lang.String[] r7 = org.apache.commons.lang.ArrayUtils.c
            return r7
        L_0x0019:
            int r2 = r9.length()
            int r3 = r8.length()
            java.util.ArrayList r4 = new java.util.ArrayList
            r4.<init>()
            r5 = 0
        L_0x0027:
            int r6 = r1 - r2
            if (r5 >= r6) goto L_0x0044
            int r5 = r7.indexOf(r8, r5)
            if (r5 >= 0) goto L_0x0032
            goto L_0x0044
        L_0x0032:
            int r5 = r5 + r3
            int r6 = r7.indexOf(r9, r5)
            if (r6 >= 0) goto L_0x003a
            goto L_0x0044
        L_0x003a:
            java.lang.String r5 = r7.substring(r5, r6)
            r4.add(r5)
            int r5 = r6 + r2
            goto L_0x0027
        L_0x0044:
            boolean r7 = r4.isEmpty()
            if (r7 == 0) goto L_0x004b
            return r0
        L_0x004b:
            int r7 = r4.size()
            java.lang.String[] r7 = new java.lang.String[r7]
            java.lang.Object[] r7 = r4.toArray(r7)
            java.lang.String[] r7 = (java.lang.String[]) r7
            return r7
        L_0x0058:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.b(java.lang.String, java.lang.String, java.lang.String):java.lang.String[]");
    }

    public static String v(String str, String str2) {
        return a(str, str2, str2);
    }

    public static String c(String str, String str2, String str3) {
        return a(str, str2, str3);
    }

    public static String[] l(String str) {
        return g(str, (String) null, -1);
    }

    public static String[] d(String str, char c2) {
        return a(str, c2, false);
    }

    public static String[] w(String str, String str2) {
        return c(str, str2, -1, false);
    }

    public static String[] g(String str, String str2, int i) {
        return c(str, str2, i, false);
    }

    public static String[] x(String str, String str2) {
        return b(str, str2, -1, false);
    }

    public static String[] h(String str, String str2, int i) {
        return b(str, str2, i, false);
    }

    public static String[] y(String str, String str2) {
        return b(str, str2, -1, true);
    }

    public static String[] i(String str, String str2, int i) {
        return b(str, str2, i, true);
    }

    private static String[] b(String str, String str2, int i, boolean z) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.c;
        }
        if (str2 == null || "".equals(str2)) {
            return c(str, (String) null, i, z);
        }
        int length2 = str2.length();
        ArrayList arrayList = new ArrayList();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < length) {
            i2 = str.indexOf(str2, i3);
            if (i2 <= -1) {
                arrayList.add(str.substring(i3));
            } else if (i2 > i3) {
                i4++;
                if (i4 == i) {
                    arrayList.add(str.substring(i3));
                } else {
                    arrayList.add(str.substring(i3, i2));
                    i3 = i2 + length2;
                }
            } else {
                if (z) {
                    i4++;
                    if (i4 == i) {
                        arrayList.add(str.substring(i3));
                        i2 = length;
                    } else {
                        arrayList.add("");
                    }
                }
                i3 = i2 + length2;
            }
            i2 = length;
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] m(String str) {
        return c(str, (String) null, -1, true);
    }

    public static String[] e(String str, char c2) {
        return a(str, c2, true);
    }

    private static String[] a(String str, char c2, boolean z) {
        boolean z2;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.c;
        }
        ArrayList arrayList = new ArrayList();
        int i = 0;
        boolean z3 = false;
        int i2 = 0;
        loop0:
        while (true) {
            z2 = false;
            while (i < length) {
                if (str.charAt(i) == c2) {
                    if (z3 || z) {
                        arrayList.add(str.substring(i2, i));
                        z3 = false;
                        z2 = true;
                    }
                    i2 = i + 1;
                    i = i2;
                } else {
                    i++;
                    z3 = true;
                }
            }
            break loop0;
        }
        if (z3 || (z && z2)) {
            arrayList.add(str.substring(i2, i));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] z(String str, String str2) {
        return c(str, str2, -1, true);
    }

    public static String[] j(String str, String str2, int i) {
        return c(str, str2, i, true);
    }

    private static String[] c(String str, String str2, int i, boolean z) {
        boolean z2;
        boolean z3;
        int i2;
        int i3;
        boolean z4;
        boolean z5;
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length == 0) {
            return ArrayUtils.c;
        }
        ArrayList arrayList = new ArrayList();
        if (str2 == null) {
            int i4 = 0;
            boolean z6 = false;
            int i5 = 1;
            int i6 = 0;
            loop0:
            while (true) {
                z2 = false;
                while (i4 < length) {
                    if (Character.isWhitespace(str.charAt(i4))) {
                        if (z6 || z) {
                            int i7 = i5 + 1;
                            if (i5 == i) {
                                i4 = length;
                                z5 = false;
                            } else {
                                z5 = true;
                            }
                            arrayList.add(str.substring(i6, i4));
                            z2 = z5;
                            i5 = i7;
                            z6 = false;
                        }
                        i6 = i4 + 1;
                        i4 = i6;
                    } else {
                        i4++;
                        z6 = true;
                    }
                }
                break loop0;
            }
            i2 = i6;
            z3 = z6;
            i3 = i4;
        } else if (str2.length() == 1) {
            char charAt = str2.charAt(0);
            i3 = 0;
            i2 = 0;
            z3 = false;
            z2 = false;
            int i8 = 1;
            while (i3 < length) {
                if (str.charAt(i3) == charAt) {
                    if (z3 || z) {
                        int i9 = i8 + 1;
                        if (i8 == i) {
                            i3 = length;
                            z2 = false;
                        } else {
                            z2 = true;
                        }
                        arrayList.add(str.substring(i2, i3));
                        i8 = i9;
                        z3 = false;
                    }
                    i2 = i3 + 1;
                    i3 = i2;
                } else {
                    i3++;
                    z3 = true;
                    z2 = false;
                }
            }
        } else {
            int i10 = 0;
            i2 = 0;
            z3 = false;
            z2 = false;
            int i11 = 1;
            while (i3 < length) {
                if (str2.indexOf(str.charAt(i3)) >= 0) {
                    if (z3 || z) {
                        int i12 = i11 + 1;
                        if (i11 == i) {
                            i3 = length;
                            z4 = false;
                        } else {
                            z4 = true;
                        }
                        arrayList.add(str.substring(i2, i3));
                        i11 = i12;
                        z3 = false;
                    }
                    i2 = i3 + 1;
                    i10 = i2;
                } else {
                    i10 = i3 + 1;
                    z3 = true;
                    z2 = false;
                }
            }
        }
        if (z3 || (z && z2)) {
            arrayList.add(str.substring(i2, i3));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String[] n(String str) {
        return a(str, false);
    }

    public static String[] o(String str) {
        return a(str, true);
    }

    private static String[] a(String str, boolean z) {
        int i;
        if (str == null) {
            return null;
        }
        if (str.length() == 0) {
            return ArrayUtils.c;
        }
        char[] charArray = str.toCharArray();
        ArrayList arrayList = new ArrayList();
        int type = Character.getType(charArray[0]);
        int i2 = 0;
        for (int i3 = 1; i3 < charArray.length; i3++) {
            int type2 = Character.getType(charArray[i3]);
            if (type2 != type) {
                if (z && type2 == 2 && type == 1) {
                    i = i3 - 1;
                    if (i != i2) {
                        arrayList.add(new String(charArray, i2, i - i2));
                    } else {
                        i = i2;
                    }
                } else {
                    arrayList.add(new String(charArray, i2, i3 - i2));
                    i = i3;
                }
                i2 = i;
                type = type2;
            }
        }
        arrayList.add(new String(charArray, i2, charArray.length - i2));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public static String a(Object[] objArr) {
        return a(objArr, (String) null);
    }

    public static String b(Object[] objArr) {
        return a(objArr, (String) null);
    }

    public static String a(Object[] objArr, char c2) {
        if (objArr == null) {
            return null;
        }
        return a(objArr, c2, 0, objArr.length);
    }

    public static String a(Object[] objArr, char c2, int i, int i2) {
        if (objArr == null) {
            return null;
        }
        int i3 = i2 - i;
        if (i3 <= 0) {
            return "";
        }
        StrBuilder strBuilder = new StrBuilder(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + 1));
        for (int i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                strBuilder.a(c2);
            }
            if (objArr[i4] != null) {
                strBuilder.a(objArr[i4]);
            }
        }
        return strBuilder.toString();
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
        StrBuilder strBuilder = new StrBuilder(i3 * ((objArr[i] == null ? 16 : objArr[i].toString().length()) + str.length()));
        for (int i4 = i; i4 < i2; i4++) {
            if (i4 > i) {
                strBuilder.c(str);
            }
            if (objArr[i4] != null) {
                strBuilder.a(objArr[i4]);
            }
        }
        return strBuilder.toString();
    }

    public static String a(Iterator it, char c2) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.c(next);
        }
        StrBuilder strBuilder = new StrBuilder(256);
        if (next != null) {
            strBuilder.a(next);
        }
        while (it.hasNext()) {
            strBuilder.a(c2);
            Object next2 = it.next();
            if (next2 != null) {
                strBuilder.a(next2);
            }
        }
        return strBuilder.toString();
    }

    public static String a(Iterator it, String str) {
        if (it == null) {
            return null;
        }
        if (!it.hasNext()) {
            return "";
        }
        Object next = it.next();
        if (!it.hasNext()) {
            return ObjectUtils.c(next);
        }
        StrBuilder strBuilder = new StrBuilder(256);
        if (next != null) {
            strBuilder.a(next);
        }
        while (it.hasNext()) {
            if (str != null) {
                strBuilder.c(str);
            }
            Object next2 = it.next();
            if (next2 != null) {
                strBuilder.a(next2);
            }
        }
        return strBuilder.toString();
    }

    public static String a(Collection collection, char c2) {
        if (collection == null) {
            return null;
        }
        return a(collection.iterator(), c2);
    }

    public static String a(Collection collection, String str) {
        if (collection == null) {
            return null;
        }
        return a(collection.iterator(), str);
    }

    public static String p(String str) {
        if (str == null) {
            return null;
        }
        return CharSetUtils.d(str, " \t\r\n\b");
    }

    public static String q(String str) {
        if (a(str)) {
            return str;
        }
        int length = str.length();
        char[] cArr = new char[length];
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            if (!Character.isWhitespace(str.charAt(i2))) {
                cArr[i] = str.charAt(i2);
                i++;
            }
        }
        if (i == length) {
            return str;
        }
        return new String(cArr, 0, i);
    }

    public static String A(String str, String str2) {
        return (a(str) || a(str2) || !str.startsWith(str2)) ? str : str.substring(str2.length());
    }

    public static String B(String str, String str2) {
        return (a(str) || a(str2) || !T(str, str2)) ? str : str.substring(str2.length());
    }

    public static String C(String str, String str2) {
        return (a(str) || a(str2) || !str.endsWith(str2)) ? str : str.substring(0, str.length() - str2.length());
    }

    public static String D(String str, String str2) {
        return (a(str) || a(str2) || !V(str, str2)) ? str : str.substring(0, str.length() - str2.length());
    }

    public static String E(String str, String str2) {
        return (a(str) || a(str2)) ? str : a(str, str2, "", -1);
    }

    public static String f(String str, char c2) {
        if (a(str) || str.indexOf(c2) == -1) {
            return str;
        }
        char[] charArray = str.toCharArray();
        int i = 0;
        for (int i2 = 0; i2 < charArray.length; i2++) {
            if (charArray[i2] != c2) {
                charArray[i] = charArray[i2];
                i++;
            }
        }
        return new String(charArray, 0, i);
    }

    public static String d(String str, String str2, String str3) {
        return a(str, str2, str3, 1);
    }

    public static String e(String str, String str2, String str3) {
        return a(str, str2, str3, -1);
    }

    public static String a(String str, String str2, String str3, int i) {
        if (a(str) || a(str2) || str3 == null || i == 0) {
            return str;
        }
        int i2 = 0;
        int indexOf = str.indexOf(str2, 0);
        if (indexOf == -1) {
            return str;
        }
        int length = str2.length();
        int length2 = str3.length() - length;
        if (length2 < 0) {
            length2 = 0;
        }
        int i3 = 64;
        if (i < 0) {
            i3 = 16;
        } else if (i <= 64) {
            i3 = i;
        }
        StrBuilder strBuilder = new StrBuilder(str.length() + (length2 * i3));
        while (indexOf != -1) {
            strBuilder.c(str.substring(i2, indexOf)).c(str3);
            i2 = indexOf + length;
            i--;
            if (i == 0) {
                break;
            }
            indexOf = str.indexOf(str2, i2);
        }
        strBuilder.c(str.substring(i2));
        return strBuilder.toString();
    }

    public static String a(String str, String[] strArr, String[] strArr2) {
        return a(str, strArr, strArr2, false, 0);
    }

    public static String b(String str, String[] strArr, String[] strArr2) {
        return a(str, strArr, strArr2, true, strArr == null ? 0 : strArr.length);
    }

    private static String a(String str, String[] strArr, String[] strArr2, boolean z, int i) {
        int length;
        if (str == null || str.length() == 0 || strArr == null || strArr.length == 0 || strArr2 == null || strArr2.length == 0) {
            return str;
        }
        if (i >= 0) {
            int length2 = strArr.length;
            int length3 = strArr2.length;
            if (length2 == length3) {
                boolean[] zArr = new boolean[length2];
                int i2 = -1;
                int i3 = -1;
                for (int i4 = 0; i4 < length2; i4++) {
                    if (!(zArr[i4] || strArr[i4] == null || strArr[i4].length() == 0 || strArr2[i4] == null)) {
                        int indexOf = str.indexOf(strArr[i4]);
                        if (indexOf == -1) {
                            zArr[i4] = true;
                        } else if (i2 == -1 || indexOf < i2) {
                            i3 = i4;
                            i2 = indexOf;
                        }
                    }
                }
                if (i2 == -1) {
                    return str;
                }
                int i5 = 0;
                for (int i6 = 0; i6 < strArr.length; i6++) {
                    if (!(strArr[i6] == null || strArr2[i6] == null || (length = strArr2[i6].length() - strArr[i6].length()) <= 0)) {
                        i5 += length * 3;
                    }
                }
                StrBuilder strBuilder = new StrBuilder(str.length() + Math.min(i5, str.length() / 5));
                int i7 = 0;
                while (i2 != -1) {
                    while (i7 < i2) {
                        strBuilder.a(str.charAt(i7));
                        i7++;
                    }
                    strBuilder.c(strArr2[i3]);
                    i7 = strArr[i3].length() + i2;
                    int i8 = -1;
                    int i9 = -1;
                    for (int i10 = 0; i10 < length2; i10++) {
                        if (!(zArr[i10] || strArr[i10] == null || strArr[i10].length() == 0 || strArr2[i10] == null)) {
                            int indexOf2 = str.indexOf(strArr[i10], i7);
                            if (indexOf2 == -1) {
                                zArr[i10] = true;
                            } else if (i8 == -1 || indexOf2 < i8) {
                                i9 = i10;
                                i8 = indexOf2;
                            }
                        }
                    }
                    i2 = i8;
                    i3 = i9;
                }
                int length4 = str.length();
                while (i7 < length4) {
                    strBuilder.a(str.charAt(i7));
                    i7++;
                }
                String strBuilder2 = strBuilder.toString();
                if (!z) {
                    return strBuilder2;
                }
                return a(strBuilder2, strArr, strArr2, z, i - 1);
            }
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("Search and Replace array lengths don't match: ");
            stringBuffer.append(length2);
            stringBuffer.append(" vs ");
            stringBuffer.append(length3);
            throw new IllegalArgumentException(stringBuffer.toString());
        }
        StringBuffer stringBuffer2 = new StringBuffer();
        stringBuffer2.append("TimeToLive of ");
        stringBuffer2.append(i);
        stringBuffer2.append(" is less than 0: ");
        stringBuffer2.append(str);
        throw new IllegalStateException(stringBuffer2.toString());
    }

    public static String a(String str, char c2, char c3) {
        if (str == null) {
            return null;
        }
        return str.replace(c2, c3);
    }

    public static String f(String str, String str2, String str3) {
        if (a(str) || a(str2)) {
            return str;
        }
        if (str3 == null) {
            str3 = "";
        }
        int length = str3.length();
        int length2 = str.length();
        StrBuilder strBuilder = new StrBuilder(length2);
        boolean z = false;
        for (int i = 0; i < length2; i++) {
            char charAt = str.charAt(i);
            int indexOf = str2.indexOf(charAt);
            if (indexOf >= 0) {
                if (indexOf < length) {
                    strBuilder.a(str3.charAt(indexOf));
                }
                z = true;
            } else {
                strBuilder.a(charAt);
            }
        }
        return z ? strBuilder.toString() : str;
    }

    public static String a(String str, String str2, int i, int i2) {
        return new StrBuilder((((str2.length() + i) + str.length()) - i2) + 1).c(str.substring(0, i)).c(str2).c(str.substring(i2)).toString();
    }

    public static String b(String str, String str2, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (str2 == null) {
            str2 = "";
        }
        int length = str.length();
        if (i < 0) {
            i = 0;
        }
        if (i > length) {
            i = length;
        }
        if (i2 < 0) {
            i2 = 0;
        }
        if (i2 > length) {
            i2 = length;
        }
        if (i > i2) {
            int i3 = i2;
            i2 = i;
            i = i3;
        }
        return new StrBuilder(((length + i) - i2) + str2.length() + 1).c(str.substring(0, i)).c(str2).c(str.substring(i2)).toString();
    }

    public static String r(String str) {
        if (a(str)) {
            return str;
        }
        if (str.length() == 1) {
            char charAt = str.charAt(0);
            return (charAt == 13 || charAt == 10) ? "" : str;
        }
        int length = str.length() - 1;
        char charAt2 = str.charAt(length);
        if (charAt2 == 10) {
            if (str.charAt(length - 1) == 13) {
                length--;
            }
        } else if (charAt2 != 13) {
            length++;
        }
        return str.substring(0, length);
    }

    public static String F(String str, String str2) {
        return (a(str) || str2 == null || !str.endsWith(str2)) ? str : str.substring(0, str.length() - str2.length());
    }

    public static String s(String str) {
        return G(str, "\n");
    }

    public static String G(String str, String str2) {
        return (str.length() != 0 && str2.equals(str.substring(str.length() - str2.length()))) ? str.substring(0, str.length() - str2.length()) : str;
    }

    public static String H(String str, String str2) {
        int lastIndexOf = str.lastIndexOf(str2);
        if (lastIndexOf == str.length() - str2.length()) {
            return str2;
        }
        return lastIndexOf != -1 ? str.substring(lastIndexOf) : "";
    }

    public static String I(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return str;
        }
        return str.substring(indexOf + str2.length());
    }

    public static String J(String str, String str2) {
        int indexOf = str.indexOf(str2);
        if (indexOf == -1) {
            return "";
        }
        return str.substring(0, indexOf + str2.length());
    }

    public static String t(String str) {
        if (str == null) {
            return null;
        }
        int length = str.length();
        if (length < 2) {
            return "";
        }
        int i = length - 1;
        String substring = str.substring(0, i);
        if (str.charAt(i) == 10) {
            int i2 = i - 1;
            if (substring.charAt(i2) == 13) {
                return substring.substring(0, i2);
            }
        }
        return substring;
    }

    public static String u(String str) {
        int length = str.length() - 1;
        if (length <= 0) {
            return "";
        }
        if (str.charAt(length) != 10) {
            length++;
        } else if (str.charAt(length - 1) == 13) {
            length--;
        }
        return str.substring(0, length);
    }

    public static String v(String str) {
        return StringEscapeUtils.a(str);
    }

    public static String d(String str, int i) {
        if (str == null) {
            return null;
        }
        if (i <= 0) {
            return "";
        }
        int length = str.length();
        if (i == 1 || length == 0) {
            return str;
        }
        if (length == 1 && i <= 8192) {
            return a(i, str.charAt(0));
        }
        int i2 = length * i;
        switch (length) {
            case 1:
                char charAt = str.charAt(0);
                char[] cArr = new char[i2];
                for (int i3 = i - 1; i3 >= 0; i3--) {
                    cArr[i3] = charAt;
                }
                return new String(cArr);
            case 2:
                char charAt2 = str.charAt(0);
                char charAt3 = str.charAt(1);
                char[] cArr2 = new char[i2];
                for (int i4 = (i * 2) - 2; i4 >= 0; i4 = (i4 - 1) - 1) {
                    cArr2[i4] = charAt2;
                    cArr2[i4 + 1] = charAt3;
                }
                return new String(cArr2);
            default:
                StrBuilder strBuilder = new StrBuilder(i2);
                for (int i5 = 0; i5 < i; i5++) {
                    strBuilder.c(str);
                }
                return strBuilder.toString();
        }
    }

    public static String k(String str, String str2, int i) {
        if (str == null || str2 == null) {
            return d(str, i);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str);
        stringBuffer.append(str2);
        return C(d(stringBuffer.toString(), i), str2);
    }

    private static String a(int i, char c2) throws IndexOutOfBoundsException {
        if (i >= 0) {
            char[] cArr = new char[i];
            for (int i2 = 0; i2 < cArr.length; i2++) {
                cArr[i2] = c2;
            }
            return new String(cArr);
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("Cannot pad a negative amount: ");
        stringBuffer.append(i);
        throw new IndexOutOfBoundsException(stringBuffer.toString());
    }

    public static String e(String str, int i) {
        return a(str, i, ' ');
    }

    public static String a(String str, int i, char c2) {
        if (str == null) {
            return null;
        }
        int length = i - str.length();
        if (length <= 0) {
            return str;
        }
        if (length > 8192) {
            return a(str, i, String.valueOf(c2));
        }
        return str.concat(a(length, c2));
    }

    public static String a(String str, int i, String str2) {
        if (str == null) {
            return null;
        }
        if (a(str2)) {
            str2 = " ";
        }
        int length = str2.length();
        int length2 = i - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= 8192) {
            return a(str, i, str2.charAt(0));
        }
        if (length2 == length) {
            return str.concat(str2);
        }
        if (length2 < length) {
            return str.concat(str2.substring(0, length2));
        }
        char[] cArr = new char[length2];
        char[] charArray = str2.toCharArray();
        for (int i2 = 0; i2 < length2; i2++) {
            cArr[i2] = charArray[i2 % length];
        }
        return str.concat(new String(cArr));
    }

    public static String f(String str, int i) {
        return b(str, i, ' ');
    }

    public static String b(String str, int i, char c2) {
        if (str == null) {
            return null;
        }
        int length = i - str.length();
        if (length <= 0) {
            return str;
        }
        if (length > 8192) {
            return b(str, i, String.valueOf(c2));
        }
        return a(length, c2).concat(str);
    }

    public static String b(String str, int i, String str2) {
        if (str == null) {
            return null;
        }
        if (a(str2)) {
            str2 = " ";
        }
        int length = str2.length();
        int length2 = i - str.length();
        if (length2 <= 0) {
            return str;
        }
        if (length == 1 && length2 <= 8192) {
            return b(str, i, str2.charAt(0));
        }
        if (length2 == length) {
            return str2.concat(str);
        }
        if (length2 < length) {
            return str2.substring(0, length2).concat(str);
        }
        char[] cArr = new char[length2];
        char[] charArray = str2.toCharArray();
        for (int i2 = 0; i2 < length2; i2++) {
            cArr[i2] = charArray[i2 % length];
        }
        return new String(cArr).concat(str);
    }

    public static int w(String str) {
        if (str == null) {
            return 0;
        }
        return str.length();
    }

    public static String g(String str, int i) {
        return c(str, i, ' ');
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0005, code lost:
        r0 = r2.length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String c(java.lang.String r2, int r3, char r4) {
        /*
            if (r2 == 0) goto L_0x001a
            if (r3 > 0) goto L_0x0005
            goto L_0x001a
        L_0x0005:
            int r0 = r2.length()
            int r1 = r3 - r0
            if (r1 > 0) goto L_0x000e
            return r2
        L_0x000e:
            int r1 = r1 / 2
            int r0 = r0 + r1
            java.lang.String r2 = b((java.lang.String) r2, (int) r0, (char) r4)
            java.lang.String r2 = a((java.lang.String) r2, (int) r3, (char) r4)
            return r2
        L_0x001a:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.c(java.lang.String, int, char):java.lang.String");
    }

    public static String c(String str, int i, String str2) {
        if (str == null || i <= 0) {
            return str;
        }
        if (a(str2)) {
            str2 = " ";
        }
        int length = str.length();
        int i2 = i - length;
        if (i2 <= 0) {
            return str;
        }
        return a(b(str, length + (i2 / 2), str2), i, str2);
    }

    public static String x(String str) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase();
    }

    public static String a(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toUpperCase(locale);
    }

    public static String y(String str) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase();
    }

    public static String b(String str, Locale locale) {
        if (str == null) {
            return null;
        }
        return str.toLowerCase(locale);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r2.length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String z(java.lang.String r2) {
        /*
            if (r2 == 0) goto L_0x0029
            int r0 = r2.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            org.apache.commons.lang.text.StrBuilder r1 = new org.apache.commons.lang.text.StrBuilder
            r1.<init>((int) r0)
            r0 = 0
            char r0 = r2.charAt(r0)
            char r0 = java.lang.Character.toTitleCase(r0)
            org.apache.commons.lang.text.StrBuilder r0 = r1.a((char) r0)
            r1 = 1
            java.lang.String r2 = r2.substring(r1)
            org.apache.commons.lang.text.StrBuilder r2 = r0.c((java.lang.String) r2)
            java.lang.String r2 = r2.toString()
            return r2
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.z(java.lang.String):java.lang.String");
    }

    public static String A(String str) {
        return z(str);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
        r0 = r2.length();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.lang.String B(java.lang.String r2) {
        /*
            if (r2 == 0) goto L_0x0029
            int r0 = r2.length()
            if (r0 != 0) goto L_0x0009
            goto L_0x0029
        L_0x0009:
            org.apache.commons.lang.text.StrBuilder r1 = new org.apache.commons.lang.text.StrBuilder
            r1.<init>((int) r0)
            r0 = 0
            char r0 = r2.charAt(r0)
            char r0 = java.lang.Character.toLowerCase(r0)
            org.apache.commons.lang.text.StrBuilder r0 = r1.a((char) r0)
            r1 = 1
            java.lang.String r2 = r2.substring(r1)
            org.apache.commons.lang.text.StrBuilder r2 = r0.c((java.lang.String) r2)
            java.lang.String r2 = r2.toString()
            return r2
        L_0x0029:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: org.apache.commons.lang.StringUtils.B(java.lang.String):java.lang.String");
    }

    public static String C(String str) {
        return B(str);
    }

    public static String D(String str) {
        int length;
        if (str == null || (length = str.length()) == 0) {
            return str;
        }
        StrBuilder strBuilder = new StrBuilder(length);
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (Character.isUpperCase(charAt)) {
                charAt = Character.toLowerCase(charAt);
            } else if (Character.isTitleCase(charAt)) {
                charAt = Character.toLowerCase(charAt);
            } else if (Character.isLowerCase(charAt)) {
                charAt = Character.toUpperCase(charAt);
            }
            strBuilder.a(charAt);
        }
        return strBuilder.toString();
    }

    public static String E(String str) {
        return WordUtils.a(str);
    }

    public static int K(String str, String str2) {
        int i = 0;
        if (a(str) || a(str2)) {
            return 0;
        }
        int i2 = 0;
        while (true) {
            int indexOf = str.indexOf(str2, i);
            if (indexOf == -1) {
                return i2;
            }
            i2++;
            i = indexOf + str2.length();
        }
    }

    public static boolean F(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean G(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetter(str.charAt(i)) && str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean H(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean I(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLetterOrDigit(str.charAt(i)) && str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean J(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!CharUtils.f(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean K(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean L(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isDigit(str.charAt(i)) && str.charAt(i) != ' ') {
                return false;
            }
        }
        return true;
    }

    public static boolean M(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean N(String str) {
        if (str == null || a(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isLowerCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean O(String str) {
        if (str == null || a(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (!Character.isUpperCase(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static String M(String str, String str2) {
        return c(str) ? str2 : str;
    }

    public static String N(String str, String str2) {
        return a(str) ? str2 : str;
    }

    public static String Q(String str) {
        if (str == null) {
            return null;
        }
        return new StrBuilder(str).l().toString();
    }

    public static String g(String str, char c2) {
        if (str == null) {
            return null;
        }
        String[] d = d(str, c2);
        ArrayUtils.d((Object[]) d);
        return a((Object[]) d, c2);
    }

    public static String O(String str, String str2) {
        if (str == null) {
            return null;
        }
        String[] w = w(str, str2);
        ArrayUtils.d((Object[]) w);
        if (str2 == null) {
            return a((Object[]) w, ' ');
        }
        return a((Object[]) w, str2);
    }

    public static String h(String str, int i) {
        return c(str, 0, i);
    }

    public static String c(String str, int i, int i2) {
        if (str == null) {
            return null;
        }
        if (i2 < 4) {
            throw new IllegalArgumentException("Minimum abbreviation width is 4");
        } else if (str.length() <= i2) {
            return str;
        } else {
            if (i > str.length()) {
                i = str.length();
            }
            int i3 = i2 - 3;
            if (str.length() - i < i3) {
                i = str.length() - i3;
            }
            if (i <= 4) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(str.substring(0, i3));
                stringBuffer.append("...");
                return stringBuffer.toString();
            } else if (i2 < 7) {
                throw new IllegalArgumentException("Minimum abbreviation width with offset is 7");
            } else if (i + i3 < str.length()) {
                StringBuffer stringBuffer2 = new StringBuffer();
                stringBuffer2.append("...");
                stringBuffer2.append(h(str.substring(i), i3));
                return stringBuffer2.toString();
            } else {
                StringBuffer stringBuffer3 = new StringBuffer();
                stringBuffer3.append("...");
                stringBuffer3.append(str.substring(str.length() - i3));
                return stringBuffer3.toString();
            }
        }
    }

    public static String l(String str, String str2, int i) {
        if (a(str) || a(str2) || i >= str.length() || i < str2.length() + 2) {
            return str;
        }
        int length = i - str2.length();
        int i2 = length / 2;
        int i3 = (length % 2) + i2;
        int length2 = str.length() - i2;
        StrBuilder strBuilder = new StrBuilder(i);
        strBuilder.c(str.substring(0, i3));
        strBuilder.c(str2);
        strBuilder.c(str.substring(length2));
        return strBuilder.toString();
    }

    public static String P(String str, String str2) {
        if (str == null) {
            return str2;
        }
        if (str2 == null) {
            return str;
        }
        int Q = Q(str, str2);
        if (Q == -1) {
            return "";
        }
        return str2.substring(Q);
    }

    public static int Q(String str, String str2) {
        if (str == str2) {
            return -1;
        }
        int i = 0;
        if (str == null || str2 == null) {
            return 0;
        }
        while (i < str.length() && i < str2.length() && str.charAt(i) == str2.charAt(i)) {
            i++;
        }
        if (i < str2.length() || i < str.length()) {
            return i;
        }
        return -1;
    }

    public static int b(String[] strArr) {
        if (strArr == null || strArr.length <= 1) {
            return -1;
        }
        int length = strArr.length;
        boolean z = true;
        int i = Integer.MAX_VALUE;
        int i2 = 0;
        boolean z2 = false;
        for (int i3 = 0; i3 < length; i3++) {
            if (strArr[i3] == null) {
                i = 0;
                z2 = true;
            } else {
                int min = Math.min(strArr[i3].length(), i);
                i2 = Math.max(strArr[i3].length(), i2);
                i = min;
                z = false;
            }
        }
        if (z || (i2 == 0 && !z2)) {
            return -1;
        }
        if (i == 0) {
            return 0;
        }
        int i4 = -1;
        for (int i5 = 0; i5 < i; i5++) {
            char charAt = strArr[0].charAt(i5);
            int i6 = 1;
            while (true) {
                if (i6 >= length) {
                    break;
                } else if (strArr[i6].charAt(i5) != charAt) {
                    i4 = i5;
                    break;
                } else {
                    i6++;
                }
            }
            if (i4 != -1) {
                break;
            }
        }
        return (i4 != -1 || i == i2) ? i4 : i;
    }

    public static String c(String[] strArr) {
        if (strArr == null || strArr.length == 0) {
            return "";
        }
        int b2 = b(strArr);
        if (b2 == -1) {
            if (strArr[0] == null) {
                return "";
            }
            return strArr[0];
        } else if (b2 == 0) {
            return "";
        } else {
            return strArr[0].substring(0, b2);
        }
    }

    public static int R(String str, String str2) {
        if (str == null || str2 == null) {
            throw new IllegalArgumentException("Strings must not be null");
        }
        int length = str.length();
        int length2 = str2.length();
        if (length == 0) {
            return length2;
        }
        if (length2 == 0) {
            return length;
        }
        if (length > length2) {
            int i = length2;
            length2 = str.length();
            length = i;
        } else {
            String str3 = str2;
            str2 = str;
            str = str3;
        }
        int i2 = length + 1;
        int[] iArr = new int[i2];
        int[] iArr2 = new int[i2];
        for (int i3 = 0; i3 <= length; i3++) {
            iArr[i3] = i3;
        }
        int[] iArr3 = iArr;
        int[] iArr4 = iArr2;
        int i4 = 1;
        while (i4 <= length2) {
            char charAt = str.charAt(i4 - 1);
            iArr4[0] = i4;
            for (int i5 = 1; i5 <= length; i5++) {
                int i6 = i5 - 1;
                iArr4[i5] = Math.min(Math.min(iArr4[i6] + 1, iArr3[i5] + 1), iArr3[i6] + (str2.charAt(i6) == charAt ? 0 : 1));
            }
            i4++;
            int[] iArr5 = iArr3;
            iArr3 = iArr4;
            iArr4 = iArr5;
        }
        return iArr3[length];
    }

    public static boolean S(String str, String str2) {
        return a(str, str2, false);
    }

    public static boolean T(String str, String str2) {
        return a(str, str2, true);
    }

    private static boolean a(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            if (str == null && str2 == null) {
                return true;
            }
            return false;
        } else if (str2.length() > str.length()) {
            return false;
        } else {
            return str.regionMatches(z, 0, str2, 0, str2.length());
        }
    }

    public static boolean c(String str, String[] strArr) {
        if (a(str) || ArrayUtils.e((Object[]) strArr)) {
            return false;
        }
        for (String S : strArr) {
            if (S(str, S)) {
                return true;
            }
        }
        return false;
    }

    public static boolean U(String str, String str2) {
        return b(str, str2, false);
    }

    public static boolean V(String str, String str2) {
        return b(str, str2, true);
    }

    private static boolean b(String str, String str2, boolean z) {
        if (str == null || str2 == null) {
            if (str == null && str2 == null) {
                return true;
            }
            return false;
        } else if (str2.length() > str.length()) {
            return false;
        } else {
            return str.regionMatches(z, str.length() - str2.length(), str2, 0, str2.length());
        }
    }

    public static String R(String str) {
        String i = i(str);
        if (i == null || i.length() <= 2) {
            return i;
        }
        StrBuilder strBuilder = new StrBuilder(i.length());
        for (int i2 = 0; i2 < i.length(); i2++) {
            char charAt = i.charAt(i2);
            if (!Character.isWhitespace(charAt)) {
                strBuilder.a(charAt);
            } else if (i2 > 0 && !Character.isWhitespace(i.charAt(i2 - 1))) {
                strBuilder.a(' ');
            }
        }
        return strBuilder.toString();
    }

    public static boolean d(String str, String[] strArr) {
        if (a(str) || ArrayUtils.e((Object[]) strArr)) {
            return false;
        }
        for (String U : strArr) {
            if (U(str, U)) {
                return true;
            }
        }
        return false;
    }
}
