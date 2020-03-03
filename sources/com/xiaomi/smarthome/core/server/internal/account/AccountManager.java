package com.xiaomi.smarthome.core.server.internal.account;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.account.AccountType;
import com.xiaomi.smarthome.core.entity.account.OAuthAccount;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.util.LogUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.library.common.util.CommonUtil;
import com.xiaomi.smarthome.library.common.util.XMEncryptUtils;
import com.xiaomi.smarthome.library.crypto.Base64Coder;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import org.json.JSONObject;

public class AccountManager {

    /* renamed from: a  reason: collision with root package name */
    public static final String f14064a = "0";
    public static final String b = "com.xiaomi.smarthome.account";
    public static final String c = "mi_account";
    public static final String d = "mi_account_encrypt";
    public static final int e = 0;
    public static final int f = 1;
    public static final int g = 2;
    private static final String h = "oauth_account";
    private static final String i = "oauth_account_encrypt";
    private static AccountManager j;
    private static Object k = new Object();
    private boolean l = false;
    private SharedPreferences m;
    private Context n = CoreService.getAppContext();
    private AccountType o;
    private LoginMiAccount p;
    private OAuthAccount q;
    private volatile long r = 0;

    private AccountManager() {
        b();
    }

    public static AccountManager a() {
        if (j == null) {
            synchronized (k) {
                if (j == null) {
                    j = new AccountManager();
                }
            }
        }
        return j;
    }

    /* JADX WARNING: Removed duplicated region for block: B:37:0x00c3  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00ca  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void b() {
        /*
            r5 = this;
            java.lang.String r0 = "AccountManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "AccountManager init:mIsInitialized="
            r1.append(r2)
            boolean r2 = r5.l
            r1.append(r2)
            java.lang.String r2 = ",CoreService.getAppContext="
            r1.append(r2)
            android.content.Context r2 = com.xiaomi.smarthome.core.server.CoreService.getAppContext()
            r1.append(r2)
            java.lang.String r2 = ",mAppContext="
            r1.append(r2)
            android.content.Context r2 = r5.n
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r0, r1)
            java.lang.Object r0 = k
            monitor-enter(r0)
            boolean r1 = r5.l     // Catch:{ all -> 0x00d5 }
            boolean r2 = r5.l     // Catch:{ all -> 0x00d5 }
            if (r2 != 0) goto L_0x003a
            r2 = 1
            r5.l = r2     // Catch:{ all -> 0x00d5 }
        L_0x003a:
            monitor-exit(r0)     // Catch:{ all -> 0x00d5 }
            if (r1 == 0) goto L_0x003e
            return
        L_0x003e:
            android.content.Context r0 = r5.n
            if (r0 != 0) goto L_0x0048
            android.content.Context r0 = com.xiaomi.smarthome.core.server.CoreService.getAppContext()
            r5.n = r0
        L_0x0048:
            android.content.Context r0 = r5.n
            if (r0 != 0) goto L_0x004d
            return
        L_0x004d:
            android.content.Context r0 = r5.n
            java.lang.String r1 = "com.xiaomi.smarthome.account"
            r2 = 0
            android.content.SharedPreferences r0 = r0.getSharedPreferences(r1, r2)
            r5.m = r0
            java.lang.String r0 = "AccountManager"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "mAccountPrefs1:"
            r1.append(r2)
            android.content.SharedPreferences r2 = r5.m
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            com.xiaomi.smarthome.core.server.internal.util.LogUtil.a(r0, r1)
            java.lang.String r0 = ""
            android.content.Context r1 = r5.n     // Catch:{ Exception -> 0x008c }
            android.content.SharedPreferences r2 = r5.m     // Catch:{ Exception -> 0x008c }
            java.lang.String r3 = "mi_account_encrypt"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ Exception -> 0x008c }
            java.lang.String r1 = b(r1, r2)     // Catch:{ Exception -> 0x008c }
            java.lang.String r0 = "mi_account"
            java.lang.String r2 = "mi_account_encrypt"
            java.lang.String r0 = r5.a(r1, r0, r2)     // Catch:{ Exception -> 0x008b }
            goto L_0x008f
        L_0x008b:
            r0 = r1
        L_0x008c:
            r5.g()
        L_0x008f:
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = com.xiaomi.youpin.login.entity.account.LoginMiAccount.d(r0)
            r5.p = r0
            r0 = 0
            r5.r = r0
            java.lang.String r0 = ""
            android.content.Context r1 = r5.n     // Catch:{ Exception -> 0x00b5 }
            android.content.SharedPreferences r2 = r5.m     // Catch:{ Exception -> 0x00b5 }
            java.lang.String r3 = "oauth_account_encrypt"
            java.lang.String r4 = ""
            java.lang.String r2 = r2.getString(r3, r4)     // Catch:{ Exception -> 0x00b5 }
            java.lang.String r1 = b(r1, r2)     // Catch:{ Exception -> 0x00b5 }
            java.lang.String r0 = "oauth_account"
            java.lang.String r2 = "oauth_account_encrypt"
            java.lang.String r0 = r5.a(r1, r0, r2)     // Catch:{ Exception -> 0x00b4 }
            goto L_0x00b8
        L_0x00b4:
            r0 = r1
        L_0x00b5:
            r5.h()
        L_0x00b8:
            com.xiaomi.smarthome.core.entity.account.OAuthAccount r0 = com.xiaomi.smarthome.core.entity.account.OAuthAccount.a(r0)
            r5.q = r0
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r5.p
            r1 = 0
            if (r0 == 0) goto L_0x00ca
            com.xiaomi.smarthome.core.entity.account.AccountType r0 = com.xiaomi.smarthome.core.entity.account.AccountType.MI
            r5.o = r0
            r5.q = r1
            goto L_0x00d4
        L_0x00ca:
            com.xiaomi.smarthome.core.entity.account.OAuthAccount r0 = r5.q
            if (r0 == 0) goto L_0x00d4
            com.xiaomi.smarthome.core.entity.account.AccountType r0 = com.xiaomi.smarthome.core.entity.account.AccountType.OAUTH
            r5.o = r0
            r5.p = r1
        L_0x00d4:
            return
        L_0x00d5:
            r1 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x00d5 }
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.account.AccountManager.b():void");
    }

    private String a(String str, String str2, String str3) {
        SharedPreferences sharedPreferences = this.m;
        if (sharedPreferences != null && TextUtils.isEmpty(str)) {
            str = new String(Base64Coder.a(sharedPreferences.getString(str2, "")));
            if (!TextUtils.isEmpty(str)) {
                try {
                    SharedPreferences.Editor edit = sharedPreferences.edit();
                    edit.putString(str3, a(this.n, str));
                    edit.putString(str2, "");
                    edit.commit();
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
            }
        }
        return str;
    }

    public synchronized AccountType c() {
        return this.o;
    }

    public synchronized String d() {
        String str;
        str = "0";
        AccountType accountType = this.o;
        AccountType accountType2 = AccountType.MI;
        if (TextUtils.isEmpty(str)) {
            str = "0";
        }
        return str;
    }

    public synchronized void a(String str, String str2, String str3, String str4, long j2) {
        MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
        miServiceTokenInfo.f23514a = str;
        miServiceTokenInfo.c = str2;
        miServiceTokenInfo.d = str3;
        miServiceTokenInfo.f = str4;
        miServiceTokenInfo.e = j2;
        if (this.p != null) {
            this.p.a(miServiceTokenInfo);
        }
    }

    public synchronized MiServiceTokenInfo a(String str) {
        if (this.p == null) {
            return null;
        }
        return this.p.a(str);
    }

    public synchronized LoginMiAccount e() {
        return this.p;
    }

    public synchronized void a(LoginMiAccount loginMiAccount) {
        this.o = AccountType.MI;
        this.p = loginMiAccount;
        this.q = null;
        this.r = 0;
        UserDegreeManager.a().c();
    }

    public synchronized OAuthAccount f() {
        return this.q;
    }

    public synchronized void a(OAuthAccount oAuthAccount) {
        this.o = AccountType.OAUTH;
        this.p = null;
        this.q = oAuthAccount;
        this.r = 0;
        UserDegreeManager.a().c();
    }

    public synchronized void g() {
        SharedPreferences sharedPreferences = this.m;
        if (sharedPreferences == null) {
            LogUtilGrey.a("AccountManager", "clearMiAccount:mAccountPrefs is null!!");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(c);
        edit.remove(d);
        edit.commit();
        this.p = null;
        this.r = 0;
        UserDegreeManager.a().c();
    }

    public synchronized void h() {
        try {
            SharedPreferences sharedPreferences = this.m;
            if (sharedPreferences == null) {
                LogUtilGrey.a("AccountManager", "clearOAuthAccount:mAccountPrefs is null!!");
                return;
            }
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.remove(h);
            edit.remove(i);
            edit.commit();
            this.q = null;
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public synchronized void i() {
        SharedPreferences sharedPreferences = this.m;
        if (sharedPreferences == null) {
            LogUtilGrey.a("AccountManager", "clearAllAccount:mAccountPrefs is null!!");
            return;
        }
        SharedPreferences.Editor edit = sharedPreferences.edit();
        edit.remove(c);
        edit.remove(h);
        edit.remove(d);
        edit.remove(i);
        edit.commit();
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = 0;
        UserDegreeManager.a().c();
    }

    private static String a(Context context, String str) throws Exception {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return XMEncryptUtils.b(str, CommonUtil.a(context));
    }

    private static String b(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        String a2 = XMEncryptUtils.a(str, CommonUtil.a(context));
        if (TextUtils.isEmpty(a2)) {
            return null;
        }
        return a2;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0051, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void j() {
        /*
            r5 = this;
            monitor-enter(r5)
            android.content.SharedPreferences r0 = r5.m     // Catch:{ all -> 0x0052 }
            if (r0 != 0) goto L_0x000e
            java.lang.String r0 = "AccountManager"
            java.lang.String r1 = "saveAccount:mAccountPrefs is null!!"
            com.xiaomi.smarthome.framework.log.LogUtilGrey.a(r0, r1)     // Catch:{ all -> 0x0052 }
            monitor-exit(r5)
            return
        L_0x000e:
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r1 = r5.p     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x002f
            android.content.SharedPreferences$Editor r1 = r0.edit()     // Catch:{ all -> 0x0052 }
            java.lang.String r2 = "mi_account_encrypt"
            android.content.Context r3 = r5.n     // Catch:{ Exception -> 0x0028 }
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r4 = r5.p     // Catch:{ Exception -> 0x0028 }
            java.lang.String r4 = r4.e()     // Catch:{ Exception -> 0x0028 }
            java.lang.String r3 = a(r3, r4)     // Catch:{ Exception -> 0x0028 }
            r1.putString(r2, r3)     // Catch:{ Exception -> 0x0028 }
            goto L_0x002c
        L_0x0028:
            r2 = move-exception
            r2.printStackTrace()     // Catch:{ all -> 0x0052 }
        L_0x002c:
            r1.commit()     // Catch:{ all -> 0x0052 }
        L_0x002f:
            com.xiaomi.smarthome.core.entity.account.OAuthAccount r1 = r5.q     // Catch:{ all -> 0x0052 }
            if (r1 == 0) goto L_0x0050
            android.content.SharedPreferences$Editor r0 = r0.edit()     // Catch:{ all -> 0x0052 }
            java.lang.String r1 = "oauth_account_encrypt"
            android.content.Context r2 = r5.n     // Catch:{ Exception -> 0x0049 }
            com.xiaomi.smarthome.core.entity.account.OAuthAccount r3 = r5.q     // Catch:{ Exception -> 0x0049 }
            java.lang.String r3 = r3.a()     // Catch:{ Exception -> 0x0049 }
            java.lang.String r2 = a(r2, r3)     // Catch:{ Exception -> 0x0049 }
            r0.putString(r1, r2)     // Catch:{ Exception -> 0x0049 }
            goto L_0x004d
        L_0x0049:
            r1 = move-exception
            r1.printStackTrace()     // Catch:{ all -> 0x0052 }
        L_0x004d:
            r0.commit()     // Catch:{ all -> 0x0052 }
        L_0x0050:
            monitor-exit(r5)
            return
        L_0x0052:
            r0 = move-exception
            monitor-exit(r5)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.account.AccountManager.j():void");
    }

    public synchronized boolean k() {
        if (this.o != null) {
            return true;
        }
        return false;
    }

    public synchronized boolean l() {
        if (this.p != null) {
            return true;
        }
        return false;
    }

    public synchronized String m() {
        if (this.p == null) {
            return "0";
        }
        if (TextUtils.isEmpty(this.p.a())) {
            return "0";
        }
        return this.p.a();
    }

    public synchronized boolean n() {
        if (this.p == null) {
            return false;
        }
        return this.p.b();
    }

    public synchronized String o() {
        if (this.p == null) {
            return "";
        }
        return this.p.c();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x003a, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(java.lang.String r5) {
        /*
            r4 = this;
            monitor-enter(r4)
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r4.p     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x0039
            boolean r0 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x003b }
            if (r0 == 0) goto L_0x000c
            goto L_0x0039
        L_0x000c:
            com.xiaomi.youpin.login.entity.account.LoginMiAccount r0 = r4.p     // Catch:{ all -> 0x003b }
            com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo r5 = r0.a((java.lang.String) r5)     // Catch:{ all -> 0x003b }
            if (r5 == 0) goto L_0x0037
            android.content.Context r0 = r4.n     // Catch:{ all -> 0x003b }
            android.accounts.AccountManager r0 = android.accounts.AccountManager.get(r0)     // Catch:{ all -> 0x003b }
            java.lang.String r1 = "com.xiaomi"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x003b }
            r2.<init>()     // Catch:{ all -> 0x003b }
            java.lang.String r3 = r5.c     // Catch:{ all -> 0x003b }
            r2.append(r3)     // Catch:{ all -> 0x003b }
            java.lang.String r3 = ","
            r2.append(r3)     // Catch:{ all -> 0x003b }
            java.lang.String r5 = r5.d     // Catch:{ all -> 0x003b }
            r2.append(r5)     // Catch:{ all -> 0x003b }
            java.lang.String r5 = r2.toString()     // Catch:{ all -> 0x003b }
            r0.invalidateAuthToken(r1, r5)     // Catch:{ all -> 0x003b }
        L_0x0037:
            monitor-exit(r4)
            return
        L_0x0039:
            monitor-exit(r4)
            return
        L_0x003b:
            r5 = move-exception
            monitor-exit(r4)
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.smarthome.core.server.internal.account.AccountManager.b(java.lang.String):void");
    }

    public synchronized void p() {
        if (this.p != null) {
            for (MiServiceTokenInfo next : this.p.d()) {
                android.accounts.AccountManager accountManager = android.accounts.AccountManager.get(this.n);
                accountManager.invalidateAuthToken("com.xiaomi", next.c + "," + next.d);
            }
        }
    }

    public void a(JSONObject jSONObject) {
        if (jSONObject != null) {
            int optInt = jSONObject.optInt("debug_users", 0);
            this.r = (long) (((jSONObject.optInt("gray_users", 0) << 1) & 2) | (optInt & 1));
            LogUtil.a("UserDegree", "mUserDegree:" + this.r);
        }
    }

    public long q() {
        return this.r;
    }

    public boolean r() {
        return (this.p == null || (this.r & 1) == 0) ? false : true;
    }

    public boolean s() {
        return (this.p == null || (this.r & 2) == 0) ? false : true;
    }
}
