package com.xiaomi.youpin.app_sdk.url_dispatch;

import android.app.Activity;
import android.app.Application;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.exoplayer2.C;
import com.mi.global.bbs.http.ParamKey;
import com.xiaomi.loan.sdk.MiFiSdk;
import com.xiaomi.miot.store.api.MiotStoreApi;
import com.xiaomi.qrcode2.QrCodeCallback;
import com.xiaomi.qrcode2.QrCodeRouter;
import com.xiaomi.secondfloor.SecondFloorActivity;
import com.xiaomi.youpin.common.util.AppInfo;
import com.xiaomi.youpin.common.util.UrlUtils;
import com.xiaomi.youpin.youpin_common.lifecycle.AppLifecycleManager;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import com.xiaomi.yp_pic_pick.PicturePickActivity;
import com.youpin.weex.app.common.WXAppStoreApiManager;
import java.util.HashMap;
import java.util.List;

public class UrlDispatchManger {

    /* renamed from: a  reason: collision with root package name */
    private static String[] f23211a = {"youpin.mi.com", "m.youpin.mi.com", "app.youpin.mi.com", "www.xiaomiyoupin.com", "m.xiaomiyoupin.com", "app.xiaomiyoupin.com"};
    private static String[] b = {"mi.com", "xiaomiyoupin.com", "mijiayoupin.com"};
    private SDKDispatcher c;
    private final String[] d;

    private UrlDispatchManger() {
        this.c = new SDKDispatcher();
        this.d = new String[]{UrlConstants.cart, "detail", UrlConstants.red_envelope_rain};
    }

    private static class Holder {

        /* renamed from: a  reason: collision with root package name */
        static UrlDispatchManger f23213a = new UrlDispatchManger();

        private Holder() {
        }
    }

    public static UrlDispatchManger a() {
        return Holder.f23213a;
    }

    public void a(SDKDispatcher sDKDispatcher) {
        this.c = sDKDispatcher;
    }

    private boolean c(String str, boolean z) {
        for (String equals : this.d) {
            if (str.equals(equals)) {
                return false;
            }
        }
        return z;
    }

    private String a(String str, String str2) {
        if (!str2.equals(UrlConstants.cart)) {
            return str;
        }
        if (str.contains("?")) {
            return str + "&isSingleTask=true";
        }
        return str + "?isSingleTask=true";
    }

    public void a(List<String> list) {
        this.c.a(list);
    }

    public String[] b() {
        return this.c.a();
    }

    public String[] c() {
        return this.c.b();
    }

    public int d() {
        return this.c.d();
    }

    public boolean e() {
        return this.c.e();
    }

    public Fragment a(String str, boolean z) {
        return this.c.a(str, z);
    }

    public Fragment b(String str, boolean z) {
        return this.c.b(str, z);
    }

    public Fragment a(Context context, String str, String str2) {
        return this.c.a(context, str, str2);
    }

    public boolean a(Activity activity, String str, String str2, int i) {
        return this.c.a(activity, str, str2, i);
    }

    public void a(int i) {
        this.c.a(i);
    }

    public void a(String str) {
        this.c.d(str);
    }

    private boolean b(Activity activity, String str, int i) {
        return this.c.a(activity, str, i);
    }

    public boolean b(String str) {
        return this.c.g(UrlUtils.a(str));
    }

    public Fragment a(Context context, String str, boolean z) {
        String a2 = UrlUtils.a(str);
        UrlUtils.b(str);
        String generateUrl = UrlConstants.generateUrl(str);
        Fragment c2 = this.c.c(generateUrl);
        if (c2 != null) {
            return c2;
        }
        if (!e() && (d() == 3 || generateUrl.contains("_rt=weex") || a2.contains("weexpage"))) {
            return a(generateUrl, z);
        }
        if (d() == 2 || generateUrl.contains("_rt=rn") || this.c.a(a2)) {
            return b(generateUrl, z);
        }
        Fragment a3 = a(context, generateUrl, a2);
        if (a3 == null) {
            return b(generateUrl, z);
        }
        return a3;
    }

    public boolean a(Activity activity, String str, int i) {
        return a(activity, str, false, i);
    }

    public void c(String str) {
        a((Activity) null, str, -1);
    }

    public boolean a(Activity activity, String str, boolean z, int i) {
        if (str != null) {
            str = str.trim();
        }
        if (e(str) || f(str) || g(str)) {
            return true;
        }
        String a2 = UrlUtils.a(str);
        String generateUrl = UrlConstants.generateUrl(str);
        boolean c2 = c(a2, z);
        if (TextUtils.isEmpty(generateUrl) || "main".equals(a2)) {
            a(generateUrl);
            return true;
        } else if (!TextUtils.isEmpty(a2) && UrlConstants.scanbar.endsWith(a2) && b(activity, generateUrl, i)) {
            return true;
        } else {
            if (!TextUtils.isEmpty(a2) && UrlConstants.scan.endsWith(a2) && a(activity)) {
                return true;
            }
            if (!TextUtils.isEmpty(generateUrl) && generateUrl.startsWith("https://api.jr.mi.com/")) {
                if (activity == null) {
                    activity = AppLifecycleManager.getInstance().getCurrentActivity();
                }
                if (activity == null) {
                    return false;
                }
                MiFiSdk.a(activity, generateUrl);
                return true;
            } else if ("testActivity".equals(a2)) {
                this.c.f();
                return true;
            } else if ("developer".equals(a2)) {
                this.c.g();
                return true;
            } else if (ParamKey.interest.equals(a2)) {
                this.c.h();
                return true;
            } else if ("detail".equals(a2) && this.c.e(generateUrl)) {
                return true;
            } else {
                if (generateUrl.contains(UrlConstants.mishopsdk)) {
                    this.c.f(generateUrl);
                    return true;
                } else if (generateUrl.contains(UrlConstants.picture_pick)) {
                    c(activity, generateUrl, i);
                    return true;
                } else if (generateUrl.contains(UrlConstants.second_floor)) {
                    d(activity, generateUrl, i);
                    return true;
                } else if (generateUrl.contains("_rt=native") && a(activity, a2, generateUrl, i)) {
                    return true;
                } else {
                    if (generateUrl.contains("_rt=rn")) {
                        MiotStoreApi.a().openPage(activity, generateUrl, i);
                        return true;
                    } else if (!e() && (generateUrl.contains("_rt=weex") || a2.contains("weexpage"))) {
                        WXAppStoreApiManager.b().a(activity, generateUrl, i);
                        return true;
                    } else if (d() == 2) {
                        if (c2) {
                            return false;
                        }
                        MiotStoreApi.a().openPage(activity, generateUrl, i);
                        return true;
                    } else if (d() == 3) {
                        if (c2) {
                            return false;
                        }
                        WXAppStoreApiManager.b().a(activity, generateUrl, i);
                        return true;
                    } else if ("testNative".equals(a2)) {
                        a(activity, a2, generateUrl, i);
                        return true;
                    } else if (this.c.a(a2)) {
                        if (c2) {
                            return false;
                        }
                        String a3 = a(generateUrl, a2);
                        if (MiotStoreApi.a() == null) {
                            return false;
                        }
                        MiotStoreApi.a().openPage(activity, a3, i);
                        return true;
                    } else if (this.c.b(a2) && a(activity, a2, generateUrl, i)) {
                        return true;
                    } else {
                        if (!i(generateUrl) || c2) {
                            return false;
                        }
                        String generateUrl2 = UrlConstants.generateUrl(generateUrl);
                        if (MiotStoreApi.a() == null) {
                            return false;
                        }
                        MiotStoreApi.a().openPage(activity, generateUrl2, i);
                        return true;
                    }
                }
            }
        }
    }

    private boolean e(String str) {
        if (str == null || !str.startsWith("tel:")) {
            return false;
        }
        h(str);
        return true;
    }

    private boolean f(String str) {
        if (str == null || !str.startsWith("mailto:")) {
            return false;
        }
        h(str);
        return true;
    }

    private boolean g(String str) {
        if (str == null || !str.startsWith("geo:")) {
            return false;
        }
        h(str);
        return true;
    }

    private void h(String str) {
        Uri parse = Uri.parse(str);
        Application a2 = AppInfo.a();
        Intent intent = new Intent("android.intent.action.VIEW", parse);
        intent.putExtra("com.android.browser.application_id", a2.getPackageName());
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        try {
            a2.startActivity(intent);
        } catch (ActivityNotFoundException unused) {
            Log.w("URLSpan", "Actvity was not found for intent, " + intent.toString());
        }
    }

    private void c(Activity activity, String str, int i) {
        if (activity != null) {
            Intent intent = new Intent(activity, PicturePickActivity.class);
            intent.putExtra("url", str);
            activity.startActivityForResult(intent, i);
        }
    }

    private void d(Activity activity, String str, int i) {
        Class<SecondFloorActivity> cls = SecondFloorActivity.class;
        if (activity != null) {
            Intent intent = new Intent(activity, cls);
            intent.putExtra("url", str);
            activity.startActivityForResult(intent, i);
            return;
        }
        Application a2 = AppInfo.a();
        Intent intent2 = new Intent(a2, cls);
        intent2.putExtra("url", str);
        intent2.setFlags(C.ENCODING_PCM_MU_LAW);
        a2.startActivity(intent2);
    }

    private boolean a(Activity activity) {
        if (activity == null) {
            return false;
        }
        QrCodeRouter.a(activity, "扫一扫", new QrCodeCallback() {
            public void onFail(int i, String str) {
            }

            public void onSuccess(String str) {
                HashMap hashMap = new HashMap();
                hashMap.put("result", str);
                UrlDispatchManger.this.c(UrlConstants.generateUrlParams(UrlConstants.scanbar, hashMap));
            }
        });
        return true;
    }

    public boolean d(String str) {
        try {
            String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host)) {
                String lowerCase = host.toLowerCase();
                for (String contains : f23211a) {
                    if (lowerCase.contains(contains)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean i(String str) {
        try {
            String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host)) {
                String lowerCase = host.toLowerCase();
                for (String contains : b) {
                    if (lowerCase.contains(contains)) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
