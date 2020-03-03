package com.mi.global.shop.xmsf.account;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mi.account.LoginManager;
import com.mi.account.util.Constants;
import com.mi.global.shop.R;
import com.mi.global.shop.ShopApp;
import com.mi.global.shop.locale.LocaleHelper;
import com.mi.global.shop.model.app.CookieInfo;
import com.mi.global.shop.model.app.LoginCallbackData;
import com.mi.global.shop.newmodel.domain.DomainModel;
import com.mi.global.shop.request.ExtendedAuthToken;
import com.mi.global.shop.util.Constants;
import com.mi.global.shop.util.DefaultDomain;
import com.mi.global.shop.util.Utils;
import com.mi.log.LogUtil;
import com.mi.util.Utils;
import com.squareup.wire.Wire;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;

public class LoginManager extends com.mi.account.LoginManager {
    protected static LoginManager p;
    private AccountManager q;

    public interface AccountListener extends LoginManager.AccountListener {
        void onUserInfoUpdate(String str, String str2, String str3, int i, String str4);
    }

    private LoginManager(Context context) {
        super(context);
        this.q = AccountManager.get(context);
    }

    public static void b(Context context) {
        com.mi.account.LoginManager.a(context);
        Constants.Account.d();
        if (!TextUtils.isEmpty(LocaleHelper.b) && !TextUtils.isEmpty(LocaleHelper.b(LocaleHelper.b))) {
            Constants.Account.f6727a = LocaleHelper.b(LocaleHelper.b);
        }
        if (p == null) {
            p = new LoginManager(context);
        }
    }

    public static LoginManager u() {
        return p;
    }

    public String e() {
        String e = super.e();
        if (e != null) {
            return e;
        }
        if (!Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return null;
        }
        String stringPref = Utils.Preference.getStringPref(this.b, "pref_system_extended_token", "");
        if (TextUtils.isEmpty(stringPref)) {
            String c = c(Constants.Account.b().c());
            if (ExtendedAuthToken.a(c) != null) {
                return ExtendedAuthToken.a(c).f6932a;
            }
            return null;
        } else if (ExtendedAuthToken.a(stringPref) != null) {
            return ExtendedAuthToken.a(stringPref).f6932a;
        } else {
            return null;
        }
    }

    public void a(boolean z, String str, com.xiaomi.accountsdk.account.data.ExtendedAuthToken extendedAuthToken) {
        if (this.b != null && !TextUtils.isEmpty(str) && extendedAuthToken != null) {
            if (z) {
                Utils.Preference.setBooleanPref(this.b, "pref_login_system", true);
                Utils.Preference.setStringPref(this.b, "pref_system_uid", str);
                Utils.Preference.setStringPref(this.b, "pref_system_extended_token", extendedAuthToken.toPlain());
                Utils.Preference.setStringPref(this.b, "pref_extended_token", extendedAuthToken.toPlain());
            } else {
                Utils.Preference.setStringPref(this.b, "pref_uid", str);
                Utils.Preference.setStringPref(this.b, "pref_extended_token", extendedAuthToken.toPlain());
            }
            a(str, extendedAuthToken.authToken, extendedAuthToken.security);
        }
    }

    public int v() {
        if (this.b == null) {
            return 0;
        }
        return Utils.Preference.getIntPref(this.b, Constants.Prefence.d, 0) + Utils.Preference.getIntPref(this.b, "pref_key_shoppingcart_number", 0);
    }

    public ArrayList<DomainModel> w() {
        ArrayList<DomainModel> arrayList;
        String stringPref = Utils.Preference.getStringPref(ShopApp.g(), Constants.Prefence.S, DefaultDomain.f7091a);
        if (TextUtils.isEmpty(stringPref)) {
            return null;
        }
        ArrayList<DomainModel> arrayList2 = new ArrayList<>();
        try {
            arrayList = (ArrayList) new Gson().fromJson(stringPref, new TypeToken<ArrayList<DomainModel>>() {
            }.getType());
        } catch (Exception unused) {
            arrayList = arrayList2;
        }
        if (arrayList != null) {
            return arrayList;
        }
        return null;
    }

    public boolean x() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            String stringPref = Utils.Preference.getStringPref(this.b, "pref_system_uid", "");
            String stringPref2 = Utils.Preference.getStringPref(this.b, "pref_system_extended_token", "");
            if (TextUtils.isEmpty(stringPref) || TextUtils.isEmpty(stringPref2)) {
                return false;
            }
            return true;
        }
        String stringPref3 = Utils.Preference.getStringPref(this.b, "pref_uid", "");
        String stringPref4 = Utils.Preference.getStringPref(this.b, "pref_extended_token", "");
        if (TextUtils.isEmpty(stringPref3) || TextUtils.isEmpty(stringPref4)) {
            return false;
        }
        return true;
    }

    public void a(String str, String str2, String str3) {
        if (LocaleHelper.j()) {
            y();
        }
        super.a(str, str2, str3);
        F();
    }

    public void y() {
        if (g()) {
            A();
        } else {
            z();
        }
    }

    public void z() {
        AccountInfo b = u().b(ShopApp.g().getString(R.string.mipay_sid));
        if (b != null) {
            a(b.serviceToken, b.getSlh(), b.getPh(), b.getEncryptedUserId());
        }
    }

    public void A() {
        String string = ShopApp.g().getString(R.string.mipay_sid);
        String b = u().b(string, false);
        LoginManager u = u();
        String d = u.d(string + "_slh");
        LoginManager u2 = u();
        String d2 = u2.d(string + "_ph");
        String d3 = u().d("encrypted_user_id");
        if (!TextUtils.isEmpty(d) && d.contains(",")) {
            d = d.split(",")[1];
        }
        if (!TextUtils.isEmpty(d2) && d2.contains(",")) {
            d2 = d2.split(",")[1];
        }
        a(b, d, d2, d3);
    }

    public void a(String str, String str2, String str3, String str4) {
        if (!TextUtils.isEmpty(str)) {
            Utils.Preference.setStringPref(ShopApp.g(), "pref_mipay_token", str);
            Utils.Preference.setStringPref(ShopApp.g(), "pref_mipay_slh", str2);
            Utils.Preference.setStringPref(ShopApp.g(), "pref_mipay_ph", str3);
            Utils.Preference.setStringPref(ShopApp.g(), "pref_mipay_cuid", str4);
        }
    }

    public String B() {
        return i() ? Utils.Preference.getStringPref(ShopApp.g(), "pref_mipay_token", "") : "";
    }

    public String C() {
        return i() ? Utils.Preference.getStringPref(ShopApp.g(), "pref_mipay_slh", "") : "";
    }

    public String D() {
        return i() ? Utils.Preference.getStringPref(ShopApp.g(), "pref_mipay_ph", "") : "";
    }

    public String E() {
        return i() ? Utils.Preference.getStringPref(ShopApp.g(), "pref_mipay_cuid", "") : "";
    }

    public void a(String str, String str2, String str3, int i, String str4) {
        if (c != null && !c.isEmpty()) {
            for (LoginManager.AccountListener accountListener : c) {
                LogUtil.b(com.xiaomi.youpin.login.api.manager.LoginManager.f23423a, "account listener:" + accountListener.toString());
                ((AccountListener) accountListener).onUserInfoUpdate(str, str2, str3, i, str4);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r1v4, types: [com.android.volley.Request] */
    /* JADX WARNING: type inference failed for: r9v1, types: [com.mi.global.shop.request.MiJsonObjectRequest] */
    /* JADX WARNING: type inference failed for: r3v1, types: [com.mi.global.shop.request.MiProtobufRequest] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void F() {
        /*
            r15 = this;
            java.util.HashMap r0 = new java.util.HashMap
            r0.<init>()
            java.lang.String r1 = com.mi.global.shop.util.ConnectionHelper.G
            java.lang.String r2 = "xmuuid"
            java.lang.String r1 = com.mi.global.shop.webview.WebViewCookieManager.a((java.lang.String) r1, (java.lang.String) r2)
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 != 0) goto L_0x001a
            java.lang.String r2 = "xmuuid"
            r0.put(r2, r1)
        L_0x001a:
            java.lang.String r1 = "security"
            java.lang.String r2 = "true"
            r0.put(r1, r2)
            boolean r1 = com.mi.global.shop.ShopApp.n()
            r2 = 1
            if (r1 == 0) goto L_0x0047
            com.mi.global.shop.request.MiProtobufRequest r1 = new com.mi.global.shop.request.MiProtobufRequest
            r4 = 1
            java.lang.String r5 = com.mi.global.shop.util.ConnectionHelper.aH()
            java.util.Map r0 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0, (boolean) r2)
            java.lang.String r6 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0)
            com.mi.global.shop.xmsf.account.LoginManager$2 r7 = new com.mi.global.shop.xmsf.account.LoginManager$2
            r7.<init>()
            com.mi.global.shop.xmsf.account.LoginManager$3 r8 = new com.mi.global.shop.xmsf.account.LoginManager$3
            r8.<init>()
            r3 = r1
            r3.<init>(r4, r5, r6, r7, r8)
            goto L_0x0064
        L_0x0047:
            com.mi.global.shop.request.MiJsonObjectRequest r1 = new com.mi.global.shop.request.MiJsonObjectRequest
            r10 = 1
            java.lang.String r11 = com.mi.global.shop.util.ConnectionHelper.aH()
            java.util.Map r0 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0, (boolean) r2)
            java.lang.String r12 = com.mi.global.shop.util.RequestUtil.a((java.util.Map<java.lang.String, java.lang.String>) r0)
            com.mi.global.shop.xmsf.account.LoginManager$4 r13 = new com.mi.global.shop.xmsf.account.LoginManager$4
            r13.<init>()
            com.mi.global.shop.xmsf.account.LoginManager$5 r14 = new com.mi.global.shop.xmsf.account.LoginManager$5
            r14.<init>()
            r9 = r1
            r9.<init>(r10, r11, r12, r13, r14)
        L_0x0064:
            java.lang.String r0 = "LoginManager"
            r1.setTag(r0)
            com.android.volley.RequestQueue r0 = com.mi.util.RequestQueueUtil.a()
            r0.add(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mi.global.shop.xmsf.account.LoginManager.F():void");
    }

    /* access modifiers changed from: private */
    public JSONArray a(LoginCallbackData loginCallbackData) {
        JSONArray jSONArray = new JSONArray();
        if (loginCallbackData == null || loginCallbackData.cookies == null) {
            return jSONArray;
        }
        for (int i = 0; i < loginCallbackData.cookies.size(); i++) {
            CookieInfo cookieInfo = loginCallbackData.cookies.get(i);
            JSONObject jSONObject = new JSONObject();
            try {
                jSONObject.put("key", Wire.get(cookieInfo.key, ""));
                jSONObject.put("value", Wire.get(cookieInfo.value, ""));
                jSONObject.put("domain", Wire.get(cookieInfo.domain, ""));
                jSONObject.put("path", Wire.get(cookieInfo.path, ""));
            } catch (Exception e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public String G() {
        if (Utils.Preference.getBooleanPref(this.b, "pref_login_system", false)) {
            return H();
        }
        return null;
    }

    @SuppressLint({"MissingPermission"})
    public String H() {
        Account[] accountsByType = this.q.getAccountsByType("com.xiaomi");
        if (accountsByType.length <= 0) {
            return null;
        }
        try {
            return this.q.getUserData(accountsByType[0], "acc_user_name");
        } catch (Exception unused) {
            return null;
        }
    }

    public void logout(LoginManager.LogoutCallback logoutCallback) {
        Utils.Preference.removePref(this.b, "pref_cache_service_token_list");
        Utils.Preference.removePref(this.b, Constants.Account.T);
        Utils.Preference.removePref(this.b, Constants.Account.W);
        Utils.Preference.removePref(this.b, "not_paid_order");
        Utils.Preference.removePref(this.b, "not_used_coupon");
        Utils.Preference.removePref(this.b, "pref_key_custom_cookies");
        Utils.Preference.removePref(this.b, "pref_key_shoppingcart_number");
        Utils.Preference.removePref(this.b, "pref_kefu_token");
        Utils.Preference.removePref(this.b, "pref_c_uid");
        Utils.Preference.removePref(this.b, "pref_system_c_uid");
        Utils.Preference.removePref(this.b, "pref_uid");
        Utils.Preference.removePref(this.b, "pref_extended_token");
        Utils.Preference.removePref(this.b, "pref_pass_token");
        Utils.Preference.removePref(this.b, "pref_system_uid");
        Utils.Preference.removePref(this.b, "pref_system_extended_token");
        Utils.Preference.removePref(this.b, "pref_key_user_ecryption_id");
        Utils.Preference.removePref(this.b, "pref_last_refresh_serviceToken_time");
        Utils.Preference.removePref(this.b, "pref_login_system");
    }

    public void I() {
        if (g()) {
            K();
        } else {
            J();
        }
    }

    public void J() {
        f(u().a(ShopApp.g().getString(R.string.kefu_sid), false));
    }

    public void K() {
        f(u().b(ShopApp.g().getString(R.string.kefu_sid), false));
    }

    public void f(String str) {
        if (!TextUtils.isEmpty(str)) {
            Utils.Preference.setStringPref(ShopApp.g(), "pref_kefu_token", str);
        }
    }

    public String L() {
        return x() ? Utils.Preference.getStringPref(ShopApp.g(), "pref_kefu_token", "") : "";
    }
}
