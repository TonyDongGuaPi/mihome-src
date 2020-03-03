package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J*\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/xiaomi/passport/ui/internal/WeiboSSOAuthProvider;", "Lcom/xiaomi/passport/ui/internal/BaseWeiboAuthProvider;", "()V", "mSsoHandler", "Lcom/sina/weibo/sdk/auth/sso/SsoHandler;", "onActivityResult", "", "activity", "Landroid/app/Activity;", "requestCode", "", "resultCode", "data", "Landroid/content/Intent;", "startLogin", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WeiboSSOAuthProvider extends BaseWeiboAuthProvider {
    private SsoHandler mSsoHandler;

    public void onActivityResult(@NotNull Activity activity, int i, int i2, @Nullable Intent intent) {
        Intrinsics.f(activity, "activity");
        SsoHandler ssoHandler = this.mSsoHandler;
        if (ssoHandler == null) {
            Intrinsics.c("mSsoHandler");
        }
        ssoHandler.authorizeCallBack(i, i2, intent);
    }

    /* access modifiers changed from: protected */
    public void startLogin(@NotNull Activity activity) {
        Intrinsics.f(activity, "activity");
        Context applicationContext = activity.getApplicationContext();
        Intrinsics.b(applicationContext, "context");
        WbSdk.install(applicationContext, new AuthInfo(applicationContext, getAppId(applicationContext), getRedirectUri(applicationContext), ""));
        this.mSsoHandler = new SsoHandler(activity);
        SsoHandler ssoHandler = this.mSsoHandler;
        if (ssoHandler == null) {
            Intrinsics.c("mSsoHandler");
        }
        ssoHandler.authorize(new WeiboSSOAuthProvider$startLogin$1(this, activity));
    }
}
