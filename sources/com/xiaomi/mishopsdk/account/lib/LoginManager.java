package com.xiaomi.mishopsdk.account.lib;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.AsyncTask;
import android.text.TextUtils;
import com.mishopsdk.volley.toolbox.RequestFuture;
import com.xiaomi.mishopsdk.Listener.IShopAccountManager;
import com.xiaomi.mishopsdk.ShopApp;
import com.xiaomi.mishopsdk.fragment.BaseFragment;
import com.xiaomi.mishopsdk.io.http.HostManager;
import com.xiaomi.mishopsdk.io.http.RequestQueueManager;
import com.xiaomi.mishopsdk.util.Log;
import com.xiaomi.mishopsdk.util.PreferenceUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginManager {
    public static final long DAY_TIME_IN_MILLIS = 86400000;
    private static final String TAG = "ShopLoginManager";
    public static IShopAccountManager iShopAccountManager;
    private static LoginManager sLoginManager;
    private BroadcastReceiver mAccountChangedReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            if (TextUtils.equals(intent.getAction(), "android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED")) {
                int intExtra = intent.getIntExtra("extra_update_type", -1);
                if (TextUtils.equals(((Account) intent.getParcelableExtra("extra_account")).type, "com.xiaomi") && intExtra != 2 && intExtra == 1 && PreferenceUtil.getBooleanPref(LoginManager.this.mContext, AccountConstants.PREF_LOGIN_SYSTEM, false)) {
                    LoginManager.this.logout();
                    LoginManager.this.setSystemLogin(false);
                }
            }
        }
    };
    private HashSet<AccountListener> mAccountLsteners;
    /* access modifiers changed from: private */
    public Context mContext;
    private LoginCallback mLoginCallback;
    private LogoutCallback mLogoutCallback;

    public interface AccountListener {
        void onInvalidAuthonToken();

        void onLogin(String str, String str2, String str3);

        void onLogout();
    }

    public interface LoginCallback {
        void onLoginFailed(int i);

        void onLoginSucceed(String str, String str2, String str3);
    }

    public interface LogoutCallback {
        void onLogout();
    }

    private LoginManager(Context context) {
        this.mContext = context;
        this.mAccountLsteners = new HashSet<>();
        context.registerReceiver(this.mAccountChangedReceiver, new IntentFilter("android.accounts.LOGIN_ACCOUNTS_PRE_CHANGED"));
    }

    public void setIShopAccountManager(IShopAccountManager iShopAccountManager2) {
        iShopAccountManager = iShopAccountManager2;
    }

    /* access modifiers changed from: protected */
    public void finalize() throws Throwable {
        this.mContext.unregisterReceiver(this.mAccountChangedReceiver);
    }

    public static LoginManager getInstance() {
        if (sLoginManager == null) {
            sLoginManager = new LoginManager(ShopApp.instance);
        }
        return sLoginManager;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x001e, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void addLoginListener(com.xiaomi.mishopsdk.account.lib.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.HashSet<com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener> r0 = r1.mAccountLsteners     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x0010
            java.util.HashSet r0 = new java.util.HashSet     // Catch:{ all -> 0x001f }
            r0.<init>()     // Catch:{ all -> 0x001f }
            r1.mAccountLsteners = r0     // Catch:{ all -> 0x001f }
        L_0x0010:
            java.util.HashSet<com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener> r0 = r1.mAccountLsteners     // Catch:{ all -> 0x001f }
            boolean r0 = r0.contains(r2)     // Catch:{ all -> 0x001f }
            if (r0 != 0) goto L_0x001d
            java.util.HashSet<com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener> r0 = r1.mAccountLsteners     // Catch:{ all -> 0x001f }
            r0.add(r2)     // Catch:{ all -> 0x001f }
        L_0x001d:
            monitor-exit(r1)
            return
        L_0x001f:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.account.lib.LoginManager.addLoginListener(com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener):void");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x000f, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized void removeLoginListener(com.xiaomi.mishopsdk.account.lib.LoginManager.AccountListener r2) {
        /*
            r1 = this;
            monitor-enter(r1)
            if (r2 != 0) goto L_0x0005
            monitor-exit(r1)
            return
        L_0x0005:
            java.util.HashSet<com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener> r0 = r1.mAccountLsteners     // Catch:{ all -> 0x0010 }
            if (r0 == 0) goto L_0x000e
            java.util.HashSet<com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener> r0 = r1.mAccountLsteners     // Catch:{ all -> 0x0010 }
            r0.remove(r2)     // Catch:{ all -> 0x0010 }
        L_0x000e:
            monitor-exit(r1)
            return
        L_0x0010:
            r2 = move-exception
            monitor-exit(r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.mishopsdk.account.lib.LoginManager.removeLoginListener(com.xiaomi.mishopsdk.account.lib.LoginManager$AccountListener):void");
    }

    public void onAccountLoginSucceed(String str, String str2, String str3) {
        if (this.mLoginCallback != null) {
            this.mLoginCallback.onLoginSucceed(str, str2, str3);
        }
        if (this.mAccountLsteners != null && !this.mAccountLsteners.isEmpty()) {
            Iterator<AccountListener> it = this.mAccountLsteners.iterator();
            while (it.hasNext()) {
                it.next().onLogin(str, str2, str3);
            }
        }
    }

    public void onAccountLoginFailed(int i) {
        if (this.mLoginCallback != null) {
            this.mLoginCallback.onLoginFailed(i);
        }
    }

    private void onAccountLogout() {
        if (this.mLogoutCallback != null) {
            this.mLogoutCallback.onLogout();
        }
        if (this.mAccountLsteners != null && !this.mAccountLsteners.isEmpty()) {
            Iterator<AccountListener> it = this.mAccountLsteners.iterator();
            while (it.hasNext()) {
                it.next().onLogout();
            }
        }
    }

    public String getPrefUserId() {
        return PreferenceUtil.getStringPref(this.mContext, "shop_sdk_pref_uid", (String) null);
    }

    public String getPrefEncryptedUserId() {
        return PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_C_UID, (String) null);
    }

    public void setSystemLogin(boolean z) {
        PreferenceUtil.setBooleanPref(this.mContext, AccountConstants.PREF_LOGIN_SYSTEM, z);
    }

    public void login() {
        final String encryptedUserId = getEncryptedUserId();
        final String accountId = getAccountId();
        new AsyncTask<String, Void, ExtendedAuthToken>() {
            /* access modifiers changed from: protected */
            public ExtendedAuthToken doInBackground(String... strArr) {
                return LoginManager.this.getExtendedAuthToken("eshopmobile");
            }

            /* access modifiers changed from: protected */
            public void onPostExecute(ExtendedAuthToken extendedAuthToken) {
                if (extendedAuthToken != null) {
                    PreferenceUtil.setStringPref(LoginManager.this.mContext, AccountConstants.PREF_C_UID, encryptedUserId);
                    PreferenceUtil.setStringPref(LoginManager.this.mContext, "shop_sdk_pref_uid", accountId);
                    PreferenceUtil.setStringPref(LoginManager.this.mContext, AccountConstants.PREF_EXTENDED_TOKEN, extendedAuthToken.toPlain());
                    PreferenceUtil.setBooleanPref(LoginManager.this.mContext, AccountConstants.PREF_LOGIN_SYSTEM, false);
                    BaseFragment.setLoginCookies(ShopApp.instance);
                    LoginManager.this.onAccountLoginSucceed(accountId, extendedAuthToken.authToken, extendedAuthToken.security);
                }
            }
        }.execute(new String[0]);
    }

    public void loginSystem(String str) {
        if (!TextUtils.isEmpty(str)) {
            setSystemLogin(true);
            String accountId = getAccountId();
            String encryptedUserId = getEncryptedUserId();
            ExtendedAuthToken parse = ExtendedAuthToken.parse(str);
            if (parse != null) {
                PreferenceUtil.setStringPref(this.mContext, AccountConstants.PREF_C_UID, encryptedUserId);
                PreferenceUtil.setStringPref(this.mContext, "shop_sdk_pref_uid", accountId);
                PreferenceUtil.setStringPref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN, str);
                BaseFragment.setLoginCookies(ShopApp.instance);
                onAccountLoginSucceed(accountId, parse.authToken, parse.security);
            }
        }
    }

    public boolean hasLogin() {
        return !TextUtils.isEmpty(getPrefUserId()) && !TextUtils.isEmpty(PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN, ""));
    }

    public boolean hasSystemAccount() {
        return !TextUtils.isEmpty(getAccountId());
    }

    public String getAccountId() {
        try {
            if (iShopAccountManager != null) {
                return iShopAccountManager.getUserId();
            }
            Log.d(TAG, "getAccountId failed, the iShopAccountManager is null.");
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    public void onActivityResult(int i, int i2, Intent intent) {
        if (iShopAccountManager != null) {
            iShopAccountManager.onActivityResult(i, i2, intent);
        }
    }

    public String getEncryptedUserId() {
        try {
            if (iShopAccountManager != null) {
                return iShopAccountManager.getEncryptedUserId();
            }
            Log.d(TAG, "getEncryptedUserId failed, the iShopAccountManager is null.");
            return null;
        } catch (SecurityException unused) {
            Log.e("SecurityException", "is different than the aythenticator");
            return "";
        }
    }

    public String getPassToken() {
        if (!hasLogin() || PreferenceUtil.getBooleanPref(this.mContext, AccountConstants.PREF_LOGIN_SYSTEM, false)) {
            return null;
        }
        return PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_PASS_TOKEN, "");
    }

    public void invalidAuthToken() {
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN);
        if (this.mAccountLsteners != null && !this.mAccountLsteners.isEmpty()) {
            Iterator<AccountListener> it = this.mAccountLsteners.iterator();
            while (it.hasNext()) {
                it.next().onInvalidAuthonToken();
            }
        }
    }

    public ExtendedAuthToken getExtendedAuthToken(String str) {
        String str2;
        if (TextUtils.equals(str, "eshopmobile")) {
            str2 = PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN, "");
            if (TextUtils.isEmpty(str2)) {
                str2 = getAccountAuthToken(str);
            }
        } else {
            str2 = getAccountAuthToken(str);
        }
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        return ExtendedAuthToken.parse(str2);
    }

    public String getAccountAuthToken(String str) {
        if (iShopAccountManager != null) {
            return iShopAccountManager.getAccountAuthToken(str);
        }
        Log.d(TAG, "getAccountAuthToken failed, the iShopAccountManager is null.");
        return null;
    }

    public void removeLoginCallback() {
        this.mLoginCallback = null;
    }

    public void logout() {
        logout((LogoutCallback) null);
    }

    public void logout(LogoutCallback logoutCallback) {
        if (iShopAccountManager == null) {
            Log.d(TAG, "logout failed, the iShopAccountManager is null.");
            return;
        }
        this.mLogoutCallback = logoutCallback;
        iShopAccountManager.invalidateAuthToken("com.xiaomi", PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN, ""));
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_C_UID);
        PreferenceUtil.removePref(this.mContext, "shop_sdk_pref_uid");
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_EXTENDED_TOKEN);
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_PASS_TOKEN);
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_LOGIN_SYSTEM);
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_KEY_ENCRYTION_USER_ID);
        PreferenceUtil.removePref(this.mContext, AccountConstants.PREF_CACHE_SERVICE_TOKEN_LIST);
        BaseFragment.removeLoginCookies(ShopApp.instance);
        onAccountLogout();
    }

    public Map<String, ExtendedAuthToken> getWebRequiredCachedServiceTokens() {
        HashMap hashMap;
        JSONArray jSONArray;
        String stringPref = PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_CACHE_SERVICE_TOKEN_LIST, "");
        try {
            if (TextUtils.isEmpty(stringPref)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(stringPref);
            }
            hashMap = new HashMap();
            int i = 0;
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i);
                    hashMap.put(jSONObject.optString("host"), ExtendedAuthToken.parse(jSONObject.optString("serviceToken")));
                    i++;
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    return hashMap;
                }
            }
        } catch (JSONException e2) {
            e = e2;
            hashMap = null;
            e.printStackTrace();
            return hashMap;
        }
        return hashMap;
    }

    public Map<String, ExtendedAuthToken> getWebRequiredServiceTokens() {
        HashMap hashMap;
        JSONArray jSONArray;
        try {
            String stringPref = PreferenceUtil.getStringPref(this.mContext, AccountConstants.PREF_CACHE_SERVICE_TOKEN_LIST, "");
            if (TextUtils.isEmpty(stringPref)) {
                jSONArray = new JSONArray();
            } else {
                jSONArray = new JSONArray(stringPref);
            }
            Map<String, String> sidsMap = getSidsMap();
            int i = 0;
            if (sidsMap != null) {
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                    JSONObject jSONObject = (JSONObject) jSONArray.get(i2);
                    arrayList.add(jSONObject.optString("sid"));
                    if (!arrayList2.contains(jSONObject.toString())) {
                        arrayList2.add(jSONObject.toString());
                    }
                }
                for (int i3 = 0; i3 < arrayList.size(); i3++) {
                    if (!sidsMap.containsValue(arrayList.get(i3)) && !TextUtils.isEmpty((CharSequence) arrayList.get(i3))) {
                        int i4 = 0;
                        while (true) {
                            if (i4 >= arrayList2.size()) {
                                break;
                            }
                            JSONObject jSONObject2 = new JSONObject((String) arrayList2.get(i4));
                            if (TextUtils.equals((CharSequence) arrayList.get(i3), jSONObject2.optString("sid"))) {
                                BaseFragment.removeCookie(this.mContext, "serviceToken", jSONObject2.optString("host"));
                                arrayList2.remove(i4);
                                break;
                            }
                            i4++;
                        }
                    }
                }
                JSONArray jSONArray2 = new JSONArray(arrayList2.toString());
                for (Map.Entry next : sidsMap.entrySet()) {
                    if (arrayList.contains(next.getValue())) {
                        int i5 = 0;
                        while (true) {
                            if (i5 >= jSONArray2.length()) {
                                break;
                            }
                            JSONObject jSONObject3 = (JSONObject) jSONArray2.get(i5);
                            String optString = jSONObject3.optString("sid");
                            String optString2 = jSONObject3.optString("serviceToken");
                            long optLong = jSONObject3.optLong("time");
                            if (!TextUtils.equals(optString, (CharSequence) next.getValue())) {
                                i5++;
                            } else if (System.currentTimeMillis() - optLong > 86400000 && iShopAccountManager != null) {
                                if (!TextUtils.isEmpty(optString2)) {
                                    iShopAccountManager.invalidateAuthToken("com.xiaomi", ExtendedAuthToken.parse(optString2).toPlain());
                                }
                                ExtendedAuthToken extendedAuthToken = getExtendedAuthToken((String) next.getValue());
                                if (extendedAuthToken != null) {
                                    jSONObject3.put("serviceToken", extendedAuthToken.toPlain());
                                    jSONObject3.put("host", next.getKey());
                                    jSONObject3.put("time", System.currentTimeMillis());
                                } else {
                                    ExtendedAuthToken extendedAuthToken2 = getExtendedAuthToken((String) next.getValue());
                                    if (extendedAuthToken2 != null) {
                                        jSONObject3.put("serviceToken", extendedAuthToken2.toPlain());
                                        jSONObject3.put("host", next.getKey());
                                        jSONObject3.put("time", System.currentTimeMillis());
                                    }
                                }
                            }
                        }
                    } else {
                        ExtendedAuthToken extendedAuthToken3 = getExtendedAuthToken((String) next.getValue());
                        if (extendedAuthToken3 != null) {
                            JSONObject jSONObject4 = new JSONObject();
                            jSONObject4.put("sid", next.getValue());
                            jSONObject4.put("host", next.getKey());
                            jSONObject4.put("serviceToken", extendedAuthToken3.toPlain());
                            jSONObject4.put("time", System.currentTimeMillis());
                            jSONArray2.put(jSONObject4);
                        } else {
                            ExtendedAuthToken extendedAuthToken4 = getExtendedAuthToken((String) next.getValue());
                            if (extendedAuthToken4 != null) {
                                JSONObject jSONObject5 = new JSONObject();
                                jSONObject5.put("sid", next.getValue());
                                jSONObject5.put("host", next.getKey());
                                jSONObject5.put("serviceToken", extendedAuthToken4.toPlain());
                                jSONObject5.put("time", System.currentTimeMillis());
                                jSONArray2.put(jSONObject5);
                            }
                        }
                    }
                }
                PreferenceUtil.setStringPref(this.mContext, AccountConstants.PREF_CACHE_SERVICE_TOKEN_LIST, jSONArray2.toString());
                jSONArray = jSONArray2;
            }
            hashMap = new HashMap();
            while (i < jSONArray.length()) {
                try {
                    JSONObject jSONObject6 = (JSONObject) jSONArray.get(i);
                    hashMap.put(jSONObject6.optString("host"), ExtendedAuthToken.parse(jSONObject6.optString("serviceToken")));
                    i++;
                } catch (JSONException e) {
                    e = e;
                    e.printStackTrace();
                    return hashMap;
                }
            }
        } catch (JSONException e2) {
            e = e2;
            hashMap = null;
            e.printStackTrace();
            return hashMap;
        }
        return hashMap;
    }

    private Map<String, String> getSidsMap() {
        RequestFuture newFuture = RequestFuture.newFuture();
        RequestQueueManager instance = RequestQueueManager.getInstance();
        instance.postApiRequest((Object) null, HostManager.FORMAL_DOMAIN_APP_SHOPAPI + "eshop/list", (HashMap<String, String>) null, JSONObject.class, false, newFuture);
        HashMap hashMap = null;
        try {
            JSONObject jSONObject = (JSONObject) newFuture.get();
            if (jSONObject != null) {
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    if (hashMap == null) {
                        hashMap = new HashMap();
                    }
                    String next = keys.next();
                    if (!TextUtils.equals("eshopmobile", next)) {
                        hashMap.put(jSONObject.optString(next), next);
                    }
                }
            }
        } catch (InterruptedException | ExecutionException unused) {
        }
        return hashMap;
    }
}
