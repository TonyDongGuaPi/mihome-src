package com.xiaomi.passport.ui.internal;

import android.graphics.Bitmap;
import android.util.Pair;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lcom/xiaomi/passport/ui/internal/Captcha;", "invoke"}, k = 3, mv = {1, 1, 10})
final class PassportRepoImpl$getCaptchaImage$1 extends Lambda implements Function0<Captcha> {
    final /* synthetic */ String $url;
    final /* synthetic */ PassportRepoImpl this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PassportRepoImpl$getCaptchaImage$1(PassportRepoImpl passportRepoImpl, String str) {
        super(0);
        this.this$0 = passportRepoImpl;
        this.$url = str;
    }

    @NotNull
    public final Captcha invoke() {
        Pair access$getCaptchaImageNullSafe = this.this$0.getCaptchaImageNullSafe(this.$url);
        Object obj = access$getCaptchaImageNullSafe.second;
        Intrinsics.b(obj, "captcha.second");
        return new Captcha((Bitmap) access$getCaptchaImageNullSafe.first, (String) obj, this.$url);
    }
}
