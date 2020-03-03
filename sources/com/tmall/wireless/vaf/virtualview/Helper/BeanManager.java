package com.tmall.wireless.vaf.virtualview.Helper;

import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.libra.TextUtils;
import com.tmall.wireless.vaf.virtualview.core.IBean;

public class BeanManager {

    /* renamed from: a  reason: collision with root package name */
    private static final String f9366a = "BeanManager_TMTEST";
    private ArrayMap<String, Class<? extends IBean>> b = new ArrayMap<>();

    public void a(String str, Class<? extends IBean> cls) {
        if (cls == null || TextUtils.a(str)) {
            Log.e(f9366a, "register failed type:" + str + "  processor:" + cls);
            return;
        }
        this.b.put(str, cls);
    }

    public void b(String str, Class<? extends IBean> cls) {
        if (cls == null || TextUtils.a(str)) {
            Log.e(f9366a, "unregister failed type:" + str + "  processor:" + cls);
            return;
        }
        this.b.remove(str);
    }

    public Class<? extends IBean> a(String str) {
        return this.b.get(str);
    }
}
