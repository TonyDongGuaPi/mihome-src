package com.xiaomi.smarthome.miio.page.devicetag;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.text.TextUtils;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.DeviceTagInterface;
import java.util.HashSet;
import java.util.Set;

public class DeviceTagAddAdapter extends DeviceTagAdapter {
    private String d;
    private String e;
    private Set<String> f;

    public DeviceTagAddAdapter(Activity activity, String str) {
        super(activity, str);
    }

    public void a(String str) {
        this.d = str;
        g();
    }

    /* access modifiers changed from: protected */
    public void b() {
        DeviceTagGroup deviceTagGroup;
        DeviceTagGroup deviceTagGroup2;
        this.b = DeviceTagGroupManager.a().b(this.d);
        if (!TextUtils.isEmpty(this.e)) {
            if (this.b != null && this.b.size() > 0 && (deviceTagGroup2 = (DeviceTagGroup) this.b.get(this.b.size() - 1)) != null && deviceTagGroup2.t == 4 && deviceTagGroup2.w != null && deviceTagGroup2.w.size() > 0) {
                for (DeviceTagChild next : deviceTagGroup2.w) {
                    if (TextUtils.equals(next.d, this.e)) {
                        next.h = true;
                    }
                }
            }
        } else if (this.f == null) {
            this.f = new HashSet();
            if (this.b != null && this.b.size() > 0 && (deviceTagGroup = (DeviceTagGroup) this.b.get(this.b.size() - 1)) != null && deviceTagGroup.t == 4 && deviceTagGroup.w != null && deviceTagGroup.w.size() > 0) {
                for (DeviceTagChild next2 : deviceTagGroup.w) {
                    if (next2.h) {
                        this.f.add(next2.d);
                    }
                }
            }
        }
    }

    public void b(String str) {
        if (!TextUtils.equals(this.e, str)) {
            this.e = str;
            boolean z = false;
            if (this.b != null && this.b.size() > 0) {
                DeviceTagInterface<Device> b = SmartHomeDeviceHelper.a().b();
                DeviceTagGroup deviceTagGroup = (DeviceTagGroup) this.b.get(this.b.size() - 1);
                if (deviceTagGroup != null && deviceTagGroup.t == 4 && deviceTagGroup.w != null && deviceTagGroup.w.size() > 0) {
                    boolean z2 = false;
                    boolean z3 = false;
                    for (DeviceTagChild next : deviceTagGroup.w) {
                        if (TextUtils.equals(next.d, str)) {
                            if (!next.h) {
                                z3 = true;
                            }
                            next.h = true;
                            z2 = true;
                        } else {
                            if (next.h) {
                                z3 = true;
                            }
                            next.h = false;
                            b.c(next.d, this.d);
                        }
                    }
                    z = !z2 ? true : z3;
                }
            }
            if (z) {
                notifyDataSetChanged();
                LocalBroadcastManager.getInstance(f()).sendBroadcast(new Intent(DeviceTagAddActivity.ACTION_TAG_SELECTED));
            }
        }
    }

    public String h() {
        return this.e;
    }

    public Set<String> i() {
        return this.f;
    }

    public void c(String str) {
        if (TextUtils.equals(this.e, str)) {
            this.e = null;
        }
        if (this.f != null && this.f.contains(str)) {
            this.f.remove(str);
        }
    }
}
