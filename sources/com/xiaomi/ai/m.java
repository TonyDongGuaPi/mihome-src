package com.xiaomi.ai;

import android.content.Context;
import android.content.SharedPreferences;
import com.miui.tsmclient.account.OAuthAccountManager;
import com.xiaomi.ai.mibrain.MibrainOauthManager;
import com.xiaomi.ai.mibrain.MibrainOauthManagerCallbacks;
import com.xiaomi.ai.mibrain.MibrainUtils;
import com.xiaomi.ai.utils.Log;
import com.xiaomi.ai.utils.n;
import com.xiaomi.jr.mipay.common.MipayConstants;
import com.xiaomi.verificationsdk.internal.Constants;
import org.json.JSONException;
import org.json.JSONObject;

public class m {
    /* access modifiers changed from: private */

    /* renamed from: a  reason: collision with root package name */
    public static String f9929a = "OauthPreference_xml";
    /* access modifiers changed from: private */
    public static String b = "MiAiOauthHelper";
    /* access modifiers changed from: private */
    public MiAiOauthCallbacks c;
    /* access modifiers changed from: private */
    public Context d;
    private MibrainOauthManager e;
    private volatile int f;
    private boolean g;
    private boolean h;
    private final Object i = new Object();
    private MibrainOauthManagerCallbacks j = new n(this);

    public static class a {

        /* renamed from: a  reason: collision with root package name */
        public String f9930a;
        public int b;
    }

    m(Context context, String str, String str2, String str3) {
        this.d = context;
        this.e = new MibrainOauthManager(this.j, str, str2, str3, b(a(context)));
    }

    public static String a(Context context) {
        return n.b(context);
    }

    public static String b(String str) {
        return MibrainUtils.base64(String.format("{\"d\":\"%s\"}", new Object[]{str}).getBytes());
    }

    private void h() {
        synchronized (this.i) {
            this.f++;
        }
    }

    private void i() {
        synchronized (this.i) {
            this.f--;
        }
    }

    private boolean j() {
        synchronized (this.i) {
            if (this.g) {
                return true;
            }
            if (this.f >= 0) {
                return false;
            }
            this.e.release();
            this.g = true;
            Log.b(b, "release MiAiOauthHelper ");
            return true;
        }
    }

    public int a(String str, String str2, String str3, int i2) {
        return this.e.setAnonymousAuth(str, str2, str3, i2);
    }

    public int a(String str, String str2, String str3, String str4, String str5, String str6) {
        return this.e.updateAnonymousArgs(str, str2, str3, str4, str5, str6);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0019, code lost:
        r2 = com.xiaomi.ai.m.class;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:11:0x001b, code lost:
        monitor-enter(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:?, code lost:
        r5 = r4.e.getAccessToken(r5);
        r0.b = r5.errorCode;
        r0.f9930a = r5.accessToken;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002a, code lost:
        monitor-exit(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x002b, code lost:
        r5 = r4.i;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x002d, code lost:
        monitor-enter(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        i();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0035, code lost:
        if (j() == false) goto L_0x0039;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x0037, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0038, code lost:
        return null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0039, code lost:
        monitor-exit(r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x003a, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.xiaomi.ai.m.a a(boolean r5) {
        /*
            r4 = this;
            com.xiaomi.ai.m$a r0 = new com.xiaomi.ai.m$a
            r0.<init>()
            java.lang.Object r1 = r4.i
            monitor-enter(r1)
            boolean r2 = r4.h     // Catch:{ all -> 0x0049 }
            if (r2 == 0) goto L_0x0041
            r4.h()     // Catch:{ all -> 0x0049 }
            boolean r2 = r4.j()     // Catch:{ all -> 0x0049 }
            r3 = 0
            if (r2 == 0) goto L_0x0018
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            return r3
        L_0x0018:
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            java.lang.Class<com.xiaomi.ai.m> r2 = com.xiaomi.ai.m.class
            monitor-enter(r2)
            com.xiaomi.ai.mibrain.MibrainOauthManager r1 = r4.e     // Catch:{ all -> 0x003e }
            com.xiaomi.ai.mibrain.MibrainOauthManager$AccessTokenResult r5 = r1.getAccessToken(r5)     // Catch:{ all -> 0x003e }
            int r1 = r5.errorCode     // Catch:{ all -> 0x003e }
            r0.b = r1     // Catch:{ all -> 0x003e }
            java.lang.String r5 = r5.accessToken     // Catch:{ all -> 0x003e }
            r0.f9930a = r5     // Catch:{ all -> 0x003e }
            monitor-exit(r2)     // Catch:{ all -> 0x003e }
            java.lang.Object r5 = r4.i
            monitor-enter(r5)
            r4.i()     // Catch:{ all -> 0x003b }
            boolean r1 = r4.j()     // Catch:{ all -> 0x003b }
            if (r1 == 0) goto L_0x0039
            monitor-exit(r5)     // Catch:{ all -> 0x003b }
            return r3
        L_0x0039:
            monitor-exit(r5)     // Catch:{ all -> 0x003b }
            return r0
        L_0x003b:
            r0 = move-exception
            monitor-exit(r5)     // Catch:{ all -> 0x003b }
            throw r0
        L_0x003e:
            r5 = move-exception
            monitor-exit(r2)     // Catch:{ all -> 0x003e }
            throw r5
        L_0x0041:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0049 }
            java.lang.String r0 = "MiAiOauthHelper is not init !"
            r5.<init>(r0)     // Catch:{ all -> 0x0049 }
            throw r5     // Catch:{ all -> 0x0049 }
        L_0x0049:
            r5 = move-exception
            monitor-exit(r1)     // Catch:{ all -> 0x0049 }
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.ai.m.a(boolean):com.xiaomi.ai.m$a");
    }

    public String a() {
        return this.d.getSharedPreferences(f9929a, 0).getString(b(a(this.d)), (String) null);
    }

    public String a(String str) {
        if (str == null) {
            return null;
        }
        String b2 = b(a(this.d));
        return this.d.getSharedPreferences(f9929a, 0).getString(String.format("%s:%s", new Object[]{b2, str}), (String) null);
    }

    public String a(String str, String str2, String str3) {
        return this.e.genAnonymousAuthorization(str, str2, str3);
    }

    public void a(int i2) {
        this.e.setOauthPt(i2);
    }

    public void a(MiAiOauthCallbacks miAiOauthCallbacks) {
        this.c = miAiOauthCallbacks;
    }

    public void a(MibrainOauthManager.updateTokenCallback updatetokencallback) {
        this.e.setUpdateTokenCallback(updatetokencallback);
    }

    public synchronized boolean a(int i2, String str, String str2, String str3, long j2) {
        if (str2 == null || str3 == null || j2 < 0) {
            Log.a(b, "setAuthorizationTokens: invalid parameters\n");
            return false;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put(OAuthAccountManager.MiOAuthConstant.TOKEN, str2);
            jSONObject.put("refreshToken", str3);
            jSONObject.put(MipayConstants.ao, Long.toString(j2 + (System.currentTimeMillis() / 1000)));
            String str4 = b(a(this.d)) + ":" + str;
            if (i2 != SpeechEngine.h) {
                if (i2 != SpeechEngine.g) {
                    this.j.putOauthData(b(a(this.d)), jSONObject.toString());
                    return true;
                }
            }
            if (str == null) {
                Log.a(b, "setAuthorizationTokens: clientId is null\n");
                return false;
            }
            this.j.putOauthData(str4, jSONObject.toString());
            return true;
        } catch (JSONException e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void b() {
        synchronized (this.i) {
            this.e.init();
            this.h = true;
            Log.b(b, "init MiAiOauthHelper ");
        }
    }

    public void b(int i2) {
        SharedPreferences sharedPreferences = this.d.getSharedPreferences(f9929a, 0);
        if (sharedPreferences.getInt(Constants.d, 0) != i2) {
            d();
            sharedPreferences.edit().putInt(Constants.d, i2).apply();
        }
        this.e.setOauthEnv(i2);
        String str = b;
        android.util.Log.e(str, "setEnv" + i2);
    }

    public void c() {
        synchronized (this.i) {
            i();
            j();
        }
    }

    public void c(int i2) {
        this.e.setNetTimeOut(i2);
    }

    public void c(String str) {
        if (str != null) {
            String str2 = b;
            Log.f(str2, "cleanOauthData clientId " + str);
            String b2 = b(a(this.d));
            SharedPreferences.Editor edit = this.d.getSharedPreferences(f9929a, 0).edit();
            edit.remove(String.format("%s:%s", new Object[]{b2, str}));
            edit.apply();
        }
    }

    public void d() {
        Log.f(b, "cleanOauthData");
        String b2 = b(a(this.d));
        SharedPreferences.Editor edit = this.d.getSharedPreferences(f9929a, 0).edit();
        edit.remove(b2);
        edit.apply();
    }

    public void e() {
        Log.f(b, "cleanAllOauthData");
        SharedPreferences.Editor edit = this.d.getSharedPreferences(f9929a, 0).edit();
        edit.clear();
        edit.apply();
    }
}
