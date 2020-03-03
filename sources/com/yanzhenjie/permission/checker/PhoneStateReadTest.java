package com.yanzhenjie.permission.checker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

class PhoneStateReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2417a;

    PhoneStateReadTest(Context context) {
        this.f2417a = context;
    }

    @SuppressLint({"HardwareIds"})
    public boolean a() throws Throwable {
        TelephonyManager telephonyManager = (TelephonyManager) this.f2417a.getSystemService("phone");
        return !TextUtils.isEmpty(telephonyManager.getDeviceId()) || !TextUtils.isEmpty(telephonyManager.getSubscriberId());
    }
}
