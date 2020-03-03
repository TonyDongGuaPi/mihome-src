package com.imi.fastjson.util;

import java.lang.ref.SoftReference;

public class ThreadLocalCache {

    /* renamed from: a  reason: collision with root package name */
    public static final int f6194a = 1024;
    public static final int b = 131072;
    public static final int c = 1024;
    public static final int d = 131072;
    private static final ThreadLocal<SoftReference<char[]>> e = new ThreadLocal<>();
    private static final ThreadLocal<SoftReference<byte[]>> f = new ThreadLocal<>();

    private static int a(int i, int i2, int i3) {
        while (i < i3) {
            i *= 2;
            if (i > i2) {
                return i3;
            }
        }
        return i;
    }

    public static void a() {
        e.set((Object) null);
    }

    public static char[] a(int i) {
        SoftReference softReference = e.get();
        if (softReference == null) {
            return c(i);
        }
        char[] cArr = (char[]) softReference.get();
        if (cArr == null) {
            return c(i);
        }
        return cArr.length < i ? c(i) : cArr;
    }

    private static char[] c(int i) {
        int a2 = a(1024, 131072, i);
        if (a2 > 131072) {
            return new char[i];
        }
        char[] cArr = new char[a2];
        e.set(new SoftReference(cArr));
        return cArr;
    }

    public static void b() {
        f.set((Object) null);
    }

    public static byte[] b(int i) {
        SoftReference softReference = f.get();
        if (softReference == null) {
            return d(i);
        }
        byte[] bArr = (byte[]) softReference.get();
        if (bArr == null) {
            return d(i);
        }
        return bArr.length < i ? d(i) : bArr;
    }

    private static byte[] d(int i) {
        int a2 = a(1024, 131072, i);
        if (a2 > 131072) {
            return new byte[i];
        }
        byte[] bArr = new byte[a2];
        f.set(new SoftReference(bArr));
        return bArr;
    }
}
