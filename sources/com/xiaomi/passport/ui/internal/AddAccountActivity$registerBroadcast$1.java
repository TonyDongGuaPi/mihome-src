package com.xiaomi.passport.ui.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0016¨\u0006\t"}, d2 = {"com/xiaomi/passport/ui/internal/AddAccountActivity$registerBroadcast$1", "Landroid/content/BroadcastReceiver;", "(Lcom/xiaomi/passport/ui/internal/AddAccountActivity;)V", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class AddAccountActivity$registerBroadcast$1 extends BroadcastReceiver {
    final /* synthetic */ AddAccountActivity this$0;

    AddAccountActivity$registerBroadcast$1(AddAccountActivity addAccountActivity) {
        this.this$0 = addAccountActivity;
    }

    public void onReceive(@Nullable Context context, @Nullable Intent intent) {
        if (intent != null) {
            boolean a2 = Intrinsics.a((Object) intent.getStringExtra(SNSAuthProvider.EXTRA_KEY_SNS_RESULT), (Object) "ok");
            this.this$0.onSnsResultReturned(a2);
            if (!a2 && this.this$0.isSnsDirectlySignInMode()) {
                this.this$0.setLoginCancelledResult();
            }
        }
    }
}
