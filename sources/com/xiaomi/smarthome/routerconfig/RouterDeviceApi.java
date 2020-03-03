package com.xiaomi.smarthome.routerconfig;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.text.format.Formatter;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.api.Callback;
import com.xiaomi.smarthome.device.api.Parser;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.server_compact.ServerCompact;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair;
import com.xiaomi.smarthome.library.common.network.NetworkUtils;
import com.xiaomi.smarthome.library.commonapi.SystemApi;
import com.xiaomi.smarthome.library.crypto.SHA1Util;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterDeviceApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f21182a = "http://%s/cgi-bin/luci/api/xqsystem/init_info";
    private static final String b = "http://%s/cgi-bin/luci/api/xqsystem/token";
    private static final String c = "http://%s/cgi-bin/luci/;stok=%s/api/xqdatacenter/identify_device";

    public static String a(Context context) {
        DhcpInfo dhcpInfo;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1 || (dhcpInfo = ((WifiManager) context.getSystemService("wifi")).getDhcpInfo()) == null) {
            return null;
        }
        return Formatter.formatIpAddress(dhcpInfo.gateway);
    }

    public static void a(String str, Callback<String> callback) {
        NetworkUtils.a(String.format(f21182a, new Object[]{str}), "GET", (List<NameValuePair>) new ArrayList(), callback, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    public static void a(Context context, String str, String str2, AsyncCallback<Boolean, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "POST");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("serialNumber", str);
            jSONObject2.put("deviceID", str2);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/appgateway/third/miwifi/app/s/device/isDeviceBound").b((List<KeyValuePair>) arrayList).a(), new JsonParser<Boolean>() {
            /* renamed from: a */
            public Boolean parse(JSONObject jSONObject) throws JSONException {
                if (jSONObject == null) {
                    return false;
                }
                return Boolean.valueOf(jSONObject.optBoolean("result", false));
            }
        }, Crypto.RC4, asyncCallback);
    }

    public static void a(Context context, String str, String str2, Callback<String> callback) {
        String format = String.format(b, new Object[]{str});
        StringBuilder sb = new StringBuilder();
        sb.append("1");
        sb.append(JSMethod.NOT_SET);
        sb.append(SystemApi.a().a(context, ServerCompact.g(context)));
        sb.append(JSMethod.NOT_SET);
        sb.append(System.currentTimeMillis());
        sb.append(JSMethod.NOT_SET);
        sb.append(new Random().nextInt(99999999));
        String lowerCase = SHA1Util.a(str2 + "a2ffa5c9be07488bbb04a3a47d3c5f6a").toLowerCase();
        String lowerCase2 = SHA1Util.a(sb.toString() + lowerCase).toLowerCase();
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("username", "admin"));
        arrayList.add(new BasicNameValuePair("nonce", sb.toString()));
        arrayList.add(new BasicNameValuePair("password", lowerCase2));
        NetworkUtils.a(format, "POST", (List<NameValuePair>) arrayList, callback, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    public static void a(String str, String str2, Callback<String> callback) {
        NetworkUtils.a(String.format(c, new Object[]{str, str2}), "GET", (List<NameValuePair>) new ArrayList(), callback, new Parser<String>() {
            /* renamed from: a */
            public String parse(String str) throws JSONException {
                return str;
            }
        });
    }

    public static void b(Context context, String str, String str2, AsyncCallback<String, Error> asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("method", "POST");
            JSONObject jSONObject2 = new JSONObject();
            jSONObject2.put("deviceName", str);
            jSONObject2.put("d", str2);
            jSONObject.put("params", jSONObject2);
        } catch (JSONException unused) {
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        Context context2 = context;
        CoreApi.a().a(context2, new NetRequest.Builder().a("POST").b("/appgateway/third/miwifi/app/s/register").b((List<KeyValuePair>) arrayList).a(), new JsonParser<String>() {
            /* renamed from: a */
            public String parse(JSONObject jSONObject) throws JSONException {
                return jSONObject == null ? "" : jSONObject.toString();
            }
        }, Crypto.RC4, asyncCallback);
    }
}
