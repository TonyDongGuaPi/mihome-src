package com.xiaomi.smarthome.miio.device;

import android.os.Build;
import com.xiaomi.smarthome.application.CommonApplication;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthomedevice.R;

public class PhoneDevice extends Device {
    /* access modifiers changed from: package-private */
    public void b() {
    }

    public PhoneDevice() {
        this.name = CommonApplication.getAppContext().getString(R.string.my_phone_name);
        this.pid = 1;
        this.model = "xiaomi.myphone.v1";
        this.did = Build.MODEL;
        a();
    }

    public void a() {
        this.canAuth = false;
        setOwner(true);
        this.isOnline = true;
        b();
    }
}
