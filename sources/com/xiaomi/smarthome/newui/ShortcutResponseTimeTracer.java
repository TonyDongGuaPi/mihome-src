package com.xiaomi.smarthome.newui;

import com.xiaomi.smarthome.device.Device;
import com.xiaomi.smarthome.stat.STAT;
import java.util.HashMap;
import java.util.Map;

public class ShortcutResponseTimeTracer {

    /* renamed from: a  reason: collision with root package name */
    private static Map<String, Long> f20372a = new HashMap();

    private ShortcutResponseTimeTracer() {
    }

    public static void a(Device device, int i, int i2) {
        if (device != null) {
            long currentTimeMillis = System.currentTimeMillis();
            if (i == 0) {
                f20372a.put(device.did, Long.valueOf(currentTimeMillis));
                return;
            }
            Long l = null;
            if (i == 1) {
                l = f20372a.get(device.did);
            } else if (i == 2) {
                l = f20372a.get(device.did);
            }
            f20372a.remove(device.did);
            if (l != null) {
                STAT.d.a(i2, device.model, System.currentTimeMillis() - l.longValue());
            }
        }
    }
}
