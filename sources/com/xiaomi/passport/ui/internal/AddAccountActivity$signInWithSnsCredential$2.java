package com.xiaomi.passport.ui.internal;

import android.widget.Toast;
import com.xiaomi.accountsdk.account.exception.NeedNotificationException;
import com.xiaomi.passport.snscorelib.internal.request.SNSRequest;
import com.xiaomi.passport.ui.R;
import com.xiaomi.passport.ui.internal.AddAccountListener;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 10})
final class AddAccountActivity$signInWithSnsCredential$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ AddAccountActivity this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AddAccountActivity$signInWithSnsCredential$2(AddAccountActivity addAccountActivity) {
        super(1);
        this.this$0 = addAccountActivity;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.f2693a;
    }

    public final void invoke(@NotNull Throwable th) {
        Intrinsics.f(th, "it");
        this.this$0.dismissProgress();
        if (th instanceof IOException) {
            this.this$0.showNetworkError((IOException) th);
        } else if (th instanceof NeedNotificationException) {
            AddAccountActivity addAccountActivity = this.this$0;
            WebAuth access$getMWebAuth$p = this.this$0.mWebAuth;
            String notificationUrl = ((NeedNotificationException) th).getNotificationUrl();
            Intrinsics.b(notificationUrl, "it.notificationUrl");
            addAccountActivity.gotoFragment(access$getMWebAuth$p.getNotificationFragment(notificationUrl), true);
        } else if (th instanceof SNSRequest.NeedLoginForBindException) {
            AddAccountListener.DefaultImpls.gotoFragment$default(this.this$0, this.this$0.defaultAuthProvider.getFragment(AddAccountActivity.access$getMSid$p(this.this$0), (String) null), false, 2, (Object) null);
        } else if (th instanceof SNSRequest.BindLimitException) {
            Toast.makeText(this.this$0, R.string.sns_bind_limit, 0).show();
        } else if (th instanceof SNSRequest.RedirectToWebLoginException) {
            this.this$0.gotoFragment(this.this$0.mWebAuth.getSnsWebLoginFragment((SNSRequest.RedirectToWebLoginException) th), true);
        } else {
            this.this$0.mCommonErrorHandler.onUnKnowError(th, this.this$0);
        }
    }
}
