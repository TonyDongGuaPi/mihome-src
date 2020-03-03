package com.xiaomi.youpin;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Pair;
import com.taobao.weex.common.WXModule;
import com.xiaomi.plugin.XmPluginBaseFragment;
import com.xiaomi.plugin.XmPluginHostApi;
import com.xiaomi.plugin.XmPluginPackage;
import com.xiaomi.pluginhost.PluginRuntimeManager;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.framework.openapi.OpenApi;
import com.xiaomi.smarthome.shop.mishop.MiShopFragment;
import com.xiaomi.smarthome.shop.mishop.MiShopUrlUtil;
import com.xiaomi.youpin.app_sdk.url_dispatch.SDKDispatcher;
import com.xiaomi.youpin.youpin_common.SharedDataKey;
import com.xiaomi.youpin.youpin_common.statistic.StatManager;
import com.xiaomi.youpin.youpin_constants.UrlConstants;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MJUrlSDKDispatcher extends SDKDispatcher {
    private static String[] b = {"com.mijiashop.main", "com.xiaomi.catalog", "com.xiaomi.pinwei", "com.xiaomi.order", "com.xiaomi.cs", "com.xiaomi.youpin.red_envelope_rain"};

    /* renamed from: a  reason: collision with root package name */
    List<String> f23148a = new ArrayList();
    private final String[] c = {"main", UrlConstants.crowdfundinglist, UrlConstants.morelist, UrlConstants.flagshipstore, "preview", UrlConstants.tab_brand, UrlConstants.tab_recommend, UrlConstants.pinwei, UrlConstants.writeComment, UrlConstants.picture_pick, UrlConstants.yp_imagebrowser, UrlConstants.goodscategory, UrlConstants.goodsbycategory, UrlConstants.customerService, UrlConstants.serviceCenter, UrlConstants.csPushHeader, UrlConstants.red_envelope_rain, UrlConstants.red_envelope_rain_gaming, UrlConstants.red_envelope_rain_checkout, UrlConstants.red_envelope_rain_web_gaming};

    public String[] a() {
        return b;
    }

    public String[] b() {
        return this.c;
    }

    public List<String> c() {
        return this.f23148a;
    }

    public void a(List<String> list) {
        this.f23148a.clear();
        if (list != null && list.size() != 0) {
            for (String add : list) {
                this.f23148a.add(add);
            }
        }
    }

    public boolean a(String str) {
        if (!(XmPluginHostApi.instance().getPageModel() == 1 || this.f23148a == null)) {
            for (int i = 0; i < this.f23148a.size(); i++) {
                if (str.equals(this.f23148a.get(i))) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean b(String str) {
        for (String equals : this.c) {
            if (equals.equals(str)) {
                return true;
            }
        }
        return false;
    }

    public Fragment c(String str) {
        if (str.contains(UrlConstants.mishopsdk)) {
            return MiShopFragment.a(MiShopUrlUtil.a(UrlConstants.mishopsdk_fragment_url, str));
        }
        return null;
    }

    public int d() {
        return XmPluginHostApi.instance().getPageModel();
    }

    public boolean e() {
        return XmPluginHostApi.instance().hasSwitch(SharedDataKey.b);
    }

    public Fragment a(Context context, String str, String str2) {
        XmPluginPackage a2 = PluginRuntimeManager.a().a(str2);
        if (a2 == null) {
            return null;
        }
        PluginRuntimeManager.a(a2);
        Intent intent = new Intent();
        intent.putExtra("url", str);
        XmPluginBaseFragment newFragment = a2.getMessageReceiver().newFragment(context, a2, intent);
        if ("$Home$".equals(newFragment.getPageName()) || "$GoodsCategory$".equals(newFragment.getPageName())) {
            return newFragment;
        }
        newFragment.enableNeedStatic();
        return newFragment;
    }

    public boolean a(Activity activity, String str, String str2, int i) {
        XmPluginPackage a2 = PluginRuntimeManager.a().a(str);
        if (a2 == null) {
            return false;
        }
        Intent intent = new Intent();
        intent.putExtra("url", str2);
        intent.putExtra(WXModule.REQUEST_CODE, i);
        Context context = activity;
        if (activity == null) {
            context = SHApplication.getAppContext();
        }
        boolean handleMessage = a2.getMessageReceiver().handleMessage(context, a2, 1, intent);
        if (handleMessage) {
            return handleMessage;
        }
        return false;
    }

    public void d(String str) {
        String parseShortPath = UrlConstants.parseShortPath(str);
        if (TextUtils.equals(parseShortPath, "main")) {
            OpenApi.a(4);
        } else if (TextUtils.equals(parseShortPath, UrlConstants.pinwei)) {
            OpenApi.a(10);
        }
    }

    public void a(int i) {
        d(UrlConstants.generateUrlParams("main", new String[]{"index"}, new Object[]{Integer.valueOf(i)}));
    }

    public boolean a(Activity activity, String str, int i) {
        Pair<String, HashMap<String, String>> parseUrlAndParams;
        if (!TextUtils.isEmpty(str) && (parseUrlAndParams = UrlConstants.parseUrlAndParams(str)) != null && TextUtils.equals((CharSequence) parseUrlAndParams.first, UrlConstants.scanbar) && ((HashMap) parseUrlAndParams.second).containsKey("result")) {
            String str2 = (String) ((HashMap) parseUrlAndParams.second).get("result");
            if (!TextUtils.isEmpty(str2) && str2.startsWith("http")) {
                Pair<String, HashMap<String, String>> parseUrlAndParams2 = UrlConstants.parseUrlAndParams(str2);
                if (TextUtils.equals((CharSequence) parseUrlAndParams2.first, "preview")) {
                    a(activity, (String) parseUrlAndParams2.first, str2, i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean e(String str) {
        String a2 = MiShopUrlUtil.a(str);
        if (str.equals(a2)) {
            return false;
        }
        StatManager.a().c(str);
        MiShopUrlUtil.b(a2);
        return true;
    }

    public void f(String str) {
        StatManager.a().c(str);
        MiShopUrlUtil.b(MiShopUrlUtil.a(UrlConstants.mishopsdk_activity_url, str));
    }

    public boolean g(String str) {
        if ((str.contains("profile") || (XmPluginHostApi.instance().getPageModel() != 2 && !a(str))) && PluginRuntimeManager.a().a(str) != null) {
            return true;
        }
        return false;
    }
}
