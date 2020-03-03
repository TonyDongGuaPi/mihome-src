package com.xiaomi.passport.ui.internal;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import com.xiaomi.passport.ui.internal.PhoneNumUtil;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 2}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Landroid/view/View;", "kotlin.jvm.PlatformType", "onClick"}, k = 3, mv = {1, 1, 10})
final class PswSignInFragment$onViewCreated$3 implements View.OnClickListener {
    final /* synthetic */ PswSignInFragment this$0;

    PswSignInFragment$onViewCreated$3(PswSignInFragment pswSignInFragment) {
        this.this$0 = pswSignInFragment;
    }

    public final void onClick(View view) {
        PswSignInFragment pswSignInFragment = this.this$0;
        WebAuth mWebAuth = this.this$0.getMWebAuth();
        Bundle arguments = this.this$0.getArguments();
        if (arguments == null) {
            Intrinsics.a();
        }
        String string = arguments.getString("sid");
        Intrinsics.b(string, "arguments!!.getString(\"sid\")");
        Context context = this.this$0.getContext();
        if (context == null) {
            Intrinsics.a();
        }
        PhoneNumUtil.CountryPhoneNumData smartGetCountryCodeData = CountryCodeUtilsExtensionKt.smartGetCountryCodeData(context, this.this$0.getDefaultCountryCodeWithPrefix());
        pswSignInFragment.gotoFragment(mWebAuth.getSignUpFragment(string, smartGetCountryCodeData != null ? smartGetCountryCodeData.countryISO : null), true);
    }
}
