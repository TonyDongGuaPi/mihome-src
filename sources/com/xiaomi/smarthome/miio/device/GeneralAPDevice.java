package com.xiaomi.smarthome.miio.device;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;

public class GeneralAPDevice extends Device {

    /* renamed from: a  reason: collision with root package name */
    public String f13561a;

    public boolean canBeDeleted() {
        return true;
    }

    public boolean canBeShared() {
        return false;
    }

    public boolean canRename() {
        return false;
    }

    public boolean hasShortcut() {
        return false;
    }

    public boolean isBinded() {
        return true;
    }

    public boolean isOpen() {
        return true;
    }

    public boolean isOwner() {
        return false;
    }

    public GeneralAPDevice(String str) {
        this.model = str;
        if (this.model.equals("fimi.camera.c1")) {
            this.did = "feimi_device";
        } else {
            this.did = "general_ap_device" + str;
        }
        this.canAuth = false;
        setOwner(true);
        this.isOnline = true;
        PluginRecord d = CoreApi.a().d(this.model);
        if (d != null) {
            this.name = d.p();
        }
    }

    public CharSequence getStatusDescription(Context context) {
        return TextUtils.isEmpty(this.desc) ? super.getStatusDescription(context) : this.desc;
    }
}
