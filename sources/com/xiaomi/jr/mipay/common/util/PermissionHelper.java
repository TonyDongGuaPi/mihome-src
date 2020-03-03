package com.xiaomi.jr.mipay.common.util;

import android.content.Context;
import com.xiaomi.jr.account.XiaomiAccountManager;
import com.xiaomi.jr.common.utils.AppUtils;
import com.xiaomi.jr.permission.PermissionManager;
import com.xiaomi.jr.permission.Request;

public class PermissionHelper {
    public static void a(Context context, Request.Callback callback) {
        PermissionManager.a(context, (AppUtils.c(context) || XiaomiAccountManager.a().d()) ? new String[]{"android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION"} : new String[]{"android.permission.GET_ACCOUNTS", "android.permission.READ_PHONE_STATE", "android.permission.ACCESS_FINE_LOCATION"}, callback);
    }
}
