package com.xiaomi.smarthome.operation.js_sdk.linker;

import android.content.Context;
import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.LoginApi;
import com.xiaomi.smarthome.framework.log.LogUtil;
import com.xiaomi.smarthome.operation.js_sdk.linker.JumperProvider;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class DeepLinkDelegate {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21083a = "DeepLinkDelegate";
    private static DeepLinkDelegate b = new DeepLinkDelegate();

    private DeepLinkDelegate() {
    }

    public static DeepLinkDelegate a() {
        return b;
    }

    public boolean a(Uri uri) {
        boolean z;
        if (uri == null || !TextUtils.equals("mihome", uri.getScheme())) {
            return false;
        }
        JumperProvider.UrlJumper a2 = JumperProvider.a(uri.getHost());
        if (a2 == null) {
            LogUtil.b(f21083a, "shouldOverrideUrlLoading: no jumper found for ignore");
            return false;
        }
        LogUtil.a(f21083a, "process: uri: " + uri);
        Set<String> queryParameterNames = uri.getQueryParameterNames();
        if (queryParameterNames == null || queryParameterNames.size() == 0) {
            return false;
        }
        if (!uri.getBooleanQueryParameter("requireLogin", false) || CoreApi.a().q()) {
            HashSet<String> hashSet = new HashSet<>(queryParameterNames);
            hashSet.removeAll(Collections.singletonList("requireLogin"));
            ArrayMap arrayMap = new ArrayMap();
            for (String str : hashSet) {
                String queryParameter = uri.getQueryParameter(str);
                if (!TextUtils.isEmpty(queryParameter)) {
                    arrayMap.put(str, queryParameter);
                }
            }
            try {
                z = a2.handle(uri, arrayMap);
            } catch (Exception e) {
                LogUtil.a(f21083a, "process: by target: " + Log.getStackTraceString(e));
                z = false;
            }
            LogUtil.a(f21083a, "process: handled: " + z);
            return z;
        }
        LoginApi.a().a((Context) SHApplication.getApplication(), 1, (LoginApi.LoginCallback) null);
        return true;
    }
}
