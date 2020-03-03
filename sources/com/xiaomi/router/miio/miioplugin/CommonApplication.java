package com.xiaomi.router.miio.miioplugin;

import android.app.Application;

@Deprecated
public class CommonApplication extends Application {
    private String mMac;

    @Deprecated
    public void setMacAddr(String str) {
    }

    @Deprecated
    public String getMacAddr() {
        return this.mMac;
    }
}
