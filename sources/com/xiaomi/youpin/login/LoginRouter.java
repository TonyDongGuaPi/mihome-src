package com.xiaomi.youpin.login;

import android.accounts.Account;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.facebook.AccessToken;
import com.google.android.exoplayer2.C;
import com.xiaomi.accountsdk.account.data.MetaLoginData;
import com.xiaomi.passport.servicetoken.data.XmAccountVisibility;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.third_part.ThirdPartUrlConstant;
import com.xiaomi.youpin.login.entity.wx.GetWXAccessTokenByAuthCodeResult;
import com.xiaomi.youpin.login.okhttpApi.api.AccessAccountCallback;
import com.xiaomi.youpin.login.other.common.HexUtil;
import com.xiaomi.youpin.login.other.http.KeyValuePair;
import com.xiaomi.youpin.login.other.http.KeyValuePairUtil;
import com.xiaomi.youpin.login.ui.LoginMiByDynamicTokenActivity;
import com.xiaomi.youpin.login.ui.LoginMiSafetyValidateActivity;
import com.xiaomi.youpin.login.ui.LoginSystemTransparentActivity;
import com.xiaomi.youpin.login.ui.web.LoginBindBaseWebActivity;
import com.xiaomi.youpin.login.ui.web.LoginFBBindMiWebActivity;
import com.xiaomi.youpin.login.ui.web.LoginWXBindMiWebActivity;
import com.xiaomi.youpin.login.ui.web.LoginWebActivity;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginRouter {
    public static void a(Context context, String str, String str2, MetaLoginData metaLoginData) {
        Intent intent = new Intent(context.getApplicationContext(), LoginMiByDynamicTokenActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(LoginMiByDynamicTokenActivity.BUNDLE_KEY_USERNAME, str);
        intent.putExtra(LoginMiByDynamicTokenActivity.BUNDLE_KEY_STEP1TOKEN, str2);
        intent.putExtra(LoginMiByDynamicTokenActivity.BUNDLE_KEY_META_LOGIN_DATA, metaLoginData);
        context.startActivity(intent);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context.getApplicationContext(), LoginMiSafetyValidateActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("common_web_url", str);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, String str2) {
        Intent intent = new Intent(context.getApplicationContext(), LoginWXBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("common_web_url", str);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_OPENID, str2);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, "login");
        context.startActivity(intent);
    }

    public static void a(Context context, int i, String str, String str2, GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult) {
        Intent intent = new Intent(context.getApplicationContext(), LoginWXBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", str);
            if (i == 0) {
                jSONObject.put("callback", ThirdPartUrlConstant.SmartHome.b);
            } else {
                jSONObject.put("callback", URLEncoder.encode(ThirdPartUrlConstant.YouPin.b, "UTF-8"));
            }
            jSONObject.put("appid", MiLoginApi.a().l());
        } catch (UnsupportedEncodingException | JSONException unused) {
        }
        arrayList.add(new KeyValuePair("state", HexUtil.a(jSONObject.toString().getBytes())));
        arrayList.add(new KeyValuePair("enToken", getWXAccessTokenByAuthCodeResult.f23524a));
        arrayList.add(new KeyValuePair("expires_in", Long.toString(getWXAccessTokenByAuthCodeResult.c)));
        arrayList.add(new KeyValuePair("openId", getWXAccessTokenByAuthCodeResult.b));
        arrayList.add(new KeyValuePair("userId", str2));
        intent.putExtra("common_web_url", KeyValuePairUtil.a(ThirdPartUrlConstant.Passport.c, (List<KeyValuePair>) arrayList));
        intent.putExtra(LoginBindBaseWebActivity.ARGS_OPENID, getWXAccessTokenByAuthCodeResult.b);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, LoginBindBaseWebActivity.SOURCE_BIND);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_USERID, str2);
        context.getApplicationContext().startActivity(intent);
    }

    public static void a(Context context, String str, String str2, GetWXAccessTokenByAuthCodeResult getWXAccessTokenByAuthCodeResult, String str3) {
        Intent intent = new Intent(context.getApplicationContext(), LoginWXBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_OPENID, getWXAccessTokenByAuthCodeResult.b);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, LoginBindBaseWebActivity.SOURCE_BIND);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_USERID, str);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_PASSTOKEN, str2);
        intent.putExtra("common_web_url", str3);
        context.getApplicationContext().startActivity(intent);
    }

    public static void b(Context context, String str) {
        Intent intent = new Intent(context.getApplicationContext(), LoginFBBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra("common_web_url", str);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, "login");
        context.startActivity(intent);
    }

    public static void a(Context context, int i, String str, String str2, AccessToken accessToken) {
        Intent intent = new Intent(context.getApplicationContext(), LoginFBBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        ArrayList arrayList = new ArrayList();
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("sid", str);
            if (i == 0) {
                jSONObject.put("callback", ThirdPartUrlConstant.SmartHome.b);
            } else {
                jSONObject.put("callback", URLEncoder.encode(ThirdPartUrlConstant.YouPin.b, "UTF-8"));
            }
            jSONObject.put("appid", MiLoginApi.a().f());
        } catch (UnsupportedEncodingException | JSONException e) {
            e.printStackTrace();
        }
        arrayList.add(new KeyValuePair("state", HexUtil.a(jSONObject.toString().getBytes())));
        arrayList.add(new KeyValuePair("token", accessToken.getToken()));
        arrayList.add(new KeyValuePair("expires_in", Long.toString(accessToken.getExpires().getTime())));
        arrayList.add(new KeyValuePair("userId", str2));
        intent.putExtra("common_web_url", KeyValuePairUtil.a(ThirdPartUrlConstant.Passport.d, (List<KeyValuePair>) arrayList));
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, LoginBindBaseWebActivity.SOURCE_BIND);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_USERID, str2);
        context.getApplicationContext().startActivity(intent);
    }

    public static void a(Context context, String str, String str2, String str3) {
        Intent intent = new Intent(context.getApplicationContext(), LoginFBBindMiWebActivity.class);
        intent.setFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_SOURCE, LoginBindBaseWebActivity.SOURCE_BIND);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_USERID, str);
        intent.putExtra(LoginBindBaseWebActivity.ARGS_PASSTOKEN, str2);
        intent.putExtra("common_web_url", str3);
        context.getApplicationContext().startActivity(intent);
    }

    public static void c(Context context, String str) {
        Intent intent = new Intent(context, LoginWebActivity.class);
        intent.putExtra("common_web_url", str);
        context.startActivity(intent);
    }

    public static void a(Context context, XmAccountVisibility xmAccountVisibility, final AccessAccountCallback accessAccountCallback) {
        LoginEventUtil.c(context, new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                LoginEventUtil.d(context, this);
                if (intent.getBooleanExtra(LoginEventUtil.g, false)) {
                    accessAccountCallback.a(new Account(intent.getStringExtra(LoginEventUtil.h), intent.getStringExtra(LoginEventUtil.i)));
                    return;
                }
                accessAccountCallback.a(-1, "");
            }
        });
        Intent intent = new Intent(context, LoginSystemTransparentActivity.class);
        intent.addFlags(C.ENCODING_PCM_MU_LAW);
        intent.putExtra(LoginSystemTransparentActivity.PARAM_XMACCOUNTVISIBILITY, xmAccountVisibility);
        context.startActivity(intent);
    }
}
