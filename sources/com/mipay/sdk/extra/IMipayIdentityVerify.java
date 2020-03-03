package com.mipay.sdk.extra;

import android.app.Activity;
import android.app.Fragment;

public interface IMipayIdentityVerify {
    void identityVerify(Activity activity, String str, String str2);

    void identityVerify(Activity activity, String str, String str2, boolean z);

    void identityVerify(Fragment fragment, String str, String str2);

    void identityVerify(Fragment fragment, String str, String str2, boolean z);
}
