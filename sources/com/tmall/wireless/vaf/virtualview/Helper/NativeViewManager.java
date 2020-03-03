package com.tmall.wireless.vaf.virtualview.Helper;

import android.support.v4.util.ArrayMap;
import com.libra.TextUtils;

public class NativeViewManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9369a = "CompactNativeManager_TMTEST";
    private ArrayMap<String, Class<?>> b = new ArrayMap<>();

    public void a(String str, Class<?> cls) {
        if (cls != null && !TextUtils.a(str)) {
            this.b.put(str, cls);
        }
    }

    public void b(String str, Class<?> cls) {
        if (cls != null && !TextUtils.a(str)) {
            this.b.remove(str);
        }
    }

    public Class<?> a(String str) {
        return this.b.get(str);
    }
}
