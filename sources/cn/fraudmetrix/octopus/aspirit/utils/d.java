package cn.fraudmetrix.octopus.aspirit.utils;

import android.text.TextUtils;
import java.util.Collection;

public class d {
    public static void a(Object obj, String str) {
        if (obj == null) {
            throw new RuntimeException(str);
        }
    }

    public static void a(String str, String str2) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException(str2);
        }
    }

    public static void a(Collection<?> collection, String str) {
        if (collection == null || collection.size() == 0) {
            throw new RuntimeException(str);
        }
    }
}
