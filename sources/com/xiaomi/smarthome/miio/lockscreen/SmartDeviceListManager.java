package com.xiaomi.smarthome.miio.lockscreen;

import android.app.Activity;
import android.view.View;
import com.xiaomi.smarthome.R;
import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.device.RouterDevice;
import com.xiaomi.smarthome.device.SmartHomeDeviceHelper;
import com.xiaomi.smarthome.device.utils.LockedDeviceAdapter;
import com.xiaomi.smarthome.library.common.widget.AnimateFakeList;
import com.xiaomi.smarthome.miio.activity.ClientAllLockedActivity;
import java.util.ArrayList;
import java.util.List;

@Deprecated
public class SmartDeviceListManager {

    /* renamed from: a  reason: collision with root package name */
    static String f13614a = "SmartDeviceListManager";
    public static volatile boolean b = false;
    AnimateFakeList c;
    LockedDeviceAdapter d;
    List<Device> e;

    public void a(View view, ClientAllLockedDialog clientAllLockedDialog) {
        this.c = (AnimateFakeList) view.findViewById(R.id.device_grid_view);
        this.d = new LockedDeviceAdapter(clientAllLockedDialog.c, this.c);
    }

    public void a() {
        this.e = SmartHomeDeviceHelper.a().m();
        this.d.a(this.e);
    }

    public static boolean a(List<Device> list, List<Device> list2) {
        if (list == null && list2 == null) {
            return true;
        }
        if (list == null || list2 == null || list.size() != list2.size()) {
            return false;
        }
        for (int i = 0; i < list.size(); i++) {
            Device device = list.get(i);
            Device device2 = list2.get(i);
            if ((device != null || device2 != null) && (device == null || device2 == null || !device.equals(device2))) {
                return false;
            }
        }
        return true;
    }

    public int b() {
        if (this.e == null || this.e.size() <= 0) {
            return 0;
        }
        return this.e.size();
    }

    public void a(Activity activity) {
        this.d.b();
    }

    public void a(ClientAllLockedActivity clientAllLockedActivity) {
        this.c.setAdapter(this.d, clientAllLockedActivity);
    }

    public List<String> c() {
        if (this.e == null || this.e.size() <= 0) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < this.e.size(); i++) {
            Device device = this.e.get(i);
            if (device instanceof RouterDevice) {
                arrayList.add(device.did);
            }
        }
        if (arrayList.size() > 0) {
            return arrayList;
        }
        return null;
    }

    public List<Device> d() {
        return this.e;
    }

    public Device a(int i) {
        if (b() > 0 && b() > i) {
            return (Device) this.d.getItem(i);
        }
        return null;
    }

    public void a(boolean z) {
        b = z;
        if (b() > 0) {
            this.d.a(z);
        }
    }

    public boolean e() {
        return this.c != null && this.c.mIsAnimating;
    }
}
