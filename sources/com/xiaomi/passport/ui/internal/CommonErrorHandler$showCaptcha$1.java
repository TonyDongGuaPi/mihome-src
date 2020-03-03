package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import com.xiaomi.accountsdk.utils.AccountLog;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class CommonErrorHandler$showCaptcha$1 implements View.OnClickListener {
    final /* synthetic */ Captcha $captcha;
    final /* synthetic */ ImageView $captchaImg;
    final /* synthetic */ Context $context;
    final /* synthetic */ Ref.ObjectRef $lastIck;
    final /* synthetic */ CommonErrorHandler this$0;

    CommonErrorHandler$showCaptcha$1(CommonErrorHandler commonErrorHandler, Captcha captcha, ImageView imageView, Ref.ObjectRef objectRef, Context context) {
        this.this$0 = commonErrorHandler;
        this.$captcha = captcha;
        this.$captchaImg = imageView;
        this.$lastIck = objectRef;
        this.$context = context;
    }

    public final void onClick(View view) {
        this.this$0.getCaptcha(this.$captcha.getCaptchaUrl()).get(new Function1<Captcha, Unit>(this) {
            final /* synthetic */ CommonErrorHandler$showCaptcha$1 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Captcha) obj);
                return Unit.f2693a;
            }

            public final void invoke(@NotNull Captcha captcha) {
                Intrinsics.f(captcha, "it");
                this.this$0.$captchaImg.setImageBitmap(captcha.getBitmap());
                this.this$0.$lastIck.element = captcha.getIck();
            }
        }, new Function1<Throwable, Unit>(this) {
            final /* synthetic */ CommonErrorHandler$showCaptcha$1 this$0;

            {
                this.this$0 = r1;
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Throwable) obj);
                return Unit.f2693a;
            }

            public final void invoke(@NotNull Throwable th) {
                Intrinsics.f(th, "it");
                AccountLog.e("Passport", "captcha", th);
                if (th instanceof IOException) {
                    this.this$0.this$0.onIOError((IOException) th, this.this$0.$context);
                } else {
                    this.this$0.this$0.onUnKnowError(th, this.this$0.$context);
                }
            }
        });
    }
}
