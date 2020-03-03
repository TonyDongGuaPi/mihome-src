package com.xiaomi.smarthome.miio.device;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.core.entity.plugin.PluginRecord;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.frame.core.CoreApi;
import java.util.concurrent.atomic.AtomicInteger;

public class TemporaryDevice extends Device {

    /* renamed from: a  reason: collision with root package name */
    public static final String f13574a = "temprory_device";
    private static AtomicInteger b = new AtomicInteger(-1);

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

    public TemporaryDevice(String str) {
        this.model = str;
        this.did = f13574a + b.addAndGet(1);
        this.canAuth = false;
        setOwner(true);
        this.isOnline = true;
        PluginRecord d = CoreApi.a().d(str);
        if (d != null) {
            this.name = d.p();
        }
    }

    public CharSequence getStatusDescription(Context context) {
        return TextUtils.isEmpty(this.desc) ? super.getStatusDescription(context) : this.desc;
    }
}
