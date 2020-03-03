package com.xiaomi.jr.mipay.codepay.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;

public class CodePayActivity extends FragmentStackActivity {
    public static final String DEEPLINK_PREFIX = "https://api.jr.mi.com/app/codepay/activity?id=";

    /* access modifiers changed from: protected */
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        getWindow().addFlags(8192);
    }
}
