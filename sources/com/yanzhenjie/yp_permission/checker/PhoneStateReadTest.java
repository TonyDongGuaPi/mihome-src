package com.yanzhenjie.yp_permission.checker;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

class PhoneStateReadTest implements PermissionTest {

    /* renamed from: a  reason: collision with root package name */
    private Context f2446a;

    PhoneStateReadTest(Context context) {
        this.f2446a = context;
    }

    public boolean a() throws Throwable {
        if (!this.f2446a.getPackageManager().hasSystemFeature("android.hardware.telephony")) {
            return true;
        }
        TelephonyManager telephonyManager = (TelephonyManager) this.f2446a.getSystemService("phone");
        if (telephonyManager.getPhoneType() == 0 || !TextUtils.isEmpty(telephonyManager.getDeviceId()) || !TextUtils.isEmpty(telephonyManager.getSubscriberId())) {
            return true;
        }
        return false;
    }
}
