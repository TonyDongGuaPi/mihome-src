package com.sina.deviceidjnisdk;

import android.content.Context;

public class DeviceId implements IDeviceId {

    /* renamed from: a  reason: collision with root package name */
    private Context f8814a;

    private native String getDeviceIdNative(Context context, String str, String str2, String str3);

    static {
        System.loadLibrary("weibosdkcore");
    }

    protected DeviceId(Context context) {
        this.f8814a = context.getApplicationContext();
    }

    public String a() {
        return getDeviceIdNative(this.f8814a, DeviceInfo.c(this.f8814a), DeviceInfo.d(this.f8814a), DeviceInfo.b(this.f8814a));
    }

    private String a(String str, String str2, String str3) {
        return str + str2 + str3;
    }
}
