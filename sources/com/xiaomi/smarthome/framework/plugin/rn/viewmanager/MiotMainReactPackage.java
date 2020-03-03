package com.xiaomi.smarthome.framework.plugin.rn.viewmanager;

import android.content.Context;
import android.graphics.Canvas;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.drawee.controller.AbstractDraweeControllerBuilder;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.image.GlobalImageLoadListener;
import com.facebook.react.views.image.ReactImageManager;
import com.facebook.react.views.image.ReactImageView;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.plugin.rn.RNRuntime;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.okhttpApi.OkHttpApi;
import com.xiaomi.youpin.login.other.cookie.YouPinCookieUtil;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;

public class MiotMainReactPackage extends MainReactPackage {

    /* renamed from: a  reason: collision with root package name */
    RNRuntime.RuntimeInfo f17544a = null;

    public MiotMainReactPackage(RNRuntime.RuntimeInfo runtimeInfo) {
        this.f17544a = runtimeInfo;
    }

    private class MiotReactImageView extends ReactImageView {
        boolean mFiltered = false;

        public MiotReactImageView(Context context, AbstractDraweeControllerBuilder abstractDraweeControllerBuilder, GlobalImageLoadListener globalImageLoadListener, @Nullable Object obj) {
            super(context, abstractDraweeControllerBuilder, globalImageLoadListener, obj);
        }

        /* access modifiers changed from: protected */
        public void onDraw(Canvas canvas) {
            if (this.mFiltered) {
                getDrawable().setFilterBitmap(false);
                this.mFiltered = false;
            }
            super.onDraw(canvas);
        }
    }

    private ReactImageManager a() {
        return new ReactImageManager() {
            public ReactImageView createViewInstance(ThemedReactContext themedReactContext) {
                return new MiotReactImageView(themedReactContext, getDraweeControllerBuilder(), (GlobalImageLoadListener) null, getCallerContext()) {
                };
            }

            public void setResizeMode(ReactImageView reactImageView, @Nullable String str) {
                if ((reactImageView instanceof MiotReactImageView) && "stretch".equalsIgnoreCase(str)) {
                    ((MiotReactImageView) reactImageView).mFiltered = true;
                }
                super.setResizeMode(reactImageView, str);
            }

            /* JADX WARNING: Removed duplicated region for block: B:17:0x008a  */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void setSource(com.facebook.react.views.image.ReactImageView r8, @javax.annotation.Nullable com.facebook.react.bridge.ReadableArray r9) {
                /*
                    r7 = this;
                    if (r9 == 0) goto L_0x0098
                    int r0 = r9.size()
                    if (r0 <= 0) goto L_0x0098
                    r0 = 0
                    com.facebook.react.bridge.ReadableType r1 = r9.getType(r0)
                    com.facebook.react.bridge.ReadableType r2 = com.facebook.react.bridge.ReadableType.Map
                    if (r1 != r2) goto L_0x0098
                    com.facebook.react.bridge.ReadableMap r1 = r9.getMap(r0)
                    r2 = 0
                    java.lang.String r3 = "uri"
                    boolean r3 = r1.hasKey(r3)
                    if (r3 == 0) goto L_0x0049
                    java.lang.String r3 = "uri"
                    java.lang.String r3 = r1.getString(r3)
                    if (r3 == 0) goto L_0x0088
                    java.lang.String r4 = "/"
                    boolean r4 = r3.startsWith(r4)
                    if (r4 == 0) goto L_0x0088
                    java.util.HashMap r1 = r1.toHashMap()
                    java.lang.String r2 = "uri"
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "file://"
                    r4.append(r5)
                    r4.append(r3)
                    java.lang.String r3 = r4.toString()
                    r1.put(r2, r3)
                    goto L_0x0087
                L_0x0049:
                    java.lang.String r3 = "local"
                    boolean r3 = r1.hasKey(r3)
                    if (r3 == 0) goto L_0x0088
                    java.lang.String r2 = "local"
                    java.lang.String r2 = r1.getString(r2)
                    java.util.HashMap r1 = r1.toHashMap()
                    java.lang.String r3 = "local"
                    r1.remove(r3)
                    java.lang.String r3 = "uri"
                    java.lang.StringBuilder r4 = new java.lang.StringBuilder
                    r4.<init>()
                    java.lang.String r5 = "file://"
                    r4.append(r5)
                    java.io.File r5 = new java.io.File
                    com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MiotMainReactPackage r6 = com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MiotMainReactPackage.this
                    com.xiaomi.smarthome.framework.plugin.rn.RNRuntime$RuntimeInfo r6 = r6.f17544a
                    java.io.File r6 = r6.c()
                    r5.<init>(r6, r2)
                    java.lang.String r2 = r5.getAbsolutePath()
                    r4.append(r2)
                    java.lang.String r2 = r4.toString()
                    r1.put(r3, r2)
                L_0x0087:
                    r2 = r1
                L_0x0088:
                    if (r2 == 0) goto L_0x0098
                    java.util.ArrayList r9 = r9.toArrayList()
                    r9.remove(r0)
                    r9.add(r0, r2)
                    com.facebook.react.bridge.WritableNativeArray r9 = com.facebook.react.bridge.Arguments.makeNativeArray((java.util.List) r9)
                L_0x0098:
                    r8.setSource(r9)
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.framework.plugin.rn.viewmanager.MiotMainReactPackage.AnonymousClass1.setSource(com.facebook.react.views.image.ReactImageView, com.facebook.react.bridge.ReadableArray):void");
            }
        };
    }

    private static class CookieWriter {

        /* renamed from: a  reason: collision with root package name */
        boolean f17545a;

        private CookieWriter() {
            this.f17545a = false;
        }

        private void a(CookieManager cookieManager, String str, String str2, String str3) {
            if (str != null && str2 != null && str3 != null) {
                cookieManager.setCookie(str3, str + "=" + str2 + "; domain=" + str3);
            }
        }

        public void a(Context context) {
            HttpCookie a2;
            if (!this.f17545a) {
                this.f17545a = true;
                CookieSyncManager.createInstance(context);
                CookieManager instance = CookieManager.getInstance();
                String w = CoreApi.a().w();
                if (TextUtils.isEmpty(w) && (a2 = YouPinCookieUtil.a(OkHttpApi.a().c(), "passToken")) != null) {
                    w = a2.getValue();
                }
                if (!TextUtils.isEmpty(w)) {
                    a(instance, "passToken", w, ".account.xiaomi.com");
                }
                String s = CoreApi.a().s();
                if (!TextUtils.isEmpty(s)) {
                    a(instance, "userId", s, ".home.mi.com");
                    a(instance, "userId", s, ".account.xiaomi.com");
                }
                try {
                    MiServiceTokenInfo a3 = CoreApi.a().a("xiaomiio");
                    if (a3 != null) {
                        a(instance, "serviceToken", URLEncoder.encode(a3.c, "UTF-8"), ".io.mi.com");
                    }
                    MiServiceTokenInfo a4 = CoreApi.a().a("xiaomihome");
                    if (a4 != null) {
                        a(instance, "serviceToken", URLEncoder.encode(a4.c, "UTF-8"), ".home.mi.com");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        new CookieWriter().a(reactApplicationContext);
        ArrayList<Pair> arrayList = new ArrayList<>();
        List<ViewManager> createViewManagers = super.createViewManagers(reactApplicationContext);
        for (ViewManager next : createViewManagers) {
            if (next instanceof ReactImageManager) {
                arrayList.add(new Pair(next, a()));
            }
        }
        for (Pair pair : arrayList) {
            createViewManagers.remove(pair.first);
            createViewManagers.add(pair.second);
        }
        return createViewManagers;
    }
}
