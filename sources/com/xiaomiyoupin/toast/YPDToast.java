package com.xiaomiyoupin.toast;

import android.content.Context;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.taobao.weex.common.WXModule;
import java.lang.ref.WeakReference;

public class YPDToast {
    public static final int ERROR = 1003;
    public static final int INFO = 1001;
    public static final int LOADING = 1004;
    public static final int SUCCESS = 1002;
    private static WeakReference<Context> applicationRef = null;
    static final String innerVersion = "20190618";
    private static volatile YPDToast toast = null;
    static final String ypdRNModuleName = "YPDToastModule";
    static final String ypdWXModuleName = "YPDToastModule";
    private YPDToastInterceptor interceptor;

    public String getRNModuleName() {
        return "YPDToastModule";
    }

    public String getWXModuleName() {
        return "YPDToastModule";
    }

    private YPDToast() {
    }

    public static void init(Context context) {
        if (applicationRef == null) {
            synchronized (YPDToast.class) {
                if (applicationRef == null) {
                    applicationRef = new WeakReference<>(context);
                }
            }
        }
    }

    public Context getApplicationContext() {
        if (applicationRef == null) {
            return null;
        }
        return (Context) applicationRef.get();
    }

    public static YPDToast getInstance() {
        if (toast == null) {
            synchronized (YPDToast.class) {
                if (toast == null) {
                    toast = new YPDToast();
                }
            }
        }
        return toast;
    }

    public void setInterceptor(YPDToastInterceptor yPDToastInterceptor) {
        this.interceptor = yPDToastInterceptor;
    }

    /* access modifiers changed from: package-private */
    public YPDToastInterceptor getInterceptor() {
        if (this.interceptor == null) {
            this.interceptor = new DefaultInterceptor();
        }
        return this.interceptor;
    }

    public ReactContextBaseJavaModule createRNModuleInstance(ReactApplicationContext reactApplicationContext) {
        return new YPDToastUIModuleRN(reactApplicationContext);
    }

    public Class<? extends WXModule> getWXModuleClass() {
        return YPDToastUIModuleWX.class;
    }

    public int toast(Context context, int i) {
        return YPDToastManager.getInstance().toast(context, i);
    }

    public int toast(Context context, String str) {
        return YPDToastManager.getInstance().toast(context, str);
    }

    public int toast(Context context, String str, int i) {
        return toast(context, str, i, true);
    }

    public int toast(Context context, String str, int i, boolean z) {
        return YPDToastManager.getInstance().toast(context, str, i, z);
    }

    public int toast(Context context, String str, int i, boolean z, int i2) {
        return YPDToastManager.getInstance().toast(context, str, i, z, i2);
    }

    public void dismiss(int i) {
        YPDToastManager.getInstance().dismiss(i);
    }

    private static class DefaultInterceptor implements YPDToastInterceptor {
        private DefaultInterceptor() {
        }

        public Object makeToast(Context context, String str, int i, long j) {
            return Integer.valueOf(YPDToast.getInstance().toast(context, str, 5, true, i == 1002 ? 2 : i == 1003 ? 3 : i == 1001 ? 1 : i));
        }

        public Object makeToast(Context context, String str, long j, boolean z) {
            return Integer.valueOf(YPDToast.getInstance().toast(context, str, 2, !z));
        }

        public Object dismiss(Object obj) {
            if (!(obj instanceof Integer)) {
                return null;
            }
            YPDToast.getInstance().dismiss(((Integer) obj).intValue());
            return null;
        }
    }
}
