package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.utils.WbAuthConstants;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\u0010\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0004J\b\u0010\n\u001a\u00020\bH\u0016J*\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\b2\u0006\u0010\u0010\u001a\u00020\b2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0014¨\u0006\u0014"}, d2 = {"Lcom/xiaomi/passport/ui/internal/BaseWeiboAuthProvider;", "Lcom/xiaomi/passport/ui/internal/SNSAuthProvider;", "()V", "getAppId", "", "context", "Landroid/content/Context;", "getIconRes", "", "getRedirectUri", "getRequestCode", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "startLogin", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class BaseWeiboAuthProvider extends SNSAuthProvider {
    public int getRequestCode() {
        return WbAuthConstants.f8849a;
    }

    public BaseWeiboAuthProvider() {
        super(PassportUI.WEIBO_AUTH_PROVIDER);
    }

    public int getIconRes() {
        return R.drawable.sns_weibo_logo;
    }

    @NotNull
    public String getAppId(@NotNull Context context) {
        Intrinsics.f(context, "context");
        String string = context.getString(R.string.weibo_application_id);
        Intrinsics.b(string, "context.getString(R.string.weibo_application_id)");
        return string;
    }

    /* access modifiers changed from: protected */
    @NotNull
    public final String getRedirectUri(@NotNull Context context) {
        Intrinsics.f(context, "context");
        String string = context.getString(R.string.weibo_redirect_uri);
        Intrinsics.b(string, "context.getString(R.string.weibo_redirect_uri)");
        return string;
    }

    /* access modifiers changed from: protected */
    public void startLogin(@NotNull Activity activity) {
        Intrinsics.f(activity, "activity");
        Context context = activity;
        Intent intent = new Intent(context, SnsAuthActivity.class);
        intent.putExtra("url", "https://api.weibo.com/oauth2/authorize?response_type=code&redirect_uri=" + getRedirectUri(context) + "&client_id=" + getAppId(context));
        activity.startActivityForResult(intent, getRequestCode());
    }

    public void onActivityResult(@NotNull Activity activity, int i, int i2, @Nullable Intent intent) {
        Intrinsics.f(activity, "activity");
        if (i == getRequestCode() && i2 == -1) {
            if (intent == null) {
                Intrinsics.a();
            }
            String stringExtra = intent.getStringExtra("code");
            Intrinsics.b(stringExtra, "code");
            storeSnsCode(activity, stringExtra);
        }
    }
}
