package com.xiaomi.youpin.login.api;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Pair;
import com.google.android.exoplayer2.C;
import com.xiaomi.accountsdk.account.XMPassport;
import com.xiaomi.accountsdk.account.XMPassportSettings;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.PassportInfo;
import com.xiaomi.accountsdk.account.exception.InvalidCredentialException;
import com.xiaomi.accountsdk.account.exception.NeedCaptchaException;
import com.xiaomi.accountsdk.request.AuthenticationFailureException;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.PassportUserEnvironment;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.interfaces.AuthenticatorComponentNameInterface;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import com.xiaomi.youpin.login.AsyncCallback;
import com.xiaomi.youpin.login.R;
import com.xiaomi.youpin.login.api.callback.GetCaptchaImageCallback;
import com.xiaomi.youpin.login.api.callback.PwdChangeCallback;
import com.xiaomi.youpin.login.api.manager.LocalPhoneDataCache;
import com.xiaomi.youpin.login.api.phone.ActivatorPhoneManager;
import com.xiaomi.youpin.login.api.phone.LocalPhoneDetailInfo;
import com.xiaomi.youpin.login.entity.account.LoginMiAccount;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;
import com.xiaomi.youpin.login.entity.error.ExceptionError;
import com.xiaomi.youpin.login.okhttpApi.api.BaseLoginApi;
import com.xiaomi.youpin.login.okhttpApi.api.MiuiSystemLoginApi;
import com.xiaomi.youpin.login.other.common.AsyncTaskUtils;
import com.xiaomi.youpin.login.setting.LoginConstant;
import com.xiaomi.youpin.login.ui.web.LoginForgetPwdWebActivity;
import com.xiaomi.youpin.login.ui.web.LoginH5HomeAcvtivity;
import com.xiaomi.youpin.login.ui.web.LoginNewPwdChangeActivity;
import com.xiaomi.youpin.login.ui.web.LoginRegisterWebActivity;
import java.util.List;
import java.util.Locale;

public class MiLoginApi {

    /* renamed from: a  reason: collision with root package name */
    private static final String f1585a = "MiLoginApi";
    private static final String b = "https://account.xiaomi.com/pass/register";
    private static final String c = "https://account.xiaomi.com/pass/forgetPassword";
    private static LoginConfig d;

    public static void a(Application application, LoginConfig loginConfig) {
        PassportExternal.initEnvironment(new PassportUserEnvironment());
        AuthenticatorComponentNameInterface k = loginConfig.k();
        if (k != null) {
            PassportExternal.setAuthenticatorComponentNameInterface(k);
        }
        XMPassportSettings.setApplicationContext(application);
        String q = loginConfig.q();
        if (!TextUtils.isEmpty(q)) {
            XMPassportSettings.setUserAgent(q);
        }
        d = loginConfig;
    }

    public static LoginConfig a() {
        return d;
    }

    public static void a(Context context) {
        LocalPhoneDataCache.a().c();
        new ActivatorPhoneManager(context.getApplicationContext()).a(false);
    }

    public static List<LocalPhoneDetailInfo> b() {
        return LocalPhoneDataCache.a().b();
    }

    @SuppressLint({"StaticFieldLeak"})
    @Deprecated
    public static void a(LoginMiAccount loginMiAccount, String str, String str2, String str3, String str4, PwdChangeCallback pwdChangeCallback) {
        final String str5 = str3;
        final String str6 = str;
        final String str7 = str2;
        final String str8 = str4;
        final PwdChangeCallback pwdChangeCallback2 = pwdChangeCallback;
        new DefaultRefreshServiceTokenCallback<Void>(loginMiAccount, pwdChangeCallback) {
            public void a(final LoginMiAccount loginMiAccount, final MiServiceTokenInfo miServiceTokenInfo, final boolean z) {
                AsyncTaskUtils.a(new AsyncTask<Void, Void, Exception>() {
                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public Exception doInBackground(Void... voidArr) {
                        PassportInfo passportInfo = new PassportInfo(loginMiAccount.a(), miServiceTokenInfo.b, "passportapi", miServiceTokenInfo.c, miServiceTokenInfo.d);
                        try {
                            if (str5 == null) {
                                XMPassport.changePassword(passportInfo, str6, str7, "", "");
                            } else {
                                XMPassport.changePassword(passportInfo, str6, str7, str5, str8);
                            }
                            return null;
                        } catch (Exception e) {
                            return e;
                        }
                    }

                    /* access modifiers changed from: protected */
                    /* renamed from: a */
                    public void onPostExecute(Exception exc) {
                        if (exc == null) {
                            pwdChangeCallback2.a(null);
                        } else if (z && (exc instanceof AuthenticationFailureException)) {
                            AnonymousClass1.this.b();
                        } else if (exc instanceof NeedCaptchaException) {
                            pwdChangeCallback2.a(((NeedCaptchaException) exc).getCaptchaUrl());
                        } else if (exc instanceof InvalidCredentialException) {
                            PwdChangeCallback pwdChangeCallback = pwdChangeCallback2;
                            pwdChangeCallback.a(-1, "InvalidCredentialException " + exc.getMessage());
                        } else {
                            pwdChangeCallback2.a(-2, exc.getMessage());
                        }
                    }
                }, new Void[0]);
            }
        }.a();
    }

    @SuppressLint({"StaticFieldLeak"})
    public static void a(final String str, final GetCaptchaImageCallback getCaptchaImageCallback) {
        if (TextUtils.isEmpty(str)) {
            getCaptchaImageCallback.a();
        } else {
            AsyncTaskUtils.a(new AsyncTask<Void, Void, Pair<Bitmap, String>>() {
                /* access modifiers changed from: protected */
                public void onPreExecute() {
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public Pair<Bitmap, String> doInBackground(Void... voidArr) {
                    return XMPassport.getCaptchaImage(str);
                }

                /* access modifiers changed from: protected */
                /* renamed from: a */
                public void onPostExecute(Pair<Bitmap, String> pair) {
                    if (pair == null) {
                        getCaptchaImageCallback.a();
                        return;
                    }
                    Bitmap bitmap = (Bitmap) pair.first;
                    String str = (String) pair.second;
                    if (bitmap == null || TextUtils.isEmpty(str)) {
                        getCaptchaImageCallback.a();
                    } else {
                        getCaptchaImageCallback.a(bitmap, str);
                    }
                }
            }, new Void[0]);
        }
    }

    public static void a(String str, @Nullable MiServiceTokenInfo miServiceTokenInfo, final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        boolean i = a().i();
        if (miServiceTokenInfo != null) {
            String str2 = miServiceTokenInfo.c + "," + miServiceTokenInfo.d;
            Context o = a().o();
            if (i) {
                AccountManager.get(o).invalidateAuthToken("com.xiaomi", str2);
            } else {
                MiAccountManager.get(o).invalidateAuthToken("com.xiaomi", str2);
            }
        }
        MiuiSystemLoginApi.a(a().i(), (Activity) null, str, false, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                if (asyncCallback != null) {
                    asyncCallback.b(miServiceTokenInfo);
                }
            }

            public void a(ExceptionError exceptionError) {
                if (asyncCallback != null) {
                    asyncCallback.b(exceptionError);
                }
            }
        });
    }

    public static void a(String str, @Nullable MiServiceTokenInfo miServiceTokenInfo, boolean z, final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        boolean i = a().i();
        if (miServiceTokenInfo != null) {
            String str2 = miServiceTokenInfo.c + "," + miServiceTokenInfo.d;
            Context o = a().o();
            if (i) {
                AccountManager.get(o).invalidateAuthToken("com.xiaomi", str2);
            } else {
                MiAccountManager.get(o).invalidateAuthToken("com.xiaomi", str2);
            }
        }
        MiuiSystemLoginApi.a(a().i(), (Activity) null, str, z, (AsyncCallback<MiServiceTokenInfo, ExceptionError>) new AsyncCallback<MiServiceTokenInfo, ExceptionError>() {
            public void a(MiServiceTokenInfo miServiceTokenInfo) {
                if (asyncCallback != null) {
                    asyncCallback.b(miServiceTokenInfo);
                }
            }

            public void a(ExceptionError exceptionError) {
                if (asyncCallback != null) {
                    asyncCallback.b(exceptionError);
                }
            }
        });
    }

    public static void a(final String str, String str2, String str3, boolean z, final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        BaseLoginApi.a(str, str2, str3, z, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(final Pair<AccountInfo, Long> pair) {
                AsyncTaskUtils.a(new AsyncTask<Object, Object, Object>() {
                    /* access modifiers changed from: protected */
                    public Object doInBackground(Object... objArr) {
                        AccountInfo accountInfo = (AccountInfo) pair.first;
                        try {
                            MiAccountManager.get(MiLoginApi.a().o()).setUseLocal();
                            AuthenticatorUtil.addOrUpdateAccountManager(MiLoginApi.a().o(), accountInfo);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (asyncCallback == null) {
                            return null;
                        }
                        MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
                        miServiceTokenInfo.f23514a = str;
                        miServiceTokenInfo.b = accountInfo.getEncryptedUserId();
                        miServiceTokenInfo.c = accountInfo.getServiceToken();
                        miServiceTokenInfo.d = accountInfo.getSecurity();
                        miServiceTokenInfo.f = LoginConstant.a(str);
                        miServiceTokenInfo.e = ((Long) pair.second).longValue();
                        asyncCallback.b(miServiceTokenInfo);
                        return null;
                    }
                }, new Object[0]);
            }

            public void a(ExceptionError exceptionError) {
                if (asyncCallback != null) {
                    asyncCallback.b(exceptionError);
                }
            }
        });
    }

    public static void a(final String str, String str2, String str3, final AsyncCallback<MiServiceTokenInfo, ExceptionError> asyncCallback) {
        BaseLoginApi.a(str, str2, str3, false, new AsyncCallback<Pair<AccountInfo, Long>, ExceptionError>() {
            public void a(Pair<AccountInfo, Long> pair) {
                AccountInfo accountInfo = (AccountInfo) pair.first;
                MiAccountManager.get(MiLoginApi.a().o()).setUseLocal();
                try {
                    AuthenticatorUtil.addOrUpdateAccountManager(MiLoginApi.a().o(), accountInfo);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (asyncCallback != null) {
                    MiServiceTokenInfo miServiceTokenInfo = new MiServiceTokenInfo();
                    miServiceTokenInfo.f23514a = str;
                    miServiceTokenInfo.b = accountInfo.getEncryptedUserId();
                    miServiceTokenInfo.c = accountInfo.getServiceToken();
                    miServiceTokenInfo.d = accountInfo.getSecurity();
                    miServiceTokenInfo.f = LoginConstant.a(str);
                    miServiceTokenInfo.e = ((Long) pair.second).longValue();
                    asyncCallback.b(miServiceTokenInfo);
                }
            }

            public void a(ExceptionError exceptionError) {
                if (asyncCallback != null) {
                    asyncCallback.b(exceptionError);
                }
            }
        });
    }

    public static void a(Activity activity, int i, Locale locale, String str) {
        Intent intent = new Intent(activity, LoginRegisterWebActivity.class);
        intent.putExtra("common_web_title", str);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        intent.putExtra("common_web_url", "https://account.xiaomi.com/pass/register?_locale=" + locale.toString());
        activity.startActivityForResult(intent, i);
    }

    public static void b(Activity activity, int i, Locale locale, String str) {
        Intent intent = new Intent(activity, LoginForgetPwdWebActivity.class);
        intent.putExtra("common_web_title", str);
        if (locale == null) {
            locale = Locale.getDefault();
        }
        intent.putExtra("common_web_url", "https://account.xiaomi.com/pass/forgetPassword?_locale=" + locale.toString());
        activity.startActivityForResult(intent, i);
    }

    public static void a(Activity activity, String str, String str2, int i) {
        Locale locale = Locale.getDefault();
        Intent intent = new Intent(activity, LoginNewPwdChangeActivity.class);
        intent.putExtra("common_web_title", activity.getApplicationContext().getString(R.string.milogin_pwd_title));
        intent.putExtra("common_web_url", a(str, str2, locale));
        activity.startActivityForResult(intent, i);
    }

    private static String a(String str, String str2, Locale locale) {
        if (TextUtils.isEmpty(str)) {
            return "https://account.xiaomi.com/pass/auth/security/home?#service=setPassword";
        }
        return "https://account.xiaomi.com/pass/auth/security/home?_locale=" + locale.toString() + "&userId=" + str + "#service=setPassword";
    }

    public static void a(Context context, String str) {
        Locale locale = Locale.getDefault();
        Intent intent = new Intent(context, LoginH5HomeAcvtivity.class);
        if (!(context instanceof Activity)) {
            intent.addFlags(C.ENCODING_PCM_MU_LAW);
        }
        intent.putExtra("common_web_title", context.getApplicationContext().getString(R.string.milogin_pwd_title));
        intent.putExtra("common_web_url", a(str, locale));
        context.startActivity(intent);
    }

    private static String a(String str, Locale locale) {
        if (TextUtils.isEmpty(str)) {
            return "https://account.xiaomi.com/pass/auth/security/home";
        }
        return "https://account.xiaomi.com/pass/auth/security/home?_locale=" + locale.toString() + "&userId=" + str;
    }
}
