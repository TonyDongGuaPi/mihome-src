package com.xiaomi.passport.ui.internal;

import android.graphics.Bitmap;
import com.taobao.weex.el.parse.Operators;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\u000b\u0010\r\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J)\u0010\u0010\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0005HÖ\u0001R\u0013\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/xiaomi/passport/ui/internal/Captcha;", "", "bitmap", "Landroid/graphics/Bitmap;", "ick", "", "captchaUrl", "(Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;)V", "getBitmap", "()Landroid/graphics/Bitmap;", "getCaptchaUrl", "()Ljava/lang/String;", "getIck", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class Captcha {
    @Nullable
    private final Bitmap bitmap;
    @NotNull
    private final String captchaUrl;
    @NotNull
    private final String ick;

    @NotNull
    public static /* synthetic */ Captcha copy$default(Captcha captcha, Bitmap bitmap2, String str, String str2, int i, Object obj) {
        if ((i & 1) != 0) {
            bitmap2 = captcha.bitmap;
        }
        if ((i & 2) != 0) {
            str = captcha.ick;
        }
        if ((i & 4) != 0) {
            str2 = captcha.captchaUrl;
        }
        return captcha.copy(bitmap2, str, str2);
    }

    @Nullable
    public final Bitmap component1() {
        return this.bitmap;
    }

    @NotNull
    public final String component2() {
        return this.ick;
    }

    @NotNull
    public final String component3() {
        return this.captchaUrl;
    }

    @NotNull
    public final Captcha copy(@Nullable Bitmap bitmap2, @NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "ick");
        Intrinsics.f(str2, "captchaUrl");
        return new Captcha(bitmap2, str, str2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Captcha)) {
            return false;
        }
        Captcha captcha = (Captcha) obj;
        return Intrinsics.a((Object) this.bitmap, (Object) captcha.bitmap) && Intrinsics.a((Object) this.ick, (Object) captcha.ick) && Intrinsics.a((Object) this.captchaUrl, (Object) captcha.captchaUrl);
    }

    public int hashCode() {
        Bitmap bitmap2 = this.bitmap;
        int i = 0;
        int hashCode = (bitmap2 != null ? bitmap2.hashCode() : 0) * 31;
        String str = this.ick;
        int hashCode2 = (hashCode + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.captchaUrl;
        if (str2 != null) {
            i = str2.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return "Captcha(bitmap=" + this.bitmap + ", ick=" + this.ick + ", captchaUrl=" + this.captchaUrl + Operators.BRACKET_END_STR;
    }

    public Captcha(@Nullable Bitmap bitmap2, @NotNull String str, @NotNull String str2) {
        Intrinsics.f(str, "ick");
        Intrinsics.f(str2, "captchaUrl");
        this.bitmap = bitmap2;
        this.ick = str;
        this.captchaUrl = str2;
    }

    @Nullable
    public final Bitmap getBitmap() {
        return this.bitmap;
    }

    @NotNull
    public final String getCaptchaUrl() {
        return this.captchaUrl;
    }

    @NotNull
    public final String getIck() {
        return this.ick;
    }
}
