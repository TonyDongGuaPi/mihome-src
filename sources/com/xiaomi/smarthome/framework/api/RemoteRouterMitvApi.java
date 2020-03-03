package com.xiaomi.smarthome.framework.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.Uri;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.miot.support.monitor.aop.okhttp3.OkHttp3Aspect;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.device.MiTvUtil;
import com.xiaomi.smarthome.device.MitvDeviceManager;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.apache.http.NameValuePair;
import com.xiaomi.smarthome.library.apache.http.message.BasicNameValuePair;
import com.xiaomi.smarthome.library.common.util.Installation;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.HttpApi;
import com.xiaomi.smarthome.library.http.Request2;
import com.xiaomi.smarthome.library.http.async.AsyncHandler;
import com.xiaomi.smarthome.library.http.async.HttpAsyncHandle;
import com.xiaomi.smarthome.library.http.async.TextAsyncHandler;
import com.xiaomi.youpin.hawkeye.HawkEyeAspect;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.CookieManager;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import okhttp3.Dispatcher;
import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.internal.Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.runtime.internal.AroundClosure;
import org.aspectj.runtime.reflect.Factory;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class RemoteRouterMitvApi {

    /* renamed from: a  reason: collision with root package name */
    public static final String f16396a = "RemoteCameraApi";
    static final String b = "http://%s:6095/controller?action=keyevent&keycode=%s";
    static final String c = "http://%s:6095/request?action=%s";
    static final String d = "http://%s:6095/account?action=getState";
    static final String e = "http://%s:6095/account?action=addNew&data=%s";
    static final String f = "http://%s:6095/controller?action=play&type=video&mediaid=0&ci=0&clientname=%s&title=%s&position=0&playlength=0&url=%s";
    static final String g = "http://%s:6095/controller?action=play&type=monitor&param=%s";
    static final String h = "http://%s:6095/general?action=modifyDeviceName&name=%s&ts=%s&sign=%s";
    static final String i = "http://%s:6095/generalA?action=systemCleanInfo";
    static final String j = "http://%s:6095/controller?action=startapp&&type=packagename&packagename=com.cmcm.cleanmaster.tv";
    static String k = null;
    private static Random l;
    private static String m = "a2ffa5c9be07488bbb04a3a47d3c5f6a";
    private static RemoteRouterMitvApi n;
    private static final Object o = new Object();
    private static final JoinPoint.StaticPart r = null;
    private OkHttpClient p;
    private OkHttpClient q = ClientUtil.a();

    public class AjcClosure1 extends AroundClosure {
        public AjcClosure1(Object[] objArr) {
            super(objArr);
        }

        public Object run(Object[] objArr) {
            Object[] objArr2 = this.state;
            return RemoteRouterMitvApi.a((RemoteRouterMitvApi) objArr2[0], (OkHttpClient.Builder) objArr2[1], (JoinPoint) objArr2[2]);
        }
    }

    public static class MitvSystemCleanInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f16405a;
        public int b;
        public long c;
    }

    public static class RouterToken {

        /* renamed from: a  reason: collision with root package name */
        public String f16406a;
        public String b;
    }

    private static void d() {
        Factory factory = new Factory("RemoteRouterMitvApi.java", RemoteRouterMitvApi.class);
        r = factory.a("method-call", (Signature) factory.a("1", "build", "okhttp3.OkHttpClient$Builder", "", "", "", "okhttp3.OkHttpClient"), 90);
    }

    static {
        d();
    }

    public RemoteRouterMitvApi() {
        OkHttpClient.Builder cookieJar = new OkHttpClient.Builder().dispatcher(new Dispatcher(new ThreadPoolExecutor(6, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp Dispatcher", false)))).connectTimeout(4, TimeUnit.SECONDS).readTimeout(30, TimeUnit.SECONDS).writeTimeout(30, TimeUnit.SECONDS).cookieJar(new JavaNetCookieJar(new CookieManager()));
        JoinPoint a2 = Factory.a(r, (Object) this, (Object) cookieJar);
        this.p = (OkHttpClient) OkHttp3Aspect.d().a(new AjcClosure1(new Object[]{this, cookieJar, a2}).linkClosureAndJoinPoint(4112));
    }

    static final OkHttpClient a(RemoteRouterMitvApi remoteRouterMitvApi, OkHttpClient.Builder builder, JoinPoint joinPoint) {
        HawkEyeAspect.a().a(joinPoint);
        return builder.build();
    }

    public static RemoteRouterMitvApi a() {
        if (n == null) {
            synchronized (o) {
                if (n == null) {
                    n = new RemoteRouterMitvApi();
                }
            }
        }
        return n;
    }

    protected static String b() {
        if (l == null) {
            l = new SecureRandom();
        }
        return "1_" + Installation.a(CommonApplication.getAppContext()) + JSMethod.NOT_SET + System.currentTimeMillis() + JSMethod.NOT_SET + l.nextLong();
    }

    protected static String a(String str, String str2) {
        String b2 = b(str + m);
        return b(str2 + b2);
    }

    private static String b(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("SHA1");
            instance.update(a(str));
            return String.format("%1$040x", new Object[]{new BigInteger(1, instance.digest())});
        } catch (NoSuchAlgorithmException e2) {
            throw new RuntimeException(e2);
        }
    }

    public static byte[] a(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException unused) {
            return str.getBytes();
        }
    }

    public static String c() {
        DhcpInfo dhcpInfo;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1 || (dhcpInfo = ((WifiManager) CommonApplication.getAppContext().getApplicationContext().getSystemService("wifi")).getDhcpInfo()) == null) {
            return null;
        }
        return Formatter.formatIpAddress(dhcpInfo.gateway);
    }

    public AsyncHandle a(Context context, ArrayList<String> arrayList, AsyncCallback<JSONObject, Error> asyncCallback) {
        ArrayList arrayList2 = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            JSONArray jSONArray = new JSONArray();
            Iterator<String> it = arrayList.iterator();
            while (it.hasNext()) {
                jSONArray.put(it.next());
            }
            jSONObject.put("dids", jSONArray);
        } catch (JSONException unused) {
        }
        arrayList2.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(context, new NetRequest.Builder().a("POST").b("/device/get_recommend_list").b((List<KeyValuePair>) arrayList2).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public HttpAsyncHandle a(Context context, final String str, String str2, String str3, final AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        String substring = str.startsWith("mitv.") ? str.substring(5) : str;
        int indexOf = substring.indexOf(58);
        if (indexOf > 0) {
            substring = substring.substring(0, indexOf);
        }
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(String.format(e, new Object[]{str2, Uri.encode(MiTvUtil.a(substring, str3))})).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("status") == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        MitvDeviceManager.b().c(str);
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(optJSONObject);
                            return;
                        }
                        return;
                    }
                } catch (JSONException unused) {
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public HttpAsyncHandle a(Context context, String str, String str2, String str3, String str4, final AsyncResponseCallback<JSONObject> asyncResponseCallback) {
        String format = String.format(f, new Object[]{str4, str2, str3, Uri.encode(str)});
        Log.d(f16396a, "play url:" + format + "real url:" + str);
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(format).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.optInt("status") == 0) {
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(optJSONObject);
                            return;
                        }
                        return;
                    }
                } catch (JSONException unused) {
                }
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public HttpAsyncHandle a(Context context, final AsyncResponseCallback<MiRouterInfo> asyncResponseCallback) {
        Log.d(f16396a, "getMiRouterInfo");
        k = c();
        if (TextUtils.isEmpty(k) && asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
        }
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(String.format("http://%s/cgi-bin/luci/api/xqsystem/init_info", new Object[]{k})).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                Log.d(RemoteRouterMitvApi.f16396a, "getMiRouterInfo onSuccess:" + str);
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

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public HttpAsyncHandle a(Context context, String str, final AsyncResponseCallback<RouterToken> asyncResponseCallback) {
        Log.d(f16396a, "getRouterToken");
        String b2 = b();
        String a2 = a(str, b2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(new BasicNameValuePair("username", "admin"));
        arrayList.add(new BasicNameValuePair("nonce", b2));
        arrayList.add(new BasicNameValuePair("password", a2));
        k = c();
        if (TextUtils.isEmpty(k) && asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
        }
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(String.format("http://%s/cgi-bin/luci/api/xqsystem/token", new Object[]{k})).a((List<NameValuePair>) arrayList).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                Log.d(RemoteRouterMitvApi.f16396a, "getRouterToken onSuccess:" + str);
                try {
                    RouterToken routerToken = new RouterToken();
                    JSONObject jSONObject = new JSONObject(str);
                    routerToken.f16406a = jSONObject.optString("token");
                    routerToken.b = jSONObject.optString("name");
                    if (!TextUtils.isEmpty(routerToken.f16406a)) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(routerToken);
                        }
                    } else if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                } catch (Exception unused) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public HttpAsyncHandle b(Context context, String str, final AsyncResponseCallback<String> asyncResponseCallback) {
        Log.d(f16396a, "getRouterSecret");
        if (TextUtils.isEmpty(k) && asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
        }
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(String.format("http://%s/cgi-bin/luci/;stok=%s/api/xqdatacenter/identify_device", new Object[]{k, str})).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                Log.d(RemoteRouterMitvApi.f16396a, "getRouterToken onSuccess:" + str);
                try {
                    String optString = new JSONObject(str).optString("info");
                    if (!TextUtils.isEmpty(optString)) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(optString);
                        }
                    } else if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                } catch (Exception unused) {
                    if (asyncResponseCallback != null) {
                        asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                    }
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }

    public static class MiRouterInfo {

        /* renamed from: a  reason: collision with root package name */
        public String f16404a;
        public String b;
        public String c;
        public String d;

        public static MiRouterInfo a(JSONObject jSONObject) {
            MiRouterInfo miRouterInfo = new MiRouterInfo();
            miRouterInfo.f16404a = jSONObject.optString("id");
            miRouterInfo.b = jSONObject.optString("routerId");
            miRouterInfo.c = jSONObject.optString("routername");
            miRouterInfo.d = jSONObject.optString(MipayConstants.S);
            return miRouterInfo;
        }
    }

    public HttpAsyncHandle a(String str, final AsyncResponseCallback<String> asyncResponseCallback) {
        k = c();
        if (TextUtils.isEmpty(k) && asyncResponseCallback != null) {
            asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
        }
        return HttpApi.a(this.q, new Request2.Builder().a("GET").b(String.format(str, new Object[]{k})).a(), (AsyncHandler) new TextAsyncHandler() {
            /* renamed from: a */
            public void onSuccess(String str, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(str);
                }
            }

            public void onFailure(com.xiaomi.smarthome.library.http.Error error, Exception exc, Response response) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a(ErrorCode.ERROR_UNKNOWN_ERROR.getCode());
                }
            }
        });
    }
}
