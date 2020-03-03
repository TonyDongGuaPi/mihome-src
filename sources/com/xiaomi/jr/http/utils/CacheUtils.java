package com.xiaomi.jr.http.utils;

import android.support.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import okhttp3.HttpUrl;

public class CacheUtils {

    /* renamed from: a  reason: collision with root package name */
    private static Map<Pattern, Set<String>> f10840a = new HashMap();

    public static void a(String str, Set<String> set) {
        f10840a.put(Pattern.compile(str), set);
    }

    @NonNull
    public static HttpUrl a(HttpUrl httpUrl) {
        Set<String> set;
        for (Pattern next : f10840a.keySet()) {
            if (next.matcher(httpUrl.toString()).matches() && (set = f10840a.get(next)) != null) {
                HttpUrl.Builder newBuilder = httpUrl.newBuilder();
                for (String removeAllQueryParameters : set) {
                    newBuilder.removeAllQueryParameters(removeAllQueryParameters);
                }
                return newBuilder.build();
            }
        }
        return httpUrl;
    }
}
