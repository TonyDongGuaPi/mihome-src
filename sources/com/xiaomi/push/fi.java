package com.xiaomi.push;

import android.text.TextUtils;
import com.xiaomi.mipush.sdk.MiPushClient;

public enum fi {
    COMMAND_REGISTER(MiPushClient.f11511a),
    COMMAND_UNREGISTER(MiPushClient.b),
    COMMAND_SET_ALIAS(MiPushClient.c),
    COMMAND_UNSET_ALIAS(MiPushClient.d),
    COMMAND_SET_ACCOUNT(MiPushClient.e),
    COMMAND_UNSET_ACCOUNT(MiPushClient.f),
    COMMAND_SUBSCRIBE_TOPIC(MiPushClient.g),
    COMMAND_UNSUBSCRIBE_TOPIC(MiPushClient.h),
    COMMAND_SET_ACCEPT_TIME(MiPushClient.i),
    COMMAND_CHK_VDEVID("check-vdeviceid");
    

    /* renamed from: a  reason: collision with other field name */
    public final String f70a;

    private fi(String str) {
        this.f70a = str;
    }

    public static int a(String str) {
        int i = -1;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (fi fiVar : values()) {
            if (fiVar.f70a.equals(str)) {
                i = fc.a((Enum) fiVar);
            }
        }
        return i;
    }
}
