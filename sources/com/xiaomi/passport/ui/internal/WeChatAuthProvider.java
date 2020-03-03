package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.widget.Toast;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.xiaomi.passport.ui.R;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\bH\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\b\u0010\n\u001a\u00020\u0004H\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\u0018\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\u0018\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u0004H\u0002J\u0010\u0010\u0014\u001a\u00020\r2\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u000e\u001a\u00020\u000fH\u0014¨\u0006\u0017"}, d2 = {"Lcom/xiaomi/passport/ui/internal/WeChatAuthProvider;", "Lcom/xiaomi/passport/ui/internal/SNSAuthProvider;", "()V", "getAppId", "", "context", "Landroid/content/Context;", "getIconRes", "", "getScope", "getState", "getTintColor", "handleWxIntent", "", "activity", "Landroid/app/Activity;", "intent", "Landroid/content/Intent;", "isPackageInstalled", "packageName", "isWxInstall", "startLogin", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public class WeChatAuthProvider extends SNSAuthProvider {
    @NotNull
    public String getScope() {
        return "snsapi_userinfo";
    }

    @NotNull
    public String getState() {
        return PassportUI.WX_API_STATE_PASSPORT;
    }

    public int getTintColor() {
        return -1;
    }

    public WeChatAuthProvider() {
        super(PassportUI.WECHAT_AUTH_PROVIDER);
    }

    @NotNull
    public String getAppId(@NotNull Context context) {
        Intrinsics.f(context, "context");
        String string = context.getString(R.string.wechat_application_id);
        Intrinsics.b(string, "context.getString(R.string.wechat_application_id)");
        return string;
    }

    public int getIconRes() {
        return R.drawable.sns_wechat_dark;
    }

    /* access modifiers changed from: protected */
    public void startLogin(@NotNull Activity activity) {
        Intrinsics.f(activity, "activity");
        Context context = activity;
        if (!isWxInstall(context)) {
            Toast.makeText(context, "未安装微信", 0).show();
            return;
        }
        String appId = getAppId(context);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, appId, true);
        createWXAPI.registerApp(appId);
        SendAuth.Req req = new SendAuth.Req();
        req.scope = getScope();
        req.state = getState();
        createWXAPI.sendReq(req);
    }

    public final boolean handleWxIntent(@NotNull Activity activity, @Nullable Intent intent) {
        Intrinsics.f(activity, "activity");
        Context context = activity;
        String appId = getAppId(context);
        IWXAPI createWXAPI = WXAPIFactory.createWXAPI(context, appId, true);
        createWXAPI.registerApp(appId);
        return createWXAPI.handleIntent(intent, new WeChatAuthProvider$handleWxIntent$1(this, activity));
    }

    private final boolean isWxInstall(Context context) {
        return isPackageInstalled(context, "com.tencent.mm");
    }

    private final boolean isPackageInstalled(Context context, String str) {
        try {
            return context.getPackageManager().getPackageInfo(str, 0) != null;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }
}
