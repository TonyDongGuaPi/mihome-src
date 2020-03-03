package com.xiaomi.smarthome.device.renderer;

import android.content.Context;
import android.text.TextUtils;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.BleDevice;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.DeviceFactory;
import com.xiaomi.smarthome.device.MiTVDevice;
import com.xiaomi.smarthome.frame.core.CoreApi;
import com.xiaomi.smarthome.globalsetting.GlobalSetting;
import com.xiaomi.smarthome.miio.activity.HomeLogContants;
import com.xiaomi.smarthome.miio.camera.match.CameraDevice;
import com.xiaomi.smarthome.miio.device.PhoneDevice;
import com.xiaomi.smarthome.miio.device.PhoneIRDevice;
import com.xiaomi.smarthome.wificonfig.WifiScanHomelog;

public class SubTitleHelper {

    /* renamed from: a  reason: collision with root package name */
    private Device f15411a;

    public SubTitleHelper(Device device) {
        this.f15411a = device;
    }

    public String a(Context context, boolean z) {
        if (this.f15411a instanceof BleDevice) {
            return f(context, z);
        }
        if (this.f15411a instanceof CameraDevice) {
            return e(context, z);
        }
        if (this.f15411a instanceof MiTVDevice) {
            return d(context, z);
        }
        if (this.f15411a instanceof PhoneDevice) {
            return c(context, z);
        }
        if (this.f15411a instanceof PhoneIRDevice) {
            return b(context, z);
        }
        return g(context, z);
    }

    private String b(Context context, boolean z) {
        return (String) this.f15411a.getStatusDescription(context);
    }

    private String c(Context context, boolean z) {
        if (!CoreApi.a().q()) {
            return context.getString(R.string.login_to_use_location_log);
        }
        String m = WifiScanHomelog.m();
        if (TextUtils.isEmpty(m)) {
            return context.getString(R.string.no_location_log);
        }
        if (m.equals("home")) {
            return context.getString(R.string.header_title_home);
        }
        if (m.equals("office")) {
            return context.getString(R.string.header_title_office);
        }
        if (m.equals(HomeLogContants.j)) {
            return context.getString(R.string.header_title_outside);
        }
        return context.getString(R.string.no_location_log);
    }

    private String d(Context context, boolean z) {
        String str = (String) this.f15411a.getStatusDescription(context);
        if (z || !GlobalSetting.w) {
            return str;
        }
        return str + " " + this.f15411a.did;
    }

    private String e(Context context, boolean z) {
        String str = (String) this.f15411a.getStatusDescription(context);
        if (this.f15411a.isShared()) {
            str = str + " " + context.getString(R.string.comefrom_device) + this.f15411a.ownerName;
        }
        if (z || !GlobalSetting.w) {
            return str;
        }
        return str + " " + this.f15411a.did;
    }

    private String f(Context context, boolean z) {
        if (TextUtils.equals(this.f15411a.model, DeviceFactory.aO)) {
            return context.getString(R.string.list_device_tap_view);
        }
        if (!((BleDevice) this.f15411a).j() || this.f15411a.isOwner() || TextUtils.isEmpty(this.f15411a.ownerName)) {
            return "";
        }
        return context.getString(R.string.comefrom_device) + this.f15411a.ownerName;
    }

    private String g(Context context, boolean z) {
        String str;
        if (this.f15411a.model == null || !this.f15411a.model.equals("jomoo.toilet.ao3")) {
            if (!this.f15411a.isConnected) {
                return "";
            }
            if (!this.f15411a.isOnline) {
                str = context.getString(R.string.offline_device);
            } else {
                str = (String) this.f15411a.getStatusDescription(context);
            }
            if (this.f15411a.isShared()) {
                str = str + " " + context.getString(R.string.comefrom_device) + this.f15411a.ownerName;
            } else if (!this.f15411a.isBinded()) {
                str = context.getString(R.string.local_device);
            }
            if (this.f15411a.isOnline && this.f15411a.rssi < -70 && this.f15411a.pid == 0) {
                StringBuilder sb = new StringBuilder();
                sb.append(str);
                sb.append(TextUtils.isEmpty(str) ? "" : ", ");
                sb.append(context.getString(R.string.weak_device_signal));
                str = sb.toString();
            }
            if (z || !GlobalSetting.w) {
                return str;
            }
            if (!TextUtils.isEmpty(str) && str.contains(this.f15411a.did)) {
                return str;
            }
            return str + " " + this.f15411a.did;
        } else if (this.f15411a.isOnline && this.f15411a.isOpen()) {
            return context.getString(R.string.list_device_online);
        } else {
            if (this.f15411a.isOnline) {
                return context.getString(R.string.list_device_offline);
            }
            return context.getString(R.string.list_device_offline);
        }
    }
}
