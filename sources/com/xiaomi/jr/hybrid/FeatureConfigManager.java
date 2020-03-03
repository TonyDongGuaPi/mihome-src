package com.xiaomi.jr.hybrid;

import android.net.Uri;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FeatureConfigManager {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Config> f1438a = new HashMap();

    public static class Config {

        /* renamed from: a  reason: collision with root package name */
        List<String> f10843a = new ArrayList();
        List<String> b = new ArrayList();
        Map<String, List<String>> c = new HashMap();
        Map<String, List<String>> d = new HashMap();
    }

    public static void a(Map<String, List<String>> map) {
        for (String next : map.keySet()) {
            List list = map.get(next);
            if (list != null) {
                f1438a.put(next, a((List<String>) list));
            }
        }
    }

    public static boolean a(String str, String str2, String str3) {
        String authority;
        if (str == null) {
            return true;
        }
        if (str2 == null || str3 == null || (authority = Uri.parse(str).getAuthority()) == null) {
            return false;
        }
        for (String next : f1438a.keySet()) {
            if (authority.endsWith(next)) {
                Config config = f1438a.get(next);
                if (!a(config.f10843a, config.b, str2) || !a(config.c.get(str2), config.d.get(str2), str3)) {
                    return false;
                }
                return true;
            }
        }
        return false;
    }

    private static boolean a(List<String> list, List<String> list2, String str) {
        if (list == null) {
            list = Collections.emptyList();
        }
        if (list2 == null) {
            list2 = Collections.emptyList();
        }
        if (list.isEmpty() && list2.isEmpty()) {
            return true;
        }
        if (list.isEmpty()) {
            return !list2.contains(str);
        }
        if (list2.isEmpty()) {
            return list.contains(str);
        }
        return !list2.contains(str);
    }

    private static Config a(List<String> list) {
        Config config = new Config();
        for (String split : list) {
            String[] split2 = split.split(":");
            if (split2.length == 2) {
                String[] split3 = split2[0].split(",");
                String[] split4 = split2[1].split(",");
                boolean startsWith = split3[0].startsWith("!");
                boolean startsWith2 = split4[0].startsWith("!");
                if (startsWith) {
                    split3[0] = split3[0].substring(1);
                    a(config.b, split3);
                } else {
                    a(config.f10843a, split3);
                    if (split3.length == 1) {
                        String str = split3[0];
                        if (startsWith2) {
                            split4[0] = split4[0].substring(1);
                            if (!config.d.containsKey(str)) {
                                config.d.put(str, new ArrayList());
                            }
                            a(config.d.get(str), split4);
                        } else {
                            if (!config.c.containsKey(str)) {
                                config.c.put(str, new ArrayList());
                            }
                            a(config.c.get(str), split4);
                        }
                    }
                }
            }
        }
        return config;
    }

    private static void a(List<String> list, String[] strArr) {
        if (!strArr[0].equals("*")) {
            Collections.addAll(list, strArr);
        }
    }
}
