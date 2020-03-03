package com.xiaomi.smarthome.device;

import android.content.Context;

public class ConfigFailedDevice extends Device {

    /* renamed from: a  reason: collision with root package name */
    public boolean f14813a = false;

    public boolean canBeDeleted() {
        return true;
    }

    public boolean canBeShared() {
        return false;
    }

    public boolean canRename() {
        return false;
    }

    public CharSequence getStatusDescription(Context context) {
        return null;
    }

    public boolean hasShortcut() {
        return false;
    }

    public boolean isBinded() {
        return true;
    }

    public boolean isNoneOperatableDevice() {
        return true;
    }

    public boolean isOpen() {
        return true;
    }

    public boolean isOwner() {
        return true;
    }

    public ConfigFailedDevice(String str, String str2) {
        this.did = str2;
        this.model = str;
        this.canAuth = false;
        setOwner(true);
        this.isOnline = true;
    }

    /* renamed from: a */
    public String getName() {
        return DeviceFactory.h(this.model);
    }
}
