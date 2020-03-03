package com.xiaomi.smarthome.auth;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.text.TextUtils;
import com.miui.tsmclient.net.TSMAuthContants;
import com.xiaomi.mipush.sdk.Constants;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.auth.AuthInfo;
import com.xiaomi.smarthome.authlib.IAuthCallBack;
import com.xiaomi.smarthome.core.entity.net.Crypto;
import com.xiaomi.smarthome.core.entity.net.KeyValuePair;
import com.xiaomi.smarthome.core.entity.net.NetRequest;
import com.xiaomi.smarthome.frame.AsyncCallback;
import com.xiaomi.smarthome.frame.AsyncHandle;
import com.xiaomi.smarthome.frame.Error;
import com.xiaomi.smarthome.frame.JsonParser;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.library.crypto.MD5Util;
import com.xiaomi.smarthome.miio.Miio;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class AuthManager {

    /* renamed from: a  reason: collision with root package name */
    public static final int f13802a = 8;
    public static String b = "1.1.4";
    public static int c = -1;
    public static String d = "-1";
    private static final String e = "AuthManager";
    private static AuthManager f;
    private String g;
    /* access modifiers changed from: private */
    public AuthInfo h;
    private String i;
    private String j;
    private IAuthCallBack k;
    private Bundle l;
    private int m = -1;

    public AuthInfo a() {
        return this.h;
    }

    public void a(AuthInfo authInfo) {
        this.h = authInfo;
    }

    public String b() {
        return this.g;
    }

    public void a(String str) {
        this.g = str;
    }

    public String c() {
        return this.i;
    }

    public void b(String str) {
        this.i = str;
    }

    public String d() {
        return this.j;
    }

    public void c(String str) {
        this.j = str;
    }

    public IAuthCallBack e() {
        return this.k;
    }

    public void a(IAuthCallBack iAuthCallBack) {
        this.k = iAuthCallBack;
    }

    public Bundle f() {
        return this.l;
    }

    public void a(Bundle bundle) {
        this.l = bundle;
    }

    public int g() {
        return this.m;
    }

    public void a(int i2) {
        this.m = i2;
    }

    private AuthManager() {
    }

    public static AuthManager h() {
        if (f == null) {
            f = new AuthManager();
        }
        return f;
    }

    public void a(final AsyncCallback asyncCallback, boolean z) {
        b(new AsyncCallback() {
            public void onSuccess(Object obj) {
                AuthInfo unused = AuthManager.this.h = (AuthInfo) obj;
                if (AuthManager.this.h != null) {
                    Miio.b(AuthManager.e, "updateAuthDefaultInfo" + AuthManager.this.h.toString());
                }
                if (asyncCallback != null) {
                    asyncCallback.onSuccess(AuthManager.this.h);
                }
            }

            public void onFailure(Error error) {
                Miio.b(AuthManager.e, "updateAuthDefaultInfo onFailure" + error.a() + error.b());
                if (asyncCallback != null) {
                    asyncCallback.onFailure(error);
                }
            }
        }, z);
    }

    public AsyncHandle b(AsyncCallback asyncCallback, boolean z) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("application_id", this.g);
            if (z) {
                jSONObject.put("package_name", this.i);
                jSONObject.put("package_sign", this.j);
                jSONObject.put("check_sign", true);
            }
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/getdefaultauth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<AuthInfo>() {
            /* renamed from: a */
            public AuthInfo parse(JSONObject jSONObject) throws JSONException {
                AuthInfo a2 = AuthInfo.a(jSONObject);
                Miio.b(AuthManager.e, "auth parse:" + a2.toString());
                return a2;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, AsyncCallback asyncCallback) {
        Miio.b(e, "scope:--" + str.toString());
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject.put("application_id", this.g);
            jSONObject.put("scope", str);
            jSONObject.put("sign", a(currentTimeMillis));
            jSONObject.put("timestamp", currentTimeMillis);
            jSONObject.put("package_name", this.i);
            jSONObject.put("package_sign", this.j);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, String str2, String str3, String str4, String str5, AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        long currentTimeMillis = System.currentTimeMillis();
        try {
            jSONObject.put("response_type", str);
            jSONObject.put("client_id", this.g);
            jSONObject.put("device_id", str2);
            jSONObject.put("redirect_uri", str4);
            jSONObject.put("scope", str3);
            jSONObject.put("state", str5);
            jSONObject.put("sign", a(currentTimeMillis));
            jSONObject.put("timestamp", currentTimeMillis / 1000);
            jSONObject.put("package_name", this.i);
            jSONObject.put("package_sign", this.j);
            jSONObject.put(TSMAuthContants.PARAM_VALID, 352800);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("GET").b("/v2/oauth/authorize").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(String str, String str2, AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("application_id", this.g);
            jSONObject.put("redirect_uri", str2);
            jSONObject.put("state", str);
            jSONObject.put("sign", a(currentTimeMillis));
            jSONObject.put("timestamp", currentTimeMillis);
            jSONObject.put("response_type", "token");
            jSONObject.put("scope", 5);
            jSONObject.put("package_name", this.i);
            jSONObject.put("package_sign", this.j);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/requesttoken").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("data", new JSONObject().toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/authlist").b((List<KeyValuePair>) arrayList).a(), new JsonParser<List<AuthAppInfo>>() {
            /* renamed from: a */
            public List<AuthAppInfo> parse(JSONObject jSONObject) throws JSONException {
                ArrayList arrayList = new ArrayList();
                if (jSONObject != null) {
                    Miio.b(AuthManager.e, "getAuthList---" + jSONObject.toString());
                    JSONArray optJSONArray = jSONObject.optJSONArray("result");
                    for (int i = 0; i < optJSONArray.length(); i++) {
                        arrayList.add(AuthAppInfo.a((JSONObject) optJSONArray.get(i)));
                    }
                }
                return arrayList;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle b(AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("application_id", this.g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/revokeauth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                Miio.b(AuthManager.e, "deleteAuth---" + jSONObject.toString());
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle a(List<String> list, AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        StringBuilder sb = new StringBuilder();
        if (list != null && list.size() > 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                if (i2 > 0) {
                    sb.append(",");
                }
                sb.append(list.get(i2));
            }
        }
        try {
            if (sb.length() > 0) {
                jSONObject.put("device_ids", sb.toString());
            }
            jSONObject.put("client_id", this.g);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/v2/oauth/revoke_auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                Miio.b(AuthManager.e, "deleteAuth---" + jSONObject.toString());
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle c(AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("client_id", this.g);
            jSONObject.put("sign", a(currentTimeMillis));
            jSONObject.put("package_name", this.i);
            jSONObject.put("package_sign", this.j);
            jSONObject.put("timestamp", currentTimeMillis);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("GET").b("/v2/oauth/get_auth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public AsyncHandle d(AsyncCallback asyncCallback) {
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            long currentTimeMillis = System.currentTimeMillis();
            jSONObject.put("application_id", this.g);
            jSONObject.put("sign", a(currentTimeMillis));
            jSONObject.put("package_name", this.i);
            jSONObject.put("package_sign", this.j);
            jSONObject.put("timestamp", currentTimeMillis);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        arrayList.add(new KeyValuePair("data", jSONObject.toString()));
        return CoreApi.a().a(SHApplication.getAppContext(), new NetRequest.Builder().a("POST").b("/auth/getauth").b((List<KeyValuePair>) arrayList).a(), new JsonParser<JSONObject>() {
            /* renamed from: a */
            public JSONObject parse(JSONObject jSONObject) throws JSONException {
                return jSONObject;
            }
        }, Crypto.RC4, asyncCallback);
    }

    public String a(long j2) {
        try {
            StringBuilder sb = new StringBuilder();
            sb.append(this.i);
            sb.append(this.g);
            sb.append(this.j);
            StringBuilder sb2 = new StringBuilder();
            sb2.append(MD5Util.a(sb.toString()));
            if (CoreApi.a().q()) {
                sb2.append(CoreApi.a().s());
            }
            sb2.append(System.currentTimeMillis());
            Miio.b(e, "sb--" + sb.toString() + "---sb2---" + sb2.toString() + "-----md5--" + MD5Util.a(sb2.toString()));
            return MD5Util.a(sb2.toString());
        } catch (Exception e2) {
            e2.printStackTrace();
            return "";
        }
    }

    private String f(String str) {
        try {
            MessageDigest instance = MessageDigest.getInstance("MD5");
            instance.update(str.getBytes());
            return g(new BigInteger(1, instance.digest()).toString(16));
        } catch (Exception e2) {
            throw new RuntimeException("MD5加密错误:" + e2.getMessage(), e2);
        }
    }

    private String g(String str) {
        if (str.length() == 32) {
            return str;
        }
        return g("0" + str);
    }

    public boolean i() {
        for (int i2 = 0; i2 < this.h.f.size(); i2++) {
            if (this.h.f.get(i2).f13801a == 4) {
                return true;
            }
        }
        return false;
    }

    public boolean j() {
        for (int i2 = 0; i2 < this.h.f.size(); i2++) {
            if (this.h.f.get(i2).f13801a == 5) {
                return true;
            }
        }
        return false;
    }

    public boolean d(String str) {
        List<String> list = null;
        for (int i2 = 0; i2 < this.h.f.size(); i2++) {
            if (this.h.f.get(i2).f13801a == 4) {
                list = this.h.f.get(i2).b;
            }
        }
        if (list == null || list.size() == 0) {
            return false;
        }
        for (int i3 = 0; i3 < list.size(); i3++) {
            if (str.equalsIgnoreCase(list.get(i3))) {
                return true;
            }
        }
        return false;
    }

    public boolean e(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        List<PackageInfo> k2 = k();
        for (int i2 = 0; i2 < k2.size(); i2++) {
            if (str.equalsIgnoreCase(k2.get(i2).applicationInfo.packageName)) {
                return true;
            }
        }
        return false;
    }

    public static List<PackageInfo> k() {
        ArrayList arrayList = new ArrayList();
        List<PackageInfo> installedPackages = SHApplication.getAppContext().getPackageManager().getInstalledPackages(0);
        for (int i2 = 0; i2 < installedPackages.size(); i2++) {
            PackageInfo packageInfo = installedPackages.get(i2);
            int i3 = packageInfo.applicationInfo.flags;
            ApplicationInfo applicationInfo = packageInfo.applicationInfo;
            if ((i3 & 1) <= 0) {
                arrayList.add(packageInfo);
            }
        }
        return arrayList;
    }

    public void l() {
        if (f != null) {
            f = null;
        }
    }

    public boolean b(int i2) {
        if (this.h == null || this.h.f == null || this.h.f.size() == 0) {
            return false;
        }
        List<AuthInfo.AuthDetail> list = this.h.f;
        if (i2 == 4) {
            for (int i3 = 0; i3 < list.size(); i3++) {
                if (list.get(i3).f13801a == 4) {
                    return true;
                }
            }
            return false;
        } else if (i2 == 2) {
            return true;
        } else {
            if (i2 != 6) {
                return false;
            }
            for (int i4 = 0; i4 < list.size(); i4++) {
                if (list.get(i4).f13801a == 6) {
                    return true;
                }
            }
            return false;
        }
    }

    public JSONObject m() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(AuthConstants.h, c);
            jSONObject.put("sdk_version_name", d);
            jSONObject.put(Constants.c, 8);
            jSONObject.put("app_version_name", b);
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        return jSONObject;
    }
}
