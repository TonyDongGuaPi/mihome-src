package com.xiaomi.smarthome.framework.plugin.rn.fixbug;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.react.bridge.NativeModule;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.modules.fresco.FrescoModule;
import com.facebook.react.modules.statusbar.StatusBarModule;
import com.facebook.react.shell.MainReactPackage;
import com.facebook.react.uimanager.ViewManager;
import com.facebook.react.views.image.ReactImageManager;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.image.FixReactImageManager;
import com.xiaomi.smarthome.framework.plugin.rn.fixbug.statusbar.FixStatusBarModule;
import com.xiaomi.smarthome.framework.plugin.rn.utils.RnPluginLog;
import com.xiaomi.smarthome.framework.plugin.rn.viewmanager.image.MHFrescoModule;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.okhttpApi.OkHttpApi;
import com.xiaomi.youpin.login.other.cookie.YouPinCookieUtil;
import java.net.HttpCookie;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class FixBugMainReactPackage extends MainReactPackage {
    @Nullable
    public NativeModule getModule(String str, ReactApplicationContext reactApplicationContext) {
        if (FrescoModule.NAME.equals(str)) {
            return new MHFrescoModule(reactApplicationContext, true, (ImagePipelineConfig) null);
        }
        if (StatusBarModule.NAME.equals(str)) {
            return new FixStatusBarModule(reactApplicationContext);
        }
        return super.getModule(str, reactApplicationContext);
    }

    public List<ViewManager> createViewManagers(ReactApplicationContext reactApplicationContext) {
        new CookieWriter().a(reactApplicationContext);
        ArrayList<Pair> arrayList = new ArrayList<>();
        List<ViewManager> createViewManagers = super.createViewManagers(reactApplicationContext);
        for (ViewManager next : createViewManagers) {
            if (next instanceof ReactImageManager) {
                RnPluginLog.a("will replace ReactImageManager...");
                arrayList.add(new Pair(next, new FixReactImageManager()));
            }
        }
        for (Pair pair : arrayList) {
            createViewManagers.remove(pair.first);
            createViewManagers.add(pair.second);
        }
        return createViewManagers;
    }

    private static class CookieWriter {

        /* renamed from: a  reason: collision with root package name */
        boolean f17275a;

        private CookieWriter() {
            this.f17275a = false;
        }

        private void a(CookieManager cookieManager, String str, String str2, String str3) {
            if (str != null && str2 != null && str3 != null) {
                cookieManager.setCookie(str3, str + "=" + str2 + "; domain=" + str3);
            }
        }

        public void a(Context context) {
            HttpCookie a2;
            if (!this.f17275a) {
                this.f17275a = true;
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
}
