package com.xiaomi.smarthome.core.server.internal.device;

import android.content.Context;
import com.xiaomi.smarthome.core.entity.device.Device;
import com.xiaomi.smarthome.core.entity.device.ScanType;
import com.xiaomi.smarthome.core.server.CoreService;
import com.xiaomi.smarthome.core.server.internal.device.DeviceSearch;
import com.xiaomi.smarthome.core.server.internal.device.MiioManager;
import com.xiaomi.smarthome.library.common.network.WifiUtil;
import java.util.List;

public class MiioDeviceSearch extends MiioDeviceSearchBase {

    /* renamed from: a  reason: collision with root package name */
    public static final int f14541a = 0;
    private Context c;

    public int b() {
        return 0;
    }

    public MiioDeviceSearch(Context context) {
        this.c = context;
    }

    public void a(ScanType scanType, final DeviceSearch.DeviceSearchCallback deviceSearchCallback) {
        if (scanType.ordinal() == ScanType.ALL.ordinal() && WifiUtil.a(CoreService.getAppContext()) != null) {
            MiioManager.a(this.c, new MiioManager.AsyncResponseCallback<List<Device>>() {
                public void a(List<Device> list) {
                    if (deviceSearchCallback != null) {
                        deviceSearchCallback.a(MiioDeviceSearch.this.b(), MiioDeviceSearch.this.b(list));
                    }
                }

                public void a(int i) {
                    if (deviceSearchCallback != null) {
                        deviceSearchCallback.a(i, (Object) null);
                    }
                }

                public void a(int i, Object obj) {
                    if (deviceSearchCallback != null) {
                        deviceSearchCallback.a(i, obj);
                    }
                }
            });
        } else if (deviceSearchCallback != null) {
            deviceSearchCallback.a(b(), b((List<Device>) null));
        }
    }
}
