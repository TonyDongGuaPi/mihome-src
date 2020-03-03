package com.xiaomiyoupin.ypdimage;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.taobao.weex.common.WXModule;
import com.taobao.weex.ui.component.WXComponent;
import com.xiaomiyoupin.ypdimage.duplo.rn.YPDImageViewUIModuleRN;
import com.xiaomiyoupin.ypdimage.duplo.weex.YPDImageViewComponentWX;
import com.xiaomiyoupin.ypdimage.duplo.weex.YPDImageViewUIModuleWX;
import okhttp3.OkHttpClient;

public class YPDImage {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1781a = "YPDImageLoaderModule";
    public static final int b = 20191114;
    private static volatile YPDImage c = null;
    private static final String d = "YPDImageComponent";
    private OkHttpClientBuilderInterceptor e;

    public interface OkHttpClientBuilderInterceptor {
        void a(OkHttpClient.Builder builder);
    }

    public String b() {
        return d;
    }

    public String c() {
        return f1781a;
    }

    public String d() {
        return d;
    }

    public String f() {
        return f1781a;
    }

    private YPDImage() {
    }

    public static YPDImage a() {
        if (c == null) {
            synchronized (YPDImage.class) {
                if (c == null) {
                    c = new YPDImage();
                }
            }
        }
        return c;
    }

    public ReactContextBaseJavaModule a(ReactApplicationContext reactApplicationContext) {
        return new YPDImageViewUIModuleRN(reactApplicationContext);
    }

    public Class<? extends WXComponent> e() {
        return YPDImageViewComponentWX.class;
    }

    public Class<? extends WXModule> g() {
        return YPDImageViewUIModuleWX.class;
    }

    public void a(OkHttpClientBuilderInterceptor okHttpClientBuilderInterceptor) {
        this.e = okHttpClientBuilderInterceptor;
    }

    public OkHttpClientBuilderInterceptor h() {
        return this.e;
    }
}
