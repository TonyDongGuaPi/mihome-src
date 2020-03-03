package com.mi.global.shop;

import android.app.Application;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.RequestQueue;
import com.facebook.CallbackManager;
import com.facebook.FacebookSdk;
import com.mi.MiApplicationContext;
import com.mi.account.LoginManager;
import com.mi.global.bbs.BuildConfig;
import com.mi.global.shop.base.ApplicationAgent;
import com.mi.global.shop.db.Setting;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.ui.HomeFragmentSingleton;
import com.mi.global.shop.util.ChannelUtil;
import com.mi.global.shop.util.ConnectionHelper;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.MiShopStatInterface;
import com.mi.global.shop.util.SplashUtil;
import com.mi.global.shop.util.UIAdapter;
import com.mi.global.shop.util.Utils;
import com.mi.global.shop.util.fresco.FrescoUtils;
import com.mi.global.shop.webview.WebViewCookieManager;
import com.mi.global.shop.xmsf.account.LoginManager;
import com.mi.global.shop.xmsf.account.ShopSdkCleanListener;
import com.mi.global.shop.xmsf.account.ShopSdkInitParamGroup;
import com.mi.global.shop.xmsf.account.ShopSdkTokenExpiredListener;
import com.mi.log.LogUtil;
import com.mi.mistatistic.sdk.BuildSetting;
import com.mi.mistatistic.sdk.controller.MiStatOptions;
import com.mi.util.Constants;
import com.mi.util.Device;
import com.mi.util.RequestQueueUtil;
import com.mi.util.ScreenInfo;
import com.mi.util.permission.PermissionUtil;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.shopviews.WidgetApplication;
import java.io.File;
import java.util.ArrayList;

public class ShopApp {

    /* renamed from: a  reason: collision with root package name */
    public static boolean f1346a = Constants.f1348a;
    public static boolean b = false;
    public static boolean c = false;
    public static boolean d = false;
    public static boolean e = false;
    public static ShopSdkTokenExpiredListener f = null;
    public static ShopSdkCleanListener g = null;
    public static ShopSdkInitParamGroup h = null;
    public static boolean i = false;
    public static boolean j = true;
    public static boolean k = true;
    public static boolean l = false;
    public static final String m = "30502";
    public static boolean n = false;
    private static final String o = "ShopApp";
    private static boolean p = true;
    private static boolean q = false;
    private static boolean r = false;
    private static ShopApp s = null;
    private static final String u = "2882303761517345102";
    private static final String v = "5181734554102";
    private static CallbackManager w;
    private final Application t;
    private MiStatOptions x;

    public static void o() {
    }

    public ShopApp(Application application) {
        s = this;
        this.t = application;
    }

    public Application a() {
        return this.t;
    }

    public static void a(Application application, @NonNull String str, @NonNull String str2) {
        LogUtil.a(f1346a);
        b(application, str, str2);
        MiApplicationContext.f1260a = application;
        Device.a(application, PermissionUtil.a((Context) application, "android.permission.READ_PHONE_STATE"));
        ScreenInfo.a().a(application);
        RequestQueueUtil.a(application);
        ShopApp shopApp = new ShopApp(application);
        LogUtil.b(o, "ShopApp 初始化");
        WidgetApplication.a(application);
        ApplicationAgent.a(application);
        shopApp.a(str, str2);
    }

    private void a(String str, String str2) {
        LocaleHelper.a(str);
        ChannelUtil.a();
        LoginManager.b(a());
        boolean z = f1346a;
        ARouter.a(a());
        Setting.b();
        UIAdapter.a().b();
        FrescoUtils.a((Context) a(), 100, str2);
        if (str2.equals("mihome_sdk")) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(BuildConfig.APPLICATION_ID);
            arrayList.add("com.google.android.youtube.player");
            arrayList.add(BuildConfig.b);
            arrayList.add(com.paytm.pgsdk.BuildConfig.b);
            arrayList.add(com.payu.custombrowser.BuildConfig.APPLICATION_ID);
            arrayList.add(com.payu.magicretry.BuildConfig.APPLICATION_ID);
            arrayList.add(com.mobikwik.sdk.BuildConfig.APPLICATION_ID);
            arrayList.add(com.mi.global.shop.base.BuildConfig.b);
            arrayList.add(com.mi.global.shop.router.BuildConfig.b);
            arrayList.add(com.mi.global.shop.feature.search.BuildConfig.APPLICATION_ID);
            this.x = new MiStatOptions.Builder().a(f()).b(Device.B).a(b).d(LoginManager.u().c()).g(false).d(true).a((ArrayList<String>) arrayList).a();
            if (LocaleHelper.g()) {
                this.x.setServerIndia(true);
            }
            if (LocaleHelper.n()) {
                this.x.setServerRussia(true);
            }
            if (n) {
                MiShopStatInterface.a(a(), this.x);
                LogUtil.b(o, str + "，ShopApp.init()，用户同意了隐私协议值，init() 直接初始化打点统计库");
            } else {
                LogUtil.b(o, str + "，ShopApp.init()，用户没有同意了隐私协议值，init() 不初始化打点统计库");
            }
        }
        MiShopStatInterface.a("shop_");
        com.mi.global.shop.base.MiShopStatInterface.a("shop_");
        q();
        q = Utils.Preference.getBooleanPref(a(), "pref_key_use_new_model", true);
        w = CallbackManager.Factory.create();
        r = Utils.Preference.getBooleanPref(a(), "pref_key_using_go_mifile_host_swtich", false);
        c = Utils.Preference.getBooleanPref(a(), "pref_key_https_request", true);
        d = Utils.Preference.getBooleanPref(a(), "pref_key_https_image_request", true);
        e = Utils.Preference.getBooleanPref(a(), "pref_key_https_webview_url_request", true);
        if (LocaleHelper.j()) {
            ConnectionHelper.E = ConnectionHelper.F;
        }
    }

    private static void b(Application application, String str, @NonNull String str2) {
        n = Utils.Preference.getBooleanPref(application, Constants.Prefence.l + str, false);
        if (!n || !str2.equals("mihome_sdk")) {
            LogUtil.b(o, str + "，ShopApp.init() 启动, 当前米家渠道隐私协议同意的值 == " + n + ", 不进行 FaceBookSdk 初始化");
        } else {
            FacebookSdk.sdkInitialize(application);
            LogUtil.b(o, str + "，ShopApp.init() 启动, 当前米家渠道隐私协议同意的值 == " + n + ", 进行 FaceBookSdk 初始化");
        }
        if (!str2.equals("mihome_sdk")) {
            FacebookSdk.sdkInitialize(application);
            LogUtil.b(o, str + "，ShopApp.init() 启动, 当前非米家渠道隐私协议同意的值 == " + n + ", 进行 FaceBookSdk 初始化");
        }
    }

    public static void a(boolean z, String str, ExtendedAuthToken extendedAuthToken) {
        LogUtil.b(o, "商城 SDK，登录完成.");
        LoginManager.u().a(z, str, extendedAuthToken);
        k = true;
    }

    public static ArrayList<DomainModel> b() {
        if (LoginManager.u() != null) {
            return LoginManager.u().w();
        }
        return null;
    }

    public static void a(ShopSdkCleanListener shopSdkCleanListener) {
        g = shopSdkCleanListener;
    }

    public static int c() {
        return LoginManager.u().v();
    }

    public static void d() {
        LogUtil.b(o, "商城 SDK，需要登录");
        if (k) {
            k = false;
            l = true;
            f.a(Constants.Account.e().c());
        }
    }

    public static void e() {
        f.a(Constants.Account.e().c());
    }

    public static void a(String str, String str2, ShopSdkCleanListener shopSdkCleanListener) {
        Utils.Preference.setStringPref(g(), "pref_key_home_new_list", "");
        LogUtil.b(o, "已清除首页缓存");
        LogUtil.b(o, "商城 SDK，开始切花国家。");
        if (str.equals(str2) || TextUtils.isEmpty(str2)) {
            new CleanAsyncTask().execute(new Object[]{"0", shopSdkCleanListener});
        } else if (!str2.equals(LocaleHelper.d)) {
            for (int i2 = 0; i2 < LocaleHelper.o.length; i2++) {
                if (LocaleHelper.o[i2][0].equals(str2)) {
                    if (TextUtils.isEmpty(LocaleHelper.b) || TextUtils.isEmpty(LocaleHelper.c) || LocaleHelper.b.equals(LocaleHelper.o[i2][0])) {
                        shopSdkCleanListener.a();
                    } else {
                        new CleanAsyncTask().execute(new Object[]{String.valueOf(i2), shopSdkCleanListener});
                        return;
                    }
                }
            }
        } else if (!str2.equals(LocaleHelper.d)) {
        } else {
            if (str.equals(LocaleHelper.e)) {
                new CleanAsyncTask().execute(new Object[]{"0", shopSdkCleanListener});
            } else if (str.equals(LocaleHelper.l)) {
                new CleanAsyncTask().execute(new Object[]{"6", shopSdkCleanListener});
            } else {
                new CleanAsyncTask().execute(new Object[]{"0", shopSdkCleanListener});
            }
        }
    }

    private void q() {
        File file = new File(Environment.getExternalStorageDirectory() + "/mishop/");
        if (!file.exists()) {
            file.mkdir();
        }
    }

    public String f() {
        if (i() || j()) {
            return a().getString(R.string.statistic_debug_id);
        }
        if (LocaleHelper.g()) {
            return a().getString(R.string.statistic_in_id);
        }
        if (LocaleHelper.h()) {
            return a().getString(R.string.statistic_tw_id);
        }
        if (LocaleHelper.i()) {
            return a().getString(R.string.statistic_hk_id);
        }
        if (LocaleHelper.k()) {
            return a().getString(R.string.statistic_es_id);
        }
        if (LocaleHelper.l()) {
            return a().getString(R.string.statistic_fr_id);
        }
        if (LocaleHelper.j()) {
            return a().getString(R.string.statistic_id_id);
        }
        if (LocaleHelper.m()) {
            return a().getString(R.string.statistic_it_id);
        }
        if (LocaleHelper.n()) {
            return a().getString(R.string.statistic_ru_id);
        }
        if (LocaleHelper.o()) {
            return a().getString(R.string.statistic_uk_id);
        }
        return a().getString(R.string.statistic_in_id);
    }

    public static Application g() {
        return s.a();
    }

    public static ShopApp h() {
        return s;
    }

    public static boolean i() {
        return f1346a;
    }

    public static boolean j() {
        return b;
    }

    public static boolean k() {
        return q;
    }

    public static boolean l() {
        return r;
    }

    public static CallbackManager m() {
        return w;
    }

    public static boolean n() {
        if (LocaleHelper.g()) {
            return p;
        }
        return false;
    }

    public RequestQueue p() {
        return RequestQueueUtil.a();
    }

    static class CleanAsyncTask extends AsyncTask<Object, Integer, ShopSdkCleanListener> {
        CleanAsyncTask() {
        }

        /* access modifiers changed from: protected */
        public void onPreExecute() {
            super.onPreExecute();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public ShopSdkCleanListener doInBackground(Object... objArr) {
            String str;
            StringBuilder sb;
            String str2;
            LocaleHelper.b = LocaleHelper.o[Integer.parseInt(objArr[0])][0];
            LocaleHelper.c = LocaleHelper.o[Integer.parseInt(objArr[0])][1];
            if (!(ShopApp.h() == null || ShopApp.g() == null)) {
                try {
                    LoginManager.u().logout((LoginManager.LogoutCallback) null);
                    SplashUtil.a();
                    Utils.Preference.setStringPref(ShopApp.g(), "pref_locale", LocaleHelper.b);
                    Utils.Preference.setStringPref(ShopApp.g(), "pref_lang", LocaleHelper.c);
                    Utils.Preference.removePref(ShopApp.g(), "pref_key_user_center_list");
                    CookieSyncManager.createInstance(ShopApp.g());
                    CookieManager.getInstance().removeAllCookie();
                    CookieSyncManager.getInstance().sync();
                    WebViewCookieManager.b((Context) ShopApp.g());
                    WebViewCookieManager.c(ShopApp.g());
                } catch (Exception e) {
                    LogUtil.a(ShopApp.o, e.getMessage());
                }
            }
            ShopApp.k = true;
            ShopApp.l = false;
            ConnectionHelper.J = LocaleHelper.b;
            ConnectionHelper.f7052a = "http://mobile.mi.com/";
            ConnectionHelper.e = "http://mobile.test.mi.com/";
            ConnectionHelper.k = "http://go.buy.mi.com/";
            ConnectionHelper.l = "http://go-buy.test.mi.com/";
            ConnectionHelper.u = "";
            ConnectionHelper.v = "";
            ConnectionHelper.w = "";
            ConnectionHelper.y = "";
            ConnectionHelper.z = "";
            ConnectionHelper.A = "";
            ConnectionHelper.B = "";
            ConnectionHelper.D = "http://sg.appeye.appmifile.com";
            ConnectionHelper.E = "mi.com";
            ConnectionHelper.G = ConnectionHelper.E + "/" + LocaleHelper.b;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("/");
            sb2.append(LocaleHelper.b);
            ConnectionHelper.H = sb2.toString();
            ConnectionHelper.bT = "http://push.buy.mi.com/";
            ConnectionHelper.bU = "http://push.buy.test.mi.com/";
            ConnectionHelper.ck = ConnectionHelper.cj;
            ConnectionHelper.cl = ConnectionHelper.f7052a + "in/service/smartboxdelivery/";
            ConnectionHelper.cv = "https://www.miui.com/res/doc/eula/" + LocaleHelper.b + com.xiaomi.smarthome.download.Constants.m;
            ConnectionHelper.cw = "https://mobile.mi.com/" + LocaleHelper.b + "/app/service/terms/";
            ConnectionHelper.cT = ShopApp.j() ? "staging.m.id.mipay.com" : "m.id.mipay.com";
            if (LocaleHelper.g()) {
                str = "https://in-go.buy.mi.com/" + LocaleHelper.b + "/httpdns";
            } else {
                if (LocaleHelper.n()) {
                    sb = new StringBuilder();
                    str2 = "https://ru-go.buy.mi.com/";
                } else {
                    sb = new StringBuilder();
                    str2 = ConnectionHelper.m;
                }
                sb.append(str2);
                sb.append(LocaleHelper.b);
                sb.append("/httpdns");
                str = sb.toString();
            }
            ConnectionHelper.cR = str;
            ConnectionHelper.cS = "https://go-buy.test.mi.com/" + LocaleHelper.b + "/httpdns";
            com.mi.mistatistic.sdk.Constants.f7315a = BuildSetting.b() ? "http://in.stat.appmifile.com/" : BuildSetting.d() ? "http://cn.stat.appmifile.com/" : "http://sg.stat.appmifile.com/";
            ConnectionHelper.c();
            ConnectionHelper.a();
            LogUtil.b(ShopApp.o, "商城 SDK 切换国家，清理，初始化完毕。");
            return objArr[1];
        }

        /* access modifiers changed from: protected */
        public void onCancelled() {
            super.onCancelled();
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onProgressUpdate(Integer... numArr) {
            super.onProgressUpdate(numArr);
        }

        /* access modifiers changed from: protected */
        /* renamed from: a */
        public void onPostExecute(ShopSdkCleanListener shopSdkCleanListener) {
            super.onPostExecute(shopSdkCleanListener);
            if (shopSdkCleanListener != null) {
                shopSdkCleanListener.a();
            }
        }
    }

    public static void logout() {
        CookieManager.getInstance().removeAllCookie();
        com.mi.global.shop.xmsf.account.LoginManager.u().logout((LoginManager.LogoutCallback) null);
        LogUtil.b(o, "商城 SDK，logout。");
    }

    public static void a(boolean z) {
        LogUtil.a(z);
    }

    public static void b(boolean z) {
        i = z;
        LogUtil.b(o, "点击商城的 tab");
        try {
            if (h != null && z) {
                MiShopStatInterface.a(h.f + JSMethod.NOT_SET + Constants.Stat.e, HomeFragmentSingleton.i());
                LogUtil.b(o, "点击商城的 tab，长传点击事件统计数据");
            }
        } catch (Exception e2) {
            LogUtil.a(o, "上传点击商城的 tab 统计事件出错，" + e2.getMessage());
        }
    }

    public static void c(boolean z) {
        j = z;
    }

    public static void a(boolean z, @NonNull String str) {
        Application g2 = g();
        boolean booleanPref = Utils.Preference.getBooleanPref(g2, Constants.Prefence.l + str, false);
        LogUtil.b(o, str + "，当前米家渠道隐私协议同意的值 == " + booleanPref);
        if (!booleanPref && z) {
            FacebookSdk.sdkInitialize(g());
            MiShopStatInterface.a(g(), h().x);
            LogUtil.b(o, str + "，用户同意隐私协议后，进行 FaceBookSdk 和 打点统计库的初始化");
        }
        Application g3 = g();
        Utils.Preference.setBooleanPref(g3, Constants.Prefence.l + str, z);
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        sb.append("，更新隐私同意的值后，米家渠道隐私协议同意的值 == ");
        Application g4 = g();
        sb.append(Utils.Preference.getBooleanPref(g4, Constants.Prefence.l + str, false));
        LogUtil.b(o, sb.toString());
    }
}
