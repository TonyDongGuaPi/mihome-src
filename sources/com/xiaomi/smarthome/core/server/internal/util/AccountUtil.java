package com.xiaomi.smarthome.core.server.internal.util;

import android.text.TextUtils;
import com.xiaomi.youpin.login.entity.account.MiServiceTokenInfo;

public class AccountUtil {
    public static boolean a(MiServiceTokenInfo miServiceTokenInfo) {
        return miServiceTokenInfo != null && !TextUtils.isEmpty(miServiceTokenInfo.f23514a) && !TextUtils.isEmpty(miServiceTokenInfo.c) && !TextUtils.isEmpty(miServiceTokenInfo.d) && !TextUtils.isEmpty(miServiceTokenInfo.f);
    }
}
