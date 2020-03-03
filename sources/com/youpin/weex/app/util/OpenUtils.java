package com.youpin.weex.app.util;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.server.internal.api.MiShopApi;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.youpin_common.StoreApiManager;
import com.xiaomi.youpin.youpin_common.StoreApiProvider;
import com.youpin.weex.app.model.pojo.UpdateActBean;
import com.youpin.weex.app.util.HttpManager;
import org.json.JSONException;
import org.json.JSONObject;

public class OpenUtils {

    /* renamed from: a  reason: collision with root package name */
    public static final int f2548a = 0;
    public static final int b = 1;
    public static final String c = "_wx_tpl";
    public static final String d = "_wx_htm";
    public static final String e = "2.0.2";
    public static final String f = "0.20.1";
    public static final String g = "1";
    private static final String h = "_rt";
    private static final String i = "action";
    private static final String j = "Check";
    private static final String k = "model";
    private static final String l = "PackageManage";
    private static final String m = "parameters";
    private static final String n = "updateact";
    private static final String o = "/api/client/package/weex/check";
    private static final String p = "app_version";
    private static final String q = "application";
    private static final String r = "bundle_type";
    private static final int s = 1;
    private static final String t = "dev_mode";
    private static final String u = "name";
    private static final String v = "platform";
    private static final int w = 2;
    private static final String x = "weex_sdk_version";

    public interface UpdateActCallback {
        void a(String str, UpdateActBean.DataBean dataBean);
    }

    private OpenUtils() {
    }

    private static String b() {
        return StoreApiManager.a().b().g() ? "https://st.shopapi.io.mi.com" : MiShopApi.b;
    }

    public static String a() {
        return StoreApiManager.a().b().f() ? "http://st.log.youpin.mi.com" : "https://log.youpin.mi.com";
    }

    private static int c() {
        return StoreApiManager.a().b().g() ? 2 : 1;
    }

    public static int a(Uri uri) {
        if (uri.getQueryParameterNames().contains("_wx_tpl") || uri.getQueryParameterNames().contains(d)) {
            return 0;
        }
        return uri.getQueryParameterNames().contains(h) ? 1 : -1;
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(Uri uri, final UpdateActCallback updateActCallback) {
        int i2;
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            updateActCallback.a((String) null, (UpdateActBean.DataBean) null);
            return;
        }
        String substring = path.substring(path.lastIndexOf("/") + 1);
        String str = b() + o;
        StoreApiProvider b2 = StoreApiManager.a().b();
        String d2 = b2.d();
        if ("SmartHome".equals(d2)) {
            i2 = 2;
        } else if ("yptuishou".equals(d2)) {
            i2 = 3;
        } else if ("MiRouter".equals(d2)) {
            i2 = 4;
        } else {
            i2 = "Mico".equals(d2) ? 5 : 1;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("app_version", AppInfo.f());
            jSONObject.put("application", i2);
            jSONObject.put(r, 1);
            jSONObject.put(t, c());
            jSONObject.put("name", substring);
            jSONObject.put("platform", 2);
            jSONObject.put(x, e);
            if (b2.g()) {
                String str2 = (String) b2.b("Dev_WeexBranch", (Object) "");
                if (!TextUtils.isEmpty(str2)) {
                    try {
                        jSONObject.put("branch", new JSONObject(str2));
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        } catch (JSONException e3) {
            e3.printStackTrace();
            updateActCallback.a((String) null, (UpdateActBean.DataBean) null);
        }
        HttpManager.a().a(str, jSONObject.toString(), UpdateActBean.class, new HttpManager.HttpCallback() {
            public void a(String str, Object obj) {
                String str2;
                UpdateActBean updateActBean = (UpdateActBean) obj;
                if (updateActBean == null) {
                    updateActCallback.a((String) null, (UpdateActBean.DataBean) null);
                    return;
                }
                try {
                    str2 = new JSONObject(str).getJSONObject("result").getJSONObject(OpenUtils.n).getJSONObject("data").toString();
                } catch (JSONException e) {
                    e.printStackTrace();
                    str2 = null;
                }
                int code = updateActBean.getCode();
                UpdateActBean.DataBean data = updateActBean.getData();
                if (code != 0 || data == null) {
                    updateActCallback.a(str2, (UpdateActBean.DataBean) null);
                } else {
                    updateActCallback.a(str2, data);
                }
            }

            public void a(String str) {
                updateActCallback.a((String) null, (UpdateActBean.DataBean) null);
            }
        });
    }
}
