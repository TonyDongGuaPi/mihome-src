package com.xiaomi.youpin.app_sdk.weex;

import android.app.Activity;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.youpin.app_sdk.url_dispatch.UrlDispatchManger;
import com.xiaomi.youpin.log.LogUtils;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.xiaomi.youpin.youpin_common.api.IStoreCallback;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import com.xiaomi.youpin.youpin_common.statistic.params.TouchParams;
import com.xiaomi.youpin.youpin_constants.YPStoreConstant;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import com.youpin.weex.app.common.WXStoreApiProvider;
import com.youpin.weex.app.model.pojo.PreCachePage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONObject;

public class AbsWXStoreApiProviderImpl implements WXStoreApiProvider {
    private final String e = "WXStoreApiProviderImp";

    public Map a() {
        return null;
    }

    public void a(Throwable th) {
    }

    public ArrayList<PreCachePage> b() {
        return null;
    }

    public void a(String str, String str2, String str3) {
        StatManager.a().a(new TouchParams.Builder().a(str).b(str2).c(str3).a());
    }

    public void a(String str, String str2, String str3, int i) {
        StatManager.a().a(str, str2, str3, i);
    }

    public void c() {
        StatManager.a().h();
    }

    public void a(Activity activity, Map<String, Object> map, ICallback iCallback) {
        if (map != null) {
            String str = (String) map.get("url");
            LogUtils.d("WXStoreApiProviderImp", "share(): url:" + str);
            if (activity == null) {
                return;
            }
            if (d()) {
                StoreApiManager.a().b().b(activity, str);
                return;
            }
            StoreApiManager.a().a((String) map.get("channel"), activity, str, iCallback);
        }
    }

    private boolean d() {
        Map<String, Object> d = StoreApiManager.a().d();
        if (d != null) {
            return ((Boolean) d.get(YPStoreConstant.EXTERN_SHARE)).booleanValue();
        }
        return false;
    }

    public void a(ICallback iCallback) {
        Set<String> f = StoreApiManager.a().f();
        HashMap hashMap = new HashMap();
        hashMap.put("result", f);
        iCallback.callback(hashMap);
    }

    public void a(Activity activity, String str, String str2, ICallback iCallback) {
        StoreApiManager.a().b(str, activity, str2, iCallback);
    }

    public void b(ICallback iCallback) {
        Set<String> e2 = StoreApiManager.a().e();
        HashMap hashMap = new HashMap();
        hashMap.put("result", e2);
        iCallback.callback(hashMap);
    }

    public void a(Activity activity, String str, String str2, boolean z) {
        if (z && activity != null) {
            StoreApiManager.a().b().a(activity);
        }
    }

    public void a(Activity activity) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a(activity);
        }
    }

    public void c(final ICallback iCallback) {
        StoreApiProvider b = StoreApiManager.a().b();
        if (b != null) {
            b.a((IStoreCallback<Map<String, Object>>) new IStoreCallback<Map<String, Object>>() {
                /* renamed from: a */
                public void onSuccess(Map<String, Object> map) {
                    iCallback.callback(map);
                }

                public void onFailed(int i, String str) {
                    HashMap hashMap = new HashMap();
                    hashMap.put("error", Integer.valueOf(i));
                    hashMap.put("error_msg", str);
                    iCallback.callback(hashMap);
                }
            });
        }
    }

    public void a(String str, String str2, String str3, String str4, ICallback iCallback) {
        StatManager.a().a(str, str2, str3, str4, "W", (JSONObject) null);
        HashMap hashMap = new HashMap();
        hashMap.put("result", "ok");
        iCallback.callback(hashMap);
    }

    public void a(String str, String str2, String str3, String str4, Map<String, String> map, ICallback iCallback) {
        Map<String, String> map2 = map;
        if (map2 == null) {
            StatManager.a().a(str, str2, str3, str4, "W", (JSONObject) null);
        } else {
            StatManager.a().a(str, str2, str3, str4, "W", new JSONObject(map2));
        }
        HashMap hashMap = new HashMap();
        hashMap.put("result", "ok");
        iCallback.callback(hashMap);
    }

    public void a(boolean z, ICallback iCallback) {
        StatManager.a().a(z);
        HashMap hashMap = new HashMap();
        hashMap.put("result", "ok");
        iCallback.callback(hashMap);
    }

    public void a(String str) {
        UrlDispatchManger.a().a(WXAppStoreApiManager.b().f(), str, -1);
    }
}
