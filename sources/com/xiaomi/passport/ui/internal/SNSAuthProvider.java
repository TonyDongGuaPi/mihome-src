package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.accountsdk.account.data.ActivatorPhoneInfo;
import com.xiaomi.passport.snscorelib.internal.entity.SNSBindParameter;
import com.xiaomi.passport.snscorelib.internal.entity.SNSLoginParameter;
import com.xiaomi.passport.snscorelib.internal.request.SNSRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONObject;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b&\u0018\u0000 +2\u00020\u0001:\u0001+B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH&J\b\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000bH\u0002J\b\u0010\u000f\u001a\u00020\rH\u0016J\b\u0010\u0010\u001a\u00020\rH\u0016J*\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0016J\u001e\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001c\u001a\u00020\u001dH\u0004J\u0018\u0010\u001e\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\u001b2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001f\u001a\u00020\"H\u0002J\u0010\u0010#\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H$J\u0016\u0010#\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0005\u001a\u00020\u0003J\u0010\u0010$\u001a\u00020\u00122\u0006\u0010%\u001a\u00020&H\u0002J\u0018\u0010'\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010(\u001a\u00020\u0003H\u0004J\u0016\u0010)\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010*\u001a\u00020\u0003R\u001a\u0010\u0005\u001a\u00020\u0003X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\u0004¨\u0006,"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SNSAuthProvider;", "Lcom/xiaomi/passport/ui/internal/AuthProvider;", "name", "", "(Ljava/lang/String;)V", "sid", "getSid", "()Ljava/lang/String;", "setSid", "getAppId", "context", "Landroid/content/Context;", "getIconRes", "", "getPhoneNumInfo", "getRequestCode", "getTintColor", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "signInWithAuthCredential", "Lcom/xiaomi/passport/ui/internal/Source;", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "credential", "Lcom/xiaomi/passport/ui/internal/AuthCredential;", "signInWithSnsCodeAuthCredential", "authCredential", "Lcom/xiaomi/passport/ui/internal/SnsCodeAuthCredential;", "signInWithSnsTokenAuthCredential", "Lcom/xiaomi/passport/ui/internal/SnsTokenAuthCredential;", "startLogin", "storeBindParameter", "parameter", "Lcom/xiaomi/passport/snscorelib/internal/entity/SNSBindParameter;", "storeSnsCode", "code", "storeSnsToken", "token", "Companion", "passportui_release"}, k = 1, mv = {1, 1, 10})
public abstract class SNSAuthProvider extends AuthProvider {
    @NotNull
    public static final String ACTION_PASSPORT_SNS_EVENTS = "passport_sns_events";
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    @NotNull
    public static final String EXTRA_KEY_SNS_RESULT = "sns_result";
    @NotNull
    public static final String VALUE_SNS_CANCELLED = "cancelled";
    @NotNull
    public static final String VALUE_SNS_ERROR = "error";
    @NotNull
    public static final String VALUE_SNS_OK = "ok";
    /* access modifiers changed from: private */
    @Nullable
    public static SNSAuthCredential authCredential;
    /* access modifiers changed from: private */
    @Nullable
    public static SNSBindParameter snsBindParameter;
    @NotNull
    public String sid;

    @NotNull
    public abstract String getAppId(@NotNull Context context);

    public abstract int getIconRes();

    public int getRequestCode() {
        return -100;
    }

    public int getTintColor() {
        return -1;
    }

    public void onActivityResult(@NotNull Activity activity, int i, int i2, @Nullable Intent intent) {
        Intrinsics.f(activity, "activity");
    }

    /* access modifiers changed from: protected */
    public abstract void startLogin(@NotNull Activity activity);

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public SNSAuthProvider(@NotNull String str) {
        super(str);
        Intrinsics.f(str, "name");
    }

    @Metadata(bv = {1, 0, 2}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0006\u0010\u0017\u001a\u00020\u0016J\u0006\u0010\u0018\u001a\u00020\u0019R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0004\u0018\u00010\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001c\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0011\u0010\u0012\"\u0004\b\u0013\u0010\u0014¨\u0006\u001a"}, d2 = {"Lcom/xiaomi/passport/ui/internal/SNSAuthProvider$Companion;", "", "()V", "ACTION_PASSPORT_SNS_EVENTS", "", "EXTRA_KEY_SNS_RESULT", "VALUE_SNS_CANCELLED", "VALUE_SNS_ERROR", "VALUE_SNS_OK", "authCredential", "Lcom/xiaomi/passport/ui/internal/SNSAuthCredential;", "getAuthCredential", "()Lcom/xiaomi/passport/ui/internal/SNSAuthCredential;", "setAuthCredential", "(Lcom/xiaomi/passport/ui/internal/SNSAuthCredential;)V", "snsBindParameter", "Lcom/xiaomi/passport/snscorelib/internal/entity/SNSBindParameter;", "getSnsBindParameter", "()Lcom/xiaomi/passport/snscorelib/internal/entity/SNSBindParameter;", "setSnsBindParameter", "(Lcom/xiaomi/passport/snscorelib/internal/entity/SNSBindParameter;)V", "invalidAuthCredential", "", "invalidBindParameter", "isBindingSnsAccount", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        @Nullable
        public final SNSBindParameter getSnsBindParameter() {
            return SNSAuthProvider.snsBindParameter;
        }

        public final void setSnsBindParameter(@Nullable SNSBindParameter sNSBindParameter) {
            SNSAuthProvider.snsBindParameter = sNSBindParameter;
        }

        @Nullable
        public final SNSAuthCredential getAuthCredential() {
            return SNSAuthProvider.authCredential;
        }

        public final void setAuthCredential(@Nullable SNSAuthCredential sNSAuthCredential) {
            SNSAuthProvider.authCredential = sNSAuthCredential;
        }

        public final boolean isBindingSnsAccount() {
            return getSnsBindParameter() != null;
        }

        public final void invalidBindParameter() {
            setSnsBindParameter((SNSBindParameter) null);
        }

        public final void invalidAuthCredential() {
            setAuthCredential((SNSAuthCredential) null);
        }
    }

    @NotNull
    public final String getSid() {
        String str = this.sid;
        if (str == null) {
            Intrinsics.c("sid");
        }
        return str;
    }

    public final void setSid(@NotNull String str) {
        Intrinsics.f(str, "<set-?>");
        this.sid = str;
    }

    /* access modifiers changed from: private */
    public final void storeBindParameter(SNSBindParameter sNSBindParameter) {
        snsBindParameter = sNSBindParameter;
    }

    /* access modifiers changed from: protected */
    public final void storeSnsCode(@NotNull Context context, @NotNull String str) {
        Intrinsics.f(context, "context");
        Intrinsics.f(str, "code");
        String name = getName();
        String appId = getAppId(context);
        String str2 = this.sid;
        if (str2 == null) {
            Intrinsics.c("sid");
        }
        authCredential = new SnsCodeAuthCredential(name, appId, str, str2);
    }

    public final void storeSnsToken(@NotNull Context context, @NotNull String str) {
        Intrinsics.f(context, "context");
        Intrinsics.f(str, "token");
        String name = getName();
        String appId = getAppId(context);
        String str2 = this.sid;
        if (str2 == null) {
            Intrinsics.c("sid");
        }
        authCredential = new SnsTokenAuthCredential(name, appId, str, str2);
    }

    public final void startLogin(@NotNull Activity activity, @NotNull String str) {
        Intrinsics.f(activity, "activity");
        Intrinsics.f(str, "sid");
        this.sid = str;
        startLogin(activity);
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final Source<AccountInfo> signInWithAuthCredential(@NotNull Context context, @NotNull AuthCredential authCredential2) {
        Intrinsics.f(context, "context");
        Intrinsics.f(authCredential2, "credential");
        if (authCredential2 instanceof SNSAuthCredential) {
            return Source.Companion.from(new SNSAuthProvider$signInWithAuthCredential$1(this, authCredential2, context));
        }
        throw new IllegalStateException("not support originAuthCredential:" + authCredential2);
    }

    /* access modifiers changed from: private */
    public final AccountInfo signInWithSnsCodeAuthCredential(Context context, SnsCodeAuthCredential snsCodeAuthCredential) {
        AccountInfo snsLoginByCode = SNSRequest.snsLoginByCode(new SNSLoginParameter.Builder().phones(getPhoneNumInfo(context)).code(snsCodeAuthCredential.getCode()).sid(snsCodeAuthCredential.getSid()).appid(snsCodeAuthCredential.getAppId()).build());
        Intrinsics.b(snsLoginByCode, "SNSRequest.snsLoginByCode(params)");
        return snsLoginByCode;
    }

    /* access modifiers changed from: private */
    public final AccountInfo signInWithSnsTokenAuthCredential(Context context, SnsTokenAuthCredential snsTokenAuthCredential) {
        AccountInfo snsLoginByAccessToken = SNSRequest.snsLoginByAccessToken(new SNSLoginParameter.Builder().phones(getPhoneNumInfo(context)).token(snsTokenAuthCredential.getToken()).sid(snsTokenAuthCredential.getSid()).appid(snsTokenAuthCredential.getAppId()).build());
        Intrinsics.b(snsLoginByAccessToken, "SNSRequest.snsLoginByAccessToken(params)");
        return snsLoginByAccessToken;
    }

    private final String getPhoneNumInfo(Context context) {
        JSONObject jSONObject = new JSONObject();
        if (!PassportUI.INSTANCE.getInternational()) {
            for (ActivatorPhoneInfo activatorPhoneInfo : new PassportRepoImpl().getLocalActivatorPhone(context, true).getSync()) {
                jSONObject.putOpt(activatorPhoneInfo.phoneHash, activatorPhoneInfo.activatorToken);
            }
        }
        String jSONObject2 = jSONObject.toString();
        Intrinsics.b(jSONObject2, "phones.toString()");
        return jSONObject2;
    }
}
