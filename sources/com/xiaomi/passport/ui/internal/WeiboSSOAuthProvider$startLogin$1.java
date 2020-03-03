package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0012\u0010\u0005\u001a\u00020\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\nH\u0016¨\u0006\u000b"}, d2 = {"com/xiaomi/passport/ui/internal/WeiboSSOAuthProvider$startLogin$1", "Lcom/sina/weibo/sdk/auth/WbAuthListener;", "(Lcom/xiaomi/passport/ui/internal/WeiboSSOAuthProvider;Landroid/app/Activity;)V", "cancel", "", "onFailure", "err", "Lcom/sina/weibo/sdk/auth/WbConnectErrorMessage;", "onSuccess", "token", "Lcom/sina/weibo/sdk/auth/Oauth2AccessToken;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WeiboSSOAuthProvider$startLogin$1 implements WbAuthListener {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ WeiboSSOAuthProvider this$0;

    WeiboSSOAuthProvider$startLogin$1(WeiboSSOAuthProvider weiboSSOAuthProvider, Activity activity) {
        this.this$0 = weiboSSOAuthProvider;
        this.$activity = activity;
    }

    public void onSuccess(@NotNull Oauth2AccessToken oauth2AccessToken) {
        Intrinsics.f(oauth2AccessToken, "token");
        if (oauth2AccessToken.isSessionValid()) {
            String token = oauth2AccessToken.getToken();
            Intrinsics.b(token, "token.token");
            this.this$0.storeSnsToken(this.$activity, token);
        }
        AuthSnsProviderKt.sendSnsBroadcast(this.$activity, "ok");
    }

    public void onFailure(@Nullable WbConnectErrorMessage wbConnectErrorMessage) {
        Context context = this.$activity;
        StringBuilder sb = new StringBuilder();
        sb.append("onFailure: ");
        String str = null;
        sb.append(wbConnectErrorMessage != null ? wbConnectErrorMessage.getErrorMessage() : null);
        sb.append(", ");
        if (wbConnectErrorMessage != null) {
            str = wbConnectErrorMessage.getErrorCode();
        }
        sb.append(str);
        Toast.makeText(context, sb.toString(), 1).show();
        AuthSnsProviderKt.sendSnsBroadcast(this.$activity, "error");
    }

    public void cancel() {
        Toast.makeText(this.$activity, "onCancel", 1).show();
        AuthSnsProviderKt.sendSnsBroadcast(this.$activity, "cancelled");
    }
}
