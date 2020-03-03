package com.xiaomi.jr.stats;

import android.content.Context;
import android.view.View;
import android.webkit.WebView;
import java.util.Map;

public interface SensorsDataTarget {
    void a();

    void a(Context context, String str, boolean z);

    void a(View view, Map<String, String> map);

    void a(WebView webView);

    void a(String str, String str2);

    void a(String str, Map<String, String> map);

    void a(Map map);

    void a(boolean z);

    void b();

    void b(WebView webView);
}
