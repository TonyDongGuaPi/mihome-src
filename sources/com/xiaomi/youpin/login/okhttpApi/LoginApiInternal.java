package com.xiaomi.youpin.login.okhttpApi;

import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.alipay.sdk.util.i;
import com.google.common.net.HttpHeaders;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.miui.tsmclient.account.OAuthAccountManager;
import com.taobao.weex.annotation.JSMethod;
import com.xiaomi.accountsdk.utils.CloudCoder;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.manager.BaseAccount;
import com.xiaomi.youpin.login.api.stat.LoginType;
import com.xiaomi.youpin.login.api.third_part.ThirdPartUrlConstant;
import com.xiaomi.youpin.login.entity.Error;
import com.xiaomi.youpin.login.entity.callback.MijiaWxAccessTokenRequestCall;
import com.xiaomi.youpin.login.entity.callback.YoupinWxAccessTokenRequestCall;
import com.xiaomi.youpin.login.entity.third_part.LoginByThirdPartAccessTokenError;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.entity.wx.MijiaWxAccessTokenByAuthCodeData;
import com.xiaomi.youpin.login.entity.wx.WxGuestVisitorInfo;
import com.xiaomi.youpin.login.entity.wx.WxGuestVisitorInfoData;
import com.xiaomi.youpin.login.entity.wx.YoupinWxAccessTokenByAuthCodeData;
import com.xiaomi.youpin.login.other.common.AppInfo;
import com.xiaomi.youpin.login.other.common.HexUtil;
import com.xiaomi.youpin.login.other.cookie.YouPinCookieManager;
import com.xiaomi.youpin.login.other.cookie.YouPinCookieUtil;
import com.xiaomi.youpin.login.other.http.KeyValuePair;
import com.xiaomi.youpin.login.other.http.KeyValuePairUtil;
import com.xiaomi.youpin.login.other.http.NameValuePair;
import com.xiaomi.youpin.login.other.http.client.utils.URLEncodedUtils;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.CookieManager;
import java.net.HttpCookie;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginApiInternal {

    /* renamed from: a  reason: collision with root package name */
    public static final String f23531a = "LoginApiInternal";
    private static final String b = "https://account.xiaomi.com";
    private static final String c = "account.xiaomi.com";
    private static final String d = "https://v.id.mi.com";
    private static final String e = "v.id.mi.com";
    /* access modifiers changed from: private */
    public static final SimpleDateFormat f = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);

    /* access modifiers changed from: private */
    public static void b(String str) {
    }

    private static Context e() {
        return MiLoginApi.a().o();
    }

    /* access modifiers changed from: private */
    public static OkHttpClient f() {
        return OkHttpApi.a().b();
    }

    /* access modifiers changed from: private */
    public static CookieManager g() {
        return OkHttpApi.a().c();
    }

    /* access modifiers changed from: private */
    public static String b(Long l, String str) {
        TreeMap treeMap = new TreeMap();
        treeMap.put("nonce", String.valueOf(l));
        return CloudCoder.generateSignature((String) null, (String) null, treeMap, str);
    }

    private static void a(String str, String str2) {
        CookieManager g = g();
        YouPinCookieUtil.a(g, str, "deviceId", "smarthome-" + AppInfo.a(e()), str2, "/");
    }

    public static void a(int i, String str, String str2, String str3, long j, AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError> asyncCallback) {
        b("loginMiByWXAccessToken accessToken " + str2 + " openId " + str3);
        a(i, LoginType.d, str, str2, str3, j, asyncCallback);
    }

    public static void a(int i, String str, String str2, long j, AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError> asyncCallback) {
        b("loginByFbAccessToken accessToken " + str2);
        a(i, LoginType.j, str, str2, "", j, asyncCallback);
    }

    public static Locale a() {
        if (Build.VERSION.SDK_INT >= 24) {
            return Resources.getSystem().getConfiguration().getLocales().get(0);
        }
        return Locale.getDefault();
    }

    private static String a(Locale locale) {
        if (locale == null) {
            return "";
        }
        String language = locale.getLanguage();
        if (TextUtils.equals("iw", language)) {
            language = "he";
        } else if (TextUtils.equals("ji", language)) {
            language = "yi";
        } else if (TextUtils.equals("in", language)) {
            language = "id";
        }
        String country = locale.getCountry();
        if (TextUtils.isEmpty(country)) {
            return language;
        }
        return language + JSMethod.NOT_SET + country;
    }

    private static void a(int i, String str, String str2, String str3, String str4, long j, AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError> asyncCallback) {
        int i2;
        final int i3;
        final int i4;
        int i5;
        String str5;
        Request request;
        String str6 = str;
        String str7 = str3;
        a(b, "account.xiaomi.com");
        ArrayList arrayList = new ArrayList();
        if (str.equals(LoginType.d)) {
            arrayList.add(new KeyValuePair("enToken", str3));
            arrayList.add(new KeyValuePair("openId", str4));
            arrayList.add(new KeyValuePair("expires_in", Long.toString(j)));
            arrayList.add(new KeyValuePair("_json", Boolean.toString(true)));
            str5 = MiLoginApi.a().l();
            i5 = LoginErrorCode.al;
            i4 = LoginErrorCode.ak;
            i3 = LoginErrorCode.an;
            i2 = LoginErrorCode.ao;
        } else {
            arrayList.add(new KeyValuePair("token", str3));
            arrayList.add(new KeyValuePair("expires_in", Long.toString(j)));
            arrayList.add(new KeyValuePair("_json", Boolean.toString(true)));
            arrayList.add(new KeyValuePair("_locale", a(a())));
            str5 = MiLoginApi.a().f();
            i5 = LoginErrorCode.au;
            i4 = LoginErrorCode.av;
            i3 = LoginErrorCode.ax;
            i2 = LoginErrorCode.ay;
        }
        String str8 = i == 0 ? ThirdPartUrlConstant.SmartHome.b : ThirdPartUrlConstant.YouPin.b;
        JSONObject jSONObject = new JSONObject();
        String str9 = str2;
        try {
            jSONObject.put("sid", str2);
            if (i != 0) {
                jSONObject.put("callback", URLEncoder.encode(str8, "UTF-8"));
            }
            jSONObject.put("appid", str5);
            arrayList.add(new KeyValuePair("state", HexUtil.a(jSONObject.toString().getBytes())));
            if (LoginType.d.equals(str)) {
                request = new Request.Builder().url(KeyValuePairUtil.a(ThirdPartUrlConstant.Passport.f23497a, (List<KeyValuePair>) arrayList)).addHeader(HttpHeaders.X_REQUESTED_WITH, e().getPackageName()).build();
            } else {
                request = new Request.Builder().url(KeyValuePairUtil.a(ThirdPartUrlConstant.Passport.b, (List<KeyValuePair>) arrayList)).addHeader(HttpHeaders.X_REQUESTED_WITH, e().getPackageName()).build();
            }
            final String str10 = str;
            final AsyncCallback<BaseAccount, LoginByThirdPartAccessTokenError> asyncCallback2 = asyncCallback;
            final int i6 = i5;
            final int i7 = i2;
            f().newCall(request).enqueue(new Callback() {
                public void onFailure(Call call, IOException iOException) {
                    LoginApiInternal.b(str10 + " onFailure" + iOException.getMessage());
                    if (asyncCallback2 != null) {
                        asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, iOException.getMessage()));
                    }
                }

                public void onResponse(Call call, Response response) throws IOException {
                    String value;
                    String value2;
                    List<NameValuePair> a2;
                    if (response == null) {
                        LoginApiInternal.b(str10 + " onFailure net response is null");
                        if (asyncCallback2 != null) {
                            asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "net response is null"));
                        }
                    } else if (!response.isSuccessful()) {
                        LoginApiInternal.b(str10 + " onFailure" + response.code());
                        if (asyncCallback2 != null) {
                            asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "AccessToken登录失败"));
                        }
                    } else {
                        try {
                            String string = response.body().string();
                            LoginApiInternal.b("responseStr " + string);
                            try {
                                JSONObject jSONObject = new JSONObject(string);
                                int optInt = jSONObject.optInt("Status");
                                String optString = jSONObject.optString("Sid");
                                String optString2 = jSONObject.optString("WebViewCallback");
                                String optString3 = jSONObject.optString("Callback");
                                jSONObject.optString("NotificationUrl");
                                LoginApiInternal.b("Status " + optInt + " Sid " + optString + " WebViewCallback " + optString2 + " Callback " + optString3);
                                String header = response.header("Date");
                                long currentTimeMillis = System.currentTimeMillis();
                                long j = 0;
                                try {
                                    j = LoginApiInternal.f.parse(header).getTime();
                                } catch (ParseException unused) {
                                }
                                final long j2 = j - currentTimeMillis;
                                YouPinCookieUtil.a(LoginApiInternal.g(), YouPinCookieManager.a());
                                if (optInt == 0) {
                                    HttpCookie a3 = YouPinCookieUtil.a(LoginApiInternal.g(), "userId");
                                    HttpCookie a4 = YouPinCookieUtil.a(LoginApiInternal.g(), "passToken");
                                    if (a3 == null) {
                                        value = "";
                                    } else {
                                        value = a3.getValue();
                                    }
                                    String str = value;
                                    if (a4 == null) {
                                        value2 = "";
                                    } else {
                                        value2 = a4.getValue();
                                    }
                                    String str2 = value2;
                                    String header2 = response.header("extension-pragma");
                                    try {
                                        URL url = new URL(optString3);
                                        String format = String.format("%s://%s%s", new Object[]{url.getProtocol(), url.getHost(), url.getPath()});
                                        try {
                                            a2 = URLEncodedUtils.a(new URI(optString3), "UTF-8");
                                        } catch (URISyntaxException e2) {
                                            LoginApiInternal.b(str10 + " onFailure " + e2.getMessage());
                                            if (asyncCallback2 != null) {
                                                asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, e2.getMessage()));
                                                return;
                                            }
                                            return;
                                        }
                                        try {
                                            if (!a2.isEmpty()) {
                                                ArrayList arrayList = new ArrayList();
                                                for (NameValuePair next : a2) {
                                                    arrayList.add(new KeyValuePair(next.a(), next.b()));
                                                }
                                                JSONObject jSONObject2 = new JSONObject(header2);
                                                long j3 = jSONObject2.getLong("nonce");
                                                final String string2 = jSONObject2.getString("ssecurity");
                                                arrayList.add(new KeyValuePair("clientSign", LoginApiInternal.b(Long.valueOf(j3), string2)));
                                                Request build = new Request.Builder().url(KeyValuePairUtil.a(format, (List<KeyValuePair>) arrayList)).build();
                                                final String str3 = str;
                                                final String str4 = str2;
                                                LoginApiInternal.f().newCall(build).enqueue(new Callback() {
                                                    public void onFailure(Call call, IOException iOException) {
                                                        LoginApiInternal.b(str10 + " onFailure " + iOException.getMessage());
                                                        if (asyncCallback2 != null) {
                                                            asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "callback fail"));
                                                        }
                                                    }

                                                    public void onResponse(Call call, Response response) throws IOException {
                                                        if (response.isSuccessful()) {
                                                            List<String> headers = response.headers("Set-Cookie");
                                                            if (headers != null) {
                                                                String str = "";
                                                                String str2 = "";
                                                                for (String split : headers) {
                                                                    HashMap hashMap = new HashMap();
                                                                    for (String str3 : split.split(i.b)) {
                                                                        int indexOf = str3.indexOf("=");
                                                                        hashMap.put(str3.substring(0, indexOf), str3.substring(indexOf + 1, str3.length()));
                                                                    }
                                                                    if (hashMap.containsKey("serviceToken")) {
                                                                        str2 = (String) hashMap.get("serviceToken");
                                                                        if (!TextUtils.isEmpty(str)) {
                                                                            break;
                                                                        }
                                                                    } else if (hashMap.containsKey("cUserId")) {
                                                                        str = (String) hashMap.get("cUserId");
                                                                        if (!TextUtils.isEmpty(str2)) {
                                                                            break;
                                                                        }
                                                                    } else {
                                                                        continue;
                                                                    }
                                                                }
                                                                if (TextUtils.isEmpty(str3) || TextUtils.isEmpty(str) || TextUtils.isEmpty(str4) || TextUtils.isEmpty(str2)) {
                                                                    LoginApiInternal.b(str10 + " onFailure passToken empty");
                                                                    if (asyncCallback2 != null) {
                                                                        asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "data empty"));
                                                                        return;
                                                                    }
                                                                    return;
                                                                }
                                                                BaseAccount baseAccount = new BaseAccount();
                                                                baseAccount.b = str;
                                                                baseAccount.f23409a = str3;
                                                                baseAccount.e = str4;
                                                                baseAccount.c = str2;
                                                                baseAccount.d = string2;
                                                                baseAccount.f = j2;
                                                                LoginApiInternal.b(str10 + " onSuccess");
                                                                if (asyncCallback2 != null) {
                                                                    asyncCallback2.b(baseAccount);
                                                                }
                                                            } else if (asyncCallback2 != null) {
                                                                asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "callback fail no serviceToken"));
                                                            }
                                                        } else if (asyncCallback2 != null) {
                                                            asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "callback fail response fail"));
                                                        }
                                                    }
                                                });
                                            } else if (asyncCallback2 != null) {
                                                asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "responseParams is empty"));
                                            }
                                        } catch (Exception e3) {
                                            LoginApiInternal.b(str10 + " onFailure " + e3.getMessage());
                                            if (asyncCallback2 != null) {
                                                asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "callback fail"));
                                            }
                                        }
                                    } catch (MalformedURLException e4) {
                                        LoginApiInternal.b(str10 + " onFailure " + e4.getMessage());
                                        if (asyncCallback2 != null) {
                                            asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, e4.getMessage()));
                                        }
                                    }
                                } else if (optInt != 1) {
                                    LoginApiInternal.b(str10 + " onFailure LoginMiByWXAccessTokenError)");
                                    if (asyncCallback2 != null) {
                                        AsyncCallback asyncCallback = asyncCallback2;
                                        int i = i3;
                                        asyncCallback.b(new LoginByThirdPartAccessTokenError(i, "status " + optInt));
                                    }
                                } else if (TextUtils.isEmpty(optString2)) {
                                    LoginApiInternal.b(str10 + " onFailure isEmpty(WebViewCallback)");
                                    if (asyncCallback2 != null) {
                                        asyncCallback2.b(new LoginByThirdPartAccessTokenError(i7, ""));
                                    }
                                } else {
                                    LoginByThirdPartAccessTokenError loginByThirdPartAccessTokenError = new LoginByThirdPartAccessTokenError(i4, "");
                                    loginByThirdPartAccessTokenError.f23523a = optInt;
                                    loginByThirdPartAccessTokenError.b = optString2;
                                    loginByThirdPartAccessTokenError.c = optString;
                                    loginByThirdPartAccessTokenError.d = j2;
                                    LoginApiInternal.b(str10 + " onFailure LoginMiByWXAccessTokenError:" + optInt);
                                    if (asyncCallback2 != null) {
                                        asyncCallback2.b(loginByThirdPartAccessTokenError);
                                    }
                                }
                            } catch (JSONException e5) {
                                LoginApiInternal.b(str10 + " onFailure " + e5.getMessage());
                                if (asyncCallback2 != null) {
                                    AsyncCallback asyncCallback2 = asyncCallback2;
                                    int i2 = i6;
                                    asyncCallback2.b(new LoginByThirdPartAccessTokenError(i2, "json exception " + e5.getMessage()));
                                }
                            }
                        } catch (Exception e6) {
                            Exception exc = e6;
                            LoginApiInternal.b(str10 + " onFailure " + exc.getMessage());
                            if (asyncCallback2 != null) {
                                asyncCallback2.b(new LoginByThirdPartAccessTokenError(i6, "AccessToken登录失败"));
                            }
                        }
                    }
                }
            });
        } catch (UnsupportedEncodingException | JSONException e2) {
            asyncCallback.b(new LoginByThirdPartAccessTokenError(i5, e2.getMessage()));
        }
    }

    public static AsyncHandle a(int i, @NonNull String str, final AsyncCallback<GetWXAccessTokenByAuthCodeResult, Error> asyncCallback) {
        String str2;
        b("getWXAccessTokenByAuthCode");
        YouPinCookieUtil.a(g());
        ArrayList arrayList = new ArrayList();
        if (i == 0) {
            str2 = ThirdPartUrlConstant.SmartHome.f23498a;
            arrayList.add(new KeyValuePair("code", str));
        } else {
            str2 = ThirdPartUrlConstant.YouPin.f23499a;
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("code", str);
            arrayList.add(new KeyValuePair("data", jsonObject.toString()));
        }
        Call newCall = f().newCall(new Request.Builder().url(KeyValuePairUtil.a(str2, (List<KeyValuePair>) arrayList)).build());
        if (i == 0) {
            newCall.enqueue(new MijiaWxAccessTokenRequestCall<MijiaWxAccessTokenByAuthCodeData>() {
                /* renamed from: b */
                public MijiaWxAccessTokenByAuthCodeData a(String str) {
                    try {
                        return (MijiaWxAccessTokenByAuthCodeData) new Gson().fromJson(str, MijiaWxAccessTokenByAuthCodeData.class);
                    } catch (Exception unused) {
                        return null;
                    }
                }

                public void a(MijiaWxAccessTokenByAuthCodeData mijiaWxAccessTokenByAuthCodeData) {
                    GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult = (GetWXAccessTokenByAuthCodeResult) mijiaWxAccessTokenByAuthCodeData.b;
                    if (getWXAccessTokenByAuthCodeResult == null) {
                        asyncCallback.b(new Error(LoginErrorCode.aj, "data is null"));
                        return;
                    }
                    String str = getWXAccessTokenByAuthCodeResult.f23524a;
                    String str2 = getWXAccessTokenByAuthCodeResult.b;
                    long j = getWXAccessTokenByAuthCodeResult.c;
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        LoginApiInternal.b("getWXAccessTokenByAuthCode succcess");
                        getWXAccessTokenByAuthCodeResult.f23524a = str;
                        getWXAccessTokenByAuthCodeResult.b = str2;
                        getWXAccessTokenByAuthCodeResult.c = j;
                        if (asyncCallback != null) {
                            asyncCallback.b(getWXAccessTokenByAuthCodeResult);
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.b(new Error(LoginErrorCode.aj, "accessToken or openId is empty"));
                    }
                }

                public void a(Error error) {
                    LoginApiInternal.b("getWXAccessTokenByAuthCode " + error.b());
                    if (asyncCallback != null) {
                        asyncCallback.b(error);
                    }
                }
            });
        } else {
            newCall.enqueue(new YoupinWxAccessTokenRequestCall<YoupinWxAccessTokenByAuthCodeData>() {
                /* renamed from: b */
                public YoupinWxAccessTokenByAuthCodeData a(String str) {
                    try {
                        return (YoupinWxAccessTokenByAuthCodeData) new Gson().fromJson(str, YoupinWxAccessTokenByAuthCodeData.class);
                    } catch (Exception unused) {
                        return null;
                    }
                }

                public void a(YoupinWxAccessTokenByAuthCodeData youpinWxAccessTokenByAuthCodeData) {
                    GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult = (GetWXAccessTokenByAuthCodeResult) youpinWxAccessTokenByAuthCodeData.b;
                    if (getWXAccessTokenByAuthCodeResult == null) {
                        asyncCallback.b(new Error(LoginErrorCode.aj, "data is null"));
                        return;
                    }
                    String str = getWXAccessTokenByAuthCodeResult.f23524a;
                    String str2 = getWXAccessTokenByAuthCodeResult.b;
                    long j = getWXAccessTokenByAuthCodeResult.c;
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        LoginApiInternal.b("getWXAccessTokenByAuthCode succcess");
                        getWXAccessTokenByAuthCodeResult.f23524a = str;
                        getWXAccessTokenByAuthCodeResult.b = str2;
                        getWXAccessTokenByAuthCodeResult.c = j;
                        if (asyncCallback != null) {
                            asyncCallback.b(getWXAccessTokenByAuthCodeResult);
                        }
                    } else if (asyncCallback != null) {
                        asyncCallback.b(new Error(LoginErrorCode.aj, "accessToken or openId is empty"));
                    }
                }

                public void a(Error error) {
                    LoginApiInternal.b("getWXAccessTokenByAuthCode " + error.b());
                    if (asyncCallback != null) {
                        asyncCallback.b(error);
                    }
                }
            });
        }
        return new AsyncHandle(newCall);
    }

    public static void a(String str, String str2, String str3, String str4, final AsyncCallback<Boolean, Error> asyncCallback) {
        a(d, e);
        YouPinCookieUtil.a(g(), d, "visitorSdkVersion", "-1", e, "/");
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("appId", str);
            jSONObject.put("openId", str4);
            jSONObject.put(OAuthAccountManager.MiOAuthConstant.TOKEN, str3);
            jSONObject.put("tid", "passportsns");
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        ArrayList arrayList = new ArrayList();
        arrayList.add(new KeyValuePair("visitorType", "5"));
        arrayList.add(new KeyValuePair("sid", str2));
        arrayList.add(new KeyValuePair("clientInfo", jSONObject.toString()));
        f().newCall(new Request.Builder().url("https://v.id.mi.com/visitor/check-association").addHeader(HttpHeaders.X_REQUESTED_WITH, e().getPackageName()).post(KeyValuePairUtil.c(arrayList)).build()).enqueue(new YoupinWxAccessTokenRequestCall<WxGuestVisitorInfoData>() {
            /* renamed from: b */
            public WxGuestVisitorInfoData a(String str) {
                try {
                    return (WxGuestVisitorInfoData) new Gson().fromJson(str, WxGuestVisitorInfoData.class);
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            public void a(WxGuestVisitorInfoData wxGuestVisitorInfoData) {
                boolean z = (wxGuestVisitorInfoData == null || wxGuestVisitorInfoData.b == null || "-1".equals(((WxGuestVisitorInfo) wxGuestVisitorInfoData.b).a())) ? false : true;
                if (asyncCallback != null) {
                    asyncCallback.b(Boolean.valueOf(z));
                }
            }

            public void a(Error error) {
                if (asyncCallback != null) {
                    asyncCallback.b(error);
                }
            }
        });
    }
}
