package com.youpin.weex.app.common;

import android.app.Activity;
import com.xiaomi.miot.store.api.ICallback;
import com.youpin.weex.app.model.pojo.PreCachePage;
import java.util.ArrayList;
import java.util.Map;

public interface WXStoreApiProvider {

    /* renamed from: a  reason: collision with root package name */
    public static final String f2510a = "channel";
    public static final String b = "url";
    public static final String c = "result";
    public static final String d = "status";

    Map a();

    void a(Activity activity);

    void a(Activity activity, String str, String str2, ICallback iCallback);

    void a(Activity activity, String str, String str2, boolean z);

    void a(Activity activity, Map<String, Object> map, ICallback iCallback);

    void a(ICallback iCallback);

    void a(String str);

    void a(String str, String str2, String str3);

    void a(String str, String str2, String str3, int i);

    void a(String str, String str2, String str3, String str4, ICallback iCallback);

    void a(String str, String str2, String str3, String str4, Map<String, String> map, ICallback iCallback);

    void a(Throwable th);

    void a(boolean z, ICallback iCallback);

    ArrayList<PreCachePage> b();

    void b(ICallback iCallback);

    void c();

    void c(ICallback iCallback);
}
