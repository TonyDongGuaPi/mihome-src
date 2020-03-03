package com.mi.global.bbs.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.JsonObject;
import com.mi.account.sdk.LoginManager;
import com.mi.global.bbs.BBSApplication;
import com.mi.global.bbs.http.ApiClient;
import com.mi.global.bbs.request.ExtendedAuthToken;
import com.mi.global.bbs.utils.Constants;
import com.mi.global.bbs.utils.Utils;
import com.mi.log.LogUtil;
import com.mi.util.AesEncryptionUtil;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.utils.AccountHelper;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import org.json.JSONObject;

public class LoginManager extends com.mi.account.sdk.LoginManager {
    private static com.mi.account.sdk.LoginManager loginManager;
    private AccountManager mAccountManager;

    public interface AccountListener extends LoginManager.AccountListener {
        void onUserInfoUpdate(String str, String str2, String str3, int i, int i2, int i3);
    }

    private LoginManager(Context context) {
        super(context);
        this.mAccountManager = AccountManager.get(context);
    }

    public static void init(Context context) {
        com.mi.account.sdk.LoginManager.init(context);
        Constants.Account.init();
        if (loginManager == null) {
            loginManager = new LoginManager(context);
        }
    }

    public static LoginManager getInstance() {
        return (LoginManager) loginManager;
    }

    public String getUserName() {
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            return getSystemAccountName();
        }
        return null;
    }

    public String getSystemAccountName() {
        Account[] accountsByType = this.mAccountManager.getAccountsByType("com.xiaomi");
        if (accountsByType.length > 0) {
            try {
                this.mAccountManager.getUserData(accountsByType[0], "acc_user_name");
            } catch (Exception unused) {
                return null;
            }
        }
        return null;
    }

    public void logout(LoginManager.LogoutCallback logoutCallback) {
        Utils.Preference.removePref(this.mContext, "pref_cache_service_token_list");
        Utils.Preference.removePref(this.mContext, Constants.Account.PREF_USER_NAMES);
        Utils.Preference.removePref(this.mContext, Constants.Account.PREF_USER_ICON);
        Utils.Preference.removePref(this.mContext, Constants.Account.PREF_USER_THREADS);
        Utils.Preference.removePref(this.mContext, Constants.Account.PREF_USER_REPLIES);
        Utils.Preference.removePref(this.mContext, Constants.Account.PREF_USER_CREDITS);
        Utils.Preference.removePref(this.mContext, "pref_key_custom_cookies");
        Utils.Preference.removePref(this.mContext, "pref_key_shoppingcart_number");
        Utils.Preference.removePref(this.mContext, Constants.Prefence.PREF_KEY_SHOP_TOKEN);
        super.logout(logoutCallback);
    }

    public String getServiceToken() {
        String serviceToken = super.getServiceToken();
        if (serviceToken != null) {
            return serviceToken;
        }
        if (Utils.Preference.getBooleanPref(this.mContext, "pref_login_system", false)) {
            String stringPref = Utils.Preference.getStringPref(this.mContext, "pref_system_extended_token", "");
            if (!TextUtils.isEmpty(stringPref) || ExtendedAuthToken.parse(stringPref) == null) {
                return null;
            }
            return ExtendedAuthToken.parse(stringPref).authToken;
        }
        return null;
    }

    public void onAccountLoginSucceed(String str, String str2, String str3) {
        super.onAccountLoginSucceed(str, str2, str3);
        loginCallback();
    }

    private void getShopToken() {
        String serviceTokenByServiceId = getInstance().getServiceTokenByServiceId("mi_mo_overseain");
        if (!TextUtils.isEmpty(serviceTokenByServiceId)) {
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Prefence.PREF_KEY_SHOP_TOKEN, serviceTokenByServiceId);
        }
    }

    public void loginCallback() {
        ApiClient.postLoginCallBack(getInstance().getUserId(), "true").subscribe(new Consumer<JsonObject>() {
            public void accept(@NonNull JsonObject jsonObject) throws Exception {
                LogUtil.b(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "LoginCallback jsonObject :" + jsonObject.toString());
                LoginManager.this.handleData(jsonObject);
            }
        }, new Consumer<Throwable>() {
            public void accept(@NonNull Throwable th) throws Exception {
                LogUtil.a(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "LoginCallback Throwable :" + th.toString());
                th.printStackTrace();
            }
        });
    }

    /* access modifiers changed from: private */
    public void handleData(JsonObject jsonObject) {
        try {
            JSONObject jSONObject = new JSONObject(jsonObject.toString());
            JSONObject jSONObject2 = null;
            if (jSONObject.has("data")) {
                if (!jSONObject.has("security") || !jSONObject.optBoolean("security")) {
                    jSONObject2 = jSONObject.optJSONObject("data");
                } else {
                    String b = AesEncryptionUtil.b(jSONObject.optString("data"));
                    if (b != null) {
                        jSONObject2 = new JSONObject(b);
                    }
                }
            }
            String optString = jSONObject2.optString("icon");
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_ICON, optString);
            String optString2 = jSONObject2.optString("username");
            Utils.Preference.setStringPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_NAMES, optString2);
            int optInt = jSONObject2.optInt("threads", 0);
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_THREADS, optInt);
            int optInt2 = jSONObject2.optInt("posts", 0);
            int i = optInt2 > optInt ? optInt2 - optInt : 0;
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_REPLIES, i);
            int optInt3 = jSONObject2.optInt("credits", 0);
            Utils.Preference.setIntPref(BBSApplication.getInstance(), Constants.Account.PREF_USER_CREDITS, optInt3);
            String optString3 = jSONObject2.optString("cookie");
            LogUtil.b(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "LoginCallback get cookies:" + optString3);
            Utils.Preference.setStringPref(BBSApplication.getInstance(), "pref_key_custom_cookies", optString3);
            getInstance().infoUserInfoUpdate(getInstance().getUserId(), optString2, optString, optInt, i, optInt3);
        } catch (Exception unused) {
        }
    }

    public void infoUserInfoUpdate(String str, String str2, String str3, int i, int i2, int i3) {
        if (mAccountLsteners != null && !mAccountLsteners.isEmpty()) {
            for (LoginManager.AccountListener accountListener : mAccountLsteners) {
                LogUtil.b(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "account listener:" + accountListener.toString());
                ((AccountListener) accountListener).onUserInfoUpdate(str, str2, str3, i, i2, i3);
            }
        }
    }

    private String getRealPasstoken(Account account) {
        ExtendedAuthToken parse;
        String password = MiAccountManager.get(this.mContext).getPassword(account);
        if (!TextUtils.isEmpty(password) && (parse = ExtendedAuthToken.parse(password)) != null) {
            return parse.authToken;
        }
        return null;
    }

    public String getServiceTokenByServiceId(String str) {
        AccountInfo serviceTokenByPassToken;
        try {
            Account xiaomiAccount = MiAccountManager.get(this.mContext).getXiaomiAccount();
            if (xiaomiAccount == null) {
                return null;
            }
            String realPasstoken = getRealPasstoken(xiaomiAccount);
            LogUtil.b(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "passToken  loginSystem save :" + realPasstoken);
            if (TextUtils.isEmpty(realPasstoken) || (serviceTokenByPassToken = AccountHelper.getServiceTokenByPassToken(getUserId(), realPasstoken, str)) == null) {
                return null;
            }
            return serviceTokenByPassToken.getServiceToken();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
