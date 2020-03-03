package com.xiaomi.youpin.youpin_common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.text.TextUtils;
import com.xiaomi.miot.store.api.ICallback;
import com.xiaomi.miot.store.api.IPayProvider;
import com.xiaomi.miot.store.api.IShareProvider;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.cookie.CookieConfig;
import com.xiaomi.youpin.cookie.CookieConfigManager;
import com.xiaomi.youpin.youpin_network.NetWorkDependency;
import com.xiaomi.yp_pic_pick.PicturePickActivity;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class StoreApiManager {

    /* renamed from: a  reason: collision with root package name */
    static final String f23790a = "StoreApiManager";
    Map<String, IPayProvider> b = new HashMap();
    IPayProvider c;
    Map<String, IShareProvider> d = new HashMap();
    IShareProvider e;
    PicPickProvider f;
    Map<String, Object> g = new HashMap();
    StoreApiProvider h;

    private static class Holder {
        /* access modifiers changed from: private */

        /* renamed from: a  reason: collision with root package name */
        public static StoreApiManager f23791a = new StoreApiManager();

        private Holder() {
        }
    }

    public static StoreApiManager a() {
        return Holder.f23791a;
    }

    public void a(StoreApiProvider storeApiProvider) {
        this.h = storeApiProvider;
        this.h.a(this);
        SDKInitUtil.a(AppInfo.a(), storeApiProvider);
        UserAgent.a(storeApiProvider.d(), storeApiProvider.c());
        CookieConfigManager.a().a(new CookieConfig.Builder(AppInfo.a()).a());
        if (!"YouPin".equals(storeApiProvider.d())) {
            new Handler().postDelayed(new Runnable() {
                public final void run() {
                    StoreApiManager.a(NetWorkDependency.this);
                }
            }, 5000);
        }
    }

    /* access modifiers changed from: private */
    public static /* synthetic */ void a(NetWorkDependency netWorkDependency) {
        if (netWorkDependency.d()) {
            netWorkDependency.a((NetWorkDependency.ServiceTokenCallback) null);
        }
    }

    public StoreApiProvider b() {
        return this.h;
    }

    public Context c() {
        return AppInfo.a();
    }

    public void a(String str, Object obj) {
        this.g.put(str, obj);
    }

    public Map<String, Object> d() {
        HashMap hashMap = new HashMap(this.g);
        Map<String, Object> e2 = this.h.e();
        if (e2 != null) {
            hashMap.putAll(e2);
        }
        return hashMap;
    }

    public void a(IPayProvider iPayProvider) {
        if (iPayProvider != null) {
            this.b.put(iPayProvider.name(), iPayProvider);
        }
    }

    public IPayProvider a(String str) {
        return this.b.get(str);
    }

    public Set<String> e() {
        return this.b.keySet();
    }

    public void a(IShareProvider iShareProvider) {
        if (iShareProvider != null) {
            this.d.put(iShareProvider.name(), iShareProvider);
        }
    }

    public Set<String> f() {
        return this.d.keySet();
    }

    public void a(String str, Activity activity, String str2, ICallback iCallback) {
        this.e = null;
        this.c = null;
        this.e = this.d.get(str);
        if (this.e != null) {
            this.e.share(activity, str2, iCallback);
            return;
        }
        this.e = null;
        iCallback.callback((Map) null);
    }

    public void b(String str, Activity activity, String str2, ICallback iCallback) {
        this.e = null;
        this.c = null;
        this.c = this.b.get(str);
        if (this.c != null) {
            this.c.pay(activity, str2, iCallback);
            return;
        }
        this.c = null;
        iCallback.callback((Map) null);
    }

    public void a(Activity activity, HashMap<String, Object> hashMap, ICallback iCallback) {
        this.f = null;
        this.f = new PicPickProvider();
        this.f.a(activity, hashMap, iCallback);
    }

    public void g() {
        if (this.c != null) {
            this.c.clear();
            this.c = null;
        }
        this.e = null;
    }

    public void a(int i, int i2, Intent intent) {
        if (this.f != null) {
            this.f.a(i, i2, intent);
            this.f = null;
        }
        if (this.c != null) {
            this.c.onActivityResult(i, i2, intent);
            this.c = null;
        }
        if (this.e != null) {
            this.e.onActivityResult(i, i2, intent);
            this.e = null;
        }
    }

    private class PicPickProvider {
        private static final int c = 110;
        private ICallback b;

        private PicPickProvider() {
        }

        public void a(int i, int i2, Intent intent) {
            if (i == 110) {
                HashMap hashMap = new HashMap();
                if (i2 != -1 || intent == null) {
                    hashMap.put("code", "-1");
                    this.b.callback(hashMap);
                    return;
                }
                String[] stringArrayExtra = intent.getStringArrayExtra("images");
                if (stringArrayExtra != null) {
                    hashMap.put("code", "0");
                    hashMap.put("images", stringArrayExtra);
                    this.b.callback(hashMap);
                    return;
                }
                hashMap.put("code", "-9");
                this.b.callback(hashMap);
            }
        }

        public void a(Activity activity, HashMap<String, Object> hashMap, ICallback iCallback) {
            this.b = iCallback;
            if (activity != null && hashMap != null) {
                StringBuilder sb = new StringBuilder("?");
                String str = (String) hashMap.get("type");
                if (TextUtils.isEmpty(str)) {
                    sb.append("type=default");
                } else if (str.equalsIgnoreCase("onlycamera")) {
                    sb.append("type=onlycamera");
                } else if (str.equalsIgnoreCase("hidecamera")) {
                    sb.append("type=hidecamera");
                }
                String str2 = (String) hashMap.get("ret");
                if (TextUtils.isEmpty(str2) || str2.equalsIgnoreCase("local")) {
                    sb.append("&ret=local");
                }
                String str3 = (String) hashMap.get("selectmax");
                if (TextUtils.isEmpty(str3)) {
                    sb.append("&maxSelectedImages=50");
                } else {
                    sb.append("&maxSelectedImages=");
                    sb.append(str3);
                }
                String str4 = (String) hashMap.get("zip_fmt");
                if (!TextUtils.isEmpty(str4)) {
                    sb.append("&zip_fmt=");
                    sb.append(str4);
                } else {
                    sb.append("&zip_fmt=jpg");
                }
                String str5 = (String) hashMap.get("zip_size");
                if (!TextUtils.isEmpty(str5)) {
                    sb.append("&zip_size=");
                    sb.append(str5);
                }
                String str6 = (String) hashMap.get("zip_width");
                if (!TextUtils.isEmpty(str6)) {
                    sb.append("&zip_width=");
                    sb.append(str6);
                } else {
                    sb.append("&zip_width=1080");
                }
                sb.append("&rn=true");
                Intent intent = new Intent(activity, PicturePickActivity.class);
                intent.putExtra("url", sb.toString());
                activity.startActivityForResult(intent, 110);
            }
        }
    }
}
