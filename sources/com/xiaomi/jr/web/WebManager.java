package com.xiaomi.jr.web;

import android.webkit.DownloadListener;
import com.xiaomi.jr.http.MifiHttpManager;
import com.xiaomi.jr.web.staticresource.StaticResourceApi;
import com.xiaomi.jr.web.webkit.WebViewConfig;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class WebManager {

    /* renamed from: a  reason: collision with root package name */
    private static StaticResourceApi f1455a;
    private static DownloadListener b;
    private static List<String> c = new ArrayList();
    private static List<WebViewConfig> d = new ArrayList();

    public static void a(DownloadListener downloadListener) {
        b = downloadListener;
    }

    public static void a(Collection<String> collection) {
        if (collection != null) {
            c.addAll(collection);
        }
    }

    public static StaticResourceApi a() {
        if (f1455a == null) {
            f1455a = (StaticResourceApi) MifiHttpManager.a().a(StaticResourceApi.class);
        }
        return f1455a;
    }

    static DownloadListener b() {
        return b;
    }

    public static boolean a(String str) {
        if (str == null) {
            return false;
        }
        for (String endsWith : c) {
            if (str.endsWith(endsWith)) {
                return true;
            }
        }
        return false;
    }

    public static void a(WebViewConfig webViewConfig) {
        d.add(webViewConfig);
    }

    public static void a(WebFragment webFragment, String str) {
        for (WebViewConfig next : d) {
            if (next.a(str)) {
                next.a(webFragment);
            }
        }
    }
}
