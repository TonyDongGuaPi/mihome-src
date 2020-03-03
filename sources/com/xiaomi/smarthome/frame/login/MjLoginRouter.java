package com.xiaomi.smarthome.frame.login;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.exoplayer2.C;
import com.xiaomi.accountsdk.account.data.RegisterUserInfo;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.frame.login.ui.LoginMiuiActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPhoneActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginPwdPhoneActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginUserChooseActivity;
import com.xiaomi.smarthome.frame.login.ui.LoginVerifyCodeActivity;
import com.xiaomi.smarthome.frame.login.ui.PwdChangeActivity;
import com.xiaomi.smarthome.frame.login.ui.SetPwdActivity;
import com.xiaomi.smarthome.frame.login.ui.SetPwdVerifyCodeActivity;
import com.xiaomi.smarthome.frame.login.util.LoginIntentUtil;
import com.xiaomi.smarthome.frame.login.util.ServerUtil;
import com.xiaomi.smarthome.framework.log.LogUtilGrey;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.youpin.login.api.MiLoginApi;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.okhttpApi.api.AccountManagerUtil;
import com.xiaomi.youpin.login.ui.web.LoginH5HomeAcvtivity;
import com.xiaomi.youpin.login.ui.web.LoginWebActivity;
import java.util.List;
import java.util.Locale;

public class MjLoginRouter {
    public static void a(Context context, int i) {
        String b = AccountManagerUtil.b(context);
        if (TextUtils.isEmpty(b)) {
            LogUtilGrey.a("MjLoginRouter", "systemAccount is empty", true);
            if (GlobalSetting.q) {
                try {
                    MiAccountManager miAccountManager = MiAccountManager.get(context.getApplicationContext());
                    StringBuilder sb = new StringBuilder();
                    sb.append("canUseSystem=" + miAccountManager.canUseSystem());
                    if (miAccountManager.canUseSystem()) {
                        Account c = AccountManagerUtil.c(context);
                        sb.append(",account=" + c);
                        if (c != null) {
                            sb.append(",account.name=" + c.name);
                        }
                    }
                    LogUtilGrey.a("MjLoginRouter", sb.toString());
                } catch (Exception e) {
                    e.getMessage();
                }
            }
            a(context);
            return;
        }
        a(context, b, i);
    }

    public static void a(Context context) {
        if (!ServerUtil.a()) {
            b(context, (LocalPhoneDetailInfo) null);
            return;
        }
        List<LocalPhoneDetailInfo> b = MiLoginApi.b();
        if (b == null || b.isEmpty()) {
            LogUtilGrey.a("LoginApi", "routeToLoginHome no phone number", true);
            c(context, (LocalPhoneDetailInfo) null);
        } else if (b.size() == 1) {
            LocalPhoneDetailInfo localPhoneDetailInfo = b.get(0);
            LogUtilGrey.a("LoginApi", "routeToLoginHome  single " + localPhoneDetailInfo.c, true);
            switch (localPhoneDetailInfo.c) {
                case 1:
                case 3:
                    c(context, localPhoneDetailInfo);
                    return;
                case 2:
                    if (localPhoneDetailInfo.b.hasPwd) {
                        a(context, localPhoneDetailInfo);
                        return;
                    } else {
                        c(context, localPhoneDetailInfo);
                        return;
                    }
                default:
                    return;
            }
        } else {
            LogUtilGrey.a("LoginApi", "routeToLoginHome dual", true);
            b(context);
        }
    }

    public static void a(Context context, String str, int i) {
        Intent intent = new Intent(context, LoginMiuiActivity.class);
        LoginIntentUtil.a(intent, str);
        LoginIntentUtil.a(intent, System.currentTimeMillis());
        LoginIntentUtil.a(intent, i);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, @Nullable LocalPhoneDetailInfo localPhoneDetailInfo) {
        Intent intent = new Intent(context, LoginPwdActivity.class);
        if (localPhoneDetailInfo != null) {
            LoginIntentUtil.a(intent, localPhoneDetailInfo);
        }
        LoginIntentUtil.a(intent, System.currentTimeMillis());
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void b(Context context, @Nullable LocalPhoneDetailInfo localPhoneDetailInfo) {
        Intent intent = new Intent(context, LoginPwdPhoneActivity.class);
        if (localPhoneDetailInfo != null) {
            LoginIntentUtil.a(intent, localPhoneDetailInfo);
        }
        LoginIntentUtil.a(intent, System.currentTimeMillis());
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, String str) {
        Intent intent = new Intent(context, LoginPwdActivity.class);
        LoginIntentUtil.a(intent, str);
        LoginIntentUtil.a(intent, System.currentTimeMillis());
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void c(Context context, @Nullable LocalPhoneDetailInfo localPhoneDetailInfo) {
        Intent intent = new Intent(context, LoginPhoneActivity.class);
        if (localPhoneDetailInfo != null) {
            LoginIntentUtil.a(intent, localPhoneDetailInfo);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void b(Context context) {
        Intent intent = new Intent(context, LoginPhoneActivity.class);
        LoginIntentUtil.a(intent, true);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void d(Context context, LocalPhoneDetailInfo localPhoneDetailInfo) {
        Intent intent = new Intent(context, LoginVerifyCodeActivity.class);
        if (localPhoneDetailInfo != null) {
            LoginIntentUtil.a(intent, localPhoneDetailInfo);
        }
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        context.startActivity(intent);
    }

    public static void a(Context context, String str, LocalPhoneDetailInfo localPhoneDetailInfo) {
        Intent intent = new Intent(context, LoginUserChooseActivity.class);
        if (localPhoneDetailInfo != null) {
            LoginIntentUtil.a(intent, localPhoneDetailInfo);
        }
        LoginIntentUtil.c(intent, str);
        context.startActivity(intent);
    }

    public static void a(Context context, String str, RegisterUserInfo registerUserInfo) {
        Intent intent = new Intent(context, LoginUserChooseActivity.class);
        if (registerUserInfo != null) {
            LoginIntentUtil.a(intent, registerUserInfo);
        }
        LoginIntentUtil.b(intent, str);
        context.startActivity(intent);
    }

    public static void a(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, SetPwdActivity.class), i);
    }

    public static void b(Context context, String str) {
        Intent intent = new Intent(context, SetPwdVerifyCodeActivity.class);
        LoginIntentUtil.d(intent, str);
        context.startActivity(intent);
    }

    public static void b(Activity activity, int i) {
        activity.startActivityForResult(new Intent(activity, PwdChangeActivity.class), i);
    }

    public static void c(Context context, String str) {
        Locale I = CoreApi.a().I();
        if (I == null) {
            I = Locale.getDefault();
        }
        Intent intent = new Intent(context, LoginH5HomeAcvtivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        intent.putExtra("common_web_title", context.getApplicationContext().getString(R.string.label_account_security));
        intent.putExtra("common_web_url", a(str, I));
        context.startActivity(intent);
    }

    private static String a(String str, Locale locale) {
        if (TextUtils.isEmpty(str)) {
            return "https://account.xiaomi.com/pass/auth/security/home";
        }
        return "https://account.xiaomi.com/pass/auth/security/home?_locale=" + locale.toString() + "&userId=" + str;
    }

    public static void d(Context context, String str) {
        Intent intent = new Intent(context, LoginWebActivity.class);
        intent.putExtra("common_web_url", str);
        context.startActivity(intent);
    }
}
