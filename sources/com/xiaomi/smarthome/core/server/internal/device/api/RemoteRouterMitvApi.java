package com.xiaomi.smarthome.core.server.internal.device.api;

import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.Error;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request2;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.json.JSONObject;

public class RemoteRouterMitvApi {

    /* renamed from: a  reason: collision with root package name */
    private static Object f14565a = new Object();
    private static RemoteRouterMitvApi b;
    private static String c = null;
    private OkHttpClient d = ClientUtil.a();

    public static RemoteRouterMitvApi a() {
        if (b == null) {
            synchronized (f14565a) {
                if (b == null) {
                    b = new RemoteRouterMitvApi();
                }
            }
        }
        return b;
    }

    public static String b() {
        DhcpInfo dhcpInfo;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CoreService.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1 || (dhcpInfo = ((WifiManager) CoreService.getAppContext().getSystemService("wifi")).getDhcpInfo()) == null) {
            return null;
        }
        return Formatter.formatIpAddress(dhcpInfo.gateway);
    }

    public HttpAsyncHandle a(final AsyncResponseCallback<MiRouterInfo> asyncResponseCallback) {
        c = b();
        if (TextUtils.isEmpty(c) && asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
        }
        return HttpApi.a(this.d, new Request2.Builder().a("GET").b(String.format("http://%s/cgi-bin/luci/api/xqsystem/init_info", new Object[]{c})).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                try {
                    MiRouterInfo a2 = MiRouterInfo.a(new JSONObject(str));
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(a2);
                    }
                } catch (Exception unused) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                }
            }

            public void onFailure(Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public static class MiRouterInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f14567a;
        public String b;
        public String c;
        public String d;
        public String e;

        public static MiRouterInfo a(JSONObject jSONObject) {
            MiRouterInfo miRouterInfo = new MiRouterInfo();
            miRouterInfo.f14567a = jSONObject.optString("id");
            miRouterInfo.b = jSONObject.optString("routerId");
            miRouterInfo.c = jSONObject.optString("routername");
            miRouterInfo.d = jSONObject.optString(MipayConstants.S);
            miRouterInfo.e = jSONObject.optString("countrycode");
            return miRouterInfo;
        }
    }
}
