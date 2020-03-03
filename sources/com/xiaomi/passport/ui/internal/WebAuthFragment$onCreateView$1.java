package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import com.xiaomi.passport.utils.AuthenticatorUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\u001c\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0016¨\u0006\u000e"}, d2 = {"com/xiaomi/passport/ui/internal/WebAuthFragment$onCreateView$1", "Lcom/xiaomi/passport/ui/internal/PassportWebView;", "(Lcom/xiaomi/passport/ui/internal/WebAuthFragment;Landroid/content/Context;)V", "onLoginEnd", "", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "onNeedReLogin", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WebAuthFragment$onCreateView$1 extends PassportWebView {
    final /* synthetic */ WebAuthFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    WebAuthFragment$onCreateView$1(WebAuthFragment webAuthFragment, Context context) {
        super(context);
        this.this$0 = webAuthFragment;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
        this.this$0.dismissProgress();
    }

    public boolean onLoginEnd(@NotNull AccountInfo accountInfo) {
        Intrinsics.f(accountInfo, "accountInfo");
        AuthenticatorUtil.addOrUpdateAccountManager(getContext(), accountInfo);
        this.this$0.loginSuccess(accountInfo);
        return true;
    }

    public boolean onNeedReLogin() {
        AddAccountListener addAccountListener = this.this$0.getAddAccountListener();
        if (addAccountListener != null) {
            addAccountListener.goBack(true);
        }
        return true;
    }
}
