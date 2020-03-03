package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.webkit.WebView;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/xiaomi/passport/ui/internal/PassportWebFragment$onCreateView$1", "Lcom/xiaomi/passport/ui/internal/PassportWebView;", "(Lcom/xiaomi/passport/ui/internal/PassportWebFragment;Landroid/content/Context;)V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class PassportWebFragment$onCreateView$1 extends PassportWebView {
    final /* synthetic */ PassportWebFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportWebFragment$onCreateView$1(PassportWebFragment passportWebFragment, Context context) {
        super(context);
        this.this$0 = passportWebFragment;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
        this.this$0.mProgressHolder.dismissProgress();
    }
}
