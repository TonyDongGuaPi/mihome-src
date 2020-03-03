package com.xiaomi.jr.appbase;

import android.util.SparseArray;

public class CustomizedSnippets {

    /* renamed from: a  reason: collision with root package name */
    public static int f1388a = 1;
    public static int b = 2;
    public static int c = 3;
    public static int d = 4;
    public static int e = 5;
    public static int f = 6;
    public static int g = 7;
    public static int h = 8;
    public static int i = 9;
    public static int j = 10;
    public static int k = 11;
    public static int l = 12;
    public static int m = 13;
    private static SparseArray<Snippet> n = new SparseArray<>();

    public interface Snippet {
        Object exec(Object... objArr);
    }

    public static void a(int i2, Snippet snippet) {
        n.put(i2, snippet);
    }

    public static Object a(int i2, Object... objArr) {
        Snippet snippet = n.get(i2);
        if (snippet != null) {
            return snippet.exec(objArr);
        }
        return null;
    }
}
