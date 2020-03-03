package com.xiaomi.smarthome.framework.navigate;

import android.net.Uri;
import android.text.TextUtils;
import android.util.Base64;
import com.xiaomi.smarthome.SmartHomeMainActivity;
import com.xiaomi.smarthome.auth.ThirdAuthMainActivity;
import com.xiaomi.smarthome.auth.bindaccount.ThirdAccountGroupListActivity;
import com.xiaomi.smarthome.device.authorization.page.DeviceAuthSlaveListActivity;
import com.xiaomi.smarthome.device.choosedevice.ChooseDeviceActivity;
import com.xiaomi.smarthome.family.ShareDeviceActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginMiuiActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdPhoneActivity;
import com.xiaomi.smarthome.framework.navigate.UrlResolver;
import com.xiaomi.smarthome.framework.webview.CommonWebViewActivity;
import com.xiaomi.smarthome.messagecenter.ui.MessageCenterActivity;
import com.xiaomi.smarthome.miio.page.deviceshare.ShareDeviceInfoActivity;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartGroupWeatherActivity;
import com.xiaomi.smarthome.miio.page.smartgroup.SmartgroupWpActivity;
import com.xiaomi.smarthome.miio.page.smartlife.SmartLifeAllDeviceActivity;
import java.util.HashMap;
import java.util.Map;

public class PageUrl {
    private static Map<String, UrlConfigInfo> A = new HashMap<String, UrlConfigInfo>() {
        {
            put("/device/*", new UrlConfigInfo().a(true).b(true));
        }
    };
    private static Map<String, UrlConfigInfo> B = new HashMap<String, UrlConfigInfo>() {
        {
            put(PageUrl.q, new UrlConfigInfo().a(true).b(true));
        }
    };
    private static Map<String, UrlConfigInfo> C = new HashMap<String, UrlConfigInfo>() {
        {
            put("/cloud_device/main", new UrlConfigInfo().a((Class<?>) ThirdAccountGroupListActivity.class).a(false).b(true));
        }
    };
    private static Map<String, UrlConfigInfo> D = new HashMap<String, UrlConfigInfo>() {
        {
            put("/recommend", new UrlConfigInfo().a(false).b(true));
        }
    };

    /* renamed from: a  reason: collision with root package name */
    public static final String f16632a = "http";
    public static final String b = "https";
    public static final String c = "mihome";
    public static final String d = "smarthome";
    public static final String e = "mijia";
    public static final String f = "home.mi.com";
    public static final String g = "mihome.app";
    public static final String h = "smarthome.app";
    public static final String i = "device";
    public static final String j = "scene";
    public static final String k = "/main";
    public static final String l = "/device";
    public static final String m = "/cloud_device";
    public static final String n = "/shop";
    public static final String o = "/detail";
    public static final String p = "/web";
    public static final String q = "/share";
    public static final String r = "/connect";
    public static final String s = "/message_center";
    public static final String t = "/recommend";
    static final String u = "https://home.mi.com/index/login?followup=";
    static final String v = "mihome://shop?url=";
    private static final UrlConfigInfo w = new UrlConfigInfo().a((Class<?>) SmartHomeMainActivity.class).a(true).b(false);
    private static final UrlConfigInfo x = new UrlConfigInfo().a((Class<?>) ShopLauncherMiddle.class).a(false).b(false);
    private static final UrlConfigInfo y = new UrlConfigInfo().a((Class<?>) ChooseDeviceActivity.class).a(true).b(true);
    private static Map<String, UrlConfigInfo> z = new HashMap<String, UrlConfigInfo>() {
        {
            put(PageUrl.k, new UrlConfigInfo().a((Class<?>) SmartHomeMainActivity.class).a(false).b(false));
            put("/main/login", new UrlConfigInfo().a((Class<?>) LoginPwdActivity.class).a(true).b(false));
            put("/main/login_phone_pwd", new UrlConfigInfo().a((Class<?>) LoginPwdPhoneActivity.class).a(true).b(false));
            put("/main/login_mi_system", new UrlConfigInfo().a((Class<?>) LoginMiuiActivity.class).a(true).b(false));
            put("/main/device_share", new UrlConfigInfo().a((Class<?>) ShareDeviceActivity.class).a(false).b(true));
            put("/main/device_share_info", new UrlConfigInfo().a((Class<?>) ShareDeviceInfoActivity.class).a(true).b(true));
            put("/main/msgcenter", new UrlConfigInfo().a((Class<?>) MessageCenterActivity.class).a(true).b(false));
            put("/main/weather", new UrlConfigInfo().a((Class<?>) SmartGroupWeatherActivity.class).a(true).b(false));
            put("/main/air_water_info", new UrlConfigInfo().a((Class<?>) SmartgroupWpActivity.class).a(true).b(false));
            put("/main/select_smart_device", new UrlConfigInfo().a((Class<?>) SmartLifeAllDeviceActivity.class).a(false).b(false));
            put("/main/voice_ctrl_authorize", new UrlConfigInfo().a((Class<?>) DeviceAuthSlaveListActivity.class).a(false).b(true));
            put("/main/third_auth", new UrlConfigInfo().a((Class<?>) ThirdAuthMainActivity.class).b(true).a(false));
            put("/main/web_page", new UrlConfigInfo().a((Class<?>) CommonWebViewActivity.class).b(false).a((UrlConfigInfo.UrlValidator) new CommonWebViewActivity.ExternalUrlValidator()).a(true));
            put("/main/ble_gateway", new UrlConfigInfo().a((Class<?>) SmartHomeMainActivity.class).b(true).a(true));
            put(PageUrl.r, new UrlConfigInfo().a((Class<?>) ChooseDeviceActivity.class).a(true).b(true));
        }
    };

    public static boolean a(Uri uri) {
        if (uri == null) {
            return false;
        }
        String scheme = uri.getScheme();
        if (TextUtils.isEmpty(scheme)) {
            return false;
        }
        if (!scheme.equals("http") && !scheme.equals("https") && !scheme.equals("mihome") && !scheme.equals(d) && !scheme.equals(e)) {
            return false;
        }
        String host = uri.getHost();
        if (TextUtils.isEmpty(host)) {
            return false;
        }
        if (host.equals(f) || host.equals(g) || host.equals(h) || host.equals("device") || host.equals(j)) {
            return true;
        }
        return false;
    }

    public static Uri b(Uri uri) {
        String uri2 = uri.toString();
        if (uri2.startsWith(u)) {
            return Uri.parse(Uri.decode(uri2.substring(u.length())));
        }
        return uri2.startsWith(v) ? Uri.parse(new String(Base64.decode(uri2.substring(v.length()), 0))) : uri;
    }

    public static UrlConfigInfo c(Uri uri) {
        if (uri == null || !a(uri)) {
            return null;
        }
        String path = uri.getPath();
        if (TextUtils.isEmpty(path)) {
            return null;
        }
        UrlConfigInfo urlConfigInfo = z.get(path);
        if (urlConfigInfo == null && g(path)) {
            urlConfigInfo = x;
        }
        if (urlConfigInfo == null && b(path)) {
            urlConfigInfo = A.get("/device/*");
        }
        if (urlConfigInfo == null && c(path)) {
            urlConfigInfo = y;
        }
        if (urlConfigInfo == null) {
            urlConfigInfo = B.get(path);
        }
        if (urlConfigInfo == null) {
            urlConfigInfo = C.get(path);
        }
        UrlConfigInfo urlConfigInfo2 = urlConfigInfo == null ? D.get(path) : urlConfigInfo;
        if (urlConfigInfo2 == null || urlConfigInfo2.a(uri)) {
            return urlConfigInfo2;
        }
        return null;
    }

    public static Map<String, String> d(Uri uri) {
        HashMap hashMap = new HashMap();
        if (uri == null) {
            return hashMap;
        }
        for (String next : uri.getQueryParameterNames()) {
            hashMap.put(next, uri.getQueryParameter(next));
        }
        return hashMap;
    }

    public static boolean a(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(k)) {
            return true;
        }
        return false;
    }

    public static boolean b(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(l)) {
            return true;
        }
        return false;
    }

    public static boolean c(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(r)) {
            return true;
        }
        return false;
    }

    public static boolean d(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(s)) {
            return true;
        }
        return false;
    }

    public static boolean a(String str, String str2) {
        if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2) && TextUtils.equals(str, j) && str2.startsWith("/recommend")) {
            return true;
        }
        return false;
    }

    public static boolean e(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(m)) {
            return true;
        }
        return false;
    }

    public static boolean f(String str) {
        if (!TextUtils.isEmpty(str) && str.startsWith(q)) {
            return true;
        }
        return false;
    }

    public static boolean g(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        if (str.startsWith(n) || str.startsWith(o) || str.startsWith(p)) {
            return true;
        }
        return false;
    }

    public static Class<?> a(Uri uri, UrlResolver.Parameter parameter) {
        UrlConfigInfo urlConfigInfo = z.get(parameter.f16677a);
        if (urlConfigInfo == null || !urlConfigInfo.a(uri)) {
            return null;
        }
        return urlConfigInfo.a();
    }

    public static Class<?> a(UrlResolver.Parameter parameter) {
        UrlConfigInfo urlConfigInfo;
        if (parameter.f16677a.equalsIgnoreCase("/shop/main")) {
            urlConfigInfo = w;
        } else {
            urlConfigInfo = x;
        }
        if (urlConfigInfo != null) {
            return urlConfigInfo.a();
        }
        return null;
    }

    static DevicePageParam h(String str) {
        if (TextUtils.isEmpty(str) || !str.startsWith("/device/")) {
            return null;
        }
        DevicePageParam devicePageParam = new DevicePageParam();
        devicePageParam.f16633a = "";
        devicePageParam.b = "";
        String substring = str.substring("/device/".length());
        int indexOf = substring.indexOf("/");
        if (indexOf < 0) {
            indexOf = substring.indexOf("?");
        }
        if (indexOf > 0) {
            devicePageParam.f16633a = substring.substring(0, indexOf);
            devicePageParam.b = substring.substring(indexOf);
        } else {
            devicePageParam.f16633a = substring;
        }
        return devicePageParam;
    }

    public static class UrlConfigInfo {

        /* renamed from: a  reason: collision with root package name */
        private Class<?> f16634a;
        private boolean b = true;
        private boolean c = true;
        private UrlValidator d = null;

        public interface UrlValidator {
            boolean a(Uri uri);
        }

        public UrlConfigInfo a(boolean z) {
            this.b = z;
            return this;
        }

        public UrlConfigInfo b(boolean z) {
            this.c = z;
            return this;
        }

        public UrlConfigInfo a(UrlValidator urlValidator) {
            this.d = urlValidator;
            return this;
        }

        public Class<?> a() {
            return this.f16634a;
        }

        public UrlConfigInfo a(Class<?> cls) {
            this.f16634a = cls;
            return this;
        }

        public boolean b() {
            return this.b;
        }

        public boolean c() {
            return this.c;
        }

        public boolean a(Uri uri) {
            if (this.d != null) {
                return this.d.a(uri);
            }
            return true;
        }
    }

    static class DevicePageParam {

        /* renamed from: a  reason: collision with root package name */
        String f16633a;
        String b;

        DevicePageParam() {
        }
    }
}
