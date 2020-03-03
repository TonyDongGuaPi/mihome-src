package com.tencent.tinker.loader.hotplug;

import java.util.HashMap;
import java.util.Map;

public class ActivityStubManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9235a = "Tinker.ActivityStubManager";
    private static Map<String, String> b = new HashMap();
    private static final int[] c = {10, 3};
    private static final int[] d = {10, 3};
    private static final int[] e = {10, 3};
    private static final int[] f = {10, 3};
    private static final int[] g = {0, 0};
    private static final int[] h = {0, 0};
    private static final int[] i = {0, 0};
    private static final int[] j = {0, 0};
    private static final int k = 0;
    private static final int l = 1;

    public static String a(String str, int i2, boolean z) {
        String str2;
        int[] iArr;
        int[] iArr2;
        String str3;
        char c2;
        String str4 = b.get(str);
        if (str4 != null) {
            return str4;
        }
        switch (i2) {
            case 1:
                str2 = ActivityStubs.c;
                iArr2 = h;
                iArr = d;
                break;
            case 2:
                str2 = ActivityStubs.d;
                iArr2 = i;
                iArr = e;
                break;
            case 3:
                str2 = ActivityStubs.e;
                iArr2 = j;
                iArr = f;
                break;
            default:
                str2 = ActivityStubs.b;
                iArr2 = g;
                iArr = c;
                break;
        }
        if (z) {
            str3 = str2 + "_T";
            c2 = 1;
        } else {
            str3 = str2;
            c2 = 0;
        }
        int i3 = iArr2[c2];
        iArr2[c2] = i3 + 1;
        if (i3 >= iArr[c2]) {
            iArr2[c2] = 0;
            i3 = 0;
        }
        String format = String.format(str3, new Object[]{Integer.valueOf(i3)});
        b.put(str, format);
        return format;
    }

    private ActivityStubManager() {
        throw new UnsupportedOperationException();
    }
}
