package com.xiaomi.smarthome.framework.api;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.TextUtils;
import android.text.format.Formatter;
import android.util.Log;
import android.util.Pair;
import com.mi.global.bbs.utils.ConnectionHelper;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.ErrorCode;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import com.xiaomi.smarthome.library.common.util.MD5;
import com.xiaomi.smarthome.library.http.ClientUtil;
import com.xiaomi.smarthome.library.http.KeyValuePair;
import com.xiaomi.smarthome.library.http.util.KeyValuePairUtil;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class RouterLocalApi {

    /* renamed from: a  reason: collision with root package name */
    static final String f16407a = "RouterLocalApi";
    private static final String f = "/cgi-bin/luci";
    private static Pair<List<KeyValuePair>, String> g = Pair.create((Object) null, (Object) null);
    private static RouterLocalApi h = null;
    private static final Object i = new Object();
    final String b = "/api/xqdatacenter/download";
    protected OkHttpClient c = ClientUtil.a();
    boolean d = false;
    ArrayList<WeakReference<AsyncCallback<Void, Error>>> e = new ArrayList<>();
    /* access modifiers changed from: private */
    public String j = "";
    private String k;
    private String l;
    /* access modifiers changed from: private */
    public RouterInitInfo m;

    public interface JSONParser<T> {
        T b(JSONObject jSONObject) throws JSONException;
    }

    private RouterLocalApi() {
    }

    public static RouterLocalApi a() {
        if (h == null) {
            synchronized (i) {
                if (h == null) {
                    h = new RouterLocalApi();
                }
            }
        }
        return h;
    }

    public boolean b() {
        return this.m != null;
    }

    public void c() {
        this.m = null;
    }

    /* access modifiers changed from: package-private */
    public void d() {
        Log.d(f16407a, "notifySuccess");
        this.d = false;
        Iterator<WeakReference<AsyncCallback<Void, Error>>> it = this.e.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (!(next == null || next.get() == null)) {
                ((AsyncCallback) next.get()).sendSuccessMessage(null);
            }
        }
        this.e.clear();
    }

    /* access modifiers changed from: package-private */
    public void e() {
        Log.d(f16407a, "notifyFail");
        this.d = false;
        Iterator<WeakReference<AsyncCallback<Void, Error>>> it = this.e.iterator();
        while (it.hasNext()) {
            WeakReference next = it.next();
            if (!(next == null || next.get() == null)) {
                ((AsyncCallback) next.get()).sendFailureMessage(new Error(ErrorCode.ERROR_UNKNOWN_ERROR.getCode(), ""));
            }
        }
        this.e.clear();
    }

    public void a(final String str, AsyncCallback<Void, Error> asyncCallback) {
        Log.d(f16407a, "checkLocalRouterInfo");
        if (asyncCallback != null) {
            if (this.d) {
                this.e.add(new WeakReference(asyncCallback));
                return;
            }
            this.d = true;
            this.e.add(new WeakReference(asyncCallback));
            if (this.m == null || !this.m.d.equals(str)) {
                this.m = null;
                if (TextUtils.isEmpty(g())) {
                    e();
                    return;
                }
                Log.d(f16407a, "simpleHandle");
                a("", "/api/xqsystem/init_info", "GET", (List<KeyValuePair>) null, new JSONParser<RouterInitInfo>() {
                    /* renamed from: a */
                    public RouterInitInfo b(JSONObject jSONObject) throws JSONException {
                        RouterInitInfo routerInitInfo = new RouterInitInfo();
                        routerInitInfo.f16412a = jSONObject.getInt("inited");
                        routerInitInfo.b = jSONObject.getInt("bound");
                        routerInitInfo.c = jSONObject.getString("id");
                        routerInitInfo.d = jSONObject.optString("routerId");
                        routerInitInfo.e = jSONObject.optString(MipayConstants.S, "R1D");
                        return routerInitInfo;
                    }
                }, new AsyncCallback<RouterInitInfo, Error>() {
                    /* renamed from: a */
                    public void onSuccess(RouterInitInfo routerInitInfo) {
                        if (routerInitInfo == null || !str.equals(routerInitInfo.d)) {
                            RouterLocalApi.this.e();
                            return;
                        }
                        RouterInitInfo unused = RouterLocalApi.this.m = routerInitInfo;
                        RouterRemoteApi.a().a(CommonApplication.getAppContext(), str, (AsyncCallback<String, Error>) new AsyncCallback<String, Error>() {
                            /* renamed from: a */
                            public void onSuccess(String str) {
                                String unused = RouterLocalApi.this.j = str;
                                RouterLocalApi.this.d();
                            }

                            public void onFailure(Error error) {
                                RouterLocalApi.this.e();
                            }
                        });
                    }

                    public void onFailure(Error error) {
                        RouterLocalApi.this.e();
                    }
                });
                return;
            }
            this.d = false;
            d();
        }
    }

    private String g() {
        this.l = null;
        this.k = null;
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) CommonApplication.getAppContext().getSystemService("connectivity")).getActiveNetworkInfo();
        boolean z = true;
        if (activeNetworkInfo == null || activeNetworkInfo.getType() != 1) {
            z = false;
        }
        if (!z) {
            return null;
        }
        WifiManager wifiManager = (WifiManager) CommonApplication.getAppContext().getApplicationContext().getSystemService("wifi");
        this.k = Formatter.formatIpAddress(wifiManager.getDhcpInfo().gateway);
        this.l = ConnectionHelper.HTTP_PREFIX + this.k + f;
        WifiInfo connectionInfo = wifiManager.getConnectionInfo();
        if (connectionInfo != null) {
            return connectionInfo.getSSID();
        }
        return null;
    }

    private String h() {
        return this.j;
    }

    /* access modifiers changed from: protected */
    public String a(String str) {
        if (TextUtils.isEmpty(this.l)) {
            return "http://192.168.31.1/cgi-bin/luci" + str;
        }
        String h2 = h();
        if (TextUtils.isEmpty(h2)) {
            return this.l + str;
        }
        return this.l + "/;stok=" + h2 + str;
    }

    /* access modifiers changed from: protected */
    public String f() {
        String d2 = WifiUtil.d(CommonApplication.getAppContext());
        return !TextUtils.isEmpty(d2) ? MD5.a(d2.toLowerCase()) : "";
    }

    /* access modifiers changed from: protected */
    public Pair<List<KeyValuePair>, String> a(String str, List<KeyValuePair> list, String str2, String str3) {
        if (list != null) {
            return Pair.create(list, (Object) null);
        }
        return g;
    }

    public String b(String str) {
        return a("/api/xqdatacenter/download") + "?path=" + URLEncoder.encode(str) + "&secret=" + f();
    }

    /* access modifiers changed from: protected */
    public <T> AsyncHandle a(String str, String str2, String str3, List<KeyValuePair> list, final JSONParser<T> jSONParser, final AsyncCallback<T, Error> asyncCallback) {
        Request request;
        String a2 = a(str2);
        final Pair<List<KeyValuePair>, String> a3 = a(str, list, str2, str3);
        if (a3 == null) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-1, ""));
            }
            return new AsyncHandle(null);
        }
        if (str3.equals("POST")) {
            request = new Request.Builder().url(a2).post(KeyValuePairUtil.a((List<KeyValuePair>) (List) a3.first)).build();
        } else {
            request = new Request.Builder().url(KeyValuePairUtil.a(a2, (List<KeyValuePair>) (List) a3.first)).build();
        }
        Call newCall = this.c.newCall(request);
        newCall.enqueue(new Callback() {
            public void onFailure(Call call, IOException iOException) {
                String str;
                if (asyncCallback != null) {
                    AsyncCallback asyncCallback = asyncCallback;
                    int code = ErrorCode.INVALID.getCode();
                    if (iOException == null) {
                        str = "request failure";
                    } else {
                        str = iOException.getMessage();
                    }
                    asyncCallback.sendFailureMessage(new Error(code, str));
                }
            }

            public void onResponse(Call call, Response response) throws IOException {
                RouterLocalApi.this.a(response, (Pair<List<KeyValuePair>, String>) a3, jSONParser, asyncCallback);
            }
        });
        return new AsyncHandle(newCall);
    }

    /* access modifiers changed from: private */
    public <T> void a(Response response, Pair<List<KeyValuePair>, String> pair, JSONParser<T> jSONParser, AsyncCallback<T, Error> asyncCallback) {
        if (response == null) {
            if (asyncCallback != null) {
                asyncCallback.sendFailureMessage(new Error(-1, "response is null"));
            }
        } else if (response.isSuccessful()) {
            try {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    if (jSONObject.optInt("code") == 0) {
                        T t = null;
                        if (jSONParser != null) {
                            t = jSONParser.b(jSONObject);
                        }
                        if (asyncCallback != null) {
                            asyncCallback.sendSuccessMessage(t);
                            return;
                        }
                        return;
                    }
                    throw new Exception("failure");
                } catch (Exception unused) {
                    if (asyncCallback != null) {
                        asyncCallback.sendFailureMessage(new Error(-1, "wrong response format"));
                    }
                }
            } catch (Exception unused2) {
                if (asyncCallback != null) {
                    asyncCallback.sendFailureMessage(new Error(-1, "response is null"));
                }
            }
        } else if (asyncCallback != null) {
            asyncCallback.sendFailureMessage(new Error(response.code(), "request failure"));
        }
    }

    class RouterInitInfo {

        /* renamed from: a  reason: collision with root package name */
        public int f16412a;
        public int b;
        public String c;
        public String d;
        public String e;

        RouterInitInfo() {
        }
    }
}
