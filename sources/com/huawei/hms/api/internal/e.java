package com.huawei.hms.api.internal;

import android.content.Context;
import android.os.Build;
import com.huawei.hms.api.HuaweiApiAvailability;
import com.huawei.hms.c.a;
import com.huawei.hms.c.e;

public abstract class e {
    public static int a(Context context) {
        a.a(context, "context must not be null.");
        if (Build.VERSION.SDK_INT < 15) {
            return 21;
        }
        com.huawei.hms.c.e eVar = new com.huawei.hms.c.e(context);
        e.a a2 = eVar.a(HuaweiApiAvailability.SERVICES_PACKAGE);
        if (e.a.NOT_INSTALLED.equals(a2)) {
            return 1;
        }
        if (e.a.DISABLED.equals(a2)) {
            return 3;
        }
        if (!HuaweiApiAvailability.SERVICES_SIGNATURE.equalsIgnoreCase(eVar.d(HuaweiApiAvailability.SERVICES_PACKAGE))) {
            return 9;
        }
        return eVar.b(HuaweiApiAvailability.SERVICES_PACKAGE) < 20502300 ? 2 : 0;
    }
}
