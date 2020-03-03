package com.xiaomi.passport.ui.internal;

import android.content.DialogInterface;
import android.widget.EditText;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Landroid/content/DialogInterface;", "<anonymous parameter 1>", "", "onClick"}, k = 3, mv = {1, 1, 10})
final class CommonErrorHandler$showCaptcha$dialog$1 implements DialogInterface.OnClickListener {
    final /* synthetic */ Function2 $callback;
    final /* synthetic */ EditText $captchaCode;
    final /* synthetic */ Ref.ObjectRef $lastIck;

    CommonErrorHandler$showCaptcha$dialog$1(Function2 function2, EditText editText, Ref.ObjectRef objectRef) {
        this.$callback = function2;
        this.$captchaCode = editText;
        this.$lastIck = objectRef;
    }

    public final void onClick(@NotNull DialogInterface dialogInterface, int i) {
        Intrinsics.f(dialogInterface, "<anonymous parameter 0>");
        this.$callback.invoke(this.$captchaCode.getText().toString(), (String) this.$lastIck.element);
    }
}
