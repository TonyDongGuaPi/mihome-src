package com.tencent.wxop.stat.common;

import java.io.File;

class o {

    /* renamed from: a  reason: collision with root package name */
    private static int f9322a = -1;

    public static boolean a() {
        if (f9322a == 1) {
            return true;
        }
        if (f9322a == 0) {
            return false;
        }
        String[] strArr = {"/bin", "/system/bin/", "/system/xbin/", "/system/sbin/", "/sbin/", "/vendor/bin/"};
        int i = 0;
        while (i < 6) {
            try {
                if (new File(strArr[i] + "su").exists()) {
                    f9322a = 1;
                    return true;
                }
                i++;
            } catch (Exception unused) {
            }
        }
        f9322a = 0;
        return false;
    }
}
