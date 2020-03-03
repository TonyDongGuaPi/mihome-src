package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.webkit.WebView;
import com.xiaomi.accountsdk.account.data.AccountInfo;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000-\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u0012\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016J\u0012\u0010\r\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016¨\u0006\u000e"}, d2 = {"com/xiaomi/passport/ui/internal/SnsBindSignInFragment$onCreateView$1", "Lcom/xiaomi/passport/ui/internal/PassportWebView;", "(Lcom/xiaomi/passport/ui/internal/SnsBindSignInFragment;Landroid/content/Context;)V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "onSnsBindCancel", "", "accountInfo", "Lcom/xiaomi/accountsdk/account/data/AccountInfo;", "onSnsBindFinished", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class SnsBindSignInFragment$onCreateView$1 extends PassportWebView {
    final /* synthetic */ SnsBindSignInFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SnsBindSignInFragment$onCreateView$1(SnsBindSignInFragment snsBindSignInFragment, Context context) {
        super(context);
        this.this$0 = snsBindSignInFragment;
    }

    public boolean onSnsBindCancel(@Nullable AccountInfo accountInfo) {
        if (accountInfo == null) {
            return true;
        }
        this.this$0.loginSuccess(accountInfo);
        return true;
    }

    public boolean onSnsBindFinished(@Nullable AccountInfo accountInfo) {
        if (accountInfo == null) {
            return true;
        }
        this.this$0.loginSuccess(accountInfo);
        return true;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
        this.this$0.dismissProgress();
    }
}
