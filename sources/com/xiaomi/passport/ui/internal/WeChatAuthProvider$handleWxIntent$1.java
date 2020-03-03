package com.xiaomi.passport.ui.internal;

import android.app.Activity;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\n\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0002J\u0012\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u0012\u0010\b\u001a\u00020\u00052\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0016¨\u0006\u000b"}, d2 = {"com/xiaomi/passport/ui/internal/WeChatAuthProvider$handleWxIntent$1", "Lcom/tencent/mm/opensdk/openapi/IWXAPIEventHandler;", "(Lcom/xiaomi/passport/ui/internal/WeChatAuthProvider;Landroid/app/Activity;)V", "getWXAPIEventHandler", "onReq", "", "req", "Lcom/tencent/mm/opensdk/modelbase/BaseReq;", "onResp", "resp", "Lcom/tencent/mm/opensdk/modelbase/BaseResp;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WeChatAuthProvider$handleWxIntent$1 implements IWXAPIEventHandler {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ WeChatAuthProvider this$0;

    WeChatAuthProvider$handleWxIntent$1(WeChatAuthProvider weChatAuthProvider, Activity activity) {
        this.this$0 = weChatAuthProvider;
        this.$activity = activity;
    }

    public void onReq(@Nullable BaseReq baseReq) {
        IWXAPIEventHandler wXAPIEventHandler = getWXAPIEventHandler();
        if (wXAPIEventHandler != null) {
            wXAPIEventHandler.onReq(baseReq);
        }
    }

    private final IWXAPIEventHandler getWXAPIEventHandler() {
        Object wXAPIEventHandler = PassportUI.INSTANCE.getWXAPIEventHandler();
        if (!(wXAPIEventHandler instanceof IWXAPIEventHandler)) {
            wXAPIEventHandler = null;
        }
        return (IWXAPIEventHandler) wXAPIEventHandler;
    }

    public void onResp(@Nullable BaseResp baseResp) {
        if (baseResp instanceof SendAuth.Resp) {
            SendAuth.Resp resp = (SendAuth.Resp) baseResp;
            if (Intrinsics.a((Object) resp.state, (Object) PassportUI.WX_API_STATE_PASSPORT) || baseResp.errCode != 0) {
                if (baseResp.errCode == 0) {
                    String str = resp.code;
                    Intrinsics.b(str, "resp.code");
                    this.this$0.storeSnsCode(this.$activity, str);
                    AuthSnsProviderKt.sendSnsBroadcast(this.$activity, "ok");
                    return;
                }
                AuthSnsProviderKt.sendSnsBroadcast(this.$activity, "error");
                return;
            }
        }
        IWXAPIEventHandler wXAPIEventHandler = getWXAPIEventHandler();
        if (wXAPIEventHandler != null) {
            wXAPIEventHandler.onResp(baseResp);
        }
    }
}
