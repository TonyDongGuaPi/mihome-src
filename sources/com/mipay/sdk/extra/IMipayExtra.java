package com.mipay.sdk.extra;

import android.app.Activity;
import android.app.Fragment;

public interface IMipayExtra {
    void simpleBindCard(Activity activity, String str);

    void simpleBindCard(Fragment fragment, String str);
}
