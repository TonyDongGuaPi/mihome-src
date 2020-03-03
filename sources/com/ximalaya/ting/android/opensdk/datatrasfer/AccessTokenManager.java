package com.ximalaya.ting.android.opensdk.datatrasfer;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.httputil.BaseBuilder;
import com.ximalaya.ting.android.opensdk.httputil.BaseCall;
import com.ximalaya.ting.android.opensdk.httputil.BaseResponse;
import com.ximalaya.ting.android.opensdk.httputil.IHttpCallBack;
import com.ximalaya.ting.android.opensdk.httputil.XimalayaException;
import com.ximalaya.ting.android.opensdk.httputil.util.SignatureUtil;
import com.ximalaya.ting.android.opensdk.model.token.AccessToken;
import com.ximalaya.ting.android.opensdk.player.XmPlayerManager;
import com.ximalaya.ting.android.opensdk.util.SharedPreferencesUtil;
import com.ximalaya.ting.android.player.MD5;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessTokenManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f1861a = "simple_sso_code";
    public static final String b = "simple_redirect_uri";
    public static final String c = "refresh_token_pref";
    public static final String d = "xm_thirdUid";
    public static final String e = "xm_thirdToken";
    private static final String f = "access_token_pref";
    private static final String g = "expire_in_pref";
    private static final String h = "current_time_pref";
    private static final String i = "auth2uid";
    private static final String j = "client_id";
    private static final String k = "device_id";
    private static final String l = "nonce";
    private static final String m = "timestamp";
    private static final String n = "sig";
    private static final String o = "grant_type";
    private static final String p = "client_credentials";
    private static AccessTokenManager q;
    private AccessToken r;
    /* access modifiers changed from: private */
    public Context s;
    private CommonRequest.ITokenStateChange t;
    /* access modifiers changed from: private */
    public String u;
    /* access modifiers changed from: private */
    public String v;

    private AccessTokenManager() {
    }

    public static AccessTokenManager a() {
        if (q == null) {
            synchronized (AccessTokenManager.class) {
                if (q == null) {
                    q = new AccessTokenManager();
                }
            }
        }
        return q;
    }

    public static String a(int i2) {
        Random random = new Random();
        StringBuffer stringBuffer = new StringBuffer();
        for (int i3 = 0; i3 < i2; i3++) {
            stringBuffer.append("abcdefghijklmnopqrstuvwxyz0123456789".charAt(random.nextInt("abcdefghijklmnopqrstuvwxyz0123456789".length())));
        }
        return stringBuffer.toString();
    }

    public void a(Context context) {
        this.s = context.getApplicationContext();
    }

    public void b(Context context) {
        this.s = context.getApplicationContext();
        this.u = SharedPreferencesUtil.a(this.s).c(f1861a);
        this.v = SharedPreferencesUtil.a(this.s).c(b);
        f().a(SharedPreferencesUtil.a(this.s).c(d));
        f().b(SharedPreferencesUtil.a(this.s).c(e));
        if (!SharedPreferencesUtil.a(this.s).m(f) || !SharedPreferencesUtil.a(this.s).m(g)) {
            c();
            return;
        }
        f().c(SharedPreferencesUtil.a(this.s).c(f));
        f().a(SharedPreferencesUtil.a(this.s).b(g));
        f().b(SharedPreferencesUtil.a(this.s).b(h));
        f().d(SharedPreferencesUtil.a(this.s).c(i));
        f().e(SharedPreferencesUtil.a(this.s).c(c));
        if (TextUtils.isEmpty(this.r.c()) || a(this.r.d(), this.r.f())) {
            c();
        }
    }

    public void a(CommonRequest.ITokenStateChange iTokenStateChange) {
        this.t = iTokenStateChange;
    }

    private boolean a(long j2, long j3) {
        return (System.currentTimeMillis() - j3) / 1000 > j2;
    }

    public Map<String, String> b() throws XimalayaException {
        HashMap hashMap = new HashMap();
        hashMap.put("client_id", CommonRequest.a().f());
        hashMap.put("grant_type", p);
        hashMap.put("device_id", CommonRequest.a().k());
        hashMap.put(l, a(9));
        hashMap.put("timestamp", System.currentTimeMillis() + "");
        hashMap.put("sig", SignatureUtil.b(CommonRequest.a().e(), hashMap));
        return hashMap;
    }

    public void c() {
        boolean b2 = this.t != null ? this.t.b() : false;
        if (this.t != null && !b2) {
            n();
            this.t.c();
        } else if (this.t == null || !b2) {
            n();
            if (i()) {
                j();
                return;
            }
            Request.Builder builder = null;
            try {
                builder = BaseBuilder.b(DTransferConstants.aZ, a().b());
            } catch (XimalayaException unused) {
            }
            if (builder != null) {
                BaseCall.a().a(builder.build(), (IHttpCallBack) new IHttpCallBack() {
                    public void a(int i, String str) {
                    }

                    public void a(Response response) {
                        if (response.code() == 200) {
                            try {
                                JSONObject jSONObject = new JSONObject(response.body().string());
                                AccessTokenManager.this.a(jSONObject.optString("access_token"), jSONObject.optLong("expires_in"));
                            } catch (Throwable th) {
                                th.printStackTrace();
                            }
                        } else {
                            AccessTokenManager.this.n();
                        }
                    }
                });
            }
        }
    }

    public synchronized String d() {
        if (this.r != null) {
            if (!TextUtils.isEmpty(this.r.c())) {
                return this.r.c();
            }
        }
        return "" + System.currentTimeMillis();
    }

    /* access modifiers changed from: protected */
    public void a(String str, long j2) {
        n();
        if (!TextUtils.isEmpty(str) && j2 > 0) {
            f().c(str);
            f().a(j2);
            long currentTimeMillis = System.currentTimeMillis();
            f().b(currentTimeMillis);
            SharedPreferencesUtil.a(this.s).a(f, str);
            SharedPreferencesUtil.a(this.s).a(g, j2);
            SharedPreferencesUtil.a(this.s).a(h, currentTimeMillis);
            XmPlayerManager.a(this.s).a(f());
        }
    }

    public void a(String str, String str2, long j2, String str3) {
        if (!TextUtils.isEmpty(str) && j2 > 0 && !TextUtils.isEmpty(str3)) {
            f().c(str);
            f().a(j2);
            f().d(str3);
            f().e(str2);
            long currentTimeMillis = System.currentTimeMillis();
            f().b(currentTimeMillis);
            SharedPreferencesUtil.a(this.s).a(f, str);
            SharedPreferencesUtil.a(this.s).a(g, j2);
            SharedPreferencesUtil.a(this.s).a(h, currentTimeMillis);
            SharedPreferencesUtil.a(this.s).a(i, str3);
            SharedPreferencesUtil.a(this.s).a(c, str2);
            XmPlayerManager.a(this.s).a(f());
        }
    }

    public void a(String str, long j2, String str2, String str3) {
        SharedPreferencesUtil.a(this.s).a(e, str3);
        SharedPreferencesUtil.a(this.s).a(d, str2);
        f().b(str3);
        f().a(str2);
        a(str, (String) null, j2, str2);
    }

    @Deprecated
    public void a(String str, long j2, String str2) {
        a(str, (String) null, j2, str2);
    }

    public void a(AccessToken accessToken) {
        this.r = accessToken;
    }

    /* access modifiers changed from: private */
    public void n() {
        this.r = null;
        SharedPreferencesUtil.a(this.s).l(f);
        SharedPreferencesUtil.a(this.s).l(g);
        SharedPreferencesUtil.a(this.s).l(h);
        SharedPreferencesUtil.a(this.s).l(i);
        SharedPreferencesUtil.a(this.s).l(c);
    }

    public void a(final ILoginOutCallBack iLoginOutCallBack) {
        Request.Builder builder;
        try {
            builder = BaseBuilder.b(DTransferConstants.aZ, a().b());
        } catch (XimalayaException e2) {
            e2.printStackTrace();
            builder = null;
        }
        if (builder == null) {
            iLoginOutCallBack.a(-1, "初始化错误");
        } else {
            BaseCall.a().a(builder.build(), (IHttpCallBack) new IHttpCallBack() {
                public void a(int i, String str) {
                    iLoginOutCallBack.a(i, str);
                }

                public void a(Response response) {
                    if (response.code() == 200) {
                        try {
                            JSONObject jSONObject = new JSONObject(response.body().string());
                            AccessTokenManager.this.n();
                            String unused = AccessTokenManager.this.u = null;
                            String unused2 = AccessTokenManager.this.v = null;
                            SharedPreferencesUtil.a(AccessTokenManager.this.s).l(AccessTokenManager.f1861a);
                            SharedPreferencesUtil.a(AccessTokenManager.this.s).l(AccessTokenManager.b);
                            SharedPreferencesUtil.a(AccessTokenManager.this.s).l(AccessTokenManager.e);
                            SharedPreferencesUtil.a(AccessTokenManager.this.s).l(AccessTokenManager.d);
                            AccessTokenManager.this.a(jSONObject.optString("access_token"), jSONObject.optLong("expires_in"));
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    if (iLoginOutCallBack != null) {
                                        iLoginOutCallBack.a();
                                    }
                                }
                            });
                        } catch (Throwable th) {
                            th.printStackTrace();
                            new Handler(Looper.getMainLooper()).post(new Runnable() {
                                public void run() {
                                    iLoginOutCallBack.a(1007, "parse response json data error");
                                }
                            });
                        }
                    } else {
                        AccessTokenManager.this.n();
                        iLoginOutCallBack.a(response.code(), "http 请求出错");
                    }
                }
            });
        }
    }

    public String e() {
        return this.r != null ? this.r.e() : "";
    }

    public AccessToken f() {
        if (this.r != null) {
            return this.r;
        }
        AccessToken accessToken = new AccessToken();
        this.r = accessToken;
        return accessToken;
    }

    public Context g() {
        return this.s;
    }

    public void a(String str, String str2) {
        this.u = str;
        this.v = str2;
        SharedPreferencesUtil.a(this.s).a(f1861a, str);
        SharedPreferencesUtil.a(this.s).a(b, str2);
    }

    @Nullable
    public String h() {
        if (this.r != null) {
            return this.r.g();
        }
        return null;
    }

    public static boolean i() {
        return !TextUtils.isEmpty(a().m()) && !TextUtils.isEmpty(a().l());
    }

    public static void j() {
        HashMap hashMap = new HashMap();
        hashMap.put("grant_type", "token_exchange");
        long currentTimeMillis = System.currentTimeMillis();
        hashMap.put(l, MD5.a(currentTimeMillis + ""));
        hashMap.put("timestamp", currentTimeMillis + "");
        hashMap.put("third_uid", a().l());
        hashMap.put("third_token", a().m());
        try {
            hashMap.put("client_id", CommonRequest.a().f());
        } catch (XimalayaException e2) {
            e2.printStackTrace();
        }
        CommonRequest.b(DTransferConstants.cT, hashMap, new IDataCallBack<String>() {
            public void a(int i, String str) {
            }

            public void a(String str) {
                if (str != null) {
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        AccessTokenManager.a().a(jSONObject.optString("access_token"), jSONObject.optLong("expires_in"), AccessTokenManager.a().l(), AccessTokenManager.a().m());
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new CommonRequest.IRequestCallBack<String>() {
            /* renamed from: a */
            public String b(String str) throws Exception {
                return str;
            }
        });
    }

    public static void k() throws XimalayaException {
        Response response;
        try {
            HashMap hashMap = new HashMap();
            hashMap.put("grant_type", "token_exchange");
            long currentTimeMillis = System.currentTimeMillis();
            hashMap.put(l, MD5.a(currentTimeMillis + ""));
            hashMap.put("timestamp", currentTimeMillis + "");
            hashMap.put("third_uid", a().l());
            hashMap.put("third_token", a().m());
            try {
                hashMap.put("client_id", CommonRequest.a().f());
            } catch (XimalayaException e2) {
                e2.printStackTrace();
            }
            try {
                response = BaseCall.a().a(BaseBuilder.b(CommonRequest.d(DTransferConstants.cT), CommonRequest.a((Map<String, String>) hashMap), CommonRequest.a().e()).build());
            } catch (Exception e3) {
                e3.printStackTrace();
                response = null;
            }
            if (response == null) {
                throw XimalayaException.getExceptionByCode(1010);
            } else if (new BaseResponse(response).a() == 200) {
                try {
                    JSONObject jSONObject = new JSONObject(response.body().string());
                    a().a(jSONObject.optString("access_token"), jSONObject.optLong("expires_in"), a().l(), a().m());
                } catch (Exception unused) {
                    throw XimalayaException.getExceptionByCode(1009);
                }
            } else {
                throw XimalayaException.getExceptionByCode(1010);
            }
        } catch (Exception unused2) {
            throw XimalayaException.getExceptionByCode(1010);
        }
    }

    public String l() {
        return f().a();
    }

    public String m() {
        return f().b();
    }
}
