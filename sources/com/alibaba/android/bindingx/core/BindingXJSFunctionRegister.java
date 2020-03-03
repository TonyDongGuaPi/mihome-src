package com.alibaba.android.bindingx.core;

import android.text.TextUtils;
import com.alibaba.android.bindingx.core.internal.JSFunctionInterface;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public class BindingXJSFunctionRegister {

    /* renamed from: a  reason: collision with root package name */
    private static final BindingXJSFunctionRegister f741a = new BindingXJSFunctionRegister();
    private final LinkedHashMap<String, JSFunctionInterface> b = new LinkedHashMap<>(8);

    public static BindingXJSFunctionRegister a() {
        return f741a;
    }

    public void a(String str, JSFunctionInterface jSFunctionInterface) {
        if (!TextUtils.isEmpty(str) && jSFunctionInterface != null) {
            this.b.put(str, jSFunctionInterface);
        }
    }

    public boolean a(String str) {
        if (TextUtils.isEmpty(str) || this.b.remove(str) == null) {
            return false;
        }
        return true;
    }

    public void b() {
        this.b.clear();
    }

    public Map<String, JSFunctionInterface> c() {
        return Collections.unmodifiableMap(this.b);
    }
}
