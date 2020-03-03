package org.apache.commons.compress.compressors.xz;

import cn.com.fmsh.tsm.business.constants.Constants;
import java.util.HashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public class XZUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final FileNameUtil f3341a;
    private static final byte[] b = {-3, 55, Constants.TagName.ELECTRONIC_OUT_STATE, 88, Constants.TagName.PREDEPOSIT_TOTAL, 0};
    private static volatile CachedAvailability c = CachedAvailability.DONT_CACHE;

    enum CachedAvailability {
        DONT_CACHE,
        CACHED_AVAILABLE,
        CACHED_UNAVAILABLE
    }

    static {
        HashMap hashMap = new HashMap();
        hashMap.put(".txz", ".tar");
        hashMap.put(".xz", "");
        hashMap.put("-xz", "");
        f3341a = new FileNameUtil(hashMap, ".xz");
        try {
            Class.forName("org.osgi.framework.BundleEvent");
        } catch (Exception unused) {
            a(true);
        }
    }

    private XZUtils() {
    }

    public static boolean a(byte[] bArr, int i) {
        if (i < b.length) {
            return false;
        }
        for (int i2 = 0; i2 < b.length; i2++) {
            if (bArr[i2] != b[i2]) {
                return false;
            }
        }
        return true;
    }

    public static boolean a() {
        CachedAvailability cachedAvailability = c;
        if (cachedAvailability != CachedAvailability.DONT_CACHE) {
            return cachedAvailability == CachedAvailability.CACHED_AVAILABLE;
        }
        return c();
    }

    private static boolean c() {
        try {
            XZCompressorInputStream.a((byte[]) null, 0);
            return true;
        } catch (NoClassDefFoundError unused) {
            return false;
        }
    }

    public static boolean a(String str) {
        return f3341a.a(str);
    }

    public static String b(String str) {
        return f3341a.b(str);
    }

    public static String c(String str) {
        return f3341a.c(str);
    }

    public static void a(boolean z) {
        if (!z) {
            c = CachedAvailability.DONT_CACHE;
        } else if (c == CachedAvailability.DONT_CACHE) {
            c = c() ? CachedAvailability.CACHED_AVAILABLE : CachedAvailability.CACHED_UNAVAILABLE;
        }
    }

    static CachedAvailability b() {
        return c;
    }
}
