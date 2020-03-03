package com.xiaomi.smarthome.miio.miband.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.smarthome.application.SHApplication;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.framework.api.AsyncResponseCallback;
import org.json.JSONException;
import org.json.JSONObject;

public class AccessManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f19471a = "oauth.2.0.access.info";
    public static final String b = "access.token";
    public static final String c = ".last.user.id";
    public static final String d = "user_id";
    public static final String e = "access_token";
    public static final String f = "mac_key";
    public static final String g = "mac_algorithm";
    public static final String h = "expires";
    public static final String i = "get_token_time";
    public static final int j = 24577;
    public static final int k = 24578;
    public static final int l = 37121;
    private static volatile AccessManager p;
    /* access modifiers changed from: private */
    public AccessInfo m;
    /* access modifiers changed from: private */
    public boolean n = false;
    /* access modifiers changed from: private */
    public volatile boolean o = false;

    public class AccessInfo implements Cloneable {

        /* renamed from: a  reason: collision with root package name */
        public String f19474a;
        public String b;
        public String c;
        /* access modifiers changed from: private */
        public long e = 0;
        /* access modifiers changed from: private */
        public long f = 0;
        /* access modifiers changed from: private */
        public String g;

        public AccessInfo() {
        }

        public Object clone() {
            try {
                return (AccessInfo) super.clone();
            } catch (CloneNotSupportedException unused) {
                return null;
            }
        }
    }

    public String a() {
        if (a(this.m)) {
            return this.m.c;
        }
        return null;
    }

    public String b() {
        String s = CoreApi.a().s();
        if (s == null || !a(this.m) || !s.equalsIgnoreCase(this.m.g)) {
            return null;
        }
        return this.m.b;
    }

    public static AccessManager c() {
        if (p == null) {
            synchronized (AccessManager.class) {
                p = new AccessManager();
            }
        }
        return p;
    }

    public void a(final Activity activity, final AsyncResponseCallback<AccessInfo> asyncResponseCallback) {
        if (!this.o) {
            this.o = true;
            final String s = CoreApi.a().s();
            if (s == null || s.isEmpty()) {
                if (asyncResponseCallback != null) {
                    asyncResponseCallback.a((int) j);
                }
                this.o = false;
            } else if (!a(this.m) || !this.m.g.equalsIgnoreCase(s)) {
                OAuthUtil.a(activity, (AsyncResponseCallback<XiaomiOAuthResults>) new AsyncResponseCallback<XiaomiOAuthResults>() {
                    public void a(XiaomiOAuthResults xiaomiOAuthResults) {
                        int errorCode = xiaomiOAuthResults.getErrorCode();
                        if (errorCode != 0) {
                            if (asyncResponseCallback != null) {
                                asyncResponseCallback.a(errorCode);
                            }
                            boolean unused = AccessManager.this.o = false;
                            return;
                        }
                        if (AccessManager.this.m == null) {
                            AccessInfo unused2 = AccessManager.this.m = new AccessInfo();
                        }
                        long unused3 = AccessManager.this.m.e = System.currentTimeMillis() / 1000;
                        long unused4 = AccessManager.this.m.f = Long.parseLong(xiaomiOAuthResults.getExpiresIn());
                        AccessManager.this.m.b = xiaomiOAuthResults.getAccessToken();
                        AccessManager.this.m.c = xiaomiOAuthResults.getMacKey();
                        AccessManager.this.m.f19474a = xiaomiOAuthResults.getMacAlgorithm();
                        final AccessInfo accessInfo = (AccessInfo) AccessManager.this.m.clone();
                        OAuthUtil.a(activity, AccessManager.this.m.b, AccessManager.this.m.c, AccessManager.this.m.f19474a, new AsyncResponseCallback<String>() {
                            public void a(String str) {
                                try {
                                    JSONObject optJSONObject = new JSONObject(str).optJSONObject("data");
                                    if (optJSONObject != null) {
                                        String unused = AccessManager.this.m.g = optJSONObject.optString("userId", (String) null);
                                        String unused2 = accessInfo.g = AccessManager.this.m.g;
                                        Log.d("access user info id:", AccessManager.this.m.g);
                                        Log.d("app user info id:", s);
                                        if (accessInfo.g.equalsIgnoreCase(s)) {
                                            AccessManager.this.a((Context) activity, accessInfo);
                                            if (asyncResponseCallback != null) {
                                                boolean unused3 = AccessManager.this.n = true;
                                                asyncResponseCallback.a(accessInfo);
                                            }
                                            boolean unused4 = AccessManager.this.o = false;
                                            return;
                                        }
                                        if (asyncResponseCallback != null) {
                                            asyncResponseCallback.a((int) AccessManager.k);
                                        }
                                        boolean unused5 = AccessManager.this.o = false;
                                        return;
                                    }
                                    if (asyncResponseCallback != null) {
                                        asyncResponseCallback.a(-1);
                                    }
                                    boolean unused6 = AccessManager.this.o = false;
                                } catch (JSONException unused7) {
                                    if (asyncResponseCallback != null) {
                                        asyncResponseCallback.a(-1);
                                    }
                                }
                            }

                            public void a(int i) {
                                String unused = AccessManager.this.m.g = null;
                                if (asyncResponseCallback != null) {
                                    asyncResponseCallback.a(i);
                                }
                                boolean unused2 = AccessManager.this.o = false;
                            }

                            public void a(int i, Object obj) {
                                String unused = AccessManager.this.m.g = null;
                                if (asyncResponseCallback != null) {
                                    asyncResponseCallback.a(i);
                                }
                                boolean unused2 = AccessManager.this.o = false;
                            }
                        });
                    }

                    public void a(int i) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(i);
                        }
                        boolean unused = AccessManager.this.o = false;
                    }

                    public void a(int i, Object obj) {
                        if (asyncResponseCallback != null) {
                            asyncResponseCallback.a(i, obj);
                        }
                        boolean unused = AccessManager.this.o = false;
                    }
                });
            } else {
                if (asyncResponseCallback != null) {
                    this.n = true;
                    asyncResponseCallback.a(this.m);
                }
                this.o = false;
            }
        }
    }

    private boolean a(AccessInfo accessInfo) {
        return (accessInfo == null || accessInfo.b == null || accessInfo.e + accessInfo.f <= System.currentTimeMillis() / 1000) ? false : true;
    }

    /* access modifiers changed from: private */
    public void a(Context context, AccessInfo accessInfo) {
        SharedPreferences sharedPreferences;
        if (a(accessInfo) && (sharedPreferences = context.getSharedPreferences(f19471a, 0)) != null) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            String e2 = e();
            if (e2 != null) {
                edit.remove(b + accessInfo.g);
                edit.putString(b + accessInfo.g, e2);
                edit.apply();
            }
        }
    }

    private void a(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(f19471a, 0);
        if (sharedPreferences != null) {
            String s = CoreApi.a().s();
            a(sharedPreferences.getString(b + s, (String) null));
        }
    }

    private String e() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("access_token", this.m.b);
            jSONObject.put("mac_key", this.m.c);
            jSONObject.put("mac_algorithm", this.m.f19474a);
            jSONObject.put("expires", this.m.f);
            jSONObject.put("user_id", this.m.g);
            jSONObject.put(i, this.m.e);
            return jSONObject.toString();
        } catch (JSONException unused) {
            return null;
        }
    }

    private void a(String str) {
        if (str != null && !str.isEmpty()) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                if (this.m == null) {
                    this.m = new AccessInfo();
                    this.m.b = jSONObject.optString("access_token", (String) null);
                    this.m.c = jSONObject.optString("mac_key", (String) null);
                    this.m.f19474a = jSONObject.optString("mac_algorithm", (String) null);
                    long unused = this.m.f = jSONObject.optLong("expires", 0);
                    String unused2 = this.m.g = jSONObject.optString("user_id", (String) null);
                    long unused3 = this.m.e = jSONObject.optLong(i, 0);
                }
            } catch (JSONException unused4) {
            }
        }
    }

    private AccessManager() {
        a(SHApplication.getAppContext());
    }

    public boolean d() {
        return this.n;
    }
}
