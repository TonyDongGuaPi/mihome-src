package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.webkit.WebView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000%\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016J\u001a\u0010\t\u001a\u00020\n2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\u000b"}, d2 = {"com/xiaomi/passport/ui/internal/SnsAuthActivity$onCreate$2", "Lcom/xiaomi/passport/ui/internal/PassportWebView;", "(Lcom/xiaomi/passport/ui/internal/SnsAuthActivity;Landroid/content/Context;)V", "onPageFinished", "", "view", "Landroid/webkit/WebView;", "url", "", "shouldOverrideUrlLoading", "", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class SnsAuthActivity$onCreate$2 extends PassportWebView {
    final /* synthetic */ SnsAuthActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SnsAuthActivity$onCreate$2(SnsAuthActivity snsAuthActivity, Context context) {
        super(context);
        this.this$0 = snsAuthActivity;
    }

    public boolean shouldOverrideUrlLoading(@Nullable WebView webView, @NotNull String str) {
        Intrinsics.f(str, "url");
        String queryParameter = Uri.parse(str).getQueryParameter("code");
        if (queryParameter == null) {
            return super.shouldOverrideUrlLoading(webView, str);
        }
        Intent intent = new Intent();
        intent.putExtra("code", queryParameter);
        this.this$0.setResult(-1, intent);
        this.this$0.finish();
        return true;
    }

    public void onPageFinished(@Nullable WebView webView, @Nullable String str) {
        this.this$0.mProgressHolder.dismissProgress();
    }
}
