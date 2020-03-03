package com.xiaomi.smarthome.library.common.util;

import android.graphics.Typeface;
import android.os.Looper;
import android.text.TextUtils;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.log.MyLog;
import java.util.HashMap;

public class FontManager {

    /* renamed from: a  reason: collision with root package name */
    private static final HashMap<String, Typeface> f18673a = new HashMap<>();

    public static Typeface a(String str) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            Typeface defaultFromStyle = Typeface.defaultFromStyle(0);
            if (TextUtils.isEmpty(str)) {
                return defaultFromStyle;
            }
            Typeface typeface = f18673a.get(str);
            if (typeface == null) {
                try {
                    typeface = Typeface.createFromAsset(SHApplication.getAppContext().getAssets(), str);
                } catch (Exception e) {
                    MyLog.a((Throwable) e);
                }
                if (typeface != null) {
                    f18673a.put(str, typeface);
                }
            }
            return typeface != null ? typeface : defaultFromStyle;
        }
        throw new IllegalStateException("Method call should happen from the main thread.");
    }
}
