package com.xiaomi.passport.ui.internal;

import com.xiaomi.passport.servicetoken.ServiceTokenResult;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/xiaomi/passport/servicetoken/ServiceTokenResult;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 10})
final class PassportWebView$onReceivedLoginRequest$2 extends Lambda implements Function1<ServiceTokenResult, Unit> {
    final /* synthetic */ PassportWebView this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportWebView$onReceivedLoginRequest$2(PassportWebView passportWebView) {
        super(1);
        this.this$0 = passportWebView;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ServiceTokenResult) obj);
        return Unit.f2693a;
    }

    public final void invoke(ServiceTokenResult serviceTokenResult) {
        if (serviceTokenResult.errorCode == ServiceTokenResult.ErrorCode.ERROR_NONE) {
            PassportWebView passportWebView = this.this$0;
            String str = serviceTokenResult.serviceToken;
            Intrinsics.b(str, "it.serviceToken");
            passportWebView.loadUrl(str);
        }
    }
}
