package com.xiaomi.jr.appbase.utils;

import android.text.TextUtils;
import java.util.regex.Pattern;

public class MiFiUrlUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final String f10323a = "https://s\\.mi\\.cn/[lf]/.*";
    private static final Pattern b = Pattern.compile(f10323a);
    private static final String c = "https?://([a-zA-Z0-9]+\\.)*jr\\.mi\\.com(/.*)?";
    private static final Pattern d = Pattern.compile(c);

    public static boolean a(String str) {
        return b(str) || c(str);
    }

    public static boolean b(String str) {
        return !TextUtils.isEmpty(str) && (str.startsWith(AppConstants.z) || str.startsWith(AppConstants.o) || str.startsWith(AppConstants.n) || str.startsWith(AppConstants.p) || d.matcher(str).matches());
    }

    private static boolean c(String str) {
        return b.matcher(str).matches();
    }
}
