package com.tmall.wireless.vaf.framework;

import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

public class UserData {

    /* renamed from: a  reason: collision with root package name */
    private Map<String, Object> f9360a = new HashMap();

    public void a() {
        this.f9360a.clear();
    }

    public void a(String str, Object obj) {
        if (!TextUtils.isEmpty(str) && obj != null) {
            this.f9360a.put(str, obj);
        }
    }

    public Object a(String str) {
        return this.f9360a.get(str);
    }
}
