package com.mi.account;

import android.accounts.Account;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import com.mi.account.db.DBContract;
import com.mi.account.util.Constants;
import com.mi.log.LogUtil;
import com.mi.util.Constants;
import com.mi.util.Utils;
import com.xiaomi.accountsdk.account.AccountIntent;
import com.xiaomi.accountsdk.account.IXiaomiAccountService;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ExtendedAuthToken;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.utils.AccountHelper;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import miuipub.content.ExtraIntent;

public class LoginManager {

    /* renamed from: a  reason: collision with root package name */
    protected static final String f6698a = "LoginManager";
    protected static Set<AccountListener> c = Collections.newSetFromMap(new ConcurrentHashMap());
    protected static LoginManager d = null;
    public static final int e = 1;
    public static final int f = 2;
    public static final int g = 3;
    public static final int h = 4;
    public static final int i = 5;
    public static final int j = 0;
    public static final int k = 1;
    public static final int l = 2;
    public static final int m = 3;
    public static final int n = 4;
    public static final int o = 5;
    protected Context b;
    private LoginCallback p;
    private LogoutCallback q;
    private MiAccountManager r;
    private BroadcastReceiver s = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED")) {
                int intExtra = intent.getIntExtra("extra_update_type", -1);
                if (TextUtils.equals(((Account) intent.getParcelableExtra("extra_account")).type, "com.xiaomi") && intExtra != 2 && intExtra == 1) {
                    Utils.Preference.removePref(LoginManager.this.b, "pref_system_uid");
                    Utils.Preference.removePref(LoginManager.this.b, "pref_system_extended_token");
                    if (Utils.Preference.getBooleanPref(LoginManager.this.b, "pref_login_system", false)) {
                        LoginManager.this.a(false);
                        LoginManager.this.h();
                    }
                }
            }
        }
    };

    public interface AccountListener {
        void onInvalidAuthonToken();

        void onLogin(String str, String str2, String str3);

        void onLogout();
    }

    public interface LoginCallback {
        void a(int i);

        void a(String str, String str2, String str3);
    }

    public interface LogoutCallback {
        void a();
    }

    private Map<String, String> u() {
        return null;
    }

    protected LoginManager(Context context) {
        this.b = context.getApplicationContext();
        context.registerReceiver(this.s, new IntentFilter("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"));
        this.r = MiAccountManager.get(this.b);
    }

    public MiAccountManager a() {
        return this.r;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.b.unregisterReceiver(this.s);
    }

    public static void a(Context context) {
        MiAccountManager.get(context);
        if (Constants.b) {
            URLs.setLocalUsePreview(context, true);
        } else {
            URLs.setLocalUsePreview(context, false);
        }
        Constants.Account.a();
        if (d == null) {
            d = new LoginManager(context);
        }
    }

    public static LoginManager b() {
        return d;
    }

    public String c() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return Utils.Preference.getStringPref(this.b, "pref_system_uid", (String) null);
        }
        return Utils.Preference.getStringPref(this.b, "pref_uid", (String) null);
    }

    public String d() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return Utils.Preference.getStringPref(this.b, "pref_system_c_uid", (String) null);
        }
        return Utils.Preference.getStringPref(this.b, "pref_c_uid", (String) null);
    }

    public String e() {
        String stringPref = Utils.Preference.getStringPref(this.b, "pref_extended_token", (String) null);
        if (ExtendedAuthToken.parse(stringPref) != null) {
            return ExtendedAuthToken.parse(stringPref).authToken;
        }
        return null;
    }

    public String f() {
        String stringPref = Utils.Preference.getStringPref(this.b, "pref_extended_token", (String) null);
        if (ExtendedAuthToken.parse(stringPref) != null) {
            return ExtendedAuthToken.parse(stringPref).security;
        }
        return null;
    }

    public ExtendedAuthToken a(String str) {
        String str2;
        if (!Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            str2 = TextUtils.equals(str, Constants.Account.b().c()) ? Utils.Preference.getStringPref(this.b, "pref_extended_token", "") : null;
        } else if (TextUtils.equals(str, Constants.Account.b().c())) {
            String stringPref = Utils.Preference.getStringPref(this.b, "pref_system_extended_token", "");
            str2 = TextUtils.isEmpty(stringPref) ? c(str) : stringPref;
        } else {
            str2 = c(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return ExtendedAuthToken.parse(str2);
    }

    public void a(boolean z) {
        Utils.Preference.setBooleanPref(this.b, "pref_login_system", z);
    }

    public boolean g() {
        return Utils.Preference.getBooleanPref(this.b, "pref_login_system", true);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0013, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void a(com.mi.account.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.Set<com.mi.account.LoginManager$AccountListener> r0 = c     // Catch:{ all -> 0x0014 }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x0014 }
            if (r0 != 0) goto L_0x0012
            java.util.Set<com.mi.account.LoginManager$AccountListener> r0 = c     // Catch:{ all -> 0x0014 }
            r0.add(r2)     // Catch:{ all -> 0x0014 }
        L_0x0012:
            monitor-exit(r1)
            return
        L_0x0014:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.LoginManager.a(com.mi.account.LoginManager$AccountListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void b(com.mi.account.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.Set<com.mi.account.LoginManager$AccountListener> r0 = c     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x000e
            java.util.Set<com.mi.account.LoginManager$AccountListener> r0 = c     // Catch:{ all -> 0x0010 }
            r0.remove(r2)     // Catch:{ all -> 0x0010 }
        L_0x000e:
            monitor-exit(r1)
            return
        L_0x0010:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.LoginManager.b(com.mi.account.LoginManager$AccountListener):void");
    }

    public void a(String str, String str2, String str3) {
        if (this.p != null) {
            this.p.a(str, str2, str3);
        }
        LogUtil.b("LoginManager", "mAccountLsteners:" + c + ",mAccountLsteners.size:" + c.size());
        if (c != null && !c.isEmpty()) {
            for (AccountListener onLogin : c) {
                onLogin.onLogin(str, str2, str3);
            }
        }
    }

    public void a(int i2) {
        if (this.p != null) {
            this.p.a(i2);
        }
        Log.d("LoginManager", "account login failed: " + i2);
    }

    /* access modifiers changed from: protected */
    public void h() {
        if (this.q != null) {
            this.q.a();
        }
        if (c != null && !c.isEmpty()) {
            for (AccountListener onLogout : c) {
                onLogout.onLogout();
            }
        }
        Log.d("LoginManager", "account has logout");
    }

    public boolean i() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return !TextUtils.isEmpty(k());
        }
        return !TextUtils.isEmpty(Utils.Preference.getStringPref(this.b, "pref_uid", "")) && !TextUtils.isEmpty(Utils.Preference.getStringPref(this.b, "pref_extended_token", ""));
    }

    public boolean j() {
        return !TextUtils.isEmpty(k());
    }

    public String k() {
        Account[] accountsByType = this.r.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            return accountsByType[0].name;
        }
        return null;
    }

    public String b(final boolean z) {
        final Account[] accountsByType = this.r.getAccountsByType("com.xiaomi");
        if (accountsByType.length <= 0) {
            return null;
        }
        try {
            String userData = this.r.getUserData(accountsByType[0], "encrypted_user_id");
            if (z) {
                Utils.Preference.setStringPref(this.b, "pref_system_c_uid", userData);
            } else {
                Utils.Preference.setStringPref(this.b, "pref_c_uid", userData);
            }
            return userData;
        } catch (SecurityException unused) {
            Intent intent = new Intent(ExtraIntent.V);
            intent.setPackage(AccountIntent.PACKAGE_XIAOMI_ACCOUNT);
            this.b.bindService(intent, new ServiceConnection() {
                public void onServiceDisconnected(ComponentName componentName) {
                }

                public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                    try {
                        String encryptedUserId = IXiaomiAccountService.Stub.asInterface(iBinder).getEncryptedUserId(accountsByType[0]);
                        if (z) {
                            Utils.Preference.setStringPref(LoginManager.this.b, "pref_system_c_uid", encryptedUserId);
                        } else {
                            Utils.Preference.setStringPref(LoginManager.this.b, "pref_c_uid", encryptedUserId);
                        }
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            }, 1);
            return null;
        }
    }

    public String l() {
        if (!i() || Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return null;
        }
        return Utils.Preference.getStringPref(this.b, "pref_pass_token", "");
    }

    public void m() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            Utils.Preference.removePref(this.b, "pref_extended_token");
        } else {
            Utils.Preference.removePref(this.b, "pref_system_extended_token");
        }
        if (c != null && !c.isEmpty()) {
            for (AccountListener onInvalidAuthonToken : c) {
                onInvalidAuthonToken.onInvalidAuthonToken();
            }
        }
    }

    public String a(String str, boolean z) {
        AccountInfo serviceTokenByPassToken;
        try {
            Account xiaomiAccount = this.r.getXiaomiAccount();
            if (xiaomiAccount != null) {
                String a2 = a(xiaomiAccount);
                LogUtil.b("LoginManager", "passToken  loginSystem save :" + a2);
                if (!TextUtils.isEmpty(a2) && (serviceTokenByPassToken = AccountHelper.getServiceTokenByPassToken(c(), a2, str)) != null) {
                    return serviceTokenByPassToken.getServiceToken();
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (z) {
                logout((LogoutCallback) null);
            }
        }
        return null;
    }

    public AccountInfo b(String str) {
        try {
            Account xiaomiAccount = this.r.getXiaomiAccount();
            if (xiaomiAccount == null) {
                return null;
            }
            String a2 = a(xiaomiAccount);
            LogUtil.b("LoginManager", "passToken  loginSystem save :" + a2);
            if (!TextUtils.isEmpty(a2)) {
                return AccountHelper.getServiceTokenByPassToken(c(), a2, str);
            }
            return null;
        } catch (Exception e2) {
            e2.printStackTrace();
            return null;
        }
    }

    private String a(Account account) {
        ExtendedAuthToken parse;
        String password = MiAccountManager.get(this.b).getPassword(account);
        if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
            return parse.authToken;
        }
        return null;
    }

    public String c(String str) {
        return b(str, true);
    }

    public String b(String str, boolean z) {
        Bundle result;
        try {
            AccountManagerFuture<Bundle> authToken = this.r.getAuthToken(this.r.getXiaomiAccount(), str, (Bundle) null, true, (AccountManagerCallback<Bundle>) null, (Handler) null);
            if (!(authToken == null || (result = authToken.getResult()) == null)) {
                a(result);
                String string = result.getString("authtoken");
                if (!TextUtils.isEmpty(string)) {
                    return string;
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            if (z) {
                logout((LogoutCallback) null);
            }
        }
        return null;
    }

    public String d(String str) {
        if (this.r.getXiaomiAccount() == null) {
            return "";
        }
        return this.r.getUserData(this.r.getXiaomiAccount(), str);
    }

    private void a(Bundle bundle) {
        for (String str : bundle.keySet()) {
            LogUtil.b("LoginManager", "bundle-key:" + str + ", value:" + bundle.get(str) + "|||||");
        }
    }

    public void n() {
        Bundle result;
        LogUtil.b("LoginManager", "local login");
        try {
            a(false);
            AccountManagerFuture<Bundle> authToken = this.r.getAuthToken(this.r.getXiaomiAccount(), Constants.Account.b().c(), (Bundle) null, (Activity) null, (AccountManagerCallback<Bundle>) null, (Handler) null);
            if (authToken != null && (result = authToken.getResult()) != null) {
                String string = result.getString("authAccount");
                String string2 = result.getString("authtoken");
                b(false);
                ExtendedAuthToken parse = ExtendedAuthToken.parse(string2);
                Utils.Preference.setStringPref(this.b, "pref_uid", string);
                Utils.Preference.setStringPref(this.b, "pref_extended_token", parse.toPlain());
                a(string, parse.authToken, parse.security);
            }
        } catch (OperationCanceledException e2) {
            e2.printStackTrace();
        } catch (IOException e3) {
            e3.printStackTrace();
        } catch (AuthenticatorException e4) {
            e4.printStackTrace();
        } catch (Exception e5) {
            e5.printStackTrace();
        }
    }

    public void e(String str) {
        if (!TextUtils.isEmpty(str)) {
            LogUtil.b("LoginManager", "system login");
            a(true);
            String k2 = k();
            ExtendedAuthToken parse = ExtendedAuthToken.parse(str);
            Utils.Preference.setStringPref(this.b, "pref_system_uid", k2);
            Utils.Preference.setStringPref(this.b, "pref_system_extended_token", str);
            Utils.Preference.setStringPref(this.b, "pref_extended_token", str);
            a(k2, parse.authToken, parse.security);
            try {
                String b2 = b(true);
                LogUtil.b("LoginManager", "system login userId:" + k2 + ",cUid:" + b2 + ",authToken:" + str);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
    }

    public void o() {
        this.p = null;
    }

    public void logout() {
        logout((LogoutCallback) null);
    }

    public void logout(LogoutCallback logoutCallback) {
        this.q = logoutCallback;
        Utils.Preference.removePref(this.b, "pref_c_uid");
        Utils.Preference.removePref(this.b, "pref_system_c_uid");
        Utils.Preference.removePref(this.b, "pref_uid");
        Utils.Preference.removePref(this.b, "pref_extended_token");
        Utils.Preference.removePref(this.b, "pref_pass_token");
        Utils.Preference.removePref(this.b, "pref_system_uid");
        Utils.Preference.removePref(this.b, "pref_system_extended_token");
        Utils.Preference.removePref(this.b, "pref_key_user_ecryption_id");
        Utils.Preference.removePref(this.b, "pref_last_refresh_serviceToken_time");
        p();
        Utils.Preference.removePref(this.b, "pref_login_system");
        h();
    }

    public void p() {
        if (g()) {
            this.r.setUseSystem();
        } else {
            this.r.setUseLocal();
        }
        try {
            q();
            a((AccountManagerCallback<Boolean>) new AccountManagerCallback<Boolean>() {
                public void run(AccountManagerFuture<Boolean> accountManagerFuture) {
                    try {
                        LogUtil.b("LoginManager", "future.getResult():" + accountManagerFuture.getResult().booleanValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, (Handler) null);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void a(AccountManagerCallback<Boolean> accountManagerCallback, Handler handler) {
        for (Account account : this.r.getAccounts()) {
            LogUtil.b("LoginManager", "removeXiaomiAccount:" + account.toString());
            if (TextUtils.equals(account.type, "com.xiaomi")) {
                this.r.removeAccount(account, accountManagerCallback, handler);
            }
        }
    }

    public void q() {
        String stringPref = Utils.Preference.getStringPref(this.b, "pref_extended_token", (String) null);
        LogUtil.b("LoginManager", "old extended token plain:" + stringPref);
        a().invalidateAuthToken("com.xiaomi", stringPref);
    }

    public String r() {
        MiAccountManager a2 = a();
        if (g()) {
            a2.setUseSystem();
        } else {
            a2.setUseLocal();
        }
        return b().c(Constants.Account.b().c());
    }

    public Map<String, ExtendedAuthToken> s() {
        Cursor query = this.b.getContentResolver().query(DBContract.DataStats.CONTENT_URI, new String[]{"stats"}, "type='service_token'", (String[]) null, (String) null);
        HashMap hashMap = null;
        if (query != null) {
            try {
                if (query.moveToFirst()) {
                    do {
                        String string = query.getString(0);
                        if (!TextUtils.isEmpty(string)) {
                            Pair<String, String> parseServiceToken = DBContract.DataMimeType.parseServiceToken(string);
                            if (hashMap == null) {
                                hashMap = new HashMap();
                            }
                            hashMap.put(parseServiceToken.first, ExtendedAuthToken.parse((String) parseServiceToken.second));
                            LogUtil.b("LoginManager", "The cached serviceToken is:" + ((String) parseServiceToken.second));
                        }
                    } while (query.moveToNext());
                }
            } finally {
                query.close();
            }
        }
        return hashMap;
    }

    public Map<String, ExtendedAuthToken> t() {
        Map<String, ExtendedAuthToken> s2 = s();
        if (s2 != null) {
            for (Map.Entry next : s2.entrySet()) {
                LogUtil.b("LoginManager", "Invalide serviceToken:" + next.getValue());
                this.r.invalidateAuthToken("com.xiaomi", ((ExtendedAuthToken) next.getValue()).toPlain());
            }
            s2.clear();
        }
        Map<String, String> u = u();
        if (u != null) {
            for (Map.Entry next2 : u.entrySet()) {
                ExtendedAuthToken a2 = a((String) next2.getValue());
                if (a2 != null) {
                    if (s2 == null) {
                        s2 = new HashMap<>();
                    }
                    s2.put(next2.getKey(), a2);
                    a((String) next2.getValue(), a2.toPlain());
                }
                StringBuilder sb = new StringBuilder();
                sb.append("The sid ");
                sb.append((String) next2.getValue());
                sb.append(" 's serviceToken is ");
                sb.append(a2 == null ? "null" : a2.authToken);
                LogUtil.b("LoginManager", sb.toString());
            }
        }
        return s2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0083 A[DONT_GENERATE] */
    /* JADX WARNING: Removed duplicated region for block: B:17:? A[RETURN, SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void a(java.lang.String r9, java.lang.String r10) {
        /*
            r8 = this;
            android.content.Context r0 = r8.b
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r2 = com.mi.account.db.DBContract.DataStats.CONTENT_URI
            r0 = 1
            java.lang.String[] r3 = new java.lang.String[r0]
            java.lang.String r0 = "_id"
            r7 = 0
            r3[r7] = r0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "type='service_token' AND stats LIKE '"
            r0.append(r4)
            r0.append(r9)
            java.lang.String r4 = "%'"
            r0.append(r4)
            java.lang.String r4 = r0.toString()
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            android.content.ContentValues r1 = new android.content.ContentValues
            r1.<init>()
            java.lang.String r2 = "stats"
            java.lang.String r9 = com.mi.account.db.DBContract.DataMimeType.formatServiceToken(r9, r10)
            r1.put(r2, r9)
            if (r0 == 0) goto L_0x006e
            int r9 = r0.getCount()     // Catch:{ all -> 0x006c }
            if (r9 <= 0) goto L_0x006e
            boolean r9 = r0.moveToFirst()     // Catch:{ all -> 0x006c }
            if (r9 == 0) goto L_0x0081
            long r9 = r0.getLong(r7)     // Catch:{ all -> 0x006c }
            android.content.Context r2 = r8.b     // Catch:{ all -> 0x006c }
            android.content.ContentResolver r2 = r2.getContentResolver()     // Catch:{ all -> 0x006c }
            android.net.Uri r3 = com.mi.account.db.DBContract.DataStats.CONTENT_URI     // Catch:{ all -> 0x006c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x006c }
            r4.<init>()     // Catch:{ all -> 0x006c }
            java.lang.String r5 = "_id="
            r4.append(r5)     // Catch:{ all -> 0x006c }
            r4.append(r9)     // Catch:{ all -> 0x006c }
            java.lang.String r9 = r4.toString()     // Catch:{ all -> 0x006c }
            r10 = 0
            r2.update(r3, r1, r9, r10)     // Catch:{ all -> 0x006c }
            goto L_0x0081
        L_0x006c:
            r9 = move-exception
            goto L_0x0087
        L_0x006e:
            java.lang.String r9 = "type"
            java.lang.String r10 = "service_token"
            r1.put(r9, r10)     // Catch:{ all -> 0x006c }
            android.content.Context r9 = r8.b     // Catch:{ all -> 0x006c }
            android.content.ContentResolver r9 = r9.getContentResolver()     // Catch:{ all -> 0x006c }
            android.net.Uri r10 = com.mi.account.db.DBContract.DataStats.CONTENT_URI     // Catch:{ all -> 0x006c }
            r9.insert(r10, r1)     // Catch:{ all -> 0x006c }
        L_0x0081:
            if (r0 == 0) goto L_0x0086
            r0.close()
        L_0x0086:
            return
        L_0x0087:
            if (r0 == 0) goto L_0x008c
            r0.close()
        L_0x008c:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.account.LoginManager.a(java.lang.String, java.lang.String):void");
    }
}
