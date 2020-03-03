package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.widget.Toast;
import com.facebook.FacebookSdk;
import com.google.android.exoplayer2.C;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.accountsdk.account.XMPassportUserAgent;
import com.xiaomi.accountsdk.utils.AccountLog;
import com.xiaomi.passport.PassportExternal;
import com.xiaomi.passport.accountmanager.MiAccountManager;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.settings.AccountSettingsActivity;
import com.xiaomi.passport.ui.settings.ChangePasswordActivity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0018\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\b\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0016\u00107\u001a\u0002082\u0006\u00109\u001a\u00020\u00042\u0006\u0010:\u001a\u00020\u0004J\u0018\u0010;\u001a\u0002082\u0006\u0010<\u001a\u00020=2\u0006\u0010>\u001a\u00020\u0004H\u0002J\u0010\u0010;\u001a\u0002082\u0006\u0010>\u001a\u00020$H\u0002J\u0018\u0010?\u001a\u00020\u001d2\u0006\u0010<\u001a\u00020=2\u0006\u0010@\u001a\u00020AH\u0002J\u000e\u0010B\u001a\u00020C2\u0006\u0010D\u001a\u00020\u0004J\u0006\u0010E\u001a\u00020CJ\u0010\u0010F\u001a\u0004\u0018\u00010$2\u0006\u0010G\u001a\u00020HJ\u0010\u0010F\u001a\u0004\u0018\u00010$2\u0006\u0010D\u001a\u00020\u0004J\u0012\u0010I\u001a\u0004\u0018\u00010A2\u0006\u0010<\u001a\u00020=H\u0002J\u000e\u0010J\u001a\u0002082\u0006\u0010<\u001a\u00020=J\u000e\u0010K\u001a\u0002082\u0006\u0010>\u001a\u00020\u0004J\u000e\u0010L\u001a\u0002082\u0006\u0010<\u001a\u00020=J\u000e\u0010M\u001a\u0002082\u0006\u0010<\u001a\u00020=J)\u0010N\u001a\u0002082\b\u0010O\u001a\u0004\u0018\u00010A2\u0006\u0010<\u001a\u00020=2\b\u0010P\u001a\u0004\u0018\u00010QH\u0002¢\u0006\u0002\u0010RR\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n \u0011*\u0004\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\u0014\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R\u000e\u0010\u0019\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u0016\u0010\u001a\u001a\u00020\u00048\u0000XT¢\u0006\b\n\u0000\u0012\u0004\b\u001b\u0010\u0002R\u001a\u0010\u001c\u001a\u00020\u001dX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001f\"\u0004\b \u0010!R \u0010\"\u001a\b\u0012\u0004\u0012\u00020$0#X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R\u001a\u0010)\u001a\u00020*X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R\u001c\u0010/\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b0\u00101\"\u0004\b2\u00103R\u001c\u00104\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b5\u00101\"\u0004\b6\u00103¨\u0006S"}, d2 = {"Lcom/xiaomi/passport/ui/internal/PassportUI;", "", "()V", "ACTION_LOCAL_ACCOUNT_CHANGE_PASSWORD", "", "CHINA_COUNTRY_CODE", "EXTRA_DEFAULT_AUTH_PROVIDER", "EXTRA_DEFAULT_PHONE_COUNTRY_CODE", "EXTRA_LOCAL_ACCOUNT_CHANGE_PASSWORD_RESULT", "EXTRA_LOCAL_ACCOUNT_CHANGE_PASSWORD_RESULT_CODE", "EXTRA_SNS_SIGN_IN", "FACEBOOK_AUTH_PROVIDER", "GOOGLE_AUTH_PROVIDER", "ID_PSW_AUTH_PROVIDER", "PHONE_SMS_AUTH_PROVIDER", "QQ_AUTH_PROVIDER", "TAG", "kotlin.jvm.PlatformType", "WECHAT_AUTH_PROVIDER", "WEIBO_AUTH_PROVIDER", "WXAPIEventHandler", "getWXAPIEventHandler", "()Ljava/lang/Object;", "setWXAPIEventHandler", "(Ljava/lang/Object;)V", "WX_API_STATE_PASSPORT", "ZHIFUBAO_AUTH_PROVIDER", "ZHIFUBAO_AUTH_PROVIDER$annotations", "international", "", "getInternational", "()Z", "setInternational", "(Z)V", "mProviders", "", "Lcom/xiaomi/passport/ui/internal/AuthProvider;", "getMProviders$passportui_release", "()Ljava/util/List;", "setMProviders$passportui_release", "(Ljava/util/List;)V", "passportRepo", "Lcom/xiaomi/passport/ui/internal/PassportRepo;", "getPassportRepo", "()Lcom/xiaomi/passport/ui/internal/PassportRepo;", "setPassportRepo", "(Lcom/xiaomi/passport/ui/internal/PassportRepo;)V", "privacyPolicyUrl", "getPrivacyPolicyUrl", "()Ljava/lang/String;", "setPrivacyPolicyUrl", "(Ljava/lang/String;)V", "userAgreementUrl", "getUserAgreementUrl", "setUserAgreementUrl", "addLicense", "", "readableText", "url", "addProvider", "context", "Landroid/content/Context;", "provider", "checkActivityIntent", "intent", "Landroid/content/Intent;", "getBaseAuthProvider", "Lcom/xiaomi/passport/ui/internal/BaseAuthProvider;", "providerName", "getDefaultBaseAuthProvider", "getProvider", "authCredential", "Lcom/xiaomi/passport/ui/internal/AuthCredential;", "getSystemAccountSettingsIntent", "init", "rmProvider", "startAccountSettings", "startChangePassword", "tryStartActivityIntent", "accountSettingsIntent", "requestCode", "", "(Landroid/content/Intent;Landroid/content/Context;Ljava/lang/Integer;)V", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PassportUI {
    @NotNull
    public static final String ACTION_LOCAL_ACCOUNT_CHANGE_PASSWORD = "action_local_account_change_password";
    @NotNull
    public static final String CHINA_COUNTRY_CODE = "+86";
    @NotNull
    public static final String EXTRA_DEFAULT_AUTH_PROVIDER = "default_auth_provider";
    @NotNull
    public static final String EXTRA_DEFAULT_PHONE_COUNTRY_CODE = "default_phone_country_code";
    @NotNull
    public static final String EXTRA_LOCAL_ACCOUNT_CHANGE_PASSWORD_RESULT = "result";
    @NotNull
    public static final String EXTRA_LOCAL_ACCOUNT_CHANGE_PASSWORD_RESULT_CODE = "result_code";
    @NotNull
    public static final String EXTRA_SNS_SIGN_IN = "sns_sign_in";
    @NotNull
    public static final String FACEBOOK_AUTH_PROVIDER = "FACEBOOK_AUTH_PROVIDER";
    @NotNull
    public static final String GOOGLE_AUTH_PROVIDER = "GOOGLE_AUTH_PROVIDER";
    @NotNull
    public static final String ID_PSW_AUTH_PROVIDER = "ID_PSW_AUTH_PROVIDER";
    public static final PassportUI INSTANCE = new PassportUI();
    @NotNull
    public static final String PHONE_SMS_AUTH_PROVIDER = "PHONE_SMS_AUTH_PROVIDER";
    @NotNull
    public static final String QQ_AUTH_PROVIDER = "QQ_AUTH_PROVIDER";
    private static final String TAG = PassportUI.class.getSimpleName();
    @NotNull
    public static final String WECHAT_AUTH_PROVIDER = "WECHAT_AUTH_PROVIDER";
    @NotNull
    public static final String WEIBO_AUTH_PROVIDER = "WEIBO_AUTH_PROVIDER";
    @Nullable
    private static Object WXAPIEventHandler = null;
    @NotNull
    public static final String WX_API_STATE_PASSPORT = "wx_api_passport";
    @NotNull
    public static final String ZHIFUBAO_AUTH_PROVIDER = "ZHIFUBAO_AUTH_PROVIDER";
    private static boolean international;
    @NotNull
    private static List<AuthProvider> mProviders = new ArrayList();
    @NotNull
    private static PassportRepo passportRepo = new PassportRepoImpl();
    @Nullable
    private static String privacyPolicyUrl;
    @Nullable
    private static String userAgreementUrl;

    public static /* synthetic */ void ZHIFUBAO_AUTH_PROVIDER$annotations() {
    }

    static {
        XMPassportUserAgent.addExtendedUserAgent("PassportSDK/1.6.1-SNAPSHOT");
        XMPassportUserAgent.addExtendedUserAgent("passport-ui/1.6.1-SNAPSHOT");
        mProviders.add(new IdPswAuthProvider());
        mProviders.add(new PhoneSmsAuthProvider());
    }

    private PassportUI() {
    }

    @NotNull
    public final List<AuthProvider> getMProviders$passportui_release() {
        return mProviders;
    }

    public final void setMProviders$passportui_release(@NotNull List<AuthProvider> list) {
        Intrinsics.f(list, "<set-?>");
        mProviders = list;
    }

    @Nullable
    public final String getUserAgreementUrl() {
        return userAgreementUrl;
    }

    public final void setUserAgreementUrl(@Nullable String str) {
        userAgreementUrl = str;
    }

    @Nullable
    public final String getPrivacyPolicyUrl() {
        return privacyPolicyUrl;
    }

    public final void setPrivacyPolicyUrl(@Nullable String str) {
        privacyPolicyUrl = str;
    }

    @NotNull
    public final PassportRepo getPassportRepo() {
        return passportRepo;
    }

    public final void setPassportRepo(@NotNull PassportRepo passportRepo2) {
        Intrinsics.f(passportRepo2, "<set-?>");
        passportRepo = passportRepo2;
    }

    public final boolean getInternational() {
        return international;
    }

    public final void setInternational(boolean z) {
        international = z;
    }

    public final void init(@NotNull Context context) {
        Intrinsics.f(context, "context");
        PassportExternal.setAuthenticatorComponentNameInterface(new AuthComponent(context));
        addProvider(context, FACEBOOK_AUTH_PROVIDER);
        addProvider(context, GOOGLE_AUTH_PROVIDER);
        addProvider(context, WEIBO_AUTH_PROVIDER);
        addProvider(context, QQ_AUTH_PROVIDER);
        addProvider(context, WECHAT_AUTH_PROVIDER);
    }

    private final void addProvider(Context context, String str) {
        boolean z = false;
        if (Intrinsics.a((Object) str, (Object) WEIBO_AUTH_PROVIDER)) {
            String string = context.getString(R.string.weibo_application_id);
            Intrinsics.b(string, "context.getString(R.string.weibo_application_id)");
            if (string != null) {
                if (!(StringsKt.b(string).toString().length() == 0)) {
                    addProvider(new WeiboSSOAuthProvider());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        if (Intrinsics.a((Object) str, (Object) QQ_AUTH_PROVIDER)) {
            String string2 = context.getString(R.string.qq_application_id);
            Intrinsics.b(string2, "context.getString(R.string.qq_application_id)");
            if (string2 != null) {
                if (!(StringsKt.b(string2).toString().length() == 0)) {
                    addProvider(new QQSSOAuthProvider());
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        if (Intrinsics.a((Object) str, (Object) WECHAT_AUTH_PROVIDER)) {
            String string3 = context.getString(R.string.wechat_application_id);
            Intrinsics.b(string3, "context.getString(R.string.wechat_application_id)");
            if (string3 != null) {
                if (!(StringsKt.b(string3).toString().length() == 0)) {
                    try {
                        AccountLog.v(TAG, WXAPIFactory.class.getName());
                        addProvider(new WeChatAuthProvider());
                    } catch (NoClassDefFoundError unused) {
                        new RuntimeException("WE_CHAT provider cannot be configured without dependency. Did you forget to add 'com.tencent.mm.opensdk:wechat-sdk-android-without-mta:+' dependency?").printStackTrace();
                    }
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        if (Intrinsics.a((Object) str, (Object) FACEBOOK_AUTH_PROVIDER)) {
            String string4 = context.getString(R.string.facebook_application_id);
            Intrinsics.b(string4, "context.getString(R.stri….facebook_application_id)");
            if (string4 != null) {
                if (!(StringsKt.b(string4).toString().length() == 0)) {
                    try {
                        AccountLog.v(TAG, FacebookSdk.class.getName());
                        addProvider(new FaceBookAuthProvider());
                    } catch (NoClassDefFoundError unused2) {
                        new RuntimeException("FaceBook provider cannot be configured without dependency. Did you forget to add com.facebook.android:facebook-login:+ dependency?").printStackTrace();
                    }
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
        if (Intrinsics.a((Object) str, (Object) GOOGLE_AUTH_PROVIDER)) {
            String string5 = context.getString(R.string.google_application_id);
            Intrinsics.b(string5, "context.getString(R.string.google_application_id)");
            if (string5 != null) {
                if (StringsKt.b(string5).toString().length() == 0) {
                    z = true;
                }
                if (!z) {
                    try {
                        AccountLog.v(TAG, GoogleSignInClient.class.getName());
                        addProvider(new GoogleAuthProvider());
                    } catch (NoClassDefFoundError unused3) {
                        new RuntimeException("Google provider cannot be configured without dependency. Did you forget to add \"com.google.android.gms:play-services-auth:16.0.1\" dependency?").printStackTrace();
                    }
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.CharSequence");
            }
        }
    }

    public final void rmProvider(@NotNull String str) {
        Intrinsics.f(str, "provider");
        if (CollectionsKt.a(mProviders, getProvider(str))) {
            Collection collection = mProviders;
            AuthProvider provider = getProvider(str);
            if (collection != null) {
                TypeIntrinsics.k(collection).remove(provider);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
        }
    }

    private final void addProvider(AuthProvider authProvider) {
        rmProvider(authProvider.getName());
        mProviders.add(authProvider);
    }

    @NotNull
    public final BaseAuthProvider getDefaultBaseAuthProvider() {
        if (international) {
            AuthProvider provider = getProvider(ID_PSW_AUTH_PROVIDER);
            if (provider != null) {
                return (BaseAuthProvider) provider;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.BaseAuthProvider");
        }
        AuthProvider provider2 = getProvider(PHONE_SMS_AUTH_PROVIDER);
        if (provider2 != null) {
            return (BaseAuthProvider) provider2;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.BaseAuthProvider");
    }

    @Nullable
    public final AuthProvider getProvider(@NotNull String str) {
        Intrinsics.f(str, "providerName");
        Collection arrayList = new ArrayList();
        for (Object next : mProviders) {
            if (Intrinsics.a((Object) ((AuthProvider) next).getName(), (Object) str)) {
                arrayList.add(next);
            }
        }
        Iterator it = ((List) arrayList).iterator();
        if (it.hasNext()) {
            return (AuthProvider) it.next();
        }
        return null;
    }

    @NotNull
    public final BaseAuthProvider getBaseAuthProvider(@NotNull String str) {
        Intrinsics.f(str, "providerName");
        Collection arrayList = new ArrayList();
        for (Object next : mProviders) {
            if (Intrinsics.a((Object) ((AuthProvider) next).getName(), (Object) str)) {
                arrayList.add(next);
            }
        }
        Iterator it = ((List) arrayList).iterator();
        if (it.hasNext()) {
            AuthProvider authProvider = (AuthProvider) it.next();
            if (authProvider != null) {
                return (BaseAuthProvider) authProvider;
            }
            throw new TypeCastException("null cannot be cast to non-null type com.xiaomi.passport.ui.internal.BaseAuthProvider");
        }
        throw new RuntimeException("FaceBook provider cannot be configured ");
    }

    @Nullable
    public final AuthProvider getProvider(@NotNull AuthCredential authCredential) {
        Intrinsics.f(authCredential, "authCredential");
        return getProvider(authCredential.getProvider());
    }

    public final void addLicense(@NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "readableText");
        Intrinsics.f(str2, "url");
        UserLicenseUtils.Companion.addLicense(str, str2);
    }

    @Nullable
    public final Object getWXAPIEventHandler() {
        return WXAPIEventHandler;
    }

    public final void setWXAPIEventHandler(@Nullable Object obj) {
        WXAPIEventHandler = obj;
    }

    public final void startChangePassword(@NotNull Context context) {
        Intrinsics.f(context, "context");
        MiAccountManager miAccountManager = MiAccountManager.get(context);
        if (miAccountManager == null || miAccountManager.getXiaomiAccount() == null) {
            Toast.makeText(context, R.string.no_account, 0).show();
        } else if (miAccountManager.isUseSystem()) {
            tryStartActivityIntent(getSystemAccountSettingsIntent(context), context, (Integer) null);
        } else if (miAccountManager.isUseLocal()) {
            tryStartActivityIntent(ChangePasswordActivity.newIntent(context), context, (Integer) null);
        }
    }

    public final void startAccountSettings(@NotNull Context context) {
        Intrinsics.f(context, "context");
        MiAccountManager miAccountManager = MiAccountManager.get(context);
        if (miAccountManager == null || miAccountManager.getXiaomiAccount() == null) {
            Toast.makeText(context, R.string.no_account, 0).show();
            return;
        }
        Intent intent = null;
        if (miAccountManager.isUseSystem()) {
            intent = getSystemAccountSettingsIntent(context);
        } else if (miAccountManager.isUseLocal()) {
            intent = new Intent(context, AccountSettingsActivity.class);
        }
        tryStartActivityIntent(intent, context, (Integer) null);
    }

    private final void tryStartActivityIntent(Intent intent, Context context, Integer num) {
        if (intent != null) {
            try {
                intent.addFlags(C.ENCODING_PCM_MU_LAW);
                if (num == null || !(context instanceof Activity)) {
                    context.startActivity(intent);
                } else {
                    ((Activity) context).startActivityForResult(intent, num.intValue());
                }
            } catch (ActivityNotFoundException e) {
                AccountLog.e(TAG, "launch account settings failed: ", e);
            }
        }
    }

    private final Intent getSystemAccountSettingsIntent(Context context) {
        Intent intent = new Intent(Constants.ACTION_XIAOMI_ACCOUNT_USER_INFO_DETAIL);
        if (checkActivityIntent(context, intent)) {
            return intent;
        }
        Intent intent2 = new Intent("android.settings.XIAOMI_ACCOUNT_SYNC_SETTINGS");
        if (checkActivityIntent(context, intent2)) {
            return intent2;
        }
        return null;
    }

    private final boolean checkActivityIntent(Context context, Intent intent) {
        List<ResolveInfo> queryIntentActivities = context.getPackageManager().queryIntentActivities(intent, 0);
        Intrinsics.b(queryIntentActivities, "context.packageManager.q…tentActivities(intent, 0)");
        return !queryIntentActivities.isEmpty();
    }
}
