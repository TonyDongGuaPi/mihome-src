package com.xiaomi.youpin.login.api.manager;

import android.accounts.OperationCanceledException;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.AsyncTask;
import android.util.Pair;
import com.alipay.mobile.common.logging.api.LogCategory;
import com.xiaomi.account.openauth.XMAuthericationException;
import com.xiaomi.account.openauth.XiaomiOAuthConstants;
import com.xiaomi.account.openauth.XiaomiOAuthFuture;
import com.xiaomi.account.openauth.XiaomiOAuthResults;
import com.xiaomi.account.openauth.XiaomiOAuthorize;
import com.xiaomi.youpin.login.api.LoginErrorCode;
import com.xiaomi.youpin.login.api.manager.callback.OAuthLoginCallback;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.entity.oauth.LoginMiByOAuthResult;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

public class OAuthLoginManager {
    @SuppressLint({"StaticFieldLeak"})
    public void a(Activity activity, int[] iArr, String str, String str2, OAuthLoginCallback oAuthLoginCallback) {
        final String str3 = str;
        final String str4 = str2;
        final int[] iArr2 = iArr;
        final Activity activity2 = activity;
        final OAuthLoginCallback oAuthLoginCallback2 = oAuthLoginCallback;
        AsyncTaskUtils.a(new AsyncTask<Object, Object, Pair<LoginMiByOAuthResult, ExceptionError>>() {
            private XiaomiOAuthFuture<XiaomiOAuthResults> g;

            /* access modifiers changed from: protected */
            public void onPreExecute() {
                this.g = new XiaomiOAuthorize().setAppId(Long.parseLong(str3)).setRedirectUrl(str4).setScope(iArr2).startGetAccessToken(activity2);
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public Pair<LoginMiByOAuthResult, ExceptionError> doInBackground(Object... objArr) {
                XiaomiOAuthResults xiaomiOAuthResults;
                String str;
                try {
                    xiaomiOAuthResults = this.g.getResult();
                    e = null;
                } catch (OperationCanceledException | XMAuthericationException | IOException e2) {
                    e = e2;
                    xiaomiOAuthResults = null;
                }
                if (e != null) {
                    ExceptionError exceptionError = new ExceptionError(-1, LogCategory.CATEGORY_EXCEPTION);
                    exceptionError.f23518a = e;
                    return new Pair<>((Object) null, exceptionError);
                } else if (xiaomiOAuthResults == null) {
                    return new Pair<>((Object) null, new ExceptionError(-2, "oauthResults is null"));
                } else {
                    if (xiaomiOAuthResults.hasError()) {
                        return new Pair<>((Object) null, new ExceptionError(xiaomiOAuthResults.getErrorCode(), xiaomiOAuthResults.getErrorMessage()));
                    }
                    try {
                        str = new XiaomiOAuthorize().callOpenApi(activity2, Long.parseLong(str3), XiaomiOAuthConstants.OPEN_API_PATH_PROFILE, xiaomiOAuthResults.getAccessToken(), xiaomiOAuthResults.getMacKey(), xiaomiOAuthResults.getMacAlgorithm()).getResult();
                    } catch (OperationCanceledException | XMAuthericationException | IOException e3) {
                        e = e3;
                        str = null;
                    }
                    if (e != null) {
                        ExceptionError exceptionError2 = new ExceptionError(-3, LogCategory.CATEGORY_EXCEPTION);
                        exceptionError2.f23518a = e;
                        return new Pair<>((Object) null, exceptionError2);
                    }
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        LoginMiByOAuthResult loginMiByOAuthResult = new LoginMiByOAuthResult();
                        loginMiByOAuthResult.b = xiaomiOAuthResults.getAccessToken();
                        loginMiByOAuthResult.c = xiaomiOAuthResults.getExpiresIn();
                        loginMiByOAuthResult.d = xiaomiOAuthResults.getScopes();
                        loginMiByOAuthResult.e = xiaomiOAuthResults.getState();
                        loginMiByOAuthResult.f = xiaomiOAuthResults.getTokenType();
                        loginMiByOAuthResult.g = xiaomiOAuthResults.getMacKey();
                        loginMiByOAuthResult.h = xiaomiOAuthResults.getMacAlgorithm();
                        loginMiByOAuthResult.i = xiaomiOAuthResults.getCode();
                        JSONObject optJSONObject = jSONObject.optJSONObject("data");
                        loginMiByOAuthResult.f23519a = optJSONObject.optString("userId");
                        loginMiByOAuthResult.j = optJSONObject.optString("miliaoIcon_90");
                        loginMiByOAuthResult.k = optJSONObject.optString("miliaoNick");
                        loginMiByOAuthResult.l = optJSONObject.optString("miliaoIcon_75");
                        loginMiByOAuthResult.m = optJSONObject.optString("miliaoIcon_320");
                        loginMiByOAuthResult.n = optJSONObject.optString("miliaoIcon_120");
                        loginMiByOAuthResult.o = optJSONObject.optString("miliaoIcon_orig");
                        loginMiByOAuthResult.p = optJSONObject.optString("miliaoIcon");
                        return new Pair<>(loginMiByOAuthResult, (Object) null);
                    } catch (JSONException e4) {
                        e4.printStackTrace();
                        return new Pair<>((Object) null, new ExceptionError(-4, "JSONException " + e4));
                    }
                }
            }

            /* access modifiers changed from: protected */
            /* renamed from: a */
            public void onPostExecute(Pair<LoginMiByOAuthResult, ExceptionError> pair) {
                LoginMiByOAuthResult loginMiByOAuthResult = (LoginMiByOAuthResult) pair.first;
                ExceptionError exceptionError = (ExceptionError) pair.second;
                if (exceptionError == null) {
                    oAuthLoginCallback2.a(loginMiByOAuthResult);
                } else if (exceptionError.f23518a == null) {
                    oAuthLoginCallback2.a(LoginErrorCode.aI, exceptionError.b());
                } else {
                    oAuthLoginCallback2.a(LoginErrorCode.aI, exceptionError.f23518a.getMessage());
                }
            }
        }, new Object[0]);
    }
}
