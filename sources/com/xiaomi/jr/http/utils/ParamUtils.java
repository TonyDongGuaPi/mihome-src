package com.xiaomi.jr.http.utils;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.Map;

public class ParamUtils {
    public static Map<String, String> a(Map<String, String> map) {
        if (map != null) {
            Iterator<String> it = map.keySet().iterator();
            while (it.hasNext()) {
                if (TextUtils.isEmpty(map.get(it.next()))) {
                    it.remove();
                }
            }
        }
        return map;
    }
}
