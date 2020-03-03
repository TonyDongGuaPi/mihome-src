package com.xiaomi.jr.account;

import android.os.Bundle;

public class XiaomiAccountUtils {
    public static String a(Bundle bundle) {
        return "errorCode=" + bundle.getString("errorCode") + ", errorMsg=" + bundle.getString("errorMessage") + ", intent=" + bundle.getParcelable("intent");
    }
}
