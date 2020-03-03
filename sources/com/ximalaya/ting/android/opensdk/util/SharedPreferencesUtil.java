package com.ximalaya.ting.android.opensdk.util;

import android.content.Context;
import com.ximalaya.ting.android.opensdk.constants.PreferenceConstantsInOpenSdk;

public class SharedPreferencesUtil extends BaseSharedPreferencesUtil {
    private static SharedPreferencesUtil b;

    public SharedPreferencesUtil(Context context, String str) {
        super(context, str);
    }

    public SharedPreferencesUtil(Context context, String str, int i) {
        super(context, str, i);
    }

    public static SharedPreferencesUtil a(Context context) {
        if (b == null) {
            b = new SharedPreferencesUtil(context, PreferenceConstantsInOpenSdk.u);
        }
        return b;
    }

    public static SharedPreferencesUtil b(Context context) {
        b = new SharedPreferencesUtil(context, PreferenceConstantsInOpenSdk.u);
        return b;
    }
}
