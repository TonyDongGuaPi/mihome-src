package com.ximalaya.ting.android.opensdk.auth.component;

import android.content.Context;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.auth.call.IXmlyAuthListener;
import java.util.HashMap;
import java.util.Map;

public final class e {

    /* renamed from: a  reason: collision with root package name */
    private static e f1821a;
    private Context b;
    private Map<String, IXmlyAuthListener> c = new HashMap();

    private e(Context context) {
        this.b = context;
    }

    public static synchronized e a(Context context) {
        e eVar;
        synchronized (e.class) {
            if (f1821a == null) {
                f1821a = new e(context);
            }
            eVar = f1821a;
        }
        return eVar;
    }

    public final synchronized IXmlyAuthListener a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return this.c.get(str);
    }

    public final synchronized void a(String str, IXmlyAuthListener iXmlyAuthListener) {
        if (!TextUtils.isEmpty(str) && iXmlyAuthListener != null) {
            this.c.put(str, iXmlyAuthListener);
        }
    }

    public final synchronized void b(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.c.remove(str);
        }
    }

    private static String a() {
        return String.valueOf(System.currentTimeMillis());
    }
}
