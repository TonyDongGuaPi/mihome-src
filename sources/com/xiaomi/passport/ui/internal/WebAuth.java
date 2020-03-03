package com.xiaomi.passport.ui.internal;

import android.support.v4.app.Fragment;
import com.xiaomi.accountsdk.account.URLs;
import com.xiaomi.accountsdk.utils.XMPassportUtil;
import com.xiaomi.passport.snscorelib.internal.request.SNSRequest;
import com.xiaomi.passport.ui.internal.WebAuthFragment;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0002J\u0006\u0010\u0007\u001a\u00020\u0004J\u000e\u0010\b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0006\u0010\t\u001a\u00020\u0004J\u0018\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00062\b\u0010\f\u001a\u0004\u0018\u00010\u0006J\u000e\u0010\r\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u000e\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u000e\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0013¨\u0006\u0014"}, d2 = {"Lcom/xiaomi/passport/ui/internal/WebAuth;", "", "()V", "getAuthFragment", "Lcom/xiaomi/passport/ui/internal/SignInFragment;", "url", "", "getFindPswFragment", "getNotificationFragment", "getSignInFragment", "getSignUpFragment", "sid", "region", "getSnsBindFragment", "e", "Lcom/xiaomi/passport/ui/internal/NeedBindSnsException;", "getSnsWebLoginFragment", "Lcom/xiaomi/passport/snscorelib/internal/request/SNSRequest$RedirectToWebLoginException;", "getUserSettingFragment", "Landroid/support/v4/app/Fragment;", "passportui_release"}, k = 1, mv = {1, 1, 10})
public final class WebAuth {
    @NotNull
    public final SignInFragment getSignInFragment() {
        String str = URLs.ACCOUNT_DOMAIN;
        Intrinsics.b(str, "url");
        return getAuthFragment(str);
    }

    @NotNull
    public final SignInFragment getFindPswFragment() {
        return getAuthFragment(URLs.ACCOUNT_DOMAIN + "/pass/forgetPassword");
    }

    @NotNull
    public final SignInFragment getSignUpFragment(@NotNull String str, @Nullable String str2) {
        Intrinsics.f(str, "sid");
        String str3 = URLs.ACCOUNT_DOMAIN + "/pass/register?sid=" + str;
        if (str2 != null) {
            str3 = str3 + "&_uRegion=" + str2;
        }
        return getAuthFragment(str3);
    }

    @NotNull
    public final SignInFragment getNotificationFragment(@NotNull String str) {
        Intrinsics.f(str, "url");
        return WebAuthFragment.Companion.newInstance(str);
    }

    @NotNull
    public final SignInFragment getSnsBindFragment(@NotNull NeedBindSnsException needBindSnsException) {
        Intrinsics.f(needBindSnsException, "e");
        return SnsBindSignInFragment.Companion.newInstance(needBindSnsException);
    }

    @NotNull
    public final SignInFragment getSnsWebLoginFragment(@NotNull SNSRequest.RedirectToWebLoginException redirectToWebLoginException) {
        Intrinsics.f(redirectToWebLoginException, "e");
        return SnsWebLoginFragment.Companion.newInstance(redirectToWebLoginException);
    }

    @NotNull
    public final Fragment getUserSettingFragment() {
        return PassportWebFragment.Companion.newInstance(URLs.ACCOUNT_DOMAIN + "/pass/auth/security/home");
    }

    private final SignInFragment getAuthFragment(String str) {
        String buildUrlWithLocaleQueryParam = XMPassportUtil.buildUrlWithLocaleQueryParam(str);
        WebAuthFragment.Companion companion = WebAuthFragment.Companion;
        Intrinsics.b(buildUrlWithLocaleQueryParam, "urlWithLocale");
        return companion.newInstance(buildUrlWithLocaleQueryParam);
    }
}
