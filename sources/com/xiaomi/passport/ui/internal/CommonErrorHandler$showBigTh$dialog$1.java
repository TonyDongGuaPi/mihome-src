package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.content.DialogInterface;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 10})
final class CommonErrorHandler$showBigTh$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ Context $context;
    final /* synthetic */ Throwable $tr;
    final /* synthetic */ CommonErrorHandler this$0;

    CommonErrorHandler$showBigTh$dialog$1(CommonErrorHandler commonErrorHandler, Context context, Throwable th) {
        this.this$0 = commonErrorHandler;
        this.$context = context;
        this.$tr = th;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.this$0.uploadLog(this.$context, this.$tr);
    }
}
