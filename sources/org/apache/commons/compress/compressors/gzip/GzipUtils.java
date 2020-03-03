package org.apache.commons.compress.compressors.gzip;

import java.util.LinkedHashMap;
import org.apache.commons.compress.compressors.FileNameUtil;

public class GzipUtils {

    /* renamed from: a  reason: collision with root package name */
    private static final FileNameUtil f3325a;

    static {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        linkedHashMap.put(".tgz", ".tar");
        linkedHashMap.put(".taz", ".tar");
        linkedHashMap.put(".svgz", ".svg");
        linkedHashMap.put(".cpgz", ".cpio");
        linkedHashMap.put(".wmz", ".wmf");
        linkedHashMap.put(".emz", ".emf");
        linkedHashMap.put(".gz", "");
        linkedHashMap.put(".z", "");
        linkedHashMap.put("-gz", "");
        linkedHashMap.put("-z", "");
        linkedHashMap.put("_z", "");
        f3325a = new FileNameUtil(linkedHashMap, ".gz");
    }

    private GzipUtils() {
    }

    public static boolean a(String str) {
        return f3325a.a(str);
    }

    public static String b(String str) {
        return f3325a.b(str);
    }

    public static String c(String str) {
        return f3325a.c(str);
    }
}
