package org.apache.commons.compress.compressors.bzip2;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public abstract class BZip2Utils {

    /* renamed from: a  reason: collision with root package name */
    private static final FileNameUtil f3315a;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(".tar.bz2", ".tar");
        linkedHashMap.put(".tbz2", ".tar");
        linkedHashMap.put(".tbz", ".tar");
        linkedHashMap.put(".bz2", "");
        linkedHashMap.put(".bz", "");
        f3315a = new FileNameUtil(linkedHashMap, ".bz2");
    }

    private BZip2Utils() {
    }

    public static boolean a(String str) {
        return f3315a.a(str);
    }

    public static String b(String str) {
        return f3315a.b(str);
    }

    public static String c(String str) {
        return f3315a.c(str);
    }
}
